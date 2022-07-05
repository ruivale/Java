package exp.tables;


import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;

public class SortRendererDecorator implements TableCellRenderer {
  TableCellRenderer realRenderer;
  JPanel panel;

  public SortRendererDecorator(TableCellRenderer r) {
    realRenderer = r;
  }
  public Component getTableCellRendererComponent(
    JTable table,
    Object value,
    boolean isSelected,
    boolean hasFocus,
    int row, int col) {
    Component c = realRenderer.getTableCellRendererComponent(
                    table,
                    value,
                    isSelected,
                    hasFocus,
                    row, col);
    embellishComponent(c);

    return panel;
  }
  private void embellishComponent(Component c) {
    if (panel == null) {
      panel = new JPanel();
      panel.setLayout(new BorderLayout());
      panel.add(c, BorderLayout.CENTER);
      panel.add(new JLabel("s"), BorderLayout.EAST);
      panel.add(new JLabel("s"), BorderLayout.WEST);
    }
  }
}
