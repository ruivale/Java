// %1132847297718:exp.jdbc.jtable%
package exp.jdbc.jtable;


import java.sql.*;

import java.util.*;

import javax.swing.*;
import javax.swing.table.*;


/**
 * an immutable table model built from getting metadata about a table in a jdbc
 * database
 */
public class JDBCTableModel
    extends AbstractTableModel {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  Class[]    columnClasses;

  /** .. */
  String[]   columnNames;

  /** .. */
  Object[][] contents;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new JDBCTableModel object.
   *
   * @param conn  Insert doc ...
   * @param tableName  Insert doc ...
   *
   * @throws SQLException  Insert doc ...
   */
  public JDBCTableModel(
    Connection conn,
    String     tableName)
      throws SQLException {
    super();
    getTableContents(
      conn,
      tableName);
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  // overrides methods for which AbstractTableModel
  // has trivial implementations
  public Class getColumnClass(int col) {
    return columnClasses[col];
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public int getColumnCount() {
    if(contents.length==0) {
      return 0;
    } else {
      return contents[0].length;
    }
  }

  /**
   * Insert doc ...
   *
   * @param col  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public String getColumnName(int col) {
    return columnNames[col];
  }

// AbstractTableModel methods
  public int getRowCount() {
    return contents.length;
  }

  /**
   * Insert doc ...
   *
   * @param row  Insert doc ...
   * @param column  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public Object getValueAt(
    int row,
    int column) {
    return contents[row][column];
  }

  /**
   * Insert doc ...
   *
   * @param conn  Insert doc ...
   * @param tableName  Insert doc ...
   *
   * @throws SQLException  Insert doc ...
   */
  protected void getTableContents(
    Connection conn,
    String     tableName)
      throws SQLException {
    // get metadata: what columns exist and what
    // types (classes) are they?
    DatabaseMetaData meta = conn.getMetaData();
    System.out.println("got meta = " + meta.toString());

    ResultSet results = meta.getColumns(
        null,
        null,
        tableName,
        null);
    System.out.println("got column results");

    ArrayList colNamesList   = new ArrayList();
    ArrayList colClassesList = new ArrayList();

    System.out.println("results="+results.toString()+" row="+results.getRow()+".");

    while(true) {
      System.out.println("...");

      if(!results.next()){
        break;
      }

      colNamesList.add(results.getString("COLUMN_NAME"));
      System.out.println("name: " + results.getString("COLUMN_NAME"));

      int dbType = results.getInt("DATA_TYPE");

      switch(dbType) {
        case Types.INTEGER:
          colClassesList.add(Integer.class);

          break;

        case Types.FLOAT:
          colClassesList.add(Float.class);

          break;

        case Types.DOUBLE:
        case Types.REAL:
          colClassesList.add(Double.class);

          break;

        case Types.DATE:
        case Types.TIME:
        case Types.TIMESTAMP:
          colClassesList.add(java.sql.Date.class);

          break;

        default:
          colClassesList.add(String.class);

          break;
      }

      System.out.println("type: " + results.getInt("DATA_TYPE"));
    }

    columnNames = new String[colNamesList.size()];
    colNamesList.toArray(columnNames);
    columnClasses = new Class[colClassesList.size()];
    colClassesList.toArray(columnClasses);

    // get all data from table and put into
    // contents array
    Statement statement = conn.createStatement();
    results = statement.executeQuery("SELECT * FROM " + tableName);

    ArrayList rowList = new ArrayList();

    while(results.next()) {
      ArrayList cellList = new ArrayList();

      for(int i = 0; i<columnClasses.length; i++) {
        Object cellValue = null;

        if(columnClasses[i]==String.class) {
          cellValue = results.getString(columnNames[i]);
        } else {
          if(columnClasses[i]==Integer.class) {
            cellValue = new Integer(results.getInt(columnNames[i]));
          } else {
            if(columnClasses[i]==Float.class) {
              cellValue = new Float(results.getInt(columnNames[i]));
            } else {
              if(columnClasses[i]==Double.class) {
                cellValue = new Double(results.getDouble(columnNames[i]));
              } else {
                if(columnClasses[i]==java.sql.Date.class) {
                  cellValue = results.getDate(columnNames[i]);
                } else {
                  System.out.println("Can't assign " + columnNames[i]);
                }
              }
            }
          }
        }

        cellList.add(cellValue);
      } // for

      Object[] cells = cellList.toArray();
      rowList.add(cells);
    } // while

// finally create contents two-dim array
    contents = new Object[rowList.size()][];

    for(int i = 0; i<contents.length; i++) {
      contents[i] = (Object[])rowList.get(i);
    }

    System.out.println("Created model with " + contents.length + " rows");

// close stuff
    results.close();
    statement.close();
  }
}
