package exp.awt.robot.myedt;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MyEventQueue
    extends EventQueue {
  protected void dispatchEvent(AWTEvent event) {
    // the only functionality I add is that I print out all the events
    System.out.println(event);
    super.dispatchEvent(event);
  }
}
