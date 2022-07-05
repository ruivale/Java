/**
 * <p>
 * Classname: exp.swing.window.NewJWindowTest
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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

package exp.swing.window;


import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on May 14, 2015, 5:04:13 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class NewJWindowTest extends JDialog {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(NewJWindowTest.class.getName());
  private static final boolean mayMaximize =
      Toolkit.getDefaultToolkit().isFrameStateSupported(Frame.MAXIMIZED_BOTH);

  private static final Rectangle rectWhere2ShowInoss = new Rectangle(0, 0, 1920, 1079);


 /**
  * The NewJWindowTest default constructor.
  */
  public NewJWindowTest(){

    final JButton jb = new JButton("Jãste quelique mi...");

    final JPanel jp = new JPanel(new BorderLayout());
    jp.add(jb);



    this.setLayout(new BorderLayout());
    this.add(jp);
    this.setBounds(rectWhere2ShowInoss);
    this.setVisible(true);

  }


  public static void main(final String[] args){
    final NewJWindowTest clazz = new NewJWindowTest();
  }
}
