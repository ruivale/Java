package jdk1_5examples.io.mac.obtain;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class with methods to get the MAC Address/es of the network card/s
 *
 * @author Felipe Lang
 */
public final class MacAddress {

  static private Pattern macPattern = Pattern.compile(
      ".*((:?[0-9a-f]{2}[-:]){5}[0-9a-f]{2}).*", Pattern.CASE_INSENSITIVE);

  static final String[] windowsCommand = {"ipconfig", "/all"};

  static final String[] linuxCommand = {"/sbin/ifconfig", "-a"};

  /**
   * Search the MAC addresses of al network cards
   *
   * @return the list of MAC addresses
   * @throws IOException
   */
  public final static List getMacAddresses()
      throws IOException {
    final List macAddressList = new ArrayList();

// Extract the MAC addresses from stdout
    BufferedReader reader = getMacAddressesReader();
    for (String line = null; (line = reader.readLine()) != null; ) {
      Matcher matcher = macPattern.matcher(line);
      if (matcher.matches()) {
        macAddressList.add(matcher.group(1).replaceAll("[-:]", ""));
      }
    }
    reader.close();

    return macAddressList;
  }

  /**
   * Find the MAC address of the first network card
   *
   * @return a MAC address
   * @throws IOException
   */
  public final static String getMacAddress()
      throws IOException {
    return getMacAddress(0);
  }

  /**
   * Search the MAC address of the network card number=index
   *
   * @param nicIndex
   * the number of the network card (0 is the first index)
   *
   * @return a MAC address
   * @throws IOException
   */
  public final static String getMacAddress(int nicIndex)
      throws IOException {
// Extract the MAC addresses from stdout
    BufferedReader reader = getMacAddressesReader();
    int nicCount = 0;
    for (String line = null; (line = reader.readLine()) != null; ) {
      Matcher matcher = macPattern.matcher(line);
      if (matcher.matches()) {
        if (nicCount == nicIndex) {
          reader.close();
          return matcher.group(1).replaceAll("[-:]", "");
        }
        nicCount++;
      }
    }
    reader.close();
    return null;
  }

  private static BufferedReader getMacAddressesReader()
      throws IOException {
    final String[] command;
    final String os = System.getProperty("os.name");

    if (os.startsWith("Windows")) {
      command = windowsCommand;
    } else if (os.startsWith("Linux")) {
      command = linuxCommand;
    } else {
      throw new IOException("Unknown operating system: " + os);
    }

    final Process process = Runtime.getRuntime().exec(command);

// Discard the stderr
    new Thread() {
      public void run() {
        try {
          InputStream errorStream = process.getErrorStream();
          while (errorStream.read() != -1) {
          }
          ; errorStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    }.start();

// Extract the MAC addresses from stdout
    return new BufferedReader(new InputStreamReader(process.getInputStream()));
  }

  public static void main(String args[]) {
    try {
      System.out.println(getMacAddress());
      System.out.println(getMacAddress(1));

      final List listAdd = getMacAddresses();
      String mac;

      for (int i = 0; i < listAdd.size(); i++) {
        mac = (String)listAdd.get(i);
        System.out.println(mac);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
