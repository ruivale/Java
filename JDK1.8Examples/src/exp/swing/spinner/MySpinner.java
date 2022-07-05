package exp.swing.spinner;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MySpinner
    extends JSpinner {


  public MySpinner(SpinnerModel sm) {
    super(sm);
  }

  public static void main(String[] a) {

    JPanel p = new JPanel(new BorderLayout());

    Object objs[] = {
        "dvr 1", "dvr 2", "dvr 3", "dvr 4", "dvr 5"};
    SpinnerModel sm = new SpinnerListModel(objs);
    final MySpinner ms = new MySpinner(sm);
    JLabel l = new JLabel("AH!AH!AH!");
    JTextField tf = new JTextField("BH!BH!BH!");


    DefaultEditor c = (DefaultEditor) ms.getEditor();
    c.getTextField().setEditable(false);

    c.getTextField().addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        System.out.println("jComboBoxEquip mouseClicked");

        ms.setValue("dvr 3");

        if (e.getModifiers() == e.BUTTON1_MASK) {
          System.out.println("BUTTON1_MASK");
        } else if (e.getModifiers() == e.BUTTON2_MASK) {
          System.out.println("BUTTON2_MASK");
        } else if (e.getModifiers() == e.BUTTON3_MASK) {
          System.out.println("BUTTON3_MASK");
        }

        if ( (e.getClickCount() % 2) == 0) {
          System.out.println("DUPLO DUPLO CLICK");
        }
      }
    });

    p.add(ms, BorderLayout.CENTER);

    JFrame f = new JFrame();
    f.setTitle("JSpinner");
    f.getContentPane()
        .setLayout(new BorderLayout());
    f.getContentPane()
        .add(p, BorderLayout.CENTER);
    f.setBounds(100, 100, 200, 70);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

  }

}
