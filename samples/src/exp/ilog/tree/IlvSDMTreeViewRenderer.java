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
 * $Id: IlvSDMTreeViewRenderer.java,v 1.6 2001/10/03 15:36:51 gdigugli Exp $
 *
 */

package exp.ilog.tree;

import ilog.views.sdm.renderer.*;

/**
 * The class <code>IlvSDMTreeViewRenderer</code> is a filtering renderer that controls the settings of the {@link ilog.views.sdm.gui.tree.IlvSDMTree}.
 * Here is a description of the settings of the <code>IlvSDMTreeViewRenderer</code>:
 * <p>
 * <table align="center" border="1">
 * <tr>
 * <td>
 * <tt><b>propertyNameIcon</b></tt>
 * </td>
 * <td>
 * <code>["url_of_the_icon"]</code>
 * </td>
 * <td>
 * The icon for the name of the SDM model property.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>propertyValueIcon</b></tt>
 * </td>
 * <td>
 * <code>["url_of_the_icon"]</code>
 * </td>
 * <td>
 * The icon for the value of the SDM model property.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>tagIcon</b></tt>
 * </td>
 * <td><code>["url_of_the_icon"]</code>
 * </td>
 * <td>
 * The icon used for each tag category of the SDM model.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>subgraphIcon</b></tt>
 * </td>
 * <td>
 * <code>["url_of_the_icon"]</code>
 * </td>
 * <td>
 * The icon for the subgraph elements of the SDM model.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>rootIcon</b></tt>
 * </td>
 * <td>
 * <code>["url_of_the_icon"]</code>
 * </td>
 * <td>
 * The icon for the root element of the tree.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>unknownObjectIcon</b></tt>
 * </td>
 * <td>
 * <code>["url_of_the_icon"]</code>
 * </td>
 * <td>
 * The default icon for the unknown object of the tree.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>sdmObjectIcon</b></tt>
 * </td>
 * <td>
 * <code>["url_of_the_icon"]</code>
 * </td>
 * <td>
 * The default icon for all the objects of the SDM model.
 * If <code>useGeneratedIcons</code> is <code>"true"</code> this property is not used.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>sdmGroupIcon</b></tt>
 * </td>
 * <td>
 * <code>["url_of_the_icon"]</code>
 * </td>
 * <td>
 * The icon for the groups of the SDM model.
 * If <code>useGeneratedIcons</code> is <code>"true"</code> this property is not used.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>showTreeLines</b></tt>
 * </td>
 * <td>
 * <code>["true"|"false"]</code>
 * </td>
 * <td>
 * Controls whether the dotted lines are visible in the tree.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>rootTreeLabel</b></tt>
 * </td>
 * <td>
 * <code>["text_of_the_label"]</code>
 * </td>
 * <td>
 * The label of the root node of the tree.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>subgraphTreeLabel</b></tt>
 * </td>
 * <td>
 * <code>["text_of_the_label"]</code>
 * </td>
 * <td>
 * The label of the subgraphs of the tree.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>generatedIcon</b></tt>
 * </td>
 * <td>
 * <code>["true"|"false"]</code>
 * </td>
 * <td>
 * Controls the use of auto-generated icons.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>showAllSdmProperties</b></tt>
 * </td>
 * <td>
 * <code>["true"|"false"]</code>
 * </td>
 * <td>
 * Controls the visibility of the SDM object properties in the tree.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>propertiesEditables</b></tt>
 * </td>
 * <td>
 * <code>["true"|"false"]</code>
 * </td>
 * <td>
 * Controls the editing capabilities of the SDM object properties.
 * </td>
 * </tr>
 * </table>
 * <p>
 * Here is a sample of CSS rules to configure this renderer:
 * <p>
 * <pre>
 * TreeView {
 *   propertyNameIcon     : url("icons/roles_class.gif");
 *   propertyValueIcon    : url("icons/interface.gif");
 *   tagIcon              : url("icons/class.gif");
 *   subgraphIcon         : url("icons/deployment_diagram.gif");
 *   rootIcon             : url("icons/package.gif");
 *   unknownObjectIcon    : url("icons/swimlane.gif");
 *   sdmObjectIcon        : url("icons/use_case.gif");
 *   sdmGroupIcon         : url("icons/class_diagram.gif");
 *   propertiesEditables  : "true";
 *   showTreeLines        : "false";
 *   rootTreeLabel        : "Workflow tree";
 *   subgraphTreeLabel    : "Sub workflow tree";
 *   generatedIcon        : "true";
 *   showAllSdmProperties : "false";
 * }
 * </pre>
 * <p>
 * The tree objects support another set of properties that match the elements of the <code>IlvSDMModel</code>.
 * <p>
 * <table align="center" border="1">
 * <tr>
 * <td>
 * <tt><b>hiddenPropertyList</b></tt>
 * </td>
 * <td>
 * <code>["property1,property2,..."]</code>
 * </td>
 * <td>
 * The list of the SDM object properties that are hidden in the tree (default: all the properties are visible if no CSS hidden rules are applied).
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>highlightedPropertyList</b></tt>
 * </td>
 * <td>
 * <code>["property1,property2,..."]</code>
 * </td>
 * <td>
 *  The list of the SDM object properties that use a highlighted color (defined by <code>highlightColor</code>).
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>readOnlyPropertyList</b></tt>
 * </td>
 * <td>
 * <code>["property1,property2,..."]</code>
 * </td>
 * <td>
 * The list of the SDM object properties that are not editable in the tree (default: all the properties are editable if no CSS read-only rules are applied).
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>showObjectProperies</b></tt>
 * </td>
 * <td>
 * <code>["true"|"false"]</code>
 * </td>
 * <td>
 * Controls the visibility of all the object properties.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>useGeneratedIcons</b></tt>
 * </td>
 * <td>
 * <code>["true"|"false"]</code>
 * </td>
 * <td>
 * Controls the use of auto-generated icons.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>defaultIcon</b></tt>
 * </td>
 * <td>
 * <code>["url_of_the_icon"]</code>
 * </td>
 * <td>
 * The URL of the loaded icons to display the object in the tree.<br>
 * If <code>useGeneratedIcons</code> is <code>"true"</code> this property is not used.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>highlightColor</b></tt>
 * </td>
 * <td>
 * <code>["#color_code_in_hexa"]</code>
 * </td>
 * <td>
 * The color used by the properties in the <code>highlightedPropertyList</code>.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>textColor</b></tt>
 * </td>
 * <td>
 * <code>["#default_color,#selection_color"]</code>
 * </td>
 * <td>
 * The color of the text in the tree.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>cellColor</b></tt>
 * </td>
 * <td>
 * <code>["#default_color,#selection_color"]</code>
 * </td>
 * <td>
 * The background color of the cells in the tree.
 * </td>
 * </tr>
 * <tr>
 * <td>
 * <tt><b>label</b></tt>
 * </td>
 * <td>
 * <code>["text_of_the_label"]</code>
 * </td>
 * <td>
 * The text label displayed next to the icon of the object.
 * </td>
 * </tr>
 * </table>
 * <p>
 * Here is a sample of CSS rules to configure the global settings of the model's nodes. It uses the CSS selector <code>treeView</code>:
 * <ul>
 * <li>the <code>"name,x,y"</code> properties are hidden,</li>
 * <li>the <code>"kind"</code> property is highlighted in red <code>#ff0000</code> and not editable,</li>
 * <li>the label displayed next to the icon of the node is the <code>name</code> property of the node,</li>
 * <li>the color of the text when the node is not selected is <code>#00FF00</code> and <code>#FF0000</code> when selected,</li>
 * <li>the background color of the labels when the node is not selected is <code>#00FFFF</code> and <code>#00FF00</code> when selected.</li>
 * </ul>
 * <pre>
 * node:treeView {
 *   hiddenPropertyList      : "name,x,y";
 *   highlightedPropertyList : "kind";
 *   readOnlyPropertyList    : "kind";
 *   showObjectProperies     : "true";
 *   generatedIcons          : "true";
 *   defaultIcon             : url("icons/generic_node.gif");
 *   highlightColor          : "#ff0000";
 *   textColor               : "#00FF00,#FF0000";
 *   cellColor               : "#00FFFF,#00FF00";
 *   label                   : @name;
 * }
 * <pre>
 * @see ilog.views.sdm.gui.tree.IlvSDMTree
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeModel
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellEditor
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellRenderer
 * @see ilog.views.sdm.gui.IlvSDMTreeView
 */
public class IlvSDMTreeViewRenderer extends IlvFilterSDMRenderer
{
  private String propertyNameIcon = null;
  private String propertyValueIcon = null;
  private String tagIcon = null;
  private String subgraphIcon = null;
  private String rootIcon = null;
  private String unknownObjectIcon = null;
  private String sdmObjectIcon = null;
  private String sdmGroupIcon = null;
  private boolean showTreeLines = true;
  private String rootTreeLabel = null;
  private String subgraphTreeLabel = null;
  private boolean generatedIcon = true;
  private boolean showAllSdmProperties = false;
  private boolean propertiesEditables = true;

  /**
   * Creates a new <code>treeView</code> renderer with a specified filtered renderer.
   * @param renderer The filtered renderer.
   */
  public IlvSDMTreeViewRenderer(IlvSDMRenderer renderer)
  {
    super(renderer);
  }

  /**
   * Creates a new <code>treeView</code> renderer with a <code>null</code> filtered renderer.
   */
  public IlvSDMTreeViewRenderer()
  {
    super(null);
  }

  /**
   * Sets the icon for the name of the SDM model property.
   * @param iconFile The string of the icon URL.
   */
  public void setPropertyNameIcon(String iconFile)
  {
    propertyNameIcon = iconFile;
  }

  /**
   * Returns the string of the icon URL for the name of the SDM model property.
   */
  public String getPropertyNameIcon()
  {
    return propertyNameIcon;
  }

