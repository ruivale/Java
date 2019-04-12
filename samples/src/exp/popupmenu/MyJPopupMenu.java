package exp.popupmenu;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */



import java.awt.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;







public class MyJPopupMenu extends JFrame implements ActionListener, ItemListener {

//  final JPopupMenu jPopupMenuQuadrant = new JPopupMenu();
  final TransparentJPopupMenu jPopupMenuQuadrant = new TransparentJPopupMenu();
  static JPanel p = null;


    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    JPopupMenu popup;




  public MyJPopupMenu() {

    //p = this.getContentPane();
    p=null;



    JButton b = new JButton("clica");
    b.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        jPopupMenuQuadrant.show(p, 4,4);
        jPopupMenuQuadrant.setVisible(true);
      }
    });
    b.setBackground(Color.red);
    this.getContentPane().setLayout(new BorderLayout());
    //b.setToolTipText("clack");

    this.getContentPane().add(b, BorderLayout.CENTER);

    this.setBackground(new Color(230,230,230));

    // Building the JPopupMenu that indicates the quadrant number.
    JLabel l = new JLabel("Label -----");
    l.setFont(new Font("Dialog", 1, 10));
    l.setForeground(Color.black);
    l.setBackground(Color.green);
    //l.setBackground(new Color(230,230,230));
    l.setHorizontalAlignment(SwingConstants.LEFT);
    //l.setOpaque(false);
    l.setBorder(null);

    JButton jb = new JButton("button 1-- ");
    jb.setFont(new Font("Dialog", 1, 10));
    jb.setForeground(Color.black);
    jb.setBackground(Color.green);
    //jb.setBackground(new Color(230,230,230));
    jb.setHorizontalAlignment(SwingConstants.LEFT);
    jb.setOpaque(false);
    jb.setBorder(null);
    jb.setBorderPainted(false);





//    jPopupMenuQuadrant.add(l);
    jPopupMenuQuadrant.add(jb);
//    jPopupMenuQuadrant.setBackground(Color.blue);
//    jPopupMenuQuadrant.setBackground(new Color(230,230,230));
    jPopupMenuQuadrant.setBorderPainted(false);
    jPopupMenuQuadrant.setBorder(null);
//    jPopupMenuQuadrant.setOpaque(false);











/*
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //Add regular components to the window, using the default BorderLayout.
        Container contentPane = getContentPane();
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        //Create the menu bar.
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Build the first menu.
        menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("A text-only menu item",
                                 KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Both text and icon",
                                 new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);

        //a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);
        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Another Menu");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);

        //Create the popup menu.
        popup = new JPopupMenu();
        menuItem = new JMenuItem("A popup menu item");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Another popup menu item");
        menuItem.addActionListener(this);
        popup.add(menuItem);

        //Add listener to components that can bring up popup menus.
        MouseListener popupListener = new PopupListener();
        output.addMouseListener(popupListener);
        scrollPane.addMouseListener(popupListener);
        menuBar.addMouseListener(popupListener);
*/

    }





    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")";
        output.append(s + newline);
    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        output.append(s + newline);
    }

    // Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }


    class PopupListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }
    }









  public static void main(String[] args) {
    MyJPopupMenu myJPopupMenu1 = new MyJPopupMenu();

//    myJPopupMenu1.setVisible(true);


    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(myJPopupMenu1, BorderLayout.CENTER);
    f.setBounds(200,200,300,200);
    f.setVisible(true);

  }
}



class TransparentJPopupMenu extends JPopupMenu {
  public TransparentJPopupMenu(){
    super();
    setOpaque(false);
  }
  // Just for simulate the setOpaque(false) method.
  protected void paintComponent(Graphics gc){
System.out.println("paintComponent");
    // do nothing
  }
  public boolean isOpaque(){
    return false;
  }
}













