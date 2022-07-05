package exp.jmf;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import javax.swing.*;
//import javax.swing.event.*;
//import java.io.*;
////import javax.media.*;
////import javax.media.format.*;
////import javax.media.util.*;
////import javax.media.control.*;
////import javax.media.protocol.*;
//import java.util.*;
import java.awt.*;
import java.awt.event.*;
//import com.sun.image.codec.jpeg.*;
//import java.net.*;

public class CaptureWindow
    extends JPanel
    implements ActionListener {

//  public static Player player = null;
//  public CaptureDeviceInfo di = null;
//  public MediaLocator ml = null;
//  public JButton capture = null;
//  public Buffer buf = null;
//  public Image img = null;
//  public VideoFormat vf = null;
//  public BufferToImage btoi = null;
//  public ImagePanel imgpanel = null;
//  //Uploader tf = new Uploader();
//  private javax.swing.Timer timer;
//  public static int delayTime = 30000;
//  public String filename = "Testing.jpg";
//  public boolean active = true;
//
//  public CaptureWindow() {
//    createFrame();
//  }
//
//  public CaptureWindow(int del) {
//    delayTime = del;
//    createFrame();
//  }
//
//  public void createFrame() {
//    setLayout(new BorderLayout());
//    setSize(320, 550);
//
//    imgpanel = new ImagePanel();
//    capture = new JButton("Capture");
//    capture.addActionListener(this);
//
//    //DeviceTest.detectDevices();
//    timer = new javax.swing.Timer(delayTime, new TimerListener());
//
//    di = CaptureDeviceManager.getDevice("vfw:3Com HomeConnect Digital Camera AVI Driver:0");
//    //di = DeviceTest.getVideoDevice();
//    ml = di.getLocator();
//
//    try {
//      player = Manager.createRealizedPlayer(ml);
//      player.start();
//      Component comp;
//
//      if ( (comp = player.getVisualComponent()) != null) {
//        add(comp, BorderLayout.NORTH);
//      }
//
//      add(capture, BorderLayout.CENTER);
//      add(imgpanel, BorderLayout.SOUTH);
//
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//    this.startUpload();
//  }
//
//  public void startUpload() {
//    if (active) {
//      timer.start();
//    }
//
//  }
//
//  public void stopUpload() {
//    if (timer.isRunning()) {
//      timer.stop();
//    }
//
//  }
//
//  public static void playerclose() {
//    player.close();
//    player.deallocate();
//  }
//
//  public void CaptureIt() {
//
//    FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl(
//        "javax.media.control.FrameGrabbingControl");
//    buf = fgc.grabFrame();
//    btoi = new BufferToImage( (VideoFormat) buf.getFormat());
//    java.awt.image.BufferedImage img = (java.awt.image.BufferedImage) btoi.
//        createImage(buf);
//
//    try {
//      File file = new File(filename);
//      FileOutputStream out = new FileOutputStream(file);
//      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//      JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(img);
//      param.setQuality(0.7f, false);
//      encoder.setJPEGEncodeParam(param);
//      encoder.encode(img);
//      out.close();
//
//    }
//    catch (Exception ex) {
//      ex.printStackTrace();
//    }
//    //tf.main(filename);
//    imgpanel.setImage(img);
//  }

  public void actionPerformed(ActionEvent e) {
//    JComponent c = (JComponent) e.getSource();
//
//    if (c == capture) {
//
//      CaptureIt();
//
//    }
  }

//  class TimerListener
//      implements ActionListener {
//    public void actionPerformed(ActionEvent evt) {
//      CaptureIt();
//    }
//  }
//
//  class ImagePanel
//      extends Panel {
//
//    public Image myimg = null;
//
//    public ImagePanel() {
//      setLayout(null);
//      setSize(320, 240);
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
//
//  }

}
