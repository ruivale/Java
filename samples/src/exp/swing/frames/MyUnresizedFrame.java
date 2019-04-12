package exp.swing.frames;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyUnresizedFrame {
  public MyUnresizedFrame() {
  }

  public static void main(String[] args) {
    final JButton b = new JButton("AJAJAJAJAJA");
    final JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentResized(ComponentEvent event) {

        ComponentListener[] compListeners = frame.getComponentListeners();
        for (int i = 0; i < compListeners.length; i++) {
          frame.removeComponentListener(compListeners[i]);
          System.out.println("Removed comp listener.");
        }

        //frame.setSize(
        //  Math.max(600, frame.getWidth()),
        //Math.max(600, frame.getHeight()));

        final Container cont = frame.getContentPane();
        b.setSize(cont.getWidth(), cont.getHeight());

        //frame.pack();
        frame.validate();
        frame.repaint();

        for (int i = 0; i < compListeners.length; i++) {
          frame.addComponentListener(compListeners[i]);
          System.out.println("Add comp listener.");
        }

      }
    });
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(b, BorderLayout.CENTER);
    frame.setMaximizedBounds(new Rectangle(Integer.MAX_VALUE, 25,
                                           Integer.MAX_VALUE, 500));
    frame.setVisible(true);
  }

}
