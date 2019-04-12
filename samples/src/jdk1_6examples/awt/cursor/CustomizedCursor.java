/**
 * <p>
 * Classname: package jdk1_6examples.awt.cursor.CustomizedCursor
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
package jdk1_6examples.awt.cursor;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
//import org.openide.modules.ModuleInstall;
//import org.openide.windows.WindowManager;


public class CustomizedCursor /* extends ModuleInstall */ {

  public static void main(String[] s) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        //Create the cursor:
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon shishaImageIcon = new ImageIcon("c:/temp/LabelTextImg.jpg");
        Image shishaImage = shishaImageIcon.getImage();
        Cursor shishaCursor = tk.createCustomCursor(shishaImage, new Point(10,
            10), "Shisha");
        //Use the cursor in the main window:
        JFrame frame = new JFrame("CustomCursor");
        //JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
        frame.setCursor(shishaCursor);
        frame.setBounds(100,100,750,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
      }
    });
  }
}