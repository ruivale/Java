package exp.tables;

// File: GroupableHeaderExample.java
//
/* (swing1.1beta3)
 *
 * |-----------------------------------------------------|
 * ||Name  |    Language     |
 * ||-----------------|--------------------------|
 * |  SNo.| |  |   |  Others     |
 * ||   1 |    2   | Native |-----------------|
 * || |  |   |   2    |3    |
 * |-----------------------------------------------------|
 * || |  |   |    |     |
 *
 */
//package jp.gr.java_conf.tame.swing.examples;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
//import jp.gr.java_conf.tame.swing.table.*;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.plaf.basic.*;

/**
 * @version 1.0 11/09/98
 */
public class NestedHeaders
    extends JFrame {
  NestedHeaders() {
    super("Groupable Header Example");
    DefaultTableModel dm = new DefaultTableModel();
    dm.setDataVector(new Object[][] {
                     {"119", "foo", "bar", "ja", "ko", "zh"},
                     {"911", "bar", "foo", "en", "fr", "pt"}},
                     new Object[] {
                     "SNo.", "1", "2", "Native", "2", "3"});
    JTable table = new JTable(dm) {
      protected JTableHeader createDefaultTableHeader() {
        return new GroupableTableHeader(columnModel);
      }
    };
    TableColumnModel cm = table.getColumnModel();
    ColumnGroup g_name = new ColumnGroup("Name");
    g_name.add(cm.getColumn(1));
    g_name.add(cm.getColumn(2));
    ColumnGroup g_lang = new ColumnGroup("Language");
    g_lang.add(cm.getColumn(3));
    ColumnGroup g_other = new ColumnGroup("Others");
    g_other.add(cm.getColumn(4));
    g_other.add(cm.getColumn(5));
    g_lang.add(g_other);
    GroupableTableHeader header = (GroupableTableHeader) table.getTableHeader();
    header.addColumnGroup(g_name);
    header.addColumnGroup(g_lang);
    JScrollPane scroll = new JScrollPane(table);
    getContentPane().add(scroll);
    setSize(400, 120);
  }

  public static void main(String[] args) {
    NestedHeaders n = new NestedHeaders();
    n.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    }
    );
    n.pack();
    n.setVisible(true);
  }
}

// File: ColumnGroup.java
/*
 * (swing1.1beta3)
 *
 */

/**
 * ColumnGroup
 *
 * @version 1.0 10/20/98
 * @author Nobuo Tamemasa
 */
class ColumnGroup {
  protected TableCellRenderer renderer;
  protected Vector v;
  protected String text;
  protected int margin = 0;
  public ColumnGroup(String text) {
    this(null, text);
  }

  public ColumnGroup(TableCellRenderer renderer, String text) {
    if (renderer == null) {
      this.renderer = new DefaultTableCellRenderer() {
        public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
          JTableHeader header = table.getTableHeader();
          if (header != null) {
            setForeground(header.getForeground());
            setBackground(header.getBackground());
            setFont(header.getFont());
          }
          setHorizontalAlignment(JLabel.CENTER);
          setText( (value == null) ? "" : value.toString());
          setBorder(UIManager.getBorder("TableHeader.cellBorder"));
          return this;
        }
      };
    } else {
      this.renderer = renderer;
    }
    this.text = text;
    v = new Vector();
  }

  /**
   * @param obj    TableColumn or ColumnGroup
   */
  public void add(Object obj) {
    if (obj == null) {
      return;
    }
    v.addElement(obj);
  }

  /**
   * @param c    TableColumn
   * @param v    ColumnGroups
   */
  public Vector getColumnGroups(TableColumn c, Vector g) {
    g.addElement(this);
    if (v.contains(c)) {
      return g;
    }
    Enumeration enumeration = v.elements();
    while (enumeration.hasMoreElements()) {
      Object obj = enumeration.nextElement();
      if (obj instanceof ColumnGroup) {
        Vector groups =
            (Vector) ( (ColumnGroup) obj).getColumnGroups(c, (Vector) g.clone());
        if (groups != null) {
          return groups;
        }
      }
    }
    return null;
  }

  public TableCellRenderer getHeaderRenderer() {
    return renderer;
  }

  public void setHeaderRenderer(TableCellRenderer renderer) {
    if (renderer != null) {
      this.renderer = renderer;
    }
  }

  public Object getHeaderValue() {
    return text;
  }

  public Dimension getSize(JTable table) {
    Component comp = renderer.getTableCellRendererComponent(
        table, getHeaderValue(), false, false, -1, -1);
    int height = comp.getPreferredSize().height;
    int width = 0;
    Enumeration enumeration = v.elements();
    while (enumeration.hasMoreElements()) {
      Object obj = enumeration.nextElement();
      if (obj instanceof TableColumn) {
        TableColumn aColumn = (TableColumn) obj;
        width += aColumn.getWidth();
        width += margin;
      } else {
        width += ( (ColumnGroup) obj).getSize(table).width;
      }
    }
    return new Dimension(width, height);
  }

  public void setColumnMargin(int margin) {
    this.margin = margin;
    Enumeration enumeration = v.elements();
    while (enumeration.hasMoreElements()) {
      Object obj = enumeration.nextElement();
      if (obj instanceof ColumnGroup) {
        ( (ColumnGroup) obj).setColumnMargin(margin);
      }
    }
  }
}

// File: GroupableTableHeader.java
//
/*
 * (swing1.1beta3)
 *
 */

/**
 * GroupableTableHeader
 *
 * @version 1.0 10/20/98
 * @author Nobuo Tamemasa
 */
