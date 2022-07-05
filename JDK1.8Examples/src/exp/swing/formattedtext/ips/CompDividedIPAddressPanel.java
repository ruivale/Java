package exp.swing.formattedtext.ips;


import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
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
public class CompDividedIPAddressPanel
    extends JPanel {

  private static final String S_IP_REG_EXP =
      "\\d{0}|0|\\d{1}|\\d{2}|1\\d{2}|2[0-4]\\d{1}|25[0-5]";

  final IPComponent ipC1 = new IPComponent(
      S_IP_REG_EXP,
      "172");
  final IPComponent ipC2 = new IPComponent(
      S_IP_REG_EXP,
      "18");
  final IPComponent ipC3 = new IPComponent(
      S_IP_REG_EXP,
      "56");
  final IPComponent ipC4 = new IPComponent(
      S_IP_REG_EXP,
      "234");
  final IPComponent port = new IPComponent(
      "[0-9]*",
      "9000");


  /**
   *
   */
  public CompDividedIPAddressPanel() {
    final JLabel jLDot1 = new JLabel(".");
    final JLabel jLDot2 = new JLabel(".");
    final JLabel jLDot3 = new JLabel(".");
    final JLabel jL2xDot3 = new JLabel(":");
    jLDot1.setHorizontalAlignment(SwingConstants.LEFT);
    jLDot1.setPreferredSize(new Dimension(5, 22));
    jLDot2.setPreferredSize(new Dimension(5, 22));
    jLDot3.setPreferredSize(new Dimension(5, 22));
    jL2xDot3.setPreferredSize(new Dimension(5, 22));
    jLDot1.setBackground(Color.WHITE);
    jLDot2.setBackground(Color.WHITE);
    jLDot3.setBackground(Color.WHITE);
    jL2xDot3.setBackground(Color.WHITE);

    port.setPreferredSize(new Dimension(65, 22));
    ipC1.setPreferredSize(new Dimension(25, 22));
    ipC2.setPreferredSize(new Dimension(25, 22));
    ipC3.setPreferredSize(new Dimension(25, 22));
    ipC4.setPreferredSize(new Dimension(25, 22));

    port.setHorizontalAlignment(SwingConstants.LEADING);

    this.setBackground(Color.WHITE);
    this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    this.add(ipC1);
    this.add(jLDot1);
    this.add(ipC2);
    this.add(jLDot2);
    this.add(ipC3);
    this.add(jLDot3);
    this.add(ipC4);
    this.add(jL2xDot3);
    this.add(port);

    this.ipC1.addKeyListener(
      new KeyAdapter_ForNumbersField(this.ipC1, null, this.ipC2, 3));
    this.ipC2.addKeyListener(
      new KeyAdapter_ForNumbersField(this.ipC2, this.ipC1, this.ipC3, 3));
    this.ipC3.addKeyListener(
      new KeyAdapter_ForNumbersField(this.ipC3, this.ipC2, this.ipC4, 3));
    this.ipC4.addKeyListener(
      new KeyAdapter_ForNumbersField(this.ipC4, this.ipC3, port, 3));
    this.port.addKeyListener(
      new KeyAdapter_ForNumbersField(this.port, this.ipC4, null, 3));


  }

  /**
   *
   * @return String
   */
  public String getValue() {
    return ipC1.getValue()+"."+
        ipC2.getValue()+"."+
        ipC3.getValue()+"."+
        ipC4.getValue()+"."+
        port.getValue();
  }


  public static void main(String[] args) {

    final CompDividedIPAddressPanel c = new CompDividedIPAddressPanel();

    JFrame frame = new JFrame();
    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new GridLayout(2,1));
    javax.swing.JButton b = new javax.swing.JButton("print value");
    b.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
         System.out.println(c.getValue());
      }
    });
    contentPane.add(b);

    contentPane.add(c);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    try {
      System.out.println("sleeping ..");
      Thread.sleep(5000);
      System.out.println("... wake up!");
      c.ipC1.setValue("145");
      c.ipC2.setValue("145");

    } catch (InterruptedException ex) {
    }

  }

}


