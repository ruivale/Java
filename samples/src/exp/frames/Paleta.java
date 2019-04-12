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


public class Paleta extends JPanel{
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  Border border1;

  public Paleta() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,5,5,5);
    this.setLayout(borderLayout1);
    this.setBorder(border1);
    this.add(jTabbedPane1, BorderLayout.CENTER);
    jTabbedPane1.add(jPanel1, "Equipamentos");
    jTabbedPane1.add(jPanel2, "Genéricos");


  }
  public static void main(String[] args) {
    Paleta paleta1 = new Paleta();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(paleta1, BorderLayout.CENTER);
    f.setBounds(200,200,300,200);
    f.setVisible(true);
  }
}