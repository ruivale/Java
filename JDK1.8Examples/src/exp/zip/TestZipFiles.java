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


class TestZipFiles {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java testFiles [zipfile path] ");
      return;
    }
    try {
      String filename = args[0];
      TestZipFiles list = new TestZipFiles();
      list.getZipFiles(filename);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getZipFiles(String filename) {
    try {
      String destinationname = "d:\\servlet\\testZip\\";
      byte[] buf = new byte[1024];
      ZipInputStream zipinputstream = null;
      ZipEntry zipentry;
      zipinputstream = new ZipInputStream(
          new FileInputStream(filename));

      zipentry = zipinputstream.getNextEntry();
      while (zipentry != null) {
        //for each entry to be extracted
        String entryName = zipentry.getName();
        System.out.println("entryname " + entryName);
        int n;
        FileOutputStream fileoutputstream;
        File newFile = new File(entryName);
        String directory = newFile.getParent();

        if (directory == null) {
          if (newFile.isDirectory()) {
            break;
          }
        }

        fileoutputstream = new FileOutputStream(
            destinationname + entryName);

        while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
          fileoutputstream.write(buf, 0, n);
        }

        fileoutputstream.close();
        zipinputstream.closeEntry();
        zipentry = zipinputstream.getNextEntry();

      } //while

      zipinputstream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
