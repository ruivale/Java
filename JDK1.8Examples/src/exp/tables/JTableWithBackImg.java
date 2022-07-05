package exp.tables;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


public class JTableWithBackImg extends JFrame{

  JTable imTable = new JTable( 10, 5 ){


  public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

    Component c = super.prepareRenderer( renderer, row, column);

    // We want renderer component to be
    //transparent so background image is visible
    if ( c instanceof JComponent )
      ((JComponent)c).setOpaque(false);

    return c;
  }

  ImageIcon image = new ImageIcon( "D:\\Projects\\exp\\beans.jpg" );
  public void paint( Graphics g ) {
    // tile the background image
    Dimension d = getSize();
    for ( int x = 0; x < d.width; x += image.getIconWidth() )
      for ( int y = 0; y < d.height; y += image.getIconHeight() )
        g.drawImage( image.getImage(), x, y, null, null );

    // Now let the paint do its usual work
    super.paint(g);
  }
  };




  public JTableWithBackImg() {



    //make the table transparent
    imTable.setOpaque(false);

    JScrollPane jsp = new JScrollPane(imTable);
    getContentPane().add(jsp);

    pack();
//    show();
  }







  public static void main(String[] args) {
    //JFrame frame = new JFrame("Table Example");
    JTableWithBackImg frame = new JTableWithBackImg();
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          Window w = e.getWindow();
          w.setVisible(false);
          w.dispose();
          System.exit(0);
        }
      }
    );
    frame.setVisible(true);
  }




}

