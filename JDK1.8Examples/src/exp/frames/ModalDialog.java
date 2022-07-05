package exp.frames;


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
import javax.swing.*;


/**
 * <p>file :       ModalDialog.java</p>
 * <p>title: </p>
 * <p>description: </p>
 * <p>company: </p>
 *
 * @author $author$
 * @version $Revision$
 */
public class ModalDialog
    extends JFrame {
  //~ Static fields/initializers /////////////////////////////////////////////

  /**  */
  public static JFrame FRAME;

  //~ Constructors ///////////////////////////////////////////////////////////

  /**
   * Creates a new ModalDialog object.
   */
  public ModalDialog() {
    FRAME = this;

    setIconImage(new ImageIcon("D:\\SA\\Images\\BotaoSeta.jpg").getImage());

    setTitle("titulo");

    final JDialog dialog = new JDialog(this, true);

    //dialog.setModal(true);
    dialog.getContentPane()
          .setLayout(new BorderLayout());
    JButton b = new JButton("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
    dialog.getContentPane()
          .add(b, BorderLayout.CENTER);
    dialog.pack();

    JButton buttonExit = new JButton("DIIIIAAAAAALOOOOOOGGGGGG");
    buttonExit.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          dialog.setLocation(
            ((
              ModalDialog.FRAME.getLocation().x+
              (ModalDialog.FRAME.getWidth()/2)
            )-(dialog.getWidth()/2)),
            (
              ModalDialog.FRAME.getLocation().y+
              (ModalDialog.FRAME.getHeight()/2)
            )-(dialog.getHeight()/2));
          dialog.setVisible(true);

        }
      });

    getContentPane()
      .setLayout(new BorderLayout());
    getContentPane()
      .add(buttonExit, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);

  }

  //~ Methods ////////////////////////////////////////////////////////////////

  /**
   *
   *
   * @param args
   */
  public static void main(String[] args) {
    ModalDialog modalDialog1 = new ModalDialog();
  }
}
