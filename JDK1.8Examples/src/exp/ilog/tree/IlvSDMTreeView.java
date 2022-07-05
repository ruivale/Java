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
 * $Id: IlvSDMTreeView.java,v 1.20 2001/10/03 15:36:42 gdigugli Exp $
 *
 */

package exp.ilog.tree;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import ilog.views.sdm.*;
import ilog.views.sdm.swing.*;
import ilog.views.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

import ilog.views.sdm.*;
import ilog.views.sdm.gui.util.*;
import ilog.views.sdm.gui.event.*;
import ilog.views.sdm.gui.action.*;
import ilog.views.*;
import ilog.views.event.*;

/**
 * <p>
 * <code>IlvSDMTreeView</code> is a tree representation of the data model of the document.
 * The implementation is based on the {@link ilog.views.sdm.gui.tree.IlvSDMTree} and {@link ilog.views.sdm.gui.tree.IlvSDMTreeModel}.
 * </p>
 * <p>
 * <i>A sample of the tree view using the <code>IlvSDMInternalFrame</code>:</i>
 * <br>
 * <br>
 * <img src="../../../../../../sdm/doc/images/treeview.gif" alt="A sample of the tree view.">
 * </p>
 * <p>
 * See {@link ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer} for the styling features of the tree.
 * </p>
 * @see ilog.views.sdm.gui.tree.IlvSDMTree
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeModel
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellEditor
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeCellRenderer
 * @see ilog.views.sdm.gui.tree.IlvSDMTreeViewRenderer
 */
public class IlvSDMTreeView implements IlvSDMDocumentViewInterface
{
  private Vector messagesListenerVector = new Vector(5);
  private IlvSDMDocument document;
  private boolean isShared = false;
  private IlvSDMFrameInterface frame;
  private IlvSDMTree tree;
  private JCheckBox visibilityButton;
  // for garbage collection
  private ItemListener propertyVisibleListener;
  private MouseAdapter propertyVisibleMouseAdapter;

  /**
   * Creates an <code>IlvSDMTreeView</code> attached to the document parameter.
   * @param document The document to which the document view is attached.
   * @param frame The frame where the view is instantiated.
   */
  public IlvSDMTreeView(IlvSDMDocument document, IlvSDMFrameInterface frame)
  {
    this.frame = frame;
    this.document = document;
    this.frame.setTitle(document.getFilename() + " - " + IlvSDMMessages.getMessage(IlvSDMMessages.VIEWNAME_treeView));
    this.frame.setClosable(true);
    this.frame.setMaximizable(true);
    this.frame.setIconifiable(true);
    this.frame.setResizable(true);
    this.frame.setDocumentView(this);
    this.frame.setSize(250,350);
    init();
    this.document.addDocumentView(this);
    addMessageListener(this.document.getDocumentController());
    this.tree.selectionChanged(null);
  }

  /**
   * Creates an <code>IlvSDMTreeView</code> attached to the document parameter and shared among all the documents of the application.
   * @param document The document to which the document view is attached.
   * @param frame The frame where the view is instantiated.
   * @param isShared The flag to make this view shared among all the documents of the application.
   */
  public IlvSDMTreeView(IlvSDMDocument document, IlvSDMFrameInterface frame, boolean isShared)
  {
    this(document,frame);
    this.isShared = isShared;
    if (isShared) document.getDocumentController().addActiveDocumentViewListener(this);
  }

  /**
   * Returns <code>true</code> if this document can be modified.
   */
  public boolean isEditable()
  {
    return getDocument().isEditable();
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
    fireActionStateChangedEvent();
  }

  /**
   * Sets the document view title and adds the document view type at the end of the title.
   * @param title The new document view title.
   */
  public void setTitle(String title)
  {
    this.frame.setTitle(title + " - " + IlvSDMMessages.getMessage(IlvSDMMessages.VIEWNAME_treeView));
  }

  /**
   * Returns the title of the document view.
   */
  public String getTitle()
  {
    return this.frame.getTitle();
  }

