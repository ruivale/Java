package exp.detectingexitapp;

/**
 * Classname: exp.detectingexitapp.DetectingAnExitingProgram
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author 2334
 */
public class DetectingAnExitingProgram {

  public DetectingAnExitingProgram() {


    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {

//        SwingUtilities.invokeLater(new Runnable() {
//          @Override
//          public void run() {
//            JOptionPane.showMessageDialog(null, "Exit shutdown hook ...");
//          }
//        });
//
        try {
          Runtime.getRuntime().exec(
              "C:/Program Files/Internet Explorer/IEXPLORE.EXE D:\\Rui\\HomePage\\HomePage.html");

          System.out.println("In the EXIT shutdown hook & sleeping 4 6 secs");
          Thread.sleep(6000);

        } catch (Exception iOException) {
          iOException.printStackTrace();
        }

        System.out.println("\nENDINGENDINENDINGENDINENDINGENDINENDINGENDIN\n");
      }
    });

    JFrame f = new JFrame("frame");
    f.setBounds(10,10,300,150);
    f.setLayout(new BorderLayout());

    JButton jb = new JButton("Exit 1");
    jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        JOptionPane.showMessageDialog(null, "Exit shutdown hook ...");

        final Thread thread = new Thread(new Runnable() {
          @Override
          public void run() {
            System.out.println("will sleep 4 2 secs before attempting to exit ...");
            try {Thread.sleep(2000);} catch (InterruptedException interruptedException) {}
            System.out.println("Trying to exit ...");
          }
        }, "ThreadName");
        thread.setDaemon(true);
        thread.start();


        System.exit(0);
      }
    });

    f.add(jb, BorderLayout.CENTER);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JFrame f2 = new JFrame("frame 2");
    f2.setBounds(500,10,300,150);
    f2.setVisible(true);
    f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    final JDialog d = new JDialog(f, "dialog", true /*modal*/);
    d.setBounds(810,10,300,150);
    d.setLayout(new BorderLayout());
    JButton jbd = new JButton("Exit 1");
    jbd.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //d.setVisible(false);
        d.dispose();
      }
    });
    d.add(jbd, BorderLayout.CENTER);
    d.setVisible(true);
    d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


  }
  public static void main(String[] args) {
    DetectingAnExitingProgram detectingAnExitingProgram1 = new DetectingAnExitingProgram();
  }
}