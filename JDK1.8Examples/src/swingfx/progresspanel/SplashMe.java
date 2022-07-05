// %1220009897943:%
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swingfx.progresspanel;


import net.java.swingfx.waitwithstyle.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * 
 *
 * @author $author$
 * @version $Revision$
  */
public class SplashMe {
  /**
   *
   *
   * @param args 
   */
  public static void main(final String[] args) {
    JFrame frame = new JFrame();

    JPanel panel = new JPanel();

    panel.add(new JLabel("Example Text:"));
    panel.add(new JButton("Example Button"));
    frame.add(panel);

    final PerformanceInfiniteProgressPanel pane =
      new PerformanceInfiniteProgressPanel(true, 15);

    Thread thread =
      new Thread(
          new Runnable() {
            public void run() {
              try {
                Thread.sleep(10000);
              } catch(final InterruptedException e) {
              }

              pane.setVisible(false);
            }
          });

    thread.start();

    //pane.setBounds(0,0, 50,50);

    frame.setSize(
      350,
      300);
    frame.setGlassPane(pane);
    pane.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}