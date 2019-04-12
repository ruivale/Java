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
 * $Id: IlvSDMGrapherDocumentViewInterface.java,v 1.4 2001/08/07 09:23:40 mariasin Exp $
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

public interface IlvSDMGrapherDocumentViewInterface extends IlvSDMDocumentViewInterface
{
  /**
   * Sets the zoom to its initial level.
   * @see #zoomOut
   * @see #zoomIn
   * @see #zoomBox
   */
  public void    zoomInit();

  /**
   * Zooms in to the view.
   * @see #zoomInit
   * @see #zoomOut
   * @see #zoomBox
   */
  public void    zoomIn();

  /**
   * Zooms out of the view.
   * @see #zoomInit
   * @see #zoomBox
   * @see #zoomIn
   */
  public void    zoomOut();

  /**
   * Activates the zoom box interactor.
   * @see #zoomInit
   * @see #zoomBox
   * @see #zoomOut
   */
  public void    zoomBox();

  /**
   * Activates the pan interactor.
   */
  public void    panInteractor();

  /**
   * Returns <code>true</code> if the pan interactor is in use, and <code>false</code> otherwise.
   */
  public boolean isPanInteractorEnabled();

  /**
     * Activates the expand/collapse interactor.
     * @see #isExpandInteractorEnabled
     */
  public void    expandInteractor();

  /**
   * Returns <code>true</code> if the expand/collapse interactor is in use, and <code>false</code> otherwise.
   * @see #expandInteractor
   */
  public boolean isExpandInteractorEnabled();

  /**
   * Activates the selection interactor.
   * @see #isSelectInteractorEnabled
   */
  public void    selectInteractor();

  /**
   * Returns <code>true</code> if the selection interactor is in use, and <code>false</code> otherwise.
   * @see #selectInteractor
   */
  public boolean isSelectInteractorEnabled();

  /**
   * Deactivates all the interactors in use.
   */
  public void    activate();

  /**
   * Activates the edit label interactor.
   * @see #isEditLabelInteractorEnabled
   */
  public void    editLabelInteractor();

  /**
   * Returns <code>true</code> if the edit label interactor is in use, and <code>false</code> otherwise.
   * @see #editLabelInteractor
   */
  public boolean isEditLabelInteractorEnabled();

  /**
   * Sets the zoom level to see all the graphic components of the document in the view.
   */
  public void    fitToContent();

  /**
   * Activates the node layout.
   * @see #isNodeLayoutEnabled
   */
  public void    nodeLayout();

  /**
   * Returns <code>true</code> if the node layout is enabled, and <code>false</code> otherwise.
   * @see #nodeLayout
   */
  public boolean isNodeLayoutEnabled();

  /**
   * Activates the link layout.
   * @see #isLinkLayoutEnabled
   */
  public void    linkLayout();

  /**
   * Returns <code>true</code> if the link layout is enabled, and <code>false</code> otherwise.
   * @see #linkLayout
   */
  public boolean isLinkLayoutEnabled();

  /**
   * Activates the label layout.
   * @see #isLabelLayoutEnabled
   */
  public void    labelLayout();

  /**
   * Returns <code>true</code> if the label layout is enabled, and <code>false</code> otherwise.
   * @see #labelLayout
   */
  public boolean isLabelLayoutEnabled();

  /**
   * Enables the grid of the document view.
   * @see #isGridEnabled
   */
  public void    grid();

  /**
   * Returns <code>true</code> if the grid is enabled, and <code>false</code> otherwise.
   * @see #grid
   */
  public boolean isGridEnabled();

  /**
   * Sets up the page format for printing.
   */
  public void pageSetup();

  /**
   * Prints the view in one page.
   */
  public void printInOnePage();
}
