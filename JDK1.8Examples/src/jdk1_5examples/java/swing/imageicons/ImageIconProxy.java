package jdk1_5examples.java.swing.imageicons;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * ImageIcon working with two icons than can be superposed for
 * drawing various elements : errors, warning ...
 * @author A.Brillant */
public class ImageIconProxy
    extends ImageIcon {
  private ImageIcon rootIcon;
  private ImageIcon overridingIcon;
  private int xicon, yicon;

  /** Main icon */
  public ImageIconProxy(ImageIcon rootIcon) {
    this.rootIcon = rootIcon;
  }

  /** Main icon and a secondary one more little that will be drawn on the main one
   * @param rootIcon the main icon
   * @param x the location of the second one
   * @param x the location of the second one
   * @param overridingIcon the second one
   */
  public ImageIconProxy(ImageIcon rootIcon, int x, int y,
                        ImageIcon overridingIcon) {
    this(rootIcon);
    setOverridingIcon(x, y, overridingIcon);
  }

  public void setOverridingIcon(int x, int y, ImageIcon overridingIcon) {
    this.xicon = x;
    this.yicon = y;
    this.overridingIcon = overridingIcon;
  }

  public int getIconHeight() {
    return rootIcon.getIconHeight();
  }

  public int getIconWidth() {
    return rootIcon.getIconWidth();
  }

  public Image getImage() {
    return rootIcon.getImage();
  }

  public int getImageLoadStatus() {
    return rootIcon.getImageLoadStatus();
  }

  public ImageObserver getImageObserver() {
    return rootIcon.getImageObserver();
  }

  public boolean activeOverringImage = false;

  public synchronized void paintIcon(Component arg0, Graphics arg1, int x,
                                     int y) {
    arg1.drawImage(rootIcon.getImage(), x, y, null);
    if (activeOverringImage && overridingIcon != null) {
      arg1.drawImage(overridingIcon.getImage(), x + xicon, y + yicon, null);
    }
  }

  public void setImage(Image arg0) {
    rootIcon.setImage(arg0);
  }

  public void setImageObserver(ImageObserver arg0) {
    rootIcon.setImageObserver(arg0);
  }

  public static void main(String[] args) {
    JFrame fr = new JFrame();
    fr.setSize(300, 300);
    ImageIcon ii = new ImageIcon("beans.jpg");
    ImageIcon ii2 = new ImageIcon("view.gif");
    ImageIconProxy iip = new ImageIconProxy(ii);
    iip.setOverridingIcon(10, 10, ii2);
    iip.activeOverringImage = true;
    fr.add(new JButton(iip));
    fr.setVisible(true);
  }

}
