package exp.swing.button.dropdown;

import java.awt.*;
import java.awt.event.*;

import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.*;
import java.beans.PropertyChangeEvent;

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
public class TestDropDownButton extends JPanel{

  static DDB ddb;

  public TestDropDownButton() {
    ddb = new DDB();
    ddb.setText("AHAHAHAHAH");
//    setLayout(new BorderLayout());
//    add(ddb,BorderLayout.CENTER);
  }

  public static void main(String a[]){
    TestDropDownButton m = new TestDropDownButton();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(m, BorderLayout.CENTER);
    JToolBar pToolbar = new JToolBar(JToolBar.HORIZONTAL);
    ddb.addToToolBar(pToolbar);
    f.getContentPane().add(pToolbar, BorderLayout.NORTH);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(100,100,400,300);
    f.setVisible(true);


  }

  class DDB extends DropDownButton{
    JPopupMenu popup = new JPopupMenu("L A B E L");

    DDB(){
      super();

      popup.add(new JMenuItem("M E N U   I T E M   1"));
      popup.add(new JMenuItem("M E N U   I T E M   2"));
      popup.add(new JMenuItem("M E N U   I T E M   3"));
      popup.add(new JMenuItem("M E N U   I T E M   4"));
      popup.add(new JMenuItem("M E N U   I T E M   5"));
      popup.add(new JMenuItem("M E N U   I T E M   6"));
      popup.add(new JMenuItem("M E N U   I T E M   7"));

    }

    public JPopupMenu getPopupMenu(){
      return popup;
    }
  }

}
