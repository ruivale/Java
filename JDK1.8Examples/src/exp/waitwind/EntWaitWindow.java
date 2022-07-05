package exp.waitwind;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/*
   TO CHANGE WIN BAR COLOURS
   UIManager.put("Frame.activeTitleBackground", new Color(64,128,255));
   UIManager.put("Frame.activeTitleForeground", Color.white);
   UIManager.put("Frame.inactiveTitleBackground", new Color(128,128,128));
   UIManager.put("Frame.inactiveTitleForeground", Color.black);
 */
/**
 *
 *
 */
public class EntWaitWindow
    extends JDialog {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** .. */
  public static final int PLAIN_TYPE  = 0;

  /** .. */
  public static final int CANCEL_TYPE = 1;

  /** .. */
  static JLabel       messageLabel;

  /** .. */
  static JLabel       noteLabel;

  /** .. */
  static JProgressBar theBar;

  /** .. */
  static volatile boolean cancelled = false;

  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  JPanel aPanel;

  /** .. */
  float percent = 0f;

  /** .. */
  int increment = 10;

  /** .. */
  int max;

  /** .. */
  int min;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   *
   */
  public EntWaitWindow(
    JFrame  owner,
    boolean modal,
    int     min,
    int     max,
    String  title,
    String  msg,
    String  note,
    int     type) {
    super(owner, title, modal);

    this.increment = max / 10;

    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    this.getContentPane()
        .setLayout(
      new VerticalFlowLayout(VerticalFlowLayout.MIDDLE, 5, 5, true, false));

    this.setResizable(false);
    this.min       = min;
    this.max       = max;
    messageLabel   = new JLabel(msg);
    messageLabel.setVisible(true);
    noteLabel = new JLabel(note);
    noteLabel.setVisible(true);
    theBar = new JProgressBar(min, max);
    theBar.setVisible(true);
    this.getContentPane()
        .add(messageLabel);
    this.getContentPane()
        .add(noteLabel);
    this.getContentPane()
        .add(theBar);

    this.setSize(300, 150);

    this.setLocation((owner.getWidth() - this.getWidth()) / 2,
      (owner.getHeight() - this.getHeight()) / 2);
    this.paintComponents(this.getGraphics());
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   *
   */
  public boolean isCancelled() {
    return cancelled;
  }

  /**
   *
   */
  public void setMessage(String newMessage) {
    if(!cancelled) {
      messageLabel.setText(newMessage);
    }
  }

  /**
   *
   */
  public void setNote(String newNote) {
    if(!cancelled) {
      noteLabel.setText(newNote);
    }
  }

  /**
   *
   */
  public void setProgress(int newValue) {
    if(!cancelled) {
      theBar.setValue(newValue);

      float f = round((float)newValue / max * 100F, 1);

      if(f>percent) {
        percent = f;
        this.paintComponents(this.getGraphics());
      }
    }
  }

  /**
   *
   */
  public void close() {
    _cancel();
  }

  /**
   *
   */
  public void incrementProgress() {
    if(!cancelled) {
      theBar.setValue(theBar.getValue() + this.increment);

      float f = round((float)theBar.getValue() / max * 100F, 1);

      if(f>percent) {
        percent = f;
        this.paintComponents(this.getGraphics());
      }
    }
  }

  /**
   *
   */
  public void incrementProgress(int increment) {
    if(!cancelled) {
      theBar.setValue(theBar.getValue() + increment);

      float f = round((float)theBar.getValue() / max * 100F, 1);

      if(f>percent) {
        percent = f;
        this.paintComponents(this.getGraphics());
      }
    }
  }

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setBounds(20, 20, 400, 250);
    frame.setVisible(true);

    (new EntWaitWindow(frame, true, 0, 1000, "Informação", "A processar ... ",
      "A obter os dados da base de dados.", JOptionPane.INFORMATION_MESSAGE)).setVisible(true);
  }

/*
   public void actionPerformed(ActionEvent e) {
     this.setMessage("Operation cancelled by user.");
     this.setNote("Please wait.....");
     this.setTitle("Cancelling.....");
     this.paintComponents(this.getGraphics());
     _cancel();
   }
 */
  /**
   *
   */
  private void _cancel() {
    cancelled = true;
    this.dispose();
  }

  /**
   *
   */
  private float round(
    float x,
    int   decimalPlaces) {
    return (float)((Math.round(x * Math.pow(10, decimalPlaces))) / Math.pow(10,
      decimalPlaces));
  }
}


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
class VerticalFlowLayout
    extends FlowLayout
    implements java.io.Serializable {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** .. */
  public static final int TOP    = 0;

  /** .. */
  public static final int MIDDLE = 1;

  /** .. */
  public static final int BOTTOM = 2;

  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  boolean hfill;

  /** .. */
  boolean vfill;

  /** .. */
  int hgap;

  /** .. */
  int vgap;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new VerticalFlowLayout object.
   */
  public VerticalFlowLayout() {
    this(TOP, 5, 5, true, false);
  }

  /**
   * Creates a new VerticalFlowLayout object.
   *
   * @param hfill  Insert doc ...
   * @param vfill  Insert doc ...
   */
  public VerticalFlowLayout(
    boolean hfill,
    boolean vfill) {
    this(TOP, 5, 5, hfill, vfill);
  }

  /**
   * Creates a new VerticalFlowLayout object.
   *
   * @param align  Insert doc ...
   */
  public VerticalFlowLayout(int align) {
    this(align, 5, 5, true, false);
  }

  /**
   * Creates a new VerticalFlowLayout object.
   *
   * @param align  Insert doc ...
   * @param hfill  Insert doc ...
   * @param vfill  Insert doc ...
   */
  public VerticalFlowLayout(
    int     align,
    boolean hfill,
    boolean vfill) {
    this(align, 5, 5, hfill, vfill);
  }

  /**
   * Creates a new VerticalFlowLayout object.
   *
   * @param align  Insert doc ...
   * @param hgap  Insert doc ...
   * @param vgap  Insert doc ...
   * @param hfill  Insert doc ...
   * @param vfill  Insert doc ...
   */
  public VerticalFlowLayout(
    int     align,
    int     hgap,
    int     vgap,
    boolean hfill,
    boolean vfill) {
    setAlignment(align);
    this.hgap    = hgap;
    this.vgap    = vgap;
    this.hfill   = hfill;
    this.vfill   = vfill;
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param hgap  Insert doc ...
   */
  public void setHgap(int hgap) {
    super.setHgap(hgap);
    this.hgap = hgap;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public int getHgap() {
    return hgap;
  }

  /**
   * Insert doc ...
   *
   * @param hfill  Insert doc ...
   */
  public void setHorizontalFill(boolean hfill) {
    this.hfill = hfill;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public boolean getHorizontalFill() {
    return hfill;
  }

  /**
   * Insert doc ...
   *
   * @param vfill  Insert doc ...
   */
  public void setVerticalFill(boolean vfill) {
    this.vfill = vfill;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public boolean getVerticalFill() {
    return vfill;
  }

  /**
   * Insert doc ...
   *
   * @param vgap  Insert doc ...
   */
  public void setVgap(int vgap) {
    super.setVgap(vgap);
    this.vgap = vgap;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  public int getVgap() {
    return vgap;
  }

  /**
   * Insert doc ...
   *
   * @param target  Insert doc ...
   */
  public void layoutContainer(Container target) {
    Insets insets    = target.getInsets();
    int    maxheight =
      target.getSize().height - (insets.top + insets.bottom + (vgap * 2));
    int    maxwidth  =
      target.getSize().width - (insets.left + insets.right + (hgap * 2));
    int    numcomp   = target.getComponentCount();
    int    x         = insets.left + hgap;
    int    y         = 0;
    int    colw      = 0;
    int    start     = 0;

    for(int i = 0; i<numcomp; i++) {
      Component m = target.getComponent(i);

      if(m.isVisible()) {
        Dimension d = m.getPreferredSize();

        if((this.vfill) && (i==(numcomp - 1))) {
          d.height = Math.max((maxheight - y), m.getPreferredSize().height);
        }

        if(this.hfill) {
          m.setSize(maxwidth, d.height);
          d.width = maxwidth;
        } else {
          m.setSize(d.width, d.height);
        }

        if((y + d.height)>maxheight) {
          placethem(target, x, insets.top + vgap, colw, maxheight - y, start, i);
          y = d.height;
          x += (hgap + colw);
          colw    = d.width;
          start   = i;
        } else {
          if(y>0) {
            y += vgap;
          }

          y += d.height;
          colw = Math.max(colw, d.width);
        }
      }
    }

    placethem(target, x, insets.top + vgap, colw, maxheight - y, start, numcomp);
  }

  /**
   * Insert doc ...
   *
   * @param target  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public Dimension minimumLayoutSize(Container target) {
    Dimension tarsiz = new Dimension(0, 0);

    for(int i = 0; i<target.getComponentCount(); i++) {
      Component m = target.getComponent(i);

      if(m.isVisible()) {
        Dimension d = m.getMinimumSize();
        tarsiz.width = Math.max(tarsiz.width, d.width);

        if(i>0) {
          tarsiz.height += vgap;
        }

        tarsiz.height += d.height;
      }
    }

    Insets insets = target.getInsets();
    tarsiz.width += (insets.left + insets.right + (hgap * 2));
    tarsiz.height += (insets.top + insets.bottom + (vgap * 2));

    return tarsiz;
  }

  /**
   * Insert doc ...
   *
   * @param target  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public Dimension preferredLayoutSize(Container target) {
    Dimension tarsiz = new Dimension(0, 0);

    for(int i = 0; i<target.getComponentCount(); i++) {
      Component m = target.getComponent(i);

      if(m.isVisible()) {
        Dimension d = m.getPreferredSize();
        tarsiz.width = Math.max(tarsiz.width, d.width);

        if(i>0) {
          tarsiz.height += vgap;
        }

        tarsiz.height += d.height;
      }
    }

    Insets insets = target.getInsets();
    tarsiz.width += (insets.left + insets.right + (hgap * 2));
    tarsiz.height += (insets.top + insets.bottom + (vgap * 2));

    return tarsiz;
  }

  /**
   * Insert doc ...
   *
   * @param target  Insert doc ...
   * @param x  Insert doc ...
   * @param y  Insert doc ...
   * @param width  Insert doc ...
   * @param height  Insert doc ...
   * @param first  Insert doc ...
   * @param last  Insert doc ...
   */
  private void placethem(
    Container target,
    int       x,
    int       y,
    int       width,
    int       height,
    int       first,
    int       last) {
    int    align  = getAlignment();
    Insets insets = target.getInsets();

    if(align==this.MIDDLE) {
      y += (height / 2);
    }

    if(align==this.BOTTOM) {
      y += height;
    }

    for(int i = first; i<last; i++) {
      Component m  = target.getComponent(i);
      Dimension md = m.getSize();

      if(m.isVisible()) {
        int px = x + ((width - md.width) / 2);
        m.setLocation(px, y);
        y += (vgap + md.height);
      }
    }
  }
}
