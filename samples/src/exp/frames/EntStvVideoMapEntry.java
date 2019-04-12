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

public class EntStvVideoMapEntry extends JPanel {
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JComboBox jTextField1 = new JComboBox();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField5 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JTextField jTextField6 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField7 = new JTextField();
  Border border1;

  public EntStvVideoMapEntry() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,5,5,5);
    gridLayout1.setRows(6);
    gridLayout1.setColumns(2);
    this.setLayout(gridLayout1);
    this.setBorder(border1);
    jLabel1.setToolTipText("");
    jLabel1.setText("ID");
    jLabel8.setText("Equipamento fonte");
    jLabel5.setText("Porto no eq. fonte");
    jLabel4.setText("Tipo de porto");
    jLabel2.setText("Equipamento alvo");
    jLabel3.setText("Porto no eq. alvo");
    jTextField7.setText("-1");
    jTextField3.addActionListener(new EntStvVideoMapEntry_jTextField3_actionAdapter(this));
    jTextField3.setText("199001");
    jTextField6.setText("1");
    jTextField5.setText("190011");
    jTextField4.setText("Saída");
    this.add(jLabel1, null);
    this.add(jTextField7, null);
    this.add(jLabel8, null);
    this.add(jTextField3, null);
    this.add(jLabel5, null);
    this.add(jTextField6, null);
    this.add(jLabel4, null);
    this.add(jTextField1, null);
    this.add(jLabel2, null);
    this.add(jTextField5, null);
    this.add(jLabel3, null);
    this.add(jTextField4, null);
    jTextField1.addItem("Saída");
    jTextField1.addItem("Entrada");
  }

  void jTextField3_actionPerformed(ActionEvent e) {

  }
}

class EntStvVideoMapEntry_jTextField3_actionAdapter implements java.awt.event.ActionListener {
  EntStvVideoMapEntry adaptee;

  EntStvVideoMapEntry_jTextField3_actionAdapter(EntStvVideoMapEntry adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField3_actionPerformed(e);
  }
}