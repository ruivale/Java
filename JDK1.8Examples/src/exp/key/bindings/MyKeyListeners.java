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

public class MyKeyListeners extends JPanel {
  public MyKeyListeners() {
    setLayout(new BorderLayout());
    JButton b = new JButton("AAA");
    add(b, BorderLayout.CENTER);
    b.addKeyListener(new MyKeyAdapter(this));
  }

  public static void main(String[] args) {
    MyKeyListeners p = new MyKeyListeners();
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
class MyKeyAdapter extends KeyAdapter {
  MyKeyListeners adaptee = null;
//  private boolean isPressed = false;
  private boolean isZKeyBeingPressed = false;
  private boolean isZPlusKeyBeingPressed = false;
  private boolean isZMinusKeyBeingPressed = false;
  private boolean isFKeyBeingPressed = false;
  private boolean isFPlusKeyBeingPressed = false;
  private boolean isFMinusKeyBeingPressed = false;
  private boolean isUpKeyBeingPressed = false;
  private boolean isUpLeftKeyBeingPressed = false;
  private boolean isUpRightKeyBeingPressed = false;
  private boolean isDownKeyBeingPressed = false;
  private boolean isDownLeftKeyBeingPressed = false;
  private boolean isDownRightKeyBeingPressed = false;
  private boolean isLeftKeyBeingPressed = false;
  private boolean isRightKeyBeingPressed = false;

  MyKeyAdapter(MyKeyListeners adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(final KeyEvent e) {
    final String strKeyChar = ""+e.getKeyChar();
    final int intKeyCode = e.getKeyCode();




    if((strKeyChar.equals("z") || strKeyChar.equals("Z")) && !isZKeyBeingPressed){
      isZKeyBeingPressed = true;
System.out.println("PRESSED char="+e.getKeyChar()+", code="+e.getKeyCode()+".");

    } else if((strKeyChar.equals("f") || strKeyChar.equals("F")) && !isFKeyBeingPressed){
      isFKeyBeingPressed = true;
System.out.println("PRESSED char="+e.getKeyChar()+", code="+e.getKeyCode()+".");




    } else if((strKeyChar.equals("+") && isZKeyBeingPressed) && !isZPlusKeyBeingPressed){
      isZPlusKeyBeingPressed = true;
//      isPressed = true;
System.out.println("PRESSED char="+e.getKeyChar()+", code="+e.getKeyCode()+". isZKeyBeingPressed");
    } else if((strKeyChar.equals("+") && isFKeyBeingPressed) && !isFPlusKeyBeingPressed){
      isFPlusKeyBeingPressed = true;
//      isPressed = true;
System.out.println("PRESSED char="+e.getKeyChar()+", code="+e.getKeyCode()+". isFKeyBeingPressed");




    } else if((strKeyChar.equals("-") && isZKeyBeingPressed) && !isZMinusKeyBeingPressed){
      isZMinusKeyBeingPressed = true;
//      isPressed = true;
System.out.println("PRESSED char="+e.getKeyChar()+", code="+e.getKeyCode()+". isZKeyBeingPressed");
    } else if((strKeyChar.equals("-") && isFKeyBeingPressed) && !isFMinusKeyBeingPressed){
      isFMinusKeyBeingPressed = true;
//      isPressed = true;
System.out.println("PRESSED char="+e.getKeyChar()+", code="+e.getKeyCode()+". isFKeyBeingPressed");




    } else if(intKeyCode == 37 && !isRightKeyBeingPressed){ // LEFT
//      isPressed = true;

      if(isUpKeyBeingPressed && !isUpLeftKeyBeingPressed){ // NO
        isUpLeftKeyBeingPressed = true;
System.out.println("isUpLeftKeyBeingPressed");

      }else if(isDownKeyBeingPressed && !isDownLeftKeyBeingPressed){ // SO
        isDownLeftKeyBeingPressed = true;

System.out.println("isDownLeftKeyBeingPressed");

      }else if(!isLeftKeyBeingPressed){ // ONLY LEFT
        isLeftKeyBeingPressed = true;
System.out.println("isLeftKeyBeingPressed");

      }
      isLeftKeyBeingPressed = true;



    } else if(intKeyCode == 38 && !isDownKeyBeingPressed){ // UP
//      isPressed = true;

      if(isLeftKeyBeingPressed && !isUpLeftKeyBeingPressed){ // NO
        isUpLeftKeyBeingPressed = true;
System.out.println("isUpLeftKeyBeingPressed");

      }else if(isRightKeyBeingPressed && !isUpRightKeyBeingPressed){ // NE
        isUpRightKeyBeingPressed = true;
System.out.println("isUpRightKeyBeingPressed");

      }else if(!isUpKeyBeingPressed){ // ONLY UP
        isUpKeyBeingPressed = true;
System.out.println("isUpKeyBeingPressed");

      }
      isUpKeyBeingPressed = true;



    } else if(intKeyCode == 39 && !isLeftKeyBeingPressed){ // RIGHT
//      isPressed = true;

      if(isUpKeyBeingPressed && !isUpRightKeyBeingPressed){ // NE
        isUpRightKeyBeingPressed = true;
System.out.println("isUpRightKeyBeingPressed");

      }else if(isDownKeyBeingPressed && !isDownRightKeyBeingPressed){ // SE
        isDownRightKeyBeingPressed = true;
System.out.println("isDownRightKeyBeingPressed");

      }else if(!isRightKeyBeingPressed){ // ONLY RIGHT
        isRightKeyBeingPressed = true;
System.out.println("isRightKeyBeingPressed");

      }
      isRightKeyBeingPressed = true;



    } else if(intKeyCode == 40 && !isUpKeyBeingPressed){ // DOWN
//      isPressed = true;

      if(isLeftKeyBeingPressed && !isDownLeftKeyBeingPressed){ // SO
        isDownLeftKeyBeingPressed = true;
System.out.println("isDownLeftKeyBeingPressed");

      }else if(isRightKeyBeingPressed && !isDownRightKeyBeingPressed){ // SE
        isDownRightKeyBeingPressed  = true;
System.out.println("isDownRightKeyBeingPressed");

      }else if(!isDownKeyBeingPressed){ // ONLY DOWN
        isDownKeyBeingPressed = true;
System.out.println("isDownKeyBeingPressed");

      }
      isDownKeyBeingPressed = true;

    }

  }
  public void keyReleased(final KeyEvent e) {
    final String strKeyChar = ""+e.getKeyChar();
    final int intKeyCode = e.getKeyCode();

    if(strKeyChar.equals("z") || strKeyChar.equals("Z")){
      isZKeyBeingPressed = false;
      System.out.println("RELEASED char="+e.getKeyChar()+", code="+e.getKeyCode()+".");


    } else if(strKeyChar.equals("f") || strKeyChar.equals("F")){
      isFKeyBeingPressed = false;
      System.out.println("RELEASED char="+e.getKeyChar()+", code="+e.getKeyCode()+".");


    } else if(strKeyChar.equals("+")){
      isFPlusKeyBeingPressed = false;
      isZPlusKeyBeingPressed = false;
//      isPressed = false;
      System.out.println("RELEASED char="+e.getKeyChar()+", code="+e.getKeyCode()+".");


    } else if(strKeyChar.equals("-") ){
      isFMinusKeyBeingPressed = false;
      isZMinusKeyBeingPressed = false;
//      isPressed = false;
      System.out.println("RELEASED char="+e.getKeyChar()+", code="+e.getKeyCode()+".");


    } else if(intKeyCode == 36){ // HOME
      System.out.println("RELEASED HOME, code="+e.getKeyCode()+".");


    } else if(intKeyCode == 37){ // LEFT
      isLeftKeyBeingPressed = false;
      isDownLeftKeyBeingPressed = false;
      isUpRightKeyBeingPressed = false;
//      isPressed = false;
      System.out.println("RELEASED LEFT, code="+e.getKeyCode()+".");


    } else if(intKeyCode == 38){ // UP
      isUpKeyBeingPressed = false;
      isUpLeftKeyBeingPressed = false;
      isUpRightKeyBeingPressed = false;
//      isPressed = false;
      System.out.println("RELEASED UP, code="+e.getKeyCode()+".");


    } else if(intKeyCode == 39){ // RIGHT
      isRightKeyBeingPressed = false;
      isDownRightKeyBeingPressed = false;
      isUpRightKeyBeingPressed = false;
//      isPressed = false;
      System.out.println("RELEASED RIGHT, code="+e.getKeyCode()+".");


    } else if(intKeyCode == 40){ // DOWN
      isDownKeyBeingPressed = false;
      isDownLeftKeyBeingPressed = false;
      isDownRightKeyBeingPressed = false;
//      isPressed = false;
      System.out.println("RELEASED DOWN, code="+e.getKeyCode()+".");


    }
  }
}





