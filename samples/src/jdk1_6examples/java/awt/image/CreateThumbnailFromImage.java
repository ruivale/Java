/**
 * <p>
 * Classname:  jdk1_6examples.java.awt.image.CreateThumbnailFromImage
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_6examples.java.awt.image;



import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class CreateThumbnailFromImage {

//  
//  /** This class LOGGER */
//  private static final Logger LOGGER =
//                              Logger.getLogger(CreateThumbnailFromImage.class.getName());
//
//  /**
//   * The CreateThumbnailFromImage default constuctor.
//   */
//  public CreateThumbnailFromImage() {
//  }
//
//  /**
//   * 
//   * @param filename
//   * @param thumbWidth
//   * @param thumbHeight
//   * @param quality
//   * @param outFilename
//   * @throws java.lang.InterruptedException
//   * @throws java.io.FileNotFoundException
//   * @throws java.io.IOException
//   */
//  private void createThumbnail(
//      String filename,
//      int thumbWidth,
//      int thumbHeight,
//      int quality,
//      String outFilename) throws InterruptedException,
//                                 FileNotFoundException,
//                                 IOException {
//
//    // load image from filename
//    Image image = Toolkit.getDefaultToolkit().getImage(filename);
//    MediaTracker mediaTracker = new MediaTracker(new Container());
//    mediaTracker.addImage(image, 0);
//    mediaTracker.waitForID(0);
//    // use this to test for errors at this point: System.out.println(mediaTracker.isErrorAny());
//
//    // determine thumbnail size from WIDTH and HEIGHT
//    double thumbRatio = (double) thumbWidth / (double) thumbHeight;
//    int imageWidth = image.getWidth(null);
//    int imageHeight = image.getHeight(null);
//    double imageRatio = (double) imageWidth / (double) imageHeight;
//    if (thumbRatio < imageRatio) {
//      thumbHeight = (int) (thumbWidth / imageRatio);
//    } else {
//      thumbWidth = (int) (thumbHeight * imageRatio);
//    }
//
//    // draw original image to thumbnail image object and
//    // scale it to the new size on-the-fly
//    BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
//    Graphics2D graphics2D = thumbImage.createGraphics();
//    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//    graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
//
//    // save thumbnail image to outFilename
//    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
//    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//    JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
//    quality = Math.max(0, Math.min(quality, 100));
//    param.setQuality((float) quality / 100.0f, false);
//    encoder.setJPEGEncodeParam(param);
//    encoder.encode(thumbImage);
//    out.close();
//  }
//  
//public static void saveAsJPEG(String jpgFlag,BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos) throws IOException {
// 
//    //useful documentation at http://docs.oracle.com/javase/7/docs/api/javax/imageio/metadata/doc-files/jpeg_metadata.html
//    //useful example program at http://johnbokma.com/java/obtaining-image-metadata.html to output JPEG data
// 
//    //old jpeg class
//    //com.sun.image.codec.jpeg.JPEGImageEncoder jpegEncoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(fos);
//    //com.sun.image.codec.jpeg.JPEGEncodeParam jpegEncodeParam = jpegEncoder.getDefaultJPEGEncodeParam(image_to_save);
// 
//    // Image writer
//    JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix(?jpeg?).next();
//    ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
//    imageWriter.setOutput(ios);
// 
//    //and metadata
//    IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);
// 
//    if (jpgFlag != null){
// 
//        int dpi = 96;
// 
//        try {
//            dpi = Integer.parseInt(jpgFlag);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
// 
//        //old metadata
//        //jpegEncodeParam.setDensityUnit(com.sun.image.codec.jpeg.JPEGEncodeParam.DENSITY_UNIT_DOTS_INCH);
//        //jpegEncodeParam.setXDensity(dpi);
//        //jpegEncodeParam.setYDensity(dpi);
// 
//        //new metadata
//        Element tree = (Element) imageMetaData.getAsTree(?javax_imageio_jpeg_image_1.0?);
//        Element jfif = (Element)tree.getElementsByTagName(?app0JFIF?).item(0);
//        jfif.setAttribute(?Xdensity?, Integer.toString(dpi));
//        jfif.setAttribute(?Ydensity?, Integer.toString(dpi));
// 
//    }
// 
//    if(JPEGcompression>=0 && JPEGcompression<=1f){
// 
//        //old compression
//        //jpegEncodeParam.setQuality(JPEGcompression,false);
// 
//        // new Compression
//        JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
//        jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
//        jpegParams.setCompressionQuality(JPEGcompression);
// 
//    }
// 
//    //old write and clean
//    //jpegEncoder.encode(image_to_save, jpegEncodeParam);
// 
//    //new Write and clean up
//    imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
//    ios.close();
//    imageWriter.dispose();
// 
//}
//
//  public static void main(final String[] args) {
//    final CreateThumbnailFromImage clazz = new CreateThumbnailFromImage();
//  }
}
