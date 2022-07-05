package exp.desktop;

import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

//import javax.swing.event.*;

public class TestInternalFrame
    extends JFrame {
  ImageIcon icon;

  public TestInternalFrame() {
    icon = new ImageIcon("D:\\JBProjects\\exp\\beans.jpg");

    JDesktopPane desktop = new JDesktopPane() {
      public void paintComponent(Graphics g) {
        Dimension d = getSize();
        g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
        setOpaque(false);
        //super.paintComponent(g);
      }
    };
    setContentPane(desktop);

    JInternalFrame internal = new JInternalFrame("Internal Frame");
    desktop.add(internal);
    internal.setLocation(50, 50);
    internal.setSize(300, 300);
    internal.setVisible(true);

    JButton button = new JButton("Hello");
    desktop.add(button);
    button.setLocation(10, 10);
    button.setSize(100, 50);
    button.setVisible(true);
  }

  public static void main(String args[]) {
    TestInternalFrame frame = new TestInternalFrame();
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setVisible(true);
  }
}
