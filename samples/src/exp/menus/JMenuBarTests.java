package exp.menus;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class JMenuBarTests extends JFrame {

    JMenuBar b2 = new JMenuBar();
    JMenuBar b4 = new JMenuBar();

    JFrame f = this;

    int i=0;

  public JMenuBarTests() {

    JButton b = new JButton("CLICA");
    this.getContentPane().setLayout(new java.awt.BorderLayout());
    this.getContentPane().add(b, java.awt.BorderLayout.CENTER);

    b.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("        b4.getMenuCount(): "+ b4.getMenuCount() +".");

/*        JMenuBar b4 = new JMenuBar();

        JMenu m3 = new JMenu(""+i);
        JMenu m4 = new JMenu(""+i);
        JMenu m5 = new JMenu(""+i);
        JMenu m6 = new JMenu(""+i);

        i++;

        b4.add(m3);
        b4.add(m4);
        b4.add(m5);
        b4.add(m6);
*/
        int nbrOfMenus = b2.getMenuCount();
        System.out.println("        b2.getMenuCount(): "+nbrOfMenus+".");
        for (int i=nbrOfMenus-1; i>0; i--) {
          b2.remove(b2.getMenu(i));
        }
        nbrOfMenus = b4.getMenuCount();
        System.out.println("        b4.getMenuCount(): "+nbrOfMenus+".");
        JMenu[] menus = new JMenu[nbrOfMenus];
        for (int i = 0; i < nbrOfMenus; i++) {
          menus[i] = b4.getMenu(i);
        }
        for (int i = 0; i <nbrOfMenus ; i++) {
          b2.add(menus[i], (i+1));
        }
        menus = null;
        f.setJMenuBar(b2);
//        f.setBounds(50,50,300,250);
  //      f.setVisible(true);

      }
    });


    JMenu m1 = new JMenu("m1");
    JMenu m2 = new JMenu("m2");
    JMenu m3 = new JMenu("m3");
    JMenu m4 = new JMenu("m4");
    JMenu m5 = new JMenu("m5");
    JMenu m6 = new JMenu("m6");

    b2.add(m1);
    b2.add(m2);
    b4.add(m3);
    b4.add(m4);
    b4.add(m5);
    b4.add(m6);

      int nbrOfMenus = b2.getMenuCount();
      System.out.println("b2.getMenuCount(): "+nbrOfMenus+".");
      for (int i=nbrOfMenus-1; i>0; i--) {
        b2.remove(b2.getMenu(i));
      }
      nbrOfMenus = b4.getMenuCount();
      System.out.println("b4.getMenuCount(): "+nbrOfMenus+".");
      JMenu[] menus = new JMenu[nbrOfMenus];
      for (int i = 0; i < nbrOfMenus; i++) {
        menus[i] = b4.getMenu(i);
      }
      for (int i = 0; i <nbrOfMenus ; i++) {
        b2.add(menus[i], (i+1));
      }

      menus = null;

    this.setJMenuBar(b2);
    this.setBounds(50,50,300,250);
    this.setVisible(true);
    System.out.println("    b4.getMenuCount(): "+nbrOfMenus+".");
  }
  public static void main(String[] args) {
    JMenuBarTests JMenuBarTests1 = new JMenuBarTests();
  }
}