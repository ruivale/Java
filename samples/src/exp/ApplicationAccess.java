
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


public class ApplicationAccess extends JFrame {
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();

  public ApplicationAccess() {
    try {
      jbInit();
      setVisible(true);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ApplicationAccess applicationAccess1 = new ApplicationAccess();
  }

  private void jbInit() throws Exception {
    jLabel1.setText("STV");
    jLabel1.setBounds(new Rectangle(52, 35, 137, 30));
    this.getContentPane().setLayout(null);
    jLabel2.setBounds(new Rectangle(52, 96, 137, 30));
    jLabel2.setText("Gestão");
    jLabel3.setBounds(new Rectangle(52, 66, 137, 30));
    jLabel3.setText("SIP");
    jLabel4.setBounds(new Rectangle(52, 133, 137, 30));
    jLabel4.setText("Editor de mapas");
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jLabel4, null);
  }
}