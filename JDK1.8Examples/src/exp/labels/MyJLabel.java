package exp.labels;


import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyJLabel extends JLabel{
  public MyJLabel() {
    setText("<html><body><img src=\"file:///c:/temp/a.jpg\"></body></html>");
  }
  public static void main(String[] args) {
    MyJLabel l = new MyJLabel();
    JFrame f = new JFrame();
    f.getContentPane().add(l);
    f.pack();
    f.setLocation(100,100);
    f.setVisible(true);
  }

}
