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
 * $Id: IlvSDMTreeModel.java,v 1.16 2001/10/03 15:36:51 gdigugli Exp $
 *
 */

package exp.ilog.tree;


import java.awt.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.util.*;
import ilog.views.sdm.*;
import ilog.views.sdm.*;
import ilog.views.event.*;
import ilog.views.sdm.event.*;
import ilog.views.sdm.renderer.*;

/**
 * The <code>IlvSDMTreeModel</code> is an implementation of <code>TreeModel</code> that maps the {@link ilog.views.sdm.IlvSDMModel} in a gadget tree.
 * <p>
 * See {@link ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer} for the styling features of the tree.
 * </p>
 * @see ilog.views.sdm.gui.tree.IlvSDMTree
 * @see ilog.views.sdm.gui.IlvSDMTreeView
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellEditor
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellRenderer
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer
 */
public class IlvSDMTreeModel implements TreeModel, SDMPropertyChangeListener, SDMModelListener
{
  private static final int NODES_CHANGED = 0;
  private static final int NODES_INSERTED = 2;
  private static final int NODES_REMOVED = 1;
  private static final int STRUCTURE_CHANGED = 3;
  private static final boolean CACHE_ENABLE = true;
  private HashSet allTreeModelListeners = new HashSet();
  private TreeElement root = new TreeElement(null, rootLabel, TYPE_SDM_ROOT_NODE);
  private IlvSDMModel model;
  private IlvSDMEngine engine;
  private HashSet selectedPath;
  private TreeQueue addedObjects;
  private TreeQueue removedObjects;
  private HashMap subGraphBuffer;
  private boolean showProperty = true;
  private IlvSDMTreeViewRenderer renderer;
  private HashMap treePathCachingTable;
  private HashMap treeElementCachingTable;

  /**
   * The string displayed in the tree for the root node.
   */
  public static String rootLabel = "SDM";

  /**
   * The string displayed in the tree for the nodes that include some SDM subgraph elements.
   */
  public static String subGraphLabel = "Subgraph";

  /**
   * Defines the type of a node of the tree model that maps the object of the <code>IlvSDMModel</code>.
   */
  public static final int TYPE_SDM_OBJECT_NODE = 0;

  /**
   * Defines the type of a node of the tree model that maps a group of the <code>IlvSDMModel</code>.
   */
  public static final int TYPE_SDM_GROUP_OBJECT_NODE = 1;

  /**
   * Defines the type of a node of the tree model that maps a tag category of objects in the <code>IlvSDMModel</code>.
   */
  public static final int TYPE_SDM_TAG_NODE = 2;

  /**
   * Defines the type of the root node in the tree model.
   */
  public static final int TYPE_SDM_ROOT_NODE = 3;

  /**
   * Defines the type of a node of the tree model that maps a property name of an object of the <code>IlvSDMModel</code>.
   */
  public static final int TYPE_SDM_PROPERTY_NODE = 4;

  /**
   * Defines the type of a node of the tree model that maps a property value of an object of the <code>IlvSDMModel</code>.
   */
  public static final int TYPE_SDM_PROPERTY_VALUE_NODE = 5;

  /**
   * Defines the type of a node of the tree model that includes some SDM subgraph elements.
   */
  public static final int TYPE_TREE_SUBGRAPH_NODE = 6;

  /**
   * Defines an undefined type of node of the tree model.
   */
  public static final int TYPE_UNKNOWN = 7;

  /**
   * Constructs an <code>IlvSDMTreeModel</code> from an <code>IlvSDMModel</code>.
   * @param model The <code>IlvSDMModel</code> that is represented in this tree model.
   * @param showProperty Allows you to display or hide the properties of the SDM objects in the tree model.
   */
  public IlvSDMTreeModel(IlvSDMModel model, IlvSDMEngine engine, boolean showProperty)
  {
    this.showProperty = showProperty;
    this.model = model;
    this.engine = engine;
    initTreeViewRenderer();
    init();
  }

  void initTreeViewRenderer()
  {
    this.renderer = (IlvSDMTreeViewRenderer) IlvRendererUtil.getRenderer(this.engine,"TreeView");
    if (renderer != null) {
      String label = this.renderer.getRootTreeLabel();
      if (label != null)
        rootLabel = label;
      label = this.renderer.getSubgraphTreeLabel();
      if (label != null)
        subGraphLabel = label;
      showProperty = this.renderer.getShowAllSdmProperties();
    }
  }