  private void init()
  {
    this.tree = new IlvSDMTree(this.document.getDocumentController().getActiveDocument().getEngine(), true, this.isEditable());

    JScrollPane treeView = new JScrollPane(tree);
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(treeView, BorderLayout.CENTER);

    JPanel optionPanel = new JPanel();
    if (UIManager.getLookAndFeel().getName().equals("CDE/Motif"))
      optionPanel.setBackground(UIManager.getColor("activeCaptionBorder"));
    else
      optionPanel.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));

    this.visibilityButton = new JCheckBox(/*IlvSDMMessages.getMessage(IlvSDMMessages.ACTION_showPropertyActionName)*/);
    this.visibilityButton.setSelected(this.tree.isPropertyVisible());
    if (UIManager.getLookAndFeel().getName().equals("CDE/Motif"))
      this.visibilityButton.setBackground(UIManager.getColor("activeCaptionBorder"));
    else
      this.visibilityButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));

    this.propertyVisibleListener = new PropertyVisibleListener(this);
    this.visibilityButton.addItemListener(propertyVisibleListener);

    this.propertyVisibleMouseAdapter = new PropertyVisibleMouseAdapter(this);
    this.visibilityButton.addMouseListener(propertyVisibleMouseAdapter);

    optionPanel.add(this.visibilityButton);
    panel.add(optionPanel, BorderLayout.SOUTH);

    this.frame.getContentPane().add(panel, BorderLayout.CENTER);

    URL iconURL = IlvSDMUtils.findURL(null, this.getClass(), "data/tree.gif");
    this.frame.setIconImage((new ImageIcon(iconURL)).getImage());
  }

  /**
   * Returns the associated document.
   */
  public IlvSDMDocument getDocument()
  {
    return this.document;
  }

  /**
   * Returns the <code>IlvSDMView</code> of the document view if it is possible, and <code>null</code> otherwise.
   */
  public IlvSDMView getView()
  {
    if (this.document != null && this.document.getMainDocumentView() != null)
      return this.document.getMainDocumentView().getView();
    else
      return null;
  }

  /**
   * Returns the <code>IlvSDMEngine</code> associated with the document view.
   */
  public IlvSDMEngine getEngine()
  {
    return this.tree.getEngine();
  }

  /**
   * Sets the <code>IlvSDMEngine</code> associated with the document view.
   */
  public void setEngine(IlvSDMEngine engine)
  {
    this.tree.setEngine(engine);
  }

  /**
   * Returns the <code>IlvSDMModel</code> of the <code>IlvSDMEngine</code> associated with the document view.
   */
  public IlvSDMModel getModel()
  {
    return this.document.getEngine().getModel();
  }

  /**
   * Adds an <code>IlvSDMMessageListener</code> that is notified of the table view user messages.
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
   * Updates the state of all the <code>IlvSDMNotifiedAction</code>s registered with the document view.
   */
  private void fireActionStateChangedEvent()
  {
    if (getDocument() != null)
      getDocument().getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * This method is performed by the <code>IlvSDMDocumentController</code> when the active document view changes.
   * @param event The AWT event that can be the source of the update.
   */
  public void activeDocumentViewChanged(AWTEvent event)
  {
    if (this.document != this.document.getDocumentController().getActiveDocument()) {
      if (this.isShared) {
        setDocument(this.document.getDocumentController().getActiveDocument());
      }
      this.tree.selectionChanged(null);
    }
    this.visibilityButton.setSelected(this.tree.isPropertyVisible());
  }

  /**
   * Updates the document to which the table view is attached.
   * <br>
   * This method is used when the table view is shared among all the documents of the application.
   * @param document The document.
   */
  public void setDocument(IlvSDMDocument document)
  {
    unRegisterFromCurrentDocument();
    if (document != null) {
      registerToDocument(document);
      setTitle(document.getFilename());
      this.tree.setEngine(this.document.getDocumentController().getActiveDocument().getEngine());
    }
    this.tree.selectionChanged(null);
  }

  /**
   * Sets the referenced document with a new one.
   */
  private void registerToDocument(IlvSDMDocument document)
  {
    this.document = document;
    if(this.document != null)
      this.document.addDocumentView(this);
  }

  /**
   * Stops referencing the current document.
   */
  private void unRegisterFromCurrentDocument()
  {
    if(this.document != null)
      this.document.removeDocumentView(this);
    this.document = null;
  }

  // Implements IlvSDMDocumentViewInterface.

  /**
   * Closes the view.
   */
  public void close()
  {
    try {
      this.frame.setClosed(true);
    }
    catch(Exception ex) {}
  }

  /**
   * Prints the view.
   */
  public void print() {}

  /**
   * Activates the anti-aliasing of the view.
   * @see #isAntialisingEnabled
   */
  public void antialiasing() {}

  /**
   * Returns <code>true</code> if the view is anti-aliased.
   * @see #antialiasing
   */
  public boolean isAntialisingEnabled()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if the instance of the view is shared among multiple documents, and <code>false</code> otherwise.
   */
  public boolean isShared()
  {
    return this.isShared;
  }

  /**
   * Returns the frame that contains the document view.
   */
  public IlvSDMFrameInterface getFrame()
  {
    return this.frame;
  }

  /**
   * Called before the document view is closed.
   * Unregisters all the listeners on the <code>IlvSDMEngine</code> and the <code>IlvSDMModel</code>.
   */
  public void beforeClose()
  {
    this.tree.setEngine(null);
    this.document = null;
    this.visibilityButton.removeItemListener(this.propertyVisibleListener);
    this.visibilityButton.removeMouseListener(this.propertyVisibleMouseAdapter);
    this.propertyVisibleListener = null;
    this.propertyVisibleMouseAdapter = null;
    this.frame = null;
  }

  static class PropertyVisibleMouseAdapter extends MouseAdapter
  {
    private IlvSDMTreeView treeView;

    public PropertyVisibleMouseAdapter(IlvSDMTreeView treeView)
    {
      super();
      this.treeView = treeView;
    }

    public void mouseEntered(MouseEvent e)
    {
      this.treeView.fireMessageEvent(null/*IlvSDMMessages.getMessage(IlvSDMMessages.ACTION_showPropertyActionDescription)*/);
    }

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
  }

  static class PropertyVisibleListener implements ItemListener
  {
    private IlvSDMTreeView treeView;

    public PropertyVisibleListener(IlvSDMTreeView treeView)
    {
      this.treeView = treeView;
    }

    public void itemStateChanged(ItemEvent e)
      {
        if (e.getStateChange() == ItemEvent.DESELECTED && this.treeView.tree.isPropertyVisible()) {
          this.treeView.tree.setPropertyVisible(false);
        }
        else if (e.getStateChange() == ItemEvent.SELECTED && !this.treeView.tree.isPropertyVisible()) {
          this.treeView.tree.setPropertyVisible(true);
        }
      }
  }

}
