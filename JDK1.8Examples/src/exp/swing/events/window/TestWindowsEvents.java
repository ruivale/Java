package exp.swing.events.window;


import java.awt.*;
import javax.swing.*;
//import com.ent.stv.oper.codecs.SWCodecs;
//import com.ent.stv.oper.codecs.CodecSWJMenu;
import java.awt.event.WindowAdapter;
//import com.ent.stv.oper.codecs.SWCodecsOpenWindows;
import java.awt.event.WindowEvent;
//import com.ent.stv.menubar.GISTVMenubar;
import java.awt.event.*;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestWindowsEvents {
//  public TestWindowsEvents() {
//    final F f = new F("JFRAME");
//    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    f.setBounds(10,10,300,250);
//    addWindowListener(f, "F");
//
//    final D d = new D( "JDIALOG");
//    d.setBounds(500,10,300,250);
//    //addWindowListener(d, "D");
//
//    final JButton b = new JButton("hide/show dialog");
//    b.addActionListener(new ActionListener(){
//      public void actionPerformed(ActionEvent e){
//        d.setVisible(!d.isVisible());
//      }
//    });
//    final JButton bd = new JButton("hide/show frame");
//    bd.addActionListener(new ActionListener(){
//      public void actionPerformed(ActionEvent e){
//        f.setVisible(!f.isVisible());
//      }
//    });
//
//    f.getContentPane().setLayout(new BorderLayout());
//    f.getContentPane().add(b, BorderLayout.CENTER);
//    d.getContentPane().setLayout(new BorderLayout());
//    d.getContentPane().add(bd, BorderLayout.CENTER);
//
//    f.setVisible(true);
//
//  }
//
//  public void addWindowListener(Window win, final String strID){
//    win.addWindowListener(new WindowAdapter() {
//      public void windowActivated(WindowEvent e) {
//        System.out.println(strID+" - windowActivated");
//      }
//      public void windowClosed(WindowEvent e) {
//        System.out.println(strID+" - windowClosed");
//      }
//      public void windowClosing(WindowEvent e) {
//        System.out.println(strID+" - windowClosing");
//      }
//      public void windowIconified(WindowEvent e) {
//        System.out.println(strID+" - windowIconified");
//      }
//      public void windowOpened(WindowEvent e) {
//        System.out.println(strID+" - windowOpened");
//      }
//      public void windowDeiconified(WindowEvent e) {
//        System.out.println(strID+" - windowDeiconified");
//      }
//      public void windowDeactivated(WindowEvent e) {
//        System.out.println(strID+" - windowDeactivated");
//      }
//      public void windowLostFocus(WindowEvent e) {
//        System.out.println(strID+" - windowLostFocus");
//      }
//      public void windowGainedFocus(WindowEvent e) {
//        System.out.println(strID+" - windowGainedFocus");
//      }
//
//      public void windowStateChanged(WindowEvent e) {
//        System.out.println(strID+" - windowStateChanged evt="+e.toString()+".");
//      }
//    });
//
//  }
//
//  public static void main(String[] args) {
//    TestWindowsEvents testwindowsevents = new TestWindowsEvents();
//  }
//
//  class D extends JDialog{
//
//    D(String s){
//      super(new JDialog(), s);
//      setUndecorated(true);
//      getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
//      setResizable(false);
//      //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
//    }
//
//    public void setVisible(boolean b){
//
//      System.out.println("D - setVisible("+b+")");
//
//      super.setVisible(b);
//    }
//  }
//  class F extends JFrame{
//
//    F(String s){
//      super(s);
//    }
//
//
//    public void setVisible(boolean b){
//
//      System.out.println("F - setVisible("+b+")");
//
//      super.setVisible(b);
//    }
//  }
}








