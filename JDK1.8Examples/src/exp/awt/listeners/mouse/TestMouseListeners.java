package exp.awt.listeners.mouse;


import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestMouseListeners extends JPanel{
  public TestMouseListeners() {
    addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
        if((e.getClickCount() % 2) == 0){
          System.out.println("2222222222222222");
        }else if((e.getClickCount()%3) == 0){
              System.out.println("333333333333333");
        }else{
          System.out.println("111111111111111");
        }
        System.out.println("################################");
      }
    });
  }

  public static void main(String[] args) {
    TestMouseListeners t = new TestMouseListeners();
    JFrame frame = new JFrame( "Mouse listeners tests" );
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add( t );
    frame.setBounds(100,100,300,200);
    frame.setVisible( true );

  }
}
