/*
 *  Copyright (C) 2001 by ILOG.
 *  All rights reserved.
 *
 * This source code is an addition to the ILOG JViews Reference Manual
 * delivered with the ILOG JViews library.
 * It is supplied as an example to show you how to code with ILOG JViews.
 * Feel free to use, copy, or modify it within the framework of your
 * ILOG JViews license agreement.
 */
/*
 * $Id: IlvSDMTreeCellEditor.java,v 1.3 2001/10/03 15:36:51 gdigugli Exp $
 *
 */

package exp.ilog.tree;


import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import ilog.views.sdm.renderer.*;

/**
 * A basic <code>DefaultTreeCellEditor</code> customization which allows to edit
 * the values of the properties of the SDM objects in the {@link IlvSDMTree}.
 * <p>
 * See {@link ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer} for the styling features of the tree.
 * </p>
 * @see ilog.views.sdm.gui.tree.IlvSDMTree
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeModel
 * @see ilog.views.sdm.gui.IlvSDMTreeView
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellRenderer
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer
 */
public class IlvSDMTreeCellEditor extends DefaultTreeCellEditor
{
  /**
   * Constructs a <code>IlvSDMTreeCellEditor</code> object for a {@link IlvSDMTree} using the specified renderer and a default editor.
   * @param tree      a JTree object
   * @param renderer  a DefaultTreeCellRenderer object
   */
  public IlvSDMTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer)
  {
    super(tree,renderer);
  }

  /**
   * Returns <code>true</code> if the {@link IlvSDMTreeModel} allows to edit the current tree element.
   * Currrently it returns <code>true</code> only if the tree element is a value of a property of a SDM objects.
   */
  public boolean isCellEditable(EventObject event)
  {
    Object comp = tree.getLastSelectedPathComponent();
    if (IlvSDMTreeModel.getObjectType(comp) == IlvSDMTreeModel.TYPE_SDM_PROPERTY_VALUE_NODE) {
      String readOnlyPropertyList = IlvRendererUtil.getGraphicPropertyAsString(((IlvSDMTree)this.tree).getEngine(),
                                                                               ((IlvSDMTreeModel.TreeElement) comp).parent.parent.element,
                                                                               "readOnlyPropertyList",
                                                                               IlvSDMTree.TreeViewClasses, "");
      StringTokenizer readOnlyPropertyListToken = new StringTokenizer(readOnlyPropertyList, ",");
      HashMap readOnlyPropertySet = new HashMap(readOnlyPropertyListToken.countTokens());
      while(readOnlyPropertyListToken.hasMoreTokens()) {
        readOnlyPropertySet.put(readOnlyPropertyListToken.nextToken(),comp);
      }

      boolean isPropertyReadOnly = readOnlyPropertySet.get(((IlvSDMTreeModel.TreeElement) comp).parent.toString()) != null;
      return !isPropertyReadOnly && super.isCellEditable(event);
    }
    else
      return false;
  }
}
