package exp.swing.jbutton;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import exp.filechoosers.ShowUIDefaults;


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
public class TestJButtons extends JPanel {

  /**
   *
   */
  public TestJButtons() {
    final JButton b = new JButton("bbbbb");
    b.addActionListener(new ActionListener(){
      boolean bool = false;
      Color backColor = b.getBackground();
      public void actionPerformed(ActionEvent e){
        System.out.println("Button - actionPerformed");

        bool = !bool;

        if(bool){
          b.setBackground(Color.gray);
        }else{
          b.setBackground(backColor);
        }
      }
    });

    setLayout(new BorderLayout());
    add(b, BorderLayout.CENTER);

  }

  public static void main(String[] args) {
    TestJButtons t = new TestJButtons();
    JFrame f = new JFrame("......");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(t, BorderLayout.CENTER );
    f.setBounds(100,100,200,200);
    f.setVisible(true);
  }
}
