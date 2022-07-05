package exp.java.awt.events;


import java.awt.*;
import java.awt.event.*;


public class EventTest
    extends Frame {
  public EventTest(String title) {
    super(title);
  }

  void init() {
    setSize(100, 100);
    setVisible(true);
    getToolkit().addAWTEventListener(new AWTEventListener() {
      public void eventDispatched(AWTEvent e) {
        System.out.println(e + "[" + e.getID() + "]");
      }
    },
        AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK |
        AWTEvent.MOUSE_MOTION_EVENT_MASK);
  }

  public static void main(String args[]) {
    EventTest et = new EventTest("Monitoring events:");
    et.init();
  }
}
