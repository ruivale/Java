package exp.swing.borders.titledborder;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


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
public class TitledborderTests
    extends JPanel {
  public TitledborderTests() {
    TitledBorder tb = new TitledBorder("AHHAHAH");
    Border border = new TitledBorder(BorderFactory.createLineBorder(Color.black),
                                     "Address",
                                     TitledBorder.LEADING,
                                     TitledBorder.TOP,
                                     new Font("SansSerif", 0, 10)) {
      public Insets getBorderInsets(Component c) {
        Insets insets = super.getBorderInsets(c);
        if (insets.top > 5) {
          insets = (Insets) insets.clone();
          insets.top -= 2;
        }
        return insets;
      }
    };
    Border borderTitled = new TitledBorder("Address") {
      public Insets _getBorderInsets(Component c) {
        Insets insets = super.getBorderInsets(c);
        if (insets.top > 5) {
          insets = (Insets) insets.clone();
          insets.top -= 2;
        }
        return insets;
      }

      public void _paintBorder(Component c, Graphics g, int x, int y, int width,
                              int height) {
        super.paintBorder(c, g, x, y, width, height);
        ImageIcon image = new ImageIcon("d:/temp/Cam.gif");
        JLabel label = new JLabel(image);
        label.paintImmediately(x + (getTitle().length()) + 180, y,
                               image.getIconWidth(), image.getIconHeight());
        //g.drawImage(image.getImage(), x + (getTitle().length()) + 180, y, image.getIconWidth(), image.getIconHeight(), image.getImageObserver());
      }
    };

    //setBorder(tb);
    //setBorder(border);
    setBorder(borderTitled);
  }


  public static void main(String[] args) {
    TitledborderTests t = new TitledborderTests();
    javax.swing.JFrame f = new javax.swing.JFrame();
    f.getContentPane().setLayout(new java.awt.BorderLayout());
    f.getContentPane().add(t, java.awt.BorderLayout.CENTER);
    f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    //f.pack();
    f.setSize(new Dimension(400, 300));
    f.setVisible(true);
  }
}
