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
 * $Id: IlvSDMTreeCellRenderer.java,v 1.20 2001/10/03 15:36:51 gdigugli Exp $
 *
 */

package exp.ilog.tree;


import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import ilog.views.*;
import ilog.views.sdm.*;
import ilog.views.sdm.model.*;
import ilog.views.sdm.renderer.*;
import ilog.views.sdm.graphic.*;
import ilog.views.sdm.gui.util.*;

import ilog.views.awt.*;
import ilog.views.graphic.*;
import ilog.views.prototypes.*;

/**
 * Defines the requirements for an object that displays a tree node of an {@link IlvSDMTreeModel}.
 * It generates icons from the <code>IlvSDMEngine</code> using the stylesheet support of the engine.
 * And it use specific static icons following the type of tree elements in of the {@link IlvSDMTreeModel}.
 * <p>
 * See {@link ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer} for the styling features of the tree.
 * </p>
 * @see ilog.views.sdm.gui.tree.IlvSDMTree
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeModel
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellEditor
 * @see ilog.views.sdm.gui.IlvSDMTreeView
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer
 */
public class IlvSDMTreeCellRenderer extends DefaultTreeCellRenderer
{
  private String sdmObjectNodeIconFile;
  private String sdmGroupObjectNodeIconFile;
  private String sdmTagNodeIconFile;
  private String sdmRootNodeIconFile;
  private String sdmPropertyNodeIconFile;
  private String sdmPropertyValueNodeIconFile;
  private String treeSubgraphNodeIconFile;
  private String unknownNodeIconFile;

  private static final String TreeViewRendererClass = "TreeView";
  private static final String[] TreeViewRendererClasses = { TreeViewRendererClass };

  private static final Class urlIconBaseClass = IlvSDMTreeCellRenderer.class;
  private static IlvSDMEngine iconWorkingEngine;
  private static IlvSDMView iconWorkingView;
  private static IlvManagerViewPanel iconWorkingViewPanel;
  private static MediaTracker iconWorkingTracker;
  private static Frame iconWorkingFrame;
  private static Window iconWorkingWindow;
  private static IlvReliefRectangle iconWorkingShadowRectangle;
  private static int iconHints = Image.SCALE_DEFAULT;
  private static int iconMargin = 1;
  private static int iconShadowThickness = 0;

  private IlvSDMEngine engine;
  private HashMap iconCachingTable;
  private IlvSDMTreeViewRenderer renderer;
  private boolean isGeneratedIcon = true;

  private static boolean DEBUG_FLAG = false;

  /**
   * Creates an <code>IlvSDMTreeCellRenderer</code>.
   * @param engine the engine used by the {@link IlvSDMTreeModel}.
   */
  public IlvSDMTreeCellRenderer(IlvSDMEngine engine) {
    super();
    this.engine = engine;
    this.iconCachingTable = new HashMap(100);
    this.iconWorkingEngine = new IlvSDMEngine(new IlvGrapher(), new IlvDefaultSDMModel());
    this.iconWorkingView = new IlvSDMView(this.iconWorkingEngine);
    this.iconWorkingView.setAntialiasing(true);
    this.iconWorkingViewPanel = new IlvManagerViewPanel(this.iconWorkingView);
    this.iconWorkingTracker = new MediaTracker(this.iconWorkingViewPanel);
    this.iconWorkingFrame = new Frame();
    this.iconWorkingWindow = new Window(this.iconWorkingFrame);
    this.iconWorkingWindow.add(this.iconWorkingViewPanel);
    this.iconWorkingWindow.setSize(100, 100);
    this.iconWorkingShadowRectangle = new IlvReliefRectangle(new IlvRect(0, 0, 10, 10));
    if (DEBUG_FLAG) this.iconWorkingWindow.setVisible(true);
    try {
      String[] stylesheets = this.engine.getStyleSheets();
      if (stylesheets != null) {
        ((IlvStyleSheetRenderer) this.iconWorkingEngine.getRenderer()).setStyleSheets(stylesheets);
      }
    }
    catch (IlvSDMException ex) {
      ex.printStackTrace();
    }

    this.renderer = (IlvSDMTreeViewRenderer) IlvRendererUtil.getRenderer(this.engine,"TreeView");

    if (renderer != null) {
      String iconFile = this.renderer.getSdmObjectIcon();
      if (iconFile != null)
        this.sdmObjectNodeIconFile = iconFile;

      iconFile = this.renderer.getSdmGroupIcon();
      if (iconFile != null)
        this.sdmGroupObjectNodeIconFile = iconFile;

      iconFile = this.renderer.getTagIcon();
      if (iconFile != null)
        this.sdmTagNodeIconFile = iconFile;

      iconFile = this.renderer.getRootIcon();
      if (iconFile != null)
        this.sdmRootNodeIconFile = iconFile;

      iconFile = this.renderer.getPropertyNameIcon();
      if (iconFile != null)
        this.sdmPropertyNodeIconFile = iconFile;

      iconFile = this.renderer.getPropertyValueIcon();
      if (iconFile != null)
        this.sdmPropertyValueNodeIconFile = iconFile;

      iconFile = this.renderer.getSubgraphIcon();
      if (iconFile != null)
        this.treeSubgraphNodeIconFile = iconFile;

      iconFile = this.renderer.getUnknownObjectIcon();
      if (iconFile != null)
        this.unknownNodeIconFile = iconFile;

      this.isGeneratedIcon = this.renderer.getGeneratedIcon();
    }
  }

