package jdk1_6examples.javax.swing.jtree;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;



/**
 * 
 * @author 
 */
public class CheckNodeTreeExample extends JFrame {

  
  /**
   * 
   */
  public CheckNodeTreeExample() {
    super("CheckNode TreeExample");

    final CheckNode cbnRoot = new CheckNode("LUAS");
    final CheckNode cbn1 = new CheckNode("RedLine");
    final CheckNode cbn2 = new CheckNode("GreenLine");
    cbnRoot.add(cbn1);
    cbnRoot.add(cbn2);

    final CheckNode cbn11 = new CheckNode("Stops");
    final CheckNode cbn12 = new CheckNode("Trams");
    cbn1.add(cbn11);
    cbn1.add(cbn12);
    final CheckNode cbn21 = new CheckNode("Stops");
    final CheckNode cbn22 = new CheckNode("Trams");
    cbn2.add(cbn21);
    cbn2.add(cbn22);

    final CheckNode cbn211 = new CheckNode("Windy Arbour");
    cbn21.add(cbn211);
    final CheckNode cbn2111 = new CheckNode("Zone");
    cbn211.add(cbn2111);
    final CheckNode cbn21111 = new CheckNode("Platform IB", false, false, true);
    cbn2111.add(cbn21111);
    final CheckNode cbn21112 = new CheckNode("Platform OB", false, false, true);
    cbn2111.add(cbn21112);
    final CheckNode cbn2111n = new CheckNode("Equip n", false, false, true);
    cbn2111.add(cbn2111n);

    final JTree jTree = new JTree(cbnRoot);
    jTree.setShowsRootHandles(true);

    
    jTree.setCellRenderer(new CheckRenderer());
    jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    jTree.putClientProperty("JTree.lineStyle", "Angled");
    jTree.addMouseListener(new NodeSelectionListener(jTree));
    final JScrollPane jScrollPane = new JScrollPane(jTree);


    
    final JButton jb = new JButton("Dump selected equips ...");
    jb.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent actionEvt) {
        System.out.println("Dumping all the selected equips ...");
        displaySelectedEquips(cbnRoot);
        System.out.println("... dumped!");
      }

      private void displaySelectedEquips(final CheckNode checkNode) {
        System.out.println(checkNode.toPrivateString());
          final int nChilds = checkNode.getChildCount();

          for (int i = 0; i < nChilds; i++) {
            displaySelectedEquips((CheckNode)checkNode.getChildAt(i));
          }


//        if(cbn != null && !cbn.isLeaf()){
//          final int nChilds = cbn.getChildCount();
//
//          for (int i = 0; i < nChilds; i++) {
//            displaySelectedEquips((CheckNode)cbn.getChildAt(i));
//          }
//        }else if(cbn != null && cbn.isEquip() && cbn.isSelected()){
//          System.out.println(cbn.toPrivateString());
//        }
      }
    });

    getContentPane().add(jScrollPane, BorderLayout.CENTER);
    getContentPane().add(jb, BorderLayout.SOUTH);
  }


  /**
   * 
   */
  private class NodeSelectionListener extends MouseAdapter {

    private JTree jTree;

    /**
     * 
     * @param jTree 
     */
    NodeSelectionListener(final JTree jTree) {
      this.jTree = jTree;
    }

    /**
     * 
     * @param mouseEvt 
     */
    @Override
    public void mouseClicked(final MouseEvent mouseEvt) {
      final int x = mouseEvt.getX();
      final int y = mouseEvt.getY();
      final int row = this.jTree.getRowForLocation(x, y);
      final TreePath path = this.jTree.getPathForRow(row);

      if (path != null) {
        final CheckNode checkNode = (CheckNode) path.getLastPathComponent();
        final boolean isSelected = !(checkNode.isSelected());
        checkNode.setSelected(isSelected);

        if (isSelected) {
          this.jTree.expandPath(path);
        } else {
          this.jTree.collapsePath(path);
        }

        ((DefaultTreeModel) this.jTree.getModel()).nodeChanged(checkNode);
      }
      
      this.jTree.revalidate();
      this.jTree.repaint();
    }
  }

  
  
  
  public static void main(String args[]) {
    CheckNodeTreeExample frame = new CheckNodeTreeExample();
    frame.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(500, 350);
    frame.setVisible(true);
  }
}

/**
 * 
 * @author 
 */
class CheckRenderer extends JPanel implements TreeCellRenderer {

  private static Color selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
  private static Color selectionForeground = UIManager.getColor("Tree.selectionForeground");
  private static Color selectionBackground = UIManager.getColor("Tree.selectionBackground");
  private static Color textForeground = UIManager.getColor("Tree.textForeground");
  private static Color textBackground = UIManager.getColor("Tree.textBackground");
  private static Color not100PerCentSelected = Color.GRAY;    
  
  private JCheckBox check;
  private TreeLabel label;


