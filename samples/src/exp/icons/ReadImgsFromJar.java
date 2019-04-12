package exp.icons;

import java.awt.*;
import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ReadImgsFromJar extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  JButton jButton1 = new JButton();

  public ReadImgsFromJar() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jButton1.setText("jButton1");
    this.setLayout(borderLayout1);
    this.add(jButton1, BorderLayout.CENTER);

    // The image is in the images/ dir inside the jar.
    Icon icon = new ImageIcon(
        ReadImgsFromJar.class.getResource("images/ReadImgsFromJar.gif"));

    this.jButton1.setIcon(icon);


    //new java.net.URL().getFile();

  }
  public static void main(String[] args) {
    ReadImgsFromJar r = new ReadImgsFromJar();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(r);
    f.pack();
    f.setLocation(200,200);
    f.setVisible(true);
  }
}