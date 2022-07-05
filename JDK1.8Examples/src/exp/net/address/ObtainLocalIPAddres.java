package exp.net.address;


import java.net.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ObtainLocalIPAddres {
  public ObtainLocalIPAddres() {
    try {
      InetAddress inetAddressLocal = InetAddress.getLocalHost();
      System.out.println("inetAddress.getHostAddress()=" + inetAddressLocal.getHostAddress());
      System.out.println("inetAddress.getHostName()=" + inetAddressLocal.getHostName());

      System.out.println("inetAddressLocal.getCanonicalHostName()=" + inetAddressLocal.getCanonicalHostName());


    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ObtainLocalIPAddres obtainlocalipaddres = new ObtainLocalIPAddres();
  }
}
