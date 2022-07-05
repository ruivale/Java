package exp.jmf;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

//import javax.swing.*;
//import javax.swing.event.*;
//import java.io.*;
//import javax.media.*;
//import javax.media.format.*;
//import javax.media.util.*;
//import javax.media.control.*;
//import javax.media.protocol.*;
//import java.util.*;
//import java.awt.*;
//import java.awt.image.*;
//import java.awt.event.*;
//import com.sun.image.codec.jpeg.*;

public class PassportPhoto
    //extends JInternalFrame    implements ActionListener
{
//  public static Player player = null;
//  public CaptureDeviceInfo di = null;
//  public MediaLocator ml = null;
//  public JButton capture = null;
//  public Buffer buf = null;
//  public Image img = null;
//  public VideoFormat vf = null;
//  public BufferToImage btoi = null;
//  public ImagePanel imgpanel = null;
//
//  public PassportPhoto(String empID, String empName) {
//    super("Photo Capture " + empName, false, true, false, true);
//    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // exit control
//    addInternalFrameListener(new InternalFrameAdapter() {
//      public void internalFrameActivated(InternalFrameEvent e) {}
//
//      public void internalFrameClosed(InternalFrameEvent e) {}
//
//      public void internalFrameClosing(InternalFrameEvent e) {
//        ////EmployeeTree.PassportPhotoOpen = false;
//        playerclose();
//        dispose();
//      }
//
//      public void internalFrameDeactivated(InternalFrameEvent e) {}
//
//      public void internalFrameDeiconified(InternalFrameEvent e) {}
//
//      public void internalFrameIconified(InternalFrameEvent e) {}
//
//      public void internalFrameOpened(InternalFrameEvent e) {}
//    });
//    JPanel videoPanel = new JPanel();
//    capture = new JButton("Capture");
//    capture.setSize(new Dimension(79, 26));
//    capture.setMinimumSize(new Dimension(79, 26));
//    capture.setMaximumSize(new Dimension(79, 26));
//    capture.addActionListener(this);
//    imgpanel = new ImagePanel();
//    //String str1 = "vfw:Logitech USB Video Camera:0";
//    //String str2 = "vfw:Microsoft WDM Image Capture (Win32):0";
//    //di = CaptureDeviceManager.getDevice(str1);
//    //ml = di.getLocator();
//    // remove above and substitute below
//    ml = new MediaLocator("vfw://0");
//    try {
//      Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));
//      player = Manager.createRealizedPlayer(ml);
//      player.start();
//      Component comp;
//      if ( (comp = player.getVisualComponent()) != null) {
//        videoPanel.add(comp, BorderLayout.CENTER);
//        videoPanel.setSize(new Dimension(320, 240));
//        videoPanel.setMinimumSize(new Dimension(320, 240));
//        videoPanel.setMaximumSize(new Dimension(320, 240));
//      }
//      else {
//        // actually will warn user, log error and exit here
//        System.out.println("null");
//      }
//    }
//    catch (Exception e) {
//      // actually will warn user, log error and exit here
//      e.printStackTrace();
//    }
//    /******************************/
//    /* assemble the final screen  */
//    /******************************/
//    Container contentPane = getContentPane();
//    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
//    contentPane.add(videoPanel, BorderLayout.WEST);
//    contentPane.add(capture, BorderLayout.CENTER);
//    contentPane.add(imgpanel, BorderLayout.EAST);
//    setSize(730, 275); // window size
//    setMinimumSize(new Dimension(730, 275));
//    setMaximumSize(new Dimension(730, 275));
//    setLocation(30, 30); // window location
//  }
//
//  public static void playerclose() {
//    player.close();
//    player.deallocate();
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    JComponent c = (JComponent) e.getSource();
//    if (c == capture) {
//      // Grab the frame
//      FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl(
//          "javax.media.control.FrameGrabbingControl");
//      buf = fgc.grabFrame();
//      btoi = new BufferToImage( (VideoFormat) buf.getFormat()); // Convert to image
//      img = btoi.createImage(buf);
//      imgpanel.setImage(img); // display the image
//      // actually will have a separate save routine
//      saveJPG(img, "c:\\test.jpg"); // save image
//    }
//  }
//
//  class ImagePanel
//      extends JPanel {
//    public Image myimg = null;
//    public ImagePanel() {
//      setSize(320, 240);
//      setMinimumSize(new Dimension(320, 240));
//      setMaximumSize(new Dimension(320, 240));
//    }
//
//    public void setImage(Image img) {
//      this.myimg = img;
//      repaint();
//    }
//
//    public void paint(Graphics g) {
//      if (myimg != null) {
//        g.drawImage(myimg, 0, 0, this);
//      }
//    }
//  }
//
//  public static void saveJPG(Image img, String s) {
//    BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null),
//                                         BufferedImage.TYPE_INT_RGB);
//    Graphics2D g2 = bi.createGraphics();
//    g2.drawImage(img, null, null);
//    FileOutputStream out = null;
//    try {
//      out = new FileOutputStream(s);
//    }
//    catch (java.io.FileNotFoundException io) {
//      // again actually will warn user, log error and exit here
//      System.out.println("File Not Found");
//    }
//    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//    JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
//    param.setQuality(0.5f, false);
//    encoder.setJPEGEncodeParam(param);
//    try {
//      encoder.encode(bi);
//      out.close();
//    }
//    catch (java.io.IOException io) {
//      // again actually will warn user, log error and exit here
//      System.out.println("IOException");
//    }
//  }
}


/*
...
public static boolean PassportPhotoOpen = false;
public static JInternalFrame photoFrameReference;

...

    // program sample menu item creation
    mItem = new JMenuItem("Capture Photo");
    mItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String empID = nText.employeeID; // derived from tree node
        String empName = nText.employeeName;
        createPhotoFrame(empID,empName);
      }
    });
    popup.add(mItem);

...

// routine that is executed above that creates the photo capture internal frame
protected void createPhotoFrame(String empID, String empName) {
  // kill photo capture frame if it exists
  if (PassportPhotoOpen){
    PassportPhoto.playerclose(); // rather use the reference?
    photoFrameReference.dispose();
    PassportPhotoOpen = false;
  }
  // create and display frame
  PassportPhoto frame = new PassportPhoto(empID, empName);
  frame.setVisible(true);
  ImageIcon frameIcon = new ImageIcon(Passport.class.getClassLoader().getResource("images/SmallWIM.gif"));
  frame.setFrameIcon(frameIcon);
  PassportPhotoOpen = true;
  photoFrameReference = frame;
  frame.setRequestFocusEnabled(true);
  try {frame.setSelected(true);}
  catch (java.beans.PropertyVetoException e) {} // ignore as obvious if no frame appears
  frame.toFront();
}

...
*/
