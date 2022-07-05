package exp.zip;


import java.io.*;

import java.util.*;
import java.util.zip.*;


/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description: Java has plenty of support for handling ZIP files. Although not
 * being entirely transparent to traditional file work, the API is clear and
 * concise. The starting point in most cases is the java.util.zip.ZipFile
 * class. Yes, that's right, it's in a 'util' package, and not an 'io'
 * package. Makes a whole lot of sense, doesn't it? Anyway, from a
 * java.util.zip.ZipFile  object, you can get access to the name and data of
 * each entry in the zip bundle. Here is a code snippet that converts a zip
 * file into a folder with the same name, and contains all of the files that
 * were in the zip file in extracted format:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c)
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class UnZip {
  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args Insert doc ...
   */
  public static void main(final String[] args) {
    try {
      File    file    = new File("myZipFile.zip");
      ZipFile zipFile = new ZipFile(file);

      // create a directory named the same as the zip file in the
      // same directory as the zip file.
      File zipDir = new File(
          file.getParentFile(),
          "myZipFile");
      zipDir.mkdir();

      // JDK1.5
      //Enumeration<ZipEntry> entries = zipFile.entries();
      Enumeration entries = zipFile.entries();

      while(entries.hasMoreElements()) {
        ZipEntry entry = (ZipEntry)entries.nextElement();

        String   nme = entry.getName();

        // File for current file or directory
        File entryDestination = new File(zipDir, nme);

        // This file may be in a subfolder in the Zip bundle
        // This line ensures the parent folders are all
        // created.
        entryDestination.getParentFile()
                        .mkdirs();

        // Directories are included as seperate entries
        // in the zip file.
        if(!entry.isDirectory()) {
          generateFile(entryDestination, entry, zipFile);
        }
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Insert doc ...
   *
   * @param destination Insert doc ...
   * @param entry Insert doc ...
   * @param owner Insert doc ...
   *
   * @throws IOException Insert doc ...
   */
  private static void generateFile(
    final File     destination,
    final ZipEntry entry,
    final ZipFile  owner)
      throws IOException {
    InputStream  in  = null;
    OutputStream out = null;

    try {
      InputStream rawIn = owner.getInputStream(entry);
      in = new BufferedInputStream(rawIn);

      FileOutputStream rawOut = new FileOutputStream(destination);
      out = new BufferedOutputStream(rawOut);

      // pump data from zip file into new files
      byte[] buf = new byte[1024];
      int    len;

      while((len = in.read(buf))>0) {
        out.write(buf, 0, len);
      }
    } finally {
      if(in!=null) {
        in.close();
      }

      if(out!=null) {
        out.close();
      }
    }
  }
}
