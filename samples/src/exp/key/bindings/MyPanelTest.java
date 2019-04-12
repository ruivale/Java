package exp.key.bindings;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyPanelTest extends JPanel {
  public MyPanelTest() {

    setLayout(new BorderLayout());
    JButton b = new JButton("AAA");
    add(b, BorderLayout.CENTER);

    // Enabling a closing door.
    Action zoomin = new AbstractAction() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("zoomin");
      }
    };
    //this.getInputMap().put(KeyStroke.getKeyStroke('z'), "focus");
    b.getInputMap().put(KeyStroke.getKeyStroke("ctrl Z"), "zoomin");
    b.getActionMap().put("zoomin", zoomin);

    ActionMap am = b.getActionMap();
    Object[] keys = am.allKeys();
    for(int i=0; i<keys.length; ++i)
        System.out.println(keys[i]);


 /*
    ActionMap amap = SwingUtilities.getUIActionMap(this);
    amap.remove("close");
    amap.put("close", new CloseAction());
    InputMap map = SwingUtilities.getUIInputMap(this, 1);
    map.put(KeyStroke.getKeyStroke(
      java.awt.event.KeyEvent.VK_ENTER,java.awt.event.InputEvent.ALT_GRAPH_MASK),
      "close");
   */
  }

  private final class CloseAction
      extends AbstractAction {
    public void actionPerformed(ActionEvent e) {
      System.out.println("AAAAAAAAAAAAAAAAAAAA");
    }

  }



  public static void main(String[] args) {
    MyPanelTest p = new MyPanelTest();
    JFrame f = new JFrame();
    f.setTitle("Key bindings ");
    f.getContentPane()
        .setLayout(new BorderLayout());
    f.getContentPane()
        .add(p, BorderLayout.CENTER);
    f.setBounds(100, 100, 200, 70);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }

}
