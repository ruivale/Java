package exp;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

    import java.awt.*;
     import javax.swing.*;
     import java.awt.event.*;

     public class FocusDemo2 {
         public static void main(String args[]) {
             JFrame frame = new JFrame("Focus Demo 2");

             // handle window closing

             frame.addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent e) {
                     System.exit(0);
                 }
             });

             // set up a panel and two buttons and a scrollbar

             JPanel panel = new JPanel();
             JButton button1 = new JButton("Button1");
             JScrollBar jsb = new JScrollBar();
             JButton button2 = new JButton("Button2");

             panel.add(button1);
             panel.add(jsb);
             panel.add(button2);

             // display

             frame.getContentPane().add(panel);
             frame.setLocation(100, 100);
             frame.pack();
             frame.setVisible(true);
         }
     }

