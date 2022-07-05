package exp.swing.jtables;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import exp.swing.jtables.sort.*;


/**
 * <p>
 * Title: </p>
 * <p>
 * <p>
 * Description: </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) </p>
 * <p>
 * <p>
 * Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SortTable
  extends JPanel {

  /** .. */
  private static final int NBR_OF_COLUMNS = 6;

  /** .. */
  private static final int SEVERITY = 0;

  /** .. */
  private static final int DATE = 1;

  /** .. */
  private static final int DESCRIPTION = 2;

  /** .. */
  private static final int STATION = 3;

  /** .. */
  private static final int OBJECT = 4;

  /** .. */
  protected static final int ID = 5;

  /** .. */
  protected static final String[] COLUMN_NAMES = new String[NBR_OF_COLUMNS];

  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable = null;

  public SortTable() {
    Object[][] data = {
      {
        "1", "20050210", "Desc ... ", "Viso", "Equip 1", "5.13:24:56"}, {
        "1", "20050310", "Descr ... ", "SH", "Equip 2", "12:34:34"}, {
        "1", "20050322", "Descri ... ", "Trin", "Equip 3", "24:32"}, {
        "1", "20040207", "Descrip ... ", "Boa", "Equip 4", "6.11:45:54"}, {
        "1", "20030517", "Descript ... ", "Mat", "Equip 5", "12.23:06:07"}, {
        "1", "20050122", "Descripti ... ", "PR", "Equip 6", "3:24:45"}, {
        "1", "20050110", "Descriptio ... ", "Cam", "Equip 7", "57"}
    };
    buildTable(data);
    jbInit();
  }

  /**
   *
   * @param objData
   */
  private void buildTable(final Object[][] objData) {
    this.COLUMN_NAMES[SEVERITY] = "Severity";
    this.COLUMN_NAMES[DATE] = "Date";
    this.COLUMN_NAMES[DESCRIPTION] = "Description";
    this.COLUMN_NAMES[STATION] = "Station";
    this.COLUMN_NAMES[OBJECT] = "Object";
    this.COLUMN_NAMES[ID] = "Duration";

    MyTableModel myTableModel = new MyTableModel(objData, COLUMN_NAMES);
    TableSorter jTableSorter = new TableSorter(myTableModel);
    jTableSorter.sortByColumn(1);
    jTable = new JTable(jTableSorter);
    jTableSorter.addMouseListenerToHeaderInTable(jTable);

    for (int i = 0; i < NBR_OF_COLUMNS; i++) {
      try {
        TableColumn column = jTable.getColumn(COLUMN_NAMES[i]);
        column.setHeaderRenderer(new SortedHeaderRenderer());
        column.setCellRenderer(new RowRenderer(this.SEVERITY));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    jTable.getTableHeader().setReorderingAllowed(false);

    jTable.addMouseListener(new CustomTableMouseAdapter(jTable));
  }

  /**
   *
   */
  private void updateData(){
      final DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
      final int nRows = tableModel.getRowCount();

      for (int i = nRows - 1; i > -1; i--) {
        tableModel.removeRow(i);
      }

      Object[][] objData = {
      {
        "1", "20050210", "Desc ... ", "Viso", "Equip 1", "5.13:24:56"}, {
        "1", "20050310", "Descr ... ", "SH", "Equip 2", "12:34:34"}, {
        "1", "20050322", "Descri ... ", "Trin", "Equip 3", "24:32"}, {
        "1", "20040207", "Descrip ... ", "Boa", "Equip 4", "6.11:45:54"}, {
        "1", "20030517", "Descript ... ", "Mat", "Equip 5", "12.23:06:07"}, {
        "1", "20050122", "Descripti ... ", "PR", "Equip 6", "3:24:45"}, {
        "1", "20050110", "Descriptio ... ", "Cam", "Equip 7", "57"}
    };

      final int nItems2Add = objData != null ? objData.length : 0;

      for (int i = 0; i < nItems2Add; i++) {
        tableModel.addRow(objData[i]);
      }

  }




  public static void main(String[] args) {

//    try {
//      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//    } catch (ClassNotFoundException | InstantiationException
//      | IllegalAccessException | UnsupportedLookAndFeelException e) {
//      e.printStackTrace();
//    }


    SortTable s = new SortTable();
    JFrame f = new JFrame("NetVideoRecRecordings");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(s, BorderLayout.CENTER);
    f.setBounds(100, 100, 600, 500);
    //f.pack();
    f.setVisible(true);
    
    final Thread thread = new Thread(() -> {
      while (true) {        
        try {
          s.jTable.setBackground(Color.WHITE);
          s.jTable.setForeground(Color.RED);
          System.out.println("Table BG:"+s.jTable.getBackground().getRGB()+" FG:"+s.jTable.getForeground().getRGB()+".");
          System.out.println("UIDef BG:"+UIManager.getColor("Table.background").getRGB()+" FG:"+UIManager.getColor("Table.foreground").getRGB()+".");

          Thread.sleep(5000);
                    
          System.out.println("Updating data...");
          //s.updateData();
          
          Thread.sleep(1000);
          
          s.jTable.setBackground(Color.RED);
          s.jTable.setForeground(Color.WHITE);
          System.out.println("Table BG:"+s.jTable.getBackground().getRGB()+" FG:"+s.jTable.getForeground().getRGB()+".");
          System.out.println("UIDef BG:"+UIManager.getColor("Table.background").getRGB()+" FG:"+UIManager.getColor("Table.foreground").getRGB()+".");
          
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "ThreadName");
    thread.setDaemon(true);
    thread.start();

  }

  private void jbInit() {
    this.setLayout(borderLayout1);
    this.add(jScrollPane1, java.awt.BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTable);
    jTable.addMouseListener(new CustomTableMouseAdapter(jTable));
  }

}


/**
 * <p>
 * Title: </p>
 * <p>
 * <p>
 * Description: </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2004</p>
 * <p>
 * <p>
 * Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
class CustomTableMouseAdapter extends java.awt.event.MouseAdapter {

  private final JTable table;

  CustomTableMouseAdapter(final JTable table) {
    this.table = table;
  }

  public void mouseClicked(final MouseEvent e) {

    System.out.println("Clicked in the table");

    int selected = table.getSelectedRow();
    Object[] o = new Object[6];
    if (selected > -1 && selected < table.getModel().getRowCount()) {
      for (int i = 0; i < 6; i++) {
        o[i] = table.getModel().getValueAt(selected, i);
      }
      System.out.println("#########################################");
      System.out.println("[" + o[0] + "," + o[1] + "," + o[2] + "," + o[3] + "," + o[4] + "," + o[5]
                         + "]");
      System.out.println("#########################################");
    }

    if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
    } else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
    }
  }
}


class CustomTableModel extends DefaultTableModel {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new CustomModel object.
   *
   * @param data
   * @param columnNames
   */
  public CustomTableModel(
    final Object[][] data,
    final Object[] columnNames) {
    super(data, columnNames);
  }

  /**
   * Creates a new CustomModel object.
   *
   * @param columnNames
   */
  public CustomTableModel(final Object[] columnNames) {
    super(columnNames, 0);
  }

  //~ Methods //////////////////////////////////////////////////////////////////
  /**
   * DOCUMENT ME!
   *
   * @param row
   * @param col
   *
   * @return
   */
  public boolean isCellEditable(final int row, final int col) {
    return false;
  }

  /**
   * DOCUMENT ME!
   *
   * @param col
   *
   * @return
   */
  public Class getColumnClass(final int col) {
    // dataVector is a protected member of DefaultTableModel
    final Vector v = (Vector) dataVector.elementAt(0);

    return v.elementAt(col)
      .getClass();
  }
}


class RowRenderer
  extends DefaultTableCellRenderer {

  private static final Color ligthOrange = new Color(251, 200, 0);
  private static final Color darkOrange = new Color(251, 150, 0);
  private int intSeverityColumn = 1;

  public RowRenderer(final int intSeverityColumn) {
    this.intSeverityColumn = intSeverityColumn;
  }

  public Component getTableCellRendererComponent(
    final JTable table,
    final Object value,
    final boolean isSelected,
    final boolean hasFocus,
    final int row,
    final int column) {

    return super.getTableCellRendererComponent(table, value, isSelected,
      hasFocus, row, column);
  }
}


class MyTableModel
  extends DefaultTableModel {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private String[] COLUMN_NAMES = SortTable.COLUMN_NAMES;

  /** .. */
  private final Object[][] objData;

  /** .. */
  private TableSorter tableSorter;

  //~ Constructors /////////////////////////////////////////////////////////////
  /**
   * DOCUMENT ME!
   *
   * @param objData      Object[][]
   * @param COLUMN_NAMES String[]
   */
  MyTableModel(final Object[][] objData, final String[] COLUMN_NAMES) {
    super(objData, COLUMN_NAMES);
    this.objData = objData;
    this.COLUMN_NAMES = COLUMN_NAMES;
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /*
   * JTable uses this method to determine the default renderer/
   * editor for each cell.  If we didn't implement this method,
   * then the last column would contain text ("true"/"false"),
   * rather than a check box.
   */
  public Class getColumnClass(final int iColumnIdx) {
    // dataVector is a protected member of DefaultTableModel
    final Vector vector = (Vector) dataVector.elementAt(0);

    return
      iColumnIdx != SortTable.ID?
        vector.elementAt(iColumnIdx).getClass():
        Integer.class;

  }

  /**
   * Insert doc ...
   *
   * @param col Insert doc ...
   *
   * @return Insert doc ...
   */
  public String getColumnName(final int col) {

    String strName = this.COLUMN_NAMES[col];

    if (this.tableSorter != null) {
      final int intLastSelectedColumn = this.tableSorter.getLastSortedColumn();

      if (col == intLastSelectedColumn) {
        strName = new StringBuffer(strName).append(" .")
          .toString();
      }
    }

    return strName;
  }

  /*
   * Don't need to implement this method unless your jTable's
   * objData can change.
   */
  public void setValueAt(
    final Object value,
    final int row,
    int col) {
    if (this.objData[0][col] instanceof Integer && !(value instanceof Integer)) {
      //With JFC/Swing 1.1 and JDK 1.2, we need to create
      //an Integer from the value; otherwise, the column
      //switches to contain Strings.  Starting with v 1.3,
      //the jTable automatically converts value to an Integer,
      //so you only need the code in the 'else' part of this
      //'if' block.
      try {
        this.objData[row][col] = new Integer(value.toString());
        this.fireTableCellUpdated(row, col);
      } catch (NumberFormatException e) {
        ;
      }
    } else {
      this.objData[row][col] = value;
      this.fireTableCellUpdated(row, col);
    }
  }

  /**
   * Insert doc ...
   *
   * @param row Insert doc ...
   * @param col Insert doc ...
   *
   * @return Insert doc ...
   */
  public Object getValueAt(
    final int row,
    final int col) {
    return this.objData[row][col];
  }

  /**
   * Insert doc ...
   *
   * @return Insert doc ...
   */
  public int _getColumnCount() {
    return this.COLUMN_NAMES.length;
  }

  /**
   * Insert doc ...
   *
   * @return Insert doc ...
   */
  public int _getRowCount() {
    return this.objData.length;
  }

  /**
   * DOCUMENT ME!
   *
   * @param tableSorter TableSorter
   */
  protected void setTableSorter(final TableSorter tableSorter) {
    if (this.tableSorter == null) {
      this.tableSorter = tableSorter;
    }
  }
}
