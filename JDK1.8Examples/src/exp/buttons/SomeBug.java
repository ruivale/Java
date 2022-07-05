package exp.buttons;


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
 * Copyright: Copyright (c)
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class SomeBug {
  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param argv  Insert doc ...
   */
  public static void main(String[] argv) {
    JFrame    frm = new JFrame();
    JComboBox box = new JComboBox();
    box.setEditable(true);
    frm.getContentPane()
       .setLayout(new BoxLayout(
        frm.getContentPane(),
        BoxLayout.Y_AXIS));
    frm.getContentPane()
       .add(box);
    box.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          System.exit(0);
        }
      });
    frm.getContentPane()
       .add(new JCheckBox("Click"));
    frm.pack();
    frm.setVisible(true);
  }
}
