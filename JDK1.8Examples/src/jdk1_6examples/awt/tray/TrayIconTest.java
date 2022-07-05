package jdk1_6examples.awt.tray;


import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TrayIconTest {
  static Image image = Toolkit.getDefaultToolkit().getImage(
      "images/InossIcon.gif");

  static TrayIcon trayIcon = new TrayIcon(image, "Tester2");

  public static void main(String[] a)
      throws Exception {
    if (SystemTray.isSupported()) {
      SystemTray tray = SystemTray.getSystemTray();

      trayIcon.setImageAutoSize(true);
      trayIcon.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("In here");
          trayIcon.displayMessage("Tester!", "Some action performed",
                                  TrayIcon.MessageType.INFO);
        }
      });

      try {
        tray.add(trayIcon);
      } catch (AWTException e) {
        System.err.println("TrayIcon could not be added.");
      }
    }
  }
}
