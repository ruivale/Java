/**
 * <p>
 * Classname: jdk8examples.i18n.arabic.ArabicTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2014 Efacec Engenharia e Sistemas, S.A.
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

package jdk8examples.i18n.arabic;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 21, 2015, 4:24:50 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ArabicTests {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ArabicTests.class.getName());


 /**
  * The ArabicTests default constructor.
  */
  public ArabicTests(){
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("ArabicTests").append("").toString();
  }

  public static void main(final String[] args){



    SwingUtilities.invokeLater(() -> {
      String strTitle;

      try {
        final ResourceBundle rb = ResourceBundle.getBundle("jdk8examples.i18n.arabic.arabic", new Locale("ar"));
        strTitle = rb.getString("title");

      } catch (Exception e) {
        strTitle = "TITLE";
        e.printStackTrace();
      }

      String str1= "\u062a\u0639\u0637\u064a \u064a\u0648\u0646\u064a\u0643\u0648\u062f "
          + "\u0631\u0642\u0645\u0627 \u0641\u0631\u064a\u062f\u0627 \u0644\u0643\u0644 \u062d\u0631\u0641";
      JOptionPane.showMessageDialog(null, str1, strTitle, JOptionPane.ERROR_MESSAGE);

//        String str2 = "???? ??????? ???? ????? ??? ???";
//        JOptionPane.showMessageDialog(null, str2);
    });
  }
}
