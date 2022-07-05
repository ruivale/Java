package exp.swing.jlist.sort;

import java.awt.*;
import java.awt.event.*;

import java.text.Collator;

import java.util.*;

import javax.swing.*;


// import java.util.Locale;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class SortJList extends JPanel implements ActionListener {
  JButton jb1;
  JButton jb2;
  JTextField tf;
  JList list;
  int i = 1;

  public SortJList() {
    Vector data;
    setLayout(new BorderLayout());
    list = new JList();
    list.setModel(new DefaultListModel());

    add(new JScrollPane(list), "Center");

    add(jb1 = new JButton("Add"), "West");
    add(jb2 = new JButton("Sort"), "East");
    add(tf = new JTextField(), "North");
    jb1.addActionListener(this);
    jb2.addActionListener(this);
  }

  public Dimension getPreferredSize() {
    return new Dimension(50, 50);
  }

  public void actionPerformed(ActionEvent ae) {
    DefaultListModel dlm;

    if (ae.getSource() == jb1) {
      // add
      dlm = (DefaultListModel) list.getModel();
      dlm.addElement((Object) tf.getText());

    } else {
      // sort
      dlm = (DefaultListModel) list.getModel();

      int numItems = dlm.getSize();
      String[] a = new String[numItems];

      for (int i = 0; i < numItems; i++) {
        a[i] = (String) dlm.getElementAt(i);
      }

      sortArray(Collator.getInstance(), a);

      // Locale loc = Locale.FRENCH;
      // sortArray(Collator.getInstance(loc), (String[])a);
      for (int i = 0; i < numItems; i++) {
        dlm.setElementAt(a[i], i);
      }
    }
  }

  public static void sortArray(Collator collator, String[] strArray) {
    String tmp;

    if (strArray.length == 1) {
      return;
    }

    for (int i = 0; i < strArray.length; i++) {
      for (int j = i + 1; j < strArray.length; j++) {
        if (collator.compare(strArray[i], strArray[j]) > 0) {
          tmp = strArray[i];
          strArray[i] = strArray[j];
          strArray[j] = tmp;
        }
      }
    }
  }

  public static void main(String[] s) {
    JFrame frame = new JFrame("Sort JList");
    SortJList panel = new SortJList();
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.getContentPane().add(panel, "Center");

    frame.setSize(200, 200);
    frame.setVisible(true);
  }
}
