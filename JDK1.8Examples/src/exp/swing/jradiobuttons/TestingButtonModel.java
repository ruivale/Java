package exp.swing.jradiobuttons;

import javax.swing.*;
import java.awt.*;
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
public class TestingButtonModel {
  public TestingButtonModel() {

    JPanel p  =new JPanel(new GridLayout(4,1));
    JRadioButton r1 = new JRadioButton("11111");
    r1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("R1 r1 r1 r1");
      }
    });
    JRadioButton r2 = new JRadioButton("22222");
    r2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("R2 r2 r2 r2");
      }
    });
    JRadioButton r3 = new JRadioButton("33333");
    r3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("R3 r3 r3 r3");
      }
    });
    p.add(r1);
    p.add(r2);
    p.add(r3);
    final ButtonGroup bg = new ButtonGroup();
    bg.add(r1);
    bg.add(r2);
    bg.add(r3);

    JButton b = new JButton("bbbbb");
    b.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        ButtonModel bm = bg.getSelection();
        bm.setPressed(true);
      }
    });
    p.add(b);


    JFrame f = new JFrame("AAAA");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(p, BorderLayout.CENTER);
    f.setBounds(100,100,300,200);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

  }

  public static void main(String[] args) {
    TestingButtonModel testingbuttonmodel = new TestingButtonModel();
  }
}
