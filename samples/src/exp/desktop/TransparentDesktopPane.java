package exp.desktop;

/**
 * Title:        GUI test<p>
 * Description:  Test for the GUI aplication!<p>
 * Copyright:    Copyright (c) Rui Vale<p>
 * Company:      ENT<p>
 * @author Rui Vale
 * @version 1.0
 */

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;

/**
 * Title:        GUI test
 *
 * <p>
 * Description:  Test for the GUI aplication!
 * </p>
 *
 * <p>
 * Copyright:    Copyright (c) Rui Vale
 * </p>
 *
 * <p>
 * Company:      ENT
 * </p>
 *
 * <p></p>
 */
import javax.swing.JDesktopPane;


/**
 * Classe that will be used to incorporate the GI's windows (JInternalFrames).
 */
public class TransparentDesktopPane extends JDesktopPane {
  /**
   * Classe that will be used to incorporate the GI's windows. Remember that
   * if you choose to use a LayoutManager for this class, you must set one.
   * The default layout manager for this class, like it's parent
   * JDesktopPane, is the null layout. If You want to show imutable
   * JInternalFrames then set the hasDesktopManager variable to true and a
   * desktop manager is added to this class disabling the user to move the
   * JInternalFrame.
   *
   * @param hasDesktopManager DOCUMENT ME!
   */
  public TransparentDesktopPane(boolean hasDesktopManager) {
    super();

    // If hasDesktopManager is set to false, the desktop pane allows the
    // internal frames to be dragged and so on ...
    if (hasDesktopManager) {
      setDesktopManager(new ImutableManager());
    }

    setOpaque(false);
    putClientProperty(
      "JDesktopPane.dragMode",
      "outline");
  }

  /**
   *
   *
   * @return DOCUMENT ME!
   */
  public boolean isOpaque() {
    return (false);
  }

  // Just for simulate the setOpaque(false) method.
  protected void paintComponent(Graphics gc) {
    // do nothing
  }

  /**
   * Class which extends the DefaultDesktopManager class and override the
   * dragging processes.
   */
  class ImutableManager extends DefaultDesktopManager {
    // dragging ...
    public void beginDraggingFrame(JComponent frame) { /* Do nothing.*/
    }

    /**
     *
     *
     * @param frame DOCUMENT ME!
     * @param x DOCUMENT ME!
     * @param y DOCUMENT ME!
     */
    public void dragFrame(
      JComponent frame,
      int        x,
      int        y) { /* Do nothing.*/
    }

    /**
     *
     *
     * @param frame DOCUMENT ME!
     */
    public void endDraggingFrame(JComponent frame) { /* Do nothing.*/
    }
  }
}
