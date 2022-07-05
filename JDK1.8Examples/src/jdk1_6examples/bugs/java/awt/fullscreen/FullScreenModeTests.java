package jdk1_6examples.bugs.java.awt.fullscreen;

import java.awt.*;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */
public class FullScreenModeTests {

  /**
   *
   */
  public FullScreenModeTests() {

    final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    final GraphicsDevice[] gdlist = ge.getScreenDevices();
    final GraphicsDevice myDevice = ge.getDefaultScreenDevice();

    final javax.swing.JDialog dialog = new javax.swing.JDialog();
    dialog.setModal(true);
    dialog.setResizable(false);
    dialog.setUndecorated(true);
    javax.swing.JButton b = new javax.swing.JButton(" E X I T ");
    b.addActionListener(new java.awt.event.ActionListener(){
      public void actionPerformed(java.awt.event.ActionEvent e){
        dialog.setVisible(false);
      }
    });
    dialog.getContentPane().setLayout(new BorderLayout());
    dialog.getContentPane().add(b);
    dialog.pack();

    final DisplayMode oldDisplayMode = myDevice.getDisplayMode();
    final DisplayMode newDisplayMode =
        new DisplayMode(1024,
                        768,
                        oldDisplayMode.getBitDepth(),
                        oldDisplayMode.getRefreshRate());

    if(myDevice != null &&
        myDevice.isFullScreenSupported()){
      System.out.println("The device does support the full screen mode.");


      /**/
      try {
        myDevice.setFullScreenWindow(dialog);

        if(myDevice.isDisplayChangeSupported()){
          System.out.println("The device does support the display change.");
          //myDevice.setDisplayMode(newDisplayMode);
        }else{
          System.out.println("The device does NOT support the display change.");
        }
      } finally {
        myDevice.setDisplayMode(oldDisplayMode);
        myDevice.setFullScreenWindow(null);
      }
      /**/
    }else{
      System.out.println("The device does NOT support the full screen mode.");
    }
  }

  public static void main(String[] args) {
    FullScreenModeTests fullscreenmodetests = new FullScreenModeTests();
  }
}
