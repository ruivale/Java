/**
 * <p>
 * Classname: package jdk1_6examples.javax.swing.jlabel.SavingLabel2ImgFile
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
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

package jdk1_6examples.javax.swing.jlabel;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - rui dot vale at efacec dot pt
 * @version $Revision: 1.1 $
 */
public class SavingLabel2ImgFile extends JPanel{
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(SavingLabel2ImgFile.class.getName());


  private final String strFileDir = "c:/temp";
  private final String strFileName = "LabelTextImg.jpg";
  private final String strFileType = "jpg";

  private final JLabel jLabel = new JLabel("Mon1, Alarm1, Camera1");



 /**
  * The SavingLabel2ImgFile default constuctor.
  */
  public SavingLabel2ImgFile(){
    setLayout(new BorderLayout());
    add(jLabel, BorderLayout.CENTER);
  }

  public boolean saveImages(){

    boolean saved = true;

    String strFileAbsolutPath;
    Image img;
    Graphics g;
    BufferedImage buffImage;
    Graphics gBI;

      try {
        strFileAbsolutPath = new StringBuffer(this.strFileDir).append(
            "/").append(this.strFileName).toString();

        // create an image for the capture
        img = this.jLabel.createImage(350,20);

        // grab a graphics context for that image
        g = img.getGraphics();

        // print the GUI into that image
        this.jLabel.printAll(g);

        // Create empty BufferedImage, sized to img
        buffImage =
            new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_RGB);

        // Draw Image into BufferedImage
        gBI = buffImage.getGraphics();
        gBI.clearRect(0,0,350,20);
        gBI.drawString(jLabel.getText(), 2, 15);

        jLabel.setIcon(new ImageIcon(buffImage));

        final File file = this.buildImageFile(strFileAbsolutPath);

        if(file != null){
          ImageIO.write(buffImage,
                        this.strFileType,
                        file);

          if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(
                Level.FINEST,
                new StringBuffer("Saved ").append(
                strFileAbsolutPath).append(" image.").toString());
          }

        }else{

          saved = false;

          if (LOGGER.isLoggable(Level.WARNING)) {
            LOGGER.log(
                Level.WARNING,
                new StringBuffer("Cannot save ").append(
                    strFileAbsolutPath).append(" image.").toString());
          }

        }

      } catch (Exception ex) {

        saved = false;

        if (LOGGER.isLoggable(Level.SEVERE)) {
          LOGGER.log(
              Level.SEVERE,
              "Cannot save image.",
              ex);
        }

      }


    return saved;

  }

  /**
   * Checks for the availability of the desired file. If not file is found,
   * the search goes up, searching for the parent dir. If not parent dir is
   * found either, the dirs (dirs/dirs/dirs) are built.
   *
   * @param strAbsFilePath String
   * @return File
   */
  private File buildImageFile(final String strAbsFilePath){
    File file = null;

    if(strAbsFilePath != null && !strAbsFilePath.equals("")){
      file = new File(strAbsFilePath);

      if(!file.exists()){
        final String strParent = file.getParent();

        if (strParent != null && !strParent.equals("")) {
          final File fileDirs = new File(strParent);

          if(!fileDirs.exists()){
            fileDirs.mkdirs();
          }
        }
      }
    }

    return file;
  }



 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("SavingLabel2ImgFile").append("").toString();
  }
  
  public static void main(final String[] args) throws Exception{
    final SavingLabel2ImgFile clazz = new SavingLabel2ImgFile();

    final JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new java.awt.BorderLayout());
    f.getContentPane().add(clazz, java.awt.BorderLayout.CENTER);
    f.setBounds(800,800,225,100);
    f.setVisible(true);
    Thread.sleep(500);
    System.out.println("saving ...");
    clazz.saveImages();
    System.out.println("... saved.");
    //System.exit(0);

    clazz.jLabel.repaint();
    f.validate();
    f.repaint();
  }
}
