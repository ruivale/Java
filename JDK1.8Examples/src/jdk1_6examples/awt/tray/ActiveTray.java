package jdk1_6examples.awt.tray;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Image;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;



//???
//
//


public class ActiveTray {


  private static  String STR_H = "";
      //"????";
      //"Hello";
  private static  String STR_W = "";
      //"????";
      //"World";
  private static final String STR_C = "Cruel";
  private static final String STR_G = "Goodbye";
  private static final String STR_TT = "Tip Text";



  static{

    try {
      ResourceBundle rb =
          ResourceBundle.getBundle("jdk1_6examples.awt.tray.ActiveTray", new Locale("zh", "CN"));
      STR_H = rb.getString("hello");
      STR_W = rb.getString("world");

    } catch (Exception e) {
      STR_H = "h e l l o ";
      STR_W = " w o r l d ";
    }
  }


  public static void main(String args[])     throws Exception {


    final Button jb = new Button("\u6B22\u8FCE\u4F7F\u7528");
    jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
//        JOptionPane.showMessageDialog(null, STR_H + " " + STR_W + "!");


        JPopupMenu popup = new JPopupMenu();
        JMenuItem item = new JMenuItem(STR_H);
        popup.add(item);
        JMenuItem item2 = new JMenuItem(STR_W);
        popup.add(item2);

System.out.println("show show show show show show show show show show show show show show show show ");

        popup.show(jb, 0, 0);

System.out.println("close close close close close close close close close close close close close close ");
      }
    });
    JLabel jLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528" + "\u0020\u0020Unicode\u0021");
    jLabel.setToolTipText("This is Traditional Chinese");
    Label label = new Label("\u6B22\u8FCE\u4F7F\u7528");
    final JFrame f = new JFrame(STR_W);
    f.setBounds(100, 100, 300, 200);
    f.setLayout(new BorderLayout());
    f.add(label, BorderLayout.NORTH);
    f.add(jLabel, BorderLayout.CENTER);
    f.add(jb, BorderLayout.SOUTH);
    f.setVisible(true);


    if (SystemTray.isSupported() == false) {
      System.err.println("No system tray available");
      return;
    }
    final SystemTray tray = SystemTray.getSystemTray();
    PropertyChangeListener propListener = new PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent evt) {
        TrayIcon oldTray[] = (TrayIcon[]) evt.getOldValue();
        TrayIcon newTray[] = (TrayIcon[]) evt.getNewValue();
        System.out.println(oldTray.length + " / " + newTray.length);
      }
    };
    tray.addPropertyChangeListener("trayIcons", propListener);
    Image image = Toolkit.getDefaultToolkit().getImage("images/InossIcon.gif");
    PopupMenu popup = new PopupMenu();
    MenuItem item = new MenuItem(STR_H /*+ " " + STR_W + "!"*/);
    item.setFont(new java.awt.Font("FangSong", 0, 12));
    final TrayIcon trayIcon = new TrayIcon(image, STR_TT, popup);
    ActionListener menuActionListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        trayIcon.displayMessage(STR_G, STR_C +" "+ STR_W, TrayIcon.MessageType.INFO);
      }
    };
    item.addActionListener(menuActionListener);
    popup.add(item);
    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tray.remove(trayIcon);

        System.exit(0);
      }
    };
    trayIcon.addActionListener(actionListener);
    tray.add(trayIcon);

  }
}
