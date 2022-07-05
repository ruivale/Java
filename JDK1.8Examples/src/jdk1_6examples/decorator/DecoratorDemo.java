package jdk1_6examples.decorator;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */
/* Copyright (c) 2006 Timothy Wall All Rights Reserved */
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.util.*;
import java.util.Timer;


public class DecoratorDemo {

  static Timer timer = new Timer();

  static class Warning
      extends AbstractComponentDecorator {
    private final int SIZE = 16;
    public Warning(JTextField f) {
      super(f);
    }

    /** Position the decoration at the right edge of the text field. */
    public Rectangle getDecorationBounds() {
      Rectangle r = super.getDecorationBounds();
      r.x = getComponent().getWidth() - SIZE - 1;
      Insets insets = getComponent().getInsets();
      if (insets != null) {
        r.x -= insets.right;
      }
      r.y = (getComponent().getHeight() - SIZE) / 2;
      return r;
    }

    public void paint(Graphics graphics) {
      Rectangle r = getDecorationBounds();
      Graphics2D g = (Graphics2D) graphics;
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
      GeneralPath triangle = new GeneralPath();
      triangle.moveTo(r.x + SIZE / 2, r.y);
      triangle.lineTo(r.x + SIZE - 1, r.y + SIZE - 1);
      triangle.lineTo(r.x, r.y + SIZE - 1);
      triangle.closePath();
      g.setColor(Color.yellow);
      g.fill(triangle);
      g.setColor(Color.black);
      g.draw(triangle);
      g.drawLine(r.x + SIZE / 2, r.y + 4, r.x + SIZE / 2,
                 r.y + SIZE * 3 / 4 - 2);
      g.drawLine(r.x + SIZE / 2, r.y + SIZE * 3 / 4 + 1, r.x + SIZE / 2,
                 r.y + SIZE - 4);
    }
  }


  static class Dimmer
      extends AbstractComponentDecorator {
    public Dimmer(JComponent target) {
      super(target);
    }

    public void paint(Graphics g) {
      Color bg = getComponent().getBackground();
      g.setColor(new Color(bg.getRed(), bg.getGreen(), bg.getBlue(), 200));
      Rectangle r = getDecorationBounds();
      g.fillRect(r.x, r.y, r.width, r.height);
    }
  }


  private static final int SIZE = 1000;
  private static final int BLOCK = 20;
  private static final int LINE = 20;

  static class Labeler
      extends AbstractComponentDecorator {
    public Labeler(JComponent target) {
      super(target);
    }

    public Rectangle getDecorationBounds() {
      Rectangle r = super.getDecorationBounds();
      Rectangle visible = getComponent().getVisibleRect();
      if (r.x < visible.x) {
        r.x = visible.x;
      }
      return r;
    }

    public void paint(Graphics g) {
      Rectangle r = getDecorationBounds();
      for (int i = 0; i < SIZE; i += LINE) {
        g.drawString("label " + (i / LINE + 1), r.x,
                     r.y + i + g.getFontMetrics().getAscent() + 2);
      }
    }
  }


  static final class Lines
      extends JComponent implements Scrollable {
    public Dimension getPreferredScrollableViewportSize() {
      return new Dimension(100, LINE * 3);
    }

    public Dimension getPreferredSize() {
      return new Dimension(SIZE, SIZE);
    }

    public void paintComponent(Graphics g) {
      for (int i = 0; i < SIZE; i += LINE) {
        g.drawLine(0, i, SIZE - 1, i);
        for (int dot = 0; dot < SIZE; dot += BLOCK) {
          g.fillRect(i - 2, dot - 2, 5, 5);
        }
      }
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect,
                                          int orientation, int direction) {
      return 1;
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect,
                                           int orientation, int direction) {
      return LINE / 3;
    }

    public boolean getScrollableTracksViewportWidth() {
      return false;
    }

    public boolean getScrollableTracksViewportHeight() {
      return false;
    }
  }


  static final class Background
      extends AbstractComponentDecorator {
    private float angle;
    public Background(JComponent target) {
      super(target, -1);
      timer.schedule(new TimerTask() {
        public void run() {
          angle += 2 * 3.14159 / 90;
          repaint();
        }
      }, 0, 50);
    }

    public void paint(Graphics graphics) {
      Rectangle r = getDecorationBounds();
      Graphics2D g = (Graphics2D) graphics;
      float x1 = (float) (r.width / 2 + Math.cos(angle) * 100);
      float x2 = (float) (r.width / 2 + Math.cos(angle + 3.14159) * 100);
      float y1 = (float) (r.height / 2 + Math.sin(angle) * 100);
      float y2 = (float) (r.height / 2 + Math.sin(angle + 3.14159) * 100);
      Paint p = new GradientPaint(x1, y1, Color.green, x2, y2, Color.blue, true);
      g.setPaint(p);
      g.fillRect(r.x, r.y, r.width, r.height);
    }
  }


  static class Spotlight
      extends AbstractComponentDecorator {
    private Point where;
    private int size;
    private int delta = 1;
    private int dx = delta, dy = delta;
    public Spotlight(JComponent t, final int size) {
      super(t);
      where = new Point(t.getWidth() / 2, t.getHeight() / 2);
      this.size = size;
      timer.schedule(new TimerTask() {
        public void run() {
          if (where.x + size >= getComponent().getWidth()) {
            dx = -delta;
          } else if (where.x < 0) {
            dx = delta;
          }
          if (where.y + size >= getComponent().getHeight()) {
            dy = -delta;
          } else if (where.y < 0) {
            dy = delta;
          }
          where.x += dx;
          where.y += dy;
          repaint();
        }
      }, 0, 20);
    }

    public void paint(Graphics graphics) {
      Graphics2D g = (Graphics2D) graphics;
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
      Rectangle r = getDecorationBounds();
      Color c = new Color(20, 20, 20, 196);
      g.setColor(c);
      Shape spot = new Ellipse2D.Float(r.x + where.x, r.y + where.y, size, size);
      Area area = new Area(r);
      area.subtract(new Area(spot));
      g.fill(area);
      g.setColor(new Color(255, 255, 0, 128));
      g.fill(spot);
    }
  }


