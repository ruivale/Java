/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exp.swing.jmenu.helpmenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


/**
 *
 * @author c2334
 */
public class MyJMenuBar extends JMenuBar {
  JPanel jPanelToolbar = new JPanel(new BorderLayout());
     
  public MyJMenuBar() {
    final JPanel jPWest = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    jPWest.setPreferredSize(new Dimension(450,18));
    final JPanel jPEast = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
    jPEast.setPreferredSize(new Dimension(80,18));
    
    JMenu jM1 = new JMenu("WEST1");
    jM1.add(new JMenuItem("jM1 - 1"));
    jM1.add(new JMenuItem("jM1 - 2"));
    jM1.add(new JMenuItem("jM1 - 3"));
    
    jPWest.add(jM1);
    jPWest.add(new JMenu("WEST3"));
    jPWest.add(new JMenu("WEST4"));
    
    jPEast.add(new JMenu("EAST"));
    
    this.jPanelToolbar.setPreferredSize(new Dimension(650,20));
    this.jPanelToolbar.add(jPWest, BorderLayout.WEST);
    this.jPanelToolbar.add(jPEast, BorderLayout.EAST);
    
    this.setLayout(new BorderLayout(0, 0));
    this.add(this.jPanelToolbar, BorderLayout.CENTER);
  }
  
  
  public static void main(String[] args) {
    JFrame f = new JFrame("frame");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setJMenuBar(new MyJMenuBar());
    
    f.setBounds(100, 100, 650, 350);
    f.setVisible(true);
  }

}
