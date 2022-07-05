package exp.swing.jtables.cellrenderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author 2334
 */
public class RowFlash implements ActionListener, ChangeListener {

  FlashRenderer renderer;

  public void actionPerformed(ActionEvent e) {
    renderer.flashRow();
  }

  public void stateChanged(ChangeEvent e) {
    JSlider slider = (JSlider) e.getSource();
    int value = slider.getValue();
    renderer.setFlashRowSelection(value);
  }

  private JTable getTable() {
    int rows = 32, cols = 3;
    String[] colNames = {"column 1", "column 2", "column 3"};
    Object[][] data = new Object[rows][cols];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        data[row][col] = "item " + (row * cols + col + 1);
      }
    }
    DefaultTableModel model = new DefaultTableModel(data, colNames);
    JTable table = new JTable(model);
    renderer = new FlashRenderer(table);
    TableColumnModel colModel = table.getColumnModel();
    for (int j = 0; j < table.getColumnCount(); j++) {
      TableColumn col = colModel.getColumn(j);
      col.setCellRenderer(renderer);
    }
    return table;
  }

  private JPanel getControls(JTable table) {
    int min = 0;
    int max = table.getRowCount() - 1;
    int value = min;
    JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, value);
    slider.addChangeListener(this);
    slider.setSnapToTicks(true);
    slider.setPaintTicks(true);
    slider.setMinorTickSpacing(1);
    slider.setMajorTickSpacing(5);
    slider.setPaintLabels(true);
    JButton flash = new JButton("flash");
    flash.addActionListener(this);
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(2, 2, 2, 2);
    gbc.weightx = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    panel.add(slider, gbc);
    gbc.weightx = 0.0;
    gbc.fill = GridBagConstraints.NONE;
    panel.add(flash, gbc);
    return panel;
  }

  public static void main(String[] args) {
    RowFlash test = new RowFlash();
    JTable table = test.getTable();
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(new JScrollPane(table));
    f.getContentPane().add(test.getControls(table), "South");
    f.setSize(400, 400);
    f.setLocation(200, 200);
    f.setVisible(true);
  }
}


class FlashRenderer extends DefaultTableCellRenderer implements ActionListener {

  JTable table;
  int flashRowSelection;
  Color selectionColor;
  Timer flashTimer;

  public FlashRenderer(JTable table) {
    this.table = table;
    flashRowSelection = -1;
    selectionColor = Color.red;
    flashTimer = new Timer(500, this);

    flashTimer.setRepeats(false);
    //flashTimer.setRepeats(true);
    
    //flashTimer.setDelay(500);
  }

  public Component getTableCellRendererComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus,
                                                 int row,
                                                 int column) {

    super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);

    if (row == flashRowSelection && flashTimer.isRunning()) {
      setBackground(selectionColor);

    } else if (isSelected) {
      super.setForeground(table.getSelectionForeground());
      super.setBackground(table.getSelectionBackground());

    } else {
      super.setForeground(table.getForeground());
      super.setBackground(table.getBackground());
    }
    return this;
  }

  public void actionPerformed(ActionEvent e) {
    flashTimer.stop();
    table.repaint();
  }

  public void setFlashRowSelection(int frs) {
    flashRowSelection = frs;
  }

  public void flashRow() {
    if (!flashTimer.isRunning()) {
      flashTimer.start();
    }
    Rectangle r = table.getCellRect(flashRowSelection, 0, true);
    table.scrollRectToVisible(r);
    table.repaint();
  }
}
