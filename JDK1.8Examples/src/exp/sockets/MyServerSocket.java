package exp.sockets;


// Very simple server that just
// echoes whatever the client sends.
import java.io.*;
import java.net.*;
import java.util.logging.Level;
//import com.ent.stv.oper.codecs.ConsoleVideoOldListener;
//import com.ent.util.Utils;
//import com.ent.stv.oper.codecs.SWCodecInt;


public class MyServerSocket {
//// Choose a port outside of the range 1-1024:
//  public static final int PORT = 8080;
//  private static boolean boolContinueListening = true;
//  private static int BUFFER_SIZE = 1024;
//  private static BufferedInputStream is;
//  private static BufferedOutputStream outputStream;
//  private static ServerSocket serverSocket;
//  private static Socket socket;
//
//  /**
//   *
//   */
//  MyServerSocket() {
//    try {
//      serverSocket = new ServerSocket(PORT);
//
//      while (boolContinueListening) { // 4 life ...
//        // Blocks until a connection occurs:
//        try {
//
//          System.out.println("SERVER - Accepting ...");
//
//          socket = serverSocket.accept();
//
//          try {
//            // Creates the array of bytes to hold the reading buffer
//            byte[] bytesReceived = new byte[BUFFER_SIZE];
//
//            // Creates the input stream from the socket
//            is = new BufferedInputStream(socket.getInputStream());
//
//            // Creates the output stream from the socket
//            outputStream = new BufferedOutputStream(socket.getOutputStream());
//
//            //while (boolContinueListening) {
//
//            System.out.println("SERVER - Reading ... s.available()=" +
//                               is.available() + ".");
//
//            // The next test (&& boolContinueListening) is made cause the
//            // read(..) blocks and when it's released the life cicle boolean
//            // variable, boolContinueListening, could be false.
//            while (is.read(bytesReceived) > 0 && boolContinueListening) {
//              System.out.println("SERVER - Received: " +
//                                 new String(bytesReceived).trim() + ".");
//            }
//            //}
//
//          } catch (Exception e) {
//            e.printStackTrace();
//
//          } finally {
//            if (is != null) {
//              try {
//                is.close();
//                System.out.println("SERVER - is close");
//              } catch (Exception ex) {
//                ex.printStackTrace();
//              }
//            }
//
//            if (outputStream != null) {
//              try {
//                outputStream.close();
//                System.out.println("SERVER - os close");
//              } catch (Exception ex) {
//                ex.printStackTrace();
//              }
//            }
//
//            if (socket != null) {
//              try {
//                socket.close();
//                System.out.println("SERVER - socket close");
//              } catch (Exception ex) {
//                ex.printStackTrace();
//              }
//            }
//
//          }
//
//        } catch (Exception ex) {
//          ex.printStackTrace();
//        }
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    } finally {
//
//      //
//      // HOUSE CLEANING
//      //
//
//
//      if (is != null) {
//        try {
//          is.close();
//          System.out.println("SERVER - is close");
//        } catch (Exception ex) {
//          ex.printStackTrace();
//        }
//      }
//
//      if (outputStream != null) {
//        try {
//          outputStream.close();
//          System.out.println("SERVER - os close");
//        } catch (Exception ex) {
//          ex.printStackTrace();
//        }
//      }
//
//      if (socket != null) {
//        try {
//          socket.close();
//          System.out.println("SERVER - socket close");
//        } catch (Exception ex) {
//          ex.printStackTrace();
//        }
//      }
//
//      if (serverSocket != null) {
//        try {
//          serverSocket.close();
//          System.out.println("SERVER - server socket close");
//        } catch (Exception ex) {
//          ex.printStackTrace();
//        }
//      }
//    }
//  }
//
//  public static void main(String[] args) {
//    (new Thread() {
//      public void run() {
//        new MyServerSocket();
//      }
//    }).start();
//
//    try {
//      Thread.sleep(3000);
//    } catch (Exception ex) {
//      ex.printStackTrace();
//    }
//
//    (new Thread() {
//      public void run() {
//        System.out.println("Starting new Client");
//        new ClientTests(111111111);
//        try {
//          Thread.sleep(2000);
//        } catch (Exception ex) {
//          ex.printStackTrace();
//        }
//        System.out.println("Starting new Client");
//        new ClientTests(888888888);
//      }
//    }).start();
//
//  }
//
//
//  static class ClientTests {
//    ClientTests(final int id) {
//      Socket socket = null;
//
//      try {
//        socket = new Socket("172.18.50.175", PORT);
//
//        System.out.println("CLIENT(" + id + ") - Created the socket.");
//
//        final OutputStream os = socket.getOutputStream();
//        final InputStream is = socket.getInputStream();
//
//        /*
//                 (new Thread() {
//          public void run() {
//            byte[] bytes = new byte[BUFFER_SIZE];
//            while (true) {
//              try {
//                if (is.read(bytes) > 0) {
//         System.out.println("CLIENT("+id+") - Received:" + new String(bytes) + ".");
//                }
//              } catch (Exception ex) {
//                ex.printStackTrace();
//                break;
//              }
//            }
//          }
//                 }).start();
//         */
//
//        for (int i = 0; i < 5; i++) {
//          Thread.sleep(250);
//          os.write(("Ole. Im the " + id + " client. ;-)").getBytes());
//          os.flush();
//          System.out.println("CLIENT(" + id + ") - Sended the data.");
//        }
//
//        System.out.println("CLIENT(" + id +
//                           ") - sleeping before closing stuff ...");
//        Thread.sleep(3000);
//        System.out.println("CLIENT(" + id + ") - ... awaked up");
//
//        os.close();
//        System.out.println("CLIENT(" + id + ") - os close");
//        is.close();
//        System.out.println("CLIENT(" + id + ") - is close");
//        socket.close();
//        System.out.println("CLIENT(" + id + ") - socket close");
//
//        boolContinueListening = false;
//
//      } catch (Exception ex) {
//        ex.printStackTrace();
//
//        /*
//               } finally {
//          System.out.println("closing...");
//          if (socket != null && socket.isConnected()) {
//            try {
//              socket.close();
//            } catch (Exception ex) {
//              ex.printStackTrace();
//            }
//          }
//         */
//      }
//    }
//  }
//

}