  /**
   * Constructs an <code>IlvSDMTreeModel</code> from an <code>IlvSDMModel</code>.
   * The properties of the SDM objects are displayed in the tree model by default.
   * @param model The <code>IlvSDMModel</code> that is represented in this tree model.
   */
  public IlvSDMTreeModel(IlvSDMModel model, IlvSDMEngine engine)
  {
    this(model,engine,true);
  }

  void setSDMModel(IlvSDMModel model)
  {
    if (showProperty) {
      this.model.removeSDMPropertyChangeListener(this);
    }
    this.model.removeSDMModelListener(this);

    if (model != null) {
      this.model = model;
      init();
    }
  }

  void setPropertyVisible(boolean showProperty)
  {
    if (this.showProperty != showProperty) {
      this.showProperty = showProperty;
      init();
    }
  }

  boolean isPropertyVisible()
  {
    if (renderer != null)
      showProperty = this.renderer.getShowAllSdmProperties();
    return showProperty;
  }

  /**
   * Returns the path of an object in the tree model.
   * @param searchedObject An element of the tree structure.
   */
  public TreePath getTreePath(Object searchedObject)
  {
    TreePath returnedTreePath = null;
    if (CACHE_ENABLE)
      returnedTreePath = (TreePath) treePathCachingTable.get(searchedObject);
    if (returnedTreePath != null) {
      return returnedTreePath;
    }
    else {
      TreeElement searchedTreeElement = findObject(searchedObject, TYPE_UNKNOWN, -1);
      if (searchedTreeElement == null) return null;
      returnedTreePath = new TreePath(searchedTreeElement.getObjectPath());
      if (CACHE_ENABLE)
        treePathCachingTable.put(searchedTreeElement, returnedTreePath);
      return returnedTreePath;
    }
  }

  /**
   * Returns the type of an object from the tree model.
   * Returns <code>TYPE_UNKNOWN</code> if the object is not in the tree structure.
   * @param searchedObject An element of the tree structure.
   * @see #TYPE_SDM_OBJECT_NODE
   * @see #TYPE_SDM_GROUP_OBJECT_NODE
   * @see #TYPE_SDM_TAG_NODE
   * @see #TYPE_SDM_ROOT_NODE
   * @see #TYPE_SDM_PROPERTY_NODE
   * @see #TYPE_SDM_PROPERTY_VALUE_NODE
   * @see #TYPE_TREE_SUBGRAPH_NODE
   * @see #TYPE_UNKNOWN
   */
  public static int getObjectType(Object searchedObject)
  {
    if (searchedObject instanceof TreeElement)
      return ((TreeElement) searchedObject).SDMObjectType;
    else
      return TYPE_UNKNOWN;
  }

  /**
   * Returns the <code>Object<code> contained in an element of the tree structure.
   * Returns <code>null</code> if the object is not in the tree structure.
   * @param searchedObject An element of the tree structure.
   */
  public static Object getSDMObject(Object searchedObject)
  {
    if (searchedObject instanceof TreeElement)
      return ((TreeElement) searchedObject).element;
    else
      return null;
  }

  private TreeElement findObject(Object[] path, int SDMObjectType)
  {
    int i = 1;
    TreeElement node = root;
    node = findObject(node, path[path.length -1], path.length);
    return node;
  }

  private TreeElement findObject(Object searchedObject, int SDMObjectType, int scope)
  {
    TreeElement returnedObject = null;
    if (CACHE_ENABLE && (SDMObjectType == IlvSDMTreeModel.TYPE_SDM_GROUP_OBJECT_NODE ||
                         SDMObjectType == IlvSDMTreeModel.TYPE_SDM_OBJECT_NODE))
      returnedObject = (TreeElement) treeElementCachingTable.get(searchedObject);
    if (returnedObject == null) {
      returnedObject = findObject(root, searchedObject, scope-1);
      if (returnedObject == null && scope != 0) {
        returnedObject = findObjectAsString(root, searchedObject.toString(), SDMObjectType, scope-1);
      }
    }
    return returnedObject;
  }

  private TreeElement findObject(TreeElement node, Object searchedObject, int scope)
  {
    TreeElement returnValue = null;

    if (node == searchedObject || searchedObject == null) {
      returnValue = node;
    }

    if (returnValue == null && scope != 0) {
      for (int i=0; i<node.childCount; i++) {
        returnValue = findObject(node.childs[i], searchedObject, scope-1);
        if (returnValue != null) break;
      }
    }
    return returnValue;
  }

