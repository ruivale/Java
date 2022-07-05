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
 * $Id: IlvSDMTreeViewRendererBeanInfo.java,v 1.4 2001/09/07 12:46:59 mariasin Exp $
 *
 */
package exp.ilog.tree;

import java.beans.*;
import ilog.views.sdm.beans.editor.*;

/**
 * BeanInfo for {@link ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer}.
 */
public class IlvSDMTreeViewRendererBeanInfo extends SimpleBeanInfo
{
  /**
   * Creates a new <code>IlvSDMTreeViewRendererBeanInfo</code>.
   */
  public IlvSDMTreeViewRendererBeanInfo()
  {
    super();
  }

  /**
   * Returns the property descriptors.
   */
  public PropertyDescriptor[] getPropertyDescriptors()
  {
    PropertyDescriptor pds[] = null;
    try {
      pds = new PropertyDescriptor[] {
        construct("propertyNameIcon", URLStringPropertyEditor.class, "the icon for the NAME of the SDM model property"),
        construct("propertyValueIcon", URLStringPropertyEditor.class, "the icon for the VALUE of the SDM model property"),
        construct("tagIcon", URLStringPropertyEditor.class, "the icon used for each tag category of the SDM model"),
        construct("subgraphIcon", URLStringPropertyEditor.class, "the icon for the subgraph elements of the SDM model"),
        construct("rootIcon", URLStringPropertyEditor.class, "the icon for the root element of the tree"),
        construct("unknownObjectIcon", URLStringPropertyEditor.class, "the icon for the unknown object of the tree"),
        construct("sdmObjectIcon", URLStringPropertyEditor.class, "the default icon for all the objects of the SDM model"),
        construct("sdmGroupIcon", URLStringPropertyEditor.class, "the icon for the groups of the SDM model"),
        construct("showTreeLines", null, "controls whether the dotted lines are visible in the tree"),
        construct("rootTreeLabel", null, "the label of the root node of the tree"),
        construct("subgraphTreeLabel", null, "the label of the subgraph of the tree"),
        construct("generatedIcon", null, "controls the use of auto-generated icons"),
        construct("showAllSdmProperties", null, "controls the visibility of the SDM object properties in the tree"),
        construct("propertiesEditables", null, "controls the editing capabilities of the SDM object properties"),
      };
    }
    catch (IntrospectionException e) {
      e.printStackTrace();
    }
    return pds;
  }

  PropertyDescriptor construct(String name, Class editor, String displayName) throws IntrospectionException
  {
    PropertyDescriptor pd = new PropertyDescriptor(name, IlvSDMTreeViewRenderer.class);
    if (editor != null) {
      pd.setPropertyEditorClass(editor);
    }
    pd.setShortDescription(displayName);
    return pd;
  }


  /**
   * Returns the additional BeanInfos.
   */
  public BeanInfo [] getAdditionalBeanInfo()
  {
    try {
      return new BeanInfo [] {
        Introspector.getBeanInfo(IlvSDMTreeViewRenderer.class.getSuperclass())
      };
    }
    catch (IntrospectionException ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
