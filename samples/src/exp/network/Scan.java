/**
 * <p>
 * Classname: exp.network.Scan
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
import java.util.Date;


public class Scan {

  public static void main(String[] args) {
    final String[][] strHostsIsReachable = {
      {"172.18.56.12"},
      {"172.18.56.13"},
      {"172.18.56.21"}
    };
    final String[][] strHostsSocket = {
      {"172.18.56.12", "80"},
      {"172.18.56.13", "80"},
      {"172.18.56.21", "80"}
    };

    for (String[] strHost : strHostsIsReachable) {
      final long before = System.currentTimeMillis();
      run(strHost);
      final long after = System.currentTimeMillis();
      System.out.println("It took " + (after - before) + " millis.");
    }
    System.out.println("---------------------------------------------------------");
    for (String[] strHost : strHostsSocket) {
      final long before = System.currentTimeMillis();
      run(strHost);
      final long after = System.currentTimeMillis();
      System.out.println("It took " + (after - before) + " millis.");
    }
  }


  public static void run(String[] args) {
    String usage = "java Probe <address> [<port>]";
    String hostAddress = "";
    int port;
    long timeToRespond = 0; // in milliseconds

    if (args.length < 1 || args.length > 2) {
      System.out.println("usage: " + usage);
      return;
    }

    try {
      hostAddress = args[0]; // copy the string
      if (args.length == 2) {
        port = Integer.parseInt(args[1]); // convert the integer
      } else {
        port = 80;
      }

      if (args.length == 1) {
        timeToRespond = test(hostAddress);
      } else {
        timeToRespond = test(hostAddress, port);
      }
    } catch (NumberFormatException e) {
      System.out.println("Problem with arguments, usage: " + usage);
      e.printStackTrace();
    }

    if (timeToRespond >= 0) {
      System.out.println(hostAddress + " responded in " + timeToRespond + " ms");
    } else {
      System.out.println("Failed");
    }

  }

  /**
   * Connect using layer3
   * <p>
   * @param hostAddress
   * <p>
   * @return delay if the specified host responded, -1 if failed
   */
  static long test(String hostAddress) {
    InetAddress inetAddress = null;
    Date start, stop;

    try {
      inetAddress = InetAddress.getByName(hostAddress);
    } catch (UnknownHostException e) {
      System.out.println("Problem, unknown host:");
      e.printStackTrace();
    }

    try {
      start = new Date();
      if (inetAddress.isReachable(2500)) {
        stop = new Date();
        return (stop.getTime() - start.getTime());
      }

    } catch (IOException e1) {
      System.out.println("Problem, a network error has occurred:");
      e1.printStackTrace();
    } catch (IllegalArgumentException e1) {
      System.out.println("Problem, timeout was invalid:");
      e1.printStackTrace();
    }

    return -1; // to indicate failure

  }

  /**
   * Connect using layer4 (sockets)
   * <p>
   * @param
   * @return delay if the specified host responded, -1 if failed
   */
  static long test(String hostAddress,
                   int port) {
    InetAddress inetAddress = null;
    InetSocketAddress socketAddress = null;
    SocketChannel sc = null;
    long timeToRespond = -1;
    Date start, stop;

    try {
      inetAddress = InetAddress.getByName(hostAddress);
    } catch (UnknownHostException e) {
      System.out.println("Problem, unknown host:");
      e.printStackTrace();
    }

    try {
      socketAddress = new InetSocketAddress(inetAddress, port);
    } catch (IllegalArgumentException e) {
      System.out.println("Problem, port may be invalid:");
      e.printStackTrace();
    }

    // Open the channel, set it to non-blocking, initiate connect
    try {
      sc = SocketChannel.open();
      sc.configureBlocking(true);
      start = new Date();

      if (sc.connect(socketAddress)) {
        stop = new Date();
        timeToRespond = (stop.getTime() - start.getTime());
      }
    } catch (IOException e) {
      System.out.println("Problem, connection could not be made:");
      e.printStackTrace();
    }

    try {
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return timeToRespond;
  }

}
