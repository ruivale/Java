package exp.key.bindings;


import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class KeyEventsTests extends JPanel {
  public KeyEventsTests() {
    final JButton b = new JButton("nnnnn");
    b.addKeyListener(new KeyAdapter(){
      public void keyReleased(final KeyEvent e){
        System.out.println("e.char="+e.getKeyChar()+", e.code="+e.getKeyCode()+".");

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
          System.out.println("The ENTER key!");
        }
      }
    });
    setLayout(new BorderLayout());
    add(b, BorderLayout.CENTER);


    new Thread(new Runnable() {
      public void run() {
        try {
          Thread.sleep(4000);
          /** @todo REMOVE THIS 06-02-2007 10:31 */
          System.out.println("....");
          b.setEnabled(false);
        } catch (Exception ex) {

        }
      }
    }).start();
  }
  public static void main(String[] args) {
    KeyEventsTests k = new KeyEventsTests();
    JFrame f = new JFrame();
    f.setTitle("Key bindings ");
    f.getContentPane()
        .setLayout(new BorderLayout());
    f.getContentPane()
        .add(k, BorderLayout.CENTER);
    f.setBounds(100, 100, 200, 70);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }

}
