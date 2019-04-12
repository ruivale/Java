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
 * $Id: IlvSDMFrame.java,v 1.11 2001/09/14 14:11:45 gdigugli Exp $
 *
 */


package exp.ilog.tree;


import javax.swing.*;
import java.beans.*;
import java.util.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import ilog.views.*;

/**
 * An implementation of {@link ilog.views.sdm.gui.IlvSDMFrameInterface} based on a <code>JFrame</code>.
 */
public class IlvSDMFrame extends JFrame implements IlvSDMFrameInterface
{
  private IlvSDMDocumentViewInterface documentView;
  private IlvSDMDocumentController documentController;

  /**
   * Creates an <code>IlvSDMFrame</code>.
   * @param addDefaultCloseListener If this parameter is <code>true</code>,
   * a default window listener will be installed on the frame. This
   * window listener will call the <code>acceptClose</code> method of the
   * current document in its <code>windowClosing</code> method.
   * If <code>acceptClose</code> returns <code>true</code>,
   * the frame will be hidden and its <code>dispose</code> method will be called.
   */
  public IlvSDMFrame(final boolean addDefaultCloseListener)
  {
    super();
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    if(addDefaultCloseListener){
      addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            if(documentController.getMainDocumentView().getFrame() == IlvSDMFrame.this &&
               documentController.getActiveDocument().acceptClose(IlvSDMFrame.this)) {
              setVisible(false);
              dispose();
            }
            else if (documentController.getMainDocumentView().getFrame() != IlvSDMFrame.this) {
              setVisible(false);
              dispose();
            }
          }
        });
    }
  }

  /**
   * Creates an <code>IlvSDMFrame</code> with a default window listener.
   */
  public IlvSDMFrame()
  {
    this(true);
  }

  /**
   * Overrides the <code>dispose</code> method of the <code>JFrame</code>
   * to notify the document controller when the <code>JFrame</code> is disposed of.
   */
  public void dispose()
  {
    setVisible(false);
    getDocumentController(null).fireActiveDocumentViewChanged(null);
    super.dispose();
  }

  /**
   * Overrides the <code>setVisible</code> method of the <code>JFrame</code>
   * to notify the document controller when the <code>JFrame</code> is visible/hidden.
   * @param b <code>true</code> to show the frame.
   */
  public void setVisible(boolean b)
  {
    super.setVisible(b);
    if (b) {
      getDocument().removeDocumentView(getDocumentView());
      getDocument().addDocumentView(getDocumentView());
    }
    else if (getDocument() != null && getDocumentView() != null) {
      getDocument().removeDocumentView(getDocumentView());
    }
    getDocumentController(null).fireActiveDocumentViewChanged(null);
  }

  private IlvSDMDocument getDocument()
  {
    return this.documentController.getActiveDocument();
  }

  private IlvSDMDocumentController getDocumentController(InternalFrameEvent e)
  {
    return this.documentController;
  }

  /**
   * Returns the implementation of the <code>IlvSDMDocumentViewInterface</code> associated with the frame.
   */
  public IlvSDMDocumentViewInterface getDocumentView()
  {
    return this.documentView;
  }

  /**
   * Sets the implementation of the <code>IlvSDMDocumentViewInterface</code> associated with the frame.
   * @param documentView The document view.
   */
  public void setDocumentView(IlvSDMDocumentViewInterface documentView)
  {
    this.documentView = documentView;
    this.documentController = documentView.getDocument().getDocumentController();
  }

  /**
   * This method does nothing.
   * @param val This parameter has no effect.
   */
  public void setSelected(boolean val) throws PropertyVetoException {}

  /**
   * Returns <code>false</code>.
   */
  public boolean isSelected()
  {
    return false;
  }

  /**
   * This method does nothing.
   * @param closable This parameter has no effect.
   */
  public void setClosable(boolean closable) {}

  /**
   * This method does nothing.
   * @param maximizable This parameter has no effect.
   */
  public void setMaximizable(boolean maximizable) {}

  /**
   * This method does nothing.
   * @param iconifiable This parameter has no effect.
   */
  public void setIconifiable(boolean iconifiable) {}

  /**
   * This method does nothing.
   * @param opaque This parameter has no effect.
   */
  public void setOpaque(boolean opaque) {}

  /**
   * Sets the visibility of the frame.
   * @param closed <code>true</code> to show the frame.
   */
  public void setClosed(boolean closed)
  {
    setVisible(!closed);
  }
}