class GroupableTableHeader
    extends JTableHeader {
  private static final String uiClassID = "GroupableTableHeaderUI";
  protected Vector columnGroups = null;

  public GroupableTableHeader(TableColumnModel model) {
    super(model);
    setUI(new GroupableTableHeaderUI());
    setReorderingAllowed(false);
  }

  public void setReorderingAllowed(boolean b) {
    reorderingAllowed = false;
  }

  public void addColumnGroup(ColumnGroup g) {
    if (columnGroups == null) {
      columnGroups = new Vector();
    }
    columnGroups.addElement(g);
  }

  public Enumeration getColumnGroups(TableColumn col) {
    if (columnGroups == null) {
      return null;
    }
    Enumeration enumeration = columnGroups.elements();
    while (enumeration.hasMoreElements()) {
      ColumnGroup cGroup = (ColumnGroup)enumeration.nextElement();
      Vector v_ret = (Vector) cGroup.getColumnGroups(col, new Vector());
      if (v_ret != null) {
        return v_ret.elements();
      }
    }
    return null;
  }

  public void setColumnMargin() {
    if (columnGroups == null) {
      return;
    }
    int columnMargin = getColumnModel().getColumnMargin();
    Enumeration enumeration = columnGroups.elements();
    while (enumeration.hasMoreElements()) {
      ColumnGroup cGroup = (ColumnGroup)enumeration.nextElement();
      cGroup.setColumnMargin(columnMargin);
    }
  }

}

// File: GroupableTableHeaderUI.java
//
/*
 * (swing1.1beta3)
 *
 */

class GroupableTableHeaderUI
    extends BasicTableHeaderUI {

  public void paint(Graphics g, JComponent c) {
    Rectangle clipBounds = g.getClipBounds();
    if (header.getColumnModel() == null) {
      return;
    }
    ( (GroupableTableHeader) header).setColumnMargin();
    int column = 0;
    Dimension size = header.getSize();
    Rectangle cellRect = new Rectangle(0, 0, size.width, size.height);
    Hashtable h = new Hashtable();
    int columnMargin = header.getColumnModel().getColumnMargin();

    Enumeration enumerationeration = header.getColumnModel().getColumns();
    while (enumerationeration.hasMoreElements()) {
      cellRect.height = size.height;
      cellRect.y = 0;
      TableColumn aColumn = (TableColumn) enumerationeration.nextElement();
      Enumeration cGroups = ( (GroupableTableHeader) header).getColumnGroups(
          aColumn);
      if (cGroups != null) {
        int groupHeight = 0;
        while (cGroups.hasMoreElements()) {
          ColumnGroup cGroup = (ColumnGroup) cGroups.nextElement();
          Rectangle groupRect = (Rectangle) h.get(cGroup);
          if (groupRect == null) {
            groupRect = new Rectangle(cellRect);
            Dimension d = cGroup.getSize(header.getTable());
            groupRect.width = d.width;
            groupRect.height = d.height;
            h.put(cGroup, groupRect);
          }
          paintCell(g, groupRect, cGroup);
          groupHeight += groupRect.height;
          cellRect.height = size.height - groupHeight;
          cellRect.y = groupHeight;
        }
      }
      cellRect.width = aColumn.getWidth() + columnMargin;
      if (cellRect.intersects(clipBounds)) {
        paintCell(g, cellRect, column);
      }
      cellRect.x += cellRect.width;
      column++;
    }
  }

  private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
    TableColumn aColumn = header.getColumnModel().getColumn(columnIndex);
    TableCellRenderer renderer = aColumn.getHeaderRenderer();
    Component component = renderer.getTableCellRendererComponent(
        header.getTable(), aColumn.getHeaderValue(), false, false, -1,
        columnIndex);
    rendererPane.add(component);
    rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y,
                                cellRect.width, cellRect.height, true);
  }

  private void paintCell(Graphics g, Rectangle cellRect, ColumnGroup cGroup) {
    TableCellRenderer renderer = cGroup.getHeaderRenderer();
    Component component = renderer.getTableCellRendererComponent(
        header.getTable(), cGroup.getHeaderValue(), false, false, -1, -1);
    rendererPane.add(component);
    rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y,
                                cellRect.width, cellRect.height, true);
  }

  private int getHeaderHeight() {
    int height = 0;
    TableColumnModel columnModel = header.getColumnModel();
    for (int column = 0; column < columnModel.getColumnCount(); column++) {
      TableColumn aColumn = columnModel.getColumn(column);
      TableCellRenderer renderer = aColumn.getHeaderRenderer();
      Component comp = renderer.getTableCellRendererComponent(
          header.getTable(), aColumn.getHeaderValue(), false, false, -1, column);
      int cHeight = comp.getPreferredSize().height;
      Enumeration enumeration = ( (GroupableTableHeader) header).getColumnGroups(
          aColumn);
      if (enumeration != null) {
        while (enumeration.hasMoreElements()) {
          ColumnGroup cGroup = (ColumnGroup)enumeration.nextElement();
          cHeight += cGroup.getSize(header.getTable()).height;
        }
      }
      height = Math.max(height, cHeight);
    }
    return height;
  }

  private Dimension createHeaderSize(long width) {
    TableColumnModel columnModel = header.getColumnModel();
    width += columnModel.getColumnMargin() * columnModel.getColumnCount();
    if (width > Integer.MAX_VALUE) {
      width = Integer.MAX_VALUE;
    }
    return new Dimension( (int) width, getHeaderHeight());
  }

  public Dimension getPreferredSize(JComponent c) {
    long width = 0;
    Enumeration enumerationeration = header.getColumnModel().getColumns();
    while (enumerationeration.hasMoreElements()) {
      TableColumn aColumn = (TableColumn) enumerationeration.nextElement();
      width = width + aColumn.getPreferredWidth();
    }
    return createHeaderSize(width);
  }
}
