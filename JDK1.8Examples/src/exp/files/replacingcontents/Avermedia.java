/**
 * <p>
 * Classname: pt.efacec.es.inoss.stv.avermedia.util.Avermedia
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
 * Rua Eng.\u00BA Frederico Ulrich ? Ap. 3078
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
package exp.files.replacingcontents;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 23/Set/2011, 12:18:30
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.13 $
 */
public class Avermedia{

  /** This class build number */
  public static final int I_BUILD = 96669;
  public static final int IBUILD = -1;
  /** .. */
  public static final String STR_AVERMEDIA_SDK_VERSION = "7.5.0.13";
  /** .. */
  public static final String S_VERSION = STR_AVERMEDIA_SDK_VERSION + "-build_" + 96669;

  private static final Logger LOGGER = Logger.getLogger(Avermedia.class.getName());
  private static final String S_THIS_CLASS_VERSION = "1.13";
  private static final String S_PRJ_BUILD_SEPARATOR = "0";
  private static final String STR_MODULE_NAME = "Avermedia";
  private static final String STR_MODULE_DESC = "Avermedia Video implementation";



  /**
   *
   * @return
   */
  public String getVersion() {
    return S_VERSION;
  }


  /**
   *
   * @return
   */
  public String getName() {
    return STR_MODULE_NAME;
  }

  /**
   *
   * @return
   */
  public String getDescription() {
    return STR_MODULE_DESC;
  }

  /**
   *
   */
  public static void showVersions() {
    final JFrame frame = new JFrame("Avermedia GI STV Module");
    final Container contentPane = frame.getContentPane();
    contentPane.setLayout(new GridLayout(7, 1));
    contentPane.add(new JLabel("  Avermedia GI STV Module version (official): " + S_VERSION + "  "));
    contentPane.add(new JLabel("  Internal module version: " + S_THIS_CLASS_VERSION + "  "));

    contentPane.add(new JSeparator());

    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final Dimension paneSize   = frame.getSize();
    final Dimension scrSize = frame.getToolkit().getScreenSize();

    frame.setLocation((scrSize.width - paneSize.width) / 2, (scrSize.height - paneSize.height) / 2);
    frame.setVisible(true);
  }




  public static void main(final String[] args) {
    showVersions();
  }

}

