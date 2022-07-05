package exp.frames;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class MyFrames
    extends JFrame {
  JMenuBar jmb = new JMenuBar();

  JButton jButton1 = new JButton();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  Border border1;
  Border border2;
  JDialog d = null; //new JDialog(this, "dialog", true);

  MyFrames myFrames = null;

  public MyFrames() {

    myFrames = this;

    setTitle("This JFRAME looks like JDialog");
    //setSize(new Dimension(500,100));
    setUndecorated(true);
    setResizable(false);

    //getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
    //getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
    setLocationRelativeTo(null);


    try {
      jbInit();

//      setBounds(
//          0,
//          0,
//          Toolkit.getDefaultToolkit().getScreenSize().width,
//          Toolkit.getDefaultToolkit().getScreenSize().height);
      //setSize(1280, 1000);
      setExtendedState(Frame.MAXIMIZED_BOTH);
      



//      addComponentListener(new ComponentAdapter(){
//        public void componentMoved(ComponentEvent evt) {
//          System.out.println("componentMoved");
//          setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
//        public void componentResized(ComponentEvent e) {
//          System.out.println("componentResized");
//          setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
//        public void _componentShown(ComponentEvent e) {
//          System.out.println("componentShown");
//          setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
//        public void _componentHidden(ComponentEvent e) {
//          System.out.println("componentHidden");
//        }
//      });



      Runtime.getRuntime().addShutdownHook(new Thread() {
        public void run() {
          System.out.println("\nENDINGENDINENDINGENDINENDINGENDINENDINGENDIN\n"); ;
        }
      });

      setVisible(true);
      setExtendedState(Frame.MAXIMIZED_BOTH);

      d = new JDialog(this, "dialog", true);
      d.setLocationRelativeTo(this);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    MyFrames myFrames1 = new MyFrames();
  }

  /**
   *
   */
  public void setMyFrameJMenuBar(JMenuBar jMenuBar) {
    JMenuBar newJMenuBar = new JMenuBar();
    int numberOfMenus;

    if (this.getJMenuBar() != null) {
      numberOfMenus = this.getJMenuBar().getMenuCount();
      for (int i = 0; i < numberOfMenus; i++) {
        newJMenuBar.add(this.getJMenuBar().getMenu(0));
      }
    }

    numberOfMenus = jMenuBar.getMenuCount();
    for (int i = 0; i < numberOfMenus; i++) {
      newJMenuBar.add(jMenuBar.getMenu(0));
    }

//    this.remove(jmb);
    this.setJMenuBar(newJMenuBar);
//    this.setJMenuBar(jMenuBar);

    this.validate();

  }

  /**
   *
   */
  void jButton1_actionPerformed(ActionEvent e) {
    /*
     JOptionPane.showMessageDialog(this,"aaa","aa",JOptionPane.PLAIN_MESSAGE,
          new ImageIcon("/JBProjects/PInt/src/images/icons/ENTIcon.gif"));

        com.ent.PInt.windows.FaultApplication.showMessageDialog(
          "bbb","BBBBB",JOptionPane.WARNING_MESSAGE);
     */

    JMenu jm = new JMenu("pint");
    JMenuItem jmi = new JMenuItem("menu item");
    jm.add(jmi);
    jmb.add(jm);
    this.setJMenuBar(jmb);
    validate();
    jmb = new JMenuBar();

    System.out.println("no botao da myframe");

  }

  private void jbInit() throws Exception {

    border1 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white,
                                              Color.white, new Color(99, 99, 99),
                                              new Color(142, 142, 142));
    border2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    jButton1.setBounds(new Rectangle(145, 5, 95, 23));
    jButton1.setText("jButton1");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);

        d.setLocationRelativeTo(myFrames);
        d.setVisible(true);

      }
    });

//    JMenuBar jmb = new JMenuBar();

    JMenu jm = new JMenu("pint");
    JMenuItem jmi = new JMenuItem("menu item");
    jPanel1.setBorder(border1);
    jPanel1.setBounds(new Rectangle(85, 77, 184, 126));
    jPanel1.setLayout(borderLayout1);
    jLabel1.setBorder(border2);
    jLabel1.setText("jLabel1");
    jm.add(jmi);
    jmb.add(jm);
    this.setJMenuBar(jmb);

    this.getContentPane().setLayout(null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jPanel1, null);
    jPanel1.add(jLabel1, BorderLayout.CENTER);
  }

}
