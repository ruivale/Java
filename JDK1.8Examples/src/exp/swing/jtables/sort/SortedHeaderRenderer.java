package exp.swing.jtables.sort;


import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class SortedHeaderRenderer
    implements TableCellRenderer {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private JLabel jLabel = new JLabel();

  /** .. */
  private JPanel jPanel = new JPanel();

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new SorterHeaderRenderer object.
   */
  public SortedHeaderRenderer() {
    javax.swing.border.Border raisedBorder =
      BorderFactory.createRaisedBevelBorder();
    this.jLabel.setBorder(raisedBorder);
    this.jPanel.setLayout(new BorderLayout());
    this.jPanel.add(this.jLabel, BorderLayout.CENTER);
  }

  //~ Methods //////////////////////////////////////////////////////////////////
  /**
   *
   * @return JLabel
   */
  protected JLabel getLabel(){
    return this.jLabel;
  }

  /**
   *
   * @return JLabel
   */
  protected JPanel getPanel(){
    return this.jPanel;
  }

  /**
   * Insert doc ...
   *
   * @param table Insert doc ...
   * @param value Insert doc ...
   * @param isSelected Insert doc ...
   * @param hasFocus Insert doc ...
   * @param row Insert doc ...
   * @param col Insert doc ...
   *
   * @return Insert doc ...
   */
  public Component getTableCellRendererComponent(
    final JTable  table,
    final Object  value,
    final boolean isSelected,
    final boolean hasFocus,
    final int     row,
    final int     col) {

    this.jLabel.setText("" + value);

    return this.jPanel;
  }
}