  private Color[] getTextColor(Object selectedObject)
  {
    Color[] textColor = new Color[2];

    String graphicProperty = IlvRendererUtil.getGraphicPropertyAsString(this.engine, selectedObject,
                                                                        "textColor",
                                                                        IlvSDMTree.TreeViewClasses,
                                                                        "");
    StringTokenizer graphicPropertyTokenizer = new StringTokenizer(graphicProperty, ",");
    int i = 0;
    while (graphicPropertyTokenizer.hasMoreTokens() && i<2) {
      textColor[i++] = Color.decode(graphicPropertyTokenizer.nextToken());
    }
    return textColor;
  }

  private Color[] getCellColor(Object selectedObject)
  {
    Color[] cellColor = new Color[2];

    String graphicProperty = IlvRendererUtil.getGraphicPropertyAsString(this.engine, selectedObject,
                                                                        "cellColor",
                                                                        IlvSDMTree.TreeViewClasses,
                                                                        "");
    StringTokenizer graphicPropertyTokenizer = new StringTokenizer(graphicProperty, ",");
    int i = 0;
    while (graphicPropertyTokenizer.hasMoreTokens() && i<2) {
      cellColor[i++] = Color.decode(graphicPropertyTokenizer.nextToken());
    }
    return cellColor;
  }



  /**
   * Returns the <code>Component</code> that the renderer uses to draw the value
   * Configures the renderer based on the passed in components.
   * Sets the value of the current tree cell to <code>value</code>.
   * @param tree      the <code>JTree</code> in used with the renderer.
   * @param value     the value which is assigned to the component.
   * @param sel       if <code>sel</code> is <code>true</code>, the cell will be drawn as if selected.
   * @param expanded  if <code>expanded</code> is <code>true</code> the node is currently expanded.
   * @param leaf      if <code>leaf</code> is <code>true</code> the node represets a leaf.
   * @param row       the row number of the tree element.
   * @param hasFocus  if <code>hasFocus</code> is <code>true</code> the node currently has focus.
   */
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    Color[] textColor;
    Color[] cellColor;
    String highlightedPropertyList;
    StringTokenizer highlightedPropertyListToken;
    HashMap highlightedPropertySet;
    Color highlightColor;
    Icon cellIcon = null;

