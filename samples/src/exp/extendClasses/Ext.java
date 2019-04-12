package exp.extendClasses;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



public class Ext extends Main{


  JTextField jtf = new JTextField();

  public Ext(String name) {

    JButton b = new JButton(name);
    b.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed (ActionEvent e) {
          changeValue();
        }
      }
    );

    JFrame f = new JFrame(name);
    f.getContentPane().setLayout(null);

    b.setBounds(0,0,100,30);
    jtf.setBounds(0,50,50,30);

    f.getContentPane().add(b);
    f.getContentPane().add(jtf);

    f.setBounds(20,20,200,150);

    f.setVisible(true);


  }

  public void changeValue(){
    System.out.println("o super.justToBeModifBySubClasses e': "+super.justToBeModifBySubClasses);
    super.justToBeModifBySubClasses = jtf.getText();
    System.out.println("o super.justToBeModifBySubClasses depois e': "+super.justToBeModifBySubClasses);
  }

  public static void main(String[] args) {
    Ext ext1 = new Ext("AAAAAAAAA");
  }
}