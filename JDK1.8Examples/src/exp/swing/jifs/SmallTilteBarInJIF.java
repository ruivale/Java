package exp.swing.jifs;


import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JFrame;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SmallTilteBarInJIF
    extends JInternalFrame {
  /**
   *
   */
  public SmallTilteBarInJIF() {

    super("GOOD", false, true, false, true);

    ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).
      getNorthPane().setPreferredSize(
        new Dimension(getWidth() , 19) );

  }

  public static void main(String[] args) {
    SmallTilteBarInJIF n = new SmallTilteBarInJIF();
    JDesktopPane jdp = new JDesktopPane();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(jdp, BorderLayout.CENTER);

    jdp.add(n);
    n.setVisible(true);
    n.setTitle("AHAHAHAHA");
    n.setBounds(10, 10, 300, 200);
    f.setBounds(100, 100, 500, 300);
    f.setVisible(true);
  }
}
