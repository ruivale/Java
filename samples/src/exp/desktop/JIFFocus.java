/*
 *
 *
 *
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */


package exp.desktop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */
public class JIFFocus extends JPanel {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout();
  JDesktopPane jdp = new JDesktopPane();
  BorderLayout borderLayout2 = new BorderLayout();

  String justToBeModifByTheSubClasses = "a";


  //  JPanel jif1 = new JPanel();
  //JPanel jif2 = new JPanel();
  JInternalFrame jif1 = new JInternalFrame("A", false, false, false, false);
  JInternalFrame jif2 = new JInternalFrame("B", false, false, false, false);



  /**
   *
   * @args
   *
   */
  public JIFFocus () {
    try {
      jbInit();
      System.out.println("AAAAAAAAAAAAAAAAAAAAa");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   *
   * @args
   *
   * @exception Exception
   */
  private void jbInit () throws Exception {
    this.setLayout(borderLayout1);
    jButton1.setText("jButton1");
    jButton1.addActionListener(
      new java.awt.event.ActionListener() {

        public void actionPerformed (ActionEvent e) {
          jButton1_actionPerformed(e);
        }
      }
    );
    jButton2.setText("jButton2");
    jButton2.addActionListener(
      new java.awt.event.ActionListener() {

        public void actionPerformed (ActionEvent e) {
          jButton2_actionPerformed(e);
        }
      }
    );
    jPanel1.setLayout(gridLayout1);
    gridLayout1.setRows(2);
    jPanel2.setLayout(borderLayout2);
    this.add(jPanel1, BorderLayout.WEST);
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
    jPanel2.add(jdp, BorderLayout.CENTER);
    this.add(jPanel2, BorderLayout.CENTER);


    jif1.getContentPane().setLayout(new BorderLayout());
    jif1.getContentPane().add(new JButton("AAAAAAAAa"), BorderLayout.CENTER);
    jif2.getContentPane().setLayout(new BorderLayout());
    jif2.getContentPane().add(new JButton("BBBBBBBb"), BorderLayout.CENTER);

    jif1.addMouseMotionListener(new MouseMotionListener(){
      public void mouseDragged(MouseEvent e) {
        System.out.println("dragged jif1");
      }
      public void mouseMoved(MouseEvent e){
        System.out.println("moved jif1");
      }
    });
    jif2.addMouseMotionListener(new MouseMotionListener(){
      public void mouseDragged(MouseEvent e) {
        System.out.println("dragged jif2");
      }
      public void mouseMoved(MouseEvent e){
        System.out.println("moved jif2");
      }
    });

    jif1.setBounds(0, 0, 400, 400);
    jif1.setVisible(true);
    jif2.setBounds(100, 100, 400, 400);
    jif2.setVisible(true);
    jdp.add(jif1);
    jdp.add(jif2);
  }

  /**
   *
   * @args
   *
   * @param e
   */
  void jButton1_actionPerformed (ActionEvent e) {

    jif1.setBounds(0,0,this.jdp.getWidth(),this.jdp.getHeight());
    jif1.toFront();
    try {
      jif1.setSelected(true);
    } catch (Exception ea) {}
    // JPanel
    //    jif1.setVisible(true);
  }

  /**
   *
   * @args
   *
   * @param e
   */
  void jButton2_actionPerformed (ActionEvent e) {
    jif2.setBounds(0,0,this.jdp.getWidth(),this.jdp.getHeight());
    jif2.toFront();
    try {
      jif2.setSelected(true);
    } catch (Exception ea) {}
    // JPanel
    //    jif2.setVisible(true);
  }

  /**
   *
   * @args
   *
   * @param args
   */
  public static void main (String[] args) {
    JIFFocus JIFFocus1 = new JIFFocus();
    JFrame f = new JFrame();
    f.getContentPane().add(JIFFocus1);
    f.setBounds(50, 50, 400, 300);
    f.setVisible(true);
  }
}



