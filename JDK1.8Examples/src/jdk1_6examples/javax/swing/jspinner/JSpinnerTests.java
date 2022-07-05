
package jdk1_6examples.javax.swing.jspinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

public class JSpinnerTests extends JFrame {
  public JSpinnerTests() {
    JSpinner m_numberSpinner;
    SpinnerNumberModel m_numberSpinnerModel;

//    Double current = new Double(5.50);
//    Double min = new Double(0.00);
//    Double max = new Double(10.00);
//    Double step = new Double(0.25);
//    m_numberSpinnerModel = new SpinnerNumberModel(current, min, max, step);

    m_numberSpinnerModel = new SpinnerNumberModel(1000, 0, 100000000, 1);

    m_numberSpinner = new JSpinner(m_numberSpinnerModel);
    add(m_numberSpinner);
  }
  public static void main(String argv[]) {
    JSpinnerTests spinnerFrame = new JSpinnerTests();
    spinnerFrame.setBounds(350,400,100, 50);
    spinnerFrame.setVisible(true);
  }
}