package exp.swing.tooltip.html;

import java.awt.BorderLayout;

import javax.swing.*;


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
public class TooltipsWithHTML {
  public TooltipsWithHTML() {
  }

  public static void main(String[] args) {
    TooltipsWithHTML t = new TooltipsWithHTML();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());

    JLabel l = new JLabel("<html><body><a href=\"http://www.sapo.pt\">label</a></body></html>");

    JButton b = new JButton("html tooltip");
    b.setToolTipText("<html><body><head><u> H E A D </u></head><br><i><a href=\"http://www.sapo.pt\">sapo</a><br>"+
                     "<a href=\"http://www.clix.pt\">clix</a></i></body></html>");


    f.getContentPane().add(l, BorderLayout.NORTH);
    f.getContentPane().add(b, BorderLayout.CENTER);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(100,100,400,300);
    f.setVisible(true);
  }
}
