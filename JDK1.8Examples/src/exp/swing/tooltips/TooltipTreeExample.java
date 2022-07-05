package exp.swing.tooltips;


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
/* (swing1.1beta3) swing#948 */
//package jp.gr.java_conf.tame.swing.examples;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;


/**
 * @version 1.0 11/09/98
 */
class ToolTipTreeNode
    extends DefaultMutableTreeNode {
  private String toolTipText;

  public ToolTipTreeNode(String str, String toolTipText) {
    super(str);
    this.toolTipText = toolTipText;
  }

  public String getToolTipText() {
    return toolTipText;
  }
}


public class TooltipTreeExample
    extends JFrame {
  public TooltipTreeExample() {
    super("ToolTipTreeExample");
    String[][] strs = { {"swing", "boat"}, {"platf", "paltform"}, {"basic", // 0,  1
                      "fortran"}, {"metal", "heavy"}, {"JTree", "three"} // 2,  3
    }; // 4

    ToolTipTreeNode[] nodes = new ToolTipTreeNode[strs.length];
    for (int i = 0; i < strs.length; i++) {
      nodes[i] = new ToolTipTreeNode(strs[i][0], strs[i][1]);
    }
    nodes[0].add(nodes[1]);
    nodes[1].add(nodes[2]);
    nodes[1].add(nodes[3]);
    nodes[0].add(nodes[4]);
    JTree tree = new JTree(nodes[0]) {
      public String getToolTipText(MouseEvent evt) {
        if (getRowForLocation(evt.getX(), evt.getY()) == -1) {
          return null;
        }
        TreePath curPath = getPathForLocation(evt.getX(), evt.getY());
        return ((ToolTipTreeNode) curPath.getLastPathComponent()).
            getToolTipText();
      }
    };
    tree.setToolTipText("");
    JScrollPane sp = new JScrollPane(tree);
    getContentPane().add(sp, BorderLayout.CENTER);
  }

  public static void main(String args[]) {
    TooltipTreeExample frame = new TooltipTreeExample();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(300, 150);
    frame.setVisible(true);
  }
}
