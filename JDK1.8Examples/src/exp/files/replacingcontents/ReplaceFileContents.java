/**
 * <p>
 * Classname: exp.files.replacingcontents.ReplaceFileContents
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package exp.files.replacingcontents;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Feb 15, 2016, 6:36:22 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ReplaceFileContents {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ReplaceFileContents.class.getName());


 /**
  *
  * @param strToSearch
  * @param strSourceFolder
  * @param strClass
  * @param strBuildInfo
  */
  public void replace(
      final String strToSearch,
      final String strSourceFolder,
      final String strClass,
      final String strBuildInfo){

    FileReader fileReader = null;
    BufferedReader bufReader = null;
    FileWriter fileWriter = null;
    BufferedWriter bufWriter = null;

    try {
      final String strFile =
          new File("").getAbsolutePath() +
              strSourceFolder.replaceAll("/", "\\\\").replaceAll("[.]", "") + "\\" +
                  strClass.replaceAll("[.]", "\\\\") + ".java";
      fileReader = new FileReader(strFile);
      bufReader = new BufferedReader(fileReader);
      fileWriter = new FileWriter(strFile);
      bufWriter = new BufferedWriter(fileWriter);
      String strLine;

      while ((strLine = bufReader.readLine()) != null) {

        if(strLine.contains(strToSearch)){
          try {
            // +/-: public static final int I_BUILD = -1;
            bufWriter.write(strLine.substring(0, strLine.indexOf('=')) + strBuildInfo + ";");

            System.out.println(
                strClass + " updating "+strToSearch + " to " +
                    strBuildInfo);

          } catch (Exception exc) {
            exc.printStackTrace();
          }

        } else{
          bufWriter.write(strLine);
        }

        bufWriter.flush();
      }

    }catch(Exception e){
      e.printStackTrace();

    }finally{
      // <editor-fold defaultstate="collapsed" desc="Close streams/files">
      if (bufWriter != null) {
        try {
          bufWriter.close();
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }
      if (fileWriter != null) {
        try {
          fileWriter.close();
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }
      if(bufReader!=null){
        try {
          bufReader.close();
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }
      if(fileReader!=null){
        try {
          fileReader.close();
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }
      // </editor-fold>
    }
  }


  private void repl8(
      final String strToSearch,
      final String strSourceFolder,
      final String strClass,
      final String strBuildInfo){

    try {
      final String strFile =
          new File("").getAbsolutePath() +
              strSourceFolder.replaceAll("/", "\\\\").replaceAll("[.]", "") + "\\" +
                  strClass.replaceAll("[.]", "\\\\") + ".java";

      final Path path = Paths.get(strFile);
      final List<String> listLines = Files.readAllLines(path);
      final int nLines = listLines.size();

      for (int i = 0; i < nLines; i++) {
        final String strLine = listLines.get(i);

        if(strLine.contains(strToSearch)){
          listLines.set(i, strLine.substring(0, strLine.indexOf('=') + 1) + " " + strBuildInfo + ";");
          break;
        }
      }

      final StringBuilder sb = new StringBuilder(16384);

      for(String strLine: listLines){
        sb.append(strLine).append("\n");
      }

      Files.write(path, sb.toString().getBytes(StandardCharsets.ISO_8859_1));

    } catch (Exception exc) {
      exc.printStackTrace();
    }
  }



  public static void main(final String[] args){
    final ReplaceFileContents clazz = new ReplaceFileContents();
    clazz.repl8(
    //clazz.replace(
        "I_BUILD",//strToSearch,
        "./src",//strSourceFolder,
        "exp.files.replacingcontents.Avermedia",//strClass,
        "96669"//strBuildInfo
    );
  }
}


/******************************************************************************************************/
///**
// * <p>
// * Classname: pt.efacec.es.inoss.stv.avermedia.util.Avermedia
// * </p>
// *
// * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A.
// * <br>
// * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
// * You shall not disclose such Confidential Information and shall use it only in
// * accordance with the terms of the license agreement you entered into EFACEC SE.
// * </p>
// * <p>Company: EFACEC Eng. Sistemas
// * <br>
// * Rua Eng.º Frederico Ulrich ? Ap. 3078
// * <br>
// * 4471-907 Moreira da Maia
// * <br>
// * PORTUGAL
// * <br>
// * Tel: +351 22 940 2000
// * <br>
// * Fax: +351 22 948 5428
// * <br>
// * Web: www.efacec.pt
// * <br>
// * Email: te@efacec.pt
// * </p>
// */
//package exp.files.replacingcontents;
//
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.util.logging.Logger;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JSeparator;
//
//
///**
// * <p>
// * Description:
// * </p>
// *
// * Created on 23/Set/2011, 12:18:30
// *
// * @author rUI vALE - {rui dot vale at efacec dot pt}
// * @version $Revision: 1.13 $
// */
//public class Avermedia{
//
//  /** This class build number */
//  public static final int I_BUILD = -1;
//  /** .. */
//  public static final String STR_AVERMEDIA_SDK_VERSION = "7.5.0.13";
//  /** .. */
//  public static final String S_VERSION = STR_AVERMEDIA_SDK_VERSION + "-build_" + I_BUILD;
//
//  private static final Logger LOGGER = Logger.getLogger(Avermedia.class.getName());
//  private static final String S_THIS_CLASS_VERSION = "1.13";
//  private static final String S_PRJ_BUILD_SEPARATOR = "0";
//  private static final String STR_MODULE_NAME = "Avermedia";
//  private static final String STR_MODULE_DESC = "Avermedia Video implementation";
//
//
//
//  /**
//   *
//   * @return
//   */
//  public String getVersion() {
//    return S_VERSION;
//  }
//
//
//  /**
//   *
//   * @return
//   */
//  public String getName() {
//    return STR_MODULE_NAME;
//  }
//
//  /**
//   *
//   * @return
//   */
//  public String getDescription() {
//    return STR_MODULE_DESC;
//  }
//
//  /**
//   *
//   */
//  public static void showVersions() {
//    final JFrame frame = new JFrame("Avermedia GI STV Module");
//    final Container contentPane = frame.getContentPane();
//    contentPane.setLayout(new GridLayout(7, 1));
//    contentPane.add(new JLabel("  Avermedia GI STV Module version (official): " + S_VERSION + "  "));
//    contentPane.add(new JLabel("  Internal module version: " + S_THIS_CLASS_VERSION + "  "));
//
//    contentPane.add(new JSeparator());
//
//    frame.pack();
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    final Dimension paneSize   = frame.getSize();
//    final Dimension scrSize = frame.getToolkit().getScreenSize();
//
//    frame.setLocation((scrSize.width - paneSize.width) / 2, (scrSize.height - paneSize.height) / 2);
//    frame.setVisible(true);
//  }
//
//
//
//
//  public static void main(final String[] args) {
//    showVersions();
//  }
//
//}
//

/********************************************************************************************************/