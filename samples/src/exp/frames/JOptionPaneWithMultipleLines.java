package exp.frames;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

 import javax.swing.*;
 import javax.swing.event.*;
 import java.awt.*;
 import java.awt.event.*;

public class JOptionPaneWithMultipleLines implements Runnable{

  static String text = new String(
    "Não foi possível à PInt enviar o evento ao serviço de eventos!\nEvento:");

  static JOptionPane jOptionPane = new JOptionPane();

  static int clicks = 0;

/**
 *
 *
 */
  public JOptionPaneWithMultipleLines() {

    (new Thread(this)).start();

    JFrame f = new JFrame();
    JButton b = new JButton("show jop");
    b.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        showSyncJoptionPane();
        clicks++;
      }
    });
    b.setBounds(10,10,100,20);
    f.getContentPane().add(b);
    f.pack();
    f.setLocation(0,0);
    f.setVisible(true);


  }


  /**
   *
   */
  public static void showSyncJoptionPane(){

    synchronized (jOptionPane) {
      jOptionPane.showMessageDialog(
      null,
      text,
      "Linhas múltiplas -> "+clicks,
      javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

  }

  public static void print(String string){
    System.out.println(string);
  }


  public void run(){
    while(true){

      try{Thread.sleep(2000);}catch(Exception e){}
/*
      showSyncJoptionPane();
      clicks++;
*/


print("O clicks= "+clicks);

    }
  }



  public static void main(String[] args) {
    JOptionPaneWithMultipleLines JOptionPaneWithMultipleLines1 = new JOptionPaneWithMultipleLines();
  }
}