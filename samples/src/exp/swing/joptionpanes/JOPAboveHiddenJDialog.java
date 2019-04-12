/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exp.swing.joptionpanes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 *
 * @author rUI vALE {rui dot vale at efacec dot pt}
 */
public class JOPAboveHiddenJDialog {
  final static JFrame f = new JFrame("frame");
  final static JDialog d = new JDialog(f,"dialog", true);

  public static void main(String[] args) throws Exception{
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        final JButton jb = new JButton("button");
        jb.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
            System.out.println("button clicking ....");
          }
        });
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(jb);
        f.setBounds(300, 300, 500, 450);
        f.setVisible(true);
      }
    });
    
    Thread.sleep(2000);

    new Thread(new Runnable() {
      public void run() {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            d.setBounds(500, 500, 300, 250);
            d.setVisible(true);
          }
          });

        try {
          Thread.sleep(6000);
        } catch(Exception e) {
          e.printStackTrace();
        }
        
        System.out.println("closing dialog... ");
        
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            d.setVisible(false);
          }
          });
      }
    }).start();

    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        JOptionPane.showConfirmDialog(
            d,
            "message",
            "title",
            JOptionPane.YES_NO_OPTION);
      }
    });
  }
}
