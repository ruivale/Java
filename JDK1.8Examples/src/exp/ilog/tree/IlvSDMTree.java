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
 * $Id: IlvSDMTree.java,v 1.21 2001/10/03 15:36:50 gdigugli Exp $
 *
 */


package exp.ilog.tree;


import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

import ilog.views.*;
import ilog.views.event.*;
import ilog.views.sdm.*;
import ilog.views.swing.*;
import ilog.views.sdm.event.*;
import ilog.views.sdm.renderer.*;

/**
 * <code>IlvSDMTree</code> is a <code>JTree</code> component designed
 * to use the selection events of the <code>IlvManager</code>. This subclass of <code>JTree</code> is based on {@link ilog.views.sdm.gui.tree.IlvSDMTreeModel}.
 * </p>
 * The <code>IlvSDMTree</code> is supporting styling. The style support is implemented in the {@link IlvSDMTreeViewRenderer}.
 * @see ilog.views.sdm.gui.IlvSDMTreeView
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeModel
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellEditor
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellRenderer
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer
 */
public class IlvSDMTree extends JTree implements TreeModelListener, ManagerSelectionListener, TreeSelectionListener, SDMEngineStyleSheetListener
{
  private IlvSDMEngine engine;
  private boolean showTreeLines = true;
  private IlvSDMTreeViewRenderer renderer;
  private boolean propertiesEditables = true;
  static final String TreeViewClass = "treeView";
  static final String[] TreeViewClasses = { TreeViewClass };

  /**
   * Creates an <code>IlvSDMTree</code> attached to the SDM engine parameter.
   * By default the tree representation includes the properties of the SDM objects and the properties are editable.
   * @param engine The engine where the tree is plugged.
   */
  public IlvSDMTree(IlvSDMEngine engine)
  {
    this(engine,true,true);
  }

  /**
   * Creates an <code>IlvSDMTree</code> attached to the SDM engine parameter.
   * @param engine The engine where the tree is plugged.
   * @param showProperty Allows you to display or hide the properties of the SDM objects in the tree.
   * @param isEditable Allows to install an <code>IlvSDMTreeCellEditor</code>.
   */
  public IlvSDMTree(IlvSDMEngine engine, boolean showProperty, boolean isEditable)
  {
    super(new IlvSDMTreeModel(engine.getModel(), engine, showProperty));
    this.engine = engine;
    this.propertiesEditables = isEditable;
    this.engine.addSDMEngineStyleSheetListener(this);
    this.engine.getGrapher().addManagerTreeSelectionListener(this);
    getModel().addTreeModelListener(this);
    addTreeSelectionListener(this);
    initTreeViewRenderer();
    DefaultTreeCellRenderer cellRenderer = new IlvSDMTreeCellRenderer(engine);
    setCellRenderer(cellRenderer);
    setEditable(this.propertiesEditables);
    //if (this.propertiesEditables) {
    setCellEditor(new IlvSDMTreeCellEditor(this, cellRenderer));
      //}
    if (this.showTreeLines)
      putClientProperty("JTree.lineStyle", "Angled");
  }

  private void initTreeViewRenderer()
  {
    this.renderer = (IlvSDMTreeViewRenderer) IlvRendererUtil.getRenderer(this.engine,"TreeView");
    if (renderer != null) {
      this.showTreeLines = this.renderer.getShowTreeLines();
      this.propertiesEditables = this.renderer.getPropertiesEditables();
    }
  }

  /**
   *
   * Allows you to display or hide the properties of the SDM objects in the tree.
   * @param showProperty <code>true</code> if the properties are visible.
   */
  public void setPropertyVisible(boolean showProperty)
  {
    getSDMTreeModel().setPropertyVisible(showProperty);
    selectionChanged(null);
  }

  /**
   * Returns <code>true</code> if the properties are visible.
   */
  public boolean isPropertyVisible()
  {
    return getSDMTreeModel().isPropertyVisible();
  }

  /**
   * Sets the <code>IlvSDMEngine</code> used by the tree and redraws the tree.
   * If the engine is null, all the listeners are removed from the <code>IlvSDMModel</code>.
   * @param engine The SDM engine.
   */
  public void setEngine(IlvSDMEngine engine)
  {
    if (this.engine != null) {
      getModel().removeTreeModelListener(this);
      this.engine.getGrapher().removeManagerTreeSelectionListener(this);
      this.engine.removeSDMEngineStyleSheetListener(this);
    }

    this.engine = engine;

    if (this.engine != null) {
      getSDMTreeModel().setSDMModel(this.engine.getModel());
      this.engine.getGrapher().addManagerTreeSelectionListener(this);
      this.engine.addSDMEngineStyleSheetListener(this);
      getModel().addTreeModelListener(this);
      setCellRenderer(new IlvSDMTreeCellRenderer(engine));
    }
    else {
      getSDMTreeModel().setSDMModel(null);
    }
  }

