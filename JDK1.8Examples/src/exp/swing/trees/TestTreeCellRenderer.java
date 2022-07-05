package exp.swing.trees;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
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
public class TestTreeCellRenderer
    extends JPanel {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new TestTreeCellRenderer object.
   */
  public TestTreeCellRenderer() {
    SimpleMutableTreeNode nodeTreeRoot = new SimpleMutableTreeNode(" R o o t ");

    final JTree jTreeVersions = new JTree(nodeTreeRoot) {
      public String getToolTipText(MouseEvent evt) {
        if (getRowForLocation(evt.getX(), evt.getY()) == -1) {
          return null;
        }
        TreePath curPath = getPathForLocation(evt.getX(), evt.getY());
        return ((SimpleMutableTreeNode) curPath.getLastPathComponent()).
            getToolTipText();
      }
    };
    /***/

    /**
         jTreeVersions.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseMoved(MouseEvent e){
        System.out.println("mouse moved ...");

      }
         });
         jTreeVersions.addMouseListener(new MouseAdapter(){
      public void mouseEntered(MouseEvent e) {
        System.out.println("mouse entered");
      }
      public void mouseExited(MouseEvent e) {
        System.out.println("mouse exited");
      }
         });
         /**/
    ToolTipManager.sharedInstance().registerComponent(jTreeVersions);

    jTreeVersions.setEditable(false);
    jTreeVersions.setCellRenderer(new VersionTreeCellRenderer());

    final SimpleMutableTreeNode treeNodePint0 = new SimpleMutableTreeNode(" P I n t   0 ");
    nodeTreeRoot.add(treeNodePint0);

    final SimpleMutableTreeNode treeNodePint1 = new SimpleMutableTreeNode(" P   I   n   t       1 ");
    nodeTreeRoot.add(treeNodePint1);

    final SimpleMutableTreeNode treeNodePint2 = new SimpleMutableTreeNode("   P   I   n   t    2   ");
    nodeTreeRoot.add(treeNodePint2);

    final JScrollPane jScrollPane = new JScrollPane();
    jScrollPane.getViewport()
        .add(
            jTreeVersions,
            null);
    jScrollPane.setPreferredSize(new Dimension(
        100,
        400));

    setLayout(new java.awt.BorderLayout());
    add(
        jScrollPane,
        java.awt.BorderLayout.CENTER);

    jTreeVersions.treeDidChange();

    new OverlayListener(jTreeVersions);

    /***/
    new Thread(
        new Runnable() {
      public void run() {
        try {
          Thread.sleep(6000);

          while (true) {
            SwingUtilities.invokeLater(
                new Runnable() {
              public void run() {
                treeNodePint2.setToolTipText( //"AAAA\nBBB\nCC");
                    "<html><body><i>Equips in alarm</i><br>" +
                    System.currentTimeMillis() +
                    "<br><u>end</u></body></html>");

              }
            });
            Thread.sleep(753);
            System.out.println("sleeping again ...");
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();
    /***/

    /***
         try {
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception ex) {
      ex.printStackTrace();
         }
         /***/

  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    TestTreeCellRenderer t = new TestTreeCellRenderer();
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane()
        .setLayout(new java.awt.BorderLayout());
    f.getContentPane()
        .add(
            t,
            java.awt.BorderLayout.CENTER);
    f.getContentPane()
        .add(
            new JButton("AJAJAJAJAJ"),
            java.awt.BorderLayout.EAST);

    f.pack();
    f.setVisible(true);
  }

  //~ Inner Classes ////////////////////////////////////////////////////////////

  public class OverlayListener
      extends MouseInputAdapter implements TreeSelectionListener {
    Component oldGlassPane;
    JTree tree;
    Rectangle bounds;
    TreePath path;
    int row;
    JComponent c = new JComponent() {
      public void paint(Graphics g) {
        boolean selected = tree.isRowSelected(row);
        Component renderer = tree.getCellRenderer()
                             .getTreeCellRendererComponent(
                                 tree,
                                 path.getLastPathComponent(),
                                 tree.isRowSelected(row),
                                 tree.isExpanded(row),
                                 tree.getModel().isLeaf(path.
            getLastPathComponent()),
                                 row,
                                 selected);
        c.setFont(tree.getFont());

        Rectangle paintBounds = SwingUtilities.convertRectangle(
            tree,
            bounds,
            this);
        SwingUtilities.paintComponent(
            g,
            renderer,
            this,
            paintBounds);

        if (selected) {
          return;
        }

        g.setColor(Color.blue);
        ((Graphics2D) g).draw(paintBounds);
      }
    };

    public OverlayListener(JTree tree) {
      this.tree = tree;
      tree.addMouseListener(this);
      tree.addMouseMotionListener(this);
    }

    public void mouseExited(MouseEvent e) {
      resetGlassPane();
    }

    public void mouseMoved(MouseEvent me) {
      path = tree.getPathForLocation(
          me.getX(),
          me.getY());

      if (path == null) {
        resetGlassPane();

        return;
      }

      row = tree.getRowForPath(path);
      bounds = tree.getPathBounds(path);

      if (!tree.getVisibleRect()
          .contains(bounds)) {
        if (oldGlassPane == null) {
          oldGlassPane = tree.getRootPane()
                         .getGlassPane();
          c.setOpaque(false);
          tree.getRootPane()
              .setGlassPane(c);
          c.setVisible(true);
          tree.addTreeSelectionListener(this);
        } else {
          tree.getRootPane()
              .repaint();
        }
      } else {
        resetGlassPane();
      }
    }

    public void valueChanged(TreeSelectionEvent e) {
      tree.getRootPane()
          .repaint(); //repaints our glass-pane
    }

    private void resetGlassPane() {
      if (oldGlassPane != null) {
        c.setVisible(false);
        tree.getRootPane()
            .setGlassPane(oldGlassPane);
        oldGlassPane = null;
        tree.removeTreeSelectionListener(this);
      }
    }
  }


  /**
   *
   *
   */
  static class VersionTreeCellRenderer
      extends DefaultTreeCellRenderer {
    final ImageIcon ICON_POINT = new ImageIcon("d:/temp/point.png");

    /**
     * Creates a new VersionTreeCellRenderer object.
     */
    public VersionTreeCellRenderer() {
      System.out.println("new VersionTreeCellRenderer");
    }

    /**
     * DOCUMENT ME!
     *
     * @param tree
     * @param value
     * @param sel
     * @param expanded
     * @param leaf
     * @param row
     * @param hasFocus
     *
     * @return
     */
    public Component getTreeCellRendererComponent(
        JTree tree,
        Object value,
        boolean sel,
        boolean expanded,
        boolean leaf,
        int row,
        boolean hasFocus) {
      //System.out.println("TREENODE");
      try {
        /*
           if(value instanceof StationMutableTreeNode) {
             StationMutableTreeNode node = (StationMutableTreeNode)value;
             super.getTreeCellRendererComponent(
               tree,
               value,
               sel,
               expanded,
               leaf,
               row,
               hasFocus);
             if(node.isLeaf()) {
               setIcon(ICON_POINT);
             } else {
               setIcon(null);
             }
             return this;
           } else */
        if (value instanceof SimpleMutableTreeNode) {
          SimpleMutableTreeNode node = (SimpleMutableTreeNode) value;
          super.getTreeCellRendererComponent(
              tree,
              value,
              sel,
              expanded,
              leaf,
              row,
              hasFocus);

          //System.out.println("TREENODE: " + value + ", isLeaf=" + node.isLeaf() +". simple");
          if (node.isLeaf()) {
            setIcon(ICON_POINT);
          } else {
            setIcon(null);
          }

          return this;
        } else {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
          super.getTreeCellRendererComponent(
              tree,
              value,
              sel,
              expanded,
              leaf,
              row,
              hasFocus);

          //System.out.println("TREENODE: " + value + ", isLeaf=" + node.isLeaf() +". default");
          if (node.isLeaf()) {
            setIcon(ICON_POINT);
          } else {
            setIcon(null);
          }

          return this;
        }
      } catch (Exception e) {
        System.out.println(
            "STVVersions.java - Cannot return a valid value from getTreeCellRendererComponent()!");
        e.printStackTrace();

        return null;
      }
    }
  }
}
