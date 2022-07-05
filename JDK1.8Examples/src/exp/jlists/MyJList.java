package exp.jlists;


import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class MyJList
    extends JPanel {
  JPanel jPanel1 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  DefaultListModel dlm = new DefaultListModel();
  JList jList1 = new JList(dlm);

  public MyJList() {
    try {
      jbInit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setLayout(null);
    jPanel1.setBounds(new Rectangle(20, 19, 364, 270));
    jPanel1.setLayout(null);
    jScrollPane1.setBounds(new Rectangle(14, 10, 338, 252));
    this.add(jPanel1, null);
    jPanel1.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(jList1, null);

    final JCheckBox ml[] = {
        new JCheckBox("1", false),
        new JCheckBox("2", false),
        new JCheckBox("3", false),
        new JCheckBox("4", false),
    };

    this.dlm.addElement(ml[0]);
    this.dlm.addElement(ml[1]);
    this.dlm.addElement(ml[2]);
    this.dlm.addElement(ml[3]);

    final ModuleListCellRenderer cellRend = new ModuleListCellRenderer(ml);
    this.jList1.setCellRenderer(cellRend);



    new Thread(new Runnable() {
      public void run() {
        try {
          System.out.println("Slepping ...");
          Thread.sleep(5000);
          System.out.println("... waking up!");
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }

        dlm.clear();
        cellRend.setJCheckBoxes(null);

        jList1.revalidate();
        jList1.repaint();

        final JCheckBox ml[] = {
            new JCheckBox("2.1", false),
            new JCheckBox("2.2", false),
            new JCheckBox("2.3", false),
            new JCheckBox("2.4", false),
            new JCheckBox("2.5", false),
            new JCheckBox("2.6", false),
        };

        cellRend.setJCheckBoxes(ml);

        dlm.addElement(ml[0]);
        dlm.addElement(ml[1]);
        dlm.addElement(ml[2]);
        dlm.addElement(ml[3]);
        dlm.addElement(ml[4]);
        dlm.addElement(ml[5]);


        jList1.revalidate();
        jList1.repaint();

      }
    }).start();




    /***/
    this.jList1.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){

        System.out.println("mouseClicked");

        int intSelectedListRow = jList1.getSelectedIndex();

        if(intSelectedListRow>-1 && intSelectedListRow < dlm.size()){
          JCheckBox jCheckBox = (JCheckBox)dlm.get(intSelectedListRow);

          jCheckBox.setSelected(!jCheckBox.isSelected());
          jList1.repaint();
        }
      }
    });
    /**/

/*
    MyLabels ml[] = {
        new MyLabels("", "1"),
        new MyLabels("", "2"),
        new MyLabels("", "3"),
        new MyLabels("", "4"),
    };

    this.dlm.addElement(ml[0].l.getText());
    this.dlm.addElement(ml[1].l.getText());
    this.dlm.addElement(ml[2].l.getText());
    this.dlm.addElement(ml[3].l.getText());
*/

  }

  class MyLabels
      extends JPanel {
    JCheckBox cb = new JCheckBox();
    JLabel l = new JLabel();

    MyLabels(String cbText, String lText) {
      this.cb.setText(cbText);
      this.l.setText(lText);
    }

    private void jbinit(){
      FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
      this.setLayout(fl);
      this.add(this.cb);
      this.add(this.l);
    }
  }

  public static void main(String[] args) {
    MyJList myJList1 = new MyJList();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(myJList1, BorderLayout.CENTER);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(400,350);
    f.setVisible(true);
  }

}
class ModuleListCellRenderer
    extends DefaultListCellRenderer {
  JLabel label = null;
  Font fontPlain = null;
  Font fontItalic = null;
  JCheckBox ml[];


  public ModuleListCellRenderer(JCheckBox ml[]) {
    this.ml = ml;
    this.fontPlain = this.getFont();
    this.fontItalic = fontPlain.deriveFont(Font.ITALIC, 10);
  }

  public void setJCheckBoxes(final JCheckBox ml[]){
    this.ml = ml;
  }

  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {

    try {

System.out.println(index+" -> cellHasFocus="+cellHasFocus+", isSelected("+
isSelected+"), ml[index].isSelected()="+ml[index].isSelected()+".");


      this.ml[index].setBackground(list.getBackground());

      return this.ml[index];

    } catch (Exception e) {
      return this;
    }
  }
}
















