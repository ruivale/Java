package exp.images;


import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DifferencesInImages {


  public static boolean areImagesDifferent(Image img1, Image img2, double perCent){
    boolean areImagesDifferent = false;
    Graphics2D g2d1;
    Graphics2D g2d2;
    BufferedImage bi1;
    BufferedImage bi2;
    try{
      // First image
      bi1 = new BufferedImage(
          img1.getWidth(null), img1.getHeight(null), BufferedImage.TYPE_INT_RGB);
      g2d1 = bi1.createGraphics();
      g2d1.drawImage(img1, null, null);

      // Second image
      bi2 = new BufferedImage(
          img2.getWidth(null), img2.getHeight(null), BufferedImage.TYPE_INT_RGB);
      g2d2 = bi2.createGraphics();
      g2d2.drawImage(img2, null, null);

      int[][] array1 = new int[bi1.getWidth()][bi1.getHeight()];
      int[][] array2 = new int[bi2.getWidth()][bi2.getHeight()];

      int nbrOfPixels = bi1.getWidth()*bi1.getHeight();
      int nbrOfDiffPixels = 0;
      int imgWidth = bi1.getWidth();
      int imgHeight = bi1.getHeight();
      for(int i=0;  i<imgWidth; i++){
        for(int j=0; j<imgHeight; j++){
          if(bi1.getRGB(i, j) != bi2.getRGB(i, j)){
            nbrOfDiffPixels++;
//            System.out.println("bi1.getRGB(" + i + "," + j + ")=" +
  //                             bi1.getRGB(i, j) + ".");
    //        System.out.println("bi2.getRGB(" + i + "," + j + ")=" +
      //                         bi2.getRGB(i, j) + ".");
          }
        }
      }

//      System.out.println("nbrOfPixels="+nbrOfPixels+".");
  //    System.out.println("nbrOfDiffPixels="+nbrOfDiffPixels+".");
      if((nbrOfPixels*perCent) < nbrOfDiffPixels){
        areImagesDifferent = true;
    //    System.out.println("The nbr of diff pixels is greater than "+perCent+".");
      }


/*
      for(int i=0;  i<array1.length; i++){
        for(int j=0; j<array1[i].length; j++){
          System.out.println("array1["+i+"]["+j+"]="+array1[i][j]+".");
          System.out.println("array2["+i+"]["+j+"]="+array2[i][j]+".");
        }
      }
*/

      //bi1.getRGB();

    }catch(Exception e){
      e.printStackTrace(System.out);
    }



    return areImagesDifferent;
  }


  public static void main(String[] args) {
    Image img1 = (new ImageIcon("D:/Rui/Private/_Cool/NYC.jpg")).getImage();
    Image img2 = (new ImageIcon("D:/Rui/Private/_Cool/NYC2.jpg")).getImage();
//  Image img1 = (new ImageIcon("D:/Rui/Private/_Cool/NYCSmall.jpg")).getImage();
    //Image img2 = (new ImageIcon("D:/Rui/Private/_Cool/NYCSmall2.jpg")).getImage();
    long now = System.currentTimeMillis();
    DifferencesInImages.areImagesDifferent(img1, img2, 0.3);
    System.out.println("Time Elapsed is "+(System.currentTimeMillis()-now)+".");
    now = System.currentTimeMillis();
    DifferencesInImages.areImagesDifferent(img2, img1, 0.3);
    System.out.println("Time Elapsed is "+(System.currentTimeMillis()-now)+".");
    now = System.currentTimeMillis();
    DifferencesInImages.areImagesDifferent(img1, img1, 0.3);
    System.out.println("Time Elapsed is "+(System.currentTimeMillis()-now)+".");
    now = System.currentTimeMillis();
    DifferencesInImages.areImagesDifferent(img2, img2, 0.3);
    System.out.println("Time Elapsed is "+(System.currentTimeMillis()-now)+".");
  }

}
