package exp.cursors;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

import java.awt.BorderLayout;

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
public class TestCursors extends JPanel{
  public TestCursors() {
    final JButton b1 = new JButton("b1");
    b1.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        System.out.println("CLICK 1111111111111111111");
      }
    });
    final JButton b2 = new JButton("b2");
    b2.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        System.out.println("CLICK 22222222222222");
      }
    });

    JPanel p3 = new JPanel(new BorderLayout());
    final JButton b3 = new JButton("b3");
    b3.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        System.out.println("CLICK 3333333");
      }
    });
    p3.add(b3, BorderLayout.CENTER);

    this.setLayout(new GridLayout(1, 3));
    this.add(b1);
    this.add(b2);
    this.add(p3);
  }

  public static void main(String[] args) {
    TestCursors t = new TestCursors();
    JFrame f = new JFrame("NetVideoRecRecordings");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(t, BorderLayout.CENTER);

    //f.setBounds(100, 100, 600, 500);
    f.pack();
    f.setVisible(true);
  }
}
