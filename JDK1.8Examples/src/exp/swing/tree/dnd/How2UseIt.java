package exp.swing.tree.dnd;


import java.awt.datatransfer.*;
import java.awt.dnd.*;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;


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
public class How2UseIt {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new How2UseIt object.
   */
  public How2UseIt() {
    DefaultMutableTreeNode rootNode1 = new DefaultMutableTreeNode("ROOT");

    DefaultMutableTreeNode treeNode11 = new DefaultMutableTreeNode("node 1");
    treeNode11.add(new DefaultMutableTreeNode("1 1 1"));
    treeNode11.add(new DefaultMutableTreeNode("2 2 2"));
    treeNode11.add(new DefaultMutableTreeNode("3 3 3"));

    DefaultMutableTreeNode treeNode21 = new DefaultMutableTreeNode("node 2");
    treeNode21.add(new DefaultMutableTreeNode("1 1 1"));
    treeNode21.add(new DefaultMutableTreeNode("2 2 2"));
    treeNode21.add(new DefaultMutableTreeNode("3 3 3"));

    DefaultMutableTreeNode treeNode31 = new DefaultMutableTreeNode("node 3");
    treeNode31.add(new DefaultMutableTreeNode("1 1 1"));
    treeNode31.add(new DefaultMutableTreeNode("2 2 2"));
    treeNode31.add(new DefaultMutableTreeNode("3 3 3"));

    rootNode1.add(treeNode11);
    rootNode1.add(treeNode21);
    rootNode1.add(treeNode31);

    DefaultTreeModel model1 = new DefaultTreeModel(rootNode1);
    JTree tree1 = new JTree(model1);
    JPanel p1 = new JPanel(new BorderLayout());
    p1.add(tree1, BorderLayout.CENTER);

    DefaultMutableTreeNode rootNode2 = new DefaultMutableTreeNode("ROOT");

    DefaultMutableTreeNode treeNode12 = new DefaultMutableTreeNode("node 1");
    treeNode12.add(new DefaultMutableTreeNode("1 1 1"));
    treeNode12.add(new DefaultMutableTreeNode("2 2 2"));
    treeNode12.add(new DefaultMutableTreeNode("3 3 3"));

    DefaultMutableTreeNode treeNode22 = new DefaultMutableTreeNode("node 2");
    treeNode22.add(new DefaultMutableTreeNode("1 1 1"));
    treeNode22.add(new DefaultMutableTreeNode("2 2 2"));
    treeNode22.add(new DefaultMutableTreeNode("3 3 3"));

    DefaultMutableTreeNode treeNode32 = new DefaultMutableTreeNode("node 3");
    treeNode32.add(new DefaultMutableTreeNode("1 1 1"));
    treeNode32.add(new DefaultMutableTreeNode("2 2 2"));
    treeNode32.add(new DefaultMutableTreeNode("3 3 3"));

    rootNode1.add(treeNode12);
    rootNode1.add(treeNode22);
    rootNode1.add(treeNode32);

    DefaultTreeModel model2 = new DefaultTreeModel(rootNode1);
    JTree tree2 = new JTree(model1);
    JPanel p2 = new JPanel(new BorderLayout());
    p2.add(tree2, BorderLayout.CENTER);




    final DropTarget dropTarget =
        new DropTarget(tree1,
                       new TreeDropListener() {
      public void drop(DropTargetDropEvent dtde) {
        super.drop(dtde);

        if (treePath == null) {
          dtde.rejectDrop();

          return;
        }

        JTree tree =
            (JTree) dtde.getDropTargetContext()
            .getComponent();
        DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();

        try {
          String data =
              (String) dtde.getTransferable()
              .getTransferData(DataFlavor.stringFlavor);
          DefaultMutableTreeNode dragNode =
              new DefaultMutableTreeNode(data);
          DefaultMutableTreeNode dropNode =
              (DefaultMutableTreeNode) treePath.getLastPathComponent();
          DefaultMutableTreeNode dropParent =
              (DefaultMutableTreeNode) dropNode.getParent();

          if (dropParent == null) {
            dtde.rejectDrop();

            return;
          }

          int dropIndex = treeModel.getIndexOfChild(dropParent, dropNode);

          if (before == null) {
            treeModel.removeNodeFromParent(dropNode);
            treeModel.insertNodeInto(dragNode, dropParent, dropIndex);
          } else {
            if (before.equals(Boolean.TRUE)) {
              treeModel.insertNodeInto(dragNode, dropParent, dropIndex);
            } else {
              if (dropIndex < dropParent.getChildCount()) {
                treeModel.insertNodeInto(dragNode, dropParent, dropIndex + 1);
              } else {
                dropParent.add(dragNode);
                treeModel.nodesWereInserted(
                    dropParent,
                    new int[dropIndex + 1]);
              }
            }
          }

          tree.setSelectionPath(new TreePath(dragNode.getPath()));
          dtde.acceptDrop(DnDConstants.ACTION_COPY);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });



    JFrame f = new JFrame();
    f.getContentPane().setLayout(new FlowLayout());
    f.getContentPane().add(p1);
    f.getContentPane().add(p2);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(100,100,400,300);
    f.setVisible(true);

  }

  public static void main(String[] a){
    new How2UseIt();
  }
}
