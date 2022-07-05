package exp.tables;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.event.*;


public class Test2 extends JPanel {
  private int rows = 2, cols = 2;
  private Object[] rowData = new Object[cols];

  private DefaultTableModel model = new DefaultTableModel();
  private JTable table = new JTable(model);




  /**
   *
   */
  public Test2() {

    for (int c = 0; c < cols; ++c)
      model.addColumn("Column " + Integer.toString(c));
    /*
        for (int r = 0; r < rows; ++r) {
          for (int c = 0; c < cols; ++c) {
            rowData[c] = "(" + r + "," + c + ")";
          }
          model.addRow(rowData);
        }
    */

    rowData[0] = "b";
    rowData[1] = "a";
    model.addRow(rowData);
    rowData[0] = "a";
    rowData[1] = "b";
    model.addRow(rowData);


    add(new JScrollPane(table),BorderLayout.CENTER);

    final SortDecorator2 decorator = new SortDecorator2(table.getModel());

//    table.setModel(decorator);


    JTableHeader hdr = (JTableHeader)table.getTableHeader();
    hdr.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          TableColumnModel tcm = table.getColumnModel();
          int vc = tcm.getColumnIndexAtX(e.getX());
          int mc = table.convertColumnIndexToModel(vc);

System.out.println("o vc: " + vc + ", o mc: " + mc + ".");

          decorator.sort(mc);
          //table.repaint();
        }


      }
    );
  }


  public void addEvent() {
    int rowCount = model.getRowCount();
    int colCount = model.getColumnCount();

    if (colCount > rowData.length)
      rowData = new Object[colCount];

    for (int c = 0; c < colCount; ++c) {
      rowData[c] = "(" + rowCount + "," + c + ")";
    }
    model.removeRow(0);
    model.addRow(rowData);

  }



  public static void main(String args[]) {
    JFrame f = new JFrame();
    f.getContentPane().add(new Test2());
    f.setBounds(200,200,500,300);
    f.setVisible(true);
  }


}



class SortDecorator2 implements TableModel, TableModelListener {
  private TableModel realModel;
  private int indexes[];

  public SortDecorator2(TableModel model) {
    if (model == null)
      throw new IllegalArgumentException(
        "null models are not allowed");
    this.realModel = model;

    realModel.addTableModelListener(this);
    allocate();
  }
  public Object getValueAt(int row, int column) {
    return realModel.getValueAt(indexes[row], column);
  }
  public void setValueAt(Object aValue, int row, int column) {
    realModel.setValueAt(aValue, indexes[row], column);
  }
  public void tableChanged(TableModelEvent e) {
    allocate();
  }
  public void sort(int column) {

    int rowCount = getRowCount();

    System.out.println("o rowCount e´: " + rowCount);

    for (int i = 0; i < rowCount; i++) {
      for (int j = i + 1; j < rowCount; j++) {
        if (compare(indexes[i], indexes[j], column) < 0) {
          swap(i, j);

          System.out.println("just swap it");

        }
      }
    }
  }
  public void swap(int i, int j) {
    int tmp = indexes[i];
    indexes[i] = indexes[j];
    indexes[j] = tmp;
  }
  public int compare(int i, int j, int column) {
    Object io = realModel.getValueAt(i, column);
    Object jo = realModel.getValueAt(j, column);

    int c = jo.toString().compareTo(io.toString());
    return (c < 0) ? -1 : ((c > 0) ? 1 : 0);
  }
  private void allocate() {
    indexes = new int[getRowCount()];

    for (int i = 0; i < indexes.length; ++i) {
      indexes[i] = i;
    }
  }

  // TableModel pass-through methods follow ...

  public int getRowCount() {
    return realModel.getRowCount();
  }
  public int getColumnCount() {
    return realModel.getColumnCount();
  }
  public String getColumnName(int columnIndex) {
    return realModel.getColumnName(columnIndex);
  }
  public Class getColumnClass(int columnIndex) {
    return realModel.getColumnClass(columnIndex);
  }
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return realModel.isCellEditable(rowIndex, columnIndex);
  }
  public void addTableModelListener(TableModelListener l) {
    realModel.addTableModelListener(l);
  }
  public void removeTableModelListener(TableModelListener l) {
    realModel.removeTableModelListener(l);
  }
}

/*
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

    f.addWindowListener(
      new WindowAdapter() {
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
*/


/*
  class ControlPanel2 extends JPanel {
    private JButton rowButton = new JButton("Add Row"),
                                colButton = new JButton("Add Column");

    public ControlPanel2() {
      add(rowButton);
      add(colButton);

      rowButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            int rowCount = model.getRowCount();
            int colCount = model.getColumnCount();

            if (colCount > rowData.length)
              rowData = new Object[colCount];

            for (int c = 0; c < colCount; ++c) {
              rowData[c] = "(" + rowCount + "," + c + ")";
            }
            model.removeRow(0);
            model.addRow(rowData);
          }
        }
      );
      colButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            int colCount = model.getColumnCount();
            model.addColumn("Column " + colCount);

            // Bug: the call to sizeColumnsToFit()
            // should not be necessary
            table.sizeColumnsToFit( -1);
          }
        }
      );
    }
  }

*/
