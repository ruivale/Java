
package jdk1_6examples.javax.swing.dnd.jtable;


import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DragDropJTableCellContents extends JFrame {

    public DragDropJTableCellContents() {
        setTitle("Drag and Drop JTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(createTable("JTable"), BorderLayout.CENTER);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    private JPanel createTable(String tableId) {
        DefaultTableModel model = new DefaultTableModel();
        for (int i = 0; i < 10; i++) {
            model.addColumn("Column "+i);
        }
        for (int i = 0; i < 10; i++) {
            String[] rowData = new String[10];
            for (int j = 0; j < 10; j++) {
                rowData[j] = tableId + " " + i + j;
            }
            model.addRow(rowData);
        }
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setCellSelectionEnabled(true);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setDragEnabled(true);
        TableTransferHandler th = new TableTransferHandler();
        table.setTransferHandler(th);
        table.setDropTarget(new TableDropTarget(th));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane);
        panel.setBorder(BorderFactory.createTitledBorder(tableId));
        return panel;
    }

    public static void main(String[] args) {
        new DragDropJTableCellContents().setVisible(true);
    }

    abstract class StringTransferHandler extends TransferHandler {

        public int dropAction;

        protected abstract String exportString(JComponent c);

        protected abstract void importString(JComponent c, String str);

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new StringSelection(exportString(c));
        }

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
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

        @Override
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

        private int dragRow;
        private int[] dragColumns;
        private BufferedImage[] image;
        private int row;
        private int[] columns;
        public JTable target;

        @Override
        protected Transferable createTransferable(JComponent c) {
            JTable table = (JTable) c;
            dragRow = table.getSelectedRow();
            dragColumns = table.getSelectedColumns();
            createDragImage(table);
            return new StringSelection(exportString(c));
        }

        protected String exportString(JComponent c) {
            JTable table = (JTable) c;
            row = table.getSelectedRow();
            columns = table.getSelectedColumns();
            StringBuffer buff = new StringBuffer();
            for (int j = 0; j < columns.length; j++) {
                Object val = table.getValueAt(row, columns[j]);
                buff.append(val == null ? "" : val.toString());
                if (j != columns.length - 1) {
                    buff.append(",");
                }
            }
            return buff.toString();
        }

        protected void importString(JComponent c, String str) {
            target = (JTable) c;
            DefaultTableModel model = (DefaultTableModel) target.getModel();
            String[] values = str.split("\n");
            int colCount = target.getSelectedColumn();
            int max = target.getColumnCount();
            for (int ndx = 0; ndx < values.length; ndx++) {
                String[] data = values[ndx].split(",");
                for (int i = 0; i < data.length; i++) {
                    String string = data[i];
                    if(colCount < max){
                        model.setValueAt(string, target.getSelectedRow(), colCount);
                    }
                    colCount++;
                }
            }
        }

        public Image /*BufferedImage[]*/ getDragImage() {
            return image[0];
        }

        private void createDragImage(JTable table) {
            if (dragColumns != null) {
                try {
                    image = new BufferedImage[dragColumns.length];
                    for (int i = 0; i < dragColumns.length; i++) {
                        Rectangle cellBounds = table.getCellRect(dragRow, i, true);
                        TableCellRenderer r = table.getCellRenderer(dragRow, i);
                        DefaultTableModel m = (DefaultTableModel) table.getModel();
                        JComponent lbl = (JComponent) r.getTableCellRendererComponent(table,
                                table.getValueAt(dragRow, dragColumns[i]), false, false, dragRow, i);
                        lbl.setBounds(cellBounds);
                        BufferedImage img = new BufferedImage(lbl.getWidth(), lbl.getHeight(),
                                BufferedImage.TYPE_INT_ARGB_PRE);
                        Graphics2D graphics = img.createGraphics();
                        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                        lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                        lbl.paint(graphics);
                        graphics.dispose();
                        image[i] = img;
                    }
                } catch (RuntimeException re) {
                }
            }
        }
    }

    class TableDropTarget extends DropTarget {

        private Insets autoscrollInsets = new Insets(20, 20, 20, 20);
        private Rectangle rect2D = new Rectangle();
        private TableTransferHandler handler;

        public TableDropTarget(TableTransferHandler h) {
            super();
            this.handler = h;
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
            handler.dropAction = dtde.getDropAction();
            JTable table = (JTable) dtde.getDropTargetContext().getComponent();
            Point location = dtde.getLocation();
            int row = table.rowAtPoint(location);
            int column = table.columnAtPoint(location);
            table.changeSelection(row, column, false, false);
            paintImage(table, location);
            autoscroll(table, location);
            super.dragOver(dtde);
        }

        public void dragExit(DropTargetDragEvent dtde) {
            clearImage((JTable) dtde.getDropTargetContext().getComponent());
            super.dragExit(dtde);
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            Transferable data = dtde.getTransferable();
            JTable table = (JTable) dtde.getDropTargetContext().getComponent();
            clearImage(table);
            handler.importData(table, data);
            super.drop(dtde);
        }

        private final void paintImage(JTable table, Point location) {
            Point pt = new Point(location);
            /*BufferedImage[]*/Image image = handler.getDragImage();
            if (image != null) {
                table.paintImmediately(rect2D.getBounds());
                rect2D.setLocation(pt.x - 15, pt.y - 15);
                int wRect2D = 0;
                int hRect2D = 0;
                
                

                table.getGraphics().drawImage(image, pt.x - 15, pt.y - 15, table);
                ImageIcon imgIcon = new ImageIcon(image);
                pt.x += imgIcon.getIconWidth();
                if (hRect2D < imgIcon.getIconHeight()) {
                    hRect2D = imgIcon.getIconHeight();
                }
                wRect2D += imgIcon.getIconWidth();
                
//                for (int i = 0; i < image.length; i++) {
//                    table.getGraphics().drawImage(image[i], pt.x - 15, pt.y - 15, table);
//                    pt.x += image[i].getWidth();
//                    if (hRect2D < image[i].getHeight()) {
//                        hRect2D = image[i].getHeight();
//                    }
//                    wRect2D += image[i].getWidth();
//                }
                
                
                rect2D.setSize(wRect2D, hRect2D);
            }
        }

        private final void clearImage(JTable table) {
            table.paintImmediately(rect2D.getBounds());
        }

        private Insets getAutoscrollInsets() {
            return autoscrollInsets;
        }

        private void autoscroll(JTable table, Point cursorLocation) {
            Insets insets = getAutoscrollInsets();
            Rectangle outer = table.getVisibleRect();
            Rectangle inner = new Rectangle(outer.x + insets.left,
                    outer.y + insets.top,
                    outer.width - (insets.left + insets.right),
                    outer.height - (insets.top + insets.bottom));
            if (!inner.contains(cursorLocation)) {
                Rectangle scrollRect = new Rectangle(cursorLocation.x - insets.left,
                        cursorLocation.y - insets.top,
                        insets.left + insets.right,
                        insets.top + insets.bottom);
                table.scrollRectToVisible(scrollRect);
            }
        }
    }
}
