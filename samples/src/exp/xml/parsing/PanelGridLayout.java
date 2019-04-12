package exp.xml.parsing;


import java.awt.*;

import javax.swing.*;


/**
 * Title: Description: Copyright:    Copyright (c) Company:
 *
 * @author
 * @version 1.0
 */
public class PanelGridLayout
    extends JPanel {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  GridLayout gridLayout1 = new GridLayout();

  /** .. */
  JButton jButton1 = new JButton();

  /** .. */
  JButton jButton2 = new JButton();

  /** .. */
  JButton jButton3 = new JButton();

  /** .. */
  JButton jButton4 = new JButton();

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new PanelGridLayout object.
   */
  public PanelGridLayout() {
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    PanelGridLayout panelGridLayout1 = new PanelGridLayout();
    JFrame          f = new JFrame();
    f.getContentPane()
     .add(panelGridLayout1);
    f.setVisible(true);
  }

  /**
   * Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  private void jbInit()
      throws Exception {
    jButton1.setText("jButton1");
    this.setLayout(gridLayout1);
    jButton2.setText("jButton2");
    jButton3.setText("jButton3");
    jButton4.setText("jButton4");
    this.add(jButton1, null);
    this.add(jButton2, null);
    this.add(jButton3, null);
    this.add(jButton4, null);
    gridLayout1.setRows(4);
    gridLayout1.setColumns(1);
  }
}
