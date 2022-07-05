package exp.swing.menus;

import javax.swing.*;
import java.awt.*;

import javax.swing.event.*;
import java.awt.event.*;


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
public class TestingMiscMenus {
  public TestingMiscMenus() {

    JMenuBar jmb = new JMenuBar();
    JMenu ma = new JMenu("AAAA");
    JMenu mb = new JMenu("BBBB");
    JMenu mc = new JMenu("CCCC");
    jmb.add(ma);
    jmb.add(mb);
    jmb.add(mc);

    JMenu mb1 = new JMenu("BBBB 1111");
    JMenuItem mi = new JMenuItem("Menu item");
    JRadioButton r1 = new JRadioButton("r1");
    JRadioButton r2 = new JRadioButton("r2");
    mb1.add(new JMenuItem("AHAHAHA"));
    mb1.add(new JMenuItem("BHBHBHB"));
    mb1.add(mi);
    mb1.add(r1);
    mb1.add(r2);


    Component[] compsMB1 = mb1.getMenuComponents();
    for (int i = 0; i < compsMB1.length; i++) {
      if(compsMB1[i] == mi){
        System.out.println("found the mi comp. i="+i+".");
        break;
      }
    }

    mb.add(mb1);

    JFrame f = new JFrame("FRAME");
    f.setJMenuBar(jmb);
    f.setBounds(100,100,400,200);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

  }

  public static void main(String[] args) {
    TestingMiscMenus testingmiscmenus = new TestingMiscMenus();
  }
}
