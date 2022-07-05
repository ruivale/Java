package exp.swing.jcombobox;

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
public class MyComboBoxWithModel extends JPanel {

  String[] objs = {"ANY","MANUAL","EVENT"};

  public MyComboBoxWithModel() {
    setLayout(new BorderLayout());

    JComboBox jcb = new JComboBox();
    MyCustomComboBoxModel model = new MyCustomComboBoxModel(objs, jcb);
    jcb.setModel(model);

    add(jcb, BorderLayout.CENTER);

  }

  public static void main(final String[] args) {
    MyComboBoxWithModel m = new MyComboBoxWithModel();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(m);
    f.pack();
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

}

class MyCustomComboBoxModel extends  DefaultComboBoxModel{
  final JComboBox jComboBox;
  final Object[] objs;

  public MyCustomComboBoxModel(final Object[] objs, final JComboBox jComboBox) {
    super(objs);

    this.objs = objs;
    this.jComboBox = jComboBox;

  }

  public Object getElementAt(int index) {
    //final int iSelectedIndex = this.jComboBox.getSelectedIndex();

    return new JLabel(this.objs[index]+"");
  }

  public Object _getSelectedItem() {

    System.out.println("MyCustomComboBoxModel - getSelectedItem");

    final int iSelectedIndex = this.jComboBox.getSelectedIndex();

    return this.objs[iSelectedIndex];
  }
}
