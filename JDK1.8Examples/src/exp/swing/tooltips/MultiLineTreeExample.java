package exp.swing.tooltips;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;


/**
 * @version 1.0 11/09/98
 */
public class MultiLineTreeExample
    extends JFrame {
  public MultiLineTreeExample() {
    super("Multi-Line JTree Example");

    String[] strs = {"swing", // 0
                    "package", // 1
                    "java.awt.swing\n"
                    + "com.sun.java.swing", // 2
                    "javax.swing", // 3
                    "JTree"}; // 4

    DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[strs.length];
    for (int i = 0; i < strs.length; i++) {
      nodes[i] = new DefaultMutableTreeNode(strs[i]);
    }
    nodes[0].add(nodes[1]);
    nodes[1].add(nodes[2]);
    nodes[1].add(nodes[3]);
    nodes[0].add(nodes[4]);

    JTree tree = new JTree(nodes[0]);
    tree.setCellRenderer(new MultiLineCellRenderer());
    JScrollPane sp = new JScrollPane();
    sp.getViewport().add(tree);
    getContentPane().add(sp, BorderLayout.CENTER);
  }

  public static void main(String args[]) {
    MultiLineTreeExample frame = new MultiLineTreeExample();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(300, 150);
    frame.setVisible(true);
  }
}
