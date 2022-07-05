package jdk1_6examples.javax.swing.jtables;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTableSortDemo {
  public static void main(String[] args) {
    //int[] numerics = new int[]{5, 2, 4, 8};
    double[] numerics = new double[]{5.5, 2.2, 11.11, 8.8};
    final Object[][] data = { 
      { "A", numerics[0] },
      { "B", numerics[1] },
      { "C", numerics[2] },
      { "D", numerics[3] } };
    final String columnNames[] = { "Item", "Value" };
    final TableModel model = new DefaultTableModel(data, columnNames) {
      public Class<?> getColumnClass(int column) {
        return getValueAt(0, column).getClass();
      }
    };
    final JTable table = new JTable(model);
//    table.setAutoCreateRowSorter(true);

    final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
    table.setRowSorter(sorter);
    sorter.setSortsOnUpdates(true);

    final JButton jb = new JButton("click me");
    jb.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent evt){
        final int iSelRow = table.getSelectedRow();
        final int iColumns = model.getColumnCount();
        final StringBuilder sb = new StringBuilder(20);
        
        sb.append("Selected row "+iSelRow+" [");
        for (int i = 0; i < iColumns; i++) {
          sb.append(model.getValueAt(iSelRow, i)).append(",");
        }
        sb.append("]");

        int modelRow = table.convertRowIndexToModel(iSelRow);
        sb.append(" - In Model row "+modelRow+" [");
        for (int i = 0; i < iColumns; i++) {
          sb.append(model.getValueAt(modelRow, i)).append(",");
        }
        sb.append("]");
        

        System.out.println(sb.toString());


        table.setEnabled(false);

        new Thread(new Runnable() {

          @Override
          public void run() {
            try {
              System.out.println("Sleeping ...");
              Thread.sleep(1500);
            } catch (Exception e) {
            }
            SwingUtilities.invokeLater(new Runnable() {

              @Override
              public void run() {
                System.out.println("enabling table");
                table.setEnabled(true);
              }
            });
          }
        }).start();

      }
    });

    final JScrollPane scrollPane = new JScrollPane(table);
    final JFrame frame = new JFrame("Sorting Table");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(jb, BorderLayout.SOUTH);

    frame.setSize(300, 200);
    frame.setVisible(true);
  }
}
