package exp.formattextfields;

import java.text.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class FormattedMACAddress
    extends JPanel {
  //~ Instance fields ////////////////////////////////////////////////////////

  JFormattedTextField jftfMAC;
  JFormattedTextField jftfIP;
  JFormattedTextField jftfPaid;

  //~ Constructors ///////////////////////////////////////////////////////////
  /**
   * Creates a new FormattedMACAddress object.
   */
  public FormattedMACAddress() {
    try {
      MaskFormatter maskFormatterMAC = new MaskFormatter("HH.HH.HH.HH.HH.HH");
      maskFormatterMAC.setPlaceholderCharacter('0');
      maskFormatterMAC.setValidCharacters("0123456789abcdefABCDEF");
      jftfMAC = new JFormattedTextField(maskFormatterMAC);

      MaskFormatter maskFormatterIP = new MaskFormatter("###.###.###.###");
      maskFormatterIP.setPlaceholderCharacter('0');
      maskFormatterIP.setValidCharacters("0123456789");
      jftfIP = new JFormattedTextField(maskFormatterIP);


RegexFormatter regexFormatterPaid = new RegexFormatter("\\d{1,200}\\.\\d\\d");
/*
      MaskFormatter maskFormatterPaid = new MaskFormatter("#####################.##");
      //maskFormatterPaid.setPlaceholderCharacter('0');
      //maskFormatterPaid.setValidCharacters("0123456789");
      jftfPaid = new JFormattedTextField(maskFormatterPaid);
*/
      jftfPaid = new JFormattedTextField(regexFormatterPaid);
      jftfPaid.setText("0.00");

RegexFormatter regexFormatterIP = new RegexFormatter("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

JFormattedTextField f = new JFormattedTextField(regexFormatterIP);
/*
JFormattedTextField f = new JFormattedTextField(
  new NumberFormatter(new DecimalFormat("#0.00")));
*/
//f.setValue(new Double(0.00));
//f.addFocusListener(selectOnFocusListener);


      this.setLayout(new GridLayout(4,2));

      this.add(new JLabel("MAC"));
      this.add(jftfMAC);
      this.add(new JLabel("PAID"));
      this.add(jftfPaid);
      this.add(new JLabel("IP"));
      this.add(jftfIP);
      this.add(new JLabel("NEW IP"));
      this.add(f);

    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  //~ Methods ////////////////////////////////////////////////////////////////
  /**
   * DOC
   *
   * @param args
   */
  public static void main(String[] args) {
    FormattedMACAddress formattedMACAddress1 = new FormattedMACAddress();
    JFrame              frame       = new JFrame();
    Container           contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(formattedMACAddress1, BorderLayout.NORTH);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
