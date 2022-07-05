package exp.swing.jdialog;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

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
public class MyJDialogEDTTests {
  public MyJDialogEDTTests() {
    final JFrame f = new JFrame("f r a m e");
    final JDialog d = new JDialog(f, true);
    final JButton jb = new JButton("click me");
    final JButton jb2 = new JButton("Hide ... ");

    jb.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("Before show dialog ...");
        d.setVisible(true);
        System.out.println("... after show dialog.");
      }
    });

    jb2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("\tBefore dispose dialog ...");
        d.setVisible(false);
        System.out.println("\t... after dispose dialog.");
      }
    });

    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(jb);
    d.getContentPane().setLayout(new BorderLayout());
    d.getContentPane().add(jb2);

    f.setVisible(true);

  }

  public static void main(final String[] args) {
    new MyJDialogEDTTests();
  }

}
