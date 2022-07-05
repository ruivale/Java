/**
 * <p>
 * Classname: exp.swing.jsplitpanes.JSplitPaneWithUnresizableComps
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

package exp.swing.jsplitpanes;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Feb 1, 2012, 1:05:20 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class JSplitPaneWithUnresizableComps extends JPanel {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(JSplitPaneWithUnresizableComps.class.getName());


  private JSplitPane jSplitPane = new JSplitPane();

 /**
  * The JSplitPaneWithUnresizableComps default constuctor.
  */
  public JSplitPaneWithUnresizableComps(){
    this.setLayout(new BorderLayout());
    this.add(this.jSplitPane, BorderLayout.CENTER);

    final Dimension dim = new Dimension(200,200);
    final JPanel jp1 = new JPanel();
    jp1.setPreferredSize(dim);
    jp1.setMaximumSize(dim);
    jp1.setMinimumSize(dim);
    final JPanel jp2 = new JPanel();
    jp2.setPreferredSize(dim);
    jp2.setMaximumSize(dim);
    jp2.setMinimumSize(dim);

    this.jSplitPane.setLeftComponent(jp1);
    this.jSplitPane.setRightComponent(jp2);
    this.jSplitPane.setEnabled(false);

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("JSplitPaneWithUnresizableComps").append("").toString();
  }

  public static void main(final String[] args){
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        final JSplitPaneWithUnresizableComps clazz = new JSplitPaneWithUnresizableComps();
        JFrame f = new JFrame();
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(clazz);
        //f.setBounds(100, 100, 450, 450);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
    });
  }
}
