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

public class EntStvEquipmentConfig extends JPanel {
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
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
  JTextField jTextField9 = new JTextField();
  JLabel jLabel9 = new JLabel();
  JTextField jTextField10 = new JTextField();
  JLabel jLabel10 = new JLabel();
  JTextField jTextField11 = new JTextField();
  JTextField jTextField13 = new JTextField();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JLabel jLabel16 = new JLabel();
  JTextField jTextField14 = new JTextField();
  JTextField jTextField15 = new JTextField();
  JLabel jLabel17 = new JLabel();
  JTextField jTextField16 = new JTextField();
  JLabel jLabel18 = new JLabel();
  JTextField jTextField17 = new JTextField();
  JLabel jLabel19 = new JLabel();
  JTextField jTextField18 = new JTextField();
  JLabel jLabel110 = new JLabel();
  JTextField jTextField19 = new JTextField();
  JLabel jLabel111 = new JLabel();
  JTextField jTextField110 = new JTextField();
  JLabel jLabel112 = new JLabel();
  JTextField jTextField111 = new JTextField();
  Border border1;

  public EntStvEquipmentConfig() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    EntStvEquipmentConfig entStvEquipmentConfig1 = new EntStvEquipmentConfig();
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,5,5,5);
    gridLayout1.setRows(19);
    gridLayout1.setColumns(2);
    this.setLayout(gridLayout1);
    this.setBorder(border1);
    jLabel1.setText("ID");
    jLabel2.setText("Nome");
    jLabel3.setText("X");
    jLabel4.setText("Y");
    jLabel5.setText("Símbolo");
    jLabel6.setText("S. em alarme");
    jLabel7.setText("S. de inactividade");
    jLabel8.setText("Símbolo 3");
    jLabel9.setText("Símbolo 4");
    jLabel10.setText("Símbolo 5");
    jLabel14.setText("Tipo");
    jLabel15.setText("Activo");
    jLabel16.setText("Estação");
    jLabel17.setText("Zona");
    jLabel18.setText("Config. comunicações");
    jLabel19.setText("Estado genérico");
    jLabel110.setText("Estado específico");
    jLabel111.setText("Config. genérica");
    jLabel112.setText("Config. específica");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField1.setText("-1");
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField3.setText("Camara");
    jTextField4.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField4.setText("120");
    jTextField5.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField5.setText("27");
    jTextField6.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField6.setText("images/stv/camera.jpg");
    jTextField7.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField7.setText("images/stv/camera_alarm.jpg");
    jTextField8.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField8.setText("images/stv/camera_disable.jpg");
    jTextField13.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField13.setText("Camara");
    jTextField14.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField14.setText("Sim");
    jTextField9.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField10.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField11.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField15.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField16.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField17.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField18.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField19.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField110.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField111.setFont(new java.awt.Font("Dialog", 0, 11));
    this.add(jLabel1, null);
    this.add(jTextField1, null);
    this.add(jLabel2, null);
    this.add(jTextField3, null);
    this.add(jLabel3, null);
    this.add(jTextField4, null);
    this.add(jLabel4, null);
    this.add(jTextField5, null);
    this.add(jLabel5, null);
    this.add(jTextField6, null);
    this.add(jLabel6, null);
    this.add(jTextField7, null);
    this.add(jLabel7, null);
    this.add(jTextField8, null);
    this.add(jLabel8, null);
    this.add(jTextField9, null);
    this.add(jLabel9, null);
    this.add(jTextField10, null);
    this.add(jLabel10, null);
    this.add(jTextField11, null);
    this.add(jLabel14, null);
    this.add(jTextField13, null);
    this.add(jLabel15, null);
    this.add(jTextField14, null);
    this.add(jLabel16, null);
    this.add(jTextField15, null);
    this.add(jLabel17, null);
    this.add(jTextField16, null);
    this.add(jLabel18, null);
    this.add(jTextField17, null);
    this.add(jLabel19, null);
    this.add(jTextField18, null);
    this.add(jLabel110, null);
    this.add(jTextField19, null);
    this.add(jLabel111, null);
    this.add(jTextField110, null);
    this.add(jLabel112, null);
    this.add(jTextField111, null);
  }
}