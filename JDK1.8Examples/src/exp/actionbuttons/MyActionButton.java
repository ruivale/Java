package exp.actionbuttons;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
 *
 *
 */
public class MyActionButton
    extends JFrame {

  GISTVActionOneByOne check = new GISTVActionOneByOne("CHECK");
  GISTVActionZoom toggle = new GISTVActionZoom("TOGGLE");
  GISTVActionOneByOne check2 = new GISTVActionOneByOne("CHECK2");
  GISTVActionZoom toggle2 = new GISTVActionZoom("TOGGLE2");

  int i = 0;

  GISTVActionAA aa = new GISTVActionAA("aa");

  public MyActionButton() {

    getContentPane().setLayout(new BorderLayout());

    JMenuBar jmb = new JMenuBar();
    JMenu jm = new JMenu("CLICA");
    jm.setLayout(new GridLayout(2, 1));
    jm.add(check);
    jm.add(toggle);
    jm.add(aa);
    jmb.add(jm);

    this.setJMenuBar(jmb);

    JPanel p = new JPanel(new BorderLayout());

    JToolBar jtb = new JToolBar();
    //jtb.setLayout(new BorderLayout());
    jtb.add(check2 /*, BorderLayout.EAST*/);
    jtb.add(toggle2 /*, BorderLayout.WEST*/);
    jtb.add(aa /*, BorderLayout.WEST*/);
    p.add(jtb, BorderLayout.CENTER);

    this.getContentPane().add(p, BorderLayout.NORTH);

    setBounds(100, 100, 500, 300);
    setVisible(true);

  }

  public static void main(String[] args) {
    MyActionButton myActionButton1 = new MyActionButton();
  }

}

class GISTVActionOneByOne
    extends JCheckBoxMenuItem
    implements ActionListener {
  static boolean _isSelected = true;

  public GISTVActionOneByOne(String text) {
    super(text, _isSelected);
    this.addActionListener(this);
  }

  public GISTVActionOneByOne(String text, Icon icon) {
    super(text, icon, _isSelected);
    this.addActionListener(this);
  }

  private static void actionPerformed() {

    System.out.println("  JCheckBoxMenuItem");

  }

  public void actionPerformed(ActionEvent e) {
    GISTVActionOneByOne.actionPerformed();
  }

}

class GISTVActionZoom
    extends JToggleButton
    implements ActionListener {
  static boolean isZoomed = true;

  public GISTVActionZoom(String text) {
    super(text);
    this.addActionListener(this);
  }

  public GISTVActionZoom(String text, Icon icon) {
    super(text, icon);
    this.addActionListener(this);
  }

  private static void actionPerformed() {

    System.out.println("  JToggleButton");

  }

  public void actionPerformed(ActionEvent e) {
    GISTVActionZoom.actionPerformed();
  }

}

class GISTVActionAA
    extends AbstractAction {
  static boolean isZoomed = true;

  public GISTVActionAA(String text) {
    super(text);
  }

  public GISTVActionAA(String text, Icon icon) {
    super(text, icon);
  }

  private static void actionPerformed() {

    System.out.println("  AbstractAction");

  }

  public void actionPerformed(ActionEvent e) {
    GISTVActionAA.actionPerformed();
  }

}
