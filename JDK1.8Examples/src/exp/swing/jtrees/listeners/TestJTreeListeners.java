package exp.swing.jtrees.listeners;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


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
public class TestJTreeListeners extends JPanel{
  public TestJTreeListeners() {
    setLayout(new BorderLayout());
    final JTree jt = new JTree();
    add(jt, BorderLayout.CENTER);

    jt.addTreeExpansionListener(new TreeExpansionListener(){
      public void treeCollapsed(TreeExpansionEvent event){
        //System.out.println("treeCollapsed");
      }
      public void treeExpanded(TreeExpansionEvent e){
        System.out.println("treeExpanded");
        TreePath treePath = e.getPath();
        TreeNode node = (TreeNode)treePath.getLastPathComponent();
        System.out.println("node=" + node.toString());
        jt.collapsePath(treePath);

      }
    });

    jt.addTreeSelectionListener(new TreeSelectionListener(){
      public void valueChanged(TreeSelectionEvent e) {
        //System.out.println("valueChanged");

      }
    });

    jt.addTreeWillExpandListener(new TreeWillExpandListener(){
      public void treeWillCollapse(TreeExpansionEvent event) {
        //System.out.println("treeWillCollapse");

      }

      public void treeWillExpand(TreeExpansionEvent e) {
        //System.out.println("treeWillExpand");

        /***
        TreePath treePath = e.getPath();
        TreeNode node = (TreeNode)treePath.getLastPathComponent();
        System.out.println("node=" + node.toString());
        jt.collapsePath(treePath);
        /**/
      }
    });
  }

  public static void main(String[] args) {
    TestJTreeListeners t = new TestJTreeListeners();
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new java.awt.BorderLayout());
    f.getContentPane().add(t, java.awt.BorderLayout.CENTER);
    f.setBounds(100,100,250,350);
    f.setVisible(true);
  }
}












