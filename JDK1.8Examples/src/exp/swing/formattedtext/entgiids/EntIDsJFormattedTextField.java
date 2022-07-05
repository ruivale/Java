package exp.swing.formattedtext.entgiids;


import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.DefaultFormatterFactory;

import exp.formattextfields.RegexFormatter;


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
public class EntIDsJFormattedTextField
    extends JFormattedTextField {

  /**
   *
   * @param strRegExp String
   * @param strInitValue String
   * @param iCaretPos int
   */
  public EntIDsJFormattedTextField(
      final String strRegExp,
      final String strInitValue,
      final int iCaretPos) {

    super();

    RegexFormatter formatterEquip = new RegexFormatter(strRegExp);
    formatterEquip.setAllowsInvalid(false);
    formatterEquip.setOverwriteMode(true);
    formatterEquip.setCommitsOnValidEdit(false);

    this.setFocusLostBehavior(JFormattedTextField.REVERT);
    this.setFormatterFactory(new DefaultFormatterFactory(formatterEquip));
    this.setValue(strInitValue);

    final int iCaretPosition = iCaretPos > strInitValue.length() ?
                               0 :
                               iCaretPos;

    this.addCaretListener(new CaretListener() {
      public void caretUpdate(final CaretEvent e) {

        if (getDocument() != null) {

          if (iCaretPosition < getDocument().getLength() &&
              getCaretPosition() < iCaretPosition) {

            setCaretPosition(iCaretPosition);

          } else if (getCaretPosition() > 0 &&
                     getCaretPosition() == getDocument().getLength()) {
            // Last ... so, back one ...
            setCaretPosition(getDocument().getLength() - 1);
          }
        }
      }
    });
  }

  public static void main(String[] args) {

    EntIDsJFormattedTextField eEquip = new EntIDsJFormattedTextField(
        "[01234567]\\d{2}",
        "001",
        3);

    EntIDsJFormattedTextField eBox = new EntIDsJFormattedTextField(
        "1018\\d{2}",
        "101801",
        4);

    EntIDsJFormattedTextField eZone = new EntIDsJFormattedTextField(
        "1019\\d{2}",
        "101901",
        4);

    JFrame frame = new JFrame();
    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new GridLayout(3, 2));
    contentPane.add(new JLabel("Equip:"));
    contentPane.add(eEquip);
    contentPane.add(new JLabel("Box:"));
    contentPane.add(eBox);
    contentPane.add(new JLabel("Zone:"));
    contentPane.add(eZone);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }
}

