
package jdk1_6examples.javax.swing.dnd;
/*
 * DragDropTable.java
 * source level 1.4
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class DragDropTable extends JFrame {

    public DragDropTable() {
        setTitle("Drag and Drop JTable");
        JTextArea hints = new JTextArea("1. Select a row in Table A. " +
                "Press the row again and drag. \n     " +
                "While dragging the mouse pointer over Table B, the row that is just under the mouse pointer is marked " +
                "- the new rows are inserted below the selected row. \n     " +
                "Let the row drop on Table B. Note that the row has been removed from Table A, " +
                "and appears now in Table B. \n" +
                "2. Select two rows in Table A and let them drop on Table B. " +
                "Two new rows appear in Table B. ");
        hints.setEditable(false);
        hints.setBackground(new Color(255, 255, 204));
        hints.setBorder(new LineBorder(Color.orange, 5));
        getContentPane().add(hints, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(createTable("Table A"));
        panel.add(createTable("Table B"));
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createTable(String tableId) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Column 0");
        model.addColumn("Column 1");
        model.addColumn("Column 2");
        model.addColumn("Column 3");
        model.addRow(new String[]{tableId + " 00", tableId + " 01", tableId + " 02", tableId + " 03"});
        model.addRow(new String[]{tableId + " 10", tableId + " 11", tableId + " 12", tableId + " 13"});
        model.addRow(new String[]{tableId + " 20", tableId + " 21", tableId + " 22", tableId + " 23"});
        model.addRow(new String[]{tableId + " 30", tableId + " 31", tableId + " 32", tableId + " 33"});
        model.addRow(new String[]{tableId + " 40", tableId + " 41", tableId + " 42", tableId + " 43"});
        model.addRow(new String[]{tableId + " 50", tableId + " 51", tableId + " 52", tableId + " 53"});
        model.addRow(new String[]{tableId + " 60", tableId + " 61", tableId + " 62", tableId + " 63"});
        model.addRow(new String[]{tableId + " 70", tableId + " 71", tableId + " 72", tableId + " 73"});
        JTable table = new JTable(model) {
            //This method enables drop on an empty JTable:
            public boolean getScrollableTracksViewportHeight() {
                Container viewport = getParent();
                if (!(viewport instanceof JViewport)) {
                    return false;
                }
                return getPreferredSize().height < viewport.getHeight();
            }
        };
//        table.setFillsViewportHeight(true);//since 1.6 (enables drop on an empty JTable)
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        table.setDragEnabled(true);
        table.setTransferHandler(new TableTransferHandler());
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        panel.setBorder(BorderFactory.createTitledBorder(tableId));
        return panel;
    }

    public static void main(String[] args) {
        new DragDropTable().setVisible(true);
    }

    abstract class StringTransferHandler extends TransferHandler {

        protected abstract String exportString(JComponent c);

        protected abstract void importString(JComponent c, String str);

        protected abstract void cleanup(JComponent c, boolean remove);

        protected Transferable createTransferable(JComponent c) {
            return new StringSelection(exportString(c));
        }

        public int getSourceActions(JComponent c) {
            return COPY_OR_MOVE;
        }

        public boolean importData(JComponent c, Transferable t) {
            if (canImport(c, t.getTransferDataFlavors())) {
                try {
                    String str = (String) t.getTransferData(DataFlavor.stringFlavor);
                    importString(c, str);
                    return true;
                } catch (UnsupportedFlavorException ufe) {
                } catch (IOException ioe) {
                }
            }
            return false;
        }

        protected void exportDone(JComponent c, Transferable data, int action) {
            cleanup(c, action == MOVE);
        }

        public boolean canImport(JComponent c, DataFlavor[] flavors) {
            for (int ndx = 0; ndx < flavors.length; ndx++) {
                if (DataFlavor.stringFlavor.equals(flavors[ndx])) {
                    return true;
                }
            }
            return false;
        }
    }

    class TableTransferHandler extends StringTransferHandler {

        public JTable target;
        public int[] rows = null;
        public int addIndex = -1; //the position where rows are inserted
        public int addCount = 0;  //number of inserted rows

        protected String exportString(JComponent c) {
            JTable table = (JTable) c;
            rows = table.getSelectedRows();
            int colCount = table.getColumnCount();
            StringBuffer buff = new StringBuffer();
            for (int ndx = 0; ndx < rows.length; ndx++) {
                for (int j = 0; j < colCount; j++) {
                    Object val = table.getValueAt(rows[ndx], j);
                    buff.append(val == null ? "" : val.toString());
                    if (j != colCount - 1) {
                        buff.append(",");
                    }
                }
                if (ndx != rows.length - 1) {
                    buff.append("\n");
                }
            }
            return buff.toString();
        }

        protected void importString(JComponent c, String str) {
            target = (JTable) c;
            DefaultTableModel model = (DefaultTableModel) target.getModel();
            int index = target.getSelectedRow();
            //The user is not allowed to drop data on itself.
            //For example, when the user moves rows #4,#5,#6 and #7 and
            //tries to insert them below the row #5, it would be
            //problematic to remove the original rows.
            //That's why we don't allow it:
            if (rows != null && index >= rows[0] - 1 &&
                    index <= rows[rows.length - 1]) {
                rows = null;
                return;
            }
            int max = model.getRowCount();
            if (index < 0) {
                index = max;
            } else {
                index++;
                if (index > max) {
                    index = max;
                }
            }
            addIndex = index;
            String[] values = str.split("\n");
            addCount = values.length;
            for (int ndx = 0; ndx < values.length; ndx++) {
                model.insertRow(index++, values[ndx].split(","));
            }
            //If we are moving rows within the same table, we must
            //adapt the rows accordingly, as those below
            //the insertion point are shifted.
            if (rows != null && addCount > 0) {
                for (int ndx = 0; ndx < rows.length; ndx++) {
                    if (rows[ndx] > addIndex) {
                        rows[ndx] += addCount;
                    }
                }
            }
        }

        protected void cleanup(JComponent c, boolean remove) {
            JTable source = (JTable) c;
            if (remove && rows != null) {
                DefaultTableModel model =
                        (DefaultTableModel) source.getModel();
                for (int ndx = rows.length - 1; ndx >= 0; ndx--) {
                    model.removeRow(rows[ndx]);
                }
            }
            rows = null;
            addCount = 0;
            addIndex = -1;
        }
    }
}

