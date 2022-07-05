package exp.swing.jtextfields;


import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
public class NyJTextFieldsTests extends JPanel{
  public NyJTextFieldsTests() {
    setLayout(new BorderLayout());

    final JTextField jt = new JTextField("12345");
    final JButton b = new JButton("select text");
    b.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent evt){
        /**
        jt.requestFocus();
        jt.select(0, 3);
        //jt.select(0, jt.getText().length()-1);
        jt.setCaretPosition(0);
        /**/

        jt.setCaretPosition(
          jt.getText().length());
        jt.select(
          0,
          jt.getText().length());
        jt.requestFocus();

      }
    });
    add(jt, BorderLayout.NORTH);
    add(b, BorderLayout.SOUTH);



    jt.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent e) {
        jt.selectAll();

        System.out.println(jt.getText());
      }

      public void focusLost(FocusEvent evtFocus) {
        final String strValue = jt.getText();
      }
    });
  }

  public static void main(String[] args) {
    NyJTextFieldsTests n = new NyJTextFieldsTests();
    JFrame f = new JFrame("NetVideoRecRecordings");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(n, BorderLayout.CENTER);
    f.pack();
    f.setVisible(true);
  }
}