  /**
   * 
   */
  CheckRenderer() {
    this.setLayout(null);
    this.check = new JCheckBox();
    this.label = new TreeLabel();
    this.add(this.check);
    this.add(this.label);
    this.check.setBackground(CheckRenderer.textBackground);
    this.label.setForeground(CheckRenderer.textBackground);

    this.check.setPreferredSize(new Dimension(20, 18));

  }

  
  /**
   * 
   * @param jTree
   * @param objValue
   * @param isSelected
   * @param isExpanded
   * @param isLeaf
   * @param iRow
   * @param hasFocus
   * @return 
   */
  @Override
  public Component getTreeCellRendererComponent(final JTree jTree,
                                                final Object objValue,
                                                final boolean isSelected,
                                                final boolean isExpanded,
                                                final boolean isLeaf,
                                                final int iRow,
                                                final boolean hasFocus) {

    final CheckNode checkNode = (CheckNode) objValue;

    final String stringValue = 
      jTree.convertValueToText(objValue, isSelected, isExpanded, isLeaf, iRow, hasFocus);
    
    this.setEnabled(jTree.isEnabled());
    this.check.setSelected(checkNode.isSelected());
    this.label.setText(stringValue);
    this.label.setSelected(isSelected);
    this.label.setFocus(hasFocus);

    if (isSelected) {
      this.check.setForeground(CheckRenderer.selectionForeground);
      this.check.setBackground(CheckRenderer.selectionBackground);
      this.label.setForeground(CheckRenderer.selectionForeground);
      this.label.setBackground(CheckRenderer.selectionBackground);
      
    } else {
      this.check.setForeground(CheckRenderer.textForeground);
      this.check.setBackground(CheckRenderer.textBackground);
      this.label.setForeground(CheckRenderer.textForeground);
      this.label.setBackground(CheckRenderer.textBackground);
    }

    if(!isLeaf){
      if(checkNode.is100PerCentSelected()){
        this.label.setForeground(CheckRenderer.textForeground);
      }else{
        this.label.setForeground(CheckRenderer.not100PerCentSelected);
      }
    }

    this.label.setIcon(null);

    jTree.treeDidChange();
   
    return this;
  }

  /**
   * 
   * @return 
   */
  @Override
  public Dimension getPreferredSize() {
    final Dimension dimCheckBox = check.getPreferredSize();
    final Dimension dimLabel = label.getPreferredSize();
    
    return 
      new Dimension(
        dimCheckBox.width + dimLabel.width,
        (dimCheckBox.height < dimLabel.height ? dimLabel.height: dimCheckBox.height));
  }

  /**
   * 
   */
  @Override
  public void doLayout() {
    final Dimension dimCheckBox = check.getPreferredSize();
    final Dimension dimLabel = label.getPreferredSize();
    int intYCoordCheckBox = 0;
    int intYCoordLabel = 0;
    
    if (dimCheckBox.height < dimLabel.height) {
      intYCoordCheckBox = (dimLabel.height - dimCheckBox.height) / 2;
    } else {
      intYCoordLabel = (dimCheckBox.height - dimLabel.height) / 2;
    }
    
    check.setLocation(0, intYCoordCheckBox);
    check.setBounds(0, intYCoordCheckBox, dimCheckBox.width, dimCheckBox.height);
    label.setLocation(dimCheckBox.width, intYCoordLabel);
    label.setBounds(dimCheckBox.width, intYCoordLabel, dimLabel.width, dimLabel.height);
  }

  /**
   * 
   * @param color 
   */
  @Override
  public void setBackground(Color color) {
    if (color instanceof ColorUIResource) {
      color = null;
    }
    
    super.setBackground(color);
  }


  /**
   * 
   */
  class TreeLabel extends JLabel {

    boolean isSelected;
    boolean hasFocus;

    /**
     * 
     */
    public TreeLabel() {
    }

    /**
     * 
     * @param color 
     */
    @Override
    public void setBackground(Color color) {
      if (color instanceof ColorUIResource) {
        color = null;
      }
      
      super.setBackground(color);
    }

    /**
     * 
     * @param graphics 
     */
    @Override
    public void paint(final Graphics graphics) {
      String strTxt;
      
      if ((strTxt = getText()) != null) {
        if (0 < strTxt.length()) {
          if (isSelected) {
            graphics.setColor(UIManager.getColor("Tree.selectionBackground"));
          } else {
            graphics.setColor(UIManager.getColor("Tree.textBackground"));
          }
      
          final Dimension dim = getPreferredSize();
          final Icon iconCurrent = getIcon();
          int imageOffset = 0;
          
          if (iconCurrent != null) {
            imageOffset = iconCurrent.getIconWidth() + Math.max(0, getIconTextGap() - 1);
          }
          
          graphics.fillRect(imageOffset, 0, dim.width - 1 - imageOffset, dim.height);
          
          if (hasFocus) {
            graphics.setColor(UIManager.getColor("Tree.selectionBorderColor"));
            graphics.drawRect(imageOffset, 0, dim.width - 1 - imageOffset, dim.height - 1);
          }
        }
      }
      
      super.paint(graphics);
    }

