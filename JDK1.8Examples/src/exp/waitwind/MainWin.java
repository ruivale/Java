package exp.waitwind;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * DOCUMENT ME!
 *
 * @author unascribed
 * @version 1.0
 *
 * @todo fkdjfkdjk <p><p><p><p>
 */
public class MainWin
    extends JFrame {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  JButton jButtonDispose = new JButton();

  /** .. */
  JButton jButtonShow = new JButton();

  /** .. */
  JButton jButtonStartAnim = new JButton();

  /** .. */
  JButton jButtonStopAnim = new JButton();

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new MainWin object.
   */
  public MainWin() {
    try {
      JFrame frame = new JFrame();
      frame.setBounds(500, 20, 100, 50);
      frame.setVisible(true);
      new WaitWindow(frame);

      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args Insert doc ...
   */
  public static void main(String[] args) {
    MainWin mainWin1 = new MainWin();
    mainWin1.setBounds(20, 20, 400, 300);
    mainWin1.setVisible(true);
  }

  /**
   * Insert doc ...
   *
   * @param e Insert doc ...
   */
  void jButtonDispose_actionPerformed(ActionEvent e) {
    WaitWindow.finishWindow();
  }

  /**
   * Insert doc ...
   *
   * @param e Insert doc ...
   */
  void jButtonShow_actionPerformed(ActionEvent e) {
    WaitWindow.startWindow();
  }

  /**
   * Insert doc ...
   *
   * @param e Insert doc ...
   */
  void jButtonStartAnim_actionPerformed(ActionEvent e) {
    WaitWindow.startTimer();
  }

  /**
   * Insert doc ...
   *
   * @param e Insert doc ...
   */
  void jButtonStopAnim_actionPerformed(ActionEvent e) {
    WaitWindow.stopTimer();
  }

  /**
   * Insert doc ...
   *
   * @throws Exception Insert doc ...
   */
  private void jbInit()
      throws Exception {
    jButtonShow.setBounds(new Rectangle(45, 25, 127, 69));
    jButtonShow.setText("show");
    jButtonShow.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          jButtonShow_actionPerformed(e);
        }
      });
    this.getContentPane()
        .setLayout(null);
    jButtonDispose.setBounds(new Rectangle(49, 115, 122, 54));
    jButtonDispose.setText("dispose");
    jButtonDispose.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          jButtonDispose_actionPerformed(e);
        }
      });
    jButtonStopAnim.setBounds(new Rectangle(204, 41, 134, 58));
    jButtonStopAnim.setText("stop anim");
    jButtonStopAnim.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          jButtonStopAnim_actionPerformed(e);
        }
      });
    jButtonStartAnim.setBounds(new Rectangle(218, 149, 140, 88));
    jButtonStartAnim.setText("start anim");
    jButtonStartAnim.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          jButtonStartAnim_actionPerformed(e);
        }
      });
    this.getContentPane()
        .add(jButtonShow, null);
    this.getContentPane()
        .add(jButtonDispose, null);
    this.getContentPane()
        .add(jButtonStopAnim, null);
    this.getContentPane()
        .add(jButtonStartAnim, null);
  }
}
