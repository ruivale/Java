package exp.swing.comboboxes;

import javax.swing.*;
import java.awt.*;


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
public class TestEditableComboBox extends JPanel{
  public TestEditableComboBox() {
    JComboBox jcb = new JComboBox(new String[]{"111", "222", "333","111", "222", "333"});
    //jcb.setEditable(true);
    this.setLayout(new BorderLayout());
    this.add(jcb, BorderLayout.CENTER);
  }

  public static void main(String[] s){
    TestEditableComboBox t = new TestEditableComboBox();
    JFrame f = new JFrame("test editable comboboxes");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(t, BorderLayout.CENTER);
    f.setBounds(100, 100, 300, 200);
    f.setVisible(true);

  }
}
