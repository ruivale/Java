/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.icons.mergeimgs.ImagesMerging
 * </p>
 *
 * <p>Copyright: Copyright (c) 2010 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_6examples.javax.swing.icons.mergeimgs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ImagesMerging extends ImageIcon {

  /**  */
  public static final Color COLOR_BACK = new Color(0, 0, 0);
  /**  */
  public static final String S_DESIRE_IMAGE_FORMAT = "png";
  /**  */
  public boolean activeOverringImage = false;
  /**  */
  private ImageIcon overridingIcon;
  /**  */
  private ImageIcon rootIcon;
  /**  */
  private int xicon;
  /**  */
  private int yicon;
  /**  */
  private List<ImageIcon> listOfIcons = new ArrayList<ImageIcon>(3);

  /**
   * Main icon
   *
   * @param rootIcon
   */
  public ImagesMerging(final ImageIcon rootIcon) {
    this.rootIcon = rootIcon;
  }

  /**
   * Main icon and a secondary one more little that will be drawn on the main one
   *
   * @param rootIcon the main icon
   * @param x 
   * @param y
   * @param overridingIcon the second one
   */
  public ImagesMerging(
      final ImageIcon rootIcon,
      final int x,
      final int y,
      final ImageIcon overridingIcon) {
    
    this(rootIcon);
    setOverridingIcon(x, y, overridingIcon);
  }

  /**
   *
   *
   * @return
   */
  @Override
  public int getIconHeight() {
    return rootIcon.getIconHeight();
  }

  /**
   *
   *
   * @return
   */
  @Override
  public int getIconWidth() {
    return rootIcon.getIconWidth();
  }

  /**
   *
   *
   * @param arg0
   */
  @Override
  public void setImage(final Image image) {
    rootIcon.setImage(image);
  }

  /**
   *
   *
   * @return
   */
  @Override
  public Image getImage() {
    return rootIcon.getImage();
  }

  /**
   *
   *
   * @return
   */
  @Override
  public int getImageLoadStatus() {
    return rootIcon.getImageLoadStatus();
  }

  /**
   *
   *
   * @param arg0
   */
  @Override
  public void setImageObserver(final ImageObserver arg0) {
    rootIcon.setImageObserver(arg0);
  }

  /**
   *
   *
   * @return
   */
  @Override
  public ImageObserver getImageObserver() {
    return rootIcon.getImageObserver();
  }

  /**
   *
   *
   * @param x
   * @param y
   * @param overridingIcon
   */
  public void setOverridingIcon(
      final int x,
      final int y,
      final ImageIcon overridingIcon) {
    this.xicon = x;
    this.yicon = y;
    this.overridingIcon = overridingIcon;
  }

  /**
   *
   * @param overridingIcon
   */
  public synchronized void addOverridingIcon(final ImageIcon overridingIcon) {
    this.listOfIcons.add(overridingIcon);
  }

  /**
   * 
   * @param overridingIcon
   */
  public synchronized void removeOverridingIcon(final ImageIcon overridingIcon) {
    this.listOfIcons.remove(overridingIcon);
  }

  /**
   *
   *
   * @param arg0
   * @param arg1
   * @param x
   * @param y
   */
  @Override
  public synchronized void paintIcon(
      final Component arg0,
      final Graphics arg1,
      final int x,
      final int y) {

    arg1.drawImage(rootIcon.getImage(), x, y, null);

    if (activeOverringImage) {
      final int nIcons = this.listOfIcons.size();
      ImageIcon icon;

      for (int i = 0; i < nIcons; i++) {
        icon = this.listOfIcons.get(i);

        if (icon != null) {
          arg1.drawImage(icon.getImage(), x, y, null);
        }
      }
    }
  }

  /**
   * 
   * @param strFileNameWithoutExtension
   * @return
   */
  public boolean saveImage(final String strFileNameWithoutExtension) {
    boolean saved = false;

    final JWindow win = new JWindow();
    win.setBackground(COLOR_BACK);
    win.setLocation(0, 0);
    win.getContentPane().setLayout(new BorderLayout());
    win.getContentPane().setBackground(COLOR_BACK);

    final MyJLabel myJLabel = new MyJLabel(this, COLOR_BACK);
    myJLabel.setBackground(COLOR_BACK);
    myJLabel.setBorder(null);

    win.getContentPane().add(myJLabel, BorderLayout.CENTER);
    win.pack();
    win.setVisible(true);

    myJLabel.saveImage(strFileNameWithoutExtension + "." + S_DESIRE_IMAGE_FORMAT, S_DESIRE_IMAGE_FORMAT);

    win.setVisible(false);

    return saved;
  }

  /**
   *
   * @param jLabel
   * @param width
   * @param height
   * @param strFileAbsolutPath
   * @param strFileType
   * @param colorTransp
   */
  private void saveImages(
      JLabel jLabel,
      int width,
      int height,
      String strFileAbsolutPath,
      String strFileType,
      Color colorTransp) {

    Image img;
    Graphics g;
    BufferedImage buffImage;
    Graphics gBI;
    final Color transparent = new Color(colorTransp.getRGB(), true);

    try {
      //System.out.println("Dealing with " + strFileAbsolutPath + ".");

      /***
      // repaint onto a BufferedImage
      BufferedImage bufferedImage =
      new BufferedImage(width,
      height,
      BufferedImage.TYPE_4BYTE_ABGR_PRE);
      Graphics2D g2d = bufferedImage.createGraphics();
      // redraw using our paintComponent
      paintComponent(g2d);

      File file = new File(strFileAbsolutPath);
      // write out the BufferedImage as a PNG
      ImageIO.write(bufferedImage, "png", file);
      /**/
      // create an image for the capture
      img = jLabel.createImage(width, height);

      // grab a graphics context for that image
      g = img.getGraphics();

      // print the GUI into that image
      jLabel.printAll(g);

      // Create empty BufferedImage, sized to img
      buffImage =
          new BufferedImage(
          img.getWidth(null),
          img.getHeight(null),
          BufferedImage.TYPE_4BYTE_ABGR_PRE);//BufferedImage.TYPE_INT_RGB);

      // Draw Image into BufferedImage
      gBI = buffImage.getGraphics();
      gBI.drawImage(img, 0, 0, null);

      ImageIO.write(buffImage,
          strFileType,
          new File(strFileAbsolutPath));

      //System.out.println("Saved image " + strFileAbsolutPath + ".");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   *
   *
   * @param args
   */
  public static void main(final String[] args) {

    final JWindow fr = new JWindow();
    //final JFrame fr = new JFrame();
    fr.setBackground(COLOR_BACK);
    fr.setLocation(0, 0);
    fr.getContentPane().setLayout(new BorderLayout());
    fr.getContentPane().setBackground(COLOR_BACK);
    //fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final ImageIcon ii = new ImageIcon("D:/Projects/JDK1.6Examples/images/dome_green.png");
    final ImageIcon ii2 = new ImageIcon("D:/Projects/JDK1.6Examples/images/dome_on.png");
    final ImageIcon ii3 = new ImageIcon("D:/Projects/JDK1.6Examples/images/dome_highrec.png");


    final ImagesMerging iip = new ImagesMerging(ii);
    //iip.setOverridingIcon(0,0,ii2);
    iip.activeOverringImage = true;
    iip.addOverridingIcon(ii2);
    iip.addOverridingIcon(ii3);

    //final JButton jb = new JButton(iip);
    final MyJLabel jb = new MyJLabel(iip, COLOR_BACK);
    jb.setBackground(COLOR_BACK);
    jb.setBorder(null);
    fr.getContentPane().add(jb, BorderLayout.CENTER);

    final JCheckBox jcbAlrm = new JCheckBox("dome");
    jcbAlrm.setSelected(false);
    jcbAlrm.addActionListener(new ActionListener() {

      int i = 0;

      @Override
      public void actionPerformed(final ActionEvent e) {
        //iip.activeOverringImage = !iip.activeOverringImage;
        if (jcbAlrm.isSelected()) {
          iip.addOverridingIcon(ii2);
        } else {
          iip.removeOverridingIcon(ii2);
        }
        jb.repaint();
      }
    });
    final JCheckBox jcblocked = new JCheckBox("dome_locked");
    jcblocked.setSelected(false);
    jcblocked.addActionListener(new ActionListener() {
      int i = 0;

      @Override
      public void actionPerformed(final ActionEvent e) {
        //iip.activeOverringImage = !iip.activeOverringImage;
        if (jcblocked.isSelected()) {
          iip.addOverridingIcon(ii3);
        } else {
          iip.removeOverridingIcon(ii3);
        }
        jb.repaint();
      }
    });


    final JPanel jPS = new JPanel();
    jPS.add(jcbAlrm);
    jPS.add(jcblocked);

    //fr.getContentPane().add(jPS,BorderLayout.SOUTH);

    fr.pack();
    fr.setVisible(true);


    jb.saveImage("D:/Projects/JDK1.6Examples/images/dome_on_highrec.png", "png");

    /***
    iip.saveImages(
    jb,
    iip.getIconWidth(),
    iip.getIconHeight(),
    "d:/temp/PInts/dome_alarm_locked.png",
    "png",
    COLOR_BACK);
    /**/
    //fr.setVisible(false);
  }
}


final class MyJLabel extends JLabel {

  private int height = 50;
  private int width = 50;
  //private static final Color lightGreen = new Color(0x30ffaf);
  private Color colorTransparent = new Color(0x00ffffff, true);

  /**
   *
   * @param icon
   * @param colorTransp
   */
  MyJLabel(final ImageIcon icon, final Color colorTransp) {
    super(icon);

    this.colorTransparent = new Color(colorTransp.getRGB(), true);

    this.width = icon.getIconWidth();
    this.height = icon.getIconHeight();

    final Dimension d = new Dimension(width, height);
    this.setPreferredSize(d);
    this.setMinimumSize(d);
    this.setMaximumSize(d);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    final Graphics2D g2d = (Graphics2D) g;
    g2d.addRenderingHints(
        new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
    g2d.setBackground(colorTransparent);

    //g2d.clearRect(0, 0, width, height);

    /***
    //Font font = new Font("Tiresias PCFont Z", Font.BOLD, 40);
    g2d.setFont(font);
    //String text = "I/O";
    g2d.setColor(lightGreen);
    g2d.fillPolygon(
    new int[]{0, 96, 128, 32}, // xs
    new int[]{32, 32, 96, 96}, // ys
    4);

    FontMetrics fm = this.getFontMetrics(font);
    int textWidth = fm.stringWidth(text);
    FontRenderContext fr = g2d.getFontRenderContext();
    LineMetrics lm = font.getLineMetrics(text, fr);
    int textHeight = (int) (lm.getAscent() *
    .71);// overstated
    g2d.setColor(Color.BLUE);
    g2d.drawString(text,
    width /
    2 -
    textWidth /
    2,
    height /
    2 +
    textHeight /
    2);
    /**/
  }

  /**
   * save current image as a png.
   */
  public void saveImage(final String strFilePath, final String strImgFormat) {
    try {
      // repaint onto a BufferedImage
      BufferedImage bufferedImage = new BufferedImage(
          width,
          height,
          BufferedImage.TYPE_4BYTE_ABGR_PRE);
      Graphics2D g2d = bufferedImage.createGraphics();
      // redraw using our paintComponent
      paintComponent(g2d);

      File file = new File(strFilePath);
      // write out the BufferedImage as a PNG
      ImageIO.write(bufferedImage, strImgFormat, file);

    } catch (IOException e) {
      System.err.println("image not saved.");
    }
  }
}
