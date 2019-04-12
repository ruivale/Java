/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package swingfx.animatedpanel;

import javax.swing.ImageIcon;
import net.java.swingfx.waitwithstyle.AnimatedPanel;

/**
 *
 * @author c2334
 */
public class AnimatedPanelTests extends javax.swing.JFrame {

   AnimatedPanelTests(){
     ImageIcon icon = new ImageIcon("D:\\temp\\dome.gif");
     final AnimatedPanel np = new AnimatedPanel(
         "the message to display", 
         icon);
     np.start();
     
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
     
     getContentPane().setLayout(new java.awt.BorderLayout());
     getContentPane().add(np);
     setBounds(100,100,300,250);
     setVisible(true);
   }

  public static void main(String[] args) {
    new AnimatedPanelTests();
  }
}
