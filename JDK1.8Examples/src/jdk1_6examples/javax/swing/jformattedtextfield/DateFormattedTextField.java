/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jformattedtextfield.DateFormattedTextField
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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;


/**
 *
 * @author Shirin
 */
public class DateFormattedTextField extends JFormattedTextField implements java.io.Serializable {

  private String mask = "##/##/##";
  protected static final String datePattern = "MM/dd/yy";
  public DateVerifier objDateVerifier;

  /**
   * 
   */
  public DateFormattedTextField() {
    super();
    getMaskFormatter();
    setFocusLostBehavior(PERSIST);
    objDateVerifier = new DateVerifier();
    setInputVerifier(objDateVerifier);

    addFocusListener(new FocusAdapter() {

      @Override
      public void focusGained(FocusEvent e) {
        getMaskFormatter();
      }
    });
// setFormatterFactory(getCutomFormatterfactory());
  }

  public boolean getDateVerifier(boolean p_isPopupErrorEnable) {
    return objDateVerifier.verify(this, p_isPopupErrorEnable);
  }

  @Override
  public Object getValue() {
    if (super.getValue() != null) {
      return super.getValue();
    } else {
      if (!super.getText().equals("__/__/__")) {
        return (Object) super.getText();
      } else {
        return null;
      }
    }
  }

  public MaskFormatter getMaskFormatter() {
    MaskFormatter objMask = new MaskFormatter();
    try {
      objMask.setMask(mask);
      objMask.setPlaceholderCharacter('_');
      objMask.install(this);
    } catch (ParseException pe) {
      System.out.println("Exception " + pe);
    }
    return objMask;
  }
  /* public final String getMask() {
  return mask;
  }
  public final void setMask(final String m) {
  mask = m;
  try {
  MaskFormatter maskFormatter = new MaskFormatter(mask);
  maskFormatter.setPlaceholderCharacter('_');
  maskFormatter.install(this);
  } catch (java.text.ParseException e) {
  e.printStackTrace();
  }
  }*/


  public static void main(String[] args) {
    DateFormattedTextField d = new DateFormattedTextField();
    JFrame                        frame       = new JFrame();
    Container                     contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(d, BorderLayout.CENTER);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    d.setEnabled(false);

  }

}


class DateVerifier extends InputVerifier {

  private boolean isPopupErrorEnable = true;

  private boolean isDateValid(String p_Date) {

    try {
//SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
//formatter.parse(p_Date);
      Pattern p = Pattern.compile("(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.]\\d\\d");
      Matcher m = p.matcher(p_Date);
      return m.matches();
      
    } catch (Exception dfe) {
      System.out.println("Invalid date");
      return false;
    }
  }

  public boolean verify(JComponent input,
                        boolean p_isPopupErrorEnable) {
    this.isPopupErrorEnable = p_isPopupErrorEnable;

    boolean result = verify(input);

    this.isPopupErrorEnable = true;

    return result;
  }

  @Override
  public boolean verify(JComponent input) {
    try {

      if (input instanceof JFormattedTextField) {
        JFormattedTextField jtf = (JFormattedTextField) input;

        if (jtf.getText() == null || jtf.getText().equalsIgnoreCase("__/__/__") || jtf.getText().
            equalsIgnoreCase("") || jtf.getText().length() == 0) {
          jtf.setValue(null);
          jtf.commitEdit();
          ((DateFormattedTextField) jtf).getMaskFormatter();
          return true;
        } else if (isDateValid(jtf.getText())) {
          try {
            jtf.commitEdit();
            return true;
          } catch (ParseException e) {
            if (isPopupErrorEnable) {
              JOptionPane.showMessageDialog(jtf.getParent(),
                  "Invalid Date, Please Try with MM/DD/YY Format", "Date Error..",
                  JOptionPane.ERROR_MESSAGE);
            }
//jtf.setValue(null);
            jtf.commitEdit();
            jtf.selectAll();

            return false;
          }
        } else {
          if (isPopupErrorEnable) {
            JOptionPane.showMessageDialog(input.getParent(),
                "Invalid Date, Please Try with MM/DD/YY Format", "Date Error..",
                JOptionPane.ERROR_MESSAGE);
          }
///jtf.setValue(null);
//jtf.commitEdit();
          jtf.selectAll();
          return false;
        }
      }
      /* End for text field*/

    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
    return true;
  }
}
