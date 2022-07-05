package exp.borders;


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
import javax.swing.border.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class BorderExamples extends JFrame {
  //~ Instance fields ----------------------------------------------------------

  JButton b1;
  JButton b2;
  JButton b3;
  JButton b4;
  JLabel  b5;
  JPanel  p;

  //~ Constructors -------------------------------------------------------------

  /**
   * Creates a new BorderExamples object.
   */
  public BorderExamples () {
    b1 = new JButton("Bevel Border");
    b1.setBorder(BorderFactory.createRaisedBevelBorder());
    b2 = new JButton("Etched Border");
    b2.setBorder(BorderFactory.createEtchedBorder());
    b3 = new JButton("Line Border");
    b3.setBorder(BorderFactory.createLineBorder(Color.RED));
    b4 = new JButton("Lowered Bevel Border");
    b4.setBorder(BorderFactory.createLoweredBevelBorder());
    b5 = new JLabel("");
    b5.setBorder(BorderFactory.createTitledBorder("Titled Label"));

    p = new JPanel();
    p.setLayout(new GridLayout(
        3,
        2));
    p.add(b1);
    p.add(b2);
    p.add(b3);
    p.add(b4);
    p.add(b5);

    getContentPane()
      .add(
      p,
      BorderLayout.CENTER);
    setBounds(
      300,
      300,
      500,
      500);
    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  //~ Methods ------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   */
  public static void main (String[] args) {
    BorderExamples be = new BorderExamples();
  }
}
