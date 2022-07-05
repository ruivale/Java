package exp.dnd.autoscrolling;

import javax.swing.*;
import java.awt.*;
import javax.swing.tree.*;


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
public class AutoScrollComp {
  public AutoScrollComp() {
    // List
    DefaultListModel listModel = new DefaultListModel();
    listModel.addElement(new String("AAA"));
    listModel.addElement(new String("BBB"));
    listModel.addElement(new String("CCC"));
    listModel.addElement(new String("DDD"));
    listModel.addElement(new String("EEE"));
    MyList l = new MyList(listModel);
    JScrollPane jspl = new JScrollPane();
    jspl.getViewport().add(l, null);

    // Tree
    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("ROOT");
    rootNode.add(new DefaultMutableTreeNode("node 1"));
    rootNode.add(new DefaultMutableTreeNode("node 2"));
    rootNode.add(new DefaultMutableTreeNode("node 3"));
    rootNode.add(new DefaultMutableTreeNode("node 4"));
    rootNode.add(new DefaultMutableTreeNode("node 5"));
    DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
    MyJTree t = new MyJTree(treeModel);
    JScrollPane jspt = new JScrollPane();
    jspt.getViewport().add(t, null);

    // Main window
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new GridLayout(1,2));
    f.getContentPane().add(jspl, BorderLayout.WEST);
    f.getContentPane().add(jspt, BorderLayout.EAST);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(400,350);
    f.setVisible(true);

  }
  public static void main(String ss[]){
    new AutoScrollComp();
  }
}
