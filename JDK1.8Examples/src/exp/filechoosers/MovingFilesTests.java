package exp.filechoosers;

import java.util.logging.Level;
import java.io.File;
import javax.swing.SwingUtilities;
//import com.ent.stv.config.options.GeneralOptions;
import javax.swing.JFileChooser;


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
public class MovingFilesTests {
//  static final String STR_IMG_TYPE_BMP = "bmp";
//  static String strTempFileName = "beans";
//
//
//  public static void main(final String[] args) {
//    try {
//       final String strFileName = new StringBuffer(
//                   strTempFileName).append(
//                       ".").append(
//                           STR_IMG_TYPE_BMP).toString();
//       final File fileSaved = new File(strFileName);
//
//       final JFileChooser jFileChooser = new JFileChooser();
//       final String lastSelectedDir = ".";
//
//       jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//       jFileChooser.setCurrentDirectory(new File(lastSelectedDir));
//       jFileChooser.setSelectedFile(fileSaved);
//
//       SwingUtilities.invokeLater(new Runnable() {
//         public void run() {
//           try {
//             final int intFCSelectionMade = jFileChooser.showSaveDialog(null);
//
//             if (intFCSelectionMade == JFileChooser.APPROVE_OPTION) {
//               final File fileSelected = jFileChooser.getSelectedFile();
//
//               if(fileSelected != null){
//                 if (!fileSelected.getName().equals(strFileName)) {
//
//                   if(!fileSelected.exists()){
//                     fileSelected.createNewFile();
//                   }
//
//                   com.ent.stv.util.Utils.copyFiles(fileSaved, fileSelected);
//
//                   fileSaved.delete();
//
//                   /***
//                   if(!fileSaved.renameTo(fileSelected)){
//
//                     if (LOGGER.isLoggable(Level.WARNING)) {
//                       LOGGER.log(
//                           Level.WARNING,
//                           new StringBuffer(
//                               "Cannot rename the saved snapshot file name. ").append(
//                               "Will remain ").append(strFileName).append(".").toString());
//                     }
//                   }
//                   /**/
//                 }
//               }
//             }
//           } catch (Exception ex) {
//             ex.printStackTrace();
//           }
//
//           System.exit(0);
//         }
//       });
//
//     } catch (Exception ex) {
//       ex.printStackTrace();
//     }
//
//  }
//
}
