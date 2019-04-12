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

public class EntStvClasses extends JPanel {
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

  public EntStvClasses() {
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
    jLabel3.setText("Equipamento fonte");
    jLabel4.setText("Equipamento alvo");
    jLabel5.setToolTipText("");
    jLabel5.setText("Eq. possível em sequências");
    jLabel6.setText("Sintaxe de config. genérica");
    jLabel7.setText("Símbolo por defeito");
    jLabel8.setText("Símbolos permitidos");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField1.setText("700");
    jTextField2.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField2.setText("Codec");
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField3.setText("0");
    jTextField4.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField4.setText("0");
    jTextField6.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField6.setText("0");
    jTextField5.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField5.setText("NVin#|NVout#|TY<t,r,b>|PE<0,1>|");
    jTextField8.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField8.setText("1009");
    this.setBorder(border1);
    this.add(jLabel1, null);
    this.add(jTextField1, null);
    this.add(jLabel2, null);
    this.add(jTextField2, null);
    this.add(jLabel3, null);
    this.add(jTextField3, null);
    this.add(jLabel4, null);
    this.add(jTextField4, null);
    this.add(jLabel5, null);
    this.add(jTextField6, null);
    this.add(jLabel6, null);
    this.add(jTextField5, null);
    this.add(jLabel7, null);
    this.add(jTextField8, null);
    this.add(jLabel8, null);
  }
}