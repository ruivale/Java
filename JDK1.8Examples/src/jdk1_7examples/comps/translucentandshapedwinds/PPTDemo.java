/**
 * <p>
 * Classname:  jdk1_7examples.comps.translucentandshapedwinds.PPTDemo
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
 * Rua EngÂº Frederico Ulrich - Apartado 3081
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
package jdk1_7examples.comps.translucentandshapedwinds;

// PPTDemo.java
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PPTDemo extends JFrame {

  public PPTDemo() {
    super("Per-Pixel Translucency Demo");
    JPanel gradPanel = new JPanel() {
      // Transparent red

      Color colorA = new Color(255, 0, 0, 0);
      // Solid red
      Color colorB = new Color(255, 0, 0, 255);

      protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp;
        gp = new GradientPaint(0.0f, 0.0f, colorA,
            0.0f, getHeight(),
            colorB, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(),
            getHeight());

      }
    };
    gradPanel.setPreferredSize(new Dimension(300, 200));
    gradPanel.setLayout(new BoxLayout(gradPanel, BoxLayout.Y_AXIS));
    JButton btnClose = new JButton("Close");
    ActionListener al;
    al = new ActionListener() {

      public void actionPerformed(ActionEvent ae) {
        System.exit(0);
      }
    };
    btnClose.addActionListener(al);
    btnClose.setAlignmentX(0.5f);
    gradPanel.add(Box.createVerticalGlue());
    gradPanel.add(btnClose);
    gradPanel.add(Box.createVerticalGlue());
    setContentPane(gradPanel);
    if (!getGraphicsConfiguration().isTranslucencyCapable()) {
      System.err.println("per-pixel translucency not in effect for " +
                         "this graphics configuration");
      System.exit(0);
    }
    setBackground(new Color(0, 0, 0, 0)); // Achieve per-pixel
    // translucency.
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    Runnable r;
    r = new Runnable() {

      public void run() {
        GraphicsEnvironment ge;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (!ge.getDefaultScreenDevice().
            isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT)) {
          System.err.println("per-pixel translucency isn't " +
                             "supported");
          return;
        }
        new PPTDemo();
      }
    };
    EventQueue.invokeLater(r);
  }
}
