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
 * $Id: IlvSDMGrapherView.java,v 1.32 2001/09/26 14:15:19 gdigugli Exp $
 *
 */

package exp.ilog.tree;

import javax.swing.*;
import ilog.views.sdm.*;
import ilog.views.swing.*;
import java.awt.*;
import java.util.*;
import java.net.*;

import ilog.views.*;
import ilog.views.interactor.*;
import ilog.views.sdm.*;
import ilog.views.sdm.renderer.*;
import ilog.views.sdm.renderer.graphlayout.*;

import ilog.views.sdm.gui.event.*;
import ilog.views.sdm.gui.util.*;
import ilog.views.sdm.gui.print.*;
import ilog.views.sdm.gui.action.*;

import ilog.views.graphlayout.*;
import ilog.views.graphlayout.hierarchical.*;


/**
 * <p>
 * <code>IlvSDMGrapherView</code> is a grapher representation of the data model of the document.
 * </p>
 * <p>
 * <i>A sample of a grapher view with a workflow diagram using the <code>IlvSDMInternalFrame</code>:</i>
 * <br>
 * <br>
 * <img src="../../../../../../sdm/doc/images/grapherview.gif" alt="A sample of a grapher view with a workflow diagram.">
 * </p>
 * @see ilog.views.sdm.IlvSDMView
 */
public class IlvSDMGrapherView implements IlvSDMGrapherDocumentViewInterface
{
  private static String nodeLayoutName  = "ilog.views.sdm.renderer.graphlayout.IlvGraphLayoutRenderer";
  private static String linkLayoutName  = "ilog.views.sdm.renderer.graphlayout.IlvLinkLayoutRenderer";
  private static String labelLayoutName = "ilog.views.sdm.renderer.graphlayout.IlvLabelLayoutRenderer";

  private IlvSDMView view;
  private IlvJScrollManagerView scrolledView;
  private Vector messagesListenerVector = new Vector(5);
  private IlvSDMDocument document;
  private IlvSDMViewPrinter printer;
  private float zoomInFactor = (float)2.0;
  private float zoomOutFactor = (float)2.0;
  private IlvSDMFrameInterface frame;

  /**
   * Creates an <code>IlvSDMGrapherView</code> attached to the document parameter.
   * @param document The document to which the document view is attached.
   * @param frame The frame where the view is instantiated.
   */
  public IlvSDMGrapherView(IlvSDMDocument document, IlvSDMFrameInterface frame)
  {
    this.frame = frame;
    this.document = document;
    this.frame.setTitle(document.getFilename() + " - " + IlvSDMMessages.getMessage(IlvSDMMessages.VIEWNAME_grapherView));
    this.frame.setClosable(true);
    this.frame.setMaximizable(true);
    this.frame.setIconifiable(true);
    this.frame.setResizable(true);
    this.frame.setDocumentView(this);
    init();
    this.document.addDocumentView(this);
    addMessageListener(this.document.getDocumentController());
  }

  /**
   * Returns <code>true</code> if the document can be modified.
   */
  public boolean isEditable()
  {
    return getDocument().isEditable();
  }

  /**
   * Sets the editable flag of the document.
   *
   * @param editable If <code>true</code>,
   * and if the SDM data model is also editable, then the
   * user will be able to edit the document. Otherwise, the editing
   * interactions and commands should be disabled.
   */
  public void setEditable(boolean editable)
  {
    fireActionStateChangedEvent();
  }

  /**
   * Sets the document view title and adds the document view type at the end of the title.
   * @param title The new title of the document view.
   */
  public void setTitle(String title)
  {
    this.frame.setTitle(title + " - " + IlvSDMMessages.getMessage(IlvSDMMessages.VIEWNAME_grapherView));
  }

  /**
   * Returns the document view title.
   */
  public String getTitle()
  {
    return this.frame.getTitle();
  }

  /**
   *
   */
  private void init()
  {
    this.view = new IlvSDMView(getEngine());
    this.view.setDropEnabled(true);
    this.view.setLinkReconnectionEnabled(true);
    this.view.setBackground(Color.white);
    this.view.setDoubleBuffering(true);
    this.view.getManager().addManagerTreeSelectionListener(getDocument());

    IlvToolTipManager.registerView(this.view);
    this.scrolledView = new IlvJScrollManagerView(this.view);

    this.frame.setOpaque(true);
    this.frame.setBackground(Color.white);

    URL iconURL = IlvSDMUtils.findURL(null, this.getClass(), "data/sdm.gif");
    this.frame.setIconImage((new ImageIcon(iconURL)).getImage());
    this.frame.getContentPane().setLayout(new BorderLayout());
    this.frame.getContentPane().add(scrolledView,BorderLayout.CENTER);
    this.frame.setSize(500,400);
  }

