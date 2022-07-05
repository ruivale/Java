package exp.swing.jcolorchooser;


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class BuildImagesUsingColorChooser extends JPanel{

  private static final String strFileName = "d:/temp/imagetests.png";

  public BuildImagesUsingColorChooser() {
    //saveScreenShotImage();
    imageFromComp();
  }

  private void saveScreenShotImage(){
    try {
      //ImageProducer imageProducer = new MemoryImageSource();
      //Image image = Toolkit.getDefaultToolkit().createImage()

      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Dimension screenSize = toolkit.getScreenSize();
      Rectangle screenRect = new Rectangle(screenSize);
      Robot robot = new Robot();
      BufferedImage image = robot.createScreenCapture(screenRect);
      // save captured image to PNG file
      ImageIO.write(image, "png", new File(strFileName));

      // give feedback
      System.out.println("Saved screen shot (" + image.getWidth() +
              " x " + image.getHeight() + " pixels) to file \"" +
              strFileName + "\".");
      // use System.exit if the program hangs after writing the file;
      // that's an old bug which got fixed only recently
      // System.exit(0);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void imageFromComp(){
    try {
      // the top-level component you want to capture
      final JButton comp = new JButton("OLA");
      comp.setPreferredSize(new Dimension(100,100));
      JFrame f = new JFrame();
      f.getContentPane().add(comp);
      f.setBounds(100,100,200,200);
      f.setVisible(true);

      comp.addActionListener(new ActionListener(){
        public void actionPerformed(final ActionEvent e){

          System.out.println("Creating image ...");

          try {
            // create an image for the capture
            Image img = comp.createImage(comp.getWidth(), comp.getHeight());

            // grab a graphics context for that image
            Graphics g = img.getGraphics();

            // print the GUI into that image
            comp.printAll(g);

            // Create empty BufferedImage, sized to img
            BufferedImage buffImage =
                new BufferedImage(
                    200,//img.getWidth(null),
                    200,//img.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);

            // Draw Image into BufferedImage
            Graphics gBI = buffImage.getGraphics();
            gBI.drawImage(img, 0, 0, null);

            ImageIO.write(buffImage, "png", new File(strFileName));

          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      });


    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }


  public static void main(String[] args) {
    BuildImagesUsingColorChooser b = new
        BuildImagesUsingColorChooser();
  }
}
