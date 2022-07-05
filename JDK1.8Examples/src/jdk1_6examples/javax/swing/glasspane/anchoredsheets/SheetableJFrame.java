/**
 * <p>
 * Classname: package jdk1_6examples.javax.swing.glasspane.anchoredsheets.SheetableJFrame
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
package jdk1_6examples.javax.swing.glasspane.anchoredsheets;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - rui dot vale at efacec dot pt
 * @version $Revision: 1.1 $
 */
public class SheetableJFrame
    extends JFrame {

  JComponent sheet;
  JPanel glass;
  /** This class LOGGER */
  private static final Logger LOGGER =
      Logger.getLogger(SheetableJFrame.class.getName());

  public SheetableJFrame(String name) {
    super(name);
    glass = (JPanel) getGlassPane();
  }

  public JComponent showJDialogAsSheet(JDialog dialog) {
    sheet = (JComponent) dialog.getContentPane();
    sheet.setBackground(Color.red);
    glass.setLayout(new GridBagLayout());
    sheet.setBorder(new LineBorder(Color.black, 1));
    glass.removeAll();
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.NORTH;
    glass.add(sheet, gbc);
    gbc.gridy = 1;
    gbc.weighty = Integer.MAX_VALUE;
    glass.add(Box.createGlue(), gbc);
    glass.setVisible(true);
    return sheet;
  }

  public void hideSheet() {
    glass.setVisible(false);
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("SheetableJFrame").append("").toString();
  }

  public static void main(final String[] args) {
    final SheetableJFrame clazz = new SheetableJFrame("name");
  }
}

