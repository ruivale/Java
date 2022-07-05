package exp.swing.comp.remaddlisteners;


import javax.swing.*;
import javax.swing.event.*;

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
public class RemAddListeners extends JPanel{

  public RemAddListeners() {
    setLayout(new BorderLayout());
    add(new JLabel("PANEL"), BorderLayout.CENTER);

    addAncestorListener(new AncestorListener(){
      public void ancestorRemoved(AncestorEvent e){
        System.out.println("ancestorRemoved");
      }
      public void ancestorMoved(AncestorEvent e){
        //System.out.println("ancestorMoved");
      }
      public void ancestorAdded(AncestorEvent e){
        System.out.println("ancestorAdded");
      }
    });

  }

  public static void main(String[] s){

    final RemAddListeners r = new RemAddListeners();
    final JFrame f = new JFrame("----");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(r, BorderLayout.CENTER);

    final JButton b = new JButton("Remove");
    b.addMouseListener(new MouseAdapter(){
      public void mouseClicked(final MouseEvent e){
        f.remove(r);
        f.validate();
        f.repaint();
      }
    });
    f.getContentPane().add(b, BorderLayout.SOUTH);

    final JButton b2 = new JButton("Add");
    b2.addMouseListener(new MouseAdapter(){
      public void mouseClicked(final MouseEvent e){
        f.getContentPane().add(r, BorderLayout.CENTER);
        f.validate();
        f.repaint();
        f.getContentPane().add(new JLabel("THIS IS NOT THE PANEL"), BorderLayout.CENTER);
        f.validate();
        f.repaint();
      }
    });
    f.getContentPane().add(b2, BorderLayout.NORTH);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(100,100,400,300);
    f.setVisible(true);
  }
}
