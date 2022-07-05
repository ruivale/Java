/**
 * <p>
 * Classname: exp.util.os.DetermineWhichWindowsVersionWeROn
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
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

package exp.util.os;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on May 3, 2013, 3:15:16 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class DetermineWhichWindowsVersionWeROn {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(DetermineWhichWindowsVersionWeROn.class.getName());


 /**
  * The DetermineWhichWindowsVersionWeROn default constructor.
  */
  public DetermineWhichWindowsVersionWeROn(){
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("DetermineWhichWindowsVersionWeROn").append("").toString();
  }

public static void main(final String[] args) {
    System.out.println("Running ...");

    Runtime rt;
    Process pr;
    BufferedReader in;
    String line = "";
    String sysInfo = "";
    String edition = "";
    String fullOSName = "";
    final String SEARCH_TERM = "OS Name:";
    final String[] EDITIONS = {"Basic", "Home", "Professional", "Enterprise"};

    try {
      rt = Runtime.getRuntime();
      pr = rt.exec("SYSTEMINFO");
      in = new BufferedReader(new InputStreamReader(pr.getInputStream()));

      //add all the lines into a variable
      while ((line = in.readLine()) != null) {
        if (line.contains(SEARCH_TERM)) //found the OS you are using
        {
          //extract the full os name
          fullOSName = line.substring(line.lastIndexOf(SEARCH_TERM)
                                      + SEARCH_TERM.length(), line.length() - 1);
          break;
        }
      }

      //extract the edition of windows you are using
      for (String s : EDITIONS) {
        if (fullOSName.trim().contains(s)) {
          edition = s;
        }
      }

      System.out.println("The edition of Windows you are using is "
                         + edition);

    } catch (Exception ioe) {
      System.err.println(ioe.getMessage());
    }

    System.out.println("Exiting ...");
    System.exit(0);
  }


}
