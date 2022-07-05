package exp.zip;


import java.io.*;
import java.util.zip.*;

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
public class ReadZipFiles {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java testFiles [zipfile path] ");
      return;
    }
    try {
      String filename = args[0];
      ReadZipFiles list = new ReadZipFiles();
      list.readZipFiles(filename);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void readZipFiles(String filename) {
    try {
      byte[] buf = new byte[1024];
      ZipInputStream zipinputstream = null;
      ZipEntry zipentry;
      zipinputstream = new ZipInputStream(
          new FileInputStream(filename));

      zipentry = zipinputstream.getNextEntry();
      while (zipentry != null) {
        //for each entry to be extracted
        String entryName = zipentry.getName();
        System.out.println("File ::" + entryName);
        RandomAccessFile rf;
        File newFile = new File(entryName);
        String directory = newFile.getParent();

        if (directory == null) {
          if (newFile.isDirectory()) {
            break;
          }
        }

        rf = new RandomAccessFile(entryName, "r");
        String line;

        if ((line = rf.readLine()) != null) {
          System.out.println(line);
        }

        rf.close();
        zipinputstream.closeEntry();
        zipentry = zipinputstream.getNextEntry();

      } //while

      zipinputstream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
