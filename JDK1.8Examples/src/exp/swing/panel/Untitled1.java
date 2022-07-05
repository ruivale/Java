package exp.swing.panel;


import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;


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
public class Untitled1
    extends JPanel {
  public Untitled1() {
    try {
      jbInit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Untitled1 u = new Untitled1();
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new java.awt.BorderLayout());
    f.getContentPane().add(u, java.awt.BorderLayout.CENTER);
    f.pack();
    f.setVisible(true);
  }

  private void jbInit() throws Exception {
    border2 = new TitledBorder(BorderFactory.createLineBorder(Color.black, 2),
                               "Câmara");
    this.setLayout(null);
    jPanel1.setBorder(border2);
    jPanel1.setBounds(new Rectangle(23, 32, 556, 81));
    jPanel1.setLayout(null);
    jLabel1.setText("Estação:");
    jLabel1.setBounds(new Rectangle(14, 17, 65, 21));
    jComboBox1.setBounds(new Rectangle(13, 44, 134, 23));
    jComboBox2.setBounds(new Rectangle(160, 44, 137, 23));
    jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 52));
    jLabel2.setText("[");
    jLabel2.setBounds(new Rectangle(326, 17, 26, 56));
    jComboBox3.setBounds(new Rectangle(354, 45, 111, 23));
    jLabel3.setFont(new java.awt.Font("Dialog", Font.BOLD, 52));
    jLabel3.setText("]");
    jLabel3.setBounds(new Rectangle(471, 17, 21, 57));
    jLabel4.setText("Preset:");
    jLabel4.setBounds(new Rectangle(354, 22, 52, 15));
    jLabel9.setText("Câmara");
    jLabel9.setBounds(new Rectangle(160, 16, 65, 21));
    this.add(jPanel1);
    jPanel1.add(jLabel1);
    jPanel1.add(jComboBox1);
    jPanel1.add(jComboBox2);
    jPanel1.add(jLabel9);
    jPanel1.add(jComboBox3);
    jPanel1.add(jLabel4);
    jPanel1.add(jLabel2);
    jPanel1.add(jLabel3);
  }

  JPanel jPanel1 = new JPanel();
  Border border1 = BorderFactory.createEmptyBorder();
  Border border2 = new TitledBorder(border1, "Câmara");
  JLabel jLabel1 = new JLabel();
  JComboBox jComboBox1 = new JComboBox();
  JComboBox jComboBox2 = new JComboBox();
  JLabel jLabel2 = new JLabel();
  JComboBox jComboBox3 = new JComboBox();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel9 = new JLabel();
}
