package exp.desktop;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */


import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 *
 *
 */
public class GITest extends JFrame {
  JButton jButton1 = new JButton("GITest");

  GraphicInterfaceApplication gi = new GraphicInterfaceApplication();
  GraphicInterfaceApplication gi2 = new GraphicInterfaceApplication();


  /**
   *
   */
  public GITest() {

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    gi.setTitle("111111111");

    JPanel p = new JPanel();
    JButton b = new JButton("111111");
    //    b.setBounds(0,0,200,50);
    b.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed (ActionEvent e) {
          jButton1_actionPerformed();
        }
      }
    );
    p.setLayout(new BorderLayout());
    p.add(b, BorderLayout.CENTER);

    gi.setTheDefaultLayer(p);

    gi2.setTitle("2222222");

    JPanel p2 = new JPanel();
    JButton b2 = new JButton("22222");
    //    b.setBounds(0,0,200,50);
    b2.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed (ActionEvent e) {
          jButton3_actionPerformed();
        }
      }
    );
    p2.setLayout(new BorderLayout());
    p2.add(b2, BorderLayout.CENTER);

    gi2.setTheDefaultLayer(p2);


    TransparentDesktopPane jdp = new TransparentDesktopPane(true);
    jdp.setLayout(null);
    gi.setBounds(10,10,500,300);
    gi2.setBounds(320,520,100,100);
    jdp.add(gi);
    jdp.add(gi2);
/*
    jdp.setLayout(new BorderLayout());
    jdp.add(gi, BorderLayout.CENTER);
    jdp.add(g2, BorderLayout.CENTER);
*/
    getContentPane().setLayout(new BorderLayout());
    setBounds(20, 20, 900, 700);
    getContentPane().add(jdp, BorderLayout.CENTER);
    setVisible(true);





  }


  // Button events


  /**
   *
   */
  public void jButton1_actionPerformed() {

    System.out.println("clickei no JButton(setTheDefaultLayer");

    final JInternalFrame jif = new JInternalFrame("jif");
    jif.getContentPane().setLayout(new FlowLayout());

    JButton b = new JButton("No clicks available for the button in JIF under!");
    b.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed (ActionEvent e) {
          jButton2_actionPerformed();
        }
      }
    );
    jif.getContentPane().add(b);

    gi.showGIModalWindows(new JInternalFrame[]{jif});
  }




  /**
   *
   */
  public void jButton2_actionPerformed() {

    gi.dismissGIModalWindows();

  }

  /**
   *
   */
  public void jButton4_actionPerformed() {

    gi2.dismissGIModalWindows();

  }


  /**
   *
   */
  public void jButton3_actionPerformed() {

    System.out.println("clickei no JButton(setTheDefaultLayer");

    JInternalFrame jif = new JInternalFrame("jif");
    jif.getContentPane().setLayout(new FlowLayout());
    JInternalFrame jif2 = new JInternalFrame("jif2");
    jif2.getContentPane().setLayout(new FlowLayout());
    JInternalFrame jif3 = new JInternalFrame("jif3");
    jif3.getContentPane().setLayout(new FlowLayout());
    final JInternalFrame jif4 = new JInternalFrame("jif4");
    jif4.getContentPane().setLayout(new FlowLayout());

    JButton b = new JButton("jifs");
    b.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed (ActionEvent e) {
          jButton4_actionPerformed();
        }
      }
    );
    JButton b1 = new JButton("uuuu");
    b1.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed (ActionEvent e) {
          jButton4_actionPerformed();
        }
      }
    );
    JButton b2 = new JButton("juuuuuuu");
    b2.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed (ActionEvent e) {
          jButton4_actionPerformed();
        }
      }
    );
    JButton b3 = new JButton("sdttttt");
    b3.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed (ActionEvent e) {
//          jButton2_actionPerformed();
          jif4.setVisible(false);
        }
      }
    );
    jif.getContentPane().add(b);
    jif2.getContentPane().add(b1);
    jif3.getContentPane().add(b2);
    jif4.getContentPane().add(b3);

    JInternalFrame[] jifs = {jif, jif2};
    gi2.showGIModalWindows(new JInternalFrame[]{jif, jif2, jif3, jif4});
  }







  //
  public static void main(String[] args) {
    GITest GITest1 = new GITest();
  }
}
