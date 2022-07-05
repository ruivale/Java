/**
 * <p>
 * Classname: jdk1_6examples.javax.swing.jcomp.BackgroundTransparentComp
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
 * Rua Eng.� Frederico Ulrich ? Ap. 3078
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
package jdk1_6examples.javax.swing.jcomp;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 7/Out/2011, 12:25:32
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class BackgroundTransparentComp {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(BackgroundTransparentComp.class.getName());

  /**
   * The BackgroundTransparentComp default constuctor.
   */
  public BackgroundTransparentComp() {
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("BackgroundTransparentComp").append("").toString();
  }

  public static void main(final String[] args) {
    JFrame frame = new JFrame("Transparent Window");
    TransparentBackground bg = new TransparentBackground(frame);
    bg.setLayout(new BorderLayout());
    JButton button = new JButton("This is a button");
    bg.add("North", button);
    JLabel label = new JLabel("This is a label");
    bg.add("South", label);
    frame.getContentPane().add("Center", bg);
    frame.pack();
    frame.setSize(150, 100);
    frame.setVisible(true);
  }
}
