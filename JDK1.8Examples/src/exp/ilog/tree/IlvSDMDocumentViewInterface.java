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
 * $Id: IlvSDMDocumentViewInterface.java,v 1.10 2001/08/03 16:56:59 gdigugli Exp $
 *
 */

package exp.ilog.tree;


import javax.swing.*;
import ilog.views.sdm.*;
import ilog.views.sdm.gui.event.*;
import ilog.views.swing.*;
import java.awt.*;
import java.util.*;
import java.net.*;
import ilog.views.*;

/**
 * This interface must be implemented by the document view of the GUI using the SDM package.
 * <br>
 * The document view is a visible representation of a document in the GUI.
 * <br>
 * It manages the editing of the document and the user's interaction. The view is notified of the document changes to maintain the consistency of the various views.
 * <br>
 * It contains methods used by the actions of the GUI control tools ({@link ilog.views.sdm.gui.IlvSDMMenuBar} and {@link ilog.views.sdm.gui.IlvSDMToolBar}).
 */

public interface IlvSDMDocumentViewInterface extends IlvSDMActiveDocumentViewListener
{
  /**
   * Returns <code>true</code> if this document can be modified.
   */
  public boolean isEditable();

  /**
   * Sets the editable flag of this document.
   *
   * @param editable If <code>true</code>,
   * and if the SDM data model is also editable, then the
   * user will be able to edit the document. Otherwise, the editing
   * interactions and commands should be disabled.
   */
  public void setEditable(boolean editable);

  /**
   * Closes the view.
   */
  public void    close();

  /**
   * Prints the view.
   */
  public void    print();

  /**
   * Activates the antialiasing of the view.
   * @see #isAntialisingEnabled
   */
  public void    antialiasing();

  /**
   * Returns <code>true</code> if the antialiasing is in use, and <code>false</code> otherwise.
   * @see #antialiasing
   */
  public boolean isAntialisingEnabled();

  /**
   * Returns the associated document.
   */
  public IlvSDMDocument getDocument();

  /**
   * Sets the document view title.
   * @param title The new document view title.
   * @see #getTitle
   */
  public void setTitle(String  title);

  /**
   * Returns the view title.
   * @see #setTitle
   */
  public String getTitle();

  /**
   * Returns the <code>IlvSDMView</code> of the document view if it is possible, and <code>null</code> otherwise.
   */
  public IlvSDMView getView();

  /**
   * Returns the <code>IlvSDMModel</code> of the <code>IlvSDMEngine</code> associated with the document view.
   */
  public IlvSDMModel getModel();

  /**
   * Returns <code>true</code> if the instance of the view is shared among multiple documents, and <code>false</code> otherwise.
   */
  public boolean isShared();

  /**
   * Returns the <code>JInternalFrame</code> used to visualize the document view.
   * <br>
   * This method is useful for the MDI applications.
   */
  public IlvSDMFrameInterface getFrame();

  /**
   * Called before the document view is closed.
   * It is used when unregistering listeners.
   */
  public void beforeClose();

  /**
   * Allows to change the document which is diplayed by the view.
   * @param document The new document.
   */
  public void setDocument(IlvSDMDocument document);
}
