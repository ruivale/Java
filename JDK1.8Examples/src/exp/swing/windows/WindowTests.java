/**
 * <p>
 * Classname: exp.swing.windows.WindowTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package exp.swing.windows;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on May 14, 2015, 3:38:57 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class WindowTests extends JFrame {

  private static final int iDisplayNumber = 1;

  private static final boolean mayMaximize =
      Toolkit.getDefaultToolkit().isFrameStateSupported(Frame.MAXIMIZED_BOTH);

  private static final Rectangle rectWhere2ShowInoss =
      iDisplayNumber > 0?
          new Rectangle(1920, 0, 1680, 1049):
          new Rectangle(0, 0, 1920, 1079);

  private static Robot robot;

//  private static GraphicsConfiguration graphicsConfiguration = null;

  static{
    try {
      robot = new Robot();
    } catch (Exception e) {
      e.printStackTrace();
    }

//    try{
//      final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//      final GraphicsDevice[] gdlist = ge.getScreenDevices();
//
//      if (gdlist.length > 1) {
//        final GraphicsDevice gd;
//
//        if (iDisplayNumber < gdlist.length) {
//          gd = gdlist[iDisplayNumber];
//        } else {
//          gd = gdlist[gdlist.length - 1];
//        }
//
//        graphicsConfiguration = gd.getDefaultConfiguration();
//
////      } else if (gdlist.length == 1) {
////        final GraphicsDevice gd = gdlist[0];
////        graphicsConfiguration = gd.getDefaultConfiguration();
////
////      } else {
////        graphicsConfiguration =
////            GraphicsEnvironment.getLocalGraphicsEnvironment().
////                getDefaultScreenDevice().getDefaultConfiguration();
//      }
//
//    }catch(Exception e){
//      e.printStackTrace();
//    }
  }

 /**
  * The WindowTests default constructor.
  */
  public WindowTests(){

    final JButton jb = new JButton("Jãste quelique mi...");
//    jb.addActionListener((ActionEvent e) -> {
//      if(mayMaximize){
//        if(WindowTests.this.getExtendedState() == Frame.MAXIMIZED_BOTH){
//          WindowTests.this.setExtendedState(Frame.NORMAL);
//        }else{
//          WindowTests.this.setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
//      }
//    });

    final JPanel jp = new JPanel(new BorderLayout());
    jp.add(jb);

    final JMenuBar jMenuBar = new JMenuBar();
    final JMenu jMenu = new JMenu("INOSS");
    final JMenuItem jMenuItem = new JMenuItem("Exit");
    jMenuItem.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    jMenu.add(jMenuItem);
    jMenuBar.add(jMenu);
    this.setJMenuBar(jMenuBar);



    this.addListeners();



    this.setMaximizedBounds(rectWhere2ShowInoss);
    this.setResizable(false);
    this.setLayout(new BorderLayout());
    this.add(jp);
    this.setBounds(rectWhere2ShowInoss);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    if(WindowTests.mayMaximize) {
      this.setExtendedState(Frame.MAXIMIZED_BOTH);
    }


    //this.setUndecorated(true);
    //this.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);


    this.setVisible(true);

    if(WindowTests.mayMaximize) {
      this.setExtendedState(Frame.MAXIMIZED_BOTH);
    }


//    this.removeAllTitleFrameButtons();


//
//    final GraphicsDevice graphicsDevice = graphicsConfiguration.getDevice();
//
//    try {
//
//      this.setUndecorated(true);
//      graphicsDevice.setFullScreenWindow(this);
//
//      final DisplayMode oldDisplayMode = graphicsDevice.getDisplayMode();
//      graphicsDevice.setDisplayMode(oldDisplayMode);
//
//    } catch(Exception e) {
//      graphicsDevice.setFullScreenWindow(null);
//      this.setUndecorated(false);
//      this.setVisible(true);
//
//      e.printStackTrace();
//    }

  }

  private void addListeners() {
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentMoved(final ComponentEvent evt) {

        final Rectangle recInoss = WindowTests.this.getBounds();

        if(!WindowTests.rectWhere2ShowInoss.contains(recInoss)){
          System.out.println("window componentMoved OUTSIDE THE ALLOWED DESKTOP...");
          WindowTests.robot.mouseRelease(InputEvent.BUTTON1_MASK);

          if(mayMaximize){
            final ComponentListener[] compListeners = WindowTests.this.getComponentListeners();
            for (ComponentListener compListener : compListeners) {
              WindowTests.this.removeComponentListener(compListener);
            }

            WindowTests.this.setBounds(rectWhere2ShowInoss);
            WindowTests.this.setExtendedState(Frame.MAXIMIZED_BOTH);

            //graphicsConfiguration.getDevice().setFullScreenWindow(WindowTests.this);

            for (ComponentListener compListener : compListeners) {
              WindowTests.this.addComponentListener(compListener);
            }
          }
        }
      }
    });


    this.addWindowListener(new WindowAdapter() {

      @Override
      public void windowIconified(final WindowEvent winEvt) {
        if (mayMaximize) {
          setExtendedState(Frame.MAXIMIZED_BOTH);
        }
        //setState(JFrame.NORMAL);
      }

//      @Override
//      public void windowOpened(WindowEvent e) {
//        if (mayMaximize) {
//          setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
//      }
//      @Override
//      public void windowDeiconified(WindowEvent e) {
//        if (mayMaximize) {
//          setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
//      }
//      @Override
//      public void windowActivated(WindowEvent e) {
//        if (mayMaximize) {
//          setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
//      }
//      @Override
//      public void windowClosing(WindowEvent e) {}
//      @Override
//      public void windowClosed(WindowEvent e) {}
//      @Override
//      public void windowDeactivated(WindowEvent e) {}
    });
  }

  /**
   * Removes the buttons from the JDialog title frame. This is a work around
   * to removing the close button
   *
   * This is confirmed to work with the Metal L&F ??????????????????????????????????????????????
   */
  public void removeAllTitleFrameButtons() {

    /* Get the components of the dialog */
    final Component[] comps = this.getRootPane().getComponents();

    /* Indicator to break from loop */
    boolean breakFromLoop = false;

    /*
     * Go through the components and find the title
     * pane and remove the buttons.
     */
    for (Component comp : comps) {
      /* Shall we break from loop */
      if (breakFromLoop) {
        break;
      }
      if (comp.getClass().getName().indexOf("JLayeredPane") > 0) {
        for (Component jcomp : ((JLayeredPane) comp).getComponents()) {
          if (jcomp.getClass().getName().indexOf("Title") > 0) {

            /* Get the XXXXTitlePane Components */
            final Component[] titlePaneComps = ((JComponent) jcomp).getComponents();

            for (Component tpComp : titlePaneComps) {
              if (tpComp instanceof JButton) {
                ((JButton) tpComp).setVisible(false);
              }
            }
            /* No need to continue processing */
            breakFromLoop = true;
            //break;
          }
        }
      }
    }
  }


  public static void main(final String[] args){
    final WindowTests clazz = new WindowTests();
  }

}
