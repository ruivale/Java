package exp.joptionpanes;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class Options
    extends JFrame {
  private static class FrameShower
      implements Runnable {
    final Frame frame;
    public FrameShower(Frame frame) {
      this.frame = frame;
    }

    public void run() {
      frame.show();
    }
  }

  public static JOptionPane getNarrowOptionPane(
      int maxCharactersPerLineCount) {
    // Our inner class definition
    class NarrowOptionPane
        extends JOptionPane {
      int maxCharactersPerLineCount;
      NarrowOptionPane(
          int maxCharactersPerLineCount) {
        this.maxCharactersPerLineCount =
            maxCharactersPerLineCount;
      }

      public int getMaxCharactersPerLineCount() {
        return maxCharactersPerLineCount;
      }
    }

    return new NarrowOptionPane(
        maxCharactersPerLineCount);
  }

  public static JSlider getSlider(
      final JOptionPane optionPane) {
    JSlider slider = new JSlider();
    slider.setMajorTickSpacing(10);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    ChangeListener changeListener =
        new ChangeListener() {
      public void stateChanged(
          ChangeEvent changeEvent) {
        JSlider theSlider = (
            JSlider) changeEvent.getSource();
        if (!theSlider.getValueIsAdjusting()) {
          optionPane.setInputValue(new Integer(
              theSlider.getValue()));
        }
      }
    };
    slider.addChangeListener(changeListener);
    return slider;
  }

  public Options() {
    super("JOptionPane Usage");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());
    JButton message = new JButton("Message");
    ActionListener messageListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
            Options.this, "Hello, World");
      }
    };
    message.addActionListener(messageListener);
    contentPane.add(message);
    JButton confirm = new JButton("Confirm");
    ActionListener confirmListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int returnValue =
            JOptionPane.showConfirmDialog(
            Options.this, "Lunch?");
        String message = null;
        if (returnValue == JOptionPane.YES_OPTION) {
          message = "Yes";
        } else if (
            returnValue == JOptionPane.NO_OPTION) {
          message = "No";
        } else if (
            returnValue == JOptionPane.CANCEL_OPTION) {
          message = "Cancel";
        } else if (
            returnValue == JOptionPane.CLOSED_OPTION) {
          message = "Closed";
        }
        System.out.println("User selected: " + message);
      }
    };
    confirm.addActionListener(confirmListener);
    contentPane.add(confirm);
    JButton inputText = new JButton("Input Text");
    ActionListener inputTextListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String value =
            JOptionPane.showInputDialog("Name");
        System.out.println("Name: " + value);
      }
    };
    inputText.addActionListener(inputTextListener);
    contentPane.add(inputText);
    JButton inputCombo = new JButton("Input Combo");
    ActionListener inputComboListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String smallList[] = {
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        };
        String value =
            (String) JOptionPane.showInputDialog(
            Options.this, "Which Day?", "Day",
            JOptionPane.QUESTION_MESSAGE, null,
            smallList, smallList[smallList.length - 1]);
        System.out.println("Day: " + value);
      }
    };
    inputCombo.addActionListener(inputComboListener);
    contentPane.add(inputCombo);
    JButton inputList = new JButton("Input List");
    ActionListener inputListListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object largeList[] =
            System.getProperties().keySet().toArray();
        String value =
            (String) JOptionPane.showInputDialog(
            Options.this, "Which Property?", "Property",
            JOptionPane.QUESTION_MESSAGE, null,
            largeList, largeList[largeList.length - 1]);
        System.out.println("Property: " + value);
      }
    };
    inputList.addActionListener(inputListListener);
    contentPane.add(inputList);
    JButton all = new JButton("All");
    ActionListener allListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String options[] = {
            "Yes", "Not Now", "Go Away"};
        int value = JOptionPane.showOptionDialog(
            Options.this,
            "Lunch?",
            "Lunch Time",
            JOptionPane.YES_NO_OPTION,
            // Message type
            JOptionPane.QUESTION_MESSAGE,
            null, // Use default icon for message type
            options,
            options[1]);
        if (value == JOptionPane.CLOSED_OPTION) {
          System.out.println("Closed window");
        } else {
          System.out.println(
              "Selected: " + options[value]);
        }
      }
    };
    all.addActionListener(allListener);
    contentPane.add(all);
    JButton wide = new JButton("Wide");
    ActionListener wideListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String msg =
            "This is a really long message. " +
            "This is a really long message. " +
            "This is a really long message. " +
            "This is a really long message. " +
            "This is a really long message. " +
            "This is a really long message.";
        JOptionPane pane = getNarrowOptionPane(50);
        pane.setMessage(msg);
        pane.setMessageType(
            JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog =
            pane.createDialog(Options.this, "Width 50");
        dialog.show();
      }
    };
    wide.addActionListener(wideListener);
    contentPane.add(wide);
    JButton twoLine = new JButton("Two Line");
    ActionListener twoLineListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String msg[] = {
            "Welcome", "Home"};
        JOptionPane.showMessageDialog(
            Options.this, msg);
      }
    };
    twoLine.addActionListener(twoLineListener);
    contentPane.add(twoLine);
    JButton slider = new JButton("Slider");
    ActionListener sliderListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane optionPane = new JOptionPane();
        JSlider slider = getSlider(optionPane);
        Object msg[] = {
            "Select a value:", slider};
        optionPane.setMessage(msg);
        optionPane.setMessageType(
            JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(
            JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(
            Options.this, "Select Value");
        dialog.show();
        Object value = optionPane.getValue();
        if (value == null || ! (value instanceof Integer)) {
          System.out.println("Closed");
        } else {
          int i = ( (Integer) value).intValue();
          if (i == JOptionPane.CLOSED_OPTION) {
            System.out.println("Closed");
          } else if (i == JOptionPane.OK_OPTION) {
            System.out.println("OKAY - value is: " +
                               optionPane.getInputValue());
          } else if (i == JOptionPane.CANCEL_OPTION) {
            System.out.println("Cancelled");
          }
        }
      }
    };
    slider.addActionListener(sliderListener);
    contentPane.add(slider);
    setSize(300, 200);
  }

  public static void main(String args[]) {
    UIManager.put("AuditoryCues.playList",
                  UIManager.get("AuditoryCues.defaultCueList"));
    JFrame frame = new Options();
    Runnable runner = new FrameShower(frame);
    EventQueue.invokeLater(runner);
  }
}
