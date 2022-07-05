package exp.swing.timers;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyTimer {
  public MyTimer() {
    int delay = 5000; //milliseconds
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            System.out.println("actionPerformed");
            System.exit(0);
        }
    };
    Timer timer = new Timer(delay, taskPerformer);
    timer.setRepeats(false);
    timer.start();
    if(timer.isRunning()){
      System.out.println("IS RUNNING");
    }else{
      System.out.println("IN NOT RUNNING");
    }
    try {
      Thread.sleep(200000);
    }
    catch (Exception ex) {

    }

  }
  public static void main(String[] args) {
    MyTimer myTimer1 = new MyTimer();
  }

}
