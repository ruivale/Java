/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jcombobox.JComboBoxEvtsTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.javax.swing.jcombobox;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 3/Jan/2011, 16:27:46
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class JComboBoxEvtsTests  extends javax.swing.JFrame {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(JComboBoxEvtsTests.class.getName());


 /**
  * The JComboBoxEvtsTests default constuctor.
  */
  public JComboBoxEvtsTests(){
    final JComboBox jcb = new JComboBox(new String[]{"", "1", "2"});
    jcb.setBounds(10,10,100,25);
    
    this.setLayout(null);
    this.add(jcb);
//
//    jcb.addActionListener(new ActionListener() {
//
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        System.out.println(e.toString());
//      }
//    });

    jcb.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        System.out.println(e.toString());
      }
    });

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  @Override
  public String toString(){
    return new StringBuffer("JComboBoxEvtsTests").append("").toString();
  }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
            public void run() {
                new JComboBoxEvtsTests().setVisible(true);
            }
        });
    }

}
