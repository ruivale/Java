package exp.tables;


import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class RendererDecorator implements TableCellRenderer {
  protected TableCellRenderer renderer;

  public RendererDecorator(TableCellRenderer renderer) {
    if (renderer == null)
      throw new IllegalArgumentException(
        "null renderers are not allowed");
    this.renderer = renderer;
  }
  public Component getTableCellRendererComponent(
    JTable table,
    Object value,
    boolean isSelected,
    boolean hasFocus,
    int row, int col) {
    return renderer.getTableCellRendererComponent(
             table,
             value,
             isSelected,
             hasFocus,
             row, col);
  }
}
