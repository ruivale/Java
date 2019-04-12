package exp.network.interfaces;


import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2334
 */
public class ObtainNetInterfaces {

  private static final Logger LOGGER = Logger.getLogger(ObtainNetInterfaces.class.getName());

  /**
   *
   */
  public ObtainNetInterfaces(){
    printNetworkInterfaces();
  }

  /**
   *
   */
  private void printNetworkInterfaces() {
    try {
      final Enumeration e = NetworkInterface.getNetworkInterfaces();

      while (e.hasMoreElements()) {
        NetworkInterface netface = (NetworkInterface) e.nextElement();

        System.out.println(netface.toString());
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "getIP() exception.", e);
    }
  }

  public static void main(final String[] args){
    final ObtainNetInterfaces clazz = new ObtainNetInterfaces();
  }
}
