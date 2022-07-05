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
 * Description: How do you zip? Well, zipping files up is a similar process,
 * and rather than provide as comprehensive of an example, I will show you
 * given a short and sweet counterpart that focuses on the important details.
 * Just keep in mind that the names of the files in the ZIP file should
 * contain any folder paths in the prefix - for example:
 * '/root/sub/sub2/myFile.txt' would be 3 directories deep.
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
public class Zip {
  //~ Methods //////////////////////////////////////////////////////////////////

// import java.util.*, java.util.zip.* and java.io.*
  public static void main(final String[] args) {
    ZipOutputStream out = null;
    InputStream     in = null;

    try {
      File         inputFile1 = new File("myFile.txt");
      File         outputFile = new File("myZipFile.zip");

      OutputStream rawOut =
        new BufferedOutputStream(new FileOutputStream(outputFile));
      out = new ZipOutputStream(rawOut);

      // optional - manages amount of compression
      // out.setLevel(java.util.zip.Deflator.BEST_COMPRESSION);
      InputStream rawIn = new FileInputStream(inputFile1);
      in = new BufferedInputStream(rawIn);

      // entry for our file
      // should be /root/sub/sub2/myFile.txt if we want it
      // 3 folders deep
      ZipEntry entry = new ZipEntry("myFile.txt");

      // notify output stream of entry.
      out.putNextEntry(entry);

      // pump data from file into zip file
      byte[] buf = new byte[1024];
      int    len;

      while((len = in.read(buf))>0) {
        out.write(buf, 0, len);
      }
    } catch(IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if(in!=null) {
          in.close();
        }

        if(out!=null) {
          out.close();
        }
      } catch(IOException ignored) {
        ;
      }
    }
  }
}
