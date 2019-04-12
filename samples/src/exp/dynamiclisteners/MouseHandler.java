package exp.dynamiclisteners;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;

/**
 * <p>Title: </p>
 * <p>
 *
 *
 * Description:
 *
 * Using create(Class, Object, String, String, String)
 *
 * The final version of create() is what the other two wind up using in the
 * end, passing null for arguments that aren't available in the other calls.
 * Where the other versions of create() required you to do the same thing for
 * all methods of the listener interface, this last one allows you to specify
 * different actions to invoke for each listener interface method. So, with a
 * MouseListener, you could invoke one action for mousePressed(), another for
 * mouseReleased(), and yet another for mouseClicked(). Listing 5 demonstrates
 * this final version of create() with just a couple of simple printing methods
 * for mouse pressed/released events:
 *
 *
 * Listing 5. Demonstrating create(Class, Object, String, String, String)
 *
 * There is nothing really remarkable about this program, just a big empty
 * screen where you press and release the mouse. Notice that two mouse
 * listeners are attached to the screen, though, instead of just one. For
 * each of the listeners, the other methods are essentially stubbed out.
 * Also, notice that the pressed() and released() methods get an argument of
 * the event's Point. For those methods to accept no argument would require a
 * null where point was specified.
 *
 *
 * </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class MouseHandler extends JFrame {
  public MouseHandler() {
    super("Press and Release Mouse");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container contentPane = getContentPane();
    contentPane.addMouseListener(
      (MouseListener)EventHandler.create(
        MouseListener.class,
        this,
        "pressed",
        "point",
        "mousePressed")
    );
    contentPane.addMouseListener(
      (MouseListener)EventHandler.create(
        MouseListener.class,
        this,
        "released",
        "point",
        "mouseReleased")
    );
  }
  public void pressed(Point p) {
    System.out.println("Pressed at: " + p);
  }
  public void released(Point p) {
    System.out.println("Released at: " + p);
  }
  public static void main(String args[]) {
    JFrame frame = new MouseHandler();
    frame.setSize(400, 400);
    frame.show();
  }
}
