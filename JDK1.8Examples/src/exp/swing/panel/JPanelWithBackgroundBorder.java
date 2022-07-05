/**
 * <p>
 * Classname: exp.swing.panel.JPanelWithBackgroundBorder
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

package exp.swing.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 15, 2016, 6:57:09 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class JPanelWithBackgroundBorder {

  private final JPanel jPanel = new JPanel(){
    private int iBorderSize = 4;
    private Color colorBack = Color.BLACK;

    @Override
    public void setBounds(final int x, final int y, final int width, final int height){
      super.setBounds(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
      super.paint(g);

      g.setColor(this.colorBack);
      g.fillRect(
          iBorderSize,
          iBorderSize,
          this.getWidth() - iBorderSize * 2,
          this.getHeight() - iBorderSize * 2);
    }

    @Override
    public void setBackground(final Color color){
      super.setBackground(color);

      this.colorBack = color;
    }
  };

 /**
  * The JPanelWithBackgroundBorder default constructor.
  */
  public JPanelWithBackgroundBorder(){
    JFrame f = new JFrame("Testing panel with color border ...");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLayout(new BorderLayout());

    JPanel jp = new JPanel();
    jp.setBackground(Color.BLUE);
    jp.setBorder(BorderFactory.createLineBorder(Color.RED, 15));

    f.add(jp);

    f.setSize(650, 500);
    f.setVisible(true);
  }




  public static void main(final String[] args){
    final JPanelWithBackgroundBorder clazz = new JPanelWithBackgroundBorder();
  }
}
