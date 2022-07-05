/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jwindow.JWindowTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.javax.swing.jwindow;


import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JWindow;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class JWindowTests extends JWindow {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(JWindowTests.class.getName());


 /**
  * The JWindowTests default constuctor.
  */
  public JWindowTests(Frame f){
    super(f);

    setLocationRelativeTo(f);
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("JWindowTests").append("").toString();
  }

  public static void main(final String[] args){
    final JFrame f = new JFrame("main");
    f.setBounds(0,0,1280,400);
    f.add(new JButton("CLAIDH"));
    f.setVisible(true);

    final JWindowTests clazz = new JWindowTests(f);
    clazz.setLayout(new BorderLayout());
    final JButton b = new JButton("CLICK");
    clazz.add(b, BorderLayout.CENTER);
    clazz.setSize(300,150);
    b.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        clazz.setVisible(false);
      }
    });
    
    clazz.setVisible(true);
  }
}
