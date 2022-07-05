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

public class EntStvGroupConfig extends JPanel {
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField3 = new JTextField();
  Border border1;

  public EntStvGroupConfig() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    EntStvGroupConfig entStvGroupConfig1 = new EntStvGroupConfig();
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,5,5,5);
    gridLayout1.setRows(3);
    gridLayout1.setColumns(2);
    this.setLayout(gridLayout1);
    this.setBorder(border1);
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField3.setText("233,211,156");
    jTextField2.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField2.setText("Linha 1");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField1.setText("-1");
    jLabel1.setText("ID");
    jLabel2.setText("Nome");
    jLabel3.setText("Cor");
    this.add(jLabel1, null);
    this.add(jTextField1, null);
    this.add(jLabel2, null);
    this.add(jTextField2, null);
    this.add(jLabel3, null);
    this.add(jTextField3, null);
  }
}