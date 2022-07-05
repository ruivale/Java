package exp.swing.spinner;

import javax.swing.*;
import java.awt.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JSpinner.NumberEditor;
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
public class MyNumericSpinner extends JPanel {

  protected JSpinner jp = new JSpinner();
  /**
   *
   * @param iValue int
   * @param iMinimum int
   * @param iMaximum int
   * @param iStepSize int
   */
  public MyNumericSpinner(
      final int iValue,
      final int iMinimum,
      final int iMaximum,
      final int iStepSize) {

    final SpinnerNumberModel sm = new SpinnerNumberModel(iValue,
                                                         iMinimum,
                                                         iMaximum,
                                                         iStepSize);
    jp.setModel(sm);





    final DefaultEditor c = (DefaultEditor) jp.getEditor();
    final JFormattedTextField jft = c.getTextField();

    //final RegexFormatter f = new RegexFormatter("[01234567]\\d{2}000");
    final RegexFormatter f = new RegexFormatter("\\d000");
    f.setAllowsInvalid(false);
    f.setOverwriteMode(true);
    f.setCommitsOnValidEdit(false);

    jft.setFocusLostBehavior(JFormattedTextField.REVERT);
    jft.setFormatterFactory(
      new DefaultFormatterFactory(f));






    this.setLayout(new BorderLayout());
    this.add(jp);



  }

  public static void main(String[] args) {
    final MyNumericSpinner m = new MyNumericSpinner(2000,0,999000,1000);
    JFrame f = new JFrame();
    f.setTitle("JSpinner");
    f.getContentPane()
        .setLayout(new BorderLayout());
    f.getContentPane().add(m, BorderLayout.CENTER);
    f.setBounds(100, 100, 200, 70);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

    try {
      int value = -1;
      int i = 0;
      while (i++ < 5) {
        Thread.sleep(1000);
        value = ((Integer)m.jp.getValue()).intValue();

        System.out.println("value=" + value);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }


    m.jp.setValue(new Integer("3459"));

  }
}
