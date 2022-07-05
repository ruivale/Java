/**
 * <p>
 * Classname: exp.laf.closingwinswithescape.ClosingWinsWithEscapeKey
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package exp.laf.closingwinswithescape;


import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Feb 7, 2012, 10:40:10 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ClosingWinsWithEscapeKey {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ClosingWinsWithEscapeKey.class.getName());

  private static final KeyStroke escape = KeyStroke.getKeyStroke("ESCAPE");

  private static final AWTEventListener awtListener = new AWTEventListener() {

    @Override public final void eventDispatched(final AWTEvent event) {
      assert EventQueue.isDispatchThread();
      assert event != null;
      if (event instanceof HierarchyEvent && event.getID() == HierarchyEvent.HIERARCHY_CHANGED) {
        // This event represents a change in the containment hierarcy.
        // Now let's figure out what kind.
        final HierarchyEvent hevent = (HierarchyEvent) event;
        final Component changed = hevent.getChanged();
        if (changed instanceof JRootPane && ((hevent.getChangeFlags()
                                              & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0) && changed.
            isDisplayable()) {
          // Aha!  A JRootPane has just been made displayable!
          final InputMap inputMap = ((JRootPane) changed).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
          if (inputMap != null && inputMap.get(escape) == null) {
            inputMap.put(escape, "close");
          }
        }
      }
    }
  };

  public static final void makeEscapeCloseAllWindows() {
    assert EventQueue.isDispatchThread();
    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    assert toolkit != null;
    toolkit.removeAWTEventListener(awtListener);
    installCloseAction(); // from before; see earlier in this article
    toolkit.addAWTEventListener(awtListener, AWTEvent.HIERARCHY_EVENT_MASK);
  }

  public static final void installCloseAction() {
    assert EventQueue.isDispatchThread();
    ActionMap actionMap = (ActionMap) UIManager.get("RootPane.actionMap");
    if (actionMap == null) {
      actionMap = new ActionMap();
      UIManager.put("RootPane.actionMap", actionMap);
    }
    assert actionMap != null;
    if (actionMap.get("close") == null) {
      actionMap.put("close", new AbstractAction("Close") {

        @Override public final void actionPerformed(final ActionEvent event) {
          assert event != null;
          final JRootPane rootPane = (JRootPane) event.getSource();
          assert rootPane != null;
          final Container container =
              SwingUtilities.getAncestorOfClass(RootPaneContainer.class, rootPane);
          if (container instanceof JInternalFrame) {
            final JInternalFrame internalFrame = (JInternalFrame) container;
            if (internalFrame.isClosable()) {
              internalFrame.doDefaultCloseAction();
            }
          } else {
            final Window window;
            if (container instanceof Window) {
              window = (Window) container;
            } else {
              window = SwingUtilities.getWindowAncestor(rootPane);
            }
            if (window != null) {
              window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            }
          }
        }
      });
    }
  }

 /**
  * The ClosingWinsWithEscapeKey default constructor.
  */
  public ClosingWinsWithEscapeKey(){
    ClosingWinsWithEscapeKey.makeEscapeCloseAllWindows();

    JFrame jd = new JFrame();
    //JDialog jd = new JDialog();
    jd.setTitle("press escape ...");
    jd.setBounds(100, 100, 450, 350);
    jd.add(new JLabel("press escape"));
    jd.setVisible(true);
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("ClosingWinsWithEscapeKey").append("").toString();
  }

  public static void main(final String[] args){
    final ClosingWinsWithEscapeKey clazz = new ClosingWinsWithEscapeKey();
  }
}
