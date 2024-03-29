package exp.nio;


import java.io.*;

import java.net.*;

import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.*;


/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c)
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Server {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** .. */
  private static final int HTTPD_PORT = 80;

  /** .. */
  public static boolean    verbose    = false;

  /** .. */
  public static Statistics statistics = new Statistics();

  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private AcceptThread    acceptor;

  /** .. */
  private ReadWriteThread responder;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new Server object.
   *
   * @param baseDirectory  Insert doc ...
   * @param port  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  public Server(
    File baseDirectory,
    int  port)
      throws Exception {
    Selector       acceptSelector = Selector.open();
    Selector       readSelector = Selector.open();
    ConnectionList connections  = new ConnectionList(readSelector);
    acceptor    = new AcceptThread(acceptSelector, connections, port);
    responder   = new ReadWriteThread(readSelector, connections, baseDirectory);   
    
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    int  port          = HTTPD_PORT;
    File baseDirectory = null;

    try {
      int argIndex = 0;

      if((args.length>argIndex) && "-v".equals(args[argIndex])) {
        verbose = true;
        argIndex++;
      }

      if(args.length>argIndex) {
        baseDirectory = new File(args[argIndex++]);
      }

      if(args.length>argIndex) {
        port = Integer.parseInt(args[argIndex++]);
      }

      Server s = new Server(baseDirectory, port);
      s.start();
    } catch(Exception ex) {
      System.err.println(ex);
    }
  }

  /**
   * Insert doc ...
   */
  public void start() {
    statistics.start();
    responder.start();
    acceptor.start();
  }
}


/**
 * AcceptThread waits for incoming connections.  When a connection is made, it
 * registers the new socket on the "readWriteSelector".
 */
