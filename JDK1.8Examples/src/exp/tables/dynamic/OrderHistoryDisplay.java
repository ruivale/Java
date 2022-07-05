package exp.tables.dynamic;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class OrderHistoryDisplay extends JPanel {
  //~ Instance fields ----------------------------------------------------------

  private JLabel      lblHistory;
  private JScrollPane scrlHistory;
  private JTable      tblHistory;

  //~ Constructors -------------------------------------------------------------

  /**
   * Creates a new OrderHistoryDisplay object.
   */
  public OrderHistoryDisplay () {
    HistoryTableModel hstModel = new HistoryTableModel();
    setLayout(new BorderLayout());
    tblHistory = new JTable(hstModel);
    tblHistory.setPreferredScrollableViewportSize(new Dimension(
        500,
        70));

    TableColumn column = null;

    for(int i = 0; i < 3; i++) {
      column = tblHistory.getColumnModel()
                         .getColumn(i);

      if(i == 2) {
        column.setPreferredWidth(30);
      } else {
        column.setPreferredWidth(5);
      }
    }

    scrlHistory   = new JScrollPane(tblHistory);
    lblHistory    = new JLabel("Order Update History");

    add(
      lblHistory,
      BorderLayout.NORTH);
    add(
      scrlHistory,
      BorderLayout.SOUTH);
  }

  //~ Methods ------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param inHistory DOCUMENT ME!
   */
/*
  public void showHistory (OrderHistory[] inHistory) {
    HistoryTableModel hstModel = new HistoryTableModel(inHistory);
    tblHistory.setModel(hstModel);
  }
*/
  //~ Inner Classes ------------------------------------------------------------

  private class HistoryTableModel extends AbstractTableModel {
    //~ Instance fields --------------------------------------------------------

    final int          TABLE_LENGTH = 8;
    private String[]   columnNames  = { "Date", "Status", "Comments" };
    private Object[][] datHistTable;

    //~ Constructors -----------------------------------------------------------

//Constructor for an empty table
    public HistoryTableModel () {
      datHistTable = new Object[TABLE_LENGTH][columnNames.length];

      for(int i = 0; i < columnNames.length; i++) {
        for(int j = 0; j < TABLE_LENGTH; j++) {
          //datHistTable[j] = "";
        }
      }
    }

//Constructor for a table with entries
/*
    public HistoryTableModel (OrderHistory[] inHistory) {
      datHistTable   = null;
      datHistTable   = new Object[inHistory.length][columnNames.length];

      for(int row = 0; row < inHistory.length; row++) {
        datHistTable[row][0]   = inHistory[row].getDateUpdated();
        datHistTable[row][1]   = inHistory[row].getStatus();
        datHistTable[row][2]   = inHistory[row].getComments();
      }
    }
*/
    //~ Methods ----------------------------------------------------------------

    public int getColumnCount () {
      return columnNames.length;
    }

    public String getColumnName (int inCol) {
      return columnNames[inCol];
    }

    public int getRowCount () {
      return datHistTable.length;
    }

    public void setValueAt (
      Object inObject,
      int    row,
      int    col) {
      datHistTable[row][col] = inObject;
      fireTableCellUpdated(
        row,
        col);
    }

    public Object getValueAt (
      int inRow,
      int inCol) {
      return datHistTable[inRow][inCol];
    }
  }


  public static void main(String a[]){
    JFrame                  f = new JFrame();
    f.setTitle("-----------");
    f.getContentPane()
     .setLayout(new BorderLayout());
    f.getContentPane()
     .add(new OrderHistoryDisplay(), BorderLayout.CENTER);
    f.setBounds(100, 100, 200, 70);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }

}
