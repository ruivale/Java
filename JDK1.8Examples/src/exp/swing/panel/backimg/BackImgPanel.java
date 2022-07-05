package exp.swing.panel.backimg;


import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class BackImgPanel
    extends JPanel {
  public BackImgPanel() {
  }

  public void paint(Graphics g) {
    super.paint(g);

    Image icon = new ImageIcon("D:\\temp\\efacec.jpg").getImage();
    try {
      g.drawImage(icon, 0, 0, null);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    JFrame f = new JFrame("icons");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(new BackImgPanel(),
                           BorderLayout.CENTER);
    f.setBounds(100, 100, 600, 500);
    //f.pack();
    f.setVisible(true);

  }

}
