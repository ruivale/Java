package exp.swing.borders.titledborder.checkbox;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
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
public class CompTitledPaneExample extends JFrame {

  public CompTitledPaneExample() {
    super("CompTitledPaneExample2");
    JCheckBox title = new JCheckBox("Titlesdsdsd");
    title.setSelected(true);
    final TitledBorderCompPane p1 = new TitledBorderCompPane(title);
    title.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        p1.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
      }
    });
    APanel p2 = new APanel();
    p1.setTransmittingAllowed(true);
    p1.setTransmitter(p2);
    p1.getContentPane().add(p2);
    getContentPane().add(p1, BorderLayout.CENTER);
  }

  class APanel extends JPanel implements StateChangingDescendant {
    JButton    button;
    JTextField textField;

    APanel() {
      button = new JButton("abc");
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("Ouch!");
        }
      });
      textField = new JTextField(10);
      textField.setText("text");
      add(button   , BorderLayout.NORTH);
      add(textField, BorderLayout.SOUTH);
    }

    public void setDescendantsEnabled(boolean enable) {
      button.setEnabled(enable);
      textField.setEnabled(enable);
    }
  }


  public static void main (String args[]) {
    CompTitledPaneExample frame = new CompTitledPaneExample();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(280, 110);
    frame.setVisible(true);
  }
}
