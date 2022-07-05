package exp.jmf;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

/*
 * @(#)FrameAccess.java 1.5 01/03/13
 *
 * Copyright (c) 1999-2001 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

//import java.awt.*;
//import javax.media.*;
//import javax.media.control.TrackControl;
//import javax.media.Format;
//import javax.media.format.*;
//import java.io.*;
//import javax.imageio.*;
//import javax.imageio.stream.*;
//import java.awt.image.*;
//import java.util.*;
//import javax.media.util.*;

/**
 * Sample program to access individual video frames by using a
 * "pass-thru" codec. The codec is inserted into the data flow
 * path. As data pass through this codec, a callback is invoked
 * for each frame of video data.
 */
public class FrameAccess
//    implements ControllerListener
{
//
//  Processor p;
//  Object waitSync = new Object();
//  boolean stateTransitionOK = true;
//  public boolean alreadyPrnt = false;
//
//  /**
//   * Given a media locator, create a processor and use that processor
//   * as a player to playback the media.
//   *
//   * During the processor's Configured state, two "pass-thru" codecs,
//   * PreAccessCodec and PostAccessCodec, are set on the video track.
//   * These codecs are used to get access to individual video frames
//   * of the media.
//   *
//   * Much of the code is just standard code to present media in JMF.
//   */
//  public boolean open(MediaLocator ml) {
//
//    try {
//      p = Manager.createProcessor(ml);
//    }
//    catch (Exception e) {
//      System.err.println(
//          "Failed to create a processor from the given url: " + e);
//      return false;
//    }
//
//    p.addControllerListener(this);
//
//    // Put the Processor into configured state.
//    p.configure();
//    if (!waitForState(Processor.Configured)) {
//      System.err.println("Failed to configure the processor.");
//      return false;
//    }
//
//    // So I can use it as a player.
//    p.setContentDescriptor(null);
//
//    // Obtain the track controls.
//    TrackControl tc[] = p.getTrackControls();
//
//    if (tc == null) {
//      System.err.println(
//          "Failed to obtain track controls from the processor.");
//      return false;
//    }
//
//    // Search for the track control for the video track.
//    TrackControl videoTrack = null;
//
//    for (int i = 0; i < tc.length; i++) {
//      if (tc[i].getFormat() instanceof VideoFormat) {
//        videoTrack = tc[i];
//      }
//      else {
//        tc[i].setEnabled(false);
//      }
//    }
//
//    if (videoTrack == null) {
//      System.err.println("The input media does not contain a video track.");
//      return false;
//    }
//    String videoFormat = videoTrack.getFormat().toString();
//    Dimension videoSize = parseVideoSize(videoFormat);
//    System.err.println("Video format: " + videoFormat);
//
//    // Instantiate and set the frame access codec to the data flow path.
//    try {
//      Codec codec[] = {
//          new PostAccessCodec(videoSize)};
//      videoTrack.setCodecChain(codec);
//    }
//    catch (UnsupportedPlugInException e) {
//      System.err.println("The process does not support effects.");
//    }
//
//    // Realize the processor.
//    p.prefetch();
//    if (!waitForState(Processor.Prefetched)) {
//      System.err.println("Failed to realise the processor.");
//      return false;
//    }
//
//    p.start();
//    return true;
//  }
//
//  /**parse the size of the video from the string videoformat*/
//  public Dimension parseVideoSize(String videoSize) {
//    int x = 300, y = 200;
//    StringTokenizer strtok = new StringTokenizer(videoSize, ", ");
//    strtok.nextToken();
//    String size = strtok.nextToken();
//    StringTokenizer sizeStrtok = new StringTokenizer(size, "x");
//    try {
//      x = Integer.parseInt(sizeStrtok.nextToken());
//      y = Integer.parseInt(sizeStrtok.nextToken());
//    }
//    catch (NumberFormatException e) {
//      System.out.println(
//          "unable to find video size, assuming default of 300x200");
//    }
//    System.out.println("Image width  = " + String.valueOf(x) +
//                       "\nImage height = " + String.valueOf(y));
//    return new Dimension(x, y);
//  }
//
//  /**
//   * Block until the processor has transitioned to the given state.
//   * Return false if the transition failed.
//   */
//  boolean waitForState(int state) {
//    synchronized (waitSync) {
//      try {
//        while (p.getState() != state && stateTransitionOK) {
//          waitSync.wait();
//        }
//      }
//      catch (Exception e) {
//      }
//    }
//    return stateTransitionOK;
//  }
//
//  /**
//   * Controller Listener.
//   */
//  public void controllerUpdate(ControllerEvent evt) {
//
//    if (evt instanceof ConfigureCompleteEvent
//        || evt instanceof RealizeCompleteEvent
//        || evt instanceof PrefetchCompleteEvent) {
//      synchronized (waitSync) {
//        stateTransitionOK = true;
//        waitSync.notifyAll();
//      }
//    }
//    else if (evt instanceof ResourceUnavailableEvent) {
//      synchronized (waitSync) {
//        stateTransitionOK = false;
//        waitSync.notifyAll();
//      }
//    }
//    else if (evt instanceof EndOfMediaEvent) {
//      p.close();
//      System.exit(0);
//    }
//  }
//
//  /**
//   * Main program
//   */
//  public static void main(String[] args) {
//
///*
//    if (args.length == 0) {
//      prUsage();
//      System.exit(0);
//    }
//*/
//
//    String url = "file:/D:/Rui/Private/_Cool/panda.mpg";
//
//    if (url.indexOf(":") < 0) {
//      prUsage();
//      System.exit(0);
//    }
//
//    MediaLocator ml;
//
//    if ( (ml = new MediaLocator(url)) == null) {
//      System.err.println("Cannot build media locator from: " + url);
//      System.exit(0);
//    }
//
//    FrameAccess fa = new FrameAccess();
//
//    if (!fa.open(ml)) {
//      System.exit(0);
//    }
//  }
//
//  static void prUsage() {
//    System.err.println("Usage: java FrameAccess <url>");
//  }
//
//  /*********************************************************
//   * Inner class.
//   *
//   * A pass-through codec to access to individual frames.
//   *********************************************************/
//
//  public class PreAccessCodec
//      implements Codec {
//
//    /**
//     * Callback to access individual video frames.
//     */
//    void accessFrame(Buffer frame) {
//
//      // For demo, we'll just print out the frame #, time &
//      // data length.
//
//      long t = (long) (frame.getTimeStamp() / 10000000f);
//
//      System.err.println(
//          "Pre: frame #: "
//          + frame.getSequenceNumber()
//          + ", time: "
//          + ( (float) t) / 100f
//          + ", len: "
//          + frame.getLength());
//    }
//
//    /**
//     * The code for a pass through codec.
//     */
//
//    // We'll advertize as supporting all video formats.
//    protected Format supportedIns[] = new Format[] {
//        new VideoFormat(null)};
//
//    // We'll advertize as supporting all video formats.
//    protected Format supportedOuts[] = new Format[] {
//        new VideoFormat(null)};
//
//    Format input = null, output = null;
//
//    public String getName() {
//      return "Pre-Access Codec";
//    }
//
//    //these dont do anything
//    public void open() {}
//
//    public void close() {}
//
//    public void reset() {}
//
//    public Format[] getSupportedInputFormats() {
//      return supportedIns;
//    }
//
//    public Format[] getSupportedOutputFormats(Format in) {
//      if (in == null) {
//        return supportedOuts;
//      }
//      else {
//        // If an input format is given, we use that input format
//        // as the output since we are not modifying the bit stream
//        // at all.
//        Format outs[] = new Format[1];
//        outs[0] = in;
//        return outs;
//      }
//    }
//
//    public Format setInputFormat(Format format) {
//      input = format;
//      return input;
//    }
//
//    public Format setOutputFormat(Format format) {
//      output = format;
//      return output;
//    }
//
//    public int process(Buffer in, Buffer out) {
//
//      // This is the "Callback" to access individual frames.
//      accessFrame(in);
//
//      // Swap the data between the input & output.
//      Object data = in.getData();
//      in.setData(out.getData());
//      out.setData(data);
//
//      // Copy the input attributes to the output
//      out.setFlags(Buffer.FLAG_NO_SYNC);
//      out.setFormat(in.getFormat());
//      out.setLength(in.getLength());
//      out.setOffset(in.getOffset());
//
//      return BUFFER_PROCESSED_OK;
//    }
//
//    public Object[] getControls() {
//      return new Object[0];
//    }
//
//    public Object getControl(String type) {
//      return null;
//    }
//  }
//
//  public class PostAccessCodec
//      extends PreAccessCodec {
//    // We'll advertize as supporting all video formats.
//    public PostAccessCodec(Dimension size) {
//      supportedIns = new Format[] {
//          new RGBFormat()};
//      this.size = size;
//    }
//
//    /**
//     * Callback to access individual video frames.
//     */
//    void accessFrame(Buffer frame) {
//
//      // For demo, we'll just print out the frame #, time &
//      // data length.
//      if (!alreadyPrnt) {
//        BufferToImage stopBuffer = new BufferToImage( (VideoFormat) frame.
//            getFormat());
//        Image stopImage = stopBuffer.createImage(frame);
//        try {
//          BufferedImage outImage = new BufferedImage(size.width, size.height,
//              BufferedImage.TYPE_INT_RGB);
//          Graphics og = outImage.getGraphics();
//          og.drawImage(stopImage, 0, 0, size.width, size.height, null);
//          //prepareImage(outImage,rheight,rheight, null);
//          Iterator writers = ImageIO.getImageWritersByFormatName("jpg");
//          ImageWriter writer = (ImageWriter) writers.next();
//
//          //Once an ImageWriter has been obtained, its destination must be set to an ImageOutputStream:
//          File f = new File(frame.getSequenceNumber() + ".jpg");
//          ImageOutputStream ios = ImageIO.createImageOutputStream(f);
//          writer.setOutput(ios);
//
//          //Finally, the image may be written to the output stream:
//          //BufferedImage bi;
//          //writer.write(imagebi);
//          writer.write(outImage);
//          ios.close();
//        }
//        catch (IOException e) {
//          System.out.println("Error :" + e);
//        }
//      }
//      //alreadyPrnt = true;
//      long t = (long) (frame.getTimeStamp() / 10000000f);
//      System.err.println(
//          "Post: frame #: "
//          + frame.getSequenceNumber()
//          + ", time: "
//          + ( (float) t) / 100f
//          + ", len: "
//          + frame.getLength());
//    }
//
//    public String getName() {
//      return "Post-Access Codec";
//    }
//
//    private Dimension size;
//  }
}
