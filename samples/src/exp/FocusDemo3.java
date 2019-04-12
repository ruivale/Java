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

     // local version of focus manager

     class LocalFocusManager extends DefaultFocusManager {
         public boolean compareTabOrder(Component c1, Component c2) {
             Point loc1 = c1.getLocation();
             Point loc2 = c2.getLocation();

             // sort the two components

             if (loc1.x != loc2.x) {
                 return loc1.x < loc2.x;
             }

             return loc1.y < loc2.y;
         }
     }

     public class FocusDemo3 {
         public static void main(String args[]) {
             JFrame frame = new JFrame("Focus Demo 3");

             // handle window closing

             frame.addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent e) {
                     System.exit(0);
                 }
             });

             // install a new focus manager

             FocusManager.setCurrentManager(new LocalFocusManager());

             // add a 5 x 5 matrix of buttons to the panel

             JPanel panel = new JPanel();
             final int N = 5;
             panel.setLayout(new GridLayout(N, N));
             for (int i = 1; i <= N; i++) {
                 for (int j = 1; j <= N; j++) {
                     JButton b = new JButton(i + "," + j);
                     panel.add(b);
                 }
             }

             // display

             frame.getContentPane().add(panel);
             frame.setLocation(100, 100);
             frame.pack();
             frame.setVisible(true);
         }
     }