  private TreeElement findObjectAsSDMObject(TreeElement node, Object searchedObject, int scope)
  {
    TreeElement returnValue = null;
    if (node.element == searchedObject || searchedObject == null) {
      returnValue = node;
    }

    if (returnValue == null && scope != 0) {
      for (int i=0; i<node.childCount; i++) {
        returnValue = findObjectAsSDMObject(node.childs[i], searchedObject, scope-1);
        if (returnValue != null) break;
      }
    }
    return returnValue;
  }

  private TreeElement findObjectAsString(TreeElement node, String searchedObjectString, int SDMObjectType, int scope)
  {
    TreeElement returnValue = null;
    if (searchedObjectString == null || node.element.toString().compareTo(searchedObjectString) == 0) {
      returnValue = node;
    }

    if ( scope != 0 &&
         (returnValue == null || (returnValue.SDMObjectType != SDMObjectType && SDMObjectType != TYPE_UNKNOWN))) {
      for (int i=0; i<node.childCount; i++) {
        returnValue = findObjectAsString(node.childs[i], searchedObjectString, SDMObjectType, scope-1);
        if (returnValue != null) break;
      }
    }
    return returnValue;
  }

  void init()
  {
    selectedPath = new HashSet();
    addedObjects = new TreeQueue();
    removedObjects = new TreeQueue();
    subGraphBuffer = new HashMap();
    treePathCachingTable = new HashMap();
    treeElementCachingTable = new HashMap();

    if (showProperty) {
      this.model.addSDMPropertyChangeListener(this);
    }
    this.model.addSDMModelListener(this);

    this.selectedPath = new HashSet();
    this.addedObjects = new TreeQueue();
    this.removedObjects = new TreeQueue();

    this.root = new TreeElement(null, this.rootLabel, TYPE_SDM_ROOT_NODE);
    initSubGraph(root,true);
    notifyTreeModelListeners(new TreeModelEvent(this, new TreePath(root.getObjectPath())), STRUCTURE_CHANGED);
  }

  private void initSubGraph(TreeElement node, boolean recursive)
  {
    TreeElement parent = null;
    Enumeration e;
    if (node == this.root) {
      e = model.getObjects();
      parent = node;
    }
    else {
      e = model.getChildren(node.element);
      if (e != null && e.hasMoreElements() && this.showProperty) {
        node.addChild(new TreeElement(node, this.subGraphLabel, TYPE_TREE_SUBGRAPH_NODE));
        TreeElement subGraphNode = findObjectAsString(node,this.subGraphLabel, TYPE_TREE_SUBGRAPH_NODE, 1);
        parent = subGraphNode;
      }
      else {
        parent = node;
      }
      if (! recursive) return;
    }

    if (e == null || !e.hasMoreElements()) return;

    String tag;
    HashMap allTags = new HashMap();
    HashSet set;
    Object SDMObject;
    TreeElement tagNode, objectNode;
    Iterator itSet;

    while (e.hasMoreElements()) {
      SDMObject = e.nextElement();

      tag = model.getTag(SDMObject);
      if (!allTags.containsKey(tag)) {
        allTags.put(tag, new HashSet());
      }

      set = (HashSet) allTags.get(tag);
      set.add(SDMObject);
    }

    Iterator itMap = allTags.entrySet().iterator();
    Map.Entry entry;

    while (itMap.hasNext()) {
      entry = (Map.Entry) itMap.next();
      tag = (String)  entry.getKey();
      set = (HashSet) entry.getValue();

      parent.addChild(new TreeElement(parent, tag, TYPE_SDM_TAG_NODE));
      tagNode = findObjectAsString(parent,tag,TYPE_SDM_TAG_NODE,1);

      itSet = set.iterator();
      while(itSet.hasNext()) {
        SDMObject = itSet.next();

        if (model.getChildren(SDMObject) != null && model.getChildren(SDMObject).hasMoreElements()) {
          tagNode.addChild(new TreeElement(tagNode, SDMObject, TYPE_SDM_GROUP_OBJECT_NODE));
          objectNode = findObjectAsString(tagNode,SDMObject.toString(),TYPE_SDM_GROUP_OBJECT_NODE,1);
        }
        else {
          tagNode.addChild(new TreeElement(tagNode, SDMObject, TYPE_SDM_OBJECT_NODE));
          objectNode = findObjectAsString(tagNode,SDMObject.toString(),TYPE_SDM_OBJECT_NODE,1);
        }

        if (this.showProperty) addAllProperties(objectNode);
        addChildrens(objectNode, recursive);
      }
    }
  }

