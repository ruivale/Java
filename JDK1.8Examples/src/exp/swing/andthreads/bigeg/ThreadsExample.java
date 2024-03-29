package exp.swing.andthreads.bigeg;

import java.awt.event.*;
import java.io.File;
//import com.sun.java.swing.*;  //old package name
import javax.swing.*; //new package name

public class ThreadsExample {

  /**
   * This is strictly boilerplate: set the look and feel, configure the
   * frame, pack(), show().
   */
  public static void main(String[] args) {
    String laf = UIManager.getCrossPlatformLookAndFeelClassName();
    try {
      UIManager.setLookAndFeel(laf);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
    JFrame f = new JFrame("Worker Thread Examples");
    WindowListener l = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    };
    f.addWindowListener(l);
    final ExampleContainer c = new ExampleContainer(
        new File(
        "D:/JBProjects/exp/src/exp/swing/andthreads/bigeg/Example1.java"));
    f.getContentPane().add(c);
    f.pack();
    f.show();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
       c.setFocus(); //can't do this until now
      }
    });
  }

}
