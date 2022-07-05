

package jdk1_6examples.swing.tables.sorting;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class InossStvRecsTablesSorting {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(InossStvRecsTablesSorting.class.getName());


 /**
  * The InossStvRecsTablesSorting default constuctor.
  */
  public InossStvRecsTablesSorting(){
    
    
    Runnable runner = new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Sorting JTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object rows[][] = { 
          {"AMZN", "2019-05-02 12:23:34", "3.00:23:34"}, 
          {"AMZN4", "2019-05-02 14:23:34", "7:12:34"}, 
          {"AMZN3", "2019-05-02 15:23:34", "2.09:23:34"}, 
          {"AMZN1", "2019-05-02 16:23:34", "15:02:34"}, 
          {"AMZN7", "2019-05-03 10:23:34", "12:23:34"}, 
          {"AMZN6", "2019-05-03 11:23:34", "10:23:34"}, 
          {"AMZN9", "2019-04-01 23:23:34", "1.10:23:34"}, 
          {"AMZN8", "2019-04-02 22:23:34", "00:23:34"}, 
        };
        String columns[] = {"name", "date", "duration"};
        TableModel model = new DefaultTableModel(rows, columns) {
          public Class getColumnClass(int column) {
            Class returnValue;
            if ( (column >= 0) && (column < getColumnCount())) {
              returnValue = getValueAt(0, column).getClass();
            } else {
              returnValue = Object.class;
            }
            return returnValue;
          }
        };

        JTable table = new JTable(model);
        final TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<>(model);
        table.setRowSorter(tableRowSorter);

        tableRowSorter.setComparator(0, (final String str1, final String str2) -> {
          return str1.compareTo(str2);
        });
        tableRowSorter.setComparator(1, (final String str1, final String str2) -> {
          return compareDates(str1, str2);
        });
        tableRowSorter.setComparator(2, (final String str1, final String str2) -> {
          return compareDurations(str1, str2);
        });

        
        
      final EfaDefaultTableCellRenderer rtCellRenderer = new EfaDefaultTableCellRenderer();
      table.getColumn("name").setCellRenderer(rtCellRenderer);
      table.getColumn("date").setCellRenderer(rtCellRenderer);
      table.getColumn("duration").setCellRenderer(rtCellRenderer);
        
        
        
        
        
        
        
        
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.CENTER);
        frame.setSize(450, 250);
        frame.setVisible(true);
      }
    };
    EventQueue.invokeLater(runner);    
  }


  /**
   * 
   * @param strDate1 like: YYYY-mm-DD HH:MM:SS
   * @param strDate2 like: YYYY-mm-DD HH:MM:SS
   * @return 
   */
  public static int compareDates(final String strDate1, final String strDate2){
    return 
        compareDates(
            strDate1, 
            strDate2, 
            "YYYY-mm-DD HH:MM:SS");
  }

  /**
   * 
   * @param strDate1 like: YYYY-mm-DD HH:MM:SS
   * @param strDate2 like: YYYY-mm-DD HH:MM:SS
   * @param strPattern
   * @return 
   * @since 20190507
   */
  public static int compareDates(
      final String strDate1, 
      final String strDate2,
      final String strPattern){
    
    int iCompareValue = 0;

    try {
      final Date date1 = new SimpleDateFormat(strPattern).parse(strDate1);
      final Date date2 = new SimpleDateFormat(strPattern).parse(strDate2);
      
      iCompareValue = date1.before(date2)? -1: date1.after(date2)? 1: 0;
      
    } catch (ParseException parseException) {
      // 2 ignore...
    }

    return iCompareValue;
  }

  /**
   * 
   * @param str1 like: NbrDays.HH:MM:SS
   * @param str2 like: NbrDays.HH:MM:SS
   * @return 
   */
  public static int compareDurations(String str1, String str2){
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
  
  

  
  public static void main(final String[] args){
    final InossStvRecsTablesSorting clazz = new InossStvRecsTablesSorting();
  }
}

class Rec{
  
}

/**
 * 
 * @author 2334
 */
class EfaDefaultTableCellRenderer extends DefaultTableCellRenderer {
  //~ Static fields/initializers ///////////////////////////////////////////////


  public Color COLOR_TABLE_BG;
  public Color COLOR_TABLE_FG;
  public Color COLOR_TABLE_SELECTION_BG;
  public Color COLOR_TABLE_SELECTION_FG;


  //~ Instance fields //////////////////////////////////////////////////////////

  /**
   * 
   * @param jTable
   * @param value
   * @param isSelected
   * @param hasFocus
   * @param row
   * @param col
   * @return 
   */
  @Override
  public Component getTableCellRendererComponent(
      final JTable jTable,
      Object value,
      final boolean isSelected,
      final boolean hasFocus,
      final int row,
      final int col) {

    final JLabel label = 
      (JLabel) super.getTableCellRendererComponent(jTable, value, isSelected, hasFocus, row, col);
      
    if(this.COLOR_TABLE_BG == null){
      this.setColors(jTable, label);
    }
    
System.out.println("getTableCellRendererComponent "
    +"\n\tjTable.getRowCount()="+jTable.getRowCount()
    +"\n\tjTable.getSelectedRow()="+jTable.getSelectedRow()
//    +"\n\trunRecStv.getSelectedRow()="+runRecStv.getReplayType().getSelectedRow()
//    +"\n\tiReplaySelRow="+iReplaySelRow
//    +"\n\ttable.convertRow()"+jTable.convertRowIndexToModel(runRecStv.getReplayType().getSelectedRow())
//    +"\n\tDurationSorted?"+(this.netVideoRecRecordings != null && NetVideoRecRecordings.isDurationSorted()? 
//        true: 
//        this.netVideoRecAlarms != null && NetVideoRecAlarms.isDurationSorted())
//    +"\n\tthis.netVideoRecRecordings!=null?"+(this.netVideoRecRecordings!=null)
//    +"\n\tthis.netVideoRecAlarms!=null?"+(this.netVideoRecAlarms!=null)
    +"\n\tjTable.convertRowIndexToModel(..)="+
        (jTable.getSelectedRow() > -1 && jTable.getSelectedRow() < jTable.getRowCount()?
                  ""+jTable.convertRowIndexToModel(jTable.getSelectedRow()):
                  "-1")
);
      
    
    return label;

  }
  
  /**
   * 
   * @param jLabel
   * @param jTable 
   */
  public void setColors(final JTable jTable, final JLabel jLabel) {
    try {
      this.COLOR_TABLE_BG = jLabel.getBackground();
      this.COLOR_TABLE_FG = jLabel.getForeground();
      this.COLOR_TABLE_SELECTION_BG = jTable.getSelectionBackground();
      this.COLOR_TABLE_SELECTION_FG = jTable.getSelectionForeground();
      
    } catch (Exception e) {
      this.COLOR_TABLE_BG = UIManager.getColor("Table.background");
      this.COLOR_TABLE_FG = UIManager.getColor("Table.foreground");
      this.COLOR_TABLE_SELECTION_BG = UIManager.getColor("Table.selectionBackground");
      this.COLOR_TABLE_SELECTION_FG = UIManager.getColor("Table.selectionForeground");
    }
  }
    
}
