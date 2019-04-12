/**
 * <p>
 * Classname: exp.swing.jcombobox.MyJCBFillWithAClass
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Apr 23, 2014, 11:23:23 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class MyJCBFillWithAClass extends JPanel{
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(MyJCBFillWithAClass.class.getName());

  private static final int N = 5;

 /**
  * The MyJCBFillWithAClass default constructor.
  */
  public MyJCBFillWithAClass(){
    setLayout(new BorderLayout());

    final JComboBox jcb = new JComboBox();
    for (int i = 0; i < N; i++) {
      jcb.addItem(new JCBItem((N-i), ""+i+"nt element."));
    }

    final JButton jb = new JButton("Obtain element.");
    jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          System.out.println("Selected item ID: "+((JCBItem)jcb.getSelectedItem()).getId());
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    add(jcb, BorderLayout.NORTH);
    add(jb, BorderLayout.SOUTH);

  }




  public static void main(final String[] args){
    final MyJCBFillWithAClass clazz = new MyJCBFillWithAClass();
    javax.swing.JFrame f = new javax.swing.JFrame("testing ...");
    f.getContentPane().setLayout(new java.awt.BorderLayout());
    f.getContentPane().add(clazz, java.awt.BorderLayout.CENTER);
    f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    f.pack();
    //f.setBounds(100,100,550,250);
    f.setVisible(true);
  }

}

class JCBItem {
  private int iId = -1;
  private String strName = "";
  public JCBItem(
      final int iId,
      final String strName){
    this.iId = iId;
    this.strName = strName;
  }
  public int getId(){
    return this.iId;
  }
  public String getName(){
    return this.strName;
  }
  public String toString(){
    return this.getName();
  }
}
