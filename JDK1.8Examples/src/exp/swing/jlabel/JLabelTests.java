package exp.swing.jlabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Level;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
//import pt.efacec.se.inoss.stv.oper.specific.comm.CCTVJoystickStatusComponent;


/**
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
public class JLabelTests {
  public JLabelTests() {
  }

  public static void main(String[] args) {
    final ImageIcon imageIcon =
        new ImageIcon(
            "D:\\Projects\\TLC\\Projects\\TLC\\trunk\\tests\\TLC-MdP\\stv\\images\\Console_Green.png");
    final String strConsoleText = "<html><body><center>CCTV<br>Console</center></body></html>";
    final JLabel l = new JLabel(strConsoleText/*, imageIcon, SwingConstants.CENTER*/);
    l.setIcon(imageIcon);
    l.setHorizontalTextPosition(JLabel.CENTER);
    l.setVerticalTextPosition(JLabel.BOTTOM);

    //JLabel l = new JLabel("<html>Label<br><a href=\"\">Efacec site</a></html>");
    JFrame f = new JFrame("");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(l, BorderLayout.CENTER);
    f.pack();
    f.setVisible(true);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