    /**
     * 
     * @return 
     */
    @Override
    public Dimension getPreferredSize() {
      Dimension dimPrefSize = super.getPreferredSize();
      
      if (dimPrefSize != null) {
        dimPrefSize = new Dimension(dimPrefSize.width + 3,
                                     dimPrefSize.height);
      }
      return dimPrefSize;
    }

    /**
     * 
     * @param isSelected 
     */
    public void setSelected(final boolean isSelected) {
      this.isSelected = isSelected;
    }

    /**
     * 
     * @param hasFocus 
     */
    public void setFocus(final boolean hasFocus) {
      this.hasFocus = hasFocus;
    }
  }

}

/**
 * 
 * @author 
 */
class CheckNode extends DefaultMutableTreeNode {

  private static volatile boolean isSelectionChangesActive = false;

  protected boolean isSelected;
  boolean isEquip = false;
  boolean is100PerCentSelected = true;

  /**
   * 
   * @param userObject 
   */
  public CheckNode(final Object userObject) {
    this(userObject, true, false, false);
  }

  /**
   * 
   * @param userObject
   * @param allowsChildren
   * @param isSelected
   * @param isEquip 
   */
  public CheckNode(final Object userObject,
                   final boolean allowsChildren,
                   final boolean isSelected,
                   final boolean isEquip) {
    super(userObject, allowsChildren);
    
    this.isSelected = isSelected;
    this.isEquip = isEquip;
  }

  /**
   * 
   * @param is100PerCentSelected 
   */
  protected void set100PerCentSelected(final boolean is100PerCentSelected){
    this.is100PerCentSelected = is100PerCentSelected;
  }

  /**
   * 
   * @return 
   */
  protected boolean is100PerCentSelected(){
    return !this.isLeaf()? this.is100PerCentSelected: this.isSelected();
  }

  /**
   * 
   * @param isSelected 
   */
  public void setSelected(final boolean isSelected) {
    this.isSelected = isSelected;

    if (!CheckNode.isSelectionChangesActive && children != null) {
      final Enumeration<?> e = children.elements();
      
      while (e.hasMoreElements()) {
        final CheckNode checkNode = (CheckNode) e.nextElement();
        checkNode.setSelected(isSelected);
      }
    }

    if(!CheckNode.isSelectionChangesActive && !isSelected){
      // If a node is deselected, its parent, and its parent parent, and so on ...
      // must be deselected cause there's no 100% child nodes selected ...
      CheckNode.isSelectionChangesActive = true;
      processNodeParentDeselection(getParent());
      CheckNode.isSelectionChangesActive = false;

    }else if(!CheckNode.isSelectionChangesActive && isSelected){
      // if a node becomes selected, then checks must be done to ensure its
      // parent is also selected if the selected node brothers are all selected.
      // and its parent parent must also be selected if all its childs are 100%
      // selected ...
      CheckNode.isSelectionChangesActive = true;
      processNodeParentSelection(getParent());
      CheckNode.isSelectionChangesActive = false;
    }
  }

  /**
   * 
   * @return 
   */
  public boolean isSelected() {
    return isSelected;
  }
  
  /**
   * 
   * @return 
   */
  public String toPrivateString() {
    return getClass().getName() + "[" + getUserObject() + "/" + isSelected + "]";
  }

  /**
   * 
   * @return 
   */
  boolean isEquip() {
    return this.isEquip;
  }

  /**
   * 
   * @param node 
   */
  private void processNodeParentDeselection(final TreeNode node) {
    try {
      if(node != null){
        this.processNodeParentDeselection(node.getParent());
        ((CheckNode)node).set100PerCentSelected(false);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 
   * @param node 
   */
  private void processNodeParentSelection(final TreeNode node) {
    try {
      if (node != null) {
        boolean areAllChildsSelected = true;
        final Enumeration<?> e = node.children();
        
        while (e.hasMoreElements()) {
          final CheckNode nodeChild = (CheckNode) e.nextElement();

          if(!nodeChild.is100PerCentSelected()){
          //if(!nodeChild.isSelected()){
            // if @ least one child is not selected, there's no need to continue
            areAllChildsSelected = false;
            break;
          }
        }
        ((CheckNode)node).set100PerCentSelected(areAllChildsSelected);
        ((CheckNode)node).setSelected(true);
        
        this.processNodeParentSelection(node.getParent());
      }
    } catch (Exception ex) {
      // Proper logging...
    }
  }

  // If you want to change "isSelected" by CellEditor,
  /*
  public void setUserObject(Object obj) { if (obj instanceof Boolean) {
   * setSelected(((Boolean)obj).booleanValue()); } else {
   * super.setUserObject(obj); } }
   */
}

