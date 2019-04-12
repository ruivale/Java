/**
 * <p>
 * Classname: exp.swing.jcombobox.MyCBWithEqualValues
 * </p>
 *
 * <p>Copyright: Copyright (c) 2014 Efacec Engenharia e Sistemas, S.A.
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

package exp.swing.jcombobox;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Mar 4, 2015, 3:38:05 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class MyCBWithEqualValues extends JPanel{
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(MyCBWithEqualValues.class.getName());



 /**
  * The MyCBWithEqualValues default constructor.
  */
  public MyCBWithEqualValues(){
    setLayout(new BorderLayout());

    final DefaultComboBoxModel<TlcStringItem> model = new DefaultComboBoxModel<>();
    final JComboBox jcb = new JComboBox(model);
    model.addElement(new TlcStringItem("hoje"));
    model.addElement(new TlcStringItem("hoje"));
    model.addElement(new TlcStringItem("hoje 2"));
    model.addElement(new TlcStringItem("hoje"));
    model.addElement(new TlcStringItem("hoje 3"));
    model.addElement(new TlcStringItem("hoje"));


    final JButton jb = new JButton("Obtain element.");
    jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          System.out.println("Selected index: "+jcb.getSelectedIndex()+" item: "+jcb.getSelectedItem());

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    add(jcb, BorderLayout.NORTH);
    add(jb, BorderLayout.SOUTH);
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("MyCBWithEqualValues").append("").toString();
  }

  public static void main(final String[] args){
    final MyCBWithEqualValues clazz = new MyCBWithEqualValues();
    JFrame f = new JFrame("MyCBWithEqualValues");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(clazz);
    f.pack();
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
}

class TlcStringItem {

  private String value;

  /**
   *
   * @param value
   */
  public TlcStringItem(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}