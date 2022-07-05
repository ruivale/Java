package exp.koolefects;


/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following
 *   disclaimer in the documentation and/or other materials
 *   provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY
 * DAMAGES OR LIABILITIES SUFFERED BY LICENSEE AS A RESULT OF OR
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THIS SOFTWARE OR
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.font.*;


public class FadeTextSplash
    extends Canvas implements Runnable, MouseListener {
  public static Font myfont = new Font("Times", Font.ITALIC, 36);
  public static int FADESTEPS = 32;

  public String text;
  public BounceVal animx;
  public BounceVal animy;
  public BounceVal animc;
  public boolean aa;

  public Image img;
  public Thread animator;

  public FadeTextSplash(String s) {
    text = s;
    animx = new BounceVal(0, 0, 5, 10);
    animy = new BounceVal(0, 0, 5, 10);
    animc = new BounceVal(0, 767, 30, 50);
    addMouseListener(this);
  }

  public Dimension getPreferredSize() {
    return new Dimension(500, 500);
  }

  public void paint(Graphics g) {
    if (img != null) {
      g.drawImage(img, 0, 0, null);
    }
  }

  public synchronized void start() {
    animator = new Thread(this);
    animator.start();
    notifyAll();
  }

  public synchronized void stop() {
    animator = null;
    notifyAll();
  }

  public void run() {
    Thread me = Thread.currentThread();
    me.setPriority(Thread.MIN_PRIORITY);
    Graphics2D gimg = null;
    Graphics gscr = getGraphics();
    while (animator == me) {
      int w = getWidth();
      int h = getHeight();
      if (img == null ||
          img.getWidth(null) != w ||
          img.getHeight(null) != h) {
        img = null;
        if (gimg != null) {
          gimg.dispose();
          gimg = null;
        }
        img = createImage(w, h);
      }
      if (gimg == null) {
        gimg = (Graphics2D) img.getGraphics();
        gimg.setFont(myfont);
        setRanges(w, h, gimg);
      }
      render(gimg, w, h);
      step();
      paint(gscr);
      Thread.yield();
    }
    if (gimg != null) {
      gimg.dispose();
    }
    gscr.dispose();
  }

  public static Color colorOf(int v) {
    int r = 0, g = 0, b = 0;
    if (v < 256) {
      r = 255 - v;
      g = v;
    } else {
      if (v < 512) {
        g = 511 - v;
        b = v - 256;
      } else {
        b = 767 - v;
        r = v - 512;
      }
    }
    return new Color(r, g, b);
  }

  int history[] = new int[FADESTEPS * 2];
  Color colorhist[] = new Color[FADESTEPS];

  public void render(Graphics g, int w, int h) {
    int x, y;
    Graphics2D g2d = (Graphics2D) g;
    g2d.setComposite(AlphaComposite.SrcOver);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_OFF);
    g2d.setColor(Color.white);
    g2d.fillRect(0, 0, w, h);
    if (aa) {
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
    }
    for (int i = 0; i < FADESTEPS; i++) {
      float alpha = FADESTEPS - Math.abs(i * 2 - FADESTEPS);
      alpha /= FADESTEPS;
      Composite c =
          AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
      g2d.setComposite(c);
      x = history[i * 2];
      y = history[i * 2 + 1];
      g2d.setColor(colorhist[i]);
      g2d.drawString(text, x, y);
    }
  }

  public void setRanges(int w, int h, Graphics2D g2d) {
    FontRenderContext frc = g2d.getFontRenderContext();
    Rectangle r = myfont.getStringBounds(text, frc).getBounds();
    int rx1 = r.x;
    int ry1 = r.y;
    int rx2 = rx1 + r.width;
    int ry2 = ry1 + r.height;
    animx.setValueRange( -rx1, w - rx2);
    animy.setValueRange( -ry1, h - ry2);
  }

  public void step() {
    animx.jump();
    animy.jump();
    animc.step();
    System.arraycopy(history, 2, history, 0, FADESTEPS * 2 - 2);
    System.arraycopy(colorhist, 1, colorhist, 0, FADESTEPS - 1);
    history[FADESTEPS * 2 - 2] = animx.getValue();
    history[FADESTEPS * 2 - 1] = animy.getValue();
    colorhist[FADESTEPS - 1] = colorOf(animc.getValue());
  }

  public void mouseClicked(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {
    aa = !aa;
  }

  public void mouseReleased(MouseEvent e) {}

  public void mouseEntered(MouseEvent e) {}

  public void mouseExited(MouseEvent e) {}

  public static void main(String argv[]) {
    Frame f = new Frame();
    FadeTextSplash tt = new FadeTextSplash(argv.length == 0
                                           ? "Java2D text rocks!"
                                           : argv[0]);
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    f.add(tt);
    f.pack();
    f.show();
    tt.start();
  }

  public static class BounceVal {
    int minval;
    int maxval;
    int minstep;
    int maxstep;

    int value;
    int step;

    public BounceVal(int minval, int maxval, int minstep, int maxstep) {
      setValueRange(minval, maxval);
      setStepRange(minstep, maxstep);

      this.value = rand(minval, maxval);
      this.step = rand(minstep, maxstep);
    }

    public void setValueRange(int min, int max) {
      if (min <= max) {
        this.minval = min;
        this.maxval = max;
      } else {
        this.minval = max;
        this.maxval = min;
      }
      clamp();
    }

    public void setStepRange(int min, int max) {
      if (min < 0 || max < 0 || min > max) {
        throw new IllegalArgumentException("step range requires " +
                                           "0 < min <= max");
      }
      this.minstep = min;
      this.maxstep = max;
      int stepsize = Math.abs(step);
      if (stepsize < minstep || stepsize > maxstep) {
        stepsize = rand(minstep, maxstep);
        step = (step >= 0) ? stepsize : -stepsize;
      }
    }

    public void step() {
      value += step;
      clamp();
    }

    public void jump() {
      value = rand(minval, maxval);
    }

    public void incwrap() {
      value++;
      if (value > maxval) {
        value = minval;
      }
    }

    public void decwrap() {
      value--;
      if (value < minval) {
        value = maxval;
      }
    }

    public void clamp() {
      if (value < minval) {
        value = minval + (minval - value);
        if (value > maxval) {
          value = (minval + maxval) / 2;
        }
        step = rand(minstep, maxstep);
      } else {
        if (value > maxval) {
          value = maxval - (value - maxval);
          if (value < minval) {
            value = (minval + maxval) / 2;
          }
          step = -rand(minstep, maxstep);
        }
      }
    }

    public int getValue() {
      return value;
    }

    public static int rand(int min, int max) {
      return min + (int) Math.round(Math.random() * (max - min));
    }
  }
}
