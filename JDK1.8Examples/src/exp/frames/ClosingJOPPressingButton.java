package exp.frames;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ClosingJOPPressingButton extends JFrame {

  private static JOptionPane jop = new JOptionPane();

  public ClosingJOPPressingButton() {

    setBounds(100, 100, 400, 300);
    setVisible(true);

    final JFrame f = this;

    final JButton jb = new JButton("press");
    jb.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        //final JDialog dialog = jop.createDialog(f, "JOP");
        final JDialog d = new JDialog(f, "d", false);

        d.addWindowListener(
            new WindowAdapter() {
              public void windowActivated(WindowEvent e) {
                System.out.println("windowActivated");
              }

              public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
              }

              public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                d.setVisible(false);
              }

              public void windowDeactivated(WindowEvent e) {
                System.out.println("windowDeactivated");
              }

              public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");
              }

              public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
              }

              public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
              }
            });

        //dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //dialog.show();

        d.setSize(150, 100);
        d.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        d.setVisible(true);

      }
    });

    setLayout(new BorderLayout());
    add(jb);

  }

  public static void main(String[] args) {

    new ClosingJOPPressingButton();

  }
}
