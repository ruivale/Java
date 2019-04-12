package exp.dnd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.net.*;

public class DragImage extends JFrame {
  private boolean drawEnabled = false;
  private int positionX, positionY;
  private Image image;

  public DragImage() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(new Dimension(400, 300));
    this.setTitle("Drag Image");
    MouseInputListener listener = new MouseInputAdapter() {
      public void mousePressed(MouseEvent e) {
        positionX = e.getX();
        positionY = e.getY();
        drawEnabled = true;
        repaint();
      }
      public void mouseReleased(MouseEvent e) {
        positionX = 0;
        positionY = 0;
        drawEnabled = false;
        repaint();
      }
      public void mouseDragged(MouseEvent e) {
        positionX = e.getX();
        positionY = e.getY();
        repaint();
      }
      public void mouseClicked(MouseEvent e) {
        JFileChooser chooser = new JFileChooser();
        int returnValue = chooser.showOpenDialog(DragImage.this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File file = chooser.getSelectedFile();
          try {
            image = Toolkit.getDefaultToolkit().createImage(file.toURL());
          } catch (MalformedURLException ex) {
          }
        }
      }
    };
    addMouseListener(listener);
    addMouseMotionListener(listener);
  }

  public void paint(Graphics g) {
    super.paint(g);
    if (drawEnabled & (image != null)) {
      g.drawImage(image, positionX, positionY, null);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new DragImage();
    frame.show();
  }
}
