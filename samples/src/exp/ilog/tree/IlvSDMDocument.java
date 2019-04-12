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

package exp.ilog.tree;


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ilog.views.io.*;
import ilog.views.svg.*;
import ilog.views.sdm.*;
import ilog.views.sdm.event.*;
import ilog.views.sdm.swing.*;
import ilog.views.swing.*;
import ilog.views.event.*;
import java.awt.*;
import java.util.*;
import java.net.*;
import java.beans.*;

import ilog.views.sdm.gui.event.*;
import ilog.views.sdm.gui.util.*;
import ilog.views.sdm.gui.action.*;
import ilog.views.sdm.event.*;

/**
 * <code>IlvSDMDocument</code> is a non-visible entity in an application. It contains an <code>IlvSDMEngine</code> instance.
 * It references the XML and CSS files, an undo manager, and a clipboard.
 * <code>IlvSDMDocument</code> manages all the attached document views and it is notified by the document controller of the active document changes.
 * @see ilog.views.sdm.IlvSDMEngine
 * @see IlvSDMUndoManager
 */
public class IlvSDMDocument implements SDMModelListener, SDMPropertyChangeListener, IlvSDMActiveDocumentViewListener, ManagerSelectionListener, IlvSDMStylesheetChangedListener
{
  private Vector documentViews = new Vector(3);
  private IlvSDMDocumentViewInterface mainView = null;
  private IlvSDMDocumentViewInterface activeView = null;
  private IlvSDMEngine engine;
  private Vector messagesListenerVector = new Vector(5);
  private IlvSDMUndoManager undoManager;
  private boolean isDocumentChanged = false;
  private Vector stylesheets = new Vector(3);
  private String documentFilename = "";
  private int documentId = 0;
  private IlvSDMDocumentController controller;
  private boolean isDefaultName = true;
  private boolean isClipBoardEmpty = true;
  private boolean isSelectionEmpty = true;
  private boolean isSelectionMultiple = false;
  private boolean editable = true;
  private URL baseURL;

  /**
   * Creates an <code>IlvSDMDocument</code> attached to the document controller in the parameter.
   * @param controller The document controller to which the document is attached.
   */
  public IlvSDMDocument(IlvSDMDocumentController controller)
  {
    this(controller, null);
  }

  /**
   * Creates an <code>IlvSDMDocument</code> attached to the document controller in the parameter,
   * and uses the specified base URL to resolve relative URLs.
   * @param controller The document controller to which the document is attached.
   * @param baseURL The base URL.
   */
  public IlvSDMDocument(IlvSDMDocumentController controller, URL baseURL)
  {
    this.controller = controller;
    this.baseURL = baseURL;
    this.documentId = this.controller.getNewDocumentId();
    this.documentFilename = "Document " + this.documentId;
    init();
    getEngine().getModel().addSDMModelListener(this);
    getEngine().getModel().addSDMPropertyChangeListener(this);
    this.controller.addDocument(this);
    addMessageListener(this.controller);
  }

  /**
   *
   */
  private void init()
  {
    this.engine = new IlvSDMEngine() {
        public void loadData()
        {
          fireBeginStats();
          super.loadData();
          fireEndStats();
        }
      };
    String defaultCSS = this.controller.getDefaultStylesheet();
    if(defaultCSS != null) {
      try {
        IlvSDMUtils.openCSS(defaultCSS, this.engine, this.controller.getApplication().getClass(), getBaseURL());
      }
      catch (Exception ex) {
        System.err.println(ex);
      }
    }
    this.undoManager = new IlvSDMUndoManager(this.engine);
  }

  /**
   * Returns the base URL used by this document to resolve relative URLs.
   */
  public URL getBaseURL()
  {
    return this.baseURL;
  }

  /**
   * Sets the base URL used by this document to resolve relative URLs.
   * @param url The new base URL.
   */
  public void setBaseURL(URL url)
  {
    this.baseURL = url;
  }

  /**
   * Returns the ID of the document.
   */
  public int getDocumentId()
  {
    return this.documentId;
  }

  /**
   * Returns the name of the document file.
   * @see #setFilename
   */
  public String getFilename()
  {
    return this.documentFilename;
  }

  /**
   * Gets the name of the document file.
   * @param filename The new filename.
   * @see #getFilename
   */
  public void setFilename(String filename)
  {
    this.documentFilename = filename;
    updateViewsTitle(filename);
  }

