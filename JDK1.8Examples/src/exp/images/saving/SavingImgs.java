// %1135250882279:exp.images.saving%
package exp.images.saving;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import exp.swing.icons.myown.ColorIcon;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SavingImgs
    extends JPanel {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** Default icon size */
  private static final int INT_ICON_SIZE = 16;

  /** Default number of icons */
  private static final int INT_NBR_OF_ICONS = 6;

  /** Default image type */
  private static final String STR_IMAGE_TYPE = "jpg";

  /** Default target dir */
  private static final String STR_FILE_DIR = ".";

  //~ Instance fields //////////////////////////////////////////////////////////

  /** The list of labels */
  private JLabel[] jLabels;

  /** The list of file names to create */
  private String[] strFileNames;

  /** The disable icon */
  private ColorIcon iconGray;

  /** The main icon */
  private ColorIcon iconMain;

  /** .. */
  private ColorIcon iconOrange;

  /** The no-comm icon */
  private ColorIcon iconRed;

  /** .. */
  private ColorIcon iconWhite;

  /** .. */
  private ColorIcon iconYellow;

  /** The disable label */
  private JLabel jLabelGray;

  /** The main label */
  private JLabel jLabelMain;

  /** .. */
  private JLabel jLabelOrange;

  /** The no-comm label */
  private JLabel jLabelRed;

  /** .. */
  private JLabel jLabelWhite;

  /** .. */
  private JLabel jLabelYellow;

  /** The target dir. */
  private String strFileDir = STR_FILE_DIR;

  /** The image type */
  private String strFileType = STR_IMAGE_TYPE;

  /** The object name */
  private String strName = "";

  /** The main color */
  private Color color = Color.blue;

  /** The icon width */
  private int intIconW = INT_ICON_SIZE;

  /** The icon height */
  private int intIconH = INT_ICON_SIZE;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Constructs a SavingImgs class with defaults target dir, image type, icon
   * width and height.
   *
   * @param strName String
   * @param color Color
   */
  public SavingImgs(final String strName, final Color color) {
    this(STR_FILE_DIR, strName, STR_IMAGE_TYPE, color, INT_ICON_SIZE, INT_ICON_SIZE);
  }

  /**
   * Constructs a SavingImgs class with defaults target dir, icon width and
   * height.
   *
   * @param strName String
   * @param strFileType String
   * @param color Color
   */
  public SavingImgs(
      final String strName,
      final String strFileType,
      final Color color) {
    this(STR_FILE_DIR, strName, strFileType, color, INT_ICON_SIZE, INT_ICON_SIZE);
  }

  /**
   * Constructs a SavingImgs class.
   *
   * @param strFileDir String
   * @param strName String
   * @param color Color
   */
  public SavingImgs(
      final String strFileDir,
      final String strName,
      final String strFileType,
      final Color color,
      final int intWidth,
      final int intHeight){

    this.strFileDir = strFileDir;
    this.strName = strName;
    this.strFileType = strFileType;
    this.color = color;
    this.intIconW = intWidth;
    this.intIconH = intHeight;

    if(this.color != null){
      this.recreateIcons(this.color);
    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Saves the icon images to the desire dir.
   */
  public void saveImages(){
    String strFileAbsolutPath;
    Image img;
    Graphics g;
    BufferedImage buffImage;
    Graphics gBI;

    for(int i=0; i<this.jLabels.length; i++){
      try {

        strFileAbsolutPath = new StringBuffer(this.strFileDir).append(
            "/").append(this.strFileNames[i]).toString();

        //System.out.println("Dealing with "+strFileAbsolutPath+".");

        // create an image for the capture
        img = this.jLabels[i].createImage(this.jLabels[i].getIcon().getIconWidth(),
                                          this.jLabels[i].getIcon().getIconHeight());

        // grab a graphics context for that image
        g = img.getGraphics();

        // print the GUI into that image
        this.jLabels[i].printAll(g);

        // Create empty BufferedImage, sized to img
        buffImage =
            new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_RGB);

        // Draw Image into BufferedImage
        gBI = buffImage.getGraphics();
        gBI.drawImage(img, 0, 0, null);

        ImageIO.write(buffImage,
                      this.strFileType,
                      new File(strFileAbsolutPath));

        //System.out.println("Saved image "+strFileAbsolutPath+".");

      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Builds the GUI
   */
  public void recreateIcons(final Color color){

    this.color = color;

    this.iconGray = new ColorIcon(
          this.color, Color.gray, this.intIconW, this.intIconH);
    this.iconMain = new ColorIcon(
          this.color, this.color, this.intIconW, this.intIconH);
    this.iconOrange = new ColorIcon(
          this.color, Color.orange, this.intIconW, this.intIconH);
    this.iconRed = new ColorIcon(
          this.color, Color.red, this.intIconW, this.intIconH);
    this.iconWhite = new ColorIcon(
          this.color, Color.white, this.intIconW, this.intIconH);
    this.iconYellow = new ColorIcon(
          this.color, Color.yellow, this.intIconW, this.intIconH);

    this.jLabelGray = new JLabel(this.iconGray);
    this.jLabelMain = new JLabel(this.iconMain);
    this.jLabelOrange = new JLabel(this.iconOrange);
    this.jLabelRed = new JLabel(this.iconRed);
    this.jLabelWhite = new JLabel(this.iconWhite);
    this.jLabelYellow = new JLabel(this.iconYellow);

    final JLabel[] ls = {
      this.jLabelGray,
      this.jLabelMain,
      this.jLabelOrange,
      this.jLabelRed,
      this.jLabelWhite,
      this.jLabelYellow
    };
    this.jLabels = ls;

    final String[] ss = {
      new StringBuffer(this.strName).append("_gray").append(".").append(this.strFileType).toString(),
      new StringBuffer(this.strName).append("_main").append(".").append(this.strFileType).toString(),
      new StringBuffer(this.strName).append("_orange").append(".").append(this.strFileType).toString(),
      new StringBuffer(this.strName).append("_red").append(".").append(this.strFileType).toString(),
      new StringBuffer(this.strName).append("_white").append(".").append(this.strFileType).toString(),
      new StringBuffer(this.strName).append("_yellow").append(".").append(this.strFileType).toString(),
    };
    this.strFileNames = ss;

    this.removeAll();
    this.setLayout(new GridLayout(1,6,0,0));

    for (int i = 0; i < this.jLabels.length; i++) {
      this.add(this.jLabels[i]);
      this.jLabels[i].setIconTextGap(0);
      this.jLabels[i].setVerticalAlignment(JLabel.TOP);
      this.jLabels[i].setHorizontalAlignment(JLabel.LEFT);
      this.jLabels[i].setBackground(Color.white);
      this.jLabels[i].setForeground(Color.white);
      this.jLabels[i].setOpaque(false);
    }

    this.setBackground(Color.white);
    this.setForeground(Color.white);

    this.setOpaque(false);

  }

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    final SavingImgs s = new SavingImgs(
      "d:/temp", "Rambo", "jpg", Color.red, 16, 16);

    JFrame f_ = new JFrame();
    f_.setBackground(Color.WHITE);
    f_.setForeground(Color.WHITE);
    f_.getContentPane().add(s);
    f_.pack();
    f_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f_.setVisible(true);


    JButton jButtonSave = new JButton("Save images ...");
    JTextField textFieldDir = new JTextField("D:/temp/");

    jButtonSave.setPreferredSize(new Dimension(40, 20));
    textFieldDir.setPreferredSize(new Dimension(150, 20));

    JFrame f = new JFrame();
    f.setBackground(Color.WHITE);
    f.setForeground(Color.WHITE);
    f.getContentPane().setLayout(new GridLayout(1, 2));
    f.getContentPane().add(textFieldDir);
    f.getContentPane().add(jButtonSave);
    f.setBounds(100, 200, 200, 50);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

    jButtonSave.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        s.saveImages();
      }
    });
  }
}
