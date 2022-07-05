package exp.jlists;

import java.awt.*;
import javax.swing.*;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyJListTests extends JPanel{
  public Vector vector = new Vector();
  DefaultListModel model = new DefaultListModel();

  public MyJListTests() {

    JList jlist = new JList(model);

    Equips e1 = new Equips(1, "1 - 111111111");
    Equips e2 = new Equips(0, "0 - 222222222");
    Equips e3 = new Equips(0, "0 - 333333333");
    Equips e4 = new Equips(1, "1 - 444444444");
    Equips e5 = new Equips(0, "0 - 555555555");
    Equips e6 = new Equips(1, "1 - 666666666");
    Equips e7 = new Equips(0, "0 - 777777777");
    vector.add(e1);
    vector.add(e2);
    vector.add(e3);
    vector.add(e4);
    vector.add(e5);
    vector.add(e6);
    vector.add(e7);

    model.addElement(e1.name);
    model.addElement(e2.name);
    model.addElement(e3.name);
    model.addElement(e4.name);
    model.addElement(e5.name);
    model.addElement(e6.name);
    model.addElement(e7.name);

    jlist.setCellRenderer(new EquipmentsListCellRenderer(this));

    this.setLayout(new BorderLayout());
    this.add(jlist, BorderLayout.CENTER);

  }

  public static void main(String[] args) {
    MyJListTests myJListTests1 = new MyJListTests();
    // The attributes of each component will be shown on a separate tab
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(new MyJListTests(), BorderLayout.CENTER);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
  }

}
/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
class Equips {
  int state = 1;
  String name = "";

  public Equips(int state, String name){
    this.state = state;
    this.name = name;
  }
}

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
class EquipmentsListCellRenderer extends DefaultListCellRenderer {
  JLabel label = null;
  MyJListTests myJListTests;
  Font fontPlain = null;
  Font fontItalic = null;

  public EquipmentsListCellRenderer(MyJListTests myJListTests){
    this.myJListTests = myJListTests;
    fontPlain = this.getFont();
    fontItalic = fontPlain.deriveFont(Font.ITALIC, 10);
  }


  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus){

//System.out.println("getListCellRendererComponent(value:"+value+", index:"+index+
//", isSelected:"+isSelected+", cellHasFocus:"+cellHasFocus+")");

      try{

        Equips equips = (Equips)myJListTests.vector.get(index);

        this.setText( (String) value);

        if(equips != null && equips.state == 0){

System.out.println("value:"+value+", index:"+index+", entEquipInfo != null && entEquipInfo.getState() == 0");

          //JLabel jlabel = new JLabel(equips.name);
          this.setFont(this.fontItalic);

        }else{
          this.setFont(this.fontPlain);
        }

        this.setForeground(Color.red);

        return this/*new JCheckBox("AAAA", isSelected)*/;

      }catch(Exception e){
        System.out.println("EquipmentEvents.java - Error while "+
                         "rendering the equips in the appropriate list.");

        this.setText((String)value);
        return this;
      }
    }
}
