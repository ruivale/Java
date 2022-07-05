package exp.swing.jcolorchooser;

import javax.swing.*;
import java.awt.*;

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
public class SeparatePanels extends JPanel{

  JColorChooser jcc = new JColorChooser();

  static{
    new JColorChooserUIDefaults();
  }

  public SeparatePanels() {

    jcc.showDialog(null, "title", Color.black);

    /*
    JPanel[] ps = jcc.getChooserPanels();
    JTabbedPane jtp = new JTabbedPane();

    for (int i = 0; i < ps.length; i++) {
      jtp.add(" i ",ps[i]);
    }

    setLayout(new BorderLayout());
    add(jtp);
    */

  }

  public static void main(String[] args) {
    SeparatePanels s = new SeparatePanels();
    JFrame f = new JFrame("...");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(s);
    f.pack();
    f.setLocation(100,100);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
