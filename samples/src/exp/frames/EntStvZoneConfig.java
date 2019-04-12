package exp.frames;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class EntStvZoneConfig extends JPanel {
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel3 = new JLabel();
  Border border1;
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField6 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();

  public EntStvZoneConfig() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    EntStvZoneConfig entStvZoneConfig1 = new EntStvZoneConfig();
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,5,5,5);
    gridLayout1.setRows(6);
    gridLayout1.setColumns(2);
    this.setLayout(gridLayout1);
    this.setBorder(border1);
    jLabel1.setText("ID");
    jLabel3.setText("Nome");
    jLabel2.setText("X");
    jLabel4.setText("Y");
    jLabel5.setText("Color");
    jLabel6.setText("Símbolo");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField1.setText("-1");
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField3.setText("Zona 1");
    jTextField2.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField2.setText("128");
    jTextField5.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField5.setText("145");
    jTextField4.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField4.setText("243,200,155");
    jTextField4.addActionListener(new EntStvZoneConfig_jTextField4_actionAdapter(this));
    jTextField6.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField6.setText("images/stv/zon1viso.jpg");
    this.add(jLabel1, null);
    this.add(jTextField1, null);
    this.add(jLabel3, null);
    this.add(jTextField3, null);
    this.add(jLabel2, null);
    this.add(jTextField2, null);
    this.add(jLabel4, null);
    this.add(jTextField5, null);
    this.add(jLabel5, null);
    this.add(jTextField4, null);
    this.add(jLabel6, null);
    this.add(jTextField6, null);
  }

  void jTextField4_actionPerformed(ActionEvent e) {

  }
}

class EntStvZoneConfig_jTextField4_actionAdapter implements java.awt.event.ActionListener {
  EntStvZoneConfig adaptee;

  EntStvZoneConfig_jTextField4_actionAdapter(EntStvZoneConfig adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField4_actionPerformed(e);
  }
}