package exp.swing.jtables.cellrenderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;


public class FlashCellTable {

  public static Color color;

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setSize(800, 600);

    final JTable table = new JTable(4, 4);
    table.setDefaultRenderer(Object.class, new MyFlashingCellRenderer());
    table.setValueAt("Flashing", 0, 0);
    frame.getContentPane().add(new JScrollPane(table));

    final long startTime = System.currentTimeMillis();

    Thread thread = new Thread() {
      @Override
      public void run() {
        while (true) {
          long now = System.currentTimeMillis();
          long second = (now - startTime) / 1000;
          color = second / 2 * 2 == second ? Color.red : Color.white;

          System.out.println("FlashCellTable.run");

          SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              table.tableChanged(new TableModelEvent(table.getModel(), 0, 1, 0));
              table.tableChanged(new TableModelEvent(table.getModel(), 0, 1, 1));
              table.tableChanged(new TableModelEvent(table.getModel(), 0, 1, 2));
              table.tableChanged(new TableModelEvent(table.getModel(), 0, 1, 3));
            }
          });
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
          }
        }
      }
    };

    thread.start();

    frame.setVisible(true);
  }


  public static class MyFlashingCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
      JLabel label =
          (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

      if (row == 0 || row == 1 /*"Flashing".equals(value)*/) {
        label.setBackground(color);
      } else {
        label.setBackground(Color.white);
      }
      return label;
    }
  }

}