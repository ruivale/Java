package exp.swing.trees;


import java.awt.Component;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ExpandCollapseAllTree extends JPanel {
  static JTree jTreeVersions;
  DefaultMutableTreeNode nodeTreeRoot =
    new DefaultMutableTreeNode("Root");


  public ExpandCollapseAllTree() {
    jTreeVersions = new JTree(nodeTreeRoot);
    jTreeVersions.setEditable(false);

    SimpleMutableTreeNode treeNodePint0 = new SimpleMutableTreeNode("PInt 0");
    nodeTreeRoot.add(treeNodePint0);
    SimpleMutableTreeNode treeNodePint00 = new SimpleMutableTreeNode("PInt 0 - 0");
    treeNodePint0.add(treeNodePint00);
    SimpleMutableTreeNode treeNodePint01 = new SimpleMutableTreeNode("PInt 0 - 1");
    treeNodePint0.add(treeNodePint01);

    SimpleMutableTreeNode treeNodePint1 = new SimpleMutableTreeNode("PInt 1");
    nodeTreeRoot.add(treeNodePint1);
    SimpleMutableTreeNode treeNodePint10 = new SimpleMutableTreeNode("PInt 1 - 0");
    treeNodePint1.add(treeNodePint10);
    SimpleMutableTreeNode treeNodePint11 = new SimpleMutableTreeNode("PInt 1 - 1");
    treeNodePint1.add(treeNodePint11);

    SimpleMutableTreeNode treeNodePint2 = new SimpleMutableTreeNode("PInt 2");
    nodeTreeRoot.add(treeNodePint2);

    JScrollPane jScrollPane = new JScrollPane();
    jScrollPane.getViewport().add(jTreeVersions, null);

    setLayout(new java.awt.BorderLayout());
    add(jScrollPane, java.awt.BorderLayout.CENTER);

    JCheckBox jCheckBox = new JCheckBox("allalal");
    jCheckBox.addActionListener(new ActionListener() {
      boolean isCollapsed = true;
      public void actionPerformed(final ActionEvent e) {
        isCollapsed = !isCollapsed;
        changeTreeDisplay(isCollapsed);
      }
    });



    add(jCheckBox, java.awt.BorderLayout.SOUTH);


    jTreeVersions.treeDidChange();


  }



  /**
   *
   * @param isCollapsing boolean
   */
  private void changeTreeDisplay(final boolean isCollapsing) {
    if (isCollapsing) {
      collapseTree(this.nodeTreeRoot);
    } else {
      expandsTree(this.nodeTreeRoot);
    }
  }

  /**
   *
   * @param root DefaultMutableTreeNode
   */
  public void collapseTree(final DefaultMutableTreeNode root) {
    java.util.Enumeration e;
    DefaultMutableTreeNode key;
    for (e = root.children(); e.hasMoreElements(); ) {
      key = (DefaultMutableTreeNode) e.nextElement();
      collapseTree(key);
    }

    final TreePath tp = new TreePath(root.getPath());

    System.out.println(" Collapse: " + tp.toString());

    jTreeVersions.collapsePath(tp);
  }

  /**
   *
   * @param root DefaultMutableTreeNode
   */
  public void expandsTree(final DefaultMutableTreeNode root) {
    java.util.Enumeration e;
    DefaultMutableTreeNode key;
    for (e = root.children(); e.hasMoreElements(); ) {
      key = (DefaultMutableTreeNode) e.nextElement();
      expandsTree(key);
    }

    System.out.println(" Expands: " + root);

    jTreeVersions.expandPath(new TreePath(root.getPath()));
  }

  /**
   *
   * @param nodeToCollapse DefaultMutableTreeNode
   */
  public void collapseAll(DefaultMutableTreeNode nodeToCollapse) {
    JTree subtree = new JTree(nodeToCollapse);
    TreePath tp = new TreePath( ( (DefaultMutableTreeNode) nodeToCollapse).getPath());

    //recursively visit all subtrees and collapse them
    for (int i = 0; i < subtree.getModel().getChildCount(nodeToCollapse); i++) {
      DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) subtree.
          getModel().getChild(nodeToCollapse, i);

      if (!currentNode.isLeaf()) {
        for (int j = 0; j < subtree.getModel().getChildCount(nodeToCollapse); j++) {
          collapseAll( (DefaultMutableTreeNode) subtree.getModel().getChild(
            nodeToCollapse, j));
        }
      }
    }
    jTreeVersions.collapsePath(tp);
  }

  /**
   * Collapses the function group tree.
   */
  private void collapseAll(final TreeNode treeNode) {
    for (int i = treeNode.getChildCount(); i > 0; i--) {
      jTreeVersions.collapseRow(i);
    }
  }

  /**
   * Collapses the function group tree.
   */
  private void expandAll(final TreeNode treeNode) {
    for (int i = treeNode.getChildCount(); i > 0; i--) {
      jTreeVersions.expandRow(i);
    }
  }



  public static void main(String[] args) {
    ExpandCollapseAllTree t = new ExpandCollapseAllTree();
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new java.awt.BorderLayout());
    f.getContentPane().add(t, java.awt.BorderLayout.CENTER);
    f.pack();
    f.setVisible(true);
  }

}

