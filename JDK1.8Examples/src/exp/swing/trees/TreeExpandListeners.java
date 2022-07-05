package exp.swing.trees;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


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
public class TreeExpandListeners extends JPanel{
  static JTree jTreeVersions;
  DefaultMutableTreeNode nodeTreeRoot =
    new DefaultMutableTreeNode("Root");


  public TreeExpandListeners() {
    jTreeVersions = new JTree(nodeTreeRoot);
    //jTreeVersions.setEditable(false);

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

    new MyTreeExpansionListener(this.jTreeVersions);
    this.jTreeVersions.addMouseListener(new MyTreeMouseAdapter(this.jTreeVersions));

    this.jTreeVersions.getSelectionModel().setSelectionMode(TreeSelectionModel.
        SINGLE_TREE_SELECTION);
    this.jTreeVersions.setShowsRootHandles(true);
    //this.jTreeVersions.setCellRenderer(new NavTreeRenderer());
    this.jTreeVersions.setEditable(false);

  }

  public static void main(String[] args) {
    TreeExpandListeners t = new TreeExpandListeners();
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new java.awt.BorderLayout());
    f.getContentPane().add(t, java.awt.BorderLayout.CENTER);
    f.pack();
    f.setVisible(true);
  }
}


class MyTreeExpansionListener
    implements TreeExpansionListener {

  public TreePath lastSelectedTreeNodePath = null;
  private JTree navTree = null;

  /**
   * Creates a new NavTreeExpansionListener object.
   *
   * @param navTree
   * @param treeMouseListener
   */
  public MyTreeExpansionListener(final JTree navTree) {
    this.navTree = navTree;
    this.navTree.addTreeExpansionListener(this);
  }

  /**
   *
   *
   * @param event
   */
  public void treeCollapsed(final TreeExpansionEvent event) {
System.out.println("\nCOLLAPSED COLLAPSED COLLAPSED COLLAPSED COLLAPSED COLLAPSED COLLAPSED COLLAPSED ");
System.out.println("the selected node is: "+navTree.getSelectionPath().toString()+".\n");



    try {

      this.lastSelectedTreeNodePath = new TreePath(
        MyTreeMouseAdapter.lastSelectedMutableTreeNode.getPath());

      if (this.navTree.isVisible(lastSelectedTreeNodePath)) {
        if(javax.swing.SwingUtilities.isEventDispatchThread()){
          navTree.setSelectionPath(lastSelectedTreeNodePath);
        }else{
          javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run() {
              navTree.setSelectionPath(lastSelectedTreeNodePath);
            }
          });
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   *
   *
   * @param event
   */
  public void treeExpanded(final TreeExpansionEvent event) {
System.out.println("\nEXPANDED EXPANDED EXPANDED EXPANDED EXPANDED EXPANDED EXPANDED EXPANDED EXPANDED ");
System.out.println("the selected node is: "+navTree.getSelectionPath().toString()+".\n");



    try {

      this.lastSelectedTreeNodePath = new TreePath(
        MyTreeMouseAdapter.lastSelectedMutableTreeNode.getPath());

      if (this.navTree.isVisible(lastSelectedTreeNodePath)) {
        if(javax.swing.SwingUtilities.isEventDispatchThread()){
          navTree.setSelectionPath(lastSelectedTreeNodePath);
        }else{
          javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run() {
              navTree.setSelectionPath(lastSelectedTreeNodePath);
            }
          });
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}


class MyTreeMouseAdapter extends java.awt.event.MouseAdapter{
  JTree navTree;
  public static DefaultMutableTreeNode lastSelectedMutableTreeNode;
  MyTreeMouseAdapter(JTree navTree){
    this.navTree = navTree;
  }

  public void mouseClicked(final MouseEvent e){
    System.out.println("MOUSE CLICKED  MOUSE CLICKED  MOUSE CLICKED  MOUSE CLICKED  MOUSE CLICKED  ");

    TreePath selPath = navTree.getPathForLocation(e.getX(), e.getY());
    if (selPath != null) {

System.out.println("selPath != null");

      try {
        Object o = selPath.getLastPathComponent();
        if (o != null) {

System.out.println(" o != null");

          DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) o;
          if (dmtn != null) {

System.out.println("dmtn != null");

            lastSelectedMutableTreeNode = dmtn;


          }
        }
      } catch (Exception exc) {

      }
    }
  }
}








