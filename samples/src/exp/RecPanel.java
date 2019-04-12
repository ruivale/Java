
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
import java.awt.event.*;
import javax.swing.border.*;

public class RecPanel extends JInternalFrame {

  JSplitPane jSplitPane1 = new JSplitPane();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JTable jTable1 = new JTable();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JCheckBox jCheckBox1 = new JCheckBox();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();
  JPanel jPanel5 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  JButton jButton7 = new JButton();
  JButton jButton8 = new JButton();
  BorderLayout borderLayout3 = new BorderLayout();
  Border border1;
  Border border2;
  Border border3;
  Border border4;
  Border border5;

  public RecPanel() {

    super(" Painel de controlo das sequências de cãmaras",true,true,true,true);

    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    RecPanel recPanel1 = new RecPanel();
    recPanel1.pack();
    JFrame f = new JFrame();
    JDesktopPane jdp = new JDesktopPane();
    recPanel1.setVisible(true);
    jdp.add(recPanel1);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(jdp);
    f.setSize(400,400);
    f.setVisible(true);

  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(6,6,6,6);
    border2 = BorderFactory.createEmptyBorder(5,5,5,5);
    border3 = BorderFactory.createEmptyBorder(5,5,5,5);
    border4 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(178, 178, 178),new Color(124, 124, 124));
    border5 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(142, 142, 142),new Color(99, 99, 99));
    this.getContentPane().setLayout(borderLayout3);
    jPanel2.setLayout(borderLayout1);
    jPanel3.setLayout(borderLayout2);
    jButton1.setFont(new java.awt.Font("Dialog", 1, 12));
    jButton1.setText("Adicionar");
    jButton2.setFont(new java.awt.Font("Dialog", 1, 12));
    jButton2.setText("Remover");
    jCheckBox1.setText("Em ciclo");
    jButton4.setText("            ");
    jButton5.setText("Adicionar");
    jButton6.setText("Remover");
    jButton3.setToolTipText("");
    jButton3.setText("            ");
    jPanel5.setLayout(flowLayout1);
    jButton7.setText("    Sair    ");
    jButton7.addActionListener(new RecPanel_jButton7_actionAdapter(this));
    jButton8.setText("Cancelar");
    jSplitPane1.setBorder(border1);
    jPanel4.setBorder(border2);
    jPanel5.setBorder(border3);
    jTable1.setBorder(border4);
    jPanel1.setBorder(border5);
    this.setPreferredSize(new Dimension(837, 630));
    this.getContentPane().setBackground(Color.lightGray);
    this.getContentPane().add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(jPanel1, JSplitPane.TOP);
    jSplitPane1.add(jPanel2, JSplitPane.BOTTOM);
    jPanel2.add(jTable1, BorderLayout.CENTER);
    this.getContentPane().add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jPanel4, BorderLayout.NORTH);
    jPanel4.add(jButton1, null);
    jPanel4.add(jButton2, null);
    jPanel4.add(jCheckBox1, null);
    jPanel4.add(jButton3, null);
    jPanel4.add(jButton4, null);
    jPanel4.add(jButton5, null);
    jPanel4.add(jButton6, null);
    jPanel3.add(jPanel5, BorderLayout.CENTER);
    jPanel5.add(jButton7, null);
    jPanel5.add(jButton8, null);
  }

  void jButton7_actionPerformed(ActionEvent e) {

  }
}

class RecPanel_jButton7_actionAdapter implements java.awt.event.ActionListener {
  RecPanel adaptee;

  RecPanel_jButton7_actionAdapter(RecPanel adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton7_actionPerformed(e);
  }
}