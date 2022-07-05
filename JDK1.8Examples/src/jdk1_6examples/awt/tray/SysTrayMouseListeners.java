package jdk1_6examples.awt.tray;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;


public class SysTrayMouseListeners {
  public static void main(String[] args) {
    if (!SystemTray.isSupported()) {
      return;
    }
    SystemTray tray = SystemTray.getSystemTray();

    Dimension size = tray.getTrayIconSize();
    BufferedImage bi = new BufferedImage(size.width, size.height,
                                         BufferedImage.TYPE_INT_RGB);
    Graphics g = bi.getGraphics();

    g.setColor(Color.blue);
    g.fillRect(0, 0, size.width, size.height);
    g.setColor(Color.yellow);
    int ovalSize = (size.width < size.height) ? size.width : size.height;
    ovalSize /= 2;
    g.fillOval(size.width / 4, size.height / 4, ovalSize, ovalSize);

    try {
      PopupMenu popup = new PopupMenu();
      MenuItem miExit = new MenuItem("Exit");
      ActionListener al;
      al = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("Goodbye");
          System.exit(0);
        }
      };
      miExit.addActionListener(al);
      popup.add(miExit);

      TrayIcon ti = new TrayIcon(bi, "Sys Tray Mouse Listeners", popup);

      al = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println(e.getActionCommand());
        }
      };
      ti.setActionCommand("My Icon");
      ti.addActionListener(al);

      MouseListener ml;
      ml = new MouseListener() {
        public void mouseClicked(MouseEvent e) {
          System.out.println("Tray icon: Mouse clicked");
        }

        public void mouseEntered(MouseEvent e) {
          System.out.println("Tray icon: Mouse entered");
        }

        public void mouseExited(MouseEvent e) {
          System.out.println("Tray icon: Mouse exited");
        }

        public void mousePressed(MouseEvent e) {
          System.out.println("Tray icon: Mouse pressed");
        }

        public void mouseReleased(MouseEvent e) {
          System.out.println("Tray icon: Mouse released");
        }
      };
      ti.addMouseListener(ml);

      MouseMotionListener mml;
      mml = new MouseMotionListener() {
        public void mouseDragged(MouseEvent e) {
          System.out.println("Tray icon: Mouse dragged");
        }

        public void mouseMoved(MouseEvent e) {
          System.out.println("Tray icon: Mouse moved");
        }
      };
      ti.addMouseMotionListener(mml);

      tray.add(ti);
    } catch (AWTException e) {
      System.out.println(e.getMessage());
      return;
    }
  }

}
