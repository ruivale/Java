/**
 * <p>
 * Classname:  exp.swing.frames.maximize.MaximizeJFrameTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2019 EFACEC SE
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

package exp.swing.frames.maximize;


import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class MaximizeJFrameTests extends JFrame{

  private static final boolean mayMaximize = true;

 /**
  * The MaximizeJFrameTests default constuctor.
  */
  public MaximizeJFrameTests(){

    
    final JLabel jl = new JLabel("ahahahahah");
    jl.setHorizontalTextPosition(SwingConstants.CENTER);
    jl.setHorizontalAlignment(SwingConstants.CENTER);
    this.setLayout(new BorderLayout());
    this.add(jl);
    
    
    final Rectangle rec = new Rectangle(1921,0,1918,1199);
    
    
    this.setMinimumSize(rec.getSize());
    this.setMaximumSize(rec.getSize());
    this.setBounds(rec);

    if(mayMaximize){
      this.setExtendedState(Frame.MAXIMIZED_BOTH);
    }else{
//      this.setMaximizedBounds(rec);
    }
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    this.setVisible(true);
    
    
    new Thread(() -> {
      try {
        Thread.sleep(2500);
        while (true) {
          System.out.println("Bound: " + MaximizeJFrameTests.this.getBounds().toString());
          Thread.sleep(1000);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
  }

  
  public static void main(final String[] args){
    final MaximizeJFrameTests clazz = new MaximizeJFrameTests();
  }
}