class AcceptThread
    extends Thread {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private ConnectionList      acceptedConnections;

  /** .. */
  private Selector            connectSelector;

  /** .. */
  private ServerSocketChannel ssc;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new AcceptThread object.
   *
   * @param connectSelector  Insert doc ...
   * @param list  Insert doc ...
   * @param port  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  public AcceptThread(
    Selector       connectSelector,
    ConnectionList list,
    int            port)
      throws Exception {
    super("Acceptor");

    this.connectSelector       = connectSelector;
    this.acceptedConnections   = list;

    ssc = ServerSocketChannel.open();
    ssc.configureBlocking(false);

    InetSocketAddress address = new InetSocketAddress(port);
    ssc.socket()
       .bind(address);

    if(Server.verbose) {
      System.out.println("Bound to " + address);
    }

    ssc.register(this.connectSelector, SelectionKey.OP_ACCEPT);
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   */
  public void run() {
    while(true) {
      try {
        if(Server.verbose) {
          System.out.println("AcceptThread: Selecting");
        }

        connectSelector.select();

        acceptPendingConnections();
      } catch(Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  protected void acceptPendingConnections()
      throws Exception {
    Set readyKeys = connectSelector.selectedKeys();

    for(Iterator i = readyKeys.iterator(); i.hasNext();) {
      SelectionKey key = (SelectionKey)i.next();
      i.remove();

      ServerSocketChannel readyChannel = (ServerSocketChannel)key.channel();

      SocketChannel       incomingChannel = readyChannel.accept();

      if(Server.verbose) {
        System.out.println("AcceptThread: Connection from " +
          incomingChannel.socket().getInetAddress());
      }

      acceptedConnections.push(incomingChannel);
    }
  }
}


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
class ReadWriteThread
    extends Thread {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** .. */
  private static final int     READ_BUFFER_SIZE = 16;

  /** .. */
  private static final Integer INVALID_REQUEST = new Integer(400);

  /** .. */
  private static final Integer PAGE_NOT_FOUND = new Integer(404);

  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private ByteBuffer     readBuffer;

  /** .. */
  private Charset        ascii;

  /** .. */
  private CharsetDecoder asciiDecoder;

  /** .. */
  private ConnectionList acceptedConnections;

  /** .. */
  private File     baseDirectory;

  /** .. */
  private Selector readSelector;

  /** .. */
  private Selector     readWriteSelector;

  /** .. */
  private WeakHashMap  errorBufferCache = new WeakHashMap();

  /** .. */
  private WeakHashMap  fileCache = new WeakHashMap();

  /** .. */
  private ByteBuffer[] responseBuffers = new ByteBuffer[2];

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new ReadWriteThread object.
   *
   * @param readSelector  Insert doc ...
   * @param acceptedConnections  Insert doc ...
   * @param dir  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  public ReadWriteThread(
    Selector       readSelector,
    ConnectionList acceptedConnections,
    File           dir)
      throws Exception {
    super("Reader-Writer");

    this.readSelector          = readSelector;
    this.acceptedConnections   = acceptedConnections;
    this.baseDirectory         = dir;
    this.readBuffer            = ByteBuffer.allocateDirect(READ_BUFFER_SIZE);

    ascii                = Charset.forName("US-ASCII");
    asciiDecoder         = ascii.newDecoder();
    responseBuffers[0]   = initializeResponseHeader();

    if(Server.verbose) {
      System.out.println("Using " + baseDirectory.getAbsolutePath() +
        " as document root");
    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   */
  public void run() {
    while(true) {
      try {
        if(Server.verbose) {
          System.out.println("ReadWriteThread: Selecting");
        }

        registerNewChannels();

        int keysReady = readSelector.select();

        if(keysReady>0) {
          acceptPendingRequests();
        }
      } catch(Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  protected void acceptPendingRequests()
      throws Exception {
    Set readyKeys = readSelector.selectedKeys();

    for(Iterator i = readyKeys.iterator(); i.hasNext();) {
      SelectionKey key = (SelectionKey)i.next();
      i.remove();

      readRequest(key);
    }
  }

  /**
   * Insert doc ...
   *
   * @param request  Insert doc ...
   * @param channel  Insert doc ...
   *
   * @throws RequestException  Insert doc ...
   * @throws IOException  Insert doc ...
   */
  protected void handleCompletedRequest(
    String        request,
    SocketChannel channel)
      throws RequestException, IOException {
    StringTokenizer tok = new StringTokenizer(request);
    tok.nextToken(); // skip the method

    String path = tok.nextToken(); // grab the URI, ignore the rest

    sendFile(path, channel);

    Server.statistics.request();

    // This behaves like HTTP 1.0, close connection
    // after handling the request.
    channel.close();
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  protected ByteBuffer initializeResponseHeader()
      throws Exception {
    // Pre-load a "good" HTTP response as characters.
    CharBuffer chars = CharBuffer.allocate(88);
    chars.put("HTTP/1.1 200 OK\n");
    chars.put("Connection: close\n");
    chars.put("Server: Java New I/O Example\n");
    chars.put("Content-Type: text/html\n");
    chars.put("\n");
    chars.flip();

    // Translate the Unicode characters into ASCII bytes.
    ByteBuffer buffer = ascii.newEncoder()
                             .encode(chars);

    return buffer;
  }

  /**
   * Insert doc ...
   *
   * @param key  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  protected void readRequest(SelectionKey key)
      throws Exception {
    SocketChannel incomingChannel = (SocketChannel)key.channel();
    Socket        incomingSocket = incomingChannel.socket();

    try {
      int bytesRead = incomingChannel.read(readBuffer);
      readBuffer.flip();

      String result = asciiDecoder.decode(readBuffer)
                                  .toString();
      readBuffer.clear();

      StringBuffer requestString = (StringBuffer)key.attachment();
      requestString.append(result);

      if(result.endsWith("\n\n") || result.endsWith("\r\n\r\n")) {
        handleCompletedRequest(
          requestString.toString(),
          incomingChannel);
      }
    } catch(RequestException re) {
      sendError(incomingChannel, re);
    } catch(IOException ioe) {
      sendError(incomingChannel, RequestException.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  protected void registerNewChannels()
      throws Exception {
    SocketChannel channel;

    while(null!=(channel = acceptedConnections.removeFirst())) {
      channel.configureBlocking(false);
      channel.register(
        readSelector,
        SelectionKey.OP_READ,
        new StringBuffer());
    }
  }

  /**
   * Insert doc ...
   *
   * @param channel  Insert doc ...
   * @param error  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  protected void sendError(
    SocketChannel    channel,
    RequestException error)
      throws Exception {
    ByteBuffer buffer = null;
    Object     obj = errorBufferCache.get(error);

    if(obj==null) {
      if(Server.verbose) {
        System.out.println("Error cache miss");
      }

      CharBuffer chars = CharBuffer.allocate(64);
      chars.put("HTTP/1.0 " + error.getErrorCode() + " OK\n");
      chars.put("Connection: close\n");
      chars.put("Server: Java New I/O Example\n");
      chars.put("\n");
      chars.flip();

      // Translate the Unicode characters into ASCII bytes.
      buffer = ascii.newEncoder()
                    .encode(chars);

      errorBufferCache.put(error, buffer);
    } else {
      if(Server.verbose) {
        System.out.println("Error cache hit");
      }

      buffer = (ByteBuffer)obj;
      buffer.rewind();
    }

    channel.write(buffer);
  }

  /**
   * Locate the requested file and map it into memory.  Use a weak cache to
   * speed up heavily-requested files.
   *
   * @param uri  Insert doc ...
   * @param channel  Insert doc ...
   *
   * @throws RequestException  Insert doc ...
   * @throws IOException  Insert doc ...
   */
  protected void sendFile(
    String        uri,
    SocketChannel channel)
      throws RequestException, IOException {
    if(Server.verbose) {
      System.out.println("ReadWriteThread: Sending " + uri);
    }

    Object obj = fileCache.get(uri);

    if(obj==null) {
      Server.statistics.fileMiss();

      try {
        File            f   = new File(baseDirectory, uri);
        FileInputStream fis = new FileInputStream(f);
        FileChannel     fc  = fis.getChannel();

        int             fileSize = (int)fc.size();
        responseBuffers[1] = fc.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);
        fileCache.put(uri, responseBuffers[1]);
      } catch(FileNotFoundException fnfe) {
        throw RequestException.PAGE_NOT_FOUND;
      }
    } else {
      Server.statistics.fileHit();

      responseBuffers[1] = (MappedByteBuffer)obj;
      responseBuffers[1].rewind();
    }

    responseBuffers[0].rewind();
    channel.write(responseBuffers);
  }
}


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
class RequestException
    extends Exception {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** .. */
  public static final RequestException INVALID_REQUEST =
    new RequestException(400);

  /** .. */
  public static final RequestException PAGE_NOT_FOUND =
    new RequestException(404);

  /** .. */
  public static final RequestException INTERNAL_SERVER_ERROR =
    new RequestException(500);

  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private int errorCode;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new RequestException object.
   *
   * @param errorCode  Insert doc ...
   */
  public RequestException(int errorCode) {
    super();

    this.errorCode = errorCode;
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public int getErrorCode() {
    return errorCode;
  }

  /**
   * Insert doc ...
   *
   * @param that  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public boolean equals(Object that) {
    try {
      return this.errorCode==((RequestException)that).errorCode;
    } catch(ClassCastException cce) {
      return false;
    }
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public int hashCode() {
    return errorCode;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public String toString() {
    return "" + errorCode;
  }
}


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
class ConnectionList {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private LinkedList list             = new LinkedList();

  /** .. */
  private Selector selectorToNotify;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new ConnectionList object.
   *
   * @param sel  Insert doc ...
   */
  public ConnectionList(Selector sel) {
    this.selectorToNotify = sel;
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param newlyConnectedChannel  Insert doc ...
   */
  public synchronized void push(SocketChannel newlyConnectedChannel) {
    list.add(newlyConnectedChannel);
    selectorToNotify.wakeup();
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public synchronized SocketChannel removeFirst() {
    if(list.size()==0) {
      return null;
    }

    return (SocketChannel)list.removeFirst();
  }
}


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
class Statistics
    extends Thread {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private float fileCacheHits   = 0;

  /** .. */
  private float fileCacheMisses = 0;

  /** .. */
  private float requests  = 0;

  /** .. */
  private long startTime;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new Statistics object.
   */
  public Statistics() {
    super("Statistics");
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   */
  public void fileHit() {
    fileCacheHits++;
  }

  /**
   * Insert doc ...
   */
  public void fileMiss() {
    fileCacheMisses++;
  }

  /**
   * Insert doc ...
   */
  public void request() {
    requests++;
  }

  /**
   * Insert doc ...
   */
  public void run() {
    while(true) {
      try {
        sleep(30 * 1000);

        reportCacheHitRate();
        reportRequestsPerMinute();
      } catch(InterruptedException ie) {
        ;
      }
    }
  }

  /**
   * Insert doc ...
   */
  public void start() {
    startTime = System.currentTimeMillis();
    super.start();
  }

  /**
   * Insert doc ...
   */
  private void reportCacheHitRate() {
    float hitRate = fileCacheHits / (fileCacheHits + fileCacheMisses);
    System.out.println("Statistics: cache hit rate = " + hitRate);
  }

  /**
   * Insert doc ...
   */
  private void reportRequestsPerMinute() {
    long  millis      = System.currentTimeMillis() - startTime;
    float requestRate = (60 * 1000 * requests) / millis;
    System.out.println("Statistics: requests per minute = " + requestRate);
  }
}
