
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
import java.util.*;


public class STV extends JPanel {
  JButton jButton1 = new JButton();

  public STV() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    STV STV1 = new STV();
    JFrame f = new JFrame();
    JDesktopPane jdp = new JDesktopPane();
    JInternalFrame jif = new JInternalFrame(" Painel de controlo do vídeo gravador", true, true, true, true);
    jif.getContentPane().setLayout(new BorderLayout());
    jif.getContentPane().add(STV1, BorderLayout.CENTER);
    jif.setBounds(0,0,300,300);
    jif.setVisible(true);
    jdp.add(jif);
    jdp.setBounds(10,10,400,400);
    f.getContentPane().add(jdp);
    f.setVisible(true);
  }

  private void jbInit() throws Exception {
    jButton1.setText("Sair");
    jButton1.setBounds(new Rectangle(264, 189, 95, 30));
    setLayout(null);

    Vector vector = new Vector();
    vector.addElement("aldjlkasjdlask");
    this.add(jButton1, null);

  }
}