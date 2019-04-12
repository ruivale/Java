/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exp.swing.frames.independentminimize;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


/**
 *
 * @author c2334
 */
public class IndependentMinimizeFrameAndDialogs extends JFrame {
  JDialog[] diags;
  

  IndependentMinimizeFrameAndDialogs(){
    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(new JButton("AHAHAHHA"));
    
    this.addWindowListener(new WindowListener(){
      boolean isIconified = false;
      Point point;
      
      public void windowOpened(WindowEvent e){
        //System.out.println("windowOpened"); 
        isIconified = false;
      }
      public void windowClosing(WindowEvent e){
        //System.out.println("windowClosing"); 
      }
      public void windowClosed(WindowEvent e){
        //System.out.println("windowClosed"); 
      }
      public void windowIconified(WindowEvent e){
        System.out.println("windowIconified"); 
        setDialogsVisible();

        if(!isIconified){
          point = getLocation();
          
          setExtendedState(JFrame.NORMAL);
          setLocation(0, 20000);

        }else{
          setLocation(point);
        }
        
        setExtendedState(JFrame.NORMAL);
        isIconified = !isIconified;
        
      }
      public void windowDeiconified(WindowEvent e){
        //System.out.println("windowDeiconified"); 
        isIconified = false;
      }
      public void windowActivated(WindowEvent e){
        //System.out.println("windowActivated"); 
        isIconified = false;
      }
      public void windowDeactivated(WindowEvent e){
        //System.out.println("windowDeactivated"); 
        //setDialogsVisible();
      }
    });
    
  }
  
  private void setDialogs(final JDialog[] diags){
    this.diags = diags;
  }    
  
  private void setDialogsVisible(){    
    for(int i = 0; this.diags != null && i < this.diags.length; i++) {
      this.diags[i].setVisible(true);           
      this.diags[i].toFront();
      this.diags[i].requestFocus();
      System.out.println("this.diags["+i+"].setVisible(true);"); 
    }
  }
  
  public static void main(String[] args) {
    IndependentMinimizeFrameAndDialogs i = new IndependentMinimizeFrameAndDialogs();
    i.setBounds(100,100, 1000, 750 );
    i.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    i.setVisible(true);
    
    /***
    final JDialog jd1 = new JDialog();
    jd1.setModal(false);
    jd1.setTitle("jd1");
    /**/
    final JDialog jd1 = new JDialog(i, "jd1", false);
    jd1.setBounds(1300, 0, 300, 200);
    jd1.setVisible(true);
    final JDialog jd2 = new JDialog(i, "jd2", false);
    jd2.setBounds(1300, 250, 300, 200);
    jd2.setVisible(true);
    final JDialog jd3 = new JDialog(i, "jd3", false);
    jd3.setBounds(1300, 500, 300, 200);
    jd3.setVisible(true);
    
    i.setDialogs(new JDialog[]{jd1, jd2, jd3});
  }
}
