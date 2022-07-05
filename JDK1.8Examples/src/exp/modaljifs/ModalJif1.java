package exp.modaljifs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ModalJif1 {


  static class ModalAdapter
      extends InternalFrameAdapter {
    Component glass;

    public ModalAdapter(Component glass) {
      this.glass = glass;

      // Associate dummy mouse listeners
      // Otherwise mouse events pass through
      MouseInputAdapter adapter =
        new MouseInputAdapter(){};
      glass.addMouseListener(adapter);
      glass.addMouseMotionListener(adapter);
    }

    public void internalFrameClosed(
        InternalFrameEvent e) {
      glass.setVisible(false);
    }
  }

  public static void main(String args[]) {
    final JFrame frame = new JFrame(
      "Modal Internal Frame");
    frame.setDefaultCloseOperation(
      JFrame.EXIT_ON_CLOSE);

    final JDesktopPane desktop = new JDesktopPane();

    ActionListener showModal =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // Manually construct a message frame popup
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Hello, World");
        optionPane.setMessageType(
          JOptionPane.INFORMATION_MESSAGE);
        JInternalFrame modal = optionPane.
          createInternalFrame(desktop, "Modal");

        // create opaque glass pane
        JPanel glass = new JPanel();
        glass.setOpaque(false);

        // Attach modal behavior to frame
        modal.addInternalFrameListener(
          new ModalAdapter(glass));

        // Add modal internal frame to glass pane
        glass.add(modal);

        // Change glass pane to our panel
        frame.setGlassPane(glass);

        // Show glass pane, then modal dialog
        glass.setVisible(true);
        modal.setVisible(true);

        System.out.println("Returns immediately");
      }
    };

    JInternalFrame internal =
      new JInternalFrame("Opener");
    desktop.add(internal);

    JButton button = new JButton("Open");
    button.addActionListener(showModal);

    Container iContent = internal.getContentPane();
    iContent.add(button, BorderLayout.CENTER);
    internal.setBounds(25, 25, 200, 100);
    internal.setVisible(true);

    Container content = frame.getContentPane();
    content.add(desktop, BorderLayout.CENTER);
    frame.setSize(500, 300);
    frame.setVisible(true);
  }
}

