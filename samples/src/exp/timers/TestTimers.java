package exp.timers;

import javax.swing.*;
import javax.swing.event.*;


import java.awt.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class TestTimers {
javax.swing.Timer t = null;
  public TestTimers() {
     t = new javax.swing.Timer(1500, new Timer_ActionAdapter(this));
    t.setCoalesce(false);

    System.out.println("STARTING");

    t.restart();

      try {
        Thread.sleep(3000);
      }catch (Exception ex) {
        ;
      }

    int i=0;
    while(i++<5){
      try {
        Thread.sleep(900);
      }catch (Exception ex) {
        ;
      }
      //if(t.isRunning()){
        t.restart();
      //}
    }

    (new JDialog()).setVisible(true);
  }
  public static void main(String[] args) {
    TestTimers testTimers1 = new TestTimers();
/*
    try {
      Thread.sleep(3000);
    }catch (Exception ex) {
      ;
    }
*/
  }
}
class Timer_ActionAdapter
    implements ActionListener{

      long now, after;
      TestTimers tt = null;

  public Timer_ActionAdapter(TestTimers tt) {
    this.tt = tt;
    after = System.currentTimeMillis();
  }
  public void actionPerformed(ActionEvent e){
    now = System.currentTimeMillis();
    System.out.println("actionPerformed (now-after)= "+(now-after)+".");
    after = System.currentTimeMillis();

    tt.t.stop();

    System.out.println("In the end of the TIMER handler.");

  }
}
