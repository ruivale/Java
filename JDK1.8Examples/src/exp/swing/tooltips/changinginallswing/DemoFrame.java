package exp.swing.tooltips.changinginallswing;


import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;


/**
 * DemoFrame.java
 * This test frame is to demonstrate chnaging of tooltip background
 *
 * @author Rahul Sapkal(rahul@javareference.com)
 */
public class DemoFrame
    extends JFrame {
  public DemoFrame() {
    super("Changing ToolTip Color Demo");

    try {
      //set MyLookAndFeel to change the background color of the tooltip
      //to lightyellow
      UIManager.setLookAndFeel("MyLookAndFeel");
    } catch (Exception ex) {

    }

    getContentPane().setLayout(new FlowLayout());

    JButton b = new JButton();
    //setting multi-line text
    b.setText("<html>Mouse Over<br> me for ToolTip </html>");
    //setting multi-line tool tip
    b.setToolTipText("<html>Hey !<br>I have changed color<br> Hiya </html>");

    getContentPane().add(b);

    JLabel l = new JLabel("What about me");
    l.setToolTipText("Me Too...");

    getContentPane().add(l);
  }

  public static void main(String[] arg) {
    DemoFrame m = new DemoFrame();

    m.setVisible(true);
    m.setSize(new Dimension(300, 150));
    m.validate();
  }
}
