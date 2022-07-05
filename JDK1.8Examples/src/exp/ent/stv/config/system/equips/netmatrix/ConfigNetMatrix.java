package exp.ent.stv.config.system.equips.netmatrix;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ConfigNetMatrix extends JPanel{
  public ConfigNetMatrix() {
    try {
      jbInit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ConfigNetMatrix c = new ConfigNetMatrix();
    JFrame f = new JFrame("NetVideoRecRecordings");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(c, BorderLayout.CENTER);
    f.pack();
    f.setVisible(true);

  }

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jButton1.setText("Guardar");
    jButton2.setText("Salir");
    jLabel1.setText("Nombre:");
    jPanelNorth.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    jLabel2.setText("Matriz VW");
    jPanel1.setLayout(borderLayout2);
    this.add(jPanelSouth, java.awt.BorderLayout.SOUTH);
    jPanelSouth.add(jButton1);
    jPanelSouth.add(jButton2);
    this.add(jPanelNorth, java.awt.BorderLayout.NORTH);
    jPanelNorth.add(jLabel1);
    jPanelNorth.add(jLabel2);
    this.add(jPanel1, java.awt.BorderLayout.CENTER);
    jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);
    jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);
  }

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanelSouth = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JPanel jPanelNorth = new JPanel();
  JLabel jLabel1 = new JLabel();
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
}
