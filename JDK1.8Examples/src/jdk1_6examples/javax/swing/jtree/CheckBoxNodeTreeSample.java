package jdk1_6examples.javax.swing.jtree;

/*
Definitive Guide to Swing for Java 2, Second Edition
By John Zukowski
ISBN: 1-893115-78-X
Publisher: APress
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;


public class CheckBoxNodeTreeSample {

  public static void main(String args[]) {
    JFrame frame = new JFrame("CheckBox Tree");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//    CheckBoxNode accessibilityOptions[] = {
//      new CheckBoxNode(
//      "Move system caret with focus/selection changes", false),
//      new CheckBoxNode("Always expand alt text for images", true)};
//    CheckBoxNode browsingOptions[] = {
//      new CheckBoxNode("Notify when downloads complete", true),
//      new CheckBoxNode("Disable script debugging", true),
//      new CheckBoxNode("Use AutoComplete", true),
//      new CheckBoxNode("Browse in a new process", false)};
//
//    Vector accessVector = new NamedVector("Accessibility",
//                                          accessibilityOptions);
//
//    Vector browseVector = new NamedVector("Browsing", browsingOptions);
//    Object rootNodes[] = {accessVector, browseVector};
//    Vector rootVector = new NamedVector("Root", rootNodes);
//    JTree tree = new JTree(rootVector);


    final CheckBoxNode cbnRoot = new CheckBoxNode("LUAS", true);
    CheckBoxNode cbn1 = new CheckBoxNode("RedLine", true);
    CheckBoxNode cbn2 = new CheckBoxNode("GreenLine", true);
    cbnRoot.add(cbn1);
    cbnRoot.add(cbn2);

    CheckBoxNode cbn11 = new CheckBoxNode("Stops", true);
    CheckBoxNode cbn12 = new CheckBoxNode("Trams", true);
    cbn1.add(cbn11);
    cbn1.add(cbn12);
    CheckBoxNode cbn21 = new CheckBoxNode("Stops", true);
    CheckBoxNode cbn22 = new CheckBoxNode("Trams", true);
    cbn2.add(cbn21);
    cbn2.add(cbn21);

    CheckBoxNode cbn211 = new CheckBoxNode("Station ...", true);
    cbn21.add(cbn211);
    CheckBoxNode cbn2111 = new CheckBoxNode("Zone ...", true);
    cbn211.add(cbn2111);
    CheckBoxNode cbn21111 = new CheckBoxNode("Equip 1", true, true);
    cbn2111.add(cbn21111);
    CheckBoxNode cbn21112 = new CheckBoxNode("Equip 2", true, true);
    cbn2111.add(cbn21112);
    CheckBoxNode cbn2111n = new CheckBoxNode("Equip n", true, true);
    cbn2111.add(cbn2111n);

    JTree tree = new JTree(cbnRoot);


    CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
    tree.setCellRenderer(renderer);

    tree.setCellEditor(new CheckBoxNodeEditor(tree));
    tree.setEditable(true);


    final JButton jb = new JButton("Dump selected equips ...");
    jb.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dumping all the selected equips ...");
        displaySelectedEquips(cbnRoot);
        System.out.println("... dumped!");
      }

      private void displaySelectedEquips(CheckBoxNode cbn) {
        if(cbn != null && !cbn.isLeaf()){
          final int nChilds = cbn.getChildCount();

          for (int i = 0; i < nChilds; i++) {
            displaySelectedEquips((CheckBoxNode)cbn.getChildAt(i));
          }
        }else if(cbn != null && cbn.isEquip() && cbn.isSelected()){
          System.out.println(cbn.toPrivateString());
        }
      }
    });


    JScrollPane scrollPane = new JScrollPane(tree);
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.getContentPane().add(jb, BorderLayout.SOUTH);
    frame.setSize(400, 350);
    frame.setVisible(true);
  }
}

/**
 * 
 * @author C2334
 */
class CheckBoxNodeRenderer implements TreeCellRenderer {

  private JCheckBox jCheckBox = new JCheckBox();
  private DefaultTreeCellRenderer nonLeafRenderer =
                                  new DefaultTreeCellRenderer();
  Color selectionBorderColor, selectionForeground, selectionBackground,
      textForeground, textBackground;

  protected JCheckBox getLeafRenderer() {
    return jCheckBox;
  }

