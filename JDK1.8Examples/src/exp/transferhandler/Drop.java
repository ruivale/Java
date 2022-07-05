package exp.transferhandler;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.text.JTextComponent;


/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class Drop extends JFrame {
  private JTextComponent jText;

  public Drop() {
    super("Drag n Drop Demo");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(300, 400);

    createTextArea();
    createClearButton();
  }

  private void createTextArea() {
    jText = new JEditorPane();
    getContentPane().add(jText, BorderLayout.CENTER);
  }

  private void createClearButton() {
    JButton clear = new JButton("Clear");
    clear.addActionListener(new Clearer());
    getContentPane().add(clear, BorderLayout.SOUTH);
  }

  public static void main(String[] args) {
    new Drop().setVisible(true);
  }

  private class Clearer implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      jText.setText("");
    }
  }
}
