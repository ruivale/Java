package exp.ilog.tree;

import javax.swing.*;
import java.beans.*;
import java.util.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import ilog.views.*;

/**
 * This class is an implementation of {@link IlvSDMFrameInterface}
 * that uses a JComponent as the container for an SDM view.
 * This class is useful to display an {@link IlvSDMGrapherView}
 * in an applet, when JFrame is not available.
 */
public class IlvSDMContainerFrame implements IlvSDMFrameInterface
{
  private Container container;
  private IlvSDMDocumentViewInterface documentView;

  /**
   * Creates an <code>IlvSDMContainerFrame</code>.
   * @param container The container used to manage the <code>IlvSDMFrameInterface</code> implementation.
   */
  public IlvSDMContainerFrame(Container container)
  {
    super();
    this.container = container;
  }

  private IlvSDMDocumentController getDocumentController(InternalFrameEvent e)
  {
    return getDocumentView().getDocument().getDocumentController();
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
   * @param documentView The document view to set.
   */
  public void setDocumentView(IlvSDMDocumentViewInterface documentView)
  {
    this.documentView = documentView;
  }

  /**
   * Returns the container passed as a parameter to the constructor.
   */
  public Container getContentPane()
  {
    return this.container;
  }

  /**
   * This method does nothing.
   * @param val This parameter has no effect.
   */
  public void setSelected(boolean val) throws PropertyVetoException
  {
  }

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
  public void setClosable(boolean closable)
  {
  }

  /**
   * This method does nothing.
   * @param maximizable This parameter has no effect.
   */
  public void setMaximizable(boolean maximizable)
  {
  }

  /**
   * This method does nothing.
   * @param iconifiable This parameter has no effect.
   */
  public void setIconifiable(boolean iconifiable)
  {
  }

  /**
   * This method does nothing.
   * @param opaque This parameter has no effect.
   */
  public void setOpaque(boolean opaque)
  {
  }

  /**
   * This method does nothing.
   * @param closed This parameter has no effect.
   */
  public void setClosed(boolean closed)
  {
  }

  /**
   * This method does nothing.
   * @param title This parameter has no effect.
   */
  public void setTitle(String title)
  {
  }

  /**
   * Returns <code>null</code>.
   */
  public String getTitle()
  {
    return null;
  }

  /**
   * This method does nothing.
   * @param resizable This parameter has no effect.
   */
  public void setResizable(boolean resizable)
  {
  }

  /**
   * This method does nothing.
   * @param visible This parameter has no effect.
   */
  public void setVisible(boolean visible)
  {
  }

  /**
   * Sets the size of the container.
   * @param width The width of the container.
   * @param height The height of the container.
   */
  public void setSize(int width, int height)
  {
    this.container.setSize(width, height);
    if(this.container instanceof JComponent)
      ((JComponent)this.container).setPreferredSize(new Dimension(width, height));
  }

  /**
   * Sets the background of the container.
   * @param background The new background color.
   */
  public void setBackground(Color background)
  {
    this.container.setBackground(background);
  }

  /**
   * This method does nothing.
   */
  public void pack()
  {
  }

  /**
   * This method does nothing.
   * @param image This parameter has no effect.
   */
  public void setIconImage(Image image)
  {
  }

  /**
   * Sets the bounds of the frame.
   */
  public void setBounds(Rectangle r)
  {
    this.container.setBounds(r);
  }

  /**
   * Returns the bounds of the frame.
   */
  public Rectangle getBounds()
  {
    return this.container.getBounds(new Rectangle());
  }
}
