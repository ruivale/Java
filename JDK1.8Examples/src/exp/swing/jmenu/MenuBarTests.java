package exp.swing.jmenu;

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
public class MenuBarTests extends JFrame{

  JMenuBar mb = new JMenuBar();

  public MenuBarTests() {

    JMenu m1 = new JMenu("m1");
    JMenu m2 = new JMenu("m2");
    JMenu m3 = new JMenu("m3");
    mb.add(m1);
    mb.add(m2);
    mb.add(m3);

    JMenuItem mi11 = new JMenuItem("11");
    JMenu m12 = new JMenu("12");
    JMenuItem mi121 = new JMenuItem("121");
    m12.add(mi121);
    JMenuItem mi13 = new JMenuItem("13");
    m1.add(mi11);
    m1.add(m12);
    m1.add(mi13);

    JMenuItem mi21 = new JMenuItem("21");
    m2.add(mi21);

    this.setJMenuBar(mb);

    this.setSize(400, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);


    try {
      System.out.println("Sleeping ...");
      Thread.sleep(5000);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.out.println("... waking up.");


    m2.removeAll();

    final int intNbrOfComps = m1.getItemCount();
    JMenuItem[] mToolsAdminMenus = new JMenuItem[intNbrOfComps];

    for (int i = 0; i < intNbrOfComps; i++) {
      mToolsAdminMenus[i] = m1.getItem(i);
    }

    m1.removeAll();

    for (int i = 0; i < intNbrOfComps; i++) {
      m2.add(mToolsAdminMenus[i]);
    }

    if (SwingUtilities.isEventDispatchThread()) {
      validate();
    } else {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          validate();
        }
      });
    }


  }

  public static void main(final String[] args) {
    new MenuBarTests();
  }

}
