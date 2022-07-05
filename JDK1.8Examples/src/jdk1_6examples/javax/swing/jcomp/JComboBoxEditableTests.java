/**
 * <p>
 * Classname: package jdk1_6examples.javax.swing.jcomp.JComboBoxEditableTests
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
package jdk1_6examples.javax.swing.jcomp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class JComboBoxEditableTests extends JPanel {

  /** This class LOGGER */
  private static final Logger LOGGER =
      Logger.getLogger(JComboBoxEditableTests.class.getName());

  /**
   * The JComboBoxEditableTests default constuctor.
   */
  public JComboBoxEditableTests() {
    setLayout(new BorderLayout());

    final JLabel jLabel =
        new JLabel();

    final JComboBox jc =
        new JComboBox(new String[]{"poli", "liny", "reoi", "wert"});

    jc.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("..........");

        if (isSelectedItemPresent(jc, jc.getSelectedItem() + "")) {
          jLabel.setText(jc.getSelectedItem() + "");

        } else {
          jc.addItem(jc.getSelectedItem() + "");
          jLabel.setText(jc.getSelectedItem() + " NEW NEW");
        }
      }
    });
    jc.setEditable(true);
    add(jc, BorderLayout.NORTH);
    add(jLabel, BorderLayout.CENTER);
  }

  /**
   * 
   * @param jc
   * @param strSelItem
   * @return
   */
  private boolean isSelectedItemPresent(
      final JComboBox jc,
      final String strSelItem) {

    boolean is = false;

    if (jc != null && strSelItem != null && strSelItem.length() > 0) {
      final int nItens = jc.getItemCount();

      for (int i = 0; i < nItens; i++) {
        if (strSelItem.equals((String) jc.getItemAt(i))) {
          is = true;
          break;
        }
      }

    } else {
      // just to indicate a non valid selection ...
      is = true;
    }

    return is;
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("JComboBoxEditableTests").append("").toString();
  }

  public static void main(final String[] args) {
    final JComboBoxEditableTests c = new JComboBoxEditableTests();
    JFrame frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(c);
    frame.setSize(250, 150);
    frame.setVisible(true);

  }
}
