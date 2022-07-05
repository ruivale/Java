package exp.tables;

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

public class A extends JPanel {
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();

  public A() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    A a1 = new A();


    JFrame f = new JFrame();
    f.getContentPane().add(a1);
    f.setVisible(true);

  }
  private void jbInit() throws Exception {
    jButton1.setText("jButton1");
    jButton1.addActionListener(new ActionListener(this));
    jButton2.setText("jButton2");


    jButton2.addActionListener(new A_jButton2_actionAdapter(this));



    this.add(jButton1, null);
    this.add(jButton2, null);
  }

  void jButton1_actionPerformed(ActionEvent e) {

    this.jButton2_actionPerformed(null);

  }

  void jButton2_actionPerformed(ActionEvent e) {


System.out.println("jButton2_actionPerformed");



  }
}

class ActionListener implements java.awt.event.ActionListener {
  A adaptee;

  ActionListener(A adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class A_jButton2_actionAdapter implements java.awt.event.ActionListener {
  A adaptee;

  A_jButton2_actionAdapter(A adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}