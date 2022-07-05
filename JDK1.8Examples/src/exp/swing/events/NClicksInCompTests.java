package exp.swing.events;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
*
*
* 1.  Log in to your PayPal account
2.  Go to the Profile subtab
3.  Click on the 'Credit Cards' link in the Financial Information column
4.  Choose the radio button next to the credit card you would like to
update and click 'Edit'
5.  Enter your credit card verification number
6.  Enter the new credit card expiration date
7.  Click 'Save'


Thank y

 */

public class NClicksInCompTests extends JPanel{
  public NClicksInCompTests() {
    JButton b = new JButton("nnnnn");
    b.addMouseListener(new MouseAdapter(){
      public void mouseClicked(final MouseEvent e){
        System.out.println("clicked e="+e.paramString()+".");
      }

      public void mousePressed(MouseEvent e){
        System.out.println("pressed e="+e.paramString()+".");
      }
    });
    setLayout(new BorderLayout());
    add(b, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    NClicksInCompTests n = new NClicksInCompTests();
    JFrame f = new JFrame("UI Defaults");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(n, BorderLayout.CENTER);
    f.setBounds(100, 100, 300, 200);
    f.setVisible(true);
  }

}
