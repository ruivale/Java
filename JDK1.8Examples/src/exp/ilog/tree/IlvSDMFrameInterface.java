/*
 *  Copyright (C) 2001 by ILOG.
 *  All rights reserved.
 *
 * This source code is an addition to the ILOG JViews Reference Manual
 * delivered  with the JViews library.
 * It is supplied as an example to show you how to code with JViews.
 * Feel free to use, copy or modify it within the framework of your
 * JViews license agreement.
 */
/*
 * $Id: IlvSDMFrameInterface.java,v 1.8 2001/09/17 17:11:43 gdigugli Exp $
 *
 */


package exp.ilog.tree;


import java.beans.*;
import java.awt.*;
import javax.swing.*;

/**
 * This interface describes the basic features of a frame that can contain
 * a document view.
 */

public interface IlvSDMFrameInterface
{
  /**
   * Sets the title of the <code>IlvSDMFrameInterface</code> implementation class.
   * @param title The title.
   */
  public void setTitle(String title);

  /**
   * Returns the title of the implementation class of the <code>IlvSDMFrameInterface</code>.
   */
  public String getTitle();

  /**
   * The implementation of the <code>IlvSDMFrameInterface</code> can be closed by a user action.
   * @param b If <code>true</code> the frame can be closed, and <code>false</code> otherwise.
   */
  public void setClosable(boolean b);

  /**
   * The implementation of the <code>IlvSDMFrameInterface</code> can be maximized by a user action.
   * @param b If <code>true</code> the frame can be maximized, and <code>false</code> otherwise.
   */
  public void setMaximizable(boolean b);

  /**
   * The implementation of the <code>IlvSDMFrameInterface</code> can be iconified by a user action.
   * @param b If <code>true</code> the frame can be iconified, and <code>false</code> otherwise.
   */
  public void setIconifiable(boolean b);

  /**
   * The <code>JInternalFrame</code> can be resized by a user action.
   * @param b If <code>true</code>, the frame can be resized.
   */
  public void setResizable(boolean b);

  /**
   * Resizes this component so that it has width <code>d.width</code> and height <code>d.height</code>.
   * @param width The new width of the frame.
   * @param height The new height of the frame.
   */
  public void setSize(int width, int height);

  /**
   * If <code>true</code>, the component paints every pixel within its bounds.
   * Otherwise, the component may not paint some or all of its pixels,
   * allowing the underlying pixels to show through.
   * The default value of this property is <code>false</code> for JComponent.
   * However, the default value for this property on most standard JComponent subclasses
   * (such as JButton and JTree) is look-and-feel dependent.
   * @param isOpaque If <code>true</code> the frame is opaque, and <code>false</code> otherwise.
   */
  public void setOpaque(boolean isOpaque);


  /**
   * Sets the background color of this component.
   * @param bg The new background color.
   */
  public void setBackground(Color bg);

  /**
   * Sets the image to be displayed in the minimized icon for this frame.
   * Not all platforms support the concept of minimizing a window.
   * @param image The icon image to be displayed.
   * If <code>null</code>, the icon image is set to the default image, which may vary with the platform.
   */
  public void setIconImage(Image image);

  /**
   * Returns the content pane.
   */
  public Container getContentPane();

  /**
   * Makes the component visible or invisible.
   * @param aFlag If <code>true</code>, it makes the component visible.
   */
  public void setVisible(boolean aFlag);

  /**
   * If the method is called with <code>true</code>, the frame is closed.
   * @param b If <code>true</code>, the frame is closed.
   */
  public void setClosed(boolean b);

  /**
   * Returns the implementation of the <code>IlvSDMDocumentViewInterface</code> associated with the frame.
   */
  public IlvSDMDocumentViewInterface getDocumentView();

  /**
   * Sets the implementation of the <code>IlvSDMDocumentViewInterface</code> associated with the frame.
   * @param documentView The document view.
   */
  public void setDocumentView(IlvSDMDocumentViewInterface documentView);

  /**
   * The subcomponents of the implementation of <code>IlvSDMDocumentViewInterface</code> are shown at their preferred size.
   */
  public void pack();

  /**
   * Sets the bounds of the frame.
   */
  public void setBounds(Rectangle r);

  /**
   * Returns the bounds of the frame.
   */
  public Rectangle getBounds();
}
