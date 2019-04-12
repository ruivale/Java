/**
 * <p>
 * Classname: exp.swing.jdialog.JDialogsAndAJFrame
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package exp.swing.jdialog;


import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 3, 2015, 7:43:12 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class JDialogsAndAJFrame {
 /**
  * The JDialogsAndAJFrame default constructor.
  */
  public JDialogsAndAJFrame(final JFrame frame, final String strT) {

    JDialog jDiag = new JDialog(frame, strT, Dialog.ModalityType.MODELESS);
    jDiag.addWindowFocusListener(new WindowFocusListener() {

      @Override
      public void windowGainedFocus(final WindowEvent winEvt) {
System.err.println("winGainedFocus("+strT+")");
      }

      @Override
      public void windowLostFocus(final WindowEvent winEvt) {
System.err.println("winLostFocus("+strT+")");
      }
    });

    //jDiag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jDiag.getContentPane().setLayout(new BorderLayout());
    jDiag.setBounds(0,0,325,315);
    //this.oleContainer.setBounds(0,0,320, 290);
    jDiag.getContentPane().add(new JButton("sdkjskdjksjjdsk"));
    jDiag.setVisible(true);


  }

  public static void main(final String[] args){

    SwingUtilities.invokeLater(() -> {
      final JFrame f = new JFrame("main window");
      f.setBounds(0, 0, 1920, 800);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);

      final JDialogsAndAJFrame clazz1 = new JDialogsAndAJFrame(f, "1");
      final JDialogsAndAJFrame clazz2 = new JDialogsAndAJFrame(f, "2");
    });
  }
}
