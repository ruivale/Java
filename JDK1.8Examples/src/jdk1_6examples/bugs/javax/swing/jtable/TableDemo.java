package jdk1_6examples.bugs.javax.swing.jtable;

import javax.swing.*;
import java.awt.*;

/**
 *
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */
public class TableDemo extends JFrame {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
          // ignored
        }
        new TableDemo().setVisible(true);
      }
    });
  }

  public TableDemo() {
    super("Table Demo");

    add(new JScrollPane(createTable()), BorderLayout.CENTER);
    pack();
    setLocationByPlatform(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private Component createTable() {
    return new JTable(new String[][]{
                        {"111111", "111111111"},
                        {"222222", "222222222"}},
                      new String[]{"header 1", "header 2"});

    /**
    TableFormat<Bug> tableFormat = new BeanTableFormat<Bug>(
        Bug.class,
        new String[]{"legCount", "name"},
        new String[]{"Legs", "Name"});
    EventList<Bug> bugList = new BasicEventList<Bug>();
    bugList.addAll(Arrays.asList(new Bug("Aidan", 8), new Bug("Tanner", 6),
        new Bug("Eric", 4), new Bug("Jen", 8), new Bug("Dexter", 6)));

    SortedList<Bug> sortedBugList = new SortedList<Bug>(bugList, null);
    TableModel tableModel = new EventTableModel<Bug>(
        sortedBugList, tableFormat);
    JTable table = new JTable(tableModel);
    new TableComparatorChooser<Bug>(table, sortedBugList, false);
    return table;
    */
  }
}
