/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exp.swing.jtoolbar.helpmenu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;


/**
 *
 * @author c2334
 */
public class MyJToolbarTests extends JFrame{

  JButton jb = new JButton(" b u t o n ");
  JPanel jPanelToolbar = new JPanel(new BorderLayout());
  JToolBar jT = new JToolBar();
  
  public MyJToolbarTests() {
    final JPanel jPWest = new JPanel(new FlowLayout(FlowLayout.LEFT));
    final JPanel jPEast = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    jPWest.add(new JButton("WEST1"));
    jPWest.add(new JButton("WEST3"));
    jPWest.add(new JButton("WEST4"));
    
    jPEast.add(new JButton("EAST"));
    
    this.jPanelToolbar.add(jPWest, BorderLayout.WEST);
    this.jPanelToolbar.add(jPEast, BorderLayout.EAST);
    
    jT.add(this.jPanelToolbar);
    
    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(jT, BorderLayout.NORTH);
    this.getContentPane().add(jb, BorderLayout.CENTER);
    
    this.setBounds(100,100,650,350);
    this.setVisible(true);
  }

  
  public static void main(String[] args) {
    new MyJToolbarTests();
  }

}
