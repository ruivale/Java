/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exp.java.io.filelocking;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Calendar;


/**
 *
 * @author c2334
 */
public class TestFileLocking {

  //private static final String sFile =
    //  "C:/Documents and Settings/c2334/Start Menu/Programs/StartUp/BackInfo.lnk";
  private static final String sFile =
      "C:/Documents and Settings/c2334/BackInfo.exe";
  //private static final String sFile =
    //  "C:/Documents and Settings/c2334/BackInfo.ini";
  private static FileLock fileLock = null;

  public static void main(String[] args) {

    try {
      final Calendar cal = Calendar.getInstance();
      String sDate = null;
      File file = new File(sFile);

      if(file.exists()) {
        //file.delete();
        final FileOutputStream fileOutReader = new FileOutputStream(file);
        final FileChannel fileChannel = fileOutReader.getChannel();
        fileLock = fileChannel.tryLock();

        //fileEXEOutReader = new FileOutputStream(file);
        //fileEXEOutReader.write(-1);


        sDate = cal.get(Calendar.YEAR) +
            "/" +
            cal.get(Calendar.MONTH +
            1) +
            "/" +
            cal.get(Calendar.DAY_OF_MONTH) +
            " " +
            cal.get(Calendar.HOUR_OF_DAY) +
            ":" +
            cal.get(Calendar.MINUTE) +
            ":" +
            cal.get(Calendar.SECOND);

        System.out.println("Found the " +
            file.getAbsolutePath() +
            ". Locked - " +
            sDate);
      }
    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
