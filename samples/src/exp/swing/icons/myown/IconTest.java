package exp.swing.icons.myown;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.image.BufferedImage;


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
public class IconTest
    extends JPanel {
  private static final int INT_ICON_SIZE = 16;
  private static final int INT_ICON_X = 16;
  private static final int INT_ICON_Y = 10;

  //~ Instance fields //////////////////////////////////////////////////////////
  ColorIcon iconMain = new ColorIcon(Color.blue, Color.blue, INT_ICON_SIZE, INT_ICON_SIZE);
  ColorIcon iconRed = new ColorIcon(Color.blue, Color.red, INT_ICON_SIZE, INT_ICON_SIZE);
  ColorIcon iconOrange = new ColorIcon(Color.blue, Color.orange, INT_ICON_SIZE, INT_ICON_SIZE);
  ColorIcon iconYellow = new ColorIcon(Color.blue, Color.yellow, INT_ICON_SIZE, INT_ICON_SIZE);
  ColorIcon iconGray = new ColorIcon(Color.blue, Color.gray, INT_ICON_SIZE, INT_ICON_SIZE);
  ColorIcon iconWhite = new ColorIcon(Color.blue, Color.white, INT_ICON_SIZE, INT_ICON_SIZE);

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param g  Insert doc ...
   */
  public void paint(Graphics g) {
    iconMain.paintIcon(
        this,
        g,
        INT_ICON_X,
        INT_ICON_Y);
    iconRed.paintIcon(
        this,
        g,
        iconMain.getX() + iconMain.getIconWidth() + 5,
        INT_ICON_Y);
    iconOrange.paintIcon(
        this,
        g,
        iconRed.getX() + iconRed.getIconWidth() + 5,
        INT_ICON_Y);
    iconYellow.paintIcon(
        this,
        g,
        iconOrange.getX() + iconOrange.getIconWidth() + 5,
        INT_ICON_Y);
    iconGray.paintIcon(
        this,
        g,
        iconYellow.getX() + iconYellow.getIconWidth() + 5,
        INT_ICON_Y);
    iconWhite.paintIcon(
        this,
        g,
        iconGray.getX() + iconGray.getIconWidth() + 5,
        INT_ICON_Y);

  }

  public static void main(String s[]){
    IconTest it = new IconTest();
    JFrame f_ = new JFrame();
    f_.getContentPane().add(it);
    f_.setBounds(100,100,400,200);
    f_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f_.setVisible(true);

  }


}
