package jdk1_6examples.awt.desktop;


import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class DesktopDemo {
  static Desktop desktop;
  public static void main(String[] args) {

    if (Desktop.isDesktopSupported()) {
      desktop = Desktop.getDesktop();
    } else {
      System.out.println("Desktop class is not supported");
      System.exit(1);
    }
    JMenuItem openItem = new JMenuItem("Open");
    JMenuItem editItem = new JMenuItem("Edit");
    JMenuItem printItem = new JMenuItem("Print");
    JMenuItem browseToItem = new JMenuItem("Go to www.java2s.com");
    JMenuItem mailToItem = new JMenuItem("Email to a@java.com");
    JMenu fileMenu = new JMenu("File");
    JMenu mailMenu = new JMenu("Email");
    JMenu browseMenu = new JMenu("Browser");

    openItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          try {
            desktop.open(chooser.getSelectedFile().getAbsoluteFile());
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    });
    fileMenu.add(openItem);

    editItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          try {
            desktop.edit(chooser.getSelectedFile().getAbsoluteFile());
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    });
    fileMenu.add(editItem);

    printItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          try {
            desktop.print(chooser.getSelectedFile().getAbsoluteFile());
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    });
    fileMenu.add(printItem);

    browseToItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          URI browseURI = new URI("www.java2s.com");
          desktop.browse(browseURI);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
      }
    });
    browseMenu.add(browseToItem);

    mailToItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          URI mailURI = new URI("mailto:support@java.com");
          desktop.mail(mailURI);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
      }
    });
    mailMenu.add(mailToItem);

    JMenuBar jMenuBar = new JMenuBar();
    jMenuBar.add(fileMenu);
    jMenuBar.add(browseMenu);
    jMenuBar.add(mailMenu);

    JFrame frame = new JFrame();
    frame.setTitle("Desktop Helper Applications");
    frame.setSize(300, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setJMenuBar(jMenuBar);
    frame.setVisible(true);

  }
}
