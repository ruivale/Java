package exp.swing.jradiobuttons;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JRadioButtonsinTitledBorder extends JPanel{
  FlowLayout flowLayout1 = new FlowLayout();
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
  public JRadioButtonsinTitledBorder() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jRadioButton1.setText("1");
    flowLayout1.setAlignment(FlowLayout.LEFT);
    this.setLayout(flowLayout1);
    jRadioButton2.setText("2");
    this.add(jRadioButton1, null);
    this.add(jRadioButton2, null);

    TitledBorder tb = new TitledBorder("Imagem");
    this.setBorder(tb);
  }
  public static void main(String[] args) {
    JRadioButtonsinTitledBorder JRadioButtonsinTitledBorder1 = new JRadioButtonsinTitledBorder();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(JRadioButtonsinTitledBorder1, BorderLayout.CENTER);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
  }

}
