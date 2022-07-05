/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_6examples.javax.swing.jbutton.size;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;


/**
 *
 * @author c2334
 */
public class ButtonSizeAccordingToText extends JPanel{

  ButtonSizeAccordingToText(){
    setLayout(new FlowLayout());
    
    JButton b1 = new JButton("Sair");
    JButton b2 = new JButton("Cancelar");
    JButton b3 = new JButton("Cancel");
    JButton b4 = new JButton("Ok");
    
    /****
    final Dimension dim = new Dimension(105,22);
    b1.setPreferredSize(dim);
    b2.setPreferredSize(dim);
    b3.setPreferredSize(dim);
    b4.setPreferredSize(dim);
    /**/
    
    add(b1);
    add(b2);
    add(b3);
    add(b4);
  }
  
  public static void main(String[] args) {
    ButtonSizeAccordingToText b = new ButtonSizeAccordingToText();
    JFrame f = new JFrame("img divided by buttons");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(b);
    f.setBounds(100,100,50,50);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
    
  }
}
