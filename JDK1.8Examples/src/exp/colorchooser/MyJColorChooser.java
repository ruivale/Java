package exp.colorchooser;

import javax.swing.*;
import java.awt.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyJColorChooser extends JColorChooser{
  public MyJColorChooser() {
  }

  public static void main(String a[]){
    JFrame f = new JFrame("IAH");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(new MyJColorChooser(), BorderLayout.CENTER);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
  }
}
