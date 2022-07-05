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
 *  Demonstrating create(Class, Object, String)
 *
 *  Because it has the fewest arguments, the first version is the simplest.
 *  The first argument is the EventListener type whose interface you are
 *  implementing. For instance, to respond to a button selection, the argument
 *  would be ActionListener.class to represent the Class object for the
 *  interface. While ActionListener only has one method in the interface,
 *  creating an implementation of an interface in this manner means all
 *  methods of that interface implementation will execute the same code.
 *
 *  The second and third arguments are interrelated. Combined, they say to
 *  invoke the String action method of the Object target. Using reflection
 *  then, you have an ActionListener implementation, but don't have an added
 *  .class file in the file system. Listing 3 duplicates the earlier button
 *  selection example shown in Figure 1, using an EventHandler. Note that the
 *  println() call needs to be moved into a method so that it can be invoked
 *  from the handler.
 *
 *  The code in the create() call of EventHandler simply says, "call our
 *  print() method (this) when the attached ActionListener of the button
 *  needs to be notified." There are, however, a couple side effects.
 *  The first is that the call requires casting to return the appropriate
 *  listener type to satisfy the compiler. The other side effect is that
 *  because the invocation of print() is done indirectly through reflection,
 *  the method must be public (and accept no arguments). This latter feature
 *  of using EventHandler is less of an issue with the other versions of
 *  create().
 *
 *
 * </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class DynamicListenerProxy extends JFrame {
  public DynamicListenerProxy() {
    super("Selection");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JButton button = new JButton("Pick Me");
    Container contentPane = getContentPane();
    contentPane.add(button, BorderLayout.CENTER);
    button.addActionListener(
      (ActionListener)EventHandler.create(
        ActionListener.class,
	this,
	"print")
    );
  }
  public void print() {
    System.out.println("Hello, World!");
  }
  public static void main(String args[]) {
    JFrame frame = new DynamicListenerProxy();
    frame.setSize(200, 100);
    frame.show();
  }
}