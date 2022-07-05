package exp.swing.jtables.sort;


import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;


/**
 * In a chain of data manipulators some behaviour is common. TableMap provides
 * most of this behavour and can be subclassed by filters that only need to
 * override a handful of specific methods. TableMap implements TableModel by
 * routing all requests to its model, and TableModelListener by routing all
 * events to its listeners. Inserting a TableMap which has not been subclassed
 * into a chain of table filters should have no effect.
 *
 * @author Philip Milne
 * @version 1.4 12/17/97
 */
public class TableMap
    extends DefaultTableModel
    //extends AbstractTableModel
    implements TableModelListener {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  protected TableModel tableModel;

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param row  Insert doc ...
   * @param column  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public boolean isCellEditable(final int row, final  int column) {
    return this.tableModel.isCellEditable(row, column);
  }

  /**
   * Insert doc ...
   *
   * @param aColumn  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public Class getColumnClass(final int aColumn) {
    return this.tableModel.getColumnClass(aColumn);
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public int getColumnCount() {
    return (this.tableModel==null)
    ? 0
    : this.tableModel.getColumnCount();
  }

  /**
   * Insert doc ...
   *
   * @param aColumn  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public String getColumnName(final int aColumn) {
    return this.tableModel.getColumnName(aColumn);
  }

  /**
   * Insert doc ...
   *
   * @param model  Insert doc ...
   */
  public void setModel(final TableModel model) {
    this.tableModel = model;
    this.tableModel.addTableModelListener(this);
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public TableModel getModel() {
    return this.tableModel;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public int getRowCount() {
    return (this.tableModel==null)
    ? 0
    : this.tableModel.getRowCount();
  }

  /**
   * Insert doc ...
   *
   * @param aValue  Insert doc ...
   * @param aRow  Insert doc ...
   * @param aColumn  Insert doc ...
   */
  public void setValueAt(final Object aValue,final int aRow,final int aColumn) {
    this.tableModel.setValueAt(aValue, aRow, aColumn);
  }

  // By default, implement TableModel by forwarding all messages
  // to the model.
  public Object getValueAt(final int aRow, final int aColumn) {
    return this.tableModel.getValueAt(aRow, aColumn);
  }

//
// Implementation of the TableModelListener interface,
//
  // By default forward all events to all the listeners.
  public void tableChanged(final TableModelEvent e) {
    this.fireTableChanged(e);
  }
}
