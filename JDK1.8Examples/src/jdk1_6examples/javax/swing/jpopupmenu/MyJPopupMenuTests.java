package jdk1_6examples.javax.swing.jpopupmenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;


public class MyJPopupMenuTests extends JFrame{

  public MyJPopupMenuTests() {
    setLayout(new BorderLayout());
    setBounds(100,100,350,450);
    
    final JButton b = new JButton("clicv");
    b.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
        
        JPopupMenu jpm1 = new JPopupMenu();
        jpm1.add(new JMenuItem("jpm1 - 1"));
        jpm1.add(new JMenuItem("jpm1 - 2"));
        JMenu jmi = new JMenu("jpm1 - items");
        jmi.add(new JMenuItem("menuitem - 1"));
        jmi.add(new JMenuItem("menuitem - 1"));
        jpm1.add(jmi);
        jpm1.add(new JMenuItem("jpm1 - 3"));
        System.out.println("jpm1.show");         
        //jpm1.show(b, e.getX(), e.getY());
        
        
        JPopupMenu jpm2 = new JPopupMenu();
        jpm2.add(new JMenuItem("jpm2 - 1"));
        jpm2.add(new JMenuItem("jpm2 - 2"));
        JMenu jmi2 = new JMenu("jpm2 - items");
        jmi2.add(new JMenuItem("menuitem - 2"));
        jmi2.add(new JMenuItem("menuitem - 2"));
        jpm2.add(jmi2);
        jpm2.add(new JMenuItem("jpm2 - 3"));        
        System.out.println("jpm2.show"); 
        //jpm2.show(b, e.getX(), e.getY());
        
        
        JPopupMenu jpm = new JPopupMenu();
        
        JMenu jm1 = new JMenu("jpm1");
        JMenu jm2 = new JMenu("jpm2");
        
        for(MenuElement menuElement : jpm1.getSubElements()) {
          jm1.add(menuElement.getComponent());
        }
        for(MenuElement menuElement : jpm2.getSubElements()) {
          jm2.add(menuElement.getComponent());
        }
        
        jpm.add(jm1);
        jpm.add(jm2);
        
        jpm.show(b, e.getX(), e.getY());
      }
    });
    add(b, BorderLayout.CENTER);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  public static void main(String[] args) {
    new MyJPopupMenuTests();
  }
}