    switch (((IlvSDMTree) tree).getSDMTreeModel().getObjectType(value))
      {
      case IlvSDMTreeModel.TYPE_SDM_GROUP_OBJECT_NODE :
        textColor = getTextColor(((IlvSDMTreeModel.TreeElement) value).element);
        cellColor = getCellColor(((IlvSDMTreeModel.TreeElement) value).element);
        if (textColor[1] != null) setTextSelectionColor(textColor[1]);
        else setTextSelectionColor(UIManager.getColor("Tree.selectionForeground"));
        if (textColor[0] != null) setTextNonSelectionColor(textColor[0]);
        else setTextNonSelectionColor(UIManager.getColor("Tree.textForeground"));
        if (cellColor[1] != null) setBackgroundSelectionColor(cellColor[1]);
        else setBackgroundSelectionColor(UIManager.getColor("Tree.selectionBackground"));
        if (cellColor[0] != null) setBackgroundNonSelectionColor(cellColor[0]);
        else setBackgroundNonSelectionColor(UIManager.getColor("Tree.textBackground"));
        break;
      case IlvSDMTreeModel.TYPE_SDM_OBJECT_NODE :
        textColor = getTextColor(((IlvSDMTreeModel.TreeElement) value).element);
        cellColor = getCellColor(((IlvSDMTreeModel.TreeElement) value).element);
        if (textColor[0] != null) setTextNonSelectionColor(textColor[0]);
        else setTextNonSelectionColor(UIManager.getColor("Tree.textForeground"));
        if (textColor[1] != null) setTextSelectionColor(textColor[1]);
        else setTextSelectionColor(UIManager.getColor("Tree.selectionForeground"));
        if (cellColor[0] != null) setBackgroundNonSelectionColor(cellColor[0]);
        else setBackgroundNonSelectionColor(UIManager.getColor("Tree.textBackground"));
        if (cellColor[1] != null) setBackgroundSelectionColor(cellColor[1]);
        else setBackgroundSelectionColor(UIManager.getColor("Tree.selectionBackground"));
        break;
      case IlvSDMTreeModel.TYPE_SDM_PROPERTY_NODE :
        highlightedPropertyList = IlvRendererUtil.getGraphicPropertyAsString(this.engine,
                                                                             ((IlvSDMTreeModel.TreeElement) value).parent.element,
                                                                             "highlightedPropertyList",
                                                                             IlvSDMTree.TreeViewClasses, "");

        highlightedPropertyListToken = new StringTokenizer(highlightedPropertyList, ",");
        highlightedPropertySet = new HashMap(highlightedPropertyListToken.countTokens());
        while(highlightedPropertyListToken.hasMoreTokens()) {
          highlightedPropertySet.put(highlightedPropertyListToken.nextToken(),value);
        }
        initTreeCellRendererColors(this);
        if (highlightedPropertySet.get(value.toString()) != null) {
          highlightColor = Color.decode(IlvRendererUtil.getGraphicPropertyAsString(this.engine,
                                                                                   ((IlvSDMTreeModel.TreeElement) value).parent.element,
                                                                                   "highlightColor",
                                                                                   IlvSDMTree.TreeViewClasses, "#ff0000"));
          setBackgroundSelectionColor(highlightColor);
          setBackgroundNonSelectionColor(highlightColor);
        }
        break;
      case IlvSDMTreeModel.TYPE_SDM_PROPERTY_VALUE_NODE :
        highlightedPropertyList = IlvRendererUtil.getGraphicPropertyAsString(this.engine,
                                                                             ((IlvSDMTreeModel.TreeElement) value).parent.parent.element,
                                                                             "highlightedPropertyList",
                                                                             IlvSDMTree.TreeViewClasses, "");

        highlightedPropertyListToken = new StringTokenizer(highlightedPropertyList, ",");
        highlightedPropertySet = new HashMap(highlightedPropertyListToken.countTokens());
        while(highlightedPropertyListToken.hasMoreTokens()) {
          highlightedPropertySet.put(highlightedPropertyListToken.nextToken(),value);
        }
        initTreeCellRendererColors(this);
        if (highlightedPropertySet.get(((IlvSDMTreeModel.TreeElement) value).parent.toString()) != null) {
          highlightColor = Color.decode(IlvRendererUtil.getGraphicPropertyAsString(this.engine,
                                                                                   ((IlvSDMTreeModel.TreeElement) value).parent.parent.element,
                                                                                   "highlightColor",
                                                                                   IlvSDMTree.TreeViewClasses, "#ff0000"));
          setBackgroundSelectionColor(highlightColor);
          setBackgroundNonSelectionColor(highlightColor);
        }
        break;
      case IlvSDMTreeModel.TYPE_SDM_ROOT_NODE :
        initTreeCellRendererColors(this);
        break;
      case IlvSDMTreeModel.TYPE_SDM_TAG_NODE :
        initTreeCellRendererColors(this);
        break;
      case IlvSDMTreeModel.TYPE_TREE_SUBGRAPH_NODE :
        initTreeCellRendererColors(this);
        break;
      case IlvSDMTreeModel.TYPE_UNKNOWN :
        initTreeCellRendererColors(this);
        break;
      }

