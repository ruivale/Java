/**
 * <p>
 * Classname: exp.network.ReachableTest
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
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
package exp.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;


/**
 *
 * @author 2334
 */
public class ReachableTest {

  private static final int I_TIMEOUT = 3550;


  public static void main(String args[]) {
    final String[][] strHosts = {
      {"172.18.56.21", "80"},
      {"172.18.56.12", "80"},
      {"172.18.56.13", "80"},
      {"172.18.56.187", "50000"},
      {"172.18.56.12", "-1"},
      {"172.18.56.13", "-1"}
    };

    for (String[] strHost : strHosts) {
      final long before = System.currentTimeMillis();
      final int iPort = Integer.parseInt(strHost[1]);
      final boolean connected;
      String strMsg;

      if(iPort > 0){
        strMsg = "Layer4";
        connected = connect(strHost[0], iPort);

      }else{
        strMsg = "Layer3";
        connected = isReachable(strHost[0]);
      }
      final long after = System.currentTimeMillis();
      System.out.println(
          "Connected("+strHost[0]+":"+strHost[1]+")? "+connected+ " "+strMsg+
          " It took " + (after - before) +
          " millis. \n----------------------------------------------------");
    }

  }

  /**
   *
   * @param strHost
   */
  private static boolean isReachable(final String strHost) {
    boolean yes = true;

    try {
      final InetAddress address = InetAddress.getByName(strHost);
      yes = address.isReachable(I_TIMEOUT);

    } catch (UnknownHostException e) {
      System.err.println("Unable to lookup web.mit.edu");
    } catch (IOException e) {
      System.err.println("Unable to reach web.mit.edu");
    }

    return yes;
  }

  /**
   *
   * @param strHost
   * @param iPort
   * @return
   */
  private static boolean connect(final String strHost, final int iPort) {
    boolean connected = true;

    SocketChannel sc = null;

    try {
      final InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(strHost), iPort);
      sc = SocketChannel.open();
      sc.socket().connect(address, I_TIMEOUT);

    } catch (Exception x) {
      connected = false;

      //x.printStackTrace();
      System.err.println("Cannot connect to "+strHost+":"+iPort);

    } finally{
      if(sc != null && sc.socket() != null){
        try {
          sc.socket().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      if (sc != null) {
        try {
          sc.close();
        } catch (IOException xx) {
          xx.printStackTrace();
        }
      }
    }

    return connected;
  }
}