  public CheckBoxNodeRenderer() {
    Font fontValue;
    fontValue = UIManager.getFont("Tree.font");

    if (fontValue != null) {
      jCheckBox.setFont(fontValue);
    }

    Boolean booleanValue = (Boolean) UIManager.get("Tree.drawsFocusBorderAroundIcon");
    jCheckBox.setFocusPainted((booleanValue != null) && (booleanValue.booleanValue()));

    jCheckBox.setPreferredSize(new Dimension(250, 18));

    selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
    selectionForeground = UIManager.getColor("Tree.selectionForeground");
    selectionBackground = UIManager.getColor("Tree.selectionBackground");
    textForeground = UIManager.getColor("Tree.textForeground");
    textBackground = UIManager.getColor("Tree.textBackground");
  }

  public Component getTreeCellRendererComponent(JTree tree,
                                                Object value,
                                                boolean selected,
                                                boolean expanded,
                                                boolean leaf,
                                                int row,
                                                boolean hasFocus) {

    Component returnValue;
//    if (leaf) {

      String stringValue = tree.convertValueToText(value, selected,
                                                   expanded, leaf, row, false);
      jCheckBox.setText(stringValue);
      jCheckBox.setSelected(false);

      jCheckBox.setEnabled(tree.isEnabled());

      if (selected) {
        jCheckBox.setForeground(selectionForeground);
        jCheckBox.setBackground(selectionBackground);
      } else {
        jCheckBox.setForeground(textForeground);
        jCheckBox.setBackground(textBackground);
      }

      if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
        Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
        if (userObject instanceof CheckBoxNode) {
          CheckBoxNode node = (CheckBoxNode) userObject;
          jCheckBox.setText(node.getText());
          jCheckBox.setSelected(node.isSelected());

          node.setSelected(jCheckBox.isSelected());
        }
      }
      returnValue = jCheckBox;
//    } else {
//      returnValue =
//      nonLeafRenderer.getTreeCellRendererComponent(tree,
//                                                   value, selected, expanded,
//                                                   leaf, row, hasFocus);
//    }

    if(!leaf){

    }

    return returnValue;
  }
}


/**
 *
 * @author C2334
 */
class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

  CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
  ChangeEvent changeEvent = null;
  JTree tree;

  public CheckBoxNodeEditor(JTree tree) {
    this.tree = tree;
  }

  public Object getCellEditorValue() {
    JCheckBox checkbox = renderer.getLeafRenderer();
    CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText(),
                                                 checkbox.isSelected());
    return checkBoxNode;
  }

  public boolean isCellEditable(EventObject event) {
    if(true) return true;

    boolean returnValue = false;
    if (event instanceof MouseEvent) {
      MouseEvent mouseEvent = (MouseEvent) event;
      TreePath path = tree.getPathForLocation(mouseEvent.getX(),
                                              mouseEvent.getY());
      if (path != null) {
        Object node = path.getLastPathComponent();
        if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
          DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
          Object userObject = treeNode.getUserObject();
          returnValue = ((treeNode.isLeaf()) &&
                         (userObject instanceof CheckBoxNode));
        }
      }
    }
    return returnValue;
  }

  public Component getTreeCellEditorComponent(JTree tree,
                                              Object value,
                                              boolean selected,
                                              boolean expanded,
                                              boolean leaf,
                                              int row) {

    Component editor = renderer.getTreeCellRendererComponent(tree, value,
                                                             true, expanded,
                                                             leaf, row, true);

    // editor always selected / focused
    ItemListener itemListener = new ItemListener() {

      public void itemStateChanged(ItemEvent itemEvent) {
        if (stopCellEditing()) {
          fireEditingStopped();
        }
      }
    };
    if (editor instanceof JCheckBox) {
      ((JCheckBox) editor).addItemListener(itemListener);
    }

    return editor;
  }
}

/**
 *
 * @author C2334
 */
class CheckBoxNode extends DefaultMutableTreeNode{

  String text;
  boolean selected;
  boolean isEquip = false;

 public CheckBoxNode(String text,
                      boolean selected){
   this(text, selected, false);
 }

  public CheckBoxNode(String text,
                      boolean selected,
                      boolean isEquip) {
    super(text);
    this.text = text;
    this.selected = selected;
    this.isEquip = isEquip;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean newValue) {
    selected = newValue;

    System.out.println("setSelected->"+this.toPrivateString());
  }

  public String getText() {
    return text;
  }

  public void setText(String newValue) {
    text = newValue;
  }

  public String toPrivateString() {
    return getClass().getName() + "[" + text + "/" + selected + "]";
  }

  boolean isEquip() {
    return this.isEquip;
  }
}

/**
 *
 * @author C2334
 **************************************************************************
class NamedVector extends Vector {

  String name;

  public NamedVector(String name) {
    this.name = name;
  }

  public NamedVector(String name,
                     Object elements[]) {
    this.name = name;
    for (int i = 0, n = elements.length; i < n; i++) {
      add(elements[i]);
    }
  }

  public String toString() {
    return "[" + name + "]";
  }
}
/**/

