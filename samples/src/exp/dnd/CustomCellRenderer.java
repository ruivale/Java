
package  exp.dnd;

import  java.awt.Component;
import  java.util.Hashtable;
import  javax.swing.*;
import  javax.swing.tree.*;


public class CustomCellRenderer
        implements ListCellRenderer, TreeCellRenderer {
    DefaultListCellRenderer listCellRenderer = new DefaultListCellRenderer();
    DefaultTreeCellRenderer treeCellRenderer = new DefaultTreeCellRenderer();


    public Component getListCellRendererComponent (JList list, Object value,
            int index, boolean selected, boolean hasFocus) {
        listCellRenderer.getListCellRendererComponent(list, value, index, selected,
                hasFocus);
        listCellRenderer.setText(getValueString(value));
        return  listCellRenderer;
    }


    public Component getTreeCellRendererComponent (JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        treeCellRenderer.getTreeCellRendererComponent(tree, value, selected,
                expanded, leaf, row, hasFocus);
        if (value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            value = node.getUserObject();
        }
        treeCellRenderer.setText(getValueString(value));
        return  treeCellRenderer;
    }


    private String getValueString (Object value) {
        String returnString = "null";
        if (value != null) {
            if (value instanceof Hashtable) {
                Hashtable h = (Hashtable)value;
                String name = (String)h.get("name");
                String url = (String)h.get("url");
                returnString = name + " ==> " + url;
            }
            else {
                returnString = "X: " + value.toString();
            }
        }
        return  returnString;
    }
}



