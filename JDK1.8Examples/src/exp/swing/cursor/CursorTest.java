package exp.swing.cursor;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class CursorTest {

  public static void main(String args[]) {
    new CursorTestFrame();
  }
}

class CursorTestFrame
    extends JFrame
    implements ActionListener {
  JButton set = new JButton("Set Cursor");
  Component gp;

  CursorTestFrame() {
    super();

    /* Components should be added to the container's content pane */
    Container cp = getContentPane();

    /* Get the frame's glass pane */
    gp = getGlassPane();

    /* Add mouse listener to glass pane */
    gp.addMouseListener(new MouseAdapter() {});

    /* Add the window listener */
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        dispose();
        System.exit(0);
      }
    });

    set.addActionListener(this);

    cp.add(BorderLayout.NORTH, set);

    /* Size the frame */
    setSize(200, 200);

    /* Center the frame */
    Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle frameDim = getBounds();
    setLocation( (screenDim.width - frameDim.width) / 2,
                (screenDim.height - frameDim.height) / 2);

    /* Show the frame */
    setVisible(true);
  }

  public void actionPerformed(ActionEvent evt) {
    Object obj = evt.getSource();

    if (obj == set) {
      gp.setVisible(true);
      gp.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }
  }
}
