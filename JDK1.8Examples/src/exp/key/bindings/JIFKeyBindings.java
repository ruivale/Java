package exp.key.bindings;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JIFKeyBindings
    extends JFrame {
  JDesktopPane desktop;
  public JIFKeyBindings() {
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } catch (Exception ex) {
    }
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    desktop = new JDesktopPane();
    JInternalFrame frame1 = null;
    for (int i = 0; i < 5; i++) {
      frame1 = new JInternalFrame("Fr " + i, true, true, true, true);
      frame1.setBounds(i * 50, i * 50, 200, 200);
      desktop.add(frame1);
      frame1.setVisible(true);
    }

    getContentPane().add(desktop, BorderLayout.CENTER);

    ActionMap amap = SwingUtilities.getUIActionMap(desktop);
    amap.remove("close");
    amap.put("close", new CloseAction());
    InputMap map = SwingUtilities.getUIInputMap(desktop, 1);
    map.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER,
                                   java.awt.event.InputEvent.CTRL_DOWN_MASK),
            "close");
  }

  private final class CloseAction
      extends AbstractAction {
    public void actionPerformed(ActionEvent e) {
      try {
        JInternalFrame f = desktop.getSelectedFrame();
        if (f != null && f.isClosable()) {
          f.setClosed(true);
          JInternalFrame f1 = desktop.getSelectedFrame();
          if (f1 != null) {
            f1.requestFocus();
          }
        }
      } catch (Exception ex) {
        System.out.println(ex);
      }
    }
  }

  public static void main(String[] args) {
    JIFKeyBindings frame = new JIFKeyBindings();
    frame.setSize(600, 600);
    frame.setVisible(true);
  }
}
