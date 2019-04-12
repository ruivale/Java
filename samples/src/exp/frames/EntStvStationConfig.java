package exp.frames;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class EntStvStationConfig extends JPanel {
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField5 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JTextField jTextField6 = new JTextField();
  JLabel jLabel6 = new JLabel();
  JTextField jTextField7 = new JTextField();
  JLabel jLabel7 = new JLabel();
  JTextField jTextField8 = new JTextField();
  JLabel jLabel8 = new JLabel();
  Border border1;

  public EntStvStationConfig() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    EntStvStationConfig entStvStationConfig1 = new EntStvStationConfig();
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,5,5,5);
    jLabel1.setToolTipText("");
    jLabel1.setText("ID");
    gridLayout1.setRows(8);
    gridLayout1.setColumns(2);
    this.setLayout(gridLayout1);
    jLabel2.setToolTipText("");
    jLabel2.setText("Nome");
    jLabel3.setToolTipText("");
    jLabel3.setText("X");
    jLabel4.setToolTipText("");
    jLabel4.setText("Y");
    jLabel5.setToolTipText("");
    jLabel5.setText("Símbolo");
    jLabel6.setToolTipText("");
    jLabel6.setText("Activo");
    jLabel7.setToolTipText("");
    jLabel7.setText("ID servidor");
    jLabel8.setToolTipText("");
    jLabel8.setText("Estado");
    this.setBorder(border1);
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField1.setText("-1");
    jTextField2.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField2.setText("Viso");
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField3.setText("156");
    jTextField4.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField4.setText("23");
    jTextField5.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField5.setText("images/stv/viso.jpg");
    jTextField6.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField6.setText("1");
    jTextField8.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField8.setText("1");
    jTextField7.setFont(new java.awt.Font("Dialog", 0, 11));
    this.add(jLabel1, null);
    this.add(jTextField1, null);
    this.add(jLabel2, null);
    this.add(jTextField2, null);
    this.add(jLabel3, null);
    this.add(jTextField3, null);
    this.add(jLabel4, null);
    this.add(jTextField4, null);
    this.add(jLabel5, null);
    this.add(jTextField5, null);
    this.add(jLabel6, null);
    this.add(jTextField6, null);
    this.add(jLabel7, null);
    this.add(jTextField7, null);
    this.add(jLabel8, null);
    this.add(jTextField8, null);
  }
}