  private void updateViewsTitle(String title)
  {
    for(int i=0; i<this.documentViews.size(); i++) {
      ((IlvSDMDocumentViewInterface) this.documentViews.elementAt(i)).setTitle(title);
    }
  }

  /**
   * Sets the editable flag of this document.
   *
   * @param editable If <code>true</code>,
   * and if the SDM data model is also editable, then the
   * user will be able to edit the document. Otherwise, the editing
   * interactions and commands should be disabled.
   */
  public void setEditable(boolean editable)
  {
    this.editable = editable;
    for (int i=0; i<this.documentViews.size(); i++) {
      if (((IlvSDMDocumentViewInterface) this.documentViews.elementAt(i)).getView() != null) {
        ((IlvSDMDocumentViewInterface) this.documentViews.elementAt(i)).getView().setDropEnabled(editable);
      }
      ((IlvSDMDocumentViewInterface) this.documentViews.elementAt(i)).setEditable(editable);
    }
    getDocumentController().fireActiveDocumentViewChanged(new ActionEvent(this,0,null));
  }

  /**
   * Returns <code>true</code> if the editable flag of this document
   * is <code>true</code>, and if the SDM data model is editable.
   */
  public boolean isEditable()
  {
    return this.editable && this.engine.getModel().isEditable();
  }

  /**
   * Returns the vector of all the document views associated with the document.
   * @see #addDocumentView
   * @see #removeDocumentView
   * @see #getMainDocumentView
   * @see #getActiveDocumentView
   * @see #setActiveDocumentView
   */
  public Vector getDocumentViews()
  {
    return this.documentViews;
  }

  /**
   * Returns the first added document view of the document.
   * @see #addDocumentView
   * @see #removeDocumentView
   * @see #getActiveDocumentView
   * @see #setActiveDocumentView
   * @see #getDocumentViews
   */
  public IlvSDMDocumentViewInterface getMainDocumentView()
  {
    return this.mainView;
  }

  /**
   * Sets the main document view of the document.
   * @param mainDocumentView The new main document view.
   * @see #addDocumentView
   * @see #removeDocumentView
   * @see #getMainDocumentView
   * @see #getActiveDocumentView
   * @see #getDocumentViews
   */
  public void setMainDocumentView(IlvSDMDocumentViewInterface mainDocumentView)
  {
    this.mainView = mainDocumentView;
  }

  /**
   * Returns the active document view of the document.
   * @see #addDocumentView
   * @see #removeDocumentView
   * @see #getMainDocumentView
   * @see #setActiveDocumentView
   * @see #getDocumentViews
   */
  public IlvSDMDocumentViewInterface getActiveDocumentView()
  {
    return this.activeView;
  }

  /**
   * Sets the active document view of the document.
   * @param documentView The new active document view.
   * @see #addDocumentView
   * @see #removeDocumentView
   * @see #getMainDocumentView
   * @see #getActiveDocumentView
   * @see #getDocumentViews
   */
  public void setActiveDocumentView(IlvSDMDocumentViewInterface documentView)
  {
    this.activeView = documentView;
  }

  /**
   * Adds a new document view to the document.
   * @param documentView The document view to add.
   * @see #removeDocumentView
   * @see #getMainDocumentView
   * @see #getActiveDocumentView
   * @see #setActiveDocumentView
   * @see #getDocumentViews
   */
  public void addDocumentView(IlvSDMDocumentViewInterface documentView)
  {
    if(this.documentViews.size() == 0) {
      this.mainView =  documentView;
      this.activeView = documentView;
    }
    if (documentView.getView() != null)
      documentView.getView().addInteractorListener(getDocumentController());
    this.controller.addActiveDocumentViewListener(documentView);
    this.documentViews.add(documentView);
  }

  /**
   * Removes a document view of the document.
   * @param documentView The document view to remove.
   * @see #addDocumentView
   * @see #getMainDocumentView
   * @see #getActiveDocumentView
   * @see #setActiveDocumentView
   * @see #getDocumentViews
   */
  public void removeDocumentView(IlvSDMDocumentViewInterface documentView)
  {
    if (documentView.getView() != null)
      documentView.getView().removeInteractorListener(getDocumentController());
    this.controller.removeActiveDocumentViewListener(documentView);
    this.documentViews.remove(documentView);
    if (this.activeView == documentView ) this.activeView = null;
    if (this.mainView == documentView ) this.mainView = null;
  }

