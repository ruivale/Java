/**
 * <p>
 * Classname: exp.swing.jtables.CellPerfectWidth
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package exp.swing.jtables;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Oct 1, 2015, 11:08:29 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class CellPerfectWidth extends JFrame {

  Object[] columnNames = {"First Name", "MI", "Last Name"};

  Object[][] names = {
    {"Lynn", "L", "Seckinger"},
    {"Carol", "R.", "Seckinger"},
    {"Roy", "D.", "Martin"},
    {"Bill", "O.", "Veryveryveryverylonglastname"},
    {"Richard", "A.", "Tattersall"},
    {"Philip", "B.", "Edwards"},
    {"Moore", "T.", "Moore"},
    {"Lynn", "M.", "Seckinger"},
    {"Carol", "R.", "Seckinger"},
    {"Roy", "D.", "Martin"},
    {"Bill", "O.", "Veryveryveryverylonglastname"},
    {"Richard", "A.", "Tattersall"},
    {"Philip", "B.", "Edwards"},
    {"Moore", "T.", "Moore"}
  };
  JTable table = new JTable(names, columnNames);

  /**
   *
   */
  public CellPerfectWidth() {

    final int nTableColumns = table.getColumnModel().getColumnCount();

    for (int i = 0; i < nTableColumns; i++) {
      final TableColumn tableColumn = table.getColumnModel().getColumn(i);
      tableColumn.setHeaderRenderer(new DefaultTableCellRenderer());
      final int iColumnWidth = getPreferredWidthForColumn(tableColumn);
      tableColumn.setMinWidth(iColumnWidth);

      if (i != 0) {
        tableColumn.setPreferredWidth(iColumnWidth);
        //tableColumn.setMaxWidth(iColumnWidth);
      }
    }

    // sizeColumnsToFit() must be called due to a JTable  bug ...
    table.sizeColumnsToFit(0);

    getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
  }

  /**
   *
   * @param col
   * <p>
   * @return
   */
  public int getPreferredWidthForColumn(TableColumn col) {
    int hw = columnHeaderWidth(col), // hw = header width
        cw = widestCellInColumn(col);  // cw = column width

    return hw > cw ? hw : cw;
  }

  /**
   *
   * @param col
   * <p>
   * @return
   */
  private int columnHeaderWidth(TableColumn col) {
    TableCellRenderer renderer = col.getHeaderRenderer();

    Component comp = renderer.getTableCellRendererComponent(
        table, col.getHeaderValue(),
        false, false, 0, 0);

    return comp.getPreferredSize().width;
  }

  /**
   *
   * @param col
   * <p>
   * @return
   */
  private int widestCellInColumn(TableColumn col) {
    int c = col.getModelIndex(), width = 0, maxw = 0;

    for (int r = 0; r < table.getRowCount(); ++r) {
      TableCellRenderer renderer = table.getCellRenderer(r, c);

      Component comp = renderer.getTableCellRendererComponent(
          table, table.getValueAt(r, c),
          false, false, r, c);

      width = comp.getPreferredSize().width;
      maxw = width > maxw ? width : maxw;
    }
    return maxw;
  }

  public static void main(String args[]) {
    GJApp.launch(
        new CellPerfectWidth(), "Setting Column Widths", 300, 300, 320, 140);
  }

}


class GJApp extends WindowAdapter {

  static private JPanel statusArea = new JPanel();
  static private JLabel status = new JLabel(" ");
  static private ResourceBundle resources;

  public static void launch(final JFrame f,
                            String title,
                            final int x,
                            final int y,
                            final int w,
                            int h) {
    launch(f, title, x, y, w, h, null);
  }

  public static void launch(final JFrame f,
                            String title,
                            final int x,
                            final int y,
                            final int w,
                            int h,
                            String propertiesFilename) {
    f.setTitle(title);
    f.setBounds(x, y, w, h);
    f.setVisible(true);

    statusArea.setBorder(BorderFactory.createEtchedBorder());
    statusArea.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    statusArea.add(status);
    status.setHorizontalAlignment(JLabel.LEFT);

    f.setDefaultCloseOperation(
        WindowConstants.DISPOSE_ON_CLOSE);

    if (propertiesFilename != null) {
      resources = ResourceBundle.getBundle(
          propertiesFilename, Locale.getDefault());
    }

    f.addWindowListener(new WindowAdapter() {
      public void windowClosed(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  static public JPanel getStatusArea() {
    return statusArea;
  }

  static public void showStatus(String s) {
    status.setText(s);
  }

  static Object getResource(String key) {
    if (resources != null) {
      return resources.getString(key);
    }
    return null;
  }
}
