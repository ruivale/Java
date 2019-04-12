package exp.tables;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;





public class SimpleTable extends JPanel {

  public SimpleTable(JFrame f) {

    setLayout(new BorderLayout());
    Object[] header = {"SSSSSsssssssss",new ImageIcon("c:\\d\\Projects\\_Experiences\\exp\\arrow.png")};

//DefaultTableModel dtm = new DefaultTableModel(header, 0);
    CustomModel dtm = new CustomModel(header);
    JTable table = new JTable(dtm);

    dtm.insertRow(0, header);


//    final Object[] columnNames = new Object[]{"Col1", "Col2"};
//    final Object[][] rowData = new Object[][]{
//      {"SSSSSsssssssss",new ImageIcon("D:\\Projects\\exp\\arrow.png")}
//    };
//    JTable table = new JTable(rowData, columnNames);



    //table.addMouseListener(new com.ent.PInt.windows.EntTableMouseAdapter(f, table));

    JScrollPane sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(100,50));
    sp.setViewportBorder(BorderFactory.createLoweredBevelBorder());

    add(sp, BorderLayout.CENTER);

  }
  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setTitle("AKSLOA");

    SimpleTable simpleTable1 = new SimpleTable(f);

    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(simpleTable1, BorderLayout.CENTER);
    f.pack();
    f.setVisible(true);
  }
}

class CustomModel
    extends DefaultTableModel {
  //~ Constructors ///////////////////////////////////////////////////////////

  /**
   * Creates a new CustomModel object.
   *
   * @param data
   * @param columnNames
   */
  public CustomModel(
    Object[][] data,
    Object[]   columnNames) {
    super(data, columnNames);
  }

  /**
   * Creates a new CustomModel object.
   *
   * @param columnNames
   */
  public CustomModel(Object[] columnNames) {
    super(columnNames, 0);
  }

  //~ Methods ////////////////////////////////////////////////////////////////


  /**
   *
   *
   * @param row
   * @param col
   *
   * @return
   */
  public boolean isCellEditable(
    int row,
    int col) {
    return false;
  }

  /**
   *
   *
   * @param col
   *
   * @return
   */
  public Class getColumnClass(int col) {
    // dataVector is a protected member of DefaultTableModel
    Vector v = (Vector)dataVector.elementAt(0);

    return v.elementAt(col)
            .getClass();
  }
}
