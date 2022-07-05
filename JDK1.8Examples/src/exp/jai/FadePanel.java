package exp.jai;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

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
import javax.swing.*;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class FadePanel
    extends JPanel {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private BufferedImage tmp = null;

  /** .. */
  private Thread          fadeThread = new FadeThread();

  /** .. */
  private BufferedImage[] a = null;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new FadePanel object.
   *
   * @param b  Insert doc ...
   */
  public FadePanel(BufferedImage[] b) {
    int w = 0;
    int h = 0;

    for(int i = 0; i<b.length; i++) {
      if(b[i].getWidth()>w) {
        w = b[i].getWidth();
      }

      if(b[i].getHeight()>h) {
        h = b[i].getHeight();
      }
    }

    a = new BufferedImage[b.length];

    Color bg = Color.WHITE; // background color

    for(int i = 0; i<a.length; i++) {
      a[i] = cloneBufferedImage(b[i], w, h, bg);
    }

    tmp = cloneBufferedImage(b[0], w, h, bg); // start showing with b[0]
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   *
   * @throws Throwable  Insert doc ...
   */
  public static void main(String[] args)
      throws Throwable {
    BufferedImage   a1 =
      javax.imageio.ImageIO.read(
        new java.io.File("d:/Rui/Private/_Cool/a1.jpg"));
    BufferedImage   a2 =
      javax.imageio.ImageIO.read(
        new java.io.File("d:/Rui/Private/_Cool/a2.jpg"));
    BufferedImage   a3 =
      javax.imageio.ImageIO.read(
        new java.io.File("d:/Rui/Private/_Cool/a3.jpg"));

    BufferedImage[] a = new BufferedImage[]{ a1, a2, a3 };

    final FadePanel fp = new FadePanel(a);

    JFrame          f = new JFrame();

    JPanel          p = new JPanel(new BorderLayout());
    p.add(fp, BorderLayout.CENTER);

    JPanel  buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton startButton = new JButton("Start");
    buttonPanel.add(startButton);

    startButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          fp.start();
        }
      });

    p.add(buttonPanel, BorderLayout.NORTH);

    f.setContentPane(p);

    f.setSize(400, 400);
    f.show();
  }

  /**
   * Insert doc ...
   *
   * @param g  Insert doc ...
   */
  public void paint(Graphics g) {
    super.paint(g);

    if(tmp!=null) {
      g.drawImage(tmp, 0, 0, null);
    }
  }

  /**
   * Insert doc ...
   */
  public void start() {
    fadeThread.start();
  }

  /**
   * Insert doc ...
   *
   * @param bi  Insert doc ...
   * @param w  Insert doc ...
   * @param h  Insert doc ...
   * @param bg  Insert doc ...
   *
   * @return  Insert doc ...
   */
  private BufferedImage cloneBufferedImage(
    BufferedImage bi,
    int           w,
    int           h,
    Color         bg) {
    int           dx = (w - bi.getWidth()) / 2;
    int           dy = (h - bi.getHeight()) / 2;

    BufferedImage b = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Graphics      g = b.createGraphics();

    g.setColor(bg);
    g.fillRect(0, 0, w, h);

    g.drawImage(bi, dx, dy, this);

    return b;
  }

  //~ Inner Classes ////////////////////////////////////////////////////////////

  private class FadeThread
      extends Thread {
    private int delay = 1200;

    public FadeThread() {
    }

    public void run() {
      try {
        BufferedImage tmp = FadePanel.this.tmp;
        int           w = tmp.getWidth();
        int           h = tmp.getHeight();

        int           curr = 1; // already showing a[0]

        int           size   = w * h;
        int[]         marker = new int[size];

        int           change = (w * h) / 10;

        int           delay2 = 120;

        while(true) {
          for(int i = 0; i<marker.length; i++) {
            marker[i] = i;
          }

          BufferedImage to = FadePanel.this.a[curr++];

          if(curr==FadePanel.this.a.length) {
            curr = 0;
          }

          while(size!=0) {
            int a = (size<change)
              ? size
              : change;

            for(int i = 0; i<a; i++) {
              int index = (int)(Math.random() * size);
              int pixel = marker[index];
              int x     = pixel % w;
              int y     = pixel / w;

              marker[index] = marker[--size];

              int rgb = to.getRGB(x, y);
              tmp.setRGB(x, y, rgb);
            }

            FadePanel.this.repaint();
            Thread.sleep(delay2);
          }

          size = marker.length;
          Thread.sleep(delay);
        }
      } catch(InterruptedException e) {
        ;
      }

      ;
    }
  }
}
