package exp.swing.frames;


import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;

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
public class FrameListeners extends JFrame{
  public FrameListeners() {
    this.setBounds(50,50,400,200);
    this.setVisible(true);

    this.addWindowListener(new WindowAdapter(){
      public void windowActivated(WindowEvent e){
        System.out.println("windowActivated");
      }
      public void windowClosed(WindowEvent e){
        System.out.println("windowClosed");

      }

      public void windowClosing(WindowEvent e){
        System.out.println("windowClosing");

      }

      public void windowDeactivated(WindowEvent e){
        System.out.println("windowDeactivated");

      }

      public void windowDeiconified(WindowEvent e){
        System.out.println("windowDeiconified");

      }

      public void windowGainedFocus(WindowEvent e){
        System.out.println("windowGainedFocus");

      }

      public void windowIconified(WindowEvent e){
        System.out.println("windowIconified");

      }

      public void windowLostFocus(WindowEvent e){
        System.out.println("windowLostFocus");

      }

      public void windowOpened(WindowEvent e){
        System.out.println("windowOpened");

      }

      public void windowStateChanged(WindowEvent e){
        System.out.println("windowStateChanged");

      }

    });
  }

  public static void main(String[] args) {
    FrameListeners f = new FrameListeners();
  }
}
