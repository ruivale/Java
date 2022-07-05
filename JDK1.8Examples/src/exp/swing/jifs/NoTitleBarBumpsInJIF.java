package exp.swing.jifs;


import javax.swing.*;
import javax.swing.plaf.basic.*;
import java.awt.*;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * the metal bumps code is located in MetalInternalFrameTitlePane in the
 * paintComponent method and the paintPalette method. I wanted to remove
 * these bumps, so what I did was I replaced the MetalInternalFrameTitlePane
 * with a BasicInternalFrameTitlePane, which doesn't have bumps :)
 *
 * setRootPaneCheckingEnabled(false);
 * ((BasicInternalFrameUI)getUI()).setNorthPane(
 * new BasicInternalFrameTitlePane(this));
 * setRootPaneCheckingEnabled(true);
 *
 * and voila, no more bumps :). setRootPaneCheckingEnabled is necessary
 * because setting the title pane makes a call to "add", so you need to
 * disable the checking for this then reenable it. This code was in a class
 * that extended JInternalFrame.
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class NoTitleBarBumpsInJIF
    extends JInternalFrame {

  /**
   *
   */
  public NoTitleBarBumpsInJIF() {

    super("GOOD", false,true,false,true);

    setRootPaneCheckingEnabled(false);
    ((BasicInternalFrameUI) getUI()).setNorthPane(
        new BasicInternalFrameTitlePane(this));
    setRootPaneCheckingEnabled(true);

  }

  public static void main(String[] args) {
    NoTitleBarBumpsInJIF n = new NoTitleBarBumpsInJIF();
    JDesktopPane jdp = new JDesktopPane();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(jdp, BorderLayout.CENTER);

    jdp.add(n);
    n.setVisible(true);
    n.setTitle("AHAHAHAHA");
    n.setBounds(10,10,300,200);
    f.setBounds(100,100,500,300);
    f.setVisible(true);
  }
}







