package exp.frames;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MyJInternalInputFrame extends JPanel {

  public MyJInternalInputFrame() {

    final JTextField jTextField = new JTextField("Macro 1");
    jTextField.setPreferredSize(new Dimension(100, 25));
    JLabel jLabel = new JLabel("Nome: ");
    JLabel jLabel_ = new JLabel("Nomeaddadsdsds sdjksjkdjk skdj ?");
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(jLabel, BorderLayout.WEST);
    panel.add(jTextField, BorderLayout.SOUTH);


    final JButton bOk = new JButton("Ok");
    bOk.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          System.out.println("o texto do butoon OK: "+bOk.getText()+".");
        }
      }
    );
    JButton bCancel = new JButton("Cancelar");
//    final JButton[] buttonRowObjects = new JButton[] {
  //                               bOk, bCancel};
    final Object[] buttonRowObjects = new Object[] {
                                 "Ok", "Cancel"};

    String title = "T í t u l o ";

/*    jTextField.addKeyListener(
      new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextField.transferFocus();
          }
        }
      }
    );
*/

/*
    SwingUtilities.invokeLater(
      new Runnable() {
        public void run() {
          try{Thread.sleep(3000);}catch(Exception e){}
          jTextField.grabFocus();
        }
      }
    );
*/
    int value = JOptionPane.showOptionDialog(
                  null,  // parentComponent
                  /*jLabel_*/panel,              // message
                  title,                         // title
                  JOptionPane.DEFAULT_OPTION,    // optionType
                  JOptionPane.PLAIN_MESSAGE,     // messageType
                  null,                          // icon
                  buttonRowObjects,              // options
                  buttonRowObjects[0]);          // initialValue

System.out.println("VALUE: "+value+".");

    switch (value) {
    case 1:                                      // Cancel

    case JOptionPane.CLOSED_OPTION:              // Window closing

      System.out.println("CLOSED_OPTION");
//      return ;
    }

    String name = jTextField.getText();




/*
    Object valueInput = JOptionPane.showInputDialog(null, panel, title, 0, null, buttonRowObjects,jTextField);



    switch (valueInput) {
    case 1:                                      // Cancel

    case JOptionPane.CLOSED_OPTION:              // Window closing

      System.out.println("CLOSED_OPTION valueInput");
      System.exit(0);
      return ;
    }
*/




      System.exit(0);



  }
  public static void main(String[] args) {
    MyJInternalInputFrame myJInternalInputFrame1 = new MyJInternalInputFrame();
  }
}
