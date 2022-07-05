/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jdialog.JDialogsModalModes
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

package jdk1_6examples.javax.swing.jdialog;


import java.awt.Dialog.ModalityType;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class JDialogsModalModes {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(JDialogsModalModes.class.getName());


 /**
  * The JDialogsModalModes default constuctor.
  */
  public JDialogsModalModes(){
    final JFrame f = new JFrame("Main window");
    f.setBounds(100,100, 800, 550);

    final JButton b = new JButton(" JDialog(f, title, false)");
    b.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        JDialog d = new JDialog(f, " JDialog(f, title, false)", false);
        d.setLocationRelativeTo(f);
        d.setSize(200,200);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setVisible(true);
      }
    });

    final JButton b1 = new JButton(" JDialog(f, title, true)");
    b1.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        JDialog d = new JDialog(f, " JDialog(f, title, true)", true);
        d.setLocationRelativeTo(f);
        d.setSize(200,200);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setVisible(true);
      }
    });
    final JButton b2 = new JButton(" JDialog(f, title, ModalityType.MODELESS)");
    b2.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        JDialog d = new JDialog(f, " ModalityType.MODELESS", ModalityType.MODELESS);
        d.setLocationRelativeTo(f);
        d.setSize(200,200);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setVisible(true);
      }
    });
    final JButton b3 = new JButton(" JDialog(f, title, ModalityType.APPLICATION_MODAL)");
    b3.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        JDialog d = new JDialog(f, "ModalityType.APPLICATION_MODAL", ModalityType.APPLICATION_MODAL);
        d.setLocationRelativeTo(f);
        d.setSize(200,200);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setVisible(true);
      }
    });
    final JButton b4 = new JButton(" JDialog(f, title, ModalityType.DOCUMENT_MODAL)");
    b4.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        JDialog d = new JDialog(f, "ModalityType.DOCUMENT_MODAL", ModalityType.DOCUMENT_MODAL);
        d.setLocationRelativeTo(f);
        d.setSize(200,200);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setVisible(true);
      }
    });
    final JButton b5 = new JButton(" JDialog(f, title, ModalityType.TOOLKIT_MODAL)");
    b5.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        JDialog d = new JDialog(f, "ModalityType.TOOLKIT_MODAL", ModalityType.TOOLKIT_MODAL);
        d.setLocationRelativeTo(f);
        d.setBounds(900,500,200,200);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setVisible(true);
      }
    });
    final JButton[] bs = {b, b1, b2, b3, b4, b5};


    f.setLayout(new GridLayout(6,1));
    for (int i = 0; i < bs.length; i++) {
      final int j = i;
      bs[i].addMouseMotionListener(new MouseMotionAdapter(){
        public void mouseMoved(MouseEvent e) {
          bs[j].requestFocus();

          if(j==0){
            f.requestFocus();
            f.toFront();
          }
        }
      });
      f.add(bs[i]);
    }

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);


    new Thread(new Runnable() {
      public void run() {
        try {
          while (true) {
            Thread.sleep(1503);
            final Window[] wsOwned = f.getOwnedWindows();
            System.out.print("Owned wins:");
            for (Window window : wsOwned) {
              if(window instanceof JDialog && window.isVisible()){
                System.out.print(((JDialog)window).getTitle()+", ");
              }
            }
            System.out.println("\n");

            final Window[] wsNotOwned = f.getOwnerlessWindows();
            System.out.print("Not owned wins:");
            for (Window window : wsNotOwned) {
              if(window instanceof JDialog && window.isVisible()){
                System.out.print(((JDialog)window).getTitle()+", ");
              }
            }
            System.out.println("\n----------------------------------------------");

          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("JDialogsModalModes").append("").toString();
  }

  public static void main(final String[] args){
    final JDialogsModalModes clazz = new JDialogsModalModes();
  }
}
