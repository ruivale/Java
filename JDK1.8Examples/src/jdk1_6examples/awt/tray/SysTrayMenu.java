/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk1_6examples.awt.tray;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author C2334
 */
public class SysTrayMenu {

  public static void main(String[] args) {
//Check the SystemTray support
    if (!SystemTray.isSupported()) {
      System.out.println("SystemTray is not supported");
      return;
    }
    final PopupMenu popup = new PopupMenu();
    final TrayIcon trayIcon = new TrayIcon(
        new ImageIcon("images/InossIcon.gif").getImage().getScaledInstance(17, 17, 1));
    final SystemTray tray = SystemTray.getSystemTray();



    // Create a popup menu components
    MenuItem aboutItem = new MenuItem("About");
    aboutItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        final JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.setBounds(750,500,350,250);
        f.add(new JLabel("...About..."));
        final JButton jb = new JButton("Close");
        jb.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            f.setVisible(false);
          }
        });
        f.add(jb, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);
      }
    });
    CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
    CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
    Menu displayMenu = new Menu("Display");
    MenuItem errorItem = new MenuItem("Error");
    MenuItem warningItem = new MenuItem("Warning");
    MenuItem infoItem = new MenuItem("Info");
    MenuItem noneItem = new MenuItem("None");
    MenuItem exitItem = new MenuItem("Exit");
    exitItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    //Add components to popup menu
    popup.add(aboutItem);
    popup.addSeparator();
    popup.add(cb1);
    popup.add(cb2);
    popup.addSeparator();
    popup.add(displayMenu);
    displayMenu.add(errorItem);
    displayMenu.add(warningItem);
    displayMenu.add(infoItem);
    displayMenu.add(noneItem);
    popup.add(exitItem);

    trayIcon.setPopupMenu(popup);

    try {
      tray.add(trayIcon);
    } catch (AWTException e) {
      System.out.println("TrayIcon could not be added.");
      return;
    }

  }
}