    Component returnedComponent = super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);

    switch (((IlvSDMTree) tree).getSDMTreeModel().getObjectType(value))
      {
      case IlvSDMTreeModel.TYPE_SDM_GROUP_OBJECT_NODE :
        cellIcon = getSDMGroupObjectIcon(value,tree,expanded,leaf);
        break;
      case IlvSDMTreeModel.TYPE_SDM_OBJECT_NODE :
        cellIcon = getSDMObjectIcon(value,tree,expanded,leaf);
        break;
      case IlvSDMTreeModel.TYPE_SDM_PROPERTY_NODE :
        cellIcon = getSDMPropertyIcon(value,expanded,leaf);
        break;
      case IlvSDMTreeModel.TYPE_SDM_PROPERTY_VALUE_NODE :
        cellIcon = getSDMPropertyValueIcon(value,expanded,leaf);
        break;
      case IlvSDMTreeModel.TYPE_SDM_ROOT_NODE :
        cellIcon = getSDMRootIcon(value,expanded,leaf);
        break;
      case IlvSDMTreeModel.TYPE_SDM_TAG_NODE :
        cellIcon = getSDMTagIcon(value,expanded,leaf);
        break;
      case IlvSDMTreeModel.TYPE_TREE_SUBGRAPH_NODE :
        cellIcon = getTreeSubgraphIcon(value,expanded,leaf);
        break;
      case IlvSDMTreeModel.TYPE_UNKNOWN :
        cellIcon = getUnknownIcon(value,expanded,leaf);
        break;
      }

    if (cellIcon != null)
      ((DefaultTreeCellRenderer) returnedComponent).setIcon(cellIcon);
    return returnedComponent;
  }

  private void initTreeCellRendererColors(DefaultTreeCellRenderer cell)
  {
    cell.setTextSelectionColor(UIManager.getColor("Tree.selectionForeground"));
    cell.setTextNonSelectionColor(UIManager.getColor("Tree.textForeground"));
    cell.setBackgroundSelectionColor(UIManager.getColor("Tree.selectionBackground"));
    cell.setBackgroundNonSelectionColor(UIManager.getColor("Tree.textBackground"));
  }

  Icon getSDMGroupObjectIcon(Object treeNode, JTree tree, boolean expanded, boolean leaf)
  {
    boolean useObjectGeneratedIcons = IlvRendererUtil.getGraphicPropertyAsBoolean(this.engine, ((IlvSDMTreeModel.TreeElement) treeNode).element,
                                                                                  "generatedIcons",
                                                                                  IlvSDMTree.TreeViewClasses, true);

    String defaultIcon  = IlvRendererUtil.getGraphicPropertyAsString(this.engine, ((IlvSDMTreeModel.TreeElement) treeNode).element,
                                                                     "defaultIcon",
                                                                     IlvSDMTree.TreeViewClasses, "");

    Object returnedIcon = this.iconCachingTable.get(treeNode);
    if (returnedIcon == null && this.isGeneratedIcon && useObjectGeneratedIcons) {
      Object SDMObject = ((IlvSDMTree) tree).getSDMTreeModel().getSDMObject(treeNode);
      Image objectImage = makeAutoIcon(this.engine, SDMObject, 24, new Color(255,255,255));
      if (objectImage != null)
        returnedIcon = new ImageIcon(objectImage);
      if (returnedIcon != null)
        this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && !this.isGeneratedIcon && !useObjectGeneratedIcons && defaultIcon != null) {
      returnedIcon = getIconFromFile(defaultIcon);
      this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && sdmGroupObjectNodeIconFile != null) {
      returnedIcon = getIconFromFile(sdmGroupObjectNodeIconFile);
      this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && expanded) {
      returnedIcon = getDefaultOpenIcon();
    }
    if (returnedIcon == null) {
      returnedIcon = getDefaultClosedIcon();
    }
    return (Icon) returnedIcon;
  }

  Icon getSDMObjectIcon(Object treeNode, JTree tree, boolean expanded, boolean leaf)
  {
    boolean useObjectGeneratedIcons = IlvRendererUtil.getGraphicPropertyAsBoolean(this.engine, ((IlvSDMTreeModel.TreeElement) treeNode).element,
                                                                                  "generatedIcons",
                                                                                  IlvSDMTree.TreeViewClasses, true);

    String defaultIcon  = IlvRendererUtil.getGraphicPropertyAsString(this.engine, ((IlvSDMTreeModel.TreeElement) treeNode).element,
                                                                     "defaultIcon",
                                                                     IlvSDMTree.TreeViewClasses, "");

    Object returnedIcon = this.iconCachingTable.get(treeNode);
    if (returnedIcon == null && this.isGeneratedIcon && useObjectGeneratedIcons) {
      Object SDMObject = ((IlvSDMTree) tree).getSDMTreeModel().getSDMObject(treeNode);
      Image objectImage = makeAutoIcon(this.engine, SDMObject, 24, new Color(255,255,255));
      if (objectImage != null)
        returnedIcon = new ImageIcon(objectImage);
      if (returnedIcon != null)
        this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && !this.isGeneratedIcon && !useObjectGeneratedIcons && defaultIcon != null) {
      returnedIcon = getIconFromFile(defaultIcon);
      this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && sdmObjectNodeIconFile != null) {
      returnedIcon = getIconFromFile(sdmObjectNodeIconFile);
      this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && expanded) {
      returnedIcon = getDefaultOpenIcon();
    }
    if (returnedIcon == null && !leaf) {
      returnedIcon = getDefaultClosedIcon();
    }
    if (returnedIcon == null) {
      returnedIcon = getDefaultLeafIcon();
    }
    return (Icon) returnedIcon;
  }

  Icon getSDMPropertyIcon(Object treeNode, boolean expanded, boolean leaf)
  {
    Object returnedIcon = this.iconCachingTable.get(treeNode);
    if (returnedIcon == null) {
      returnedIcon = getIconFromFile(sdmPropertyNodeIconFile);
      if (returnedIcon != null)
        this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && expanded) {
      returnedIcon = getDefaultOpenIcon();
    }
    if (returnedIcon == null) {
      returnedIcon = getDefaultClosedIcon();
    }
    return (Icon) returnedIcon;
  }

  Icon getSDMPropertyValueIcon(Object treeNode, boolean expanded, boolean leaf)
  {
    Object returnedIcon = this.iconCachingTable.get(treeNode);
    if (returnedIcon == null) {
      returnedIcon = getIconFromFile(sdmPropertyValueNodeIconFile);
      this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null) {
      returnedIcon = getDefaultLeafIcon();
    }
    return (Icon) returnedIcon;
  }

  Icon getSDMRootIcon(Object treeNode, boolean expanded, boolean leaf)
  {
    Object returnedIcon = this.iconCachingTable.get(treeNode);
    if (returnedIcon == null) {
      returnedIcon = getIconFromFile(sdmRootNodeIconFile);
      if (returnedIcon != null)
        this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && expanded) {
      returnedIcon = getDefaultOpenIcon();
    }
    if (returnedIcon == null && !leaf) {
      returnedIcon = getDefaultClosedIcon();
    }
    if (returnedIcon == null) {
      returnedIcon = getDefaultLeafIcon();
    }
    return (Icon) returnedIcon;
  }

  Icon getSDMTagIcon(Object treeNode, boolean expanded, boolean leaf)
  {
    Object returnedIcon = this.iconCachingTable.get(treeNode);
    if (returnedIcon == null) {
      returnedIcon = getIconFromFile(sdmTagNodeIconFile);
      if (returnedIcon != null)
        this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && expanded) {
      returnedIcon = getDefaultOpenIcon();
    }
    if (returnedIcon == null && !leaf) {
      returnedIcon = getDefaultClosedIcon();
    }
    if (returnedIcon == null) {
      returnedIcon = getDefaultLeafIcon();
    }
    return (Icon) returnedIcon;
  }

  Icon getTreeSubgraphIcon(Object treeNode, boolean expanded, boolean leaf)
  {
    Object returnedIcon = this.iconCachingTable.get(treeNode);
    if (returnedIcon == null) {
      returnedIcon = getIconFromFile(treeSubgraphNodeIconFile);
      if (returnedIcon != null)
        this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && expanded) {
      returnedIcon = getDefaultOpenIcon();
    }
    if (returnedIcon == null && !leaf) {
      returnedIcon = getDefaultClosedIcon();
    }
    if (returnedIcon == null) {
      returnedIcon = getDefaultLeafIcon();
    }
    return (Icon) returnedIcon;
  }

  Icon getUnknownIcon(Object treeNode, boolean expanded, boolean leaf)
  {
    Object returnedIcon = this.iconCachingTable.get(treeNode);
    if (returnedIcon == null) {
      returnedIcon = getIconFromFile(unknownNodeIconFile);
      if (returnedIcon != null)
        this.iconCachingTable.put(treeNode,returnedIcon);
    }
    if (returnedIcon == null && expanded) {
      returnedIcon = getDefaultOpenIcon();
    }
    if (returnedIcon == null && !leaf) {
      returnedIcon = getDefaultClosedIcon();
    }
    if (returnedIcon == null) {
      returnedIcon = getDefaultLeafIcon();
    }
    return (Icon) returnedIcon;
  }

  static Icon getIconFromFile(String iconFileString)
  {
    ImageIcon imageIcon = null;

    if(iconFileString != null)
      {
        URL iconURL = null;
        try
          {
            iconURL = IlvSDMUtils.findURL(null, urlIconBaseClass, iconFileString);
          }
        catch(Exception ex)
          {
            ex.printStackTrace();
          }

        imageIcon = new ImageIcon(iconURL);
      }
    return imageIcon;
  }

  private static class LittleEnumeration implements Enumeration {

    private boolean hasMoreElements = true;
    private Object singleObject;

    public LittleEnumeration(Object singleObject)
    {
      this.singleObject = singleObject;
    }

    public boolean hasMoreElements()
    {
      return hasMoreElements;
    }

    public Object nextElement()
    {
      hasMoreElements = false;
      return singleObject;
    }
  }

  private synchronized Image makeAutoIcon(IlvSDMEngine engine, Object sdmObject, int iconSize, Color background)
  {
    CellRendererSDMLink link = null;
    CellRendererSDMNode node = null;
    Object sdmObjectClone = null;

    try {
      iconWorkingEngine.selectAllObjects();
      iconWorkingEngine.delete();
      iconWorkingEngine.getGrapher().deleteAll(DEBUG_FLAG);

      if (background == null) {
        background = Color.white;
      }

      iconWorkingView.setBackground(background);
      if (engine.getModel().isLink(sdmObject)) {
        link = new CellRendererSDMLink(engine.getModel(), sdmObject, iconWorkingEngine.getModel());
        link.setID(new Integer(link.hashCode()).toString());
        sdmObjectClone = link;
      }
      else {
        node = new CellRendererSDMNode(engine.getModel(), sdmObject);
        node.setID(new Integer(node.hashCode()).toString());
        sdmObjectClone = node;
      }

      iconWorkingEngine.getModel().addObject(sdmObjectClone,null,null);
      if (DEBUG_FLAG) iconWorkingWindow.repaint();
      IlvGraphic instance = iconWorkingEngine.getGraphic(sdmObjectClone,false);

      if (instance != null && instance instanceof IlvGeneralNode)
        ((IlvGeneralNode) instance).setLabel("");
      else if (instance != null && instance instanceof IlvGeneralLink)
        ((IlvGeneralLink) instance).setLabel("");
      else
        return null;

      int objectLayer = iconWorkingEngine.getGrapher().getLayer(instance);

      for (int i=0; i<iconWorkingEngine.getGrapher().getLayersCount(); i++) {
        iconWorkingEngine.getGrapher().setVisible(i, false, false);
      }
      objectLayer = iconWorkingEngine.getGrapher().getLayersCount()+1;
      iconWorkingEngine.getGrapher().addLayer(objectLayer);
      iconWorkingEngine.getGrapher().setLayer(instance, objectLayer, false);
      iconWorkingEngine.getGrapher().setVisible(objectLayer, true, false);

      IlvRect bbox = instance.boundingBox();
      float width = bbox.widthFloor() + 1;
      float height = bbox.heightFloor() + 1;
      float imageSize, x, y;

      if (width > height) {
  imageSize = width;
  x = 0;
  y = (width - height) / (float) 2;
      }
      else {
  imageSize = height;
  x = (height - width) / (float) 2;
  y = 0;
      }

      double scale = (double)iconSize / (double)imageSize;

      int margin = (int)((iconMargin + iconShadowThickness) * scale);
      imageSize += 2 * margin;
      x += margin;
      y += margin;

      if (iconShadowThickness > 0){
        iconWorkingShadowRectangle.setBackground(background);
        iconWorkingShadowRectangle.setThickness((int)(iconShadowThickness*scale));
        iconWorkingShadowRectangle.moveResize(new IlvRect(0, 0, imageSize, imageSize));
        iconWorkingEngine.getGrapher().addObject(iconWorkingShadowRectangle, 0, false);
      }

      instance.move(x, y);
      if (DEBUG_FLAG) iconWorkingView.repaint();


      BufferedImage bi = new BufferedImage(iconSize,iconSize,BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = bi.createGraphics();
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g2d.setPaint(background);

      g2d.fillRect(0, 0, iconSize, iconSize);
      g2d.setClip(0, 0, iconSize, iconSize);

      iconWorkingEngine.getGrapher().print(g2d,
                                           new IlvRect(x,y, width + x, height + y),
                                           iconWorkingView,
                                           new IlvTransformer(scale, scale, new IlvPoint(0,0)),
                                           false);
      return bi;
    }
    catch(Throwable e) {
      e.printStackTrace();
    }
    return null;
  }

  class CellRendererSDMLink extends IlvDefaultSDMLink
  {
    private IlvSDMModel model;
    private Object objectToClone;
    private IlvSDMModel targetModel;

    public CellRendererSDMLink(IlvSDMModel model, Object objectToClone, IlvSDMModel targetModel)
    {
      super(model.getTag(objectToClone));
      this.objectToClone = objectToClone;
      this.model = model;
      this.targetModel = targetModel;

      IlvDefaultSDMNode nodeFrom = new IlvDefaultSDMNode("anchor");
      nodeFrom.setProperty("x", "0");
      nodeFrom.setProperty("y", "0");
      this.targetModel.addObject(nodeFrom,null,null);

      IlvDefaultSDMNode  nodeTo = new IlvDefaultSDMNode("anchor");
      nodeTo.setProperty("x", "50");
      nodeTo.setProperty("y", "50");
      this.targetModel.addObject(nodeTo,null,null);

      setFrom(nodeFrom);
      setTo(nodeTo);
    }

    public Object getProperty(String property)
    {
      return this.model.getObjectProperty(objectToClone,property);
    }

    public void setProperty(String property, Object value)
    {
      this.model.setObjectProperty(this.objectToClone, property, value);
    }
  }

  class CellRendererSDMNode extends IlvDefaultSDMNode
  {
    private IlvSDMModel model;
    private Object objectToClone;

    public CellRendererSDMNode(IlvSDMModel model, Object objectToClone)
    {
      super(model.getTag(objectToClone));
      this.objectToClone = objectToClone;
      this.model = model;
    }

    public Object getProperty(String property)
    {
      return this.model.getObjectProperty(objectToClone,property);
    }

    public void setProperty(String property, Object value)
    {
      this.model.setObjectProperty(this.objectToClone, property, value);
    }
  }
}