  /**
   * Returns the associated document.
   */
  public IlvSDMDocument getDocument()
  {
    return this.document;
  }

  /**
   * This method is not implemented and does not change the displayed document of the view.
   * @param document The document.
   */
  public void setDocument(IlvSDMDocument document)
  {

  }

  /**
   * Returns the <code>IlvSDMView</code> of the document view if it is possible, and <code>null</code> otherwise.
   */
  public IlvSDMView getView()
  {
    return this.view;
  }

  /**
   * Returns the <code>IlvSDMEngine</code> associated with the document view.
   */
  public IlvSDMEngine getEngine()
  {
    return this.document.getEngine();
  }

  /**
   * Returns the <code>IlvSDMModel</code> of the <code>IlvSDMEngine</code> associated with the document view.
   */
  public IlvSDMModel getModel()
  {
    return this.document.getEngine().getModel();
  }

  /**
   * Adds an <code>IlvSDMMessageListener</code> that is notified of the document view user messages.
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

  private void fireMessageEvent(String message)
  {
    for(int i=0; i<this.messagesListenerVector.size(); i++) {
        ((IlvSDMMessageListener) this.messagesListenerVector.elementAt(i)).messagePerformed(new IlvSDMMessageEvent(message,this));
      }
  }

  /**
   * Updates the state of all the <code>IlvSDMNotifiedAction</code>s registered to the document view.
   */
  private void fireActionStateChangedEvent()
  {
    getDocument().getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * This method is performed by the <code>IlvSDMDocumentController</code> when the active document view changes.
   * @param event The AWT event that can be the source of the update.
   */
  public void activeDocumentViewChanged(AWTEvent event)
  {
    fireActionStateChangedEvent();
  }

  // Implements IlvSDMDocumentViewInterface

  /**
   * Closes the view.
   */
  public void close()
  {
    try {
      this.frame.setClosed(true);
    }
    catch(Exception ex) {
      System.err.println(ex);
    }
  }

  /**
   * Prints the view in one page.
   */
  public void printInOnePage()
  {
    if (this.printer == null)
      this.printer = new IlvSDMViewPrinter(getView().getManager());
    this.printer.setManagerView(getView());
    this.printer.setPrintInOnePage(true,true);
    new Thread(new RunPrint()).start();
  }

  /**
   * Prints the view.
   */
  public void print()
  {
    if (this.printer == null)
      this.printer = new IlvSDMViewPrinter(getView().getManager());
    this.printer.setAdditionalZoom(0.5,0.5);
    this.printer.setManagerView(getView());
    new Thread(new RunPrint()).start();
  }

  /**
   * Sets up the page format for printing.
   */
  public void pageSetup()
  {
    if (this.printer == null)
      this.printer = new IlvSDMViewPrinter(getView().getManager());
    this.printer.pageSetup();
  }

  /**
   * Sets the zoom to its initial level.
   * @see #zoomOut
   * @see #zoomIn
   * @see #zoomBox
   */
  public void zoomInit()
  {
    getView().setTransformer(new IlvTransformer());
    getView().repaint();
  }

  /**
   * Zooms in to the view.
   * @see #zoomInit
   * @see #zoomOut
   * @see #zoomBox
   */
  public void zoomIn()
  {
    if (getView() == null)
      return;
    Dimension size = getView().getSize();
    IlvPoint point = new IlvPoint(size.width/2,size.height/2);
    getView().zoom(point,this.zoomInFactor,this.zoomInFactor,true);
  }

  /**
   * Zooms out of the view.
   * @see #zoomInit
   * @see #zoomBox
   * @see #zoomIn
   */
  public void zoomOut()
  {
    if (getView() == null)
      return;
    Dimension size = getView().getSize();
    IlvPoint point = new IlvPoint(size.width/2,size.height/2);
    getView().zoom(point,1.0/this.zoomOutFactor,1.0/this.zoomOutFactor,true);
  }

  /**
   * Activates the zoom box interactor.
   * @see #zoomInit
   * @see #zoomBox
   * @see #zoomOut
   */
  public void zoomBox()
  {
    IlvManagerViewInteractor interactor = ((IlvSDMInteractorActionInterface) getDocument().getDocumentController().getAction(IlvSDMMessages.getMessage(IlvSDMMessages.ACTION_zoomBoxActionName))).getInteractor();
    getView().setInteractor(interactor);
  }

  /**
   * Activates the pan interactor.
   */
  public void panInteractor()
  {
    IlvManagerViewInteractor interactor = ((IlvSDMInteractorActionInterface) getDocument().getDocumentController().getAction(IlvSDMMessages.getMessage(IlvSDMMessages.ACTION_panActionName))).getInteractor();
    if (getView().getInteractor() != interactor)
      getView().setInteractor(interactor);
    else
      activate();
  }

  /**
   * Returns <code>true</code> if the pan interactor is in use, and <code>false</code> otherwise.
   */
  public boolean isPanInteractorEnabled()
  {
    return true;
  }

  /**
   * Activates the expand/collapse interactor.
   * @see #isExpandInteractorEnabled
   */
  public void expandInteractor() {}

  /**
   * Returns <code>true</code> if the expand/collapse interactor is in use, and <code>false</code> otherwise.
   * @see #expandInteractor
   */
  public boolean isExpandInteractorEnabled()
  {
    return true;
  }

  /**
   * Activates the selection interactor.
   * @see #isSelectInteractorEnabled
   */
  public void selectInteractor()
  {
    IlvManagerViewInteractor interactor = ((IlvSDMInteractorActionInterface) getDocument().getDocumentController().getAction(IlvSDMMessages.getMessage(IlvSDMMessages.ACTION_selectActionName))).getInteractor();
    getView().setInteractor(interactor);
  }

  /**
   * Returns <code>true</code> if the selection interactor is in use, and <code>false</code> otherwise.
   * @see #selectInteractor
   */
  public boolean isSelectInteractorEnabled()
  {
    return true;
  }

  /**
   * Deactivates all the interactors in use.
   */
  public void activate()
  {
    while(this.view.getInteractor() != null)
      this.view.popInteractor();
  }

  /**
     * Activates the edit label interactor.
     * @see #isEditLabelInteractorEnabled
     */
  public void editLabelInteractor()
  {
    IlvManagerViewInteractor interactor = ((IlvSDMInteractorActionInterface) getDocument().getDocumentController().getAction(IlvSDMMessages.getMessage(IlvSDMMessages.ACTION_editLabelActionName))).getInteractor();
    getView().setInteractor(interactor);
  }

  /**
   * Returns <code>true</code> if the edit label interactor is in use, and <code>false</code> otherwise.
   * @see #editLabelInteractor
   */
  public boolean isEditLabelInteractorEnabled()
  {
    return true;
  }

  /**
   * Activates the antialiasing of the view.
   * @see #isAntialisingEnabled
   */
  public void antialiasing()
  {
    this.view.setAntialiasing(!this.view.isAntialiasing());
    this.view.repaint();
  }

  /**
   * Returns <code>true</code> if the view is antialiased.
   * @see #antialiasing
   */
  public boolean isAntialisingEnabled()
  {
    return this.view.isAntialiasing();
  }

  /**
   * Sets the zoom level to see all the graphic components of the document in the view.
   */
  public void fitToContent()
  {
    if (getView() == null)
      return;
    getView().fitTransformerToContent();
    getView().repaint();
  }

  /**
   * Activates the node layout.
   * @see #isNodeLayoutEnabled
   * @see #setNodeLayoutEnabled
   */
  public void nodeLayout() {
    // temp. enable nodeLayout
    IlvSDMEngine engine = getEngine();
    boolean current = engine.isNodeLayoutEnabled();
    engine.setNodeLayoutEnabled(true);
    engine.performNodeLayout();
    engine.setNodeLayoutEnabled(current);
  }

  /**
   * Utility method for nodes layout.
   */
  public void nodeLayout1() { // using setFilter
    IlvGrapher mgr = getEngine().getGrapher();

    if(mgr.getSelectedObjectsCount(true) > 0) {

      // apply the layout only on selected nodes.

      IlvTransformer trf = getView().getTransformer();
      IlvGraphLayout r = (IlvGraphLayout) ((IlvGraphLayoutRenderer)IlvRendererUtil.getRenderer(getEngine(), this.nodeLayoutName)).getGraphLayout();

      final IlvLayoutGraphicFilter sdmFilter = ((IlvGrapherAdapter)r.getGraphModel()).getFilter();
      final HashMap selected = new HashMap(mgr.getSelectedObjectsCount(true));
      // fill selected hashtable, compute bbox of selected obj.
      IlvRect selectedRegion = null;
      IlvGraphicEnumeration selectedE = mgr.getSelectedObjects(true);
      while (selectedE.hasMoreElements()) {
        IlvGraphic g = selectedE.nextElement();
        selected.put(g, null);
        if (selectedRegion == null) {
          selectedRegion = g.boundingBox(trf);
        } else {
          selectedRegion.add(g.boundingBox(trf));
        }
      }

      ((IlvGrapherAdapter)r.getGraphModel()).setFilter(new IlvLayoutGraphicFilter()   {
          public boolean accept(IlvGraphic graphic)
          {
            return sdmFilter.accept(graphic) && selected.containsKey(graphic);
          }
        });

      getEngine().performNodeLayout();

      // reset filter
      ((IlvGrapherAdapter)r.getGraphModel()).setFilter(sdmFilter);
      //move selection back
      mgr.translateSelections(selectedRegion.x, selectedRegion.y, getView());

    } else {
      getEngine().performNodeLayout();
    }
  }

  /**
   * Utility method for nodes layout.
   */
  public void nodeLayout2()	// using setFixed
  {
    Vector fixed = new Vector();

    IlvGraphLayout r = (IlvGraphLayout) ((IlvGraphLayoutRenderer)IlvRendererUtil.getRenderer(getEngine(), this.nodeLayoutName)).getGraphLayout();
    IlvManager mgr = getEngine().getGrapher();

    if(r != null && r.supportsPreserveFixedNodes()
       && mgr.getSelectedObjectsCount(true) > 0) {
      // perform layout only on selection
      IlvGraphicEnumeration all = mgr.getObjects(true);
      while (all.hasMoreElements()) {
        IlvGraphic g = all.nextElement();
        if (r.isFixed(g)) {
          fixed.addElement(g);
        } else {
          r.setFixed(g, true);
        }
      }

      IlvRect selectedRegion = null;
      IlvGraphicEnumeration selected = mgr.getSelectedObjects(true);
      while (selected.hasMoreElements()) {
        IlvGraphic g = selected.nextElement();
        r.setFixed(g, false);
        if (selectedRegion == null) {
          selectedRegion = g.boundingBox(null);
        } else {
          selectedRegion.add(g.boundingBox(null));
        }
      }

      r.setPreserveFixedNodes(true);

      if (r.supportsLayoutRegion())
        r.setLayoutRegion(selectedRegion);

      getEngine().performNodeLayout();

      r.unfixAllNodes();
      if (r.supportsLayoutRegion())
        r.setLayoutRegion((IlvRect)null);
      else {
        //hmm move them, then
        selected = mgr.getSelectedObjects(true);
        while (selected.hasMoreElements()) {
          IlvGraphic g = selected.nextElement();
          g.translate(selectedRegion.x, selectedRegion.y);
        }
      }

      // restore fixed object
      Enumeration e = fixed.elements();
      while (e != null && e.hasMoreElements()) {
        IlvGraphic g = (IlvGraphic) e.nextElement();
        r.setFixed(g, true);
      }
    } else {
      getEngine().performNodeLayout();
    }
  }

  /**
   * Activates or deactivates the node layout.
   * @param enabled The flag to activate/deactivate the node layout.
   * @see #nodeLayout
   * @see #isNodeLayoutEnabled
   */
  public void setNodeLayoutEnabled(boolean enabled)
  {
    if(isNodeLayoutEnabled() == enabled)
      return;
    getEngine().setNodeLayoutEnabled(enabled);
    getEngine().performNodeLayout();

  }

  /**
   * Returns <code>true</code> if the link layout is enabled, and <code>false</code> otherwise.
   * @see #nodeLayout
   * @see #setNodeLayoutEnabled
   */
  public boolean isNodeLayoutEnabled()  {
      return getEngine().isNodeLayoutEnabled();
  }

  /**
   * Activates the link layout.
   * @see #isLinkLayoutEnabled
   * @see #setLinkLayoutEnabled
   */
  public void linkLayout()  {
      setLinkLayoutEnabled(!isLinkLayoutEnabled());
  }

  /**
   * Activates or deactivates the link layout.
   * @param enabled The flag to activate/deactivate the link layout.
   * @see #linkLayout
   * @see #setLinkLayoutEnabled
   */
  public void setLinkLayoutEnabled(boolean enabled)
  {
    if(isLinkLayoutEnabled() == enabled)
      return;
    getEngine().setLinkLayoutEnabled(enabled);
    getEngine().performLinkLayout();
  }

  /**
   * Returns <code>true</code> if the link layout is enabled, and <code>false</code> otherwise.
   * @see #linkLayout
   * @see #setLinkLayoutEnabled
   */
  public boolean isLinkLayoutEnabled()  {
      return getEngine().isLinkLayoutEnabled();
  }

  /**
   * Activates the label layout.
   * @see #isLabelLayoutEnabled
   * @see #setLabelLayoutEnabled
   */
  public void labelLayout()
  {
    setLabelLayoutEnabled(!isLabelLayoutEnabled());
  }

  /**
   * Returns <code>true</code> if the label layout is enabled, and <code>false</code> otherwise.
   * @see #labelLayout
   * @see #setLabelLayoutEnabled
   */
  public boolean isLabelLayoutEnabled()
  {
    IlvLabelLayoutRenderer r = (IlvLabelLayoutRenderer)IlvRendererUtil.getRenderer(getEngine(), this.labelLayoutName);
    if(r != null) {
      return r.isEnabled();
    }
    else {
      return false;
    }
  }

  /**
   * Vertical alignment of the selection.
   */
  public void verticalAlignLayout()
  {
    IlvSDMUtils.align(getEngine(), getView(), IlvDirection.VerticalCenter);
  }

  /**
   * Horizontal alignment of the selection.
   */
  public void horizontalAlignLayout()
  {
    IlvSDMUtils.align(getEngine(), getView(), IlvDirection.HorizontalCenter);
  }

  /**
   * Left alignment of the selection.
   */
  public void leftAlignLayout()
  {
    IlvSDMUtils.align(getEngine(), getView(), IlvDirection.Left);
  }

  /**
   * Right alignment of the selection.
   */
  public void rightAlignLayout()
  {
    IlvSDMUtils.align(getEngine(), getView(), IlvDirection.Right);
  }

  /**
   * Bottom alignment of the selection.
   */
  public void bottomAlignLayout()
  {
    IlvSDMUtils.align(getEngine(), getView(), IlvDirection.Bottom);
  }

  /**
   * Top alignment of the selection.
   */
  public void topAlignLayout()
  {
    IlvSDMUtils.align(getEngine(), getView(), IlvDirection.Top);
  }

  /**
   * Distributes the selection horizontally.
   */
  public void horizontalDistributeLayout()
  {
    IlvSDMUtils.align(getEngine(), getView(), IlvDirection.Horizontal);
  }

  /**
   * Distributes the selection vertically.
   */
  public void verticalDistributeLayout()
  {
    IlvSDMUtils.align(getEngine(), getView(), IlvDirection.Vertical);
  }

  /**
   * Activates or deactivates the label layout.
   * @param enabled The flag to activate/deactivate the label layout.
   * @see #labelLayout
   * @see #setLabelLayoutEnabled
   */
  public void setLabelLayoutEnabled(boolean enabled)
  {
    if(isLabelLayoutEnabled() == enabled)
      return;

    IlvLabelLayoutRenderer r = (IlvLabelLayoutRenderer)IlvRendererUtil.getRenderer(getEngine(), this.labelLayoutName);
    if(r != null) {
      r.setEnabled(enabled);
      if(enabled) {
        getEngine().renderingDone();
      }
    }
  }

  /**
   * Enables the grid of the document view.
   * @see #isGridEnabled
   */
  public void grid()
  {
    if(getView().getGrid() == null) {
      getView().setGrid(new IlvGrid());
    }
    else {
      getView().setGrid(null);
    }
    getView().repaint();
    getDocument().getDocumentController().fireActiveDocumentViewChanged(null);
  }

  /**
   * Returns <code>true</code> if the grid is enabled, and <code>false</code> otherwise.
   * @see #grid
   */
  public boolean isGridEnabled()
  {
    return getView().getGrid() != null;
  }

  /**
   * Returns <code>true</code> if the instance of the view is shared among multiple documents, and <code>false</code> otherwise.
   */
  public boolean isShared()
  {
    return false;
  }

  /**
   * Returns the frame that contains the document view.
   */
  public IlvSDMFrameInterface getFrame()
  {
    return this.frame;
  }

  /**
   * This method is called before the document view was closed.
   * Do nothing.
   */
  public void beforeClose()
  {
    this.view.getManager().removeManagerTreeSelectionListener(getDocument());
    this.document.removeDocumentView(this);
    this.document = null;
    IlvToolTipManager.unregisterView(this.view);
    this.view = null;
    this.scrolledView = null;
    this.frame = null;
  }

  private class RunPrint implements Runnable
  {
    public void run()
    {
      if (!IlvSDMGrapherView.this.printer.print())
        fireMessageEvent(IlvSDMMessages.getMessage(IlvSDMMessages.MESSAGE_printDone));
      else
        fireMessageEvent(IlvSDMMessages.getMessage(IlvSDMMessages.MESSAGE_printError));
      IlvSDMGrapherView.this.printer.setPrintInOnePage(false, false);
    }
  }
}
