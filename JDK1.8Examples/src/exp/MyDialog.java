/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class MyDialog
    extends JDialog {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout3 = new GridLayout();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();
  Border border3;
  TitledBorder titledBorder2;
  Border border4;
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();
  JButton jButton7 = new JButton();

  public MyDialog() {
    try {
      jbInit();
      pack();
      setVisible(true);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    MyDialog myDialog1 = new MyDialog();
  }

  private void jbInit() throws Exception {

    border2 = BorderFactory.createCompoundBorder(
        new TitledBorder("Interfaces"),
        BorderFactory.createEmptyBorder(5, 5, 5, 5));

    border4 = BorderFactory.createCompoundBorder(
        new TitledBorder("Acções"),
        BorderFactory.createEmptyBorder(5, 5, 5, 5));

    this.getContentPane().setLayout(borderLayout1);
    jPanel1.setLayout(gridLayout1);
    jPanel3.setLayout(gridLayout2);
    gridLayout2.setRows(4);
    gridLayout2.setColumns(1);
    gridLayout2.setVgap(3);
    gridLayout1.setColumns(2);
    gridLayout1.setVgap(5);
    gridLayout1.setHgap(5);
    jPanel4.setLayout(gridLayout3);
    gridLayout3.setRows(4);
    gridLayout3.setColumns(1);
    gridLayout3.setVgap(3);
    jPanel3.setBorder(border2);
    jButton1.setText("E1 não ATM");
    jButton2.setText("jButton2");
    jButton3.setText("jButton3");
    jButton4.setText("jButton4");
    jPanel4.setBorder(border4);
    jButton5.setText("jButton5");
    jButton6.setText("jButton6");
    jButton7.setText("jButton7");
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel3, null);
    jPanel3.add(jButton1, null);
    jPanel3.add(jButton2, null);
    jPanel3.add(jButton3, null);
    jPanel3.add(jButton4, null);
    jPanel1.add(jPanel4, null);
    jPanel4.add(jButton5, null);
    jPanel4.add(jButton6, null);
    this.getContentPane().add(jPanel2, BorderLayout.EAST);
    jPanel2.add(jButton7, null);
  }

}
