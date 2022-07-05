package exp.layered;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

class JPanelRenderer
    extends JPanel {
  BufferedImage image_off;
  Graphics graph;
  int x = 5;
  int y = 5;

  public JPanelRenderer(GridLayout mylayout) {
    super();
    image_off = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (graph == null) {
      graph = image_off.getGraphics();
    }

    image_off.setRGB(x, y, Color.red.getRGB());
    g.drawImage(image_off, 0, 0, this);
  }

  public void updateXY() {
    x = (int) (Math.random() * 100);
    y = (int) (Math.random() * 100);
    repaint();
  }
}

class essai
    extends JComponent
    implements Runnable {

  Thread my_app;
  JPanelRenderer pane;

  public static void main(String s[]) {
    JFrame frame = new JFrame();
    JDesktopPane p = new JDesktopPane();
    p.setSize(800, 600);
    frame.getContentPane().add(p);

    JInternalFrame f = new JInternalFrame();
    f.setSize(200, 200);
    f.validate();
    f.setVisible(true);
    p.add(f);

    JInternalFrame f2 = new JInternalFrame();
    f2.setSize(200, 200);
    f2.validate();
    f2.setVisible(true);
    p.add(f2);

    essai baba = new essai();
    baba.init_pane(f);
    frame.setSize(new Dimension(900, 700));
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    baba.start_thread();
  }

  public void start_thread() {
    my_app = new Thread(this);
    my_app.start();
  }

  public void init_pane(JInternalFrame frame) {
    pane = new JPanelRenderer(new GridLayout(1, 1));
    pane.setOpaque(true);
    pane.setLayout(null);
    frame.getContentPane().add(pane);
  }

  public void run() {
    while (true) {
      pane.updateXY();
      try {
        Thread.sleep(10);
      } catch (Exception e) {
      }
    }
  }
}
