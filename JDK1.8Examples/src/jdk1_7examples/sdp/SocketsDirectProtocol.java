/**
 * <p>
 * Classname:  jdk1_7examples.sdp.SocketsDirectProtocol
 * </p>
 *
 * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich – Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_7examples.sdp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 25/Ago/2011, 18:55:34
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class SocketsDirectProtocol {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(SocketsDirectProtocol.class.getName());

  /**
   * The SocketsDirectProtocol default constuctor.
   */
  public SocketsDirectProtocol() {
  }

  /**
   * 
   * @return 
   */
  private boolean startServer() throws Exception {
    
    final AsynchronousServerSocketChannel ssc =
        AsynchronousServerSocketChannel.open().bind(new InetSocketAddress("localhost", 9999));
    Future<AsynchronousSocketChannel> accepted = ssc.accept();
    AsynchronousSocketChannel sc2 = accepted.get();

    ByteBuffer bb = ByteBuffer.allocateDirect(4096);
    Future<Integer> readFuture = sc2.read(bb);

    return true;
  }
  
  /**
   * 
   * @return 
   */
  private boolean startClient() throws Exception {
    
    AsynchronousSocketChannel sc = AsynchronousSocketChannel.open();
    Future connected = sc.connect(new InetSocketAddress("localhost", 9999));
    // later ensure we are connected.
    connected.get();

    ByteBuffer bb = ByteBuffer.allocateDirect(4096);
    // populate bb
    Future<Integer> writeFuture = sc.write(bb);

    return true;
  }
  
  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("SocketsDirectProtocol").append("").toString();
  }
  
  public static void main(final String[] args) {
    final SocketsDirectProtocol clazz = new SocketsDirectProtocol();
  }
}
