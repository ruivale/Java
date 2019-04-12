/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package swingfx.animatedpanel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import net.java.swingfx.waitwithstyle.AnimatedPanel;
import net.java.swingfx.waitwithstyle.InfiniteProgressPanel;


/**
 *
 * @author c2334
 */
public class InfiniteProgressPanelTests extends JFrame{

   InfiniteProgressPanelTests(){
     
     final InfiniteProgressPanel np = new InfiniteProgressPanel(
         "the message to display", 20, .5f, 5, 5000);
     
     new Thread(
         new Runnable(){
         public void run(){
           try {
             Thread.sleep(5000);
           } catch(Exception e) {
           }
           np.stop();
         }
     }).start();
     
     setGlassPane(np);
     np.start();
     setBounds(100,100,600,350);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setVisible(true);
   }

  public static void main(String[] args) {
    new InfiniteProgressPanelTests();
  }

}
