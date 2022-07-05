package exp.swing.tooltip.accelerated;


import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.*;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class AcceleratorToolTip {
  public AcceleratorToolTip() {

    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());

    Action a = new AA("aaaaa");
    JButton b = new JButton(enableAcceleratedTooltips(a));


    f.getContentPane().add(b);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(100,100,400,300);
    f.setVisible(true);
  }

  public static void main(String s[]){
    new AcceleratorToolTip();
  }

  /**
   * converts KeyStroke to readable string format.
   * this can be used to show the accelerator along with the tooltip for
   * toolbar buttons
   *
   * this code is extracted from javax.swing.plaf.basic.BasicMenuItemUI class
   */
  public static String getAcceleratorText(KeyStroke accelerator) {
    String acceleratorDelimiter = UIManager.getString(
        "MenuItem.acceleratorDelimiter");
    if (acceleratorDelimiter == null) {
      acceleratorDelimiter = "+";
    }

    String acceleratorText = "";
    if (accelerator != null) {
      int modifiers = accelerator.getModifiers();
      if (modifiers > 0) {
        acceleratorText = KeyEvent.getKeyModifiersText(modifiers);
        acceleratorText += acceleratorDelimiter;
      }

      int keyCode = accelerator.getKeyCode();
      if (keyCode != 0) {
        acceleratorText += KeyEvent.getKeyText(keyCode);
      } else {
        acceleratorText += accelerator.getKeyChar();
      }
    }
    return acceleratorText;
  }

  public static Action enableAcceleratedTooltips(Action action) {
    String accelerator = getAcceleratorText((KeyStroke) action.getValue(Action.
        ACCELERATOR_KEY));
    action.putValue(Action.SHORT_DESCRIPTION,
                    action.getValue(Action.NAME) + "   " + accelerator);
    return action;
  }

  class AA extends AbstractAction{
    AA(String t){
      super(t);
    }

    public void actionPerformed(ActionEvent e){
      System.out.println("just clicked in "+e.toString()+".");
    }
  }

}