  private void addTreeModelObject(TreeElement node, Object SDMObject)
  {
    TreeElement parent = node;
    String tag = model.getTag(SDMObject);
    TreeElement tagNode = findObjectAsString(parent, tag, TYPE_SDM_TAG_NODE,1);
    TreeElement objectNode = null;

    if (tagNode == null) {
      parent.addChild(new TreeElement(parent, tag, TYPE_SDM_TAG_NODE));
      tagNode = findObjectAsString(parent,tag, TYPE_SDM_TAG_NODE,1);
    }


    if (subGraphBuffer.get(SDMObject) != null) {
      TreeElement subGraph = (TreeElement) subGraphBuffer.get(SDMObject);
      subGraph.parent = tagNode;
      tagNode.addChild(subGraph);
      subGraphBuffer.remove(SDMObject);
      return;
    }
    else if (model.getChildren(SDMObject) != null && model.getChildren(SDMObject).hasMoreElements()) {
      init();
      // tagNode.addChild(new TreeElement(tagNode, SDMObject, TYPE_SDM_GROUP_OBJECT_NODE));
      // objectNode = findObjectAsString(tagNode,SDMObject.toString(),TYPE_SDM_GROUP_OBJECT_NODE,1);
    }
    else {
      tagNode.addChild(new TreeElement(tagNode, SDMObject, TYPE_SDM_OBJECT_NODE));
      objectNode = findObjectAsString(tagNode,SDMObject.toString(),TYPE_SDM_OBJECT_NODE,1);
    }

    if (objectNode != null) {
      if (this.showProperty) addAllProperties(objectNode);
      addChildrens(objectNode, true);
    }
  }


  private void addAllProperties(TreeElement node)
  {
    TreeElement propertyNode;

    String hiddenPropertyList = IlvRendererUtil.getGraphicPropertyAsString(this.engine, node.element, "hiddenPropertyList",
                                                                               IlvSDMTree.TreeViewClasses, "");

    StringTokenizer hiddenPropertyListToken = new StringTokenizer(hiddenPropertyList, ",");
    HashMap hiddenPropertySet = new HashMap(hiddenPropertyListToken.countTokens());
    while(hiddenPropertyListToken.hasMoreTokens()) {
      hiddenPropertySet.put(hiddenPropertyListToken.nextToken(),node.element);
    }

    boolean showObjectProperties = IlvRendererUtil.getGraphicPropertyAsBoolean(this.engine, node.element, "showObjectProperies",
                                                                       IlvSDMTree.TreeViewClasses, true);


    String[] properties = model.getObjectPropertyNames(node.element);
    for (int i=0 ; i<properties.length; i++) {
      if (hiddenPropertySet.get(properties[i]) == null && showObjectProperties) {
        node.addChild(new TreeElement(node,properties[i], TYPE_SDM_PROPERTY_NODE));
        propertyNode = findObjectAsString(node, properties[i], TYPE_SDM_PROPERTY_NODE,1);
        propertyNode.addChild(new TreeElement(propertyNode, model.getObjectProperty(node.element, properties[i]), TYPE_SDM_PROPERTY_VALUE_NODE));
      }
    }
  }

  private void addChildrens(TreeElement node, boolean recursive)
  {
    initSubGraph(node,recursive);
  }

  /**
   *  Returns the root of the tree. Returns <code>null</code> only if the tree has no nodes.
   */
  public Object getRoot()
  {
    return root;
  }


  /**
   * Returns the child of the parent at the given index in the parent's child array.
   * The parent must be a node previously obtained from this data source.
   * This should not return <code>null</code> if <code>index</code> is a valid index for <code>parent</code>
   * (that is, index >= 0 && index < getChildCount(parent)).
   * @param parent A node in the tree, obtained from this data source.
   * @param index The position of the child node within the parent.
   */

  public Object getChild(Object parent, int index)
  {
    TreeElement parentTreeElement = findObject((TreeElement) parent, TYPE_UNKNOWN,-1);
    Object returnObject = null;

    if (index >= 0 && index < parentTreeElement.childs.length)
      returnObject = parentTreeElement.childs[index];
    return returnObject;
  }

  /**
   * Returns the number of children of <code>parent</code>.
   * Returns <code>0</code> if the node is a leaf or if it has no children.
   * <code>parent</code> must be a node previously obtained from this data source.
   * @param parent A node in the tree, obtained from this data source.
   */
  public int getChildCount(Object parent)
  {
    TreeElement parentTreeElement = findObject((TreeElement) parent, TYPE_UNKNOWN,-1);
    if (parentTreeElement != null)
      return parentTreeElement.childCount;
    else
      return 0;
  }

