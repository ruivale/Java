/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exp.swing.jtrees.myowntreecellrenderer;

import exp.swing.trees.SimpleMutableTreeNode;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;



/**
 *
 * @author c2334
 */
public class NewSelectedTreeCellRenderer extends JPanel{

  public NewSelectedTreeCellRenderer() {
    SimpleMutableTreeNode nodeTreeRoot = new SimpleMutableTreeNode(" R o o t ");

    final JTree jTreeVersions = new JTree(nodeTreeRoot);

    jTreeVersions.setEditable(false);
    jTreeVersions.setCellRenderer(new MyOwnTreeCellRenderer(jTreeVersions));

    final SimpleMutableTreeNode treeNodePint0 = new SimpleMutableTreeNode(" PInt");
    final SimpleMutableTreeNode treeNodePint01 = new SimpleMutableTreeNode(" PInt 1");
    final SimpleMutableTreeNode treeNodePint02 = new SimpleMutableTreeNode(" PInt 2");
    final SimpleMutableTreeNode treeNodePint03 = new SimpleMutableTreeNode(" PInt 3");
    treeNodePint0.add(treeNodePint01);
    treeNodePint0.add(treeNodePint02);
    treeNodePint0.add(treeNodePint03);
    final SimpleMutableTreeNode treeNodePint031 = new SimpleMutableTreeNode(" PInt 3 1");
    final SimpleMutableTreeNode treeNodePint032 = new SimpleMutableTreeNode(" PInt 3 2");
    treeNodePint03.add(treeNodePint031);
    treeNodePint03.add(treeNodePint032);
    nodeTreeRoot.add(treeNodePint0);

    final SimpleMutableTreeNode treeNodePint1 = new SimpleMutableTreeNode(" P   I   n   t       1 ");
    final SimpleMutableTreeNode treeNodePint11 = new SimpleMutableTreeNode("Equip XPTO 1");
    final SimpleMutableTreeNode treeNodePint12 = new SimpleMutableTreeNode("Equip XPTO 2 2");
    final SimpleMutableTreeNode treeNodePint13 = new SimpleMutableTreeNode("Equip XPTO 3 33333");
    treeNodePint1.add(treeNodePint11);
    treeNodePint1.add(treeNodePint12);
    treeNodePint1.add(treeNodePint13);
    nodeTreeRoot.add(treeNodePint1);

    final SimpleMutableTreeNode treeNodePint2 = new SimpleMutableTreeNode("   P   I   n   t    2   ");
    nodeTreeRoot.add(treeNodePint2);

    /***/
    final JScrollPane jScrollPane = new JScrollPane();
    jScrollPane.getViewport()
        .add(
            jTreeVersions,
            null);
    jScrollPane.setPreferredSize(new Dimension(
        400,
        300));
    /**/

    setLayout(new java.awt.BorderLayout());
    add(
        jScrollPane,
        java.awt.BorderLayout.CENTER);


    //add(new JButton("sdsfsdfsd"),java.awt.BorderLayout.SOUTH);
  }


  public static void main(String[] args) {
    NewSelectedTreeCellRenderer t = new NewSelectedTreeCellRenderer();
    JFrame f = new JFrame("Exemplo de nós seleccionados");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane()
        .setLayout(new java.awt.BorderLayout());
    f.getContentPane()
        .add(
            t,
            java.awt.BorderLayout.CENTER);

    f.pack();
    f.setVisible(true);

  }

}
class MyOwnTreeCellRenderer extends DefaultTreeCellRenderer {
  //final JTree jTree;

  final Font fontDefault;
  final Font fontSelected;

  final Border borderDefault = new JLabel().getBorder();
  final Border borderOutside =
      BorderFactory.createLineBorder(Color.BLACK);
  final Border borderInside =
      BorderFactory.createBevelBorder(BevelBorder.RAISED);
  final Border borderSelected =
      BorderFactory.createCompoundBorder(borderOutside, borderInside);

  final DefaultTreeModel model;// = ((DefaultTreeModel) tree.getModel());


  MyOwnTreeCellRenderer(final JTree jTree){
    //this.jTree = jTree;

    model = ((DefaultTreeModel) jTree.getModel());

    if(jTree != null){
      fontDefault = jTree.getFont();
    }else{
      fontDefault = new Font("Dialog", Font.PLAIN, 12);
    }

    fontSelected =
        new Font(fontDefault.getName(), Font.BOLD, fontDefault.getSize()+1);
  }

  public Component getTreeCellRendererComponent(
      JTree tree,
      Object value,
      boolean sel,
      boolean expanded,
      boolean leaf,
      int row,
      boolean hasFocus) {

    if(value instanceof SimpleMutableTreeNode) {
      SimpleMutableTreeNode node = (SimpleMutableTreeNode) value;

      super.getTreeCellRendererComponent(
          tree,
          value,
          sel,
          expanded,
          leaf,
          row,
          hasFocus);

      if(!sel) {
        this.setFont(fontDefault);
        //this.setBorder(borderDefault);

        /***/
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int iFontWidth = fontMetrics.stringWidth(this.getText());

        final Dimension dimPre = this.getPreferredSize();
        final int iW =
            this.getIcon().getIconWidth() +
            this.getIconTextGap() +
            iFontWidth +
            this.getInsets().left +
            this.getInsets().right +
            (leaf? 20: 30);

        this.setPreferredSize(new Dimension(iW, dimPre.height));
        /**/

      } else {
        this.setFont(fontSelected);

        //this.setBorder(borderSelected);
        //this.setBorder(borderInside);

        /***/
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int iFontWidth = fontMetrics.stringWidth(this.getText());

        final Dimension dimPre = this.getPreferredSize();

        final int iW =
            this.getIcon().getIconWidth() +
            this.getIconTextGap() +
            iFontWidth +
            this.getInsets().left +
            this.getInsets().right +
            (leaf? 20: 30);

        this.setPreferredSize(new Dimension(iW, dimPre.height));
        /**/

      }

      /***/
      this.validate();
      this.validateTree();

      tree.validate();
      tree.repaint();
      tree.treeDidChange();
      /**/

      //model.nodeChanged(node);


      return this;
    }

    return this;
  }
}
