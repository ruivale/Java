package exp.swing.tooltips.customized;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CustomizedTooltip {

  public static void main(String args[]) {
    JFrame frame = new JFrame("JToolTip Sample");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton b1 = new JButton("Button 1") {
      public JToolTip createToolTip() {
        JToolTip tip = super.createToolTip();
        // Make required changes during creation
        tip.setForeground(Color.RED);
        return tip;
      }

      public Point getToolTipLocation(MouseEvent event) {
        // return a modified location
        return new Point((event.getX() + 100), (event.getY() + 100));
      }

    };

    b1.setToolTipText("HELLO PARTNER ! IM A MODIFIED TOOLTIP.");

    frame.getContentPane().add(b1, BorderLayout.NORTH);

    frame.setSize(300, 150);
    frame.setVisible(true);
  }

}
