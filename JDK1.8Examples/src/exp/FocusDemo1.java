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

     // local class that extends JPanel

     class LocalCanvas extends JPanel {
         private boolean can_traverse;

         public LocalCanvas(boolean b) {
             can_traverse = b;

             // add listener for focus gained/lost events

             addFocusListener(new FocusListener() {
                 public void focusGained(FocusEvent e) {
                     repaint();
                 }
                 public void focusLost(FocusEvent e) {
                     repaint();
                 }
             });

             // add listener for mouse events

             addMouseListener(new MouseAdapter() {
                 public void mousePressed(MouseEvent e) {
                     requestFocus();
                 }
             });
         }

         // paint the canvas

         public void paintComponent(Graphics g) {
             super.paintComponent(g);

             // highlight if canvas has the focus

             if (hasFocus()) {
                 g.setColor(Color.red);
             }
             else {
                 g.setColor(Color.blue);
             }

             Dimension d = getSize();
             g.draw3DRect(1, 1, d.width - 3, d.height - 3, true);
         }

         // set preferred size of 50 x 50 for the canvas

         public Dimension getPreferredSize() {
             return new Dimension(50, 50);
         }

         // return true if can traverse this component using Tab

         public boolean isFocusTraversable() {
             return can_traverse;
         }
     }

     public class FocusDemo1 {
         public static void main(String args[]) {
             JFrame frame = new JFrame("Focus Demo 1");

             // handle window close

             frame.addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent e) {
                     System.exit(0);
                 }
             });

             // set up button and canvases

             JButton button1 = new JButton("Standard Button");
             JComponent canvas1 = new LocalCanvas(true);
             JComponent canvas2 = new LocalCanvas(false);

             // add to panel

             JPanel panel = new JPanel();

             panel.add(button1);
             panel.add(canvas1);
             panel.add(canvas2);

             // display

             frame.getContentPane().add(panel);
             frame.setLocation(100, 100);
             frame.pack();
             frame.setVisible(true);
         }
     }