/**
 * <p>
 * Classname:  exp.swing.formattedtext.time.MyFormattedTimeTextField
 * </p>
 *
 * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A.
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
package exp.swing.formattedtext.time;

import exp.formattextfields.RegexFormatter;
import ilog.views.util.logging.Level;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 19/Mai/2011, 16:26:55
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class MyFormattedTimeTextField extends JPanel{

  private static final String DATE_MASK = "##:##";
  private static final String MID_NIGHT = "00:00";
  private static final String DATE_VALID_CHARS = "0123456789";
  private static char charPlaceholder = ' ';
  private static String[] strCharsToAvoid = new String[]{":"};

  public static final int INT_RIGHT_ARROW_KEY = 39;
  public static final int INT_LEFT_ARROW_KEY = 37;
  public static final int INT_BACKSPACE_KEY = 8;
  public static final int INT_DELETE_KEY = 127;
  
  
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(MyFormattedTimeTextField.class.getName());

  private JFormattedTextField  jFormattedTextField;
  
  /**
   * The MyFormattedTimeTextField default constuctor.
   */
  public MyFormattedTimeTextField() {
    try {
      setLayout(new BorderLayout());
      
      
      
      
      
      
      
//      
//      final MaskFormatter maskFormatterMAC = new MaskFormatter(DATE_MASK);
//      maskFormatterMAC.setPlaceholderCharacter(charPlaceholder);
//      maskFormatterMAC.setValidCharacters(DATE_VALID_CHARS);
//      this.jFormattedTextField = new JFormattedTextField(maskFormatterMAC);
//      
      
      
      final RegexFormatter regexFormatterTime = new RegexFormatter(
          "(([01][0-9]|2[0123]):[012345]\\d|"+
          ":|"+
//          "([01][0-9]|2[0123]):[012345]|"+
//          "([01][0-9]|2[0123]):|"+
//          "([01][0-9]|2):|"+
//          "([01][0-9]|[0123]):|"+
//          "([01][0-9]):|"+
//          "([01]):|"+
          "  :  )");
      
      this.jFormattedTextField = new JFormattedTextField();
      regexFormatterTime.setAllowsInvalid(false);
      //regexFormatterTime.setOverwriteMode(true);
      //regexFormatterTime.
      //regexFormatterTime.setCommitsOnValidEdit(false);

      //this.jFormattedTextField.setFocusLostBehavior(JFormattedTextField.REVERT);
      this.jFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(regexFormatterTime));
      this.jFormattedTextField.setValue("23:59");

//      this.jFormattedTextField.addFocusListener(new  FocusListener() {
//        public void focusGained(FocusEvent e) {
//          jFormattedTextField.setValue("00:00");
//        }
//        public void focusLost(FocusEvent e) {
//        }
//      });
      
      this.jFormattedTextField.addKeyListener(new KeyAdapter(){
        public void keyReleased(final KeyEvent e){
          final int iCharCode = e.getKeyCode();
          final int iCaretPos = jFormattedTextField.getCaretPosition();
          final String strText = jFormattedTextField.getText();
          
          switch(iCharCode){
            case INT_BACKSPACE_KEY:              
              if(iCaretPos > 0){                                
                try {
                  final String strNextChar = jFormattedTextField.getText(iCaretPos-1, 1);
System.out.println("BACKSPACE caret: "+iCaretPos+", strNextChar:"+strNextChar);

                  if (isNextCharAnAvoidableOne(strNextChar, strCharsToAvoid)) {
                    jFormattedTextField.setCaretPosition(iCaretPos - 1);
                  }
                } catch (Exception ex) {
                  ex.printStackTrace();
                }
                                
                final char[] chars = strText.toCharArray();
                chars[iCaretPos-1] = '0';
                final String str = new String(chars);
                jFormattedTextField.setText(str);
                
System.out.println("BACKSPACE Replacing "+iCaretPos+". str: "+str+"."); 

                jFormattedTextField.setCaretPosition(iCaretPos - 1);
                return;
              }
              break;
              
              
            case INT_DELETE_KEY:
              if(iCaretPos < strText.length()){                                
                try {
                  final String strNextChar = jFormattedTextField.getText(iCaretPos, 1);
System.out.println("DELETE caret: "+iCaretPos+", strNextChar:"+strNextChar);

                  if (isNextCharAnAvoidableOne(strNextChar, strCharsToAvoid)) {
                    jFormattedTextField.setCaretPosition(iCaretPos + 1);
                  }
                } catch (Exception ex) {
                  ex.printStackTrace();
                }
                
                final char[] chars = strText.toCharArray();
                chars[iCaretPos] = '0';
                final String str = new String(chars);
                jFormattedTextField.setText(str);
                
System.out.println("DELETE Replacing "+iCaretPos+". str: "+str+"."); 

                jFormattedTextField.setCaretPosition(iCaretPos + 1);
                return;
              }
              break;
          }
 
          
          
          
          
          
          if(iCharCode != INT_RIGHT_ARROW_KEY && 
             iCharCode != INT_LEFT_ARROW_KEY &&
             strCharsToAvoid != null && 
             strCharsToAvoid.length > 0){
            
            try {
              final String strNextChar = jFormattedTextField.getText(iCaretPos, 1);

              if (isNextCharAnAvoidableOne(strNextChar, strCharsToAvoid)) {
                jFormattedTextField.setCaretPosition(iCaretPos + 1);
              }

            } catch (BadLocationException ble) {
              ble.printStackTrace();

            } catch (IllegalArgumentException ex) {
              ex.printStackTrace();
            }
          }
        }
      });

      
      
      
      
      
      
      this.add(this.jFormattedTextField, BorderLayout.CENTER);      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   *
   * @param strChar2Search String
   * @param strOfAvoidableChars String[]
   * @return boolean
   */
  private static boolean isNextCharAnAvoidableOne(
      final String strChar2Search,
      final String[] strOfAvoidableChars){

    boolean avoid = false;

    if(strChar2Search != null && strChar2Search.length() != 0 &&
       strOfAvoidableChars != null && strOfAvoidableChars.length > 0){

      for (int i = 0; i < strOfAvoidableChars.length; i++) {
        if(strChar2Search.equals(strOfAvoidableChars[i])){
          // FOUND ONE ...
          avoid = true;
          break;
        }
      }
    }

    return avoid;
  }
  
  private void clear(){
    try {
      this.jFormattedTextField.setText(MID_NIGHT);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("MyFormattedTimeTextField").append("").toString();
  }
  
  public static void main(final String[] args) {
    final MyFormattedTimeTextField clazz = new MyFormattedTimeTextField();
    JFrame f = new JFrame();
    f.setTitle("MyFormattedTimeTextField");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(clazz, BorderLayout.CENTER);
    f.setBounds(100, 100, 200, 80);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    final JButton jb = new JButton("clear");
    jb.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        clazz.clear();
      }
    });
    f.getContentPane().add(jb, BorderLayout.SOUTH);
    
    
    
    f.setVisible(true);
    
  }
}
