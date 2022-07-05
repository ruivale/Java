package exp.swing.comps.resizing;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class JSizeGrip
    extends JComponent {
  protected Robot _robot;
  protected WindowStateListener _windowStateListener;

  protected boolean _maintainSpace;
  protected Insets _insets;

  public JSizeGrip() {
    setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    try {
      _robot = new Robot();
      enableEvents(AWTEvent.MOUSE_EVENT_MASK);
      setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
      setAutoscrolls(true);
      setFocusable(false);
    } catch (AWTException ex) {
      setVisible(false);
      _robot = null;
    }
  }


  public void addNotify() {
    super.addNotify();
    if (isUsable()) {
      getWindow().addWindowStateListener(getWindowStateListener());
    }
  }


  public void removeNotify() {
    if (isUsable()) {
      getWindow().removeWindowStateListener(getWindowStateListener());
    }
    super.removeNotify();
  }


  protected WindowStateListener getWindowStateListener() {
    if (_windowStateListener == null) {
      _windowStateListener = new WindowStateListener() {
        public void windowStateChanged(WindowEvent e) {

          System.out.println("windowStateChanged");

          setCursor(e.getNewState() == Frame.MAXIMIZED_BOTH ?
                    Cursor.getDefaultCursor() :
                    Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
          if (!getMaintainSpace()) {
            setVisible(e.getNewState() != Frame.MAXIMIZED_BOTH);
          }
        }
      };
    }
    return _windowStateListener;
  }


  public boolean isActive() {
    return isVisible() && (getCursor() != Cursor.getDefaultCursor());
  }


  public boolean isUsable() {
    return _robot != null;
  }


  public void setMaintainSpace(boolean maintainSpace) {
    _maintainSpace = maintainSpace;
    setVisible(_maintainSpace);
  }


  public boolean getMaintainSpace() {
    return _maintainSpace;
  }


  protected Window getWindow() {
    return (Window) SwingUtilities.getRoot(this);
  }


  protected void processMouseEvent(MouseEvent e) {
    super.processMouseEvent(e);
    if ((e.getID() == MouseEvent.MOUSE_PRESSED) && isActive()) {

      System.out.println("processMouseEvent");

      Window window = getWindow();
      Dimension dim = window.getSize();
      //method will create a new Point()
      Point windowPoint = SwingUtilities.convertPoint(this, e.getPoint(),
          window);

      Point screenPoint = new Point(e.getPoint());
      SwingUtilities.convertPointToScreen(screenPoint, this);

      int x = screenPoint.x + dim.width - windowPoint.x - 2;
      int y = screenPoint.y + dim.height - windowPoint.y - 2;

      _robot.mouseRelease(InputEvent.BUTTON1_MASK);
      _robot.mouseMove(x, y);
      _robot.mousePress(InputEvent.BUTTON1_MASK);
    }
  }


  public Dimension getMinimumSize() {
    Insets insets = getInsets();

    return new Dimension(
        SizeGripIcon.ICON.getIconWidth() + insets.right + insets.left,
        SizeGripIcon.ICON.getIconHeight() + insets.top + insets.bottom);
  }


  public Dimension getPreferredSize() {
    return getMinimumSize();
  }


  public Dimension getMaximumSize() {
    Dimension dim = getMinimumSize();
    dim.height = Integer.MAX_VALUE;
    return dim;
  }


  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (isActive()) {

      Dimension dim = getSize();
      Insets insets = getInsets();

      int x = dim.width - (SizeGripIcon.ICON.getIconWidth() + insets.right);
      int y = dim.height - (SizeGripIcon.ICON.getIconHeight() + insets.bottom);

      SizeGripIcon.ICON.paintIcon(this, g, x, y);
    }
  }

  public static void main(String[] s){
    JSizeGrip j = new JSizeGrip();
    j.setToolTipText("skjdskdjkjasjda");
    final JFrame f = new JFrame( "Modal Internal Frame");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(j, BorderLayout.CENTER);
    f.setSize(500, 300);
    f.setVisible(true);
  }

}


class SizeGripIcon
    implements Icon {
  static public final SizeGripIcon ICON = new SizeGripIcon();


  public void paintIcon(Component c, Graphics g, int x, int y) {
    g.translate(x, y);
    Color highlight = UIManager.getColor("ScrollBar.thumbHighlight");
    Color shadow = UIManager.getColor("ScrollBar.thumbShadow");

    int width = getIconWidth() - 1;
    int height = getIconHeight() - 1;

    for (int i = 1; i < 12; ++i) {
      int mod = i % 4;
      if (mod == 0) {
        continue;
      }

      g.setColor((mod == 3) ? highlight : shadow);
      g.drawLine(width - i, height, width, height - i);
    }
    g.translate( -x, -y);
  }

  public int getIconWidth() {
    return 12;
  }

  public int getIconHeight() {
    return 12;
  }
}
