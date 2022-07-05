/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_6examples.javax.swing.jbutton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *Classname:  jdk1_6examples.javax.swing.jbutton.ImageDividedByButtons
 * @author C2334
 */
public class ImageDividedByButtons extends JPanel {
  ImageDividedByButtons(){
    setLayout(new GridLayout(1, 6, 0, 0));
    
    
    //ImageDividedByButtons.class.getResource()
    URL url = ImageDividedByButtons.class.getResource(
        "/jdk1_6examples/javax/swing/imgs/CamOperN.jpg");
    System.out.println("URL: "+url.toString()+"."); 
    JButton jb1 = new JButton(new ImageIcon(url));
    JButton jb2 = new JButton(new ImageIcon(
        ImageDividedByButtons.class.getResource("../imgs/CamOperNE.jpg")));
    //JButton jb1 = new JButton(new ImageIcon("D:/Projects/JDK1.6Examples/images/CamOperN.jpg"));
    //JButton jb2 = new JButton(new ImageIcon("D:/Projects/JDK1.6Examples/images/CamOperNE.jpg"));
    jb1.setPreferredSize(new Dimension(20,20));
    jb2.setPreferredSize(new Dimension(20,20));
    
    add(new JButton("ahahahaha"));
    add(new JButton("ahahahaha"));
    add(jb1);
    add(jb2);
    add(new JButton("ahahahaha"));
    add(new JButton("ahahahaha"));

    jb1.setEnabled(false);
  }
  
  public static void main(String[] args) {
    ImageDividedByButtons idbb = new ImageDividedByButtons();
    JFrame f = new JFrame("img divided by buttons");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(idbb);
    f.setBounds(100,100,50,50);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