  /**
   * Returns <code>true</code> if <code>node</code> is a leaf.
   * It is possible for this method to return <code>false</code> even if <code>node</code> has no children.
   * A directory in a file system, for example, may contain no files;
   * the node representing the directory is not a leaf, but it also has no children.
   * @param node A node in the tree, obtained from this data source.
   */
  public boolean isLeaf(Object node)
  {
    TreeElement parentTreeElement = findObject((TreeElement) node, TYPE_UNKNOWN,-1);

    if (parentTreeElement == null) {
      return true;
    }
    return parentTreeElement.childCount == 0;
  }


  /**
   * Called when the user has altered the value for the item identified by <code>path</code> to <code>newValue</code>.
   * If <code>newValue</code> truly signifies a new value, the model should post a <code>treeNodesChanged</code> event.
   * @param path The path to the node that the user has altered.
   * @param newValue The new value from the <code>TreeCellEditor</code>.
   */
  public void valueForPathChanged(TreePath path, Object newValue)
  {
    TreeElement parentTreeElement = findObject(path.getPath()[path.getPathCount()-1], TYPE_UNKNOWN,-1);
    if (parentTreeElement.SDMObjectType == TYPE_SDM_PROPERTY_VALUE_NODE) {
      parentTreeElement.element = newValue;
      parentTreeElement.elementString = newValue.toString();
      this.model.setObjectProperty(parentTreeElement.parent.parent.element, parentTreeElement.parent.element.toString(), newValue.toString());
    }
  }

  /**
   * Returns the index of <code>child</code> in <code>parent</code>.
   * @param parent The parent object.
   * @param child The child object.
   */
  public int getIndexOfChild(Object parent, Object child)
  {
    TreeElement parentTreeElement = findObject(parent, TYPE_UNKNOWN,-1);
    if (parentTreeElement == null) return -1;
    return parentTreeElement.getChildPosition(child);
  }


  /**
   * Adds a listener for the <code>TreeModelEvent</code> posted after the tree changes.
   * @param l The listener to add.
   * @see #removeTreeModelListener(javax.swing.event.TreeModelListener)
   */
  public void addTreeModelListener(TreeModelListener l)
  {
    allTreeModelListeners.add(l);
  }


  /**
   * Removes a listener previously added with <code>addTreeModelListener()</code>.
   * @param l The listener to remove.
   * @see #addTreeModelListener(javax.swing.event.TreeModelListener)
   */
  public void removeTreeModelListener(TreeModelListener l)
  {
    allTreeModelListeners.remove(l);
  }

  private void notifyTreeModelListeners(TreeModelEvent e, int eventType)
  {
    Iterator it = allTreeModelListeners.iterator();
    while(it.hasNext()) {
      switch (eventType)
        {
        case NODES_CHANGED :
          ((TreeModelListener) it.next()).treeNodesChanged(e);
          break;
        case  NODES_INSERTED :
          ((TreeModelListener) it.next()).treeNodesInserted(e);
          break;
        case NODES_REMOVED :
          ((TreeModelListener) it.next()).treeNodesRemoved(e);
          break;
        case STRUCTURE_CHANGED :
          ((TreeModelListener) it.next()).treeStructureChanged(e);
          break;
        }
    }
  }

  /**
   * Invoked after nodes and/or links have been added to the SDM model.
   * @param event The event.
   */
  public void objectAdded(SDMModelEvent event)
  {
    TreeElement parentNode = null;
    TreeElement subflowNode = null;
    Object addedObject = null;
    Object parentSDMObject = null;
    if (event.isAdjusting()) {
      if (event.getObject() != null && event.getObject() != this.addedObjects.last()) {
        this.addedObjects.enqueue(event.getObject());
      }
    }
    else if (!addedObjects.isEmpty()) {
      while (!this.addedObjects.isEmpty()) {

        parentNode = null;
        subflowNode = null;
        addedObject = null;
        parentSDMObject = null;

        addedObject = this.addedObjects.peek();
        if (addedObject != null) {
          parentSDMObject = model.getParent(addedObject);
        }

        if (parentSDMObject != null) {
          parentNode = findObjectAsSDMObject(root,parentSDMObject,-1);
        }

        if (parentNode != null && this.showProperty) {
          subflowNode = findObjectAsString(parentNode,subGraphLabel,TYPE_UNKNOWN,1);
          if (subflowNode != null) {
            addTreeModelObject(subflowNode, addedObject);
          }
        }
        else if (parentNode != null) {
          addTreeModelObject(parentNode, addedObject);
        }
        else {
          addTreeModelObject(this.root, addedObject);
        }
        this.addedObjects.dequeue();
      }
      notifyTreeModelListeners(new TreeModelEvent(this, new TreePath(this.root.getObjectPath())), STRUCTURE_CHANGED);
    }
  }

