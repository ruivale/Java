package exp.jmf;

//import java.io.IOException;
//import java.awt.Image;
//import java.awt.Dimension;
//import java.awt.event.*;
//import javax.swing.*;
//
//import javax.media.*;
//import javax.media.format.*;
//import javax.media.protocol.*;
//import javax.media.control.FormatControl;
//import javax.media.util.BufferToImage;
//import javax.media.bean.playerbean.MediaPlayer;
//
//import jmapps.util.*;

/**
 * This program assumes you have already entered your camera
 * into the JMFRegistry. To do this, make sure JMF is installed, then start
 * JMFRegistry:
 * Type "java JMFRegistry" at the command line. Click on the "Capture Devices"
 * tab, and make sure your device has been detected. Click on the "Detect
 * Capture Devices" button at the bottom if it has not been detected.
 * <P>
 * Next, test your camera by running "java JMStudio".
 * Go to File -> Capture, then click Ok.
 * Your live camera video should appear.
 * <P>
 * First, run this program with "java WatchUSBCamera /video" to display
 * the video in a frame.
 * <P>
 * Once that is working, run it without the command-line argument:
 * "java WatchUSBCamera".
 * After each frame is processed, a counter is printed to the screen.
 * If it doesn't work, the video format on your camera is probably different
 * than the formats available on the camera I used. You may just need
 * to modify the method getDesiredFormat().
 * <P>
 * Note: My camera uses the USB port, but as long as the JMFRegistry can
 * detect your camera, this code care what port it's attached to.
 */
public class WatchUSBCamera
//    extends jmapps.ui.PlayerFrame
{
//  public static final int DISPLAY_ON_EVENT = 0;
//  public static final int DISPLAY_ALL_VIDEO = 1;
//  protected int displayMode;
//  private static JMAppsCfg cfgJMApps = null;
//  private DataSource dataSource;
//  private VideoFormat videoFormat;
//  private ImageTransfer imageTransfer;
//
//  public WatchUSBCamera(int displayMode) {
//    super(null, "USB Camera Viewer");
//    this.displayMode = displayMode;
//    addWindowListener(new WindowAdapter() {
//      public void windowClosing(WindowEvent e) {
//        if (dataSource != null) {
//          try {
//            dataSource.stop();
//          }
//          catch (IOException ee) {}
//          dataSource.disconnect();
//        }
//        System.exit(0);
//      }
//    });
//  }
//
//  private void captureMedia() {
//    String nameCaptureDeviceVideo =
//        cfgJMApps.getLastCaptureVideoData().strDeviceName;
////"vfw:3Com HomeConnect Digital Camera AVIDriver:0";
//    videoFormat =
//        (VideoFormat) cfgJMApps.getLastCaptureVideoData().format;
//
//    dataSource = JMFUtils.createCaptureDataSource(
//        null, null, //Null Audio devices
//        nameCaptureDeviceVideo,
//        videoFormat); //dialogCapture.getVideoFormat() );
//
//    if (dataSource == null) {
//      JOptionPane.showMessageDialog(this,
//                                    "Could not connect to camera\nNotInstalled or not plugged in",
//                                    "Camera Error",
//                                    JOptionPane.ERROR_MESSAGE);
//      System.exit(1);
//    }
//    else {
//
//      FormatControl control =
//          ( (CaptureDevice) dataSource).getFormatControls()[0];
//      videoFormat = getDesiredFormat(control);
//      imageTransfer = new
//          ImageTransfer(videoFormat);
//      System.out.println("Format =" + videoFormat);
//      control.setFormat(videoFormat);
//      try {
//        dataSource.connect();
//      }
//      catch (IOException ioe) {
//
//        JOptionPane.showMessageDialog(this,
//                                      "Could not connect to camera\n" + ioe,
//                                      "Camera Error",
//                                      JOptionPane.ERROR_MESSAGE);
//        System.exit(1);
//      }
//      open(dataSource);
//
//    }
//
//    if (displayMode == DISPLAY_ALL_VIDEO) {
//      pack();
//      setVisible(true);
//    }
//    else {
//      JButton imageButton = new JButton("Display Frame ");
//      imageButton.addActionListener(new
//                                    ActionListener() {
//        public void
//            actionPerformed(ActionEvent e) {
//
//          imageTransfer.displayCurrentImage = true;
//        }
//      });
//      javax.swing.JFrame eventFrame = new
//          JFrame("Event Frame");
//
//      eventFrame.getContentPane().add(imageButton);
//      eventFrame.pack();
//      eventFrame.setVisible(true);
//    }
//  }
//
//  private VideoFormat getDesiredFormat(FormatControl
//                                       control) {
//    Format[] formats = control.getSupportedFormats();
////System.out.println("There are"+formats.length+" formats");
//    for (int i = 0; i < formats.length; i++) {
////System.out.println("Format "+i+" ="+formats);
//      if (! (formats[i] instanceof VideoFormat)) {
//        continue;
//      }
//
//      Dimension size =
//          ( (VideoFormat) formats[i]).getSize();
//      if
//          (formats[i].getEncoding().equals("rgb") &&
//           size.width == 640 &&
//           size.height == 480 &&
//           (displayMode ==
//            DISPLAY_ALL_VIDEO || ( (VideoFormat) formats[i]).getMaxDataLength()
//            == 921600)) {
//        return (VideoFormat) formats[i];
//      }
//
//    }
//    throw new RuntimeException("RGB 640x480 format not found");
//
//  }
//
//  public void open(DataSource dataSource) {
//    MediaPlayer mediaPlayer = new MediaPlayer();
//    mediaPlayer.setDataSource(dataSource);
//
//    if (mediaPlayer.getPlayer() == null) {
//      return;
//    }
//
//    killCurrentPlayer();
//    mediaPlayerCurrent = mediaPlayer;
//    mediaPlayer.setControlPanelVisible(false);
//    mediaPlayer.addControllerListener(this);
//    mediaPlayer.realize();
//  }
//
//  public synchronized void controllerUpdate(ControllerEvent
//                                            event) {
//    super.controllerUpdate(event);
//
//    if (event instanceof StartEvent && displayMode !=
//        DISPLAY_ALL_VIDEO) {
//      PushBufferStream bufStream =
//          ( (PushBufferDataSource) dataSource).getStreams()[0];
//
//      bufStream.setTransferHandler(imageTransfer);
////Setting the transfer handler at this point will enable the image pushing
////to the ImageTransfer obect. The images will no longer be displayed in the frame.
////Comment out this method if you simply want to display the video in the frame
//    }
//  }
//
//  public static void main(String args[]) {
//
//    int displayMode = DISPLAY_ON_EVENT;
//    for (int i = 0; i < args.length; i++) {
//      if (args.equals("/video")) {
//        displayMode = DISPLAY_ALL_VIDEO;
//      }
//    }
//
//    cfgJMApps = new JMAppsCfg();
//    WatchUSBCamera watcher = new
//        WatchUSBCamera(displayMode);
//    watcher.captureMedia();
//  }
//
//  public void takeSnapShot() {
// /*
//   try {
//      Control[] controls = player.getControls();
//      FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl(
//          "javax.media.control.FrameGrabbingControl");
//      if (fgc != null) {
//        Buffer buf = fgc.grabFrame();
//        javax.media.util.BufferToImage bti = new javax.media.util.BufferToImage( (
//            VideoFormat) buf.getFormat());
//        if (buf != null) {
//          try {
//            Image image = bti.createImage(buf);
//            ImageDisplayer f = new ImageDisplayer(image);
//            f.setSize();
//            f.setVisible(true);
//          }
//          catch (Exception imex) {
//            System.out.println(
//                "could not creat snapshot !!! nullpointer line 560 in VideoWindow");
//          }
//        }
//      }
//    }
//    catch (Exception ex) {
//      System.out.println("Error in snapshot module in videowindow");
//    }
//*/
//  }
//
//}
//
//class ImageTransfer
//    implements BufferTransferHandler {
//  int cnt;
//  ColorImage image;
//  Buffer buffer;
//  BufferToImage bufToImage;
//  boolean displayCurrentImage;
//  KeyAdapter showImageEvent;
//
//  public ImageTransfer(VideoFormat videoFormat) {
//    image = new ColorImage(640, 480);
//    buffer = new Buffer();
//    buffer.setFormat(videoFormat);
//    bufToImage = new BufferToImage(videoFormat);
//    showImageEvent = new KeyAdapter() {
//      public void keyPressed(KeyEvent e) {
//        if (e.getKeyChar() == 'i') {
//          displayCurrentImage =
//              true;
//        }
//      }
//    };
//  }
//
//  public void transferData(PushBufferStream stream) {
////long start = System.currentTimeMillis();
//    System.out.print(cnt++ +" ");
//    if (cnt % 10 == 0) {
//      System.out.println();
//    }
//    try {
//      stream.read(buffer);
//
//      byte[] data = (byte[]) buffer.getData();
//      image.update(data);
//
////Display image to make sure it is being decoded correctly
//      if (displayCurrentImage) {
//        displayCurrentImage = false;
//        java.awt.Image jImage =
//            bufToImage.createImage(buffer);
////jImage will be null if the format is not the same in the buffer and in bufToImage
//        show(jImage, "Image " + cnt);
//      }
//    }
//    catch (java.io.IOException ex) {
//      System.out.println("EXCEPTION");
//    }
////System.out.println(" Total: "+(System.currentTimeMillis() - start));
//  }
//
//  private void show(Image image, String title) {
//    javax.swing.JLabel label = new
//        javax.swing.JLabel(new javax.swing.ImageIcon(image));
//    javax.swing.JFrame frame = new
//        javax.swing.JFrame(title);
//    frame.getContentPane().add(label);
//    frame.addKeyListener(showImageEvent);
//    frame.pack();
//    frame.setVisible(true);
//  }
//}
//
//class ColorImage {
//  byte[][][] pixels;
//  int width, height;
//
//  public ColorImage(int width, int height) {
//    pixels = new byte[width][height][3];
//    this.width = width;
//    this.height = height;
//  }
//
//  public void update(byte[] camData) {
//    /*
//        int index = camData.length - 1;
//        for (int j = 0; j < height; j++) {
//          for (int i = 3 - 1; i >= 0; i--) {
//            pixels[j][0] = camData[index--];
//            pixels[j][1] = camData[index--];
//            pixels[j][2] = camData[index--];
//          }
//        }
//     */
//  }
//
//  private void processImage() {
////** Perform image processing here **
//  }
}
