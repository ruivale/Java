/**
 * <p>
 * Classname:  exp.swing.jbutton.TestJButtonPressAndRelease
 * </p>
 *
 * <p>Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package exp.swing.jbutton;


import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class TestJButtonPressAndRelease extends JPanel {

 /**
  * The TestJButtonPressAndRelease default constructor.
  */
  public TestJButtonPressAndRelease(){
    final JButton b = new JButton("bbbbb");
    
//    b.addActionListener(new ActionListener(){
//
//      public void actionPerformed(ActionEvent e){
//        System.out.println("Button - actionPerformed");
//
//        bool = !bool;
//
//        if(bool){
//          b.setBackground(Color.gray);
//        }else{
//          b.setBackground(backColor);
//        }
//      }
//    });
    
    b.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        
        if(!b.isEnabled()){
          System.out.println("btt not enabled... abort mouse press...");
          return;
        }
        
        System.out.println("pressed");
      
        Runnable runnable = () -> {
          System.out.println("Thread 1...");
          try {Thread.sleep(1800); } catch (InterruptedException iexc) { }
          System.out.println("...Thread 1");
          
          SwingUtilities.invokeLater(() -> {
            b.setEnabled(false);              
            
            Runnable runnable2 = () -> {
              System.out.println("Thread 2...");
              try {Thread.sleep(2500); } catch (InterruptedException iexc) { }
              System.out.println("...Thread 2");

              SwingUtilities.invokeLater(() -> b.setEnabled(true));

            };

            new Thread(runnable2).start();
            
          });
                    
        };
        
        new Thread(runnable).start();
        
      }

      public void mouseReleased(MouseEvent e) {
        System.out.println("released");
      
      }
      
    });

    setLayout(new BorderLayout());
    add(b, BorderLayout.CENTER);
  }


  
  
  public static void main(String[] args) {
    TestJButtonPressAndRelease t = new TestJButtonPressAndRelease();
    JFrame f = new JFrame("......");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(t, BorderLayout.CENTER );
    f.setBounds(100,100,200,200);
    f.setVisible(true);
  }
}
