package exp.nio;


import java.io.*;

import java.nio.channels.*;


/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
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
public class CopyFiles {
  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param sourceFile  Insert doc ...
   * @param destFile  Insert doc ...
   *
   * @throws IOException  Insert doc ...
   */
  public static void copyFile(
    final File sourceFile,
    final File destFile)
      throws IOException {
    if(!destFile.exists()) {
      destFile.createNewFile();
    }

    FileChannel source      = null;
    FileChannel destination = null;

    try {
      source        = new FileInputStream(sourceFile).getChannel();
      destination   = new FileOutputStream(destFile).getChannel();
      destination.transferFrom(
        source,
        0,
        source.size());
    } finally {
      if(source!=null) {
        source.close();
      }

      if(destination!=null) {
        destination.close();
      }
    }
  }
}
