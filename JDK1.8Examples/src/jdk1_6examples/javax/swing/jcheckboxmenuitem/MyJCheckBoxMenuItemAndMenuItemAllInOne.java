/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jcheckboxmenuitem.MyJCheckBoxMenuItemAndMenuItemAllInOne
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

package jdk1_6examples.javax.swing.jcheckboxmenuitem;


import java.awt.BorderLayout;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class MyJCheckBoxMenuItemAndMenuItemAllInOne extends JCheckBoxMenuItem {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(MyJCheckBoxMenuItemAndMenuItemAllInOne.class.getName());


 /**
  * The MyJCheckBoxMenuItemAndMenuItemAllInOne default constuctor.
  */
  public MyJCheckBoxMenuItemAndMenuItemAllInOne(final String strText){
    super(strText);
  }


  public static void main(final String[] args){
    final MyJCheckBoxMenuItemAndMenuItemAllInOne c =
        new MyJCheckBoxMenuItemAndMenuItemAllInOne("bbbbbbb");


    JMenuBar jmb = new JMenuBar();
    JMenu jm = new JMenu("aaaa");
    jmb.add(jm);
    jm.add(c);

    JFrame f = new JFrame();
    f.setJMenuBar(jmb);

    f.setTitle("add station");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(new JButton("ahahahah"), BorderLayout.CENTER);
    f.pack(); //setBounds(100, 100, 200, 100);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

  }
}
