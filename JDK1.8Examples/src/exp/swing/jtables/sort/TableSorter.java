package exp.swing.jtables.sort;


import java.awt.event.InputEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import javax.swing.event.TableModelEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * A sorter for TableModels. The sorter has a model (conforming to TableModel)
 * and itself implements TableModel. TableSorter does not store or copy the
 * data in the TableModel, instead it maintains an array of integers which it
 * keeps the same size as the number of rows in its model. When the model
 * changes it notifies the sorter that something has changed eg. "rowsAdded"
 * so that its internal array of integers can be reallocated. As requests are
 * made of the sorter (like getValueAt(row, col) it redirects them to its
 * model via the mapping array. That way the TableSorter appears to hold
 * another copy of the table with the rows in a different order. The sorting
 * algorthm used is stable which means that it does not move around rows when
 * its comparison function returns 0 to denote that they are equivalent.
 *
 * @author Philip Milne
 * @version 1.5 12/17/97
 */
public class TableSorter
    extends TableMap {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private int intLastSortedColumn = 0;

  /** .. */
  private java.util.List listOfSortingColumns = new Vector();

  /** .. */
  private int[] indexes;

  /** .. */
  private boolean isAscending = false;

  /** .. */
  private int intCompares;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new TableSorter object.
   */
  public TableSorter() {
    this.indexes = new int[0]; // for consistency
  }

  /**
   * Creates a new TableSorter object.
   *
   * @param model Insert doc ...
   */
  public TableSorter(final TableModel model) {
    this.setModel(model);
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param isAscending  Insert doc ...
   */
  public void setAscending(final boolean isAscending) {
    this.isAscending = isAscending;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public boolean isAscending() {
    return this.isAscending;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public int getLastSortedColumn() {
    return this.intLastSortedColumn;
  }

  /**
   * Insert doc ...
   *
   * @param intLastSortedColumn  Insert doc ...
   */
  public void setLastSortedColumn(final int intLastSortedColumn) {
    this.intLastSortedColumn = intLastSortedColumn;
  }

  /**
   * Insert doc ...
   *
   * @param model Insert doc ...
   */
  public void setModel(final TableModel model) {
    super.setModel(model);
    this.reallocateIndexes();
  }

  /**
   * Insert doc ...
   *
   * @param aValue Insert doc ...
   * @param aRow Insert doc ...
   * @param aColumn Insert doc ...
   */
  public void setValueAt(
      final Object aValue,
      final int aRow,
      final int aColumn) {
    //this.checkModel();
    if (SwingUtilities.isEventDispatchThread()) {
      tableModel.setValueAt(aValue, indexes[aRow], aColumn);
    } else {
      SwingUtilities.invokeLater(() -> {
        tableModel.setValueAt(aValue, indexes[aRow], aColumn);
      });
    }
  }

  // The mapping only affects the contents of the data rows.
  // Pass all requests to these rows through the mapping array: "indexes".

  /**
   * DOCUMENT ME!
   *
   * @param aRow int
   * @param aColumn int
   *
   * @return Object
   */
  public Object getValueAt(
      final int aRow,
      final int aColumn) {

    //this.checkModel();

    return this.tableModel.getValueAt(this.indexes[aRow], aColumn);
  }

  /**
   * There is no-where else to put this. Add a mouse listener to the Table to
   * trigger a table sort when a column heading is clicked in the JTable.
   *
   * @param table JTable
   */
  public void addMouseListenerToHeaderInTable(final JTable table) {
    final TableSorter sorter = this;
    final JTable tableView = table;
    tableView.setColumnSelectionAllowed(false);

    MouseAdapter listMouseListener =
        new MouseAdapter() {
      public void mouseClicked(final MouseEvent e) {
        final TableColumnModel columnModel = tableView.getColumnModel();
        final int viewColumn = columnModel.getColumnIndexAtX(e.getX());
        final int column = tableView.convertColumnIndexToModel(viewColumn);

        if ( (e.getClickCount() == 1) && (column != -1)) {
          final int shiftPressed = e.getModifiers() & InputEvent.SHIFT_MASK;
          final boolean ascending = !isAscending; //(shiftPressed==0);

          sorter.sortByColumn(column, ascending);

//          TableColumn tableColumn =
//              columnModel.getColumn(intLastSortedColumn);
//          ( (SortedHeaderRenderer) tableColumn.getHeaderRenderer()).getLabel().
//              setBorder(
//                  BorderFactory.createRaisedBevelBorder());
//
//          //intLastSortedColumn   = column;
//
//          tableColumn = columnModel.getColumn(column);
//
//          ( (SortedHeaderRenderer) tableColumn.getHeaderRenderer()).getLabel().
//              setBorder(
//                  BorderFactory.createLoweredBevelBorder());

          final JViewport viewport = (JViewport) tableView.getParent();
          final JScrollPane scrollPane = (JScrollPane) viewport.getParent();

          scrollPane.repaint();

        }
      }
    };

    final JTableHeader th = tableView.getTableHeader();
    final MouseListener[] ml = th.getMouseListeners();

    th.addMouseListener(listMouseListener);
  }

  /**
   * Insert doc ...
   */
  private void _checkModel() {
    if (indexes.length != tableModel.getRowCount()) {
//      System.err.println("Sorter not informed of a change in model.");
    }
  }

  /**
   * Insert doc ...
   *
   * @param row1 Insert doc ...
   * @param row2 Insert doc ...
   *
   * @return Insert doc ...
   */
  private int compare(final int row1, final int row2) {
    intCompares++;

    Integer column;
    int result;

    for (int level = 0; level < listOfSortingColumns.size(); level++) {
      column = (Integer) listOfSortingColumns.get(level);
      result = compareRowsByColumn(row1, row2, column.intValue());

      if (result != 0) {
        return isAscending
            ? result
            : ( -result);
      }
    }

    return 0;
  }

  /**
   * Insert doc ...
   *
   * @param row1 Insert doc ...
   * @param row2 Insert doc ...
   * @param column Insert doc ...
   *
   * @return Insert doc ...
   */
  private int compareRowsByColumn(
      final int row1,
      final int row2,
      final int column) {
    final Class type = tableModel.getColumnClass(column);
    final TableModel data = tableModel;

    // Check for nulls.
    final Object o1 = data.getValueAt(row1, column);
    final Object o2 = data.getValueAt(row2, column);

    // If both values are null, return 0.
    if ( (o1 == null) && (o2 == null)) {
      return 0;
    } else if (o1 == null) { // Define null less than everything.

      return -1;
    } else if (o2 == null) {
      return 1;
    }

    /*
     * We copy all returned values from the getValue call in case
     * an optimised model is reusing one object to return many
     * values.  The Number subclasses in the JDK are immutable and
     * so will not be used in this way but other subclasses of
     * Number might want to do this to save space and avoid
     * unnecessary heap allocation.
     */
    if (type.getSuperclass() == java.lang.Number.class) {
      if(column != 5){
        final Number n1 = (Number) data.getValueAt(row1, column);
        final double d1 = n1.doubleValue();
        final Number n2 = (Number) data.getValueAt(row2, column);
        final double d2 = n2.doubleValue();

        if (d1 < d2) {
          return -1;
        } else if (d1 > d2) {
          return 1;
        } else {
          return 0;
        }
      } else {
        // Duration like: NbrDays.HH:MM:SS
        final String str1 = data.getValueAt(row1, column) + "";
        final String str2 = data.getValueAt(row2, column) + "";

        return compareDurations(str1, str2);
      }

    } else if (type == java.util.Date.class) {
      final Date d1 = (Date) data.getValueAt(row1, column);
      final long n1 = d1.getTime();
      final Date d2 = (Date) data.getValueAt(row2, column);
      final long n2 = d2.getTime();

      if (n1 < n2) {
        return -1;
      } else if (n1 > n2) {
        return 1;
      } else {
        return 0;
      }

    } else if (type == String.class) {
      final String s1 = (String) data.getValueAt(row1, column);
      final String s2 = (String) data.getValueAt(row2, column);
      final int result = s1.compareTo(s2);

      if (result < 0) {
        return -1;
      } else if (result > 0) {
        return 1;
      } else {
        return 0;
      }

    } else if (type == Boolean.class) {
      final Boolean bool1 = (Boolean) data.getValueAt(row1, column);
      final boolean b1 = bool1.booleanValue();
      final Boolean bool2 = (Boolean) data.getValueAt(row2, column);
      final boolean b2 = bool2.booleanValue();

      if (b1 == b2) {
        return 0;
      } else if (b1) { // Define false < true

        return 1;
      } else {
        return -1;
      }

    } else {
      final Object v1 = data.getValueAt(row1, column);
      final String s1 = v1.toString();
      final Object v2 = data.getValueAt(row2, column);
      final String s2 = v2.toString();
      final int result = s1.compareTo(s2);

      if (result < 0) {
        return -1;
      } else if (result > 0) {
        return 1;
      } else {
        return 0;
      }
    }
  }

  /**
   *
   * @param strValue like: NbrDays.HH:MM:SS
   * @return
   */
  private static int compareDurations(String str1, String str2){
    int iCompareValue = 0;

    final int idxDot1 = str1.indexOf('.');
    final int idxDot2 = str2.indexOf('.');

    final int nDays1 = idxDot1 > -1 ? Integer.parseInt(str1.substring(0, idxDot1)): 0;
    final int nDays2 = idxDot2 > -1 ? Integer.parseInt(str2.substring(0, idxDot2)): 0;

    if (nDays1 != nDays2) {
      iCompareValue = nDays1 < nDays2 ? -1 : 1;

    } else {
      str1 = idxDot1 > -1? str1.substring(idxDot1+1, str1.length()): str1;
      str2 = idxDot2 > -1? str2.substring(idxDot2+1, str2.length()): str2;

      // continue comparing hours:minutes:seconds from possible HH:MM:SS
      final String[] strs1 = str1.split(":");
      final String[] strs2 = str2.split(":");

      if(strs1.length != strs2.length){
        iCompareValue = strs1.length < strs2.length ? -1 : 1;

      } else {
        // HH:MM:SS or MM:SS or SS...
        for(int i=0; i<strs1.length; i++){
          if(!strs1[i].equals(strs2[i])){
            final int i1 = Integer.parseInt(strs1[i]);
            final int i2 = Integer.parseInt(strs2[i]);

            iCompareValue =
              i1 < i2?
                -1:
                i1 > i2?
                  1:
                  0;
            break;
          }
        }
      }
    }

    return iCompareValue;
  }

  /**
   * Insert doc ...
   */
  private void n2sort() {
    final int intNbrOfRows = getRowCount();
    for (int i = 0; i < intNbrOfRows; i++) {
      for (int j = i + 1; j < intNbrOfRows; j++) {
        if (compare(indexes[i], indexes[j]) == -1) {
          swap(i, j);
        }
      }
    }
  }

  /**
   * Insert doc ...
   */
  private void reallocateIndexes() {
    final int intNbrOfRows = tableModel.getRowCount();

    // Set up a new array of indexes with the right number of elements
    // for the new data model.
    indexes = new int[intNbrOfRows];

    // Initialise with the identity mapping.
    for (int row = 0; row < intNbrOfRows; row++) {
      indexes[row] = row;
    }
  }

  /**
   * This is a home-grown implementation which we have not had time to
   * research - it may perform poorly in some circumstances. It requires twice
   * the space of an in-place algorithm and makes NlogN assigments shuttling
   * the values between the two arrays. The number of compares appears to vary
   * between N-1 and NlogN depending on the initial order but the main reason
   * for using it here is that, unlike qsort, it is stable.
   *
   * @param from int[]
   * @param to int[]
   * @param low int
   * @param high int
   */
  private void shuttlesort(
      final int[] from,
      final int[] to,
      final int low,
      final int high) {

    if ( (high - low) < 2) {
      return;
    }

    final int middle = (low + high) / 2;
    shuttlesort(to, from, low, middle);
    shuttlesort(to, from, middle, high);

    int p = low;
    int q = middle;

    /* This is an optional short-cut; at each recursive call,
       check to see if the elements in this subset are already
       ordered.  If so, no further comparisons are needed; the
       sub-array can just be copied.  The array must be copied rather
       than assigned otherwise sister calls in the recursion might
       get out of sinc.  When the number of elements is three they
       are partitioned so that the first set, [low, mid), has one
       element and and the second, [mid, high), has two. We skip the
       optimisation when the number of elements is three or less as
       the first compare in the normal merge will produce the same
       sequence of steps. This optimisation seems to be worthwhile
       for partially ordered lists but some analysis is needed to
       find out how the performance drops to Nlog(N) as the initial
       order diminishes - it may drop very quickly.  */
    if ( ( (high - low) >= 4) && (compare(from[middle - 1], from[middle]) <= 0)) {
      for (int i = low; i < high; i++) {
        to[i] = from[i];
      }

      return;
    }

    // A normal merge.
    for (int i = low; i < high; i++) {
      if ( (q >= high) || ( (p < middle) && (compare(from[p], from[q]) <= 0))) {
        to[i] = from[p++];
      } else {
        to[i] = from[q++];
      }
    }
  }

  /**
   * Insert doc ...
   *
   * @param sender Insert doc ...
   */
  private void sort(final Object sender) {
    //this.checkModel();
    this.intCompares = 0;
    this.shuttlesort( (int[])this.indexes.clone(), this.indexes, 0,
                     this.indexes.length);
  }

  /**
   * Insert doc ...
   *
   * @param column Insert doc ...
   */
  public void sortByColumn(final int column) {
    this.sortByColumn(column, !isAscending);
    //this.sortByColumn(column, true);
  }

  /**
   * Insert doc ...
   *
   * @param column Insert doc ...
   * @param ascending Insert doc ...
   */
  private void sortByColumn(final int column, final boolean ascending) {
    this.isAscending = ascending;
    this.listOfSortingColumns.clear();
    this.listOfSortingColumns.add(new Integer(column));
    this.sort(this);
    super.tableChanged(new TableModelEvent(this));
    this.setLastSortedColumn(column);
  }

  /**
   * Insert doc ...
   *
   * @param i Insert doc ...
   * @param j Insert doc ...
   */
  private void swap(final int i, final int j) {
    final int tmp = this.indexes[i];
    this.indexes[i] = indexes[j];
    this.indexes[j] = tmp;
  }

  /**
   * Insert doc ...
   *
   * @param e Insert doc ...
   */
  public void tableChanged(final TableModelEvent e) {
    this.reallocateIndexes();

    super.tableChanged(e);
  }




  public static void main(String[] args) {
    String[] ss = {
      "6.12:23:34",
      "14:21:31",
      "5.09:03:04",
      "5.9:3:4",
      "9:34:54",
      "33:24",
      "54",
      "55",
      "55:54",
    };

    for(String s1: ss){
      for(String s2: ss){
        final int i = compareDurations(s1, s2);
        System.out.println(s1+" vs "+s2+"? "+(i==-1?"smaller...": i==0?"equal...": "greater..."));
      }
    }
  }
}
