package exp.image;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

/*
 * Copyright (c) 2002 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
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
 * You acknowledge that Software is not designed,licensed or intended for use in
 * the design, construction, operation or maintenance of any nuclear facility.
 */

import java.awt.*;
import java.awt.color.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.image.renderable.*;
import java.util.*;
//import javax.media.jai.*;
//import javax.media.jai.operator.*;
//import javax.media.jai.widget.*;

/**
 * Demo code for thresholding an 8-bit grayscale image to a monochrome (1-bit
 * or bilevel) image. The source image must be an 8-bit grayscale image.
 * The result is displayed.
 *
 * Usage: java Binarize8 filename
 */
public class Binarize8
    extends Frame {
//
//  public static void main(String[] args) {
//    new Binarize8("D:/Rui/Private/_Cool/a.jpg");
//  }
//
//  Binarize8(String fileName) {
//    // Exit on window closing.
//    addWindowListener(new WindowAdapter() {
//      public void windowClosing(WindowEvent we) {
//        System.exit(0);
//      }
//    });
//
//    // Load the file.
//    PlanarImage src = JAI.create("fileload", fileName);
//
//    // Generate a histogram.
//    Histogram histogram =
//        (Histogram) JAI.create("histogram", src).getProperty("histogram");
//
//    // Get a threshold equal to the median.
//    double[] threshold = histogram.getPTileThreshold(0.5);
//
//    // Binarize the image.
//    PlanarImage dst =
//        JAI.create("binarize", src, new Double(threshold[0]));
//
//    // Display the result.
//    add(new ScrollingImagePanel(dst, dst.getWidth(), dst.getHeight()));
//    pack();
//    show();
//  }
}
