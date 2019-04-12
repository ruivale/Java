/**
 * <p>
 * Classname: exp.layouts.CenterComps
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

package exp.layouts;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Nov 15, 2013, 2:40:40 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class CenterComps extends JPanel {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(CenterComps.class.getName());

  final Icon icon =
              new ImageIcon(
                  "D:/Projects/TLC/Projects/_old/TLC-LUASnoLoginNoLogout/TLC-LUAS/tests/LUAS-TLC/PInt/images/stairs.png");
  JLabel jb1 = new JLabel("One", icon, SwingConstants.CENTER);
  JLabel jb2 = new JLabel("Two", icon, SwingConstants.CENTER);

 /**
  * The CenterComps default constructor.
  */
  public CenterComps(){

    final JPanel jP = new JPanel(new GridLayout(1,2));
    jP.setBorder(BorderFactory.createTitledBorder("Status"));
    jP.add(jb1);
    jP.add(jb2);

    setLayout(new BorderLayout());
    add(jP, BorderLayout.CENTER);
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("CenterComps").append("").toString();
  }

  public static void main(final String[] args){
    final CenterComps clazz = new CenterComps();
    JFrame f = new JFrame("centering comps");
    f.setLayout(new BorderLayout());
    f.add(clazz);
    f.pack();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
