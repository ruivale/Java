package exp.swing.glass;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class GlassTests {
  final JFrame f = new JFrame("FRAME");

  public GlassTests() {
    f.setGlassPane(new GlassComponentInner());
    f.setBounds(100,100,700,500);
    final JButton b = new JButton("Press me to show window");
    final JWindow w = new JWindow(f);
    final JButton c = new JButton("Press to hide me");

    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(b, BorderLayout.CENTER);
    f.setBounds(100,100,700,500);

    w.getContentPane().setLayout(new BorderLayout());
    w.getContentPane().add(c, BorderLayout.CENTER);
    w.setSize(300,200);

    b.addActionListener(new ActionListener(){
      boolean b = true;
      public void actionPerformed(final ActionEvent e){
        setWaitState(true);
        w.setVisible(true);
      }
    });
    c.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        w.setVisible(false);
        setWaitState(false);
      }
    });

    f.setVisible(true);
  }

  public void setWaitState(boolean wait) {
    System.out.println("setWaitState("+wait+")");
    if (wait) {
      f.setCursor(Cursor.getPredefinedCursor
                (Cursor.WAIT_CURSOR));
      f.getGlassPane().setVisible(true);
    } else {
      f.setCursor(Cursor.getPredefinedCursor
                (Cursor.DEFAULT_CURSOR));
      f.getGlassPane().setVisible(false);
    }
  }

  /**
   * This component can be used as an intelligent glass pane. In
     your JDialog
   * or JFrame say setGlassPane(new GlassComponent())
   */
  public class GlassComponentInner
      extends JComponent
      implements
      AWTEventListener {
    Window parentWindow;

    public GlassComponentInner() {
      super();
      this.setCursor(Cursor.getPredefinedCursor
                     (Cursor.WAIT_CURSOR));
      setOpaque(false);

      addMouseListener(new MouseAdapter() {});
    }

    /**
     * Override setVisible to install/remove key events hook
       that will allow us to
     * disable key events when the glass pane is visible.
     */
    public void setVisible(boolean visible) {
      if (visible) {
        if (this.parentWindow == null) {
          this.parentWindow =
              SwingUtilities.windowForComponent(this);
        }

        Toolkit.getDefaultToolkit().addAWTEventListener
            (this, AWTEvent.KEY_EVENT_MASK);
      } else {
        Toolkit.getDefaultToolkit().removeAWTEventListener
            (this);
      }
      super.setVisible(visible);
    }

    /**
     * Called whenever ther is an event in AWT queue. Note that
       the current implementation
     * skips all key events, not just events for this window.
       Logic can be enhanced to examine
     * the source of the event and it's parent window and skip
       only those events
     * that originated from disabled window
     */
    public void eventDispatched(AWTEvent event) {
      if (event instanceof KeyEvent && event.getSource()
          instanceof Component) {
        if (SwingUtilities.windowForComponent( (Component)
                                              event.getSource()) ==
            this.parentWindow) {

// Consume events only for our window
          ( (KeyEvent) event).consume();
        }
      }
    }

  }

  public static void main(String[] args) {
    GlassTests glassTests1 = new GlassTests();
  }

}
