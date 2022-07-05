package exp.tables;



/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class ColumnsWidth extends JFrame {
  Object[] columnNames =
    {"First Name", "MI", "Last Name"};

  Object[][] names = {
    { "Lynn", "M.", "Seckinger" },
    { "Carol", "R.", "Seckinger" },
    { "Roy", "D.", "Martin" },
    { "Bill", "O.", "Veryveryveryverylonglastname" },
    { "Richard", "A.", "Tattersall" },
    { "Philip", "B.", "Edwards" },
    { "Moore", "T.", "Moore" },

    // shorten scrollbar grip with these ...
    { "Lynn", "M.", "Seckinger" },
    { "Carol", "R.", "Seckinger" },
    { "Roy", "D.", "Martin" },
    { "Bill", "O.", "Veryveryveryverylonglastname" },
    { "Richard", "A.", "Tattersall" },
    { "Philip", "B.", "Edwards" },
    { "Moore", "T.", "Moore" },
  };
  JTable table = new JTable(names, columnNames);




  public ColumnsWidth() {
    TableColumn mid = table.getColumn(columnNames[1]);
    TableColumn last = table.getColumn(columnNames[2]);

    int midWidth = getPreferredWidthForColumn(mid),
                   lastWidth = getPreferredWidthForColumn(last);

    mid.setMinWidth(midWidth);
    mid.setMaxWidth(midWidth);

    last.setMinWidth(lastWidth);

    // sizeColumnsToFit() must be called due to a JTable
    // bug ...
    table.sizeColumnsToFit(0);

    getContentPane().add(new JScrollPane(table),
                         BorderLayout.CENTER);
  }




  public int getPreferredWidthForColumn(TableColumn col) {
    int hw = columnHeaderWidth(col),    // hw = header width
             cw = widestCellInColumn(col);  // cw = column width

    return hw > cw ? hw : cw;
  }





  private int columnHeaderWidth(TableColumn col) {
    TableCellRenderer renderer = col.getHeaderRenderer();

    if (renderer == null)
      System.out.println("o renderer ´e null");

    Component comp = renderer.getTableCellRendererComponent(table,
                     col.getHeaderValue(), false, false, 0, 0);

    return comp.getPreferredSize().width;
  }




  private int widestCellInColumn(TableColumn col) {
    int c = col.getModelIndex(), width = 0, maxw = 0;

    for (int r = 0; r < table.getRowCount(); ++r) {
      TableCellRenderer renderer =
        table.getCellRenderer(r, c);

      Component comp =
        renderer.getTableCellRendererComponent(
          table, table.getValueAt(r, c),
          false, false, r, c);

      width = comp.getPreferredSize().width;
      maxw = width > maxw ? width : maxw;
    }
    return maxw;
  }



  public static void main(String args[]) {
    GJApp2.launch(
      new ColumnsWidth(), "Setting Column Widths", 300, 300, 320, 140);
  }
}




class GJApp2 extends WindowAdapter {
  static private JPanel statusArea = new JPanel();
  static private JLabel status = new JLabel(" ");
  static private ResourceBundle resources;

  public static void launch(final JFrame f, String title,
                            final int x, final int y,
                            final int w, int h) {
    launch(f, title, x, y, w, h, null);
  }
  public static void launch(final JFrame f, String title,
                            final int x, final int y,
                            final int w, int h,
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
                        }
                       );
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
