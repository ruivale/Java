package exp.swing.tooltips;


import java.awt.event.*;

import javax.swing.*;


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
 *
 * @author not attributable
 * @version 1.0
 */
public class ShowToolTipsImmediately {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** .. */
  static MouseHandler MOUSE_HANDLER = new MouseHandler();

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new ShowToolTipsImmediately object.
   */
  public ShowToolTipsImmediately() {
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    ShowToolTipsImmediately showtooltipsimmediately =
      new ShowToolTipsImmediately();
  }

  /**
   * Insert doc ...
   *
   * @param c  Insert doc ...
   */
  public static void registerComponent(JComponent c) {
    //ensure InputMap and ActionMap are created
    InputMap  imap = c.getInputMap();
    ActionMap amap = c.getActionMap();

    //put dummy KeyStroke into InputMap if is empty:
    boolean     removeKeyStroke = false;
    KeyStroke[] ks = imap.keys();

    if((ks==null) || (ks.length==0)) {
      imap.put(
        KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0),
        "backSlash");
      removeKeyStroke = true;
    }

    //now we can register by ToolTipManager
    ToolTipManager.sharedInstance()
                  .registerComponent(c);

    //and remove dummy KeyStroke
    if(removeKeyStroke) {
      imap.remove(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0));
    }

    //now last part - add appropriate MouseListener and
    //hear to mouseEntered events
    c.addMouseListener(MOUSE_HANDLER);
  }

  //~ Inner Classes ////////////////////////////////////////////////////////////

//implementation of MouseHandler
  static class MouseHandler
      extends MouseAdapter {
    public void mouseEntered(MouseEvent e) {
      JComponent c      = (JComponent)e.getComponent();
      Action     action = c.getActionMap()
                           .get("postTip");

      //it is also possible to use own Timer to display
      //ToolTip with custom delay, but here we just
      //display it immediately
      if(action!=null) {
        action.actionPerformed(
          new ActionEvent(c, ActionEvent.ACTION_PERFORMED, "postTip"));
      }
    }
  }
}
