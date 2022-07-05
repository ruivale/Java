package exp.swing.glass;


/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c)
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class GlassComponent
    extends JComponent
    implements AWTEventListener {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  Toolkit toolkit      = null;

  /** .. */
  Window parentWindow = null;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new GlassComponent object.
   */
  public GlassComponent() {
    super();
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    setOpaque(false);
    toolkit = Toolkit.getDefaultToolkit();
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param visible  Insert doc ...
   */
  public void setVisible(boolean visible) {
    if(visible==true) {
      if(this.parentWindow==null) {
        this.parentWindow = SwingUtilities.windowForComponent(this);
      }

      Toolkit.getDefaultToolkit()
             .addAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK);
      Toolkit.getDefaultToolkit()
             .addAWTEventListener(this, AWTEvent.MOUSE_MOTION_EVENT_MASK);
      Toolkit.getDefaultToolkit()
             .addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);
    } else {
      Toolkit.getDefaultToolkit()
             .removeAWTEventListener(this);
    }

    super.setVisible(visible);
  }

  /**
   * Insert doc ...
   *
   * @param event  Insert doc ...
   */
  public void eventDispatched(AWTEvent event) {
    if(event instanceof KeyEvent && event.getSource() instanceof Component) {
      if(SwingUtilities.windowForComponent((Component)event.getSource())==this.parentWindow) {
        Toolkit.getDefaultToolkit()
               .beep();
        ((KeyEvent)event).consume();
      }
    } else if(event instanceof MouseEvent &&
          event.getSource() instanceof Component) {
      if(SwingUtilities.windowForComponent((Component)event.getSource())==this.parentWindow) {
        Toolkit.getDefaultToolkit()
               .beep();
        ((MouseEvent)event).consume();
      }
    }
  }
}