  /**
   * Adds the node identified by the specified <code>TreePath</code> to the current selection.
   * If any component of the path is not viewable and <code>getExpandsSelectedPaths</code> is <code>true</code>, then the node is made viewable.
   * @param path The <code>TreePath</code> identifying a node.
   */
  public void addSelectionPath(TreePath path)
  {
    if (path == null) return;
    getSDMTreeModel().addModelSelectedTreePath(path);
    removeTreeSelectionListener(this);
    super.addSelectionPath(path);
    addTreeSelectionListener(this);
  }

  /**
   * Adds each path in the array of paths to the current selection.
   * If any component of any of the paths is not viewable and <code>getExpandsSelectedPaths</code> is <code>true</code>, then the path is made viewable.
   * @param paths An array of <code>TreePath</code> objects that specifies the nodes to add.
   */
  public void addSelectionPaths(TreePath[] paths)
  {
    if (paths == null) return;
    for (int i=0; i<paths.length; i++) {
      addSelectionPath(paths[i]);
    }
    super.addSelectionPaths(paths);
  }

  /**
   * Removes the node identified by the specified path from the current selection.
   * @param path The <code>TreePath</code> identifying a node.
   */
  public void removeSelectionPath(TreePath path)
  {
    if (path == null) return;
    getSDMTreeModel().removeModelSelectedTreePath(path);
    removeTreeSelectionListener(this);
    super.removeSelectionPath(path);
    addTreeSelectionListener(this);
  }

  /**
   * Removes the nodes identified by the specified paths from the current selection.
   * @param paths An array of <code>TreePath</code> objects that specifies the nodes to remove.
   */
  public void removeSelectionPaths(TreePath[] paths)
  {
    if (paths == null) return;
    for (int i=0; i<paths.length; i++) {
      removeSelectionPath(paths[i]);
    }
    super.removeSelectionPaths(paths);
  }

  /**
   * Returns the SDM engine in use in the tree.
   */
  public IlvSDMEngine getEngine()
  {
    return this.engine;
  }

  /**
   * Returns the <code>IlvSDMModel</code> of the <code>IlvSDMEngine</code> associated with the document view.
   */
  public IlvSDMModel getSDMModel()
  {
    return this.engine.getModel();
  }

  /**
   * Removes the tree model in use.
   */
  public IlvSDMTreeModel getSDMTreeModel()
  {
    return (IlvSDMTreeModel) getModel();
  }

  /**
   * Invoked after a node (or a set of siblings) has changed in some way.
   * The nodes have not changed locations in the tree or altered their children arrays,
   * but other attributes have changed and may affect the presentation.
   * Example: the name of a file has changed, but it is in the same location in the file system.
   * To indicate that the root has changed, <code>childIndices</code> and <code>children</code> will be <code>null</code>.
   * Use <code>e.getPath()</code> to get the parent of the changed nodes. <code>e.getChildIndices()</code> returns the indexes of the changed nodes.
   * @param e The event.
   */
  public void treeNodesChanged(TreeModelEvent e)
  {
    repaint();
  }

  /**
   * Invoked after nodes have been inserted into the tree.
   * Use <code>e.getPath()</code> to get the parent of the new nodes.
   * <code>e.getChildIndices()</code> returns the indexes of the new nodes in ascending order.
   * @param e The event.
   */
  public void treeNodesInserted(TreeModelEvent e)
  {
    repaint();
  }

  /**
   * Invoked after nodes have been removed from the tree.
   * Note that if a subtree is removed from the tree,
   * this method may only be invoked once for the root of the removed subtree,
   * not once for each individual set of siblings removed.
   * Use <code>e.getPath()</code> to get the former parent of the deleted nodes. <code>e.getChildIndices()</code> returns,
   * in ascending order, the indexes the nodes had before being deleted.
   * @param e The event.
   */
  public void treeNodesRemoved(TreeModelEvent e)
  {
    repaint();
  }

