package exp.comboboxes;


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
import javax.swing.event.*;
import javax.swing.plaf.basic.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class ComboBoxPopupMenuDemo
    extends JPanel {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  JComboBox         petList;

  /** .. */
  MyBasicComboPopup myPopup;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new ComboBoxPopupMenuDemo object.
   */
  public ComboBoxPopupMenuDemo() {
    super(new BorderLayout());

    final JLabel lab = new JLabel(" CLICK ");

    String[]     petStrings =
      {
        "Birdss", "Catss", "Dogss", "Rabbitss", "Pigss", "Fisshss", "Horsess",
        "Cowss", "Beess", "Sskunkss"
      };
    petList = new JComboBox(petStrings);
    petList.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          lab.setText("" + petList.getSelectedItem());
          myPopup.hide();
        }
      });
    myPopup = new MyBasicComboPopup(petList); // <---- a replacement for your standard JPopupMenu

    lab.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          myPopup.show(
            lab,
            e.getX(),
            e.getY());
        }
      });

    add("North", lab);
    setPreferredSize(new Dimension(200, 100));
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * DOCUMENT ME!
   *
   * @param s DOCUMENT ME!
   */
  public static void main(String[] s) {
    JFrame frame = new JFrame("ComboBoxDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new ComboBoxPopupMenuDemo());
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  //~ Inner Classes ////////////////////////////////////////////////////////////

  class MyBasicComboPopup
      extends javax.swing.plaf.basic.BasicComboPopup {
    public MyBasicComboPopup(JComboBox jcb) {
      super(jcb);
      System.out.println("LIST= " + this.getList().toString() + ".");
    }

    public void show(
      Component comp,
      int       x,
      int       y) {
      Dimension dim = this.getSize();

      if(dim.height>0) {
        super.show(comp, x, y - dim.height);
      } else {
        // first time ... then the size is equal to zero. The size is setted
        // only after the fisrt paint.
        super.show(comp, x, y);
        dim = this.getSize();

        super.show(comp, x, y - dim.height);
      }
    }

/*
   protected Rectangle computePopupBounds (
     int px,
     int py,
     int pw,
     int ph) {
     System.out.println(
       "computePopupBounds(" + px + "," + py + "," + pw + "," + ph + ")");
     return (new Rectangle(
       0,
       0,
       500,
       50));
   }
 */
  }
}