  /**
   * Returns the <code>IlvSDMEngine</code> associated with the document.
   */
  public IlvSDMEngine getEngine()
  {
    return this.engine;
  }

  /**
   * Returns the document controller to which the document is attached.
   */
  public IlvSDMDocumentController getDocumentController()
  {
    return this.controller;
  }

  /**
   * Adds an <code>IlvSDMMessageListener</code> that is notified of the document user messages.
   * @param listener The listener to add.
   * @see #removeMessageListener
   */
  public void addMessageListener(IlvSDMMessageListener listener)
  {
    this.messagesListenerVector.add(listener);
  }

  /**
   * Removes a notified <code>IlvSDMMessageListener</code>.
   * @param listener The listener to remove.
   * @see #addMessageListener
   */
  public void removeMessageListener(IlvSDMMessageListener listener)
  {
    this.messagesListenerVector.remove(listener);
  }

  /**
   *
   */
  private void fireMessageEvent(String message)
  {
    for(int i=0; i<this.messagesListenerVector.size(); i++) {
      ((IlvSDMMessageListener) this.messagesListenerVector.elementAt(i)).messagePerformed(new IlvSDMMessageEvent(message,this));
    }
  }

  /**
   *
   */
  private void fireBeginStats()
  {
    for(int i=0; i<this.messagesListenerVector.size(); i++) {
      ((IlvSDMMessageListener) this.messagesListenerVector.elementAt(i)).messagePerformed(new IlvSDMMessageEvent(true,this));
    }
  }

  /**
   *
   */
  private void fireEndStats()
  {
    for(int i=0; i<this.messagesListenerVector.size(); i++) {
      ((IlvSDMMessageListener) this.messagesListenerVector.elementAt(i)).messagePerformed(new IlvSDMMessageEvent(false,this));
    }
  }

  /**
   * A debug method to print on <code>System.err</code> the document and the document views.
   */
  void printAllViews()
  {
    System.err.println("<document title='" + getFilename() + "'>");
    for(int i=0; i<this.documentViews.size(); i++) {
      System.err.println("\t<view title='" + ((IlvSDMDocumentViewInterface)this.documentViews.elementAt(i)).getTitle() + "'/>");
    }
    System.err.println("</document>");
  }

  /**
   * Returns <code>true</code> if the listener accepts to be closed, and <code>false</code> otherwise.
   * @param frame The frame where the view is instantiated.
   */
  public boolean acceptClose(IlvSDMFrameInterface frame)
  {
/*
    boolean isCloseAccepted = true;

    if (frame.getDocumentView() == getMainDocumentView() && isDocumentChanged()) {
      int chooser = getDocumentController().getApplication().confirmDialog(IlvSDMMessages.getMessage(IlvSDMMessages.MESSAGE_SaveTheChanges) +
                                                                           "\n" + getFilename() + " ?");
      if(chooser == JOptionPane.OK_OPTION) {
        if (isDefaultName())
          controller.getApplication().saveXMLAs();
        else
          saveXML();
      }

      if (chooser == JOptionPane.CANCEL_OPTION || (chooser == JOptionPane.OK_OPTION  && isDocumentChanged())) {
        return false;
      }
    }

    if (frame.getDocumentView() == getMainDocumentView()) {
      IlvSDMDocument[] docArray = this.controller.getAllDocuments();
      int docIndex = 0;
      for (int i=0; i<docArray.length; i++) {
        if (docArray[i] == frame.getDocumentView().getDocument()) docIndex = i;
      }

      IlvSDMDocument nextDoc = null;

      if (docIndex == 0 && docArray.length > 1) {
        nextDoc = docArray[1];
      }
      else if (docIndex == docArray.length-1 && docArray.length > 1) {
        nextDoc = docArray[docIndex-1];
      }
      else if (docArray.length > 1) {
        nextDoc = docArray[docIndex+1];
      }

      if (nextDoc != null) {
        this.controller.setActiveDocument(nextDoc);
      }

      Vector documentViewsCopy = new Vector(this.documentViews);


      for(int i=0; i<documentViewsCopy.size(); i++) {
        if((IlvSDMDocumentViewInterface) documentViewsCopy.elementAt(i) != frame.getDocumentView()) {
          if (((IlvSDMDocumentViewInterface) documentViewsCopy.elementAt(i)).isShared() && nextDoc != null) {
            ((IlvSDMDocumentViewInterface) documentViewsCopy.elementAt(i)).setDocument(nextDoc);
          }
          else {
            ((IlvSDMDocumentViewInterface) documentViewsCopy.elementAt(i)).getFrame().setClosed(true);
          }
        }
      }
      frame.getDocumentView().beforeClose();
      this.controller.removeDocument(this);
      this.undoManager = null;
      this.engine = null;
    }
    else {
      frame.getDocumentView().getDocument().removeDocumentView(frame.getDocumentView());
      this.controller.removeActiveDocumentViewListener(frame.getDocumentView());
      frame.getDocumentView().beforeClose();
    }
*/

    return true;
  }

