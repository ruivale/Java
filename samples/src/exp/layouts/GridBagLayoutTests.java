package exp.layouts;


import java.awt.*;
import javax.swing.*;

import java.util.*;
import javax.swing.border.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class GridBagLayoutTests extends JPanel {
  Border border1;

  public GridBagLayoutTests(){
    jbinit();
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * DOCUMENT ME!
   *
   * @param name DOCUMENT ME!
   * @param gridbag DOCUMENT ME!
   * @param c DOCUMENT ME!
   */
  protected void makebutton(
    String             name,
    GridBagLayout      gridbag,
    GridBagConstraints c) {
    JButton button = new JButton(name);
    gridbag.setConstraints(button, c);
    add(button);
  }

  /**
   * DOCUMENT ME!
   */
  public void jbinit() {
    GridBagLayout      gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    setFont(new Font("Helvetica", Font.PLAIN, 14));
    setLayout(gridbag);

    c.fill      = GridBagConstraints.BOTH;
    c.weightx   = 1.0;
    makebutton("Button1", gridbag, c);
    //makebutton("Button2", gridbag, c);
    //makebutton("Button3", gridbag, c);

    c.gridwidth = GridBagConstraints.REMAINDER; //end row
    makebutton("Button4", gridbag, c);

    c.weightx = 0.0; //reset to the default
    makebutton("Button5", gridbag, c); //another row

    c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
    makebutton("Button6", gridbag, c);

    c.gridwidth = GridBagConstraints.REMAINDER; //end row
    makebutton("Button7", gridbag, c);

    c.gridwidth    = 1; //reset to the default
    c.gridheight   = 2;
    c.weighty      = 1.0;
    makebutton("Button8", gridbag, c);

    c.weighty      = 0.0; //reset to the default
    c.gridwidth    = GridBagConstraints.REMAINDER; //end row
    c.gridheight   = 1; //reset to the default
    makebutton("Button9", gridbag, c);
    makebutton("Button10", gridbag, c);

    setSize(300, 100);
  }

  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   */
  public static void main(String[] args) {
    JFrame      f   = new JFrame("GridBag Layout Example");
    GridBagLayoutTests gridBagLayoutTests = new GridBagLayoutTests();

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    f.getContentPane().add("Center", gridBagLayoutTests);
    f.pack();
    f.setSize(f.getPreferredSize());
    f.show();
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(0,5,0,5);
    this.setBorder(border1);
  }
}
