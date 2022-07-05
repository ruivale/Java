package exp.key.bindings;


import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyKeyListenersPrePositionConfig extends JPanel {

  public MyKeyListenersPrePositionConfig() {
    setLayout(new BorderLayout());
    JButton b = new JButton("AAA");
    add(b, BorderLayout.CENTER);
    b.addKeyListener(new MyKeyAdapterPrePositionConfig(this));
    b.addMouseListener(new PPMouseAdapter());
  }

  public static void main(String[] args) {
    MyKeyListenersPrePositionConfig p = new MyKeyListenersPrePositionConfig();
    JFrame f = new JFrame();
    f.setTitle("Key bindings ");
    f.getContentPane()
        .setLayout(new BorderLayout());
    f.getContentPane()
        .add(p, BorderLayout.CENTER);
    f.setBounds(100, 100, 200, 70);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}

class MyKeyAdapterPrePositionConfig extends KeyAdapter {
  MyKeyListenersPrePositionConfig adaptee = null;
  private boolean isInsertKeyBeingPressed = false;
  private boolean isDeleteKeyBeingPressed = false;
  private int intNumericKeyPressed = 0;
  private int intFKeyPressed = -1;


  MyKeyAdapterPrePositionConfig(MyKeyListenersPrePositionConfig adaptee) {
    this.adaptee = adaptee;
  }

  public void _keyPressed(final KeyEvent e) {
    final int intKeyCode = e.getKeyCode();

    switch(intKeyCode){
      case 155: // INSERT
        isInsertKeyBeingPressed = true;
        break;

      case 127: // DELETE
        isDeleteKeyBeingPressed = true;
        break;

      case 49: // 1
      case 50: // 2
      case 51: // 3
      case 52: // 4
      case 53: // 5
      case 54: // 6
      case 55: // 7
      case 56: // 8
      case 57: // 9
        intNumericKeyPressed = intKeyCode-48;
        break;
      case 97: // 1
      case 98: // 2
      case 99: // 3
      case 100: // 4
      case 101: // 5
      case 102: // 6
      case 103: // 7
      case 104: // 8
      case 105: // 9
        intNumericKeyPressed = intKeyCode-96;
        break;

      default:

    }
  }

  public void _keyReleased(final KeyEvent e) {
    final int intKeyCode = e.getKeyCode();

    switch(intKeyCode){
      case 155: // INSERT
        isInsertKeyBeingPressed = false;
        break;

      case 127: // DELETE
        isDeleteKeyBeingPressed = false;
        break;

      case 112: // F1
      case 113: // F2
      case 114: // F3
      case 115: // F4
      case 116: // F5
      case 117: // F6
      case 118: // F7
      case 119: // F8
      case 120: // F9
      case 121: // F10
        intFKeyPressed = intKeyCode - 111;

        final int intActivatePreSet = (10*intNumericKeyPressed)+intFKeyPressed;

System.out.print("PRESET "+intActivatePreSet+" -> ");

        if(isInsertKeyBeingPressed){ // INSERT

System.out.println("INSERT");

        }else if(isDeleteKeyBeingPressed){ // DELETE

System.out.println("DELETE");

        }else{ // ACTIVATE

System.out.println("ACTIVATE");

        }

        intFKeyPressed = 0;

        break;

      case 49: // 1
      case 50: // 2
      case 51: // 3
      case 52: // 4
      case 53: // 5
      case 54: // 6
      case 55: // 7
      case 56: // 8
      case 57: // 9
      case 97: // 1
      case 98: // 2
      case 99: // 3
      case 100: // 4
      case 101: // 5
      case 102: // 6
      case 103: // 7
      case 104: // 8
      case 105: // 9
        intNumericKeyPressed = 0;
        break;

      case 36: // HOME
        if(isInsertKeyBeingPressed){
          // Gravar a home position

System.out.println("RELEASED HOME por isso, gravar a home position");

        }
        break;

      default:
    }
  }


  boolean isPressed = false;


}
class PPMouseAdapter
    extends java.awt.event.MouseAdapter {

  PPMouseAdapter() {
  }
  public void mousePressed(final MouseEvent e){
System.out.println("mousePressed");
  }
  public void mouseReleased(MouseEvent e) {
System.out.println("mouseReleased");
  }
  public void mouseEntered(final MouseEvent e){
System.out.println("mouseEntered");
  }
}






