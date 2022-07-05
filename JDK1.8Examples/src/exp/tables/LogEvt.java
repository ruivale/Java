package exp.tables;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class LogEvt extends JPanel {

  private int rows = 2;
  private int cols = 4;
  private Object[] rowData = new Object[cols];
/*
  private JTable table ;
  private JTableHeader tableHeader;
  private CustomModel model;
*/
  private DefaultTableModel model = new DefaultTableModel();
  private JTable table = new JTable(model);
  private JTableHeader tableHeader = (JTableHeader)table.getTableHeader();




  /**
   *
   */
  public LogEvt() {

    //
    // %AQUI A INFO DO HEADER DA TABLE VAI SER PEQUISADA NOS PROPERTY FILES
    //
    model.addColumn("Symbol");
    model.addColumn("id");
    model.addColumn("Description");
    model.addColumn("GI");

    add(new JScrollPane(table), BorderLayout.CENTER);

    Object[] obj = {"2001/12/14 14:30:49", "bbbb", "aaa", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")};
    addEvent(obj);
    Object[] obj2 = {"2001/12/14 14:30:42", "fg", "5", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")};
    addEvent(obj2);
    Object[] obj3 = {"2001/12/14 14:30:43", "hu", "45", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")};
    addEvent(obj3);

    final SortRows sortRows = new SortRows(table.getModel());
    table.setModel(sortRows);

    table.setShowGrid(false);

    tableHeader.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          TableColumnModel tcm = table.getColumnModel();
          int vc = tcm.getColumnIndexAtX(e.getX());
          int mc = table.convertColumnIndexToModel(vc);
          //
          // %In stand by
          //
          sortRows.sort(mc);

        }

      }
    );

    //    tableHeader.setReorderingAllowed(false);

    table.getSelectionModel().setSelectionMode(
      ListSelectionModel.SINGLE_SELECTION);



    table.addMouseListener(
      new MouseAdapter() {
        // Variable that is used to calculate the "DOUBLE CLICK" event of the
        // left mouse button click.
        long previousTime = System.currentTimeMillis();
        long time;
        long delay;

        public void mouseClicked(MouseEvent e) {

          System.out.println("clickei na table");

          if (e.getModifiers() == InputEvent.BUTTON1_MASK) {

            time = System.currentTimeMillis();
            //delay = ((float) (time - previousTime)) / 1000;
            delay = time - previousTime;
            previousTime = time;

            // I believe this a good delay to simulate the "DOUBLE CLICK" event
            if (delay < 500) {

              int row = table.getSelectedRow();

              Enumeration tableHeaderData = tableHeader.getColumnModel().getColumns();
              Vector tableData = model.getDataVector();
              String selectionData = "" + tableData.get(row);

              int i = 0;
              while (tableHeaderData.hasMoreElements()) {
                TableColumn tableColumn = (TableColumn)tableHeaderData.nextElement();

                // This will tell us where to find the "id" column
                //
                // %isto pode ser parametrizado para escolher o cabeçalho a retornar
                //
                if (tableColumn.getHeaderValue().equals("id")) {

                  // Getting info about the selection made


                  break;
                }
                i++;
              }//while
            }//if(delay<500)
          }//InputEvent.BUTTON1_MASK
        }//MouseClicked
      }

    );


    /*

    PARA DEITAR FORA
    */

    JButton b = new JButton("add row");
/*    b.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {


          Object[] obj = {"2001/12/14 14:30:49", "icon", "icon",
                          new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")};
          addEvent(obj);
          Object[] obj2 = {"2001/12/14 14:30:42", "fg", "5", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")};
          addEvent(obj2);
          Object[] obj3 = {"2001/12/14 14:30:43", "hu", "45", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")};
          addEvent(obj3);
        }
      }
    );
*/
    add(b, BorderLayout.NORTH);


  }

  /**
   *
   * return 0:      OK
   * return 1:      ERROR
   */
  public int addEvent(Object[] rowData) {
    int rowCount = model.getRowCount();
    int colCount = model.getColumnCount();

    if (colCount > rowData.length) {
      return 1;
    }

    if (rowCount > 19) {
      model.removeRow(19);
    }
    model.insertRow(0, rowData);

    return 0;

  }



  public static void main(String args[]) {
    JFrame f = new JFrame();
    f.getContentPane().add(new LogEvt(2));
    f.setBounds(200, 200, 600, 400);
    f.setVisible(true);
  }


  public LogEvt(int i) {

System.out.println("estou no  LogEventsComponent(int i)");


    Object[][] obj = {{"2001/12/14 14:30:49", "bbbb", "aaa", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")},
      {"2001/12/14 14:30:42", "fg", "5", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")},
      {"2001/12/14 14:30:43", "hu", "45", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")}};
    String[] columnNames = {"Name", "Check-In Date", "Check-Out Date", "id"};

    model = new CustomModel1(obj,columnNames);
    table = new JTable(model);

    add(new JScrollPane(table), BorderLayout.CENTER);

    table.setShowGrid(false);

    tableHeader = (JTableHeader)table.getTableHeader();
    tableHeader.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          TableColumnModel tcm = table.getColumnModel();
          int vc = tcm.getColumnIndexAtX(e.getX());
          int mc = table.convertColumnIndexToModel(vc);
System.out.println("clickei no header");
        }

      }
    );

    //    tableHeader.setReorderingAllowed(false);

    table.getSelectionModel().setSelectionMode(
      ListSelectionModel.SINGLE_SELECTION);



    table.addMouseListener(
      new MouseAdapter() {
        // Variable that is used to calculate the "DOUBLE CLICK" event of the
        // left mouse button click.
        long previousTime = System.currentTimeMillis();
        long time;
        long delay;

        public void mouseClicked(MouseEvent e) {

          System.out.println("clickei na table");

          if (e.getModifiers() == InputEvent.BUTTON1_MASK) {

            time = System.currentTimeMillis();
            //delay = ((float) (time - previousTime)) / 1000;
            delay = time - previousTime;
            previousTime = time;

            // I believe this a good delay to simulate the "DOUBLE CLICK" event
            if (delay < 500) {

              int row = table.getSelectedRow();

              Enumeration tableHeaderData = tableHeader.getColumnModel().getColumns();
              Vector tableData = model.getDataVector();
              String selectionData = "" + tableData.get(row);

              int i = 0;
              while (tableHeaderData.hasMoreElements()) {
                TableColumn tableColumn = (TableColumn)tableHeaderData.nextElement();

                // This will tell us where to find the "id" column
                //
                // %isto pode ser parametrizado para escolher o cabeçalho a retornar
                //
                if (tableColumn.getHeaderValue().equals("id")) {

                  // Getting info about the selection made


                  break;
                }
                i++;
              }//while
            }//if(delay<500)
          }//InputEvent.BUTTON1_MASK
        }//MouseClicked
      }

    );


    /*

    PARA DEITAR FORA
    */

    JButton b = new JButton("add row");
/*
    b.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {


          Object[] obj = {"2001/12/14 14:30:49", "icon", "icon",
                          new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")};
          addEvent(obj);
          Object[] obj2 = {"2001/12/14 14:30:42", "fg", "5", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")};
          addEvent(obj2);
          Object[] obj3 = {"2001/12/14 14:30:43", "hu", "45", new ImageIcon("/JBProjects/PInt/images/icons/Open.gif")};
          addEvent(obj3);
        }
      }
    );
*/
    add(b, BorderLayout.NORTH);


  }

}


class SortRows implements TableModel, TableModelListener {
  private TableModel realModel;
  private int indexes[];
  private boolean sortUp = true;


  public SortRows(TableModel model) {
    if (model == null)
      throw new IllegalArgumentException(
        "cannot use null models");
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

    System.out.println("no sort");

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

    //
    // If we want to compare date or other format, here is the place to
    // compare it.
    //
    int c = jo.toString().compareTo(io.toString());
    //
    //
    //
    //    if(sortUp){
    //    sortUp = false;
    return (c < 0) ? -1 : ((c > 0) ? 1 : 0);
    //    }else{
    //    sortUp = true;
    //  return (c > 0) ? -1 : ((c > 0) ? 1 : 0);
    //}
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
    return false;
  }
  public void addTableModelListener(TableModelListener l) {
    realModel.addTableModelListener(l);
  }
  public void removeTableModelListener(TableModelListener l) {
    realModel.removeTableModelListener(l);
  }
}






















class CustomModel1 extends DefaultTableModel implements TableModel, TableModelListener {

  private TableModel realModel;
  private int indexes[];
  private boolean sortUp = true;


  public CustomModel1(Object[][] data, Object[] columnNames) {
    super(data, columnNames);


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

    System.out.println("no sort");

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

    //
    // If we want to compare date or other format, here is the place to
    // compare it.
    //
    int c = jo.toString().compareTo(io.toString());
    //
    //
    //
    //    if(sortUp){
    //    sortUp = false;
    return (c < 0) ? -1 : ((c > 0) ? 1 : 0);
    //    }else{
    //    sortUp = true;
    //  return (c > 0) ? -1 : ((c > 0) ? 1 : 0);
    //}
  }

  private void allocate() {
    indexes = new int[getRowCount()];

    for (int i = 0; i < indexes.length; ++i) {
      indexes[i] = i;
    }
  }


  public Class getColumnClass(int col) {
    // dataVector is a protected member of DefaultTableModel

    Vector v = (Vector)dataVector.elementAt(0);
    return v.elementAt(col).getClass();
  }
  public boolean isCellEditable(int row, int col) {
    return false;
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
  public void addTableModelListener(TableModelListener l) {
    realModel.addTableModelListener(l);
  }
  public void removeTableModelListener(TableModelListener l) {
    realModel.removeTableModelListener(l);
  }

}

