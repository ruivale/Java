package exp.swing.joptionpanes;


import javax.swing.*;

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
public class TestAllJOPTypes {
  public TestAllJOPTypes() {

    JOptionPane.showConfirmDialog(null,
                                  "message",
                                  "title",
                                  JOptionPane.INFORMATION_MESSAGE);

    JOptionPane.showMessageDialog(null,
                                  "message",
                                  "title",
                                  JOptionPane.INFORMATION_MESSAGE);

    java.lang.Object[] options = {"strOk"};
    JOptionPane.showOptionDialog(
        null,
        "msg",
        "title",
        javax.swing.JOptionPane.DEFAULT_OPTION,
        javax.swing.JOptionPane.INFORMATION_MESSAGE,
        null, //don't use a custom Icon
        options, //the titles of buttons
        options[0]); //default button title

  }

  public static void main(String[] args) {
    TestAllJOPTypes testalljoptypes = new TestAllJOPTypes();
  }
}
