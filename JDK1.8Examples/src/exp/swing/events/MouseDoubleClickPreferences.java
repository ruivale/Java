package exp.swing.events;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class MouseDoubleClickPreferences
    extends JPanel
    implements ChangeListener {
  JPanel panel;
  JSlider slider;

  JLabel label;

  JButton testButton;
  DoubleClickButtonModel doubleClickButtonModel;

  MouseDoubleClickPreferences() {
    super();

    setBorder(new TitledBorder("Mouse"));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // Slider

    panel = new JPanel();
    panel.setBorder(new TitledBorder("Double Click Speed"));

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    slider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 500);
    slider.addChangeListener(this);
    //slider.setBorder( new TitledBorder("Double Click Speed"));

    //

    label = new JLabel();

    label.setText(slider.getValue() + " milliseconds");

    panel.add(slider);
    panel.add(Box.createVerticalGlue());
    panel.add(label);
    panel.add(Box.createVerticalGlue());

    // Test Button

    testButton = new JButton("Test");

    testButton.addMouseListener(new MouseAdapter(){
      public void mouseClicked(final MouseEvent e){
        if(e.getClickCount() > 1){
System.out.println("Nbr of clicks is 2.");
        }else /*if(e.getClickCount() == 1)*/{
System.out.println("Nbr of clicks is 1.");
        }
      }
    });


    doubleClickButtonModel = new DoubleClickButtonModel(slider.getValue());
    testButton.setModel(doubleClickButtonModel);

    //

    testButton.setMinimumSize(new Dimension(100, 100));
    testButton.setPreferredSize(new Dimension(100, 100));
    testButton.setMaximumSize(new Dimension(100, 100));
    testButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    testButton.setAlignmentY(JComponent.CENTER_ALIGNMENT);

    //

    add(panel);
    add(Box.createVerticalGlue());
    add(testButton);
    add(Box.createVerticalGlue());
  }

  // ChangeListener

  public void stateChanged(ChangeEvent e) {
    if (e.getSource() == slider) {
      if (!slider.getValueIsAdjusting()) {
        doubleClickButtonModel.setPeriod(slider.getValue());
      }
      label.setText(slider.getValue() + " milli seconds");
    }
  }

  //

  public static void main(String[] args) {
    JFrame frame = new JFrame("Mouse");

    //JDialog dialog = new JDialog();

    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    MouseDoubleClickPreferences panel = new MouseDoubleClickPreferences();

    frame.getContentPane().add(panel);

    frame.pack();
    frame.setVisible(true);
  }
}

// Model to override button behaviour.

class DoubleClickButtonModel
    extends DefaultButtonModel {
  private final static long NO_CLICK = -1;
  public final static long DEFAULT_DOUBLE_CLICK_IN_MILLIS = 500;

  private long last = NO_CLICK;
  private long current = NO_CLICK;
  private long doubleClickInMillis;

  //

  DoubleClickButtonModel() {
    super();
    doubleClickInMillis = DEFAULT_DOUBLE_CLICK_IN_MILLIS;
  }

  DoubleClickButtonModel(int period) {
    super();
    doubleClickInMillis = period;
  }

  //

  public void setPeriod(long period) {
    doubleClickInMillis = period;
  }

  //

  //public void setArmed(boolean b) {}

  public void setPressed(boolean b) {
    if ( (isPressed() == b) || !isEnabled()) {
      return;
    }

    if (b) {
      last = current;
      current = System.currentTimeMillis();

      if ( (current - last) <= doubleClickInMillis) {
        stateMask |= PRESSED;
        current = NO_CLICK;
      }
    } else {
      stateMask &= ~PRESSED;
    }

    if (!isPressed() && isArmed()) {
      int modifiers = 0;
      AWTEvent currentEvent = EventQueue.getCurrentEvent();
      if (currentEvent instanceof InputEvent) {
        modifiers = ( (InputEvent) currentEvent).getModifiers();
      } else if (currentEvent instanceof ActionEvent) {
        modifiers = ( (ActionEvent) currentEvent).getModifiers();
      }
      fireActionPerformed(
          new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                          getActionCommand(),
                          EventQueue.getMostRecentEventTime(),
                          modifiers));
    }

    fireStateChanged();
  }
}
