package exp.swing.frames;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JFramesListeners extends JFrame{
  final JWindow w;

  public JFramesListeners() {
    w = new JWindow(this);
    final JButton b = new JButton("BOTÃO");
    b.setBounds(10,10,200,25);
    b.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("CLICKEI ...");
        w.setVisible(true);
      }
    });
    JButton c = new JButton("BOTÃO");
    c.setBounds(10,10,200,25);
    c.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("CLICKEI ...");
        w.setVisible(false);
        requestFocus();
      }
    });
    w.getContentPane().setLayout(new BorderLayout());
    w.getContentPane().add(c, BorderLayout.CENTER);
    w.setSize(300,200);


    /**
    addHierarchyBoundsListener(new HierarchyBoundsListener(){
      public void ancestorMoved(HierarchyEvent e){
        System.out.println("ancestorMoved("+e.paramString()+")");
      }
      public void ancestorResized(HierarchyEvent e){
        System.out.println("ancestorResized("+e.paramString()+")");
      }
    });
    /**/
    /**
    addComponentListener(new ComponentListener(){
      public void componentHidden(ComponentEvent e){
        System.out.println("componentHidden("+e.paramString()+")");
      }
      public void componentMoved(ComponentEvent e){
        System.out.println("componentMoved("+e.paramString()+")");
        //setWindowLocation();
      }
      public void componentResized(ComponentEvent e){
        System.out.println("componentResized("+e.paramString()+") size="+getSize()+".");
      }
      public void componentShown(ComponentEvent e) {
        System.out.println("componentShown("+e.paramString()+")");
      }
    });
    /**/
    /**
    addHierarchyListener(new HierarchyListener (){
      public void hierarchyChanged(HierarchyEvent e){
        System.out.println("hierarchyChanged("+e.paramString()+")");
      }
    });
    /**/
    /**
    addInputMethodListener(new InputMethodListener(){
      public void caretPositionChanged(InputMethodEvent event){
        System.out.println("caretPositionChanged("+event.paramString()+")");
      }
      public void inputMethodTextChanged(InputMethodEvent event) {
        System.out.println("inputMethodTextChanged("+event.paramString()+")");
      }
    });
    /**/
    /**
    addPropertyChangeListener(new PropertyChangeListener(){
      public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange("+evt.getPropertyName()+")");
      }
    });
    /**/
    /**
    addWindowFocusListener(new  WindowFocusListener(){
      public void windowGainedFocus(WindowEvent e){
        System.out.println("windowGainedFocus");
      }
      public void windowLostFocus(WindowEvent e){
        System.out.println("windowLostFocus");
      }
    });
    /**/
    /**/
    addWindowListener(new WindowListener(){
      public void windowActivated(WindowEvent e){
        System.out.println("windowActivated");
      }
      public void windowClosed(WindowEvent e){
        System.out.println("windowClosed");
      }
      public void windowClosing(WindowEvent e){
        System.out.println("windowClosing");
      }
      public void windowDeactivated(WindowEvent e){
        System.out.println("windowDeactivated");
      }
      public void windowDeiconified(WindowEvent e){
        System.out.println("windowDeiconified");
      }
      public void windowIconified(WindowEvent e){
        System.out.println("windowIconified");
      }
      public void windowOpened(WindowEvent e) {
        System.out.println("windowOpened");
      }
    });
    /**/
    /**
    addWindowStateListener(new WindowStateListener(){
      public void windowStateChanged(WindowEvent e) {
        System.out.println("windowStateChanged("+e.paramString()+")");
      }
    });
    /**/


    getContentPane().setLayout(null);
    getContentPane().add(b, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400,350);
    setVisible(true);

    /**
    (new Thread(){
      public void run(){
        try {
          System.out.print("Sleeping ...");
          Thread.sleep(5000);
        }catch (Exception ex) {
          ex.printStackTrace();
        }
        System.out.println(" just wake up!");
        if(SwingUtilities.isEventDispatchThread()){
          performButtonStateChange(b, true);
        }else{
          SwingUtilities.invokeLater(new Runnable(){
            public void run() {
              performButtonStateChange(b, true);
            }
          });
        }
        try {
          System.out.print("Sleeping ...");
          Thread.sleep(5000);
        }catch (Exception ex) {
          ex.printStackTrace();
        }
        System.out.println(" just wake up!");
        if(SwingUtilities.isEventDispatchThread()){
          performButtonStateChange(b, false);
        }else{
          SwingUtilities.invokeLater(new Runnable(){
            public void run() {
              performButtonStateChange(b, false);
            }
          });
        }
      }
    }).start();
    /**/
    /**/
    (new Thread(){
      public void run() {
        for (int i = 5; i > -1; i++) {

          try {
            System.out.print("Sleeping ...");
            Thread.sleep(4000);
          } catch (Exception ex) {
            ex.printStackTrace();
          }

          setSize(400, (i%5) * 100);
        }
      }
    }).start();











    final Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while(true){
          try {Thread.sleep(10000);} catch (InterruptedException interruptedException) {}

          int i = 0;
          WindowListener[] wls = JFramesListeners.this.getWindowListeners();
          for (WindowListener wl : wls) {
            System.out.println("\tremoving WinListener["+(i++)+"]");
            JFramesListeners.this.removeWindowListener(wl);
          }
          System.out.println();

          SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              System.out.println("\nAdding window listener\n");
              
              JFramesListeners.this.addWindowListener(new WindowListener() {
                public void windowActivated(WindowEvent e) {
                  System.out.println("windowActivated 2");
                }

                public void windowClosed(WindowEvent e) {
                  System.out.println("windowClosed 2");
                }

                public void windowClosing(WindowEvent e) {
                  System.out.println("windowClosing 2");
                }

                public void windowDeactivated(WindowEvent e) {
                  System.out.println("windowDeactivated 2");
                }

                public void windowDeiconified(WindowEvent e) {
                  System.out.println("windowDeiconified 2");
                }

                public void windowIconified(WindowEvent e) {
                  System.out.println("windowIconified 2");
                }

                public void windowOpened(WindowEvent e) {
                  System.out.println("windowOpened 2");
                }
              });
            }
          });



          i = 0;
          wls = JFramesListeners.this.getWindowListeners();
          for (WindowListener wl : wls) {
            System.out.println("\tWinListener["+(i++)+"]");
            //wl.windowActivated(null);
          }
          System.out.println();

        }
      }
    }, "ThreadName");
    thread.setDaemon(true);
    thread.start();






  }

  private void performButtonStateChange(final JButton b, final boolean state){
    System.out.print("Painting immediately ...");
    Dimension size = b.getSize();
    b.getModel().setArmed(state);
    b.getModel().setPressed(state);
    b.paintImmediately(new Rectangle(0,0, size.width, size.height));
    System.out.println(" just painted immediately!");
  }

  private void setWindowLocation(){
    if(SwingUtilities.isEventDispatchThread()){
      w.setLocation(getLocationOnScreen());
    }else{
      SwingUtilities.invokeLater(new Runnable(){
        public void run() {
          w.setLocation(getLocationOnScreen());
        }
      });
    }
  }



  public void setBounds(final int x, final int y, final int width, final int height){
    ComponentListener[] compsListeners = getComponentListeners();
    for (int i = 0; i < compsListeners.length; i++) {
      removeComponentListener(compsListeners[i]);
      System.out.println("Removing the " + i + " comp listener.");
    }

    super.setBounds(x,y,width, height);

    for (int i = 0; i < compsListeners.length; i++) {
      addComponentListener(compsListeners[i]);
      System.out.println("Adding the " + i + " comp listener.");
    }
  }



  public static void main(String[] args) {
    JFramesListeners JFramesListeners1 = new JFramesListeners();
  }

}
