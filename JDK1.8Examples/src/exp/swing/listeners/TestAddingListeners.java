package exp.swing.listeners;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;


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
public class TestAddingListeners extends JPanel {

  String strA;

  public TestAddingListeners(String strA) {

    this.strA = strA;

    JButton b1 = new JButton("AAAAAAAa");
    b1.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
        System.out.println("1111111111111111111111");
      }
    });
    b1.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
        System.out.println("222222222222222222");
      }
    });
    setLayout(new BorderLayout());
    add(b1);
  }

  public static void main(String[] args) {
    TestAddingListeners t = new TestAddingListeners("a");
    JFrame f = new JFrame("test editable comboboxes");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(t, BorderLayout.CENTER);
    f.setBounds(100, 100, 300, 200);
    f.setVisible(true);
  }
}
