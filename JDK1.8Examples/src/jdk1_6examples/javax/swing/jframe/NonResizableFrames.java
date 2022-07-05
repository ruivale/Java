/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jframe.NonResizableFrames
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
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
package jdk1_6examples.javax.swing.jframe;

import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class NonResizableFrames {

  /** This class LOGGER */
  private static final Logger LOGGER =
      Logger.getLogger(NonResizableFrames.class.getName());

  public static void main(final String[] args) {
    final JFrame frame = new JFrame("non resizable frame");
    frame.setLayout(new GridLayout(3, 1));
    frame.add(new JButton("1           "));
    frame.add(new JButton("        2"));
    frame.add(new JButton("                 3"));

    frame.getRootPane().addComponentListener(new ComponentAdapter() {
      public void componentResized(final ComponentEvent e) {
        frame.pack();
      }
    });
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }
}
