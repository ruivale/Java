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
 * $Id: IlvSDMApplicationInterface.java,v 1.23 2001/09/21 13:45:40 gdigugli Exp $
 *
 */

package exp.ilog.tree;

import java.net.URL;
import java.awt.event.*;
import javax.swing.*;
import ilog.views.sdm.gui.event.*;


/**
 * This interface must be implemented by the main program of the GUI using the SDM package.
 * <br>
 * It contains some methods used by the actions of the GUI control tools:
 * <ul>
 * <li>{@link ilog.views.sdm.gui.IlvSDMMenuBar}</li>
 * <li>{@link ilog.views.sdm.gui.IlvSDMToolBar}</li>
 * </ul>
 * Currently, the <code>IlvSDMApplicationInterface</code> is implemented by the main program.
 */

public interface IlvSDMApplicationInterface extends IlvSDMActiveDocumentViewListener
{
  /**
   * Returns <code>true</code> if the implementation of the application is an <code>Applet</code>, and <code>false</code> otherwise.
   */
//  public boolean isApplet();

  /**
   * Returns the application title.
   */
//  public String getTitle();

  /**
   * Returns the application controller.
   */
  public IlvSDMDocumentController getDocumentController();

  /**
   * Exits the application.
   */
//  public void    exit();

  /**
   * Opens an XML data file in the application.
   */
//  public void    openXML();

  /**
   * Opens a CSS file and applies it to the current engine.
   * @see #isOpenCSSEnabled
   */
//  public void    openCSS();

  /**
   * Opens an XML data file in the application.
   * @param filename The name of the XML file.
   */
//  public void    openXML(String filename);

  /**
   * Opens a CSS file and applies it to the current engine.
   * @param filename The name of the CSS file.
   */
//  public void    openCSS(String filename);

  /**
   * Saves the XML of the data model of the current engine.
   */
//  public void    saveXMLAs();

  /**
   * Saves the contents of the current grapher view as an IVL file.
   */
//  public void    saveIVL();

  /**
   * Saves the contents of the current grapher view as an SVG file.
   */
//  public void    saveSVG();

  /**
   * Saves the contents of the current grapher view as a PNG bitmap file.
   */
//  public void    savePNG();

  /**
   * Returns <code>true</code> if the <code>openCSSAction</code> is enabled, and <code>false</code> otherwise.
   * @see #openCSS
   */
//  public boolean isOpenCSSEnabled();

  /**
   * Opens a new document in the application.
   */
  public void    newDocument();

  /**
     * Opens a new grapher view of the document in the application.
     */
//  public void    newGrapherView();

  /**
   * Opens an overview of the current document.
   * @see #hideOverview
   */
//  public void    showOverview();

  /**
   * Hides the overview of the current document.
   * @see #hideOverview
   */
//  public void    hideOverview();

  /**
   * Opens an inspector of the current document.
   * @see #hideInspector
   */
//  public void showInspector();

  /**
   * Hides the inspector of the current document.
   * @see #showInspector
   */
//  public void hideInspector();

  /**
   * Opens a table view of the current document.
   * @param tag The XML tag to filter the table.
   * @see #hideTableView
   */
//  public void    showTableView(String tag);

  /**
   * Hides the table view of the current document.
   * @see #showTableView
   */
//  public void    hideTableView();

  /**
   * Opens a tree view of the current document.
   * @see #hideTreeView
   */
  public void    showTreeView();

  /**
   * Hides the tree view of the current document.
   * @see #showTreeView
   */
//  public void    hideTreeView();

  /**
   * Takes a snapshot from the grapher view of the active document.
   */
//  public void    takeSnapshot();

  /**
   * Makes the toolbar visible.
   * @param toolBar The toolbar to control.
   * @see #isToggleToolBarEnabled
   */
//  public void    toggleToolBar(JToolBar toolBar);

  /**
   * Returns <code>true</code> if the toolbar is visible, and <code>false</code> otherwise.
   * @param toolBar The toolbar to control.
   * @see #toggleToolBar
   */
//  public boolean isToggleToolBarEnabled(JToolBar toolBar);

  /**
   * Makes the status bar visible.
   * @see #isToggleStatusBarEnabled
   */
//  public void    toggleStatusBar();

  /**
   * Returns <code>true</code> if the status bar is visible, and <code>false</code> otherwise.
   * @see #toggleStatusBar
   */
//  public boolean isToggleStatusBarEnabled();

  /**
   * Displays a 'Help' dialog box.
   */
//  public void    help();

  /**
   * Displays an 'About' dialog box.
   */
//  public void    about();

  /**
   * Displays an 'Author' dialog box.
   */
//  public void    authors();

  /**
   * Displays the internal frames with a cascade layout.
   */
//  public void    cascadeFrames();

  /**
   * Displays the internal frames with a tile layout.
   */
//  public void    tileFrames();

  /**
   * Displays a confirmation dialog box.
   * Returns <code>JOptionPane.OK_OPTION</code> if the user chooses the "Yes" button, <code>JOptionPane.NO_OPTION</code>
   * if he chooses the "No" button, and <code>JOptionPane.CANCEL_OPTION</code> if he chooses the "Cancel" button.
   * @param question The question asked in the dialog box, in string format.
   */
//  public int confirmDialog(String question);

  /**
   * Returns the default style sheet file name in string format.
   */
  public String  getDefaultStylesheet();

  /**
   * Used to initialize the application contextual popup menu.
   * @param e An event indicating that a mouse action occurred in a component.
   */
//  public void initContextualPopup(MouseEvent e);

  /**
   * Returns the top frame of the application.
   */
  public JFrame getMainApplicationFrame();

  /**
   * Returns the document base when {@link ilog.views.sdm.gui.IlvSDMApplicationInterface#isApplet} return <code>true</code>.
   */
//  public URL getDocumentBase();
}
