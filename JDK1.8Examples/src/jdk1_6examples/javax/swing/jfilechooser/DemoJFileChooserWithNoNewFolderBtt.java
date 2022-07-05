/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jfilechooser.DemoJFileChooserWithNoNewFolderBtt
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

package jdk1_6examples.javax.swing.jfilechooser;




import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;


public class DemoJFileChooserWithNoNewFolderBtt extends JPanel
   implements ActionListener {
   JButton go;

   JFileChooser chooser;
   String choosertitle;

  public DemoJFileChooserWithNoNewFolderBtt() {
    go = new JButton("Do it");
    go.addActionListener(this);
    add(go);
   }

  public void actionPerformed(ActionEvent e) {
    int result;

    chooser = new JFileChooser();
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle(choosertitle);
    //chooser.setControlButtonsAreShown(false);


    disableNewFolderButton(chooser);

    //
    int rc = chooser.showOpenDialog(this);
    if (rc == JFileChooser.APPROVE_OPTION) {
      System.out.println("getCurrentDirectory(): "
         +  chooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : "
         +  chooser.getSelectedFile());
    }
    else {
      System.out.println("No Selection!");
    }
  }


  public void disableNewFolderButton( Container c ) {
    int len = c.getComponentCount();
    for (int i = 0; i < len; i++) {
      Component comp = c.getComponent(i);
      if (comp instanceof JButton) {
        JButton b = (JButton)comp;
        Icon icon = b.getIcon();
        if (icon != null
             && icon == UIManager.getIcon("FileChooser.newFolderIcon"))
           b.setEnabled(false);
        }
      else if (comp instanceof Container) {
        disableNewFolderButton((Container)comp);
      }
    }
  }

  public Dimension getPreferredSize(){
    return new Dimension(200, 200);
  }

  public static void main(String s[]) {
    JFrame frame = new JFrame("");
    DemoJFileChooserWithNoNewFolderBtt panel = new DemoJFileChooserWithNoNewFolderBtt();
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      }
    );
    frame.getContentPane().add(panel,"Center");
    frame.setSize(panel.getPreferredSize());
    frame.setVisible(true);
  }
}