  /**
   * This method is performed by the <code>IlvSDMDocumentController</code> when the active document view changes.
   * @param event The AWT event that can be the source of the update.
   */
  public void activeDocumentViewChanged(AWTEvent event) {}

  /**
   * Opens and loads a CSS file in the <code>IlvSDMEngine</code> of the document.
   * @param filename The file name of the style sheet to be loaded.
   */
  public void openCSS(String filename)
  {
    boolean changed = this.isDocumentChanged;
    try {
      IlvSDMUtils.openCSS(filename, getEngine(), this.getClass(), getBaseURL());
    }
    catch (Exception ex) {
      IlvSDMUtils.showException(ex, getMainDocumentView().getView());
    } finally {
      this.isDocumentChanged = changed;
    }
  }

  /**
   * Opens and loads an XML data file in the <code>IlvSDMEngine</code> of the document.
   * @param filename The file name of the XML data file to be loaded.
   */
  public void openXML(String filename)
  {
    try {
      IlvSDMUtils.openXML(filename, getEngine(), this.getClass(), getBaseURL());
    }
    catch (Exception ex) {
      IlvSDMUtils.showException(ex, getMainDocumentView().getView());
    }
    this.undoManager.discardAllEdits();
    this.setFilename(filename);

//     if (isFileReadOnly(filename))
//       setEditable(false);


    this.isDocumentChanged = false;
    this.isDefaultName = false;
    getDocumentController().fireActionStateChangedEvent();
  }

  private boolean isFileReadOnly(String filename)
  {
    return !(new File(filename)).canWrite();
  }

  /**
   * Saves the <code>IlvSDMEngine</code> contents in the opened XML file.
   * @see #isSaveXMLEnabled
   */
  public void saveXML()
  {
/*
    if(this.isDefaultName == true) {
      getDocumentController().getApplication().saveXMLAs();
      return;
    }

    try {
      IlvSDMUtils.saveXML(getFilename(), getEngine());
    }
    catch (Exception ex) {
      IlvSDMUtils.showException(ex, getMainDocumentView().getView());
    }
    this.isDocumentChanged = false;
    getDocumentController().fireActionStateChangedEvent();
*/
  }

  /**
   * Returns <code>true</code> if the document can save its contents in an XML data file, and <code>false</code> otherwise.
   * @see #saveXML
   */
  public boolean isSaveXMLEnabled()
  {
    return this.isDocumentChanged;
  }

  /**
   * Saves the <code>IlvSDMEngine</code> contents in a new XML file.
   * @param filename The file name of the XML data file to be saved.
   */
  public void saveXMLAs(String filename)
  {
    if (filename == null) return;
    setFilename(filename);
    this.isDefaultName = false;
    try {
      IlvSDMUtils.saveXML(getFilename(), getEngine());
    }
    catch (Exception ex) {
      IlvSDMUtils.showException(ex, getMainDocumentView().getView());
    }
    this.isDocumentChanged = false;
    setEditable(true);
    getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * Saves the <code>IlvSDMEngine</code> contents in a new IVL file.
   * @param filename The file name of the IVL file to be saved.
   */
  public void saveIVL(String filename)
  {
    try {
      getEngine().getGrapher().write(filename);
    }
    catch (Exception ex) {
      fireMessageEvent(ex.toString());
    }
  }

  /**
   * Saves the <code>IlvSDMEngine</code> contents in a new SVG file.
   * @param filename The file name of the SVG file to be saved.
   */
  public void saveSVG(String filename)
  {
    try {
      IlvManagerStreamFactory streamFactory = getEngine().getGrapher().getStreamFactory();
      getEngine().getGrapher().setStreamFactory(new SVGStreamFactory());
      getEngine().getGrapher().write(filename);
      getEngine().getGrapher().setStreamFactory(streamFactory);
    }
    catch (Exception ex) {
      fireMessageEvent(ex.toString());
      System.err.println(ex);
    }
  }

  /**
   * Saves the <code>IlvSDMEngine</code> contents in a new PNG bitmap file.
   * @param filename The file name of the PNG file to be saved.
   */
  public void savePNG(String filename)
  {
    try {
      //(new BitmapGenerator(this.activeView.getView())).savePNGAs(filename);
    }
    catch (Exception ex) {
      fireMessageEvent(ex.toString());
    }
  }


  /**
   * Returns <code>true</code> if objects are selected, and <code>false</code> otherwise.
   * @see #cut
   */
  public boolean isCutEnabled()
  {
    return !isSelectionEmpty();
  }

  /**
   * Copies the selected objects.
   * @see #isCopyEnabled
   */
  public void copy()
  {
    getEngine().copy();
    this.isClipBoardEmpty = false;
    getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * Returns <code>true</code> if objects are selected, and <code>false</code> otherwise.
   * @see #copy
   */
  public boolean isCopyEnabled()
  {
    return !isSelectionEmpty();
  }

  /**
   * Pastes the clipboard contents to the document.
   * @see #isPasteEnabled
   */
  public void paste()
  {
    getEngine().paste();
  }

  /**
   * Returns <code>true</code> if the clipboard is not empty, and <code>false</code> otherwise.
   * @see #paste
   */
  public boolean isPasteEnabled()
  {
    return !getDocumentController().isClipBoardEmpty();
  }

  /**
   * Deletes the selected objects.
   * @see #isDeleteEnabled
   */
  public void delete()
  {
    getEngine().delete();
  }

  /**
   * Returns <code>true</code> if objects are selected, and <code>false</code> otherwise.
   * @see #delete
   */
  public boolean isDeleteEnabled()
  {
    return !isSelectionEmpty();
  }

  /**
   * Duplicates the selected objects.
   * @see #isDuplicateEnabled
   */
  public void duplicate()
  {
    getEngine().duplicate();
  }

  /**
   * Returns <code>true</code> if objects are selected, and <code>false</code> otherwise.
   * @see #duplicate
   */
  public boolean isDuplicateEnabled()
  {
    return !isSelectionEmpty();
  }

  /**
   * Makes the table view visible.
   * @param tag The XML tag to filter the table.
   * @see #hideTableView
   * @see #getTableViewCount
   */
  public void showTableView(String tag)
  {
//    this.controller.getApplication().showTableView(tag);
  }

  /**
   * Makes the table view visible.
   * @see #hideTableView
   * @see #getTableViewCount
   */
  public void hideTableView()
  {
//    this.controller.getApplication().hideTableView();
  }

  /**
   * Returns the number of visible table views.
   * @see #showTableView
   * @see #hideTableView
   */
  public int getTableViewCount()
  {
    int count = 0;
    for(int i=0; i<this.documentViews.size(); i++) {
//      if (this.documentViews.elementAt(i) instanceof IlvSDMTableView) count++;
    }
    return count;
  }


  /**
   * Makes the tree view visible.
   * @see #hideTreeView
   * @see #getTreeViewCount
   */
  public void showTreeView()
  {
    this.controller.getApplication().showTreeView();
  }

  /**
   * Makes the tree view visible.
   * @see #hideTreeView
   * @see #getTreeViewCount
   */
  public void hideTreeView()
  {
//    this.controller.getApplication().hideTreeView();
  }

  /**
   * Returns the number of visible tree views.
   * @see #showTreeView
   * @see #hideTreeView
   */
  public int getTreeViewCount()
  {
    int count = 0;
    for(int i=0; i<this.documentViews.size(); i++) {
      if (this.documentViews.elementAt(i) instanceof IlvSDMTreeView) count++;
    }
    return count;
  }


  /**
   * Reloads the open XML file.
   */
  public void reloadXML()
  {
    try {
      openXML(documentFilename);
    }
    catch(Exception ex) {
      IlvSDMUtils.showException(ex, getMainDocumentView().getView());
    }
  }

  /**
   * Reloads the open CSS files.
   */
  public void reloadCSS()
  {
    boolean changed = this.isDocumentChanged;
    try {
      String[] css = getEngine().getStyleSheets();
      if(css != null && css.length > 0) {
        getEngine().setStyleSheets(css);
      }
    }
    catch (Exception ex) {
        IlvSDMUtils.showException(ex, getMainDocumentView().getView());
    } finally {
      this.isDocumentChanged = changed;
    }
  }

  /**
   * Returns <code>true</code> if the undo manager can undo the last action, and <code>false</code> otherwise.
   * @see #undo
   */
  public void undo()
  {
    this.undoManager.undo();
  }

  /**
   * Returns <code>true</code> if the undo manager can undo the last action, and <code>false</code> otherwise.
   * @see #undo
   */
  public boolean isUndoEnabled()
  {
    return this.undoManager.canUndo();
  }

  /**
   * Repeats the last undo action.
   * @see #isRedoEnabled
   */
  public void redo()
  {
    this.undoManager.redo();
  }

  /**
   * Returns <code>true</code> if the undo manager can redo the last undo action, and <code>false</code> otherwise.
   * @see #redo
   */
  public boolean isRedoEnabled()
  {
    return this.undoManager.canRedo();
  }

  /**
     * Returns <code>true</code> if the clipboard is empty, and <code>false</code> otherwise.
     */
  public boolean isClipBoardEmpty()
  {
    return this.isClipBoardEmpty;
  }

  /**
     * Returns <code>true</code> if objects are selected in the document, and <code>false</code> otherwise.
     */
  public boolean isSelectionEmpty()
  {
    return this.isSelectionEmpty;
  }

  /**
     * Returns <code>true</code> if more than one object is selected in the document, and <code>false</code> otherwise.
     */
  public boolean isSelectionMultiple()
  {
    return this.isSelectionMultiple;
  }

  /**
     * Cuts the selected objects.
     * @see #isCutEnabled
     */
  public void cut()
  {
    getEngine().cut();
    this.isClipBoardEmpty = false;
    getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * Makes the overview visible.
   * @see #hideOverview
   * @see #getOverviewCount
   */
  public void showOverview()
  {
//    this.controller.getApplication().showOverview();
  }

  /**
   * Hides the overview.
   * @see #showOverview
   * @see #getOverviewCount
   */
  public void hideOverview()
  {
//    this.controller.getApplication().hideOverview();
  }

  /**
   * Returns the number of visible overviews.
   * @see #showOverview
   * @see #hideOverview
   */
  public int getOverviewCount()
  {
    int count = 0;
    for(int i=0; i<this.documentViews.size(); i++) {
//      if (this.documentViews.elementAt(i) instanceof IlvSDMOverview) count++;
    }
    return count;
  }

  /**
   * Displays the grapher view.
   * @see #getGrapherViewCount
   */
  public void showGrapherView() {}

  /**
   * Returns the number of visible grapher views.
   * @see #showGrapherView
   */
  public int getGrapherViewCount()
  {
    int count = 0;
    for(int i=0; i<this.documentViews.size(); i++) {
      if (this.documentViews.elementAt(i) instanceof IlvSDMGrapherView) count++;
    }
    return count;
  }

  /**
   * Returns <code>true</code> if the document has changed since it has been opened or saved, and <code>false</code> otherwise.
   */
  public boolean isDocumentChanged()
  {
    return this.isDocumentChanged;
  }

  /**
   * Returns the <code>IlvSDMUndoManager</code> associated with the document.
   */
  public IlvSDMUndoManager getUndoManager()
  {
    return this.undoManager;
  }

  /**
   * Groups the selected objects.
   * @see #isGroupEnabled
   * @return The parent object that this method has created.
   */
  public Object group()
  {
    this.engine.setAdjusting(true);
    try {
      Object parent = createGroupParent();
      if(parent != null)
        getEngine().group(parent);
      return parent;
    } finally {
      this.engine.setAdjusting(false);
    }
  }

  /**
   * This method is called by the {@link #group()} command.
   * Its purpose is to create a new node that will be used as the parent
   * of the group.
   * <p>
   * The default implementation returns a new node of tag
   * <code>"group"</code>, with no properties defined.
   * <p>
   * If this method returns <code>null</code>, the group
   * action will be cancelled.
   */
  protected Object createGroupParent()
  {
    return getEngine().getModel().createNode("group");
  }

  /**
   * Returns <code>true</code> if more than one object is selected, and <code>false</code> otherwise.
   * @see #group
   */
  public boolean isGroupEnabled()
  {
    return !isSelectionEmpty() && isSelectionMultiple();
  }

  /**
   * Dissociates a grouped object.
   * @see #ungroup
   */
  public void ungroup()
  {
    getEngine().ungroup();
  }

  /**
   * Returns <code>true</code> if at least one object is selected, and <code>false</code> otherwise.
   * @see #ungroup
   */
  public boolean isUngroupEnabled()
  {
    return !isSelectionEmpty();
  }

  // Implements SDMModelListener


  /**
   * Invoked at the end of an adjustment sequence, when <code>setAdjusting(false)</code> is called on the model.
   * @param event The event sent by an <code>IlvSDMModel</code>.
   */
  public void adjustmentFinished(SDMModelEvent event)
  {
    this.isDocumentChanged = true;
    getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * Invoked after the SDM model has changed in such a way that the SDM view must completely recreate its graphic objects.
   * @param event The event sent by an <code>IlvSDMModel</code>.
   */
  public void dataChanged(SDMModelEvent event)
  {
    this.isDocumentChanged = true;
    getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * Invoked after the destination node of a link has been changed.
   * @param event The event sent by an <code>IlvSDMModel</code>.
   */
  public void linkDestinationChanged(SDMModelEvent event)
  {
    this.isDocumentChanged = true;
    if(event.isAdjusting()) return;
    getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * Invoked after the source node of a link has been changed.
   * @param event The event sent by an <code>IlvSDMModel</code>.
   */
  public void linkSourceChanged(SDMModelEvent event)
  {
    this.isDocumentChanged = true;
    if(event.isAdjusting()) return;
    getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * Invoked after the nodes and/or the links have been added to the SDM model.
   * @param event The event sent by an <code>IlvSDMModel</code>.
   */
  public void objectAdded(SDMModelEvent event)
  {
    this.isDocumentChanged = true;
    if(event.isAdjusting()) return;
    getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * Invoked after the nodes and/or the links have been removed from the SDM model.
   * @param event The event sent by an <code>IlvSDMModel</code>.
   */
  public void objectRemoved(SDMModelEvent event)
  {
    this.isDocumentChanged = true;
    if(event.isAdjusting()) return;
    getDocumentController().fireActionStateChangedEvent();
  }

  // Implements SDMPropertyChangeListener

  /**
   * Invoked after one or several properties of a data object have changed.
   * This method can also be called when an arbitrary set of properties has changed.
   * In this case, the property name and the old and new values contained in the event are <code>null</code>.
   * @param event The event sent by an <code>IlvSDMModel</code> when a property of a data object has changed.
   */
  public void propertyChanged(SDMPropertyChangeEvent event)
  {
    this.isDocumentChanged = true;
    if(event.isAdjusting()) return;
    getDocumentController().fireActionStateChangedEvent();
  }

  // Implements ManagerSelectionListener

  /**
   * This method is called when the selection changes in a manager.
   * @param event The "selection changed" event.
   */
  public void selectionChanged(ManagerSelectionChangedEvent event)
  {
    int objectCount = 0;
    Enumeration objectEnumeration = getEngine().getSelectedObjects();
    this.isSelectionEmpty = !objectEnumeration.hasMoreElements();
    if (this.isSelectionEmpty) {
      this.isSelectionMultiple = false;
      getDocumentController().fireActionStateChangedEvent();
    }
    else {
      objectEnumeration.nextElement();
      objectCount++;
      while(objectEnumeration.hasMoreElements()) {
        objectEnumeration.nextElement();
        objectCount++;
      }
      this.isSelectionMultiple = objectCount > 1;
      getDocumentController().fireActionStateChangedEvent();
    }
  }

  /**
   * This method is performed by the <code>IlvSDMDocumentController</code> when the active document view changes.
   * @param event The event send by the document controller.
   */
  public void stylesheetChanged(IlvSDMStylesheetChangedEvent event)
  {
/*    if (event.getDocument() != this) {
      try {
        getEngine().setStyleSheets(0,event.getDefaultStylesheet());
      }
      catch (Exception ex) {
        System.err.println(ex);
      }
    }
*/
  }

  /**
   * Returns <code>true</code> if the document is new and has never been saved in a file, and <code>false</code> otherwise.
   */
  public boolean isDefaultName()
  {
    return this.isDefaultName;
  }
}