  /**
   * Invoked after the tree has drastically changed structure from a given node down.
   * If the path returned by <code>e.getPath()</code> is of length <code>1</code> and the first element does not
   * identify the current root node, then the first element should become the new root of the tree.
   * Use <code>e.getPath()</code> to get the path to the node. <code>e.getChildIndices()</code> returns <code>null</code>.
   * @param e The event.
   */
  public void treeStructureChanged(TreeModelEvent e)
  {
    repaint();
  }

  /**
   * This method is called when the selection changes in a manager.
   * @param e The event.
   */
  public void selectionChanged(ManagerSelectionChangedEvent e)
  {
    if (e != null && e.isAdjusting() && !e.isAdjustmentEnd()) return;

    Enumeration selectedObjects = this.engine.getSelectedObjects();
    Object selectedObject;
    TreePath objectPath = null;

    TreePath[] allPaths = getSelectionPaths();
    if (allPaths != null) {
      for (int i=0; i<allPaths.length; i++) {
        if (IlvSDMTreeModel.getObjectType(allPaths[i].getLastPathComponent()) == IlvSDMTreeModel.TYPE_SDM_OBJECT_NODE ||
            IlvSDMTreeModel.getObjectType(allPaths[i].getLastPathComponent()) == IlvSDMTreeModel.TYPE_SDM_GROUP_OBJECT_NODE ) {
          removeSelectionPath(allPaths[i]);
        }
      }
    }

    while (selectedObjects.hasMoreElements()) {
      selectedObject = selectedObjects.nextElement();
      objectPath = getSDMTreeModel().getTreePath(selectedObject);
      addSelectionPath(objectPath);
    }

    if (objectPath != null && e!=null && !e.isAdjusting() && isVisible()) {
      scrollPathToVisible(objectPath);
    }
  }

  /**
   * Called whenever the value of the selection changes.
   * @param e The event that characterizes the change.
   */
  public void valueChanged(TreeSelectionEvent e)
  {
    TreePath[] allPath = e.getPaths();

    for (int i=0; i<allPath.length; i++) {
      Object[] aPath = allPath[i].getPath();

      int SDMObjectType = IlvSDMTreeModel.getObjectType(allPath[i].getLastPathComponent());

      if (getSDMTreeModel().isModelSelectedTreePath(allPath[i])) {
        if (SDMObjectType == IlvSDMTreeModel.TYPE_SDM_OBJECT_NODE ||
            SDMObjectType == IlvSDMTreeModel.TYPE_SDM_GROUP_OBJECT_NODE) {
          Object changedObject = IlvSDMTreeModel.getSDMObject(allPath[i].getLastPathComponent());
          if (this.engine.getGraphic(changedObject, false) != null)
            this.engine.setSelected(changedObject, false);
        }
        removeSelectionPath(allPath[i]);
      }
      else {
        if (SDMObjectType == IlvSDMTreeModel.TYPE_SDM_OBJECT_NODE || SDMObjectType == IlvSDMTreeModel.TYPE_SDM_GROUP_OBJECT_NODE) {
          if (this.engine.getGraphic(IlvSDMTreeModel.getSDMObject(allPath[i].getLastPathComponent()), false) != null)
            this.engine.setSelected(IlvSDMTreeModel.getSDMObject(allPath[i].getLastPathComponent()), true);
        }
        addSelectionPath(allPath[i]);
        scrollPathToVisible(allPath[i]);
        expandPath(allPath[i]);
      }
    }
  }

  /**
   * Invoked after the SDM engine has created a renderer specified in the style sheet being loaded.
   * Does nothing.
   * @param event The event.
   */
  public void rendererCreated(SDMEngineRendererCreatedEvent event) {}


  /**
   * Invoked after the SDM engine has finished loading a new style sheet.
   * Updates the tree cell renderer to use the new icons of the style sheets.
   * @param event The event.
   */
  public void styleSheetLoadingDone(SDMEngineStyleSheetEvent event)
  {
    initTreeViewRenderer();
    if (this.showTreeLines) {
      putClientProperty("JTree.lineStyle", "Angled");
    }
    else {
      putClientProperty("JTree.lineStyle", "No");
    }
    setEditable(this.propertiesEditables);
    getSDMTreeModel().initTreeViewRenderer();
    getSDMTreeModel().init();
    setCellRenderer(new IlvSDMTreeCellRenderer(engine));
    selectionChanged(null);
    repaint();
  }

  /**
   * Invoked before the SDM engine starts loading a new style sheet file.
   * Does nothing.
   * @param event The event.
   */
  public void styleSheetLoadingStarted(SDMEngineStyleSheetEvent event) {}

}
