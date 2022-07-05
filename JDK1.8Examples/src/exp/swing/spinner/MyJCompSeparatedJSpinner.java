package exp.swing.spinner;

import javax.swing.*;
import java.awt.*;
import javax.swing.JSpinner.DefaultEditor;
import exp.formattextfields.RegexFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
public class MyJCompSeparatedJSpinner extends JPanel{

  final JSpinner jp = new JSpinner();



  public MyJCompSeparatedJSpinner(
      final int iValue,
      final int iMinimum,
      final int iMaximum,
      final int iStepSize) {

    final SpinnerNumberModel sm = new SpinnerNumberModel(iValue,
                                                         iMinimum,
                                                         iMaximum,
                                                         iStepSize);
    jp.setModel(sm);

    final DefaultEditor defaultEditor = (DefaultEditor) jp.getEditor();


    this.setLayout(new FlowLayout(FlowLayout.LEFT));

    final JLabel jl = new JLabel("000");

    jp.setPreferredSize(new Dimension(16,22));
    defaultEditor.setPreferredSize(new Dimension(50,22));

    this.add(defaultEditor);
    this.add(jl);
    this.add(jp);


    final JFormattedTextField jft = defaultEditor.getTextField();

    //final RegexFormatter f = new RegexFormatter("[01234567]\\d{2}000");
    final RegexFormatter f = new RegexFormatter("\\d?\\d?\\d");
    f.setAllowsInvalid(false);
    f.setOverwriteMode(true);
    f.setCommitsOnValidEdit(true);

    jft.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
    jft.setFormatterFactory(
      new DefaultFormatterFactory(f));

    jft.addKeyListener(new KeyAdapter(){
      public void keyReleased(KeyEvent e){
        final Object objOld = jp.getValue();
        final int iCaretIndex = jft.getCaretPosition();

        try {
          jp.setValue(jft.getValue());
          jp.commitEdit();

        } catch (Exception ex) {

          jp.setValue(objOld);
          jft.setValue(objOld);

          ex.printStackTrace();

        }finally{
          try {
            jft.setCaretPosition(iCaretIndex);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    });

  }

  public static void main(String[] args) {
    MyJCompSeparatedJSpinner m = new  MyJCompSeparatedJSpinner(885,0,888,1);
    JFrame f = new JFrame();
    f.setTitle("JSpinner");
    f.getContentPane()
        .setLayout(new BorderLayout());
    f.getContentPane().add(m, BorderLayout.CENTER);
    f.setBounds(100, 100, 200, 70);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
