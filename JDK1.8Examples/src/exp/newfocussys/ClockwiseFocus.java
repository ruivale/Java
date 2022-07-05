package exp.newfocussys;

import java.awt.*;
import java.awt.event.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;


public class ClockwiseFocus implements ActionListener {
  public ClockwiseFocus() {
    JFrame frame = new JFrame("Focus Cycling");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    KeyFocus keyfocus = new KeyFocus();

    Container contentPane = frame.getContentPane();
    JButton north = new JButton("North");
    north.addActionListener(this);
    north.addKeyListener(keyfocus);
    contentPane.add(north, BorderLayout.NORTH);

    JButton south = new JButton("South");
    south.addActionListener(this);
    south.addKeyListener(keyfocus);
    contentPane.add(south, BorderLayout.SOUTH);

    JButton east = new JButton("East");
    east.addActionListener(this);
    east.addKeyListener(keyfocus);
    contentPane.add(east, BorderLayout.EAST);

    JButton west = new JButton("West");
    west.addActionListener(this);
    west.addKeyListener(keyfocus);
    contentPane.add(west, BorderLayout.WEST);

    JButton center = new JButton("Center");
    center.addActionListener(this);
    center.addKeyListener(keyfocus);
    contentPane.add(center, BorderLayout.CENTER);
    contentPane.setFocusable(false);

    final Component[] order = new Component[] { north, east, south, west, center };
    FocusTraversalPolicy policy = new FocusTraversalPolicy() {
        List list = Arrays.asList(order);

        public Component getFirstComponent(Container focusCycleRoot) {
          return order[0];
        }

        public Component getLastComponent(Container focusCycleRoot) {
          return order[order.length - 1];
        }

        public Component getComponentAfter(Container focusCycleRoot,
          Component aComponent) {
          int index = list.indexOf(aComponent);

          return order[(index + 1) % order.length];
        }

        public Component getComponentBefore(Container focusCycleRoot,
          Component aComponent) {
          int index = list.indexOf(aComponent);

          return order[(index - 1 + order.length) % order.length];
        }

        public Component getDefaultComponent(Container focusCycleRoot) {
          return order[0];
        }
      };

    frame.setFocusTraversalPolicy(policy);
    frame.pack();
    frame.show();
  }

  public void actionPerformed(ActionEvent e) {
    System.out.println("Just clicked a button!");
  }

  public static void main(String[] args) {
    new ClockwiseFocus();
  }

  class KeyFocus extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
      System.out.println("keyPressed");
      if(e.getKeyCode() == KeyEvent.VK_ENTER){
        System.out.println("just pressed the ENTER key!");
      }
    }

/*
    public void keyReleased(KeyEvent e) {
      System.out.println("keyReleased");
    }

    public void keyTyped(KeyEvent e) {
      System.out.println("keyTyped");
    }
*/
  }
}
