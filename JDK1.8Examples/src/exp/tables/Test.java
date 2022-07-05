package exp.tables;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class Test extends JFrame implements Runnable{

  private Object[][] rowData = new Object[][] {
                                 {"apple", "12/10/2001", "aa"},
                                 {"mango", "12/10/2001", "aa"},
                                 {"papaya", "12/10/2001", "aa"},
                                 {"lemon", "12/10/2001", "aa"},
                                 {"orange", "12/10/2001", "aa"},
                                 {"watermelon", "12/10/2001", "aa"},
                                 {"tangerine", "12/10/2001", "aa"},
                                 {"cherry", "12/10/2001", "aa"},
                                 {"banana", "15/10/2001", "aa"},
                                 {"lime", "2/10/2001", "aa"},
                                 {"grapefruit", "1/10/2001", "aa"},
                                 {"grapes", "10/10/2001", "aa"},
                               };

  JTable table = new JTable(rowData,new Object[] { "Item", "Price/Lb.", "aa" });

  JTableHeader hdr;


  //  DefaultTableModel model;// = new DefaultTableModel();



  public Test() {
    final SortDecorator decorator = new SortDecorator(table.getModel());

    table.setModel(decorator);

    //    model = table.getModel();

    hdr = (JTableHeader)table.getTableHeader();




    System.out.println("hdr.getHeaderRect(0): " +
                       hdr.getHeaderRect(0).width + ".");




    hdr.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          TableColumnModel tcm = table.getColumnModel();
          int vc = tcm.getColumnIndexAtX(e.getX());
          int mc = table.convertColumnIndexToModel(vc);

          decorator.sort(mc);
          //table.repaint();
        }
      }
    );

    JButton rowButton = new JButton("Add Row");
/*
    rowButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {

          TableModel model = (TableModel)table.getModel();

          Object[] rowData = null;
          int rowCount = model.getRowCount();
          int colCount = model.getColumnCount();

          if (colCount > rowData.length)
            rowData = new Object[colCount];

          for (int c = 0; c < colCount; ++c) {
            rowData[c] = "(" + rowCount + "," + c + ")";
          }
          //            model.addRow(rowData);
        }

      }
    );
*/



    getContentPane().add(rowButton, BorderLayout.NORTH);
    getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

    new Thread(this, "running").start();


  }


  public void run(){
    if (Thread.currentThread().getName().equals("running")) {
      table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


      table.getColumn("aa").setMaxWidth(25);
      table.getColumn("aa").setMinWidth(25);
      table.getColumn("aa").setPreferredWidth(25);
      table.getColumn("aa").setWidth(25);


      JLabel labelHeaderItem = new JLabel("Item");
      int labelWidth = labelHeaderItem.getPreferredSize().width;

System.out.println("labelWidth: "+labelWidth+".");

    /*
        table.getColumn("Item").setMaxWidth(labelWidth);
        table.getColumn("Item").setMinWidth(labelWidth);
        table.getColumn("Item").setPreferredWidth(labelWidth);
        table.getColumn("Item").setWidth(labelWidth);
    */

    }
  }



  public static void main(String args[]) {
    GJApp.launch(
      new Test(), "A Sort Decorator", 300, 300, 450, 250);
  }

  class SortDecorator implements TableModel, TableModelListener {
    private TableModel realModel;
    private int indexes[];

    public SortDecorator(TableModel model) {
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


    System.out.println("hdr.getHeaderRect(0): " +hdr.getHeaderRect(0).width + ".");



      int rowCount = getRowCount();

      for (int i = 0; i < rowCount; i++) {
        for (int j = i + 1; j < rowCount; j++) {
          if (compare(indexes[i], indexes[j], column) < 0) {
            swap(i, j);
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
}
class GJApp extends WindowAdapter {
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
