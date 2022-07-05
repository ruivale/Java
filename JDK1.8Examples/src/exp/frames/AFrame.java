package exp.frames;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AFrame
    extends JFrame {


  JButton jButton1 = new JButton();
  Border border1;
  JLabel jLabel1 = new JLabel();

  public AFrame() {

    JLabel jLabel = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    jLabel.setToolTipText("<html><body>111111<p>222222</body></html>");
    Object[] buttonRowObjects = new Object[] {
        "Ok",
        "reload",
        "no"};
    String title = "title";
    int value = JOptionPane.showOptionDialog(
        null, // parentComponent
        jLabel, // message
        title, // title
        JOptionPane.DEFAULT_OPTION, // optionType
        JOptionPane.PLAIN_MESSAGE, // messageType
        null, // icon
        buttonRowObjects, // options
        buttonRowObjects[0]); // initialValue

    System.out.println("VALUE= " + value + ".");

    switch (value) {
      case 1:
        System.out.println("case 1");
        break;
      case JOptionPane.CLOSED_OPTION:
        System.out.println("OptionPane.CLOSED_OPTION");
        break;
      case 0:
        System.out.println("case 0");
        break;
    }

    try {
      //jbInit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    AFrame f = new AFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(200,200,300,200);
    f.setVisible(true);
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createCompoundBorder(BorderFactory.
                                                 createBevelBorder(BevelBorder.
        LOWERED, Color.white, Color.white, new Color(142, 142, 142),
        new Color(99, 99, 99)), BorderFactory.createEmptyBorder(0, 20, 0, 0));

    jButton1.setBounds(new Rectangle(132, 94, 148, 80));
    jButton1.setBorder(border1);
    jButton1.setText("jButton1");
    this.getContentPane().setLayout(null);
    jLabel1.setHorizontalTextPosition(SwingConstants.LEFT);
    jLabel1.setText("jLabel1");
    jLabel1.setBounds(new Rectangle(123, 244, 85, 20));
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jLabel1, null);
  }
}
