/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exp.layouts.flowlaytests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;


/**
 *
 * @author c2334
 */
public class MyFlowLayoutTests extends JWindow{

  final Dimension DIM_4CIF = new Dimension(665, 580);
  final Dimension DIM_CIF = new Dimension(370, 328);
  final Dimension DIM_QCIF = new Dimension(148, 148);
  
  final JPanel jp1 = new JPanel();
  final JPanel jp2 = new JPanel();
  final JPanel jp3 = new JPanel();
  final JPanel jp4 = new JPanel();
  final JPanel jp5 = new JPanel();
  final JPanel jp6 = new JPanel();
  final JPanel[] panels = {jp1,jp2,jp3,jp4,jp5,jp6};

  public MyFlowLayoutTests() throws Exception {    
    this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    
    for(int i = 0; i < panels.length; i++) {
      panels[i].setBorder(BorderFactory.createLineBorder(Color.RED));      
    }
    jp1.setPreferredSize(DIM_4CIF);
    jp2.setPreferredSize(DIM_CIF);
    jp3.setPreferredSize(DIM_CIF);
    jp4.setPreferredSize(DIM_CIF);
    jp5.setPreferredSize(DIM_CIF);
    jp6.setPreferredSize(DIM_QCIF);
    
    this.setBounds(1280*3,1024*3,1280,1024);
    //this.setBounds(3000,4000,1240,1024);
    
    for(int i = 0; i < panels.length; i++) {
      this.getContentPane().add(panels[i]);
    }
    
    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);this.setVisible(false);    
    //this.validate();
    //this.repaint();
    
    for(int i = 0; i < panels.length; i++) {
      System.out.println("panel["+i+"]("+panels[i].getBounds().x+","+panels[i].getBounds().y+")");
    }

    System.out.println("-------------------------------------------");
    
    /***/
    final int iH = 1024;
    final JWindow jw = new JWindow();
    jw.getContentPane().setLayout(null);
    jw.setBounds(1280,0,1280,1024);
    
    
    for(int i = 0; i < panels.length; i++) {
      panels[i].setLocation(
          panels[i].getLocation().x, 
          iH - panels[i].getLocation().y - panels[i].getHeight());
      jw.getContentPane().add(panels[i]);
      System.out.println("panel["+i+"]("+panels[i].getLocation().x+","+panels[i].getLocation().y+")");
    }
    
    this.setVisible(false);
    jw.setVisible(true);
    /**/
    //System.exit(0);
  }

  public static void main(String[] args) throws Exception {    
    new MyFlowLayoutTests();
  }
}