  static class RubberBand
      extends AbstractComponentDecorator implements MouseListener,
      MouseMotionListener {
    final int LINE_WIDTH = 4;
    private float phase = 0f;
    private Point origin = new Point(0, 0);
    public RubberBand(JComponent target) {
      super(target);
      setDecorationBounds(new Rectangle(0, 0, 0, 0));
      target.addMouseListener(this);
      target.addMouseMotionListener(this);
      // Make the ants march
      timer.schedule(new TimerTask() {
        public void run() {
          phase += 1.0f;
          repaint();
        }
      }, 0, 50);
    }

    public void paint(Graphics graphics) {
      Graphics2D g = (Graphics2D) graphics;
      g.setColor(UIManager.getColor("Table.selectionBackground"));
      Rectangle r = getDecorationBounds();
      g.setStroke(new BasicStroke(LINE_WIDTH, BasicStroke.CAP_BUTT,
                                  BasicStroke.JOIN_ROUND, 10.0f,
                                  new float[] {4.0f}, phase));
      g.drawRect(r.x, r.y, r.width, r.height);
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
      setDecorationBounds(new Rectangle(e.getX() - LINE_WIDTH / 2,
                                        e.getY() - LINE_WIDTH / 2, 0, 0));
      origin.setLocation(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {
      setDecorationBounds(new Rectangle(0, 0, 0, 0));
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {
      int width = Math.abs(origin.x - e.getX());
      int height = Math.abs(origin.y - e.getY());
      int x = Math.min(e.getX(), origin.x);
      int y = Math.min(e.getY(), origin.y);
      setDecorationBounds(new Rectangle(x, y, width, height));
    }

    public void mouseMoved(MouseEvent e) {}
  }


  static class Draft
      extends AbstractComponentDecorator {
    public Draft(JComponent target) {
      super(target);
    }

    public void paint(Graphics graphics) {
      Rectangle r = getDecorationBounds();
      Graphics2D g = (Graphics2D) graphics;
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
      Font f = g.getFont();
      g.setFont(f.deriveFont(Font.BOLD, f.getSize() * 2.0f));
      g.setColor(new Color(128, 128, 128, 128));
      double theta = -3.14159 / 6;
      g.rotate(theta);
      int dx = (int) Math.abs(r.height * Math.sin(theta));
      g.translate(dx, r.height);
      g.drawString("DRAFT", 0, r.height * 3 / 4);
    }
  }


  static class GradientBackground
      extends AbstractComponentDecorator {
    public GradientBackground(JComponent c) {
      super(c, -1);
    }

    public void paint(Graphics graphics) {
      Graphics2D g = (Graphics2D) graphics;
      JComponent jc = getComponent();
      int h = jc.getHeight() / 2;
      Color c = jc.getBackground().darker();
      GradientPaint gp = new GradientPaint(0, h, c, jc.getWidth() / 2, h,
                                           jc.getBackground());
      g.setPaint(gp);
      Insets insets = jc.getInsets();
      g.fillRect(insets.left, insets.top, jc.getWidth() - insets.right,
                 jc.getHeight() - insets.bottom);
    }
  }


  public static void main(String[] args) {
    JFrame frame = new JFrame("Decorator Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel p = new JPanel(new BorderLayout());
    p.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

    JLabel banner = new JLabel("Decorator Demo");
    banner.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 8, 0),
                                        new LineBorder(Color.black)));
    Font f = banner.getFont();
    banner.setFont(f.deriveFont(Font.BOLD, f.getSize() * 2));
    GradientBackground g = new GradientBackground(banner);
    p.add(banner, BorderLayout.NORTH);

    JComponent lines = new Lines();
    Labeler labeler = new Labeler(lines);
    JScrollPane scroll = new JScrollPane(lines);
    scroll.setBorder(new TitledBorder("Partially-hovering labels"));
    p.add(scroll, BorderLayout.CENTER);

    JPanel box = new JPanel(new GridLayout(0, 1));
    p.add(box, BorderLayout.SOUTH);

    JTextField tf = new JTextField("Badge decoration");
    tf.setBorder(new TitledBorder("Draw a badge icon with tooltip"));
    Warning w = new Warning(tf);
    w.setToolTipText("The tooltip is tied to the decoration");
    box.add(tf);

    JLabel label = new JLabel("This component has been dimmed");
    label.setBorder(new TitledBorder("Dim the entire component"));
    Dimmer d = new Dimmer(label);
    box.add(label);

    JLabel bgLabel = new JLabel("This background is decorated");
    bgLabel.setBorder(new TitledBorder("Dynamic Background"));
    Background bg = new Background(bgLabel);
    box.add(bgLabel);

    JLabel spot = new JLabel("A common screensaver theme");
    spot.setBorder(new TitledBorder("Dynamic Decoration"));
    Spotlight s = new Spotlight(spot, 20);
    box.add(spot);

    JLabel selection = new JLabel("Click and drag to select");
    selection.setBorder(new TitledBorder("Interactive Decoration"));
    RubberBand band = new RubberBand(selection);
    box.add(selection);

    JLabel draftLabel = new JLabel("Stamp Over");
    draftLabel.setBorder(new TitledBorder("Watermark"));
    Draft draft = new Draft(draftLabel);
    box.add(draftLabel);

    frame.getContentPane().add(p);
    frame.pack();
    frame.setVisible(true);
  }
}
