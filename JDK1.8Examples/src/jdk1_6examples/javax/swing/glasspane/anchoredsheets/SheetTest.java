/**
 * <p>
 * Classname: package jdk1_6examples.javax.swing.glasspane.anchoredsheets.SheetTest
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
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
package jdk1_6examples.javax.swing.glasspane.anchoredsheets;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - rui dot vale at efacec dot pt
 * @version $Revision: 1.1 $
 */
public class SheetTest
    extends Object
    implements PropertyChangeListener {

  JOptionPane optionPane;
  SheetableJFrame frame;

  public static void main(String[] args) {
    new SheetTest();
  }

  public SheetTest() {
    frame = new SheetableJFrame("Sheet test");
    // put an image in the frame's content pane
    ImageIcon icon = new ImageIcon("d:/temp/WinNetworkPCConfig.jpg");
    JLabel label = new JLabel(icon);
    frame.getContentPane().add(label);
    // build JOptionPane dialog and hold onto it
    optionPane = new JOptionPane("Do you want to save?",
        JOptionPane.QUESTION_MESSAGE,
        JOptionPane.YES_NO_OPTION);
    frame.pack();
    frame.setVisible(true);
    optionPane.addPropertyChangeListener(this);
    // pause for effect, then show the sheet
    try {
      Thread.sleep(1000);
    } catch(InterruptedException ie) {
    }
    JDialog dialog =
        optionPane.createDialog(frame, "irrelevant");
    frame.showJDialogAsSheet(dialog);
  }

  public void propertyChange(PropertyChangeEvent pce) {
    if(pce.getPropertyName().equals(JOptionPane.VALUE_PROPERTY)) {
      System.out.println("Selected option " +
          pce.getNewValue());
      frame.hideSheet();
    }
  }
}
