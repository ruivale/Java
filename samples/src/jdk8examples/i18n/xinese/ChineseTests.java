/**
 * <p>
 * Classname: jdk8examples.i18n.xinese.ChineseTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
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

package jdk8examples.i18n.xinese;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jul 18, 2016, 11:23:59 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ChineseTests {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ChineseTests.class.getName());


 /**
  * The ArabicTests default constructor.
  */
  public ChineseTests(){
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("ChineseTests").append("").toString();
  }

  public static void main(final String[] args){



    SwingUtilities.invokeLater(() -> {
      String strTitle;
      StringBuilder sBMsg = new StringBuilder();

      try {
        final ResourceBundle rb = ResourceBundle.getBundle("jdk8examples.i18n.xinese.chinese", new Locale("zh", "CN"));
        strTitle = rb.getString("title");

        sBMsg.append(rb.getString("auth.error")).append("  ");
        sBMsg.append(rb.getString("auth.warning")).append("  ");
        sBMsg.append(rb.getString("auth.warning.no_name")).append("  ");
        sBMsg.append(rb.getString("auth.warning.no_user")).append("  ");
        sBMsg.append(rb.getString("auth.warning.no_password")).append("  ");
        sBMsg.append(rb.getString("auth.warning.wrong_password")).append("  ");
        sBMsg.append(rb.getString("auth.warning.no_server_found")).append("  ");
        sBMsg.append(rb.getString("auth.warning.invalid_profile")).append("  ");
        sBMsg.append(rb.getString("auth.warning.no_profile_found")).append("  ");

      } catch (Exception e) {
        strTitle = "_TITLE_";
        sBMsg = new StringBuilder("_MSG_");

        e.printStackTrace();
      }

      JOptionPane.showMessageDialog(null, sBMsg.toString(), strTitle, JOptionPane.ERROR_MESSAGE);

//        String str2 = "???? ??????? ???? ????? ??? ???";
//        JOptionPane.showMessageDialog(null, str2);
    });
  }
}