  /**
   * Sets the icon for the value of the SDM model property.
   * @param iconFile The string of the icon URL.
   */
  public void setPropertyValueIcon(String iconFile)
  {
    propertyValueIcon = iconFile;
  }

  /**
   * Returns the string of the icon URL for the value of the SDM model property.
   */
  public String getPropertyValueIcon()
  {
    return propertyValueIcon;
  }

  /**
   * Sets the icon used for each tag category of the SDM model.
   * @param iconFile The string of the icon URL.
   */
  public void setTagIcon(String iconFile)
  {
    tagIcon = iconFile;
  }

  /**
   * Returns the string of the icon URL used for each tag category of the SDM model.
   */
  public String getTagIcon()
  {
    return tagIcon;
  }

  /**
   * Returns the string of the icon URL for the subgraph elements of the SDM model.
   * @param iconFile The string of the icon URL.
   */
  public void setSubgraphIcon(String iconFile)
  {
    subgraphIcon = iconFile;
  }

  /**
   * Returns the string of the icon URL for the subgraph elements of the SDM model.
   */
  public String getSubgraphIcon()
  {
    return subgraphIcon;
  }

  /**
   * Sets the icon for the root element of the tree.
   * @param iconFile The string of the icon URL.
   */
  public void setRootIcon(String iconFile)
  {
    rootIcon = iconFile;
  }

  /**
   * Returns the string of the icon URL for the root element of the tree.
   */
  public String getRootIcon()
  {
    return rootIcon;
  }

  /**
   * Sets the icon for the unknown object of the tree.
   * @param iconFile The string of the icon URL.
   */
  public void setUnknownObjectIcon(String iconFile)
  {
    unknownObjectIcon = iconFile;
  }

  /**
   * Returns the string of the icon URL for the unknown object of the tree.
   */
  public String getUnknownObjectIcon()
  {
    return unknownObjectIcon;
  }

  /**
   * Sets the icon for all the SDM objects of the SDM model.
   * @param iconFile The string of the icon URL.
   */
  public void setSdmObjectIcon(String iconFile)
  {
    sdmObjectIcon = iconFile;
  }

  /**
   * Returns the string of the icon URL for all the SDM objects of the SDM model.
   */
  public String getSdmObjectIcon()
  {
    return sdmObjectIcon;
  }

  /**
   * Sets the icon for the groups of the SDM model.
   * @param iconFile The string of the icon URL.
   */
  public void setSdmGroupIcon(String iconFile)
  {
    sdmGroupIcon = iconFile;
  }

  /**
   * Returns the string of the icon URL for the groups of the SDM model.
   */
  public String getSdmGroupIcon()
  {
    return sdmGroupIcon;
  }

  /**
   * Sets the visibility of the <code>JTree</code> dotted lines.
   * @param visible The visibility flag.
   */
  public void setShowTreeLines(boolean visible)
  {
    showTreeLines = visible;
  }

  /**
   * Returns the state of the visibility of the <code>JTree</code> dotted lines.
   */
  public boolean getShowTreeLines()
  {
    return showTreeLines;
  }

  /**
   * Sets the label of the root node of the tree.
   * @param label The label.
   */
  public void setRootTreeLabel(String label)
  {
    rootTreeLabel = label;
  }

  /**
   * Returns the value of the root node label of the tree.
   */
  public String getRootTreeLabel()
  {
    return rootTreeLabel;
  }

  /**
   * Sets the label of the subgraphs of the tree.
   * @param label The label.
   */
  public void setSubgraphTreeLabel(String label)
  {
    subgraphTreeLabel = label;
  }

  /**
   * Returns the value of the subgraph label of the tree.
   */
  public String getSubgraphTreeLabel()
  {
    return subgraphTreeLabel;
  }

  /**
   * Sets the use of auto-generated icons.
   * @param val The flag.
   */
  public void setGeneratedIcon(boolean val)
  {
    generatedIcon = val;
  }

  /**
   * Returns the state of auto-generated icons flag.
   */
  public boolean getGeneratedIcon()
  {
    return generatedIcon;
  }

  /**
   * Sets the visibility of the SDM object properties in the tree.
   * @param val The flag.
   */
  public void setShowAllSdmProperties(boolean val)
  {
    showAllSdmProperties = val;
  }

  /**
   * Returns the state of the visibility of the SDM object properties in the tree.
   */
  public boolean getShowAllSdmProperties()
  {
    return showAllSdmProperties;
  }

  /**
   * Sets the editing capabilities of the SDM object properties.
   * @param val The flag.
   */
  public void setPropertiesEditables(boolean val)
  {
    propertiesEditables = val;
  }

  /**
   * Returns the editing capabilities of the SDM object properties.
   */
  public boolean getPropertiesEditables()
  {
    return propertiesEditables;
  }
}
