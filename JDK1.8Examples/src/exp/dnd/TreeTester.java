
package exp.dnd;
/*
 * put your module comment here
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.Hashtable;


public class TreeTester {


  public static void main (String args[]) {
    JFrame f = new JFrame("Tree Dragging Tester");
    CustomCellRenderer renderer = new CustomCellRenderer();
    DraggableTree tree = new DraggableTree();
    tree.setModel(getDefaultTreeModel());
    tree.setCellRenderer(renderer);
    JScrollPane leftPane = new JScrollPane(tree);
    DroppableList list = new DroppableList();
    list.setCellRenderer(renderer);
    JScrollPane rightPane = new JScrollPane(list);
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                          leftPane, rightPane);
    f.getContentPane().add(splitPane, BorderLayout.CENTER);
    f.setSize(400, 300);
    f.addWindowListener(
      new WindowAdapter() {
        public void windowClosing (WindowEvent e) {
          System.exit(0);
        }
      }
    );
    f.setVisible(true);
  }


  private static TreeModel getDefaultTreeModel () {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Drag Me");
    DefaultMutableTreeNode parent;
    parent = new DefaultMutableTreeNode("Auctions");
    root.add(parent);
    parent.add(new DefaultMutableTreeNode(makeNode("eBay", "http://www.ebay.com")));
    parent.add(new DefaultMutableTreeNode(makeNode("EggHead", "http://www.egghead.com")));
    parent.add(new DefaultMutableTreeNode(makeNode("First Auction", "http://www.firstauction.com")));
    parent.add(new DefaultMutableTreeNode(makeNode("uBid", "http://www.ubid.com")));
    parent = new DefaultMutableTreeNode("Search Engines");
    root.add(parent);
    parent.add(new DefaultMutableTreeNode(makeNode("HotBot", "http://www.hotbot.com")));
    parent.add(new DefaultMutableTreeNode(makeNode("Infoseek", "http://www.infoseek.com")));
    parent.add(new DefaultMutableTreeNode(makeNode("Lycos", "http://www.lycos.com")));
    parent.add(new DefaultMutableTreeNode(makeNode("Yahoo", "http://www.yahoo.com")));
    parent = new DefaultMutableTreeNode("Java");
    root.add(parent);
    parent.add(new DefaultMutableTreeNode(makeNode("Focus on Java", "http://java.about.com")));
    parent.add(new DefaultMutableTreeNode(makeNode("JavaWorld", "http://www.javaworld.com")));
    parent.add(new DefaultMutableTreeNode(makeNode("Sun", "http://java.sun.com")));
    return new DefaultTreeModel(root);
  }


  private static Hashtable makeNode (String name, String url) {
    Hashtable hashtable = new Hashtable();
    hashtable.put("name", name);
    hashtable.put("url", url);
    return hashtable;
  }
}



