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
 * Using create(Class, Object, String, String)
 *
 * The next version of create() adds a fourth argument, along with an
 * additional use for the third. The first String argument now can also
 * represent the name of a writeable JavaBeans property of the Object
 * argument. So, in the case of a JButton, if the third argument was text,
 * then this would equate to a setText() call, where the argument to the
 * method was represented by the String sent into the fourth argument.
 *
 * The fourth argument allows you to access a readable property of the
 * incoming event to set the writeable property passed in as the third
 * argument. To demonstrate, Listing 4 offers a JTextField component for
 * input and a JLabel component for text display. When you press the Return
 * key in the JTextField, an ActionEvent is generated and the text of the
 * label is changed to the contents of the JTextField.
 *
 * Listing 4. Demonstrating create(Class, Object, String, String)
 *
 *
 *
 * </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class TextFieldHandler extends JFrame {
  public TextFieldHandler() {
    super("Selection");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JTextField text = new JTextField();
    JLabel label = new JLabel();
    Container contentPane = getContentPane();
    contentPane.add(text, BorderLayout.NORTH);
    contentPane.add(label, BorderLayout.CENTER);
    text.addActionListener(
      (ActionListener)EventHandler.create(
        ActionListener.class,
	label,
	"text",
	"source.text")
    );
  }
  public static void main(String args[]) {
    JFrame frame = new TextFieldHandler();
    frame.setSize(200, 150);
    frame.show();
  }
}
