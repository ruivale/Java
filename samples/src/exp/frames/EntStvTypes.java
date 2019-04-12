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


public class EntStvTypes extends JPanel {
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
  JTextField jTextField6 = new JTextField();
  JTextField jTextField8 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  Border border1;
  JTextField jTextField9 = new JTextField();

  public EntStvTypes() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,5,5,5);
    gridLayout1.setRows(8);
    gridLayout1.setColumns(2);
    this.setLayout(gridLayout1);
    jLabel1.setText("ID");
    jLabel2.setText("Nome");
    jLabel3.setToolTipText("Classe do equipamento");
    jLabel3.setText("Classe do equipamento");
    jLabel4.setToolTipText("");
    jLabel4.setText("Terminais de vídeo");
    jLabel5.setText("Configuração de comunicões");
    jLabel6.setText("Configuração genérica");
    jLabel7.setText("Sintaxe de config. específica");
    jLabel8.setToolTipText("Driver");
    jLabel8.setText("Driver");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField1.setText("TLR Philips LTC3990");
    jTextField2.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField2.setText("Videorecorder");
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField3.setText("I1,O1|");
    jTextField4.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField4.setText("Serial$<1200,2400,4800,9600>;N;8;1|");
    jTextField6.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField6.setText("NVin1|NVout1|");
    jTextField5.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField5.setText("ST#|CT#|");
    jTextField8.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField8.setText("TlrLTC3990.so");
    this.setBorder(border1);
    jTextField9.setText("301");
    jTextField9.addActionListener(new EntStvTypes_jTextField9_actionAdapter(this));
    jTextField9.setFont(new java.awt.Font("Dialog", 0, 11));
    this.add(jLabel1, null);
    this.add(jTextField9, null);
    this.add(jLabel2, null);
    this.add(jTextField1, null);
    this.add(jLabel3, null);
    this.add(jTextField2, null);
    this.add(jLabel4, null);
    this.add(jTextField3, null);
    this.add(jLabel5, null);
    this.add(jTextField4, null);
    this.add(jLabel6, null);
    this.add(jTextField6, null);
    this.add(jLabel7, null);
    this.add(jTextField5, null);
    this.add(jLabel8, null);
    this.add(jTextField8, null);
  }

  void jTextField9_actionPerformed(ActionEvent e) {

  }
}

class EntStvTypes_jTextField9_actionAdapter implements java.awt.event.ActionListener {
  EntStvTypes adaptee;

  EntStvTypes_jTextField9_actionAdapter(EntStvTypes adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField9_actionPerformed(e);
  }
}