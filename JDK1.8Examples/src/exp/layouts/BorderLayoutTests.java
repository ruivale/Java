package exp.layouts;



import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class BorderLayoutTests {
  JFrame frame = new JFrame();
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JPanel p3 = new JPanel();
  JButton b1 = new JButton("b1");
  JButton b2 = new JButton("b2");
  JButton b3 = new JButton("b3");

  public BorderLayoutTests() {


    this.p1.setLayout(new BorderLayout());
    this.p1.add(this.b2, BorderLayout.CENTER);
    this.p1.add(this.b3, BorderLayout.CENTER);

    b1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){

System.out.println("Clickin in the b1 button!");



System.out.println("Clickin in the b1 button! end");

      }
    });




    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(this.p1, BorderLayout.CENTER);
    frame.getContentPane().add(this.b1, BorderLayout.SOUTH);


    frame.setBounds(50,50,600,400);
    frame.setVisible(true);


  }
  public static void main(String[] args) {
    BorderLayoutTests borderLayoutTests1 = new BorderLayoutTests();
  }
}