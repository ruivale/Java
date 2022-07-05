package exp.desktop;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class JIFWithKeyListener extends JPanel {
  JButton jButton1 = new JButton();

  public JIFWithKeyListener() {
    try {
      jbInit();


      JDesktopPane jdp = new JDesktopPane();
      JInternalFrame jif = new JInternalFrame();
      jif.setBounds(10,10,200,100);
      jif.setVisible(true);
      jdp.add(jif);
      jdp.setBounds(0,0,250,150);
      this.add(jdp);

      jif.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {

System.out.println("keyPressed jif");

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            }
          }
        }
      );

    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    JIFWithKeyListener JIFWithKeyListener1 = new JIFWithKeyListener();
    JFrame f = new JFrame();
    f.getContentPane().add(JIFWithKeyListener1);
    f.setBounds(100,100,300,250);
    f.setVisible(true);
      f.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {

System.out.println("keyPressed frame");

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            }
          }
        }
      );

  }
  private void jbInit() throws Exception {
    this.setLayout(null);
  }

  void jButton1_actionPerformed(ActionEvent e) {

  }
}

class JIFWithKeyListener_jButton1_actionAdapter implements java.awt.event.ActionListener {
  JIFWithKeyListener adaptee;

  JIFWithKeyListener_jButton1_actionAdapter(JIFWithKeyListener adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}