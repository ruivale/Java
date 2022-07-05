package exp.jar.extractfiles;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.io.*;

import java.util.jar.*;
import java.util.zip.*;


public class ExtractFromJar {
  String strFileName = "somefile";

  public void extractMyDBFromJAR(String dest) {
    try {
      String home = getClass().getProtectionDomain().getCodeSource()
                      .getLocation().toString().substring(6);
      JarFile jar = new JarFile(home);
      ZipEntry entry = jar.getEntry(strFileName);
      File efile = new File(dest, entry.getName());

      InputStream in = new BufferedInputStream(jar.getInputStream(entry));
      OutputStream out = new BufferedOutputStream(new FileOutputStream(efile));
      byte[] buffer = new byte[2048];

      for (;;) {
        int nBytes = in.read(buffer);

        if (nBytes <= 0) {
          break;
        }

        out.write(buffer, 0, nBytes);
      }

      out.flush();
      out.close();
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new ExtractFromJar().extractMyDBFromJAR(".");
  }
}
