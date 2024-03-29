package jdk1_6examples.javax.modal;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DualModal {
  public static void main(String args[]) {
    final JFrame frame1 = new JFrame("Left");
    final JFrame frame2 = new JFrame("Right");
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton button1 = new JButton("Left");
    JButton button2 = new JButton("Right");
    frame1.add(button1);
    frame2.add(button2);
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        JOptionPane pane = new JOptionPane("New label",
                                           JOptionPane.QUESTION_MESSAGE);
        pane.setWantsInput(true);
        JDialog dialog = pane.createDialog(frame2, "Enter Text");
        // dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setModalityType(Dialog.ModalityType.MODELESS);
        dialog.setVisible(true);
        String text = (String) pane.getInputValue();

        if (!JOptionPane.UNINITIALIZED_VALUE.equals(text) &&
            text.trim().length() > 0) {
          source.setText(text);
        }
      }
    };
    button1.addActionListener(listener);
    button2.addActionListener(listener);
    frame1.setBounds(100, 100, 200, 200);
    frame1.setVisible(true);
    frame2.setBounds(400, 100, 200, 200);
    frame2.setVisible(true);
  }
}