class IPComponent
    extends JFormattedTextField {

  /**
   *
   * @param strRegExp String
   * @param strInitValue String
   */
  public IPComponent(
      final String strRegExp,
      final String strInitValue) {

    super();

    this.setBorder(null);

    this.setHorizontalAlignment(SwingConstants.CENTER);

    final RegexFormatter formatterEquip = new RegexFormatter(strRegExp);
    formatterEquip.setAllowsInvalid(false);
    formatterEquip.setOverwriteMode(true);
    formatterEquip.setCommitsOnValidEdit(true);

    this.setFocusLostBehavior(JFormattedTextField.REVERT);
    this.setFormatterFactory(new DefaultFormatterFactory(formatterEquip));
    this.setValue(strInitValue);

    this.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent e) {
        selectAll();
        System.out.println(getText());
      }

      public void focusLost(FocusEvent e) {
        final String strValue = getText();

        if (strValue != null && strValue.length() > 0) {
          try {
            commitEdit();
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        } else {
          setValue("0");
        }
      }
    });

    /***
    this.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseMoved(MouseEvent e) {
        requestFocus();
        setSelectionStart(0);
        setSelectionEnd(getText().length());
      }
    });
    /**/
    /***
    this.addMouseListener(new MouseAdapter(){
      public void mouseEntered(MouseEvent e) {
        requestFocus();
        setSelectionStart(0);
        setSelectionEnd(getText().length());
      }
    });
    /**/
  }
}


class KeyAdapter_ForNumbersField
    extends KeyAdapter {

  private static final Logger LOGGER =
      Logger.getLogger(KeyAdapter_ForNumbersField.class.getName());
  private static final int LEFT_ARROW = 37;
  private static final int RIGHT_ARROW = 39;
  private static final int ZERO_1 = 48;
  private static final int NINE_1 = 57;
  private static final int ZERO_2 = 96;
  private static final int NINE_2 = 105;
  private final JFormattedTextField jTextFieldPrevious;
  private final JFormattedTextField jTextFieldNext;
  private final JFormattedTextField jTextFieldSrc;
  private int intMaxNbrOfNumbers = 3;
  private int length = 0;
  private int keyCode = -1;
  private int caretPos = 0;

  /**
   *
   * @param jTextFieldSrc JFormattedTextField
   * @param jTextFieldPrevious JFormattedTextField
   * @param jTextFieldNext JFormattedTextField
   * @param intMaxNbrOfNumbers int
   */
  public KeyAdapter_ForNumbersField(
      final JFormattedTextField jTextFieldSrc,
      final JFormattedTextField jTextFieldPrevious,
      final JFormattedTextField jTextFieldNext,
      final int intMaxNbrOfNumbers) {

    this.jTextFieldPrevious = jTextFieldPrevious;
    this.jTextFieldNext = jTextFieldNext;
    this.jTextFieldSrc = jTextFieldSrc;
    this.intMaxNbrOfNumbers = intMaxNbrOfNumbers;
  }

  /**
   *
   * @param e KeyEvent
   */
  public void keyReleased(final KeyEvent e) {
    keyCode = e.getKeyCode();

    System.out.println("KeyCode: " + keyCode);

    if(this.jTextFieldNext != null &&
       ((keyCode >= ZERO_1 && keyCode <= NINE_1) ||
        (keyCode >= ZERO_2 && keyCode <= NINE_2))) {
      try {
        this.length = this.jTextFieldSrc.getText().length();
        this.caretPos = this.jTextFieldSrc.getCaretPosition();

        if (this.length >= intMaxNbrOfNumbers &&
            this.caretPos >= intMaxNbrOfNumbers) {
          this.jTextFieldNext.requestFocus();
        }
      } catch (Exception ex) {
        if (LOGGER.isLoggable(Level.SEVERE)) {
          LOGGER.log(
              Level.SEVERE,
              "Cannot process the key release.",
              ex);
        }
      }
    }

    /***
    }else{
      caretPos = this.jTextFieldSrc.getCaretPosition();

      if(keyCode == LEFT_ARROW && caretPos == 0){
        if(this.jTextFieldPrevious != null){
          this.jTextFieldPrevious.requestFocus();
          this.jTextFieldPrevious.setCaretPosition(
              this.jTextFieldPrevious.getText().length()-1);
        }
      }else if(keyCode == RIGHT_ARROW && caretPos == this.intMaxNbrOfNumbers){
        if(this.jTextFieldNext != null){
          this.jTextFieldNext.requestFocus();
        }
      }
    }
    /**/
  }
}
