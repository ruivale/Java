package exp.desktop;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;



/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class MyTestsInAJDesktop {

  final JDesktopPane jdp = new JDesktopPane();
  JFrame frame = new JFrame();
  JInternalFrame jif = new JInternalFrame("alalala");
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JPanel p3 = new JPanel();
  JButton b1 = new JButton("b1");
  JButton b2 = new JButton("b2");
  JButton b3 = new JButton("b3");



  /**
   *
   */
  public MyTestsInAJDesktop() {

    p2.addMouseListener(
      new MouseAdapter() {
        public void mousePressed(MouseEvent evt) {
          // Do nothing.

System.out.println("Clicking in the p2 panel");

        }
      });

    jdp.setLayout(new BorderLayout());
    jdp.add(this.jif, BorderLayout.CENTER);

    this.p1.setLayout(new BorderLayout());
    this.p1.add(this.b1, BorderLayout.CENTER);
    b1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){

System.out.println("Clickin in the b1 button!");

        p2.setLayout(new BorderLayout());
        p2.add(b2, BorderLayout.CENTER);
        p2.setOpaque(false);
        jif.getContentPane().add(p2, BorderLayout.CENTER, 1);

System.out.println("Clickin in the b1 button! end");

      }
    });
    b2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){

System.out.println("Clickin in the b2 button!");

      }
    });


    this.jif.getContentPane().setLayout(new BorderLayout());
    this.jif.getContentPane().add(this.p1, BorderLayout.CENTER, 0);
    this.jif.setVisible(true);

    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(this.jdp, BorderLayout.CENTER);


    frame.setBounds(50,50,600,400);
    frame.setVisible(true);


  }
  public static void main(String[] args) {
    MyTestsInAJDesktop myTestsInAJDesktop1 = new MyTestsInAJDesktop();
  }
}