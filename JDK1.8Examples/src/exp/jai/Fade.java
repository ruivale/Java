package exp.jai;

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
import java.awt.image.*;
import java.net.*;
import java.io.*;
import java.util.Random;
import javax.imageio.*;
import javax.swing.*;

public class Fade {
  public static void main(String[] args) throws IOException {
    URL url0 = new URL("file:/d:/Rui/Private/_Cool/a1.jpg");
    URL url1 = new URL("file:/d:/Rui/Private/_Cool/a2.jpg");
    BufferedImage image0 = convert(ImageIO.read(url0));
    BufferedImage image1 = convert(ImageIO.read(url1));
    final Fade fade = new Fade(64, image0, image1);
    JFrame f = new JFrame("Fade");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    final JLabel label = new JLabel(new ImageIcon(image0));
    class MouseTimer
        extends MouseAdapter
        implements ActionListener {
      Timer timer = new Timer(50, this);

      public void actionPerformed(ActionEvent evt) {
        if (fade.nextStage()) {
          label.repaint();
        } else {
          timer.stop();
        }
      }

      public void mousePressed(MouseEvent evt) {
        label.removeMouseListener(this);
        timer.start();
      }
    }

    label.addMouseListener(new MouseTimer());
    f.getContentPane().add(label);
    f.pack();
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }

  private int stages, currentStage;
  int[] data0, data1, step;

  static BufferedImage convert(BufferedImage image) {
    int w = image.getWidth(), h = image.getHeight();
    BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = result.createGraphics();
    g.drawRenderedImage(image, null);
    g.dispose();
    return result;
  }

  public Fade(int stages, BufferedImage image0, BufferedImage image1) {
    this.stages = stages;
    currentStage = stages - 1;
    data0 = ( (DataBufferInt) (image0.getRaster().getDataBuffer())).getData();
    data1 = ( (DataBufferInt) (image1.getRaster().getDataBuffer())).getData();
    step = new int[data0.length];
    Random rnd = new Random();
    for (int i = 0, ub = step.length; i < ub; ++i) {
      step[i] = rnd.nextInt(currentStage);
    }
  }

  public boolean nextStage() {
    if (currentStage > 0) {
      --currentStage;
      for (int i = 0, ub = step.length; i < ub; ++i) {
        if (step[i] == currentStage) {
          data0[i] = data1[i];
        }
      }
      return true;
    } else {
      return false;
    }
  }
}
