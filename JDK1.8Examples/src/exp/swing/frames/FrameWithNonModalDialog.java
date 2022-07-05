package exp.swing.frames;


import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import java.awt.event.MouseEvent;


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
public class FrameWithNonModalDialog {
  public FrameWithNonModalDialog() {

  }

  public static void main(String s[]){
    final JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    JButton b = new JButton("1111111111111");
    b.addMouseListener(new MouseAdapter(){
      public void mouseClicked(final MouseEvent e){
        final JDialog d = new JDialog(f, "dialog", false);
        JButton b11 = new JButton("11111111111 dia");
        b11.addMouseListener(new MouseAdapter(){
          public void mouseClicked(final MouseEvent e){
            d.dispose();
          }
        });
        d.getContentPane().setLayout(new BorderLayout());
        d.getContentPane().add(b11, BorderLayout.CENTER);
        d.setLocationRelativeTo(f);
        d.setSize(200,200);
        d.setVisible(true);
      }
    });
    f.getContentPane().add(b, BorderLayout.CENTER);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(100, 100, 500, 300);
    f.setVisible(true);

    final JFrame f2 = new JFrame();
    f2.getContentPane().setLayout(new BorderLayout());
    JButton b2 = new JButton("2222222222");
    b2.addMouseListener(new MouseAdapter(){
      public void mouseClicked(final MouseEvent e){
        final JDialog d = new JDialog(f2, "dialog", false);
        JButton b22 = new JButton("2222222222 dia");
        b22.addMouseListener(new MouseAdapter(){
          public void mouseClicked(final MouseEvent e){
            d.dispose();
          }
        });
        d.getContentPane().setLayout(new BorderLayout());
        d.getContentPane().add(b22, BorderLayout.CENTER);
        d.setLocationRelativeTo(f2);
        d.setSize(200,200);
        d.setVisible(true);
      }
    });
    f2.getContentPane().add(b2, BorderLayout.CENTER);
    f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f2.setBounds(600, 100, 500, 300);
    f2.setVisible(true);

  }
}
