/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jtooglebutton.JToogleButtonsTests
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

package jdk1_6examples.javax.swing.jtooglebutton;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class JToogleButtonsTests extends JPanel{
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(JToogleButtonsTests.class.getName());


 /**
  * The JToogleButtonsTests default constuctor.
  */
  public JToogleButtonsTests(){
    this.setLayout(new BorderLayout());

    final JToggleButton jtb = new JToggleButton("ahahahahah");
    this.add(jtb);

    jtb.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {

          @Override
          public void run() {
            try {
              Thread.sleep(750);
            } catch (InterruptedException interruptedException) {
            }

            SwingUtilities.invokeLater(new Runnable() {

              @Override
              public void run() {
                jtb.setSelected(false);
                jtb.setEnabled(true);

                System.out.println("--");
              }
            });
          }
        }).start();
      }
    });

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("JToogleButtonsTests").append("").toString();
  }

  public static void main(final String[] args){
    final JToogleButtonsTests clazz = new JToogleButtonsTests();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(clazz);
    frame.setBounds(100,100,350,250);
    frame.setVisible(true);
  }
}
