/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jformattedtextfield.MyJFormattedTextField
 * </p>
 *
 * <p>Copyright: Copyright (c) 2010 EFACEC SE
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

package jdk1_6examples.javax.swing.jformattedtextfield;


import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class MyJFormattedTextField {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(MyJFormattedTextField.class.getName());

  /** .. */
  private JFormattedTextField jFormattedTextField = new JFormattedTextField();
  private RegexFormatter formatterEquip;

  public static final int INT_RIGHT_ARROW_KEY = 39;
  public static final int INT_LEFT_ARROW_KEY = 37;

 /**
  * The MyJFormattedTextField default constuctor.
  */
  public MyJFormattedTextField(){
    
//    this.jFormattedTextField.addKeyListener(new KeyAdapter(){
//      public void keyReleased(final KeyEvent e){
//
//        final int iCharCode = e.getKeyCode();
//
//        if(iCharCode!=INT_RIGHT_ARROW_KEY && iCharCode!=INT_LEFT_ARROW_KEY &&
//           strCharsToAvoid != null && strCharsToAvoid.length > 0){
//          try {
//            final int iCaretPos = jFormattedTextField.getCaretPosition();
//
//            final String strNextChar = jFormattedTextField.getText(iCaretPos, 1);
//
//            if (isNextCharAnAvoidableOne(strNextChar, strCharsToAvoid)) {
//              jFormattedTextField.setCaretPosition(iCaretPos + 1);
//            }
//
//          } catch (BadLocationException ble) {
//            if (LOGGER.isLoggable(Level.SEVERE)) {
//              LOGGER.log(
//                  Level.SEVERE,
//                  "Cannot obtain the textfield next char.",
//                  ble);
//            }
//
//          } catch (IllegalArgumentException ex) {
//            if (LOGGER.isLoggable(Level.SEVERE)) {
//              LOGGER.log(
//                  Level.SEVERE,
//                  "Cannot determine if the next textfield char is an avoidable one or not.",
//                  ex);
//            }
//          }
//        }
//
//      }
//    });
//
//    formatterEquip = new RegexFormatter(strRegExp);
//    formatterEquip.setAllowsInvalid(false);
//    formatterEquip.setOverwriteMode(true);
//    formatterEquip.setCommitsOnValidEdit(true);
//
//    this.jFormattedTextField.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
//    this.jFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(formatterEquip));
//
//    this.jFormattedTextField.setPreferredSize(new Dimension(60,22));
//
//    this.jFormattedTextField.setValue(strInitValue);
//
//    try {
//      this.jFormattedTextField.commitEdit();
//
//    } catch (ParseException ex) {
//      final String _strRegExp = formatterEquip.getPattern().pattern();
//      if (LOGGER.isLoggable(Level.SEVERE)) {
//        LOGGER.log(
//            Level.SEVERE,
//            new StringBuffer(
//                "Cannot set the ini value cause it not valid. RE=").append(
//                    _strRegExp).append(", InitValue=").append(
//                 strInitValue).toString(),
//            ex);
//      }
//
//      throw ex;
//    }

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("MyJFormattedTextField").append("").toString();
  }

  public static void main(final String[] args){
    final MyJFormattedTextField clazz = new MyJFormattedTextField();
  }
}
