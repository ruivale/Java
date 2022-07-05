/**
 * <p>
 * Classname: jdk1_6examples.javax.swing.jcomp.TransparentBackground
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
package jdk1_6examples.javax.swing.jcomp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 7/Out/2011, 12:26:13
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class TransparentBackground extends JComponent {

  private JFrame frame;
  private Image background;

  public TransparentBackground(JFrame frame) {
    this.frame = frame;
    updateBackground();
  }

  public void updateBackground() {
    try {
      Robot rbt = new Robot();
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension dim = tk.getScreenSize();
      background = rbt.createScreenCapture(
          new Rectangle(0, 0, (int) dim.getWidth(),
          (int) dim.getHeight()));
    } catch (Exception ex) {      
      ex.printStackTrace();
    }
  }

  public void paintComponent(Graphics g) {
    Point pos = this.getLocationOnScreen();
    Point offset = new Point(-pos.x, -pos.y);
    g.drawImage(background, offset.x, offset.y, null);
  }
}
