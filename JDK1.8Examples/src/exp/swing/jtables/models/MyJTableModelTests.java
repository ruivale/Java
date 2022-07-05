package exp.swing.jtables.models;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class MyJTableModelTests extends JPanel{
int kl = 5;

  public MyJTableModelTests() {
    try {
      jbInit();
      buildTable();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    MyJTableModelTests s = new MyJTableModelTests();
    JFrame f = new JFrame("NetVideoRecRecordings");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(s, BorderLayout.CENTER);
    f.setBounds(100, 100, 600, 500);
    //f.pack();
    f.setVisible(true);
  }

  private void buildTable(){

    Object[][] data = {
        {"1","1","1","1"},
        {"2","2","2","2"},
        {"3","3","3","3"},
        {"4","4","4","4"},
        {"5","5","5","5"},
    };
    Object[] headers = {"First","Second","Third","Fourth"};

    DefaultTableModel model = new DefaultTableModel(data, headers);

    jTable = new JTable(model);

    jScrollPane1.getViewport().add(jTable);

  }


  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jPanel2.setLayout(borderLayout2);
    jButtonAdd.setText("Add");
    jButtonAdd.addActionListener(new
                                 MyJTableModelTests_jButtonAdd_actionAdapter(this));
    jButtonRem.setText("Remove");
    jButtonRem.addActionListener(new MyJTableModelTests_jButtonRem_actionAdapter(this));
    this.add(jPanel1, java.awt.BorderLayout.SOUTH);
    jPanel1.add(jButtonAdd);
    jPanel1.add(jButtonRem);
    this.add(jPanel2, java.awt.BorderLayout.CENTER);
    jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);
    //jScrollPane1.getViewport().add(jTable);
  }

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  BorderLayout borderLayout2 = new BorderLayout();
  JTable jTable = new JTable();
  JButton jButtonAdd = new JButton();
  JButton jButtonRem = new JButton();


  public void jButtonRem_actionPerformed(ActionEvent e) {
    int index = this.jTable.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel)this.jTable.getModel();
    if(index > -1 && index < model.getRowCount()){
      model.removeRow(index);
    }
  }

  public void jButtonAdd_actionPerformed(ActionEvent e) {
    kl++;
    Object[] data = {""+kl,""+kl,""+kl,""+kl };
    DefaultTableModel model = (DefaultTableModel)this.jTable.getModel();
    model.insertRow(model.getRowCount(), data);
  }
}

class MyJTableModelTests_jButtonRem_actionAdapter
    implements ActionListener {
  private MyJTableModelTests adaptee;
  MyJTableModelTests_jButtonRem_actionAdapter(MyJTableModelTests adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonRem_actionPerformed(e);
  }
}

class MyJTableModelTests_jButtonAdd_actionAdapter
    implements ActionListener {
  private MyJTableModelTests adaptee;
  MyJTableModelTests_jButtonAdd_actionAdapter(MyJTableModelTests adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonAdd_actionPerformed(e);
  }
}
