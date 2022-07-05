package exp.swing.transparentwin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.awt.event.WindowFocusListener;


/**
 *
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
public class TransparentBackground
    extends JComponent
    implements ComponentListener, WindowFocusListener, Runnable {

  private JFrame frame;
  private Image background;
  private long lastupdate = 0;
  public boolean refreshRequested = true;

  /**
   *
   * @param frame JFrame
   */
  public TransparentBackground(JFrame frame) {
    this.frame = frame;
    updateBackground();
    frame.addComponentListener(this);
    frame.addWindowFocusListener(this);
    new Thread(this).start();
  }

  /**
   *
   */
  public void updateBackground() {
    try {
      Robot rbt = new Robot();
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension dim = tk.getScreenSize();
      background = rbt.createScreenCapture(
          new Rectangle(0, 0, (int) dim.getWidth(),
                        (int) dim.getHeight()));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   *
   * @param g Graphics
   */
  public void paintComponent(Graphics g) {
    Point pos = this.getLocationOnScreen();
    Point offset = new Point( -pos.x, -pos.y);
    g.drawImage(background, offset.x, offset.y, null);
  }

  /**
   *
   */
  public void refresh() {
    if (frame.isVisible()) {
      repaint();
      refreshRequested = true;
      lastupdate = new Date().getTime();
    }
  }
  public void componentShown(ComponentEvent evt) {
    repaint();
  }

  public void componentResized(ComponentEvent evt) {
    repaint();
  }

  public void componentMoved(ComponentEvent evt) {
    repaint();
  }

  public void componentHidden(ComponentEvent evt) {}

  public void windowGainedFocus(WindowEvent evt) {
    refresh();
  }

  public void windowLostFocus(WindowEvent evt) {
    refresh();
  }


  public void run() {
    try {
      while (true) {
        Thread.sleep(50);
        long now = new Date().getTime();
        if (refreshRequested &&
            ((now - lastupdate) > 1000)) {
          if (frame.isVisible()) {
            Point location = frame.getLocation();
            frame.hide();
            updateBackground();
            frame.show();
            frame.setLocation(location);
            refresh();
          }
          lastupdate = now;
          refreshRequested = false;
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  public static void main(String[] args) {
    JFrame frame = new JFrame("Transparent Window");
    //frame.setUndecorated(true);

    TransparentBackground bg = new TransparentBackground(frame);
    //bg.snapBackground( );
    bg.setLayout(new BorderLayout( ));

   JPanel panel = new JPanel( ) {
        public void paintComponent(Graphics g) {
            g.setColor(Color.blue);
            Image img = new ImageIcon("src/exp/swing/transparentwin/test.gif").getImage( );
            g.drawImage(img,0,0,null);
        }
    };
    panel.setOpaque(false);

    bg.add("Center",panel);

    frame.getContentPane( ).add("Center",bg);
    frame.pack( );
    frame.setSize(200,200);
    frame.setLocation(500,500);
    frame.show( );



    /*
    JFrame frame = new JFrame("Transparent Window");
    TransparentBackground bg = new TransparentBackground(frame);
    bg.setLayout(new BorderLayout());
    JButton button = new JButton("This is a button");
    bg.add("North", button);
    JLabel label = new JLabel("This is a label");
    bg.add("South", label);
    frame.getContentPane().add("Center", bg);
    frame.pack();
    frame.setSize(150, 100);
    frame.show();
    */
  }
}
