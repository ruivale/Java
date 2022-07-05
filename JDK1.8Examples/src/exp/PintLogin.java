
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;

import javax.swing.*;
import java.awt.*;


public class PintLogin extends JFrame {
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();

  public PintLogin() {
    try {
      jbInit();
      this.setIconImage((new ImageIcon("C:/MUXManager/images/ENT.jpg")).getImage());
      setVisible(true);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    PintLogin pintLogin1 = new PintLogin();
  }

  private void jbInit() throws Exception {
    jLabel1.setText("Utilizador");
    jLabel1.setBounds(new Rectangle(13, 15, 80, 20));
    this.getContentPane().setLayout(null);
    jLabel2.setBounds(new Rectangle(13, 50, 80, 20));
    jLabel2.setText("Palavra chave");
    jTextField1.setBounds(new Rectangle(108, 15, 178, 20));
    jTextField2.setBounds(new Rectangle(108, 50, 178, 20));
    jButton1.setText("Ok");
    jButton1.setBounds(new Rectangle(82, 91, 90, 25));
    jButton2.setBounds(new Rectangle(196, 91, 90, 25));
    jButton2.setText("Apagar");
    this.setTitle("  Autenticação");
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
  }
}