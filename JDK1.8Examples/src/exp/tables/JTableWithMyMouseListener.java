package exp.tables;


import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.io.FileReader;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class JTableWithMyMouseListener extends java.awt.event.MouseAdapter {
  //~ Instance fields ////////////////////////////////////////////////////////
  JTable table;
  int    nbrOfColumns;

  //~ Constructors ///////////////////////////////////////////////////////////
  /**
   * Creates a new EntTableMouseAdapter object.
   *
   * @param table
   */
  public JTableWithMyMouseListener(JTable table) {
    this.table          = table;
    this.nbrOfColumns   = table.getColumnCount();

    this.table.getTableHeader()
              .setReorderingAllowed(false);
    this.table.getSelectionModel()
              .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }

  //~ Methods ////////////////////////////////////////////////////////////////
  /**
   *
   *
   * @param e
   */
  public void mouseClicked(MouseEvent e) {
    if(e.getModifiers()==InputEvent.BUTTON1_MASK) {
      if(e.getClickCount() > 1) {

        int                selectedRow    = this.table.getSelectedRow();
        int                nbrOfColumns   = this.table.getColumnCount();

        JTextPane          tp             = new JTextPane();
        StyledDocument     document       = (StyledDocument)tp.getDocument();
        SimpleAttributeSet sas            = new SimpleAttributeSet();
        StringBuffer       strSelectedRow = new StringBuffer("");
        int                nbrOfChars     = 0;
        String             strColumnName  = "";
        String             strColumnValue = "";

        tp.setEditable(false);

        StyleConstants.setBold(sas, true);
        int[][] intStartEndIndexes = new int[nbrOfColumns][2];

        for(int i = 0; i<nbrOfColumns; i++) {
          strColumnName    = this.table.getColumnName(i);
          strColumnValue =
            (String)this.table.getModel()
                              .getValueAt(selectedRow, i);
          strSelectedRow.append(strColumnName+": "+strColumnValue+"\n");

          intStartEndIndexes[i][0] = nbrOfChars;
          intStartEndIndexes[i][1] = strColumnName.length();

          nbrOfChars += ((strColumnName.length()+strColumnValue.length())+3);
        }

        tp.setText(strSelectedRow.toString());

        for(int i = 0; i<nbrOfColumns; i++) {
          document.setCharacterAttributes(
            intStartEndIndexes[i][0],
            intStartEndIndexes[i][1],
            sas,
            false);
        }

        JPanel      panelDescr   = new JPanel(new BorderLayout());
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.getViewport()
                    .add(tp, null);
        jScrollPane1.setPreferredSize(new Dimension(400, 200));

        panelDescr.add(jScrollPane1);
        panelDescr.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        final JDialog dialog =
          new JDialog(new JFrame(),true);


        JButton       buttonExit = new JButton("Exit");
        /*
        buttonExit.addActionListener(
          new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              dialog.dispose();
            }
          });
        */
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel     panelExit = new JPanel(flowLayout);
        panelExit.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelExit.add(buttonExit);

        dialog.addWindowListener(
          new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              dialog.dispose();
            }
          });

        dialog.getContentPane()
              .setLayout(new BorderLayout());
        dialog.getContentPane()
              .add(panelDescr, BorderLayout.CENTER);
        dialog.getContentPane()
              .add(panelExit, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setResizable(false);
//        dialog.setLocationRelativeTo(
//          com.ent.PInt.windows.FaultApplication.getParent());
        dialog.setVisible(true);

      }
    }
  }
}
