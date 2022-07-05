/**
 * <p>
 * Classname: package jdk1_6examples.javax.swing.dnd.MyOwnDnDJTable
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.javax.swing.dnd;


import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - rui dot vale at efacec dot pt
 * @version $Revision: 1.1 $
 */
public class MyOwnDnDJTable extends JPanel {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(MyOwnDnDJTable.class.getName());

  private final JTable jTable = new JTable();
  private final JTextField jTextField = new JTextField();

 /**
  * The MyOwnDnDJTable default constuctor.
  */
  public MyOwnDnDJTable(){
    this.setLayout(new BorderLayout(10,20));

    this.jTable.getSelectionModel().setSelectionMode(
        ListSelectionModel.SINGLE_SELECTION);
    this.jTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {"Mon1", "Alarm1", "Camera1",},
        {"Mon2", "Alarm2", "Camera2",},
        {"Mon3", "Alarm3", "Camera3",},
        {"-", "Alarm4", "Camera4"}
      },
      new String []{"Mon","Alarm","Camera"}
      ){
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    });

    this.add(this.jTable, BorderLayout.CENTER);
    this.add(this.jTextField, BorderLayout.NORTH);
    
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("MyOwnDnDJTable").append("").toString();
  }
  
  public static void main(final String[] args){
    final MyOwnDnDJTable clazz = new MyOwnDnDJTable();
    JFrame frame = new JFrame();
    frame.getContentPane().setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(clazz);
    frame.setBounds(800, 800, 350, 250);
    frame.setVisible(true);
  }
}