  /**
   * Invoked after nodes and/or links have been removed from the SDM model.
   * @param event The event.
   */
  public void objectRemoved(SDMModelEvent event)
  {
    Object removedObject = null;
    if (event.isAdjusting()) {
      if (event.getObject() != null && event.getObject() != this.removedObjects.last()) {
        this.removedObjects.enqueue(event.getObject());
      }
    }

    if (!event.isAdjusting() && !removedObjects.isEmpty()) {
      TreeElement treeObjectNode = null;
      while (!removedObjects.isEmpty()) {
        treeObjectNode = findObject(this.removedObjects.peek(), TYPE_SDM_OBJECT_NODE,-1);
        removeTreeModelObject(treeObjectNode);
        this.removedObjects.dequeue();
      }

      if (treeObjectNode != null) {
        notifyTreeModelListeners(new TreeModelEvent(this, new TreePath(treeObjectNode.parent.getObjectPath())), STRUCTURE_CHANGED);
      }
    }

    if (event.getObject()!=null &&
        getObjectType(findObjectAsSDMObject(root,event.getObject(),-1)) ==  TYPE_SDM_GROUP_OBJECT_NODE) {
      TreeElement treeObjectNode = findObject(event.getObject(), TYPE_SDM_GROUP_OBJECT_NODE,-1);
      removeTreeModelObject(treeObjectNode);

      while (!removedObjects.isEmpty()) {
        treeObjectNode = findObject(this.removedObjects.peek(), TYPE_SDM_OBJECT_NODE,-1);
        removeTreeModelObject(treeObjectNode);
        this.removedObjects.dequeue();
      }
    }
  }

  private void removeTreeModelObject(TreeElement node)
  {
    TreeElement parent;

    if (node == null) {
      return;
    }

    if (node.parent != null) {
      node.parent.removeChild(node);
    }

    cleanTreeCaches(node);

    if (node.SDMObjectType == TYPE_SDM_GROUP_OBJECT_NODE) {
      //cleanParents(node.parent);
      //subGraphBuffer.put(node.element, node);
      init();
    }
  }

  private void cleanTreeCaches(TreeElement node)
  {
    if (CACHE_ENABLE) {
      for (int i=0; i<node.childCount; i++) {
        treePathCachingTable.remove(node);
        treeElementCachingTable.remove(node.element);
        cleanTreeCaches(node.childs[i]);
      }
    }
  }

  private void cleanParents(TreeElement node)
  {
    TreeElement parent = node;
    while (parent.parent != null && parent.parent.childCount == 1) {
      parent.parent.removeChild(parent);
      parent = parent.parent;
    }
    notifyTreeModelListeners(new TreeModelEvent(this, new TreePath(parent.getObjectPath())), STRUCTURE_CHANGED);
  }

  /**
   * Invoked after the source node of a link has been changed.
   * @param event The event.
   */
  public void linkSourceChanged(SDMModelEvent event) {}

  /**
   * Invoked after the destination node of a link has been changed.
   * @param event The event.
   */
  public void linkDestinationChanged(SDMModelEvent event) {}

  /**
   * Invoked after the SDM model has changed in such a way that the SDM view must be completely recreated.
   * @param event The event.
   */
  public void dataChanged(SDMModelEvent event)
  {
    if (event.isAdjusting()) return;
    model = event.getModel();
    init();
    notifyTreeModelListeners(new TreeModelEvent(this, new TreePath(root.getObjectPath())), STRUCTURE_CHANGED);
  }

  /**
   * Invoked at the end of an adjustment sequence, when <code>setAdjusting(false)</code> is called on the model.
   * @param event The event.
   */
  public void adjustmentFinished(SDMModelEvent event)
  {
    this.objectRemoved(event);
    this.objectAdded(event);
  }

