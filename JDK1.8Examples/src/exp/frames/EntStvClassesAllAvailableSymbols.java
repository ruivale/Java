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

public class EntStvClassesAllAvailableSymbols extends JPanel {
  JLabel jLabel1 = new JLabel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  Border border1;
  Border border2;
  JComboBox jComboBox1 = new JComboBox();

  public EntStvClassesAllAvailableSymbols() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,5,5,5);
    border2 = BorderFactory.createEmptyBorder(1,0,0,0);
    jLabel1.setBorder(border2);
    jLabel1.setToolTipText("");
    jLabel1.setText("1000 - images/stv/camera.gif");
    this.setLayout(gridLayout1);
    gridLayout1.setRows(8);
    gridLayout1.setColumns(1);
    jLabel2.setBorder(border2);
    jLabel2.setToolTipText("");
    jLabel2.setText("1007 - images/stv/cam_e.gif");
    jLabel3.setBorder(border2);
    jLabel3.setToolTipText("");
    jLabel3.setText("1006 - images/stv/actuador.gif");
    jLabel4.setBorder(border2);
    jLabel4.setToolTipText("");
    jLabel4.setText("1005 - images/stv/sensor.gif");
    jLabel5.setBorder(border2);
    jLabel5.setToolTipText("");
    jLabel5.setText("1004 - images/stv/codec.gif");
    jLabel6.setBorder(border2);
    jLabel6.setToolTipText("");
    jLabel6.setText("1003 - images/stv/dvr.gif");
    jLabel7.setBorder(border2);
    jLabel7.setToolTipText("");
    jLabel7.setText("1002 - images/stv/vcr.gif");
    jLabel8.setBorder(border2);
    jLabel8.setToolTipText("");
    jLabel8.setText("1001 - images/stv/cam_w.gif");
    this.setBorder(border1);
    this.add(jLabel1, null);
    this.add(jComboBox1, null);
    this.add(jLabel8, null);
    this.add(jLabel7, null);
    this.add(jLabel6, null);
    this.add(jLabel5, null);
    this.add(jLabel4, null);
    this.add(jLabel3, null);
    this.add(jLabel2, null);
  }
}