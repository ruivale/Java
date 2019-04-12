/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exp.java.net.sockets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author c2334
 */
public class SocketsTests {
  private static final int PORT = 4895;
  private static final String HOST = "localhost";


  public SocketsTests() {

  }

  private void server() throws Exception{
    ServerSocket serverSocket = new ServerSocket(PORT);

    // Blocks until a connection occurs
    Socket socket = serverSocket.accept();

    // Creates the array of bytes to hold the reading buffer
    byte[] bytesReceived = new byte[1024];

    // Creates the input stream from the socket
    InputStream inputStream = new BufferedInputStream(socket.getInputStream());

    // Creates the output stream from the socket
    OutputStream outputStream =
        new BufferedOutputStream(socket.getOutputStream());

    StringBuffer sbRec = new StringBuffer();

    // The next test (&& boolContinueListening) is made cause the
    // read(..) blocks and when it's released the life cicle boolean
    // variable, boolContinueListening, could be false and no action
    // should be made.
    while(inputStream.read(bytesReceived) > 0) {
      sbRec.append(new String(bytesReceived));
    }

    System.out.println("Received from socket: \'" +
        sbRec.toString() +
        "\'.");

    final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    final DataOutputStream dos = new DataOutputStream(bos);
    dos.writeInt(1);
    dos.flush();
    
    outputStream.write(bos.toByteArray());
    outputStream.flush();
    
    socket.close();
    serverSocket.close();
    
  }

  private void client() throws Exception{
    final Socket socket = new Socket(
        HOST,
        PORT);

    byte[] b2Send = "bytes to send".getBytes();
    socket.getOutputStream().write(b2Send);
    socket.getOutputStream().flush();

    final byte[] bReceived = new byte[4];
    socket.getInputStream().read(bReceived);

    socket.close();

    final String sReceived = new String(bReceived);
    System.out.println("sReceived: " + sReceived);

  }

  
  public static void main(String[] args) throws Exception {
    SocketsTests st = new SocketsTests();
    st.server();
    st.client();
  }

}
