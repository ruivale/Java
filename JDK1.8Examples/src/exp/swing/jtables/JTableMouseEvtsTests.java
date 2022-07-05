package exp.swing.jtables;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 * <p>
 * Title: </p>
 *
 * <p>
 * Description: </p>
 *
 * <p>
 * Copyright: Copyright (c) </p>
 *
 * <p>
 * Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JTableMouseEvtsTests extends JPanel {

  /**
   * ..
   */
  private static final int NBR_OF_COLUMNS = 6;

  /**
   * ..
   */
  private static final int SEVERITY = 0;

  /**
   * ..
   */
  private static final int DATE = 1;

  /**
   * ..
   */
  private static final int DESCRIPTION = 2;

  /**
   * ..
   */
  private static final int STATION = 3;

  /**
   * ..
   */
  private static final int OBJECT = 4;

  /**
   * ..
   */
  private static final int ID = 5;

  /**
   * ..
   */
  String[] COLUMN_NAMES = new String[NBR_OF_COLUMNS];

  /**
   *
   */
  public JTableMouseEvtsTests() {
    this.COLUMN_NAMES[SEVERITY] = "Severity";
    this.COLUMN_NAMES[DATE] = "Date";
    this.COLUMN_NAMES[DESCRIPTION] = "Description";
    this.COLUMN_NAMES[STATION] = "Station";
    this.COLUMN_NAMES[OBJECT] = "Object";
    this.COLUMN_NAMES[ID] = "ID";

    Object[][] data = {
      {
        "1", "20050210", "Description big size text ... ", "Viso", "Equip 1", "2456"}, {
        "1", "20050310", "Descr ... ", "SH", "Equip 2", "3434"}, {
        "1", "20050322", "Descri ... ", "Trin", "Equip 3", "2432"}, {
        "1", "20040207", "Descrip ... ", "Boa", "Equip 4", "455466"}, {
        "1", "20030517", "Descript ... ", "Mat", "Equip 5", "6754"}, {
        "1", "20050122", "Descripti ... ", "PR", "Equip 6", "32445"}, {
        "1", "20050110", "Descriptio ... ", "Cam", "Equip 7", "657"}
    };

    final JTable jTable = new JTable(data, this.COLUMN_NAMES);
    jTable.getTableHeader().setPreferredSize(new Dimension(550, 50));
    final JScrollPane sp = new JScrollPane();
    sp.setBackground(Color.GREEN);
    sp.getViewport().setBackground(Color.ORANGE);
    sp.setViewportView(jTable);

    // trying to set a tooltip whenever the text is not all visible ...
    final TableCellRenderer alarmStackTableCellRenderer = new DefaultTableCellRenderer() {
      private Component comp;
      private JLabel jLabel;
      private Dimension dimBounds;
      private Rectangle rectTableCell;

      @Override
      public Component getTableCellRendererComponent(
        final JTable jTable,
        final Object objValue,
        final boolean isSelected,
        final boolean hasFocus,
        final int iRow,
        final int iColumn) {

        comp = super.getTableCellRendererComponent(jTable, objValue, isSelected, hasFocus, iRow, iColumn);
        jLabel = (JLabel) comp;
        dimBounds = jLabel.getPreferredSize();
        rectTableCell = jTable.getCellRect(iRow, iColumn, true);

        if (rectTableCell.width < dimBounds.width /*|| rectTableCell.height < dimBounds.height*/) {
          jLabel.setToolTipText(jLabel.getText());
        } else {
          jLabel.setToolTipText(null);
        }

        return jLabel;
      }
    };

    final int nTableColumns = jTable.getColumnCount();
    for (int i = 0; i < nTableColumns; i++) {
      jTable.getTableHeader().getColumnModel().getColumn(i).setCellRenderer(
        alarmStackTableCellRenderer);
    }

    setLayout(new BorderLayout());

    add(sp);
    //add(jTable);

    //jTable.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
    jTable.setBackground(Color.BLUE);

    //jTable.setToolTipText("tooltip text");
    new Thread(new Runnable() {
      public void run() {
        System.out.println("Sleeping ...");
        try {
          Thread.sleep(3000);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        System.out.println("... waking up!");

        final TableModel model = jTable.getModel();

        if (SwingUtilities.isEventDispatchThread()) {
          jTable.setRowSelectionInterval(model.getRowCount() - 1,
            model.getRowCount() - 1);
        } else {
          SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              jTable.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
            }
          });
        }

      }
    }).start();

  }

  public static void main(String[] args) {

    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (ClassNotFoundException | InstantiationException
      | IllegalAccessException | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

    JTableMouseEvtsTests jt = new JTableMouseEvtsTests();
    JFrame f = new JFrame("NetVideoRecRecordings");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(jt, BorderLayout.CENTER);
    f.setBounds(100, 100, 600, 500);
    //f.pack();
    f.setVisible(true);
  }
}