  /**
   * Invoked after one or several properties of a data object have changed. This
   * method can also be called when an arbitrary set of properties have changed:
   * in that case, the property name and the old and new values contained in the
   * event are <code>null</code>.
   * @param e The event.
   */
  public void propertyChanged(SDMPropertyChangeEvent e)
  {
    if (!this.showProperty) return;

    TreeElement SDMObjectNode = null;
    TreeElement propertyNode = null;
    TreeElement propertyNodeValue = null;

    SDMObjectNode = findObjectAsSDMObject(this.root, e.getObject(), -1);
     if (SDMObjectNode == null) {
       return;
     }

     if (e.getOldValue() == null && e.getNewValue() != null) {
       SDMObjectNode.addChild(new TreeElement(SDMObjectNode,e.getPropertyName(), TYPE_SDM_PROPERTY_NODE));
       propertyNode = findObjectAsString(SDMObjectNode, e.getPropertyName(), TYPE_SDM_PROPERTY_NODE,1);
       propertyNode.addChild(new TreeElement(propertyNode, e.getNewValue(), TYPE_SDM_PROPERTY_VALUE_NODE));
     }
     else if (e.getOldValue() != null && e.getNewValue() == null) {
       propertyNode = findObjectAsString(SDMObjectNode, e.getPropertyName(), TYPE_SDM_PROPERTY_NODE,1);
       SDMObjectNode.removeChild(propertyNode);
     }
     else {
       propertyNode = findObjectAsString(SDMObjectNode, e.getPropertyName(),
                                         TYPE_SDM_PROPERTY_NODE, 1);
       if (propertyNode == null) {
         return;
       }

       propertyNodeValue = findObjectAsString(propertyNode, e.getOldValue().toString(),
                                              TYPE_SDM_PROPERTY_VALUE_NODE, 1);
       if (propertyNodeValue == null) {
         return;
       }
       propertyNodeValue.element = e.getNewValue();
       propertyNodeValue.elementString = e.getNewValue().toString();
     }

     Object[] SDMObjectNodePath = SDMObjectNode.getObjectPath();

     notifyTreeModelListeners(new TreeModelEvent(this, new TreePath(SDMObjectNodePath)), STRUCTURE_CHANGED);
  }

  private void printTreeContents()
  {
    printSubTree(root,0);
  }

  private void printSubTree(TreeElement node, int spaceNum)
  {
    for (int i=0; i<=spaceNum; i++) {
      System.err.print('-');
    }

    System.err.println(node.element + "[" + node.SDMObjectType + "]");

    for (int i=0; i<node.childCount; i++) {
      printSubTree(node.childs[i], spaceNum+1);
    }
  }

  boolean isModelSelectedTreePath(TreePath treePath)
  {
    return selectedPath.contains(treePath);
  }

  void addModelSelectedTreePath(TreePath treePath)
  {
    selectedPath.add(treePath);
  }

  void removeModelSelectedTreePath(TreePath treePath)
  {
    selectedPath.remove(treePath);
  }

  void printModelSelectedTreePath()
  {
    System.err.print(selectedPath.size() + " printModelSelectedTreePath : ");
    Iterator treepaths = selectedPath.iterator();

    if (!treepaths.hasNext()) System.err.println("");

    while(treepaths.hasNext()) {
      System.err.println(treepaths.next());
    }

  }

  class TreeElement
  {
    Object element;
    String elementString;
    int SDMObjectType;
    TreeElement parent;
    TreeElement[] childs = new TreeElement[10];
    int childCount = 0;

    TreeElement(TreeElement parent, Object element, int SDMObjectType)
    {
      this.parent = parent;
      this.element = element;
      this.elementString = element.toString();
      this.SDMObjectType = SDMObjectType;
    }

    void addChild(TreeElement child)
    {
      for(int i=0; i<this.childCount; i++) {
        if (this.childs[i].element == child.element) return;
      }
      if (this.childs.length == this.childCount) {
        TreeElement[] childsTmp = new TreeElement[this.childCount + this.childCount/2];
        System.arraycopy(this.childs, 0, childsTmp,   0, this.childCount);
        childs = childsTmp;
      }
      this.childs[childCount] = child;
      this.childCount++;
      if (CACHE_ENABLE) {
        IlvSDMTreeModel.this.treePathCachingTable.put(child,new TreePath(child.getObjectPath()));
        if (child.SDMObjectType == IlvSDMTreeModel.TYPE_SDM_GROUP_OBJECT_NODE ||
            child.SDMObjectType == IlvSDMTreeModel.TYPE_SDM_OBJECT_NODE)
          IlvSDMTreeModel.this.treeElementCachingTable.put(child.element,child);
      }
    }

    void removeChild(TreeElement child)
    {
      if (CACHE_ENABLE) {
        IlvSDMTreeModel.this.treePathCachingTable.remove(child);
        if (child.SDMObjectType == IlvSDMTreeModel.TYPE_SDM_GROUP_OBJECT_NODE ||
            child.SDMObjectType == IlvSDMTreeModel.TYPE_SDM_OBJECT_NODE)
          IlvSDMTreeModel.this.treeElementCachingTable.remove(child.element);
      }
      int position = getChildPosition(child);
      this.childs[position] = null;

      for (int i=position+1; i<this.childs.length; i++) {
        this.childs[i-1] = this.childs[i];
      }
      this.childCount--;
    }

    int getChildPosition(Object child)
    {
      for(int i=0; i<this.childs.length ; i++) {
        if (this.childs[i] == child) {
          return i;
        }
      }
      return -1;
    }

    Object[] getObjectPath()
    {
      int parentCounter = 1;
      TreeElement node = this;
      while (node.parent != null) {
        node = node.parent;
        parentCounter ++;
      }

      Object[] path = new Object[parentCounter];
      node = this;
      parentCounter--;
      while (node.parent != null) {
        path[parentCounter] = node;
        node = node.parent;
        parentCounter--;
      }
      path[parentCounter] = node;
      return path;
    }

    public String toString()
    {
      String objectLabel = null;
      switch (SDMObjectType) {
      case IlvSDMTreeModel.TYPE_SDM_OBJECT_NODE:
        objectLabel = IlvRendererUtil.getGraphicPropertyAsString(IlvSDMTreeModel.this.engine, element, "label",
                                                                  IlvSDMTree.TreeViewClasses, "");
        if (objectLabel != null)
          return objectLabel;
        else
          return model.getID(element);
      case IlvSDMTreeModel.TYPE_SDM_GROUP_OBJECT_NODE:
        objectLabel = IlvRendererUtil.getGraphicPropertyAsString(IlvSDMTreeModel.this.engine, element, "label",
                                                                  IlvSDMTree.TreeViewClasses, "");
        if (objectLabel != null)
          return objectLabel;
        else
          return model.getID(element);
      default:
        return elementString;
      }
    }
  }

  private class TreeQueue
  {
    private final static int DEFAULT_CAPACITY = 5;
    private Object[] queue;
    private int front;
    private int rear;
    private int count;

    public TreeQueue()
    {
      this.queue = new Object[DEFAULT_CAPACITY];
      front = this.queue.length -1;
      rear = this.queue.length -1;
      count = 0;
    }

    public synchronized void enqueue(Object value)
    {
      if (isFull()) {
        Object[] tmpQueue = new Object[3 * queue.length];
        System.arraycopy(this.queue, this.queue.length - this.count, tmpQueue, tmpQueue.length - this.count, this.count);
        this.queue = tmpQueue;
        this.front = this.queue.length -1;
        this.rear = this.queue.length - this.count;
      }

      if (isEmpty()) {
        this.front = this.queue.length - 1;
        this.queue[front] = value;
        this.rear = this.front;
        this.count = 1;
      }
      else {
        this.rear--;
        this.queue[rear] = value;
        this.count++;
      }
    }

    public synchronized Object dequeue()
    {
      if(isEmpty()) {
        return null;
      }
      Object frontItem = this.queue[front];

      for (int i =  this.queue.length-1; i > rear; i--) {
        this.queue[i] = this.queue[i-1];
      }

      this.queue[rear] = null;
      this.rear++;
      this.count--;

      return frontItem;
    }

    public Object peek()
    {
      if (isEmpty()) {
        return null;
      }
      else {
        return this.queue[front];
      }
    }

    public Object peekNext()
    {
      if (isEmpty() || last() == peek()) {
        return null;
      }
      else {
        return this.queue[front-1];
      }
    }

    public Object last()
    {
      if (isEmpty()) {
        return null;
      }
      else {
        return this.queue[rear];
      }
    }

    public boolean isEmpty ()
    {
      return this.count == 0;
    }

    public boolean isFull()
    {
      return this.count == this.queue.length;
    }

    public String toString()
    {
      String returnValue = new String();

      returnValue += "[";
      returnValue += this.count;
      returnValue += "] ";

      for (int i=0; i<this.queue.length; i++) {
        returnValue += this.queue[i];
        returnValue += " / ";
      }
      return returnValue;
    }
  }
}
