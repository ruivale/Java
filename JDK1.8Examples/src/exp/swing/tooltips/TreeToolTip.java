package exp.swing.tooltips;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TreeToolTip
    extends JFrame {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private JScrollPane scrollPane;

  /** .. */
  private JTree tree;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new TreeToolTip object.
   */
  public TreeToolTip() {
    try {
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      scrollPane = new JScrollPane();
      getContentPane()
        .add(
        scrollPane,
        BorderLayout.CENTER);
      tree = new JTree() {
            public String getToolTipText(MouseEvent e) {
              Object   tip  = null;
              TreePath path = getPathForLocation(
                  e.getX(),
                  e.getY());

              if(path!=null) {
                tip = path.getLastPathComponent();
              }

              return (tip==null)
              ? null
              : tip.toString();
            }
          };
      ToolTipManager.sharedInstance()
                    .registerComponent(tree);
      scrollPane.setViewportView(tree);

      pack();
      setSize(
        300,
        200);
      setLocationRelativeTo(null);
      setVisible(true);
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
    new TreeToolTip();
  }
}
