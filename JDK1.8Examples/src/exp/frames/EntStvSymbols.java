package exp.frames;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class EntStvSymbols extends JPanel {
  JLabel jLabel1 = new JLabel();
  GridLayout gridLayout1 = new GridLayout();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel2 = new JLabel();
  Border border1;

  public EntStvSymbols() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,5,5,5);
    this.setLayout(gridLayout1);
    gridLayout1.setRows(2);
    gridLayout1.setColumns(2);
    this.setBorder(border1);
    jTextField2.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField2.setText("1000");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField1.setText("images/stv/camera.gif");
    jLabel1.setToolTipText("");
    jLabel1.setText("ID");
    jLabel2.setText("Símbolo");
    this.add(jLabel1, null);
    this.add(jTextField2, null);
    this.add(jLabel2, null);
    this.add(jTextField1, null);
  }
}