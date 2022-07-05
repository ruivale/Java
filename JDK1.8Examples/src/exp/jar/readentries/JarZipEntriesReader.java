package exp.jar.readentries;


import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.util.jar.*;


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
public class JarZipEntriesReader extends Frame {
  URL url = null;
  JarURLConnection URLcon = null;
  JarFile jar = null;
  TextArea TA = new TextArea(15, 35);

  public JarZipEntriesReader(String titlu) {
    super(titlu);
  }

  void init() {
    setLayout(new FlowLayout());
    setSize(300, 300);
    add(TA);
    setVisible(true);
  }

  public void getURLContent() {
    try {
      //local archive
      url = new URL("jar:file:/C:/JDKs/jdk1.5.0_11/jre/lib/jsse.jar!/");
      //remote archive
      //url=new URL("jar:http://.../archive.jar!/");

      URLcon = (JarURLConnection) (url.openConnection());
      jar = URLcon.getJarFile();
    } catch (MalformedURLException e) {
      System.out.println("Eroare1:" + e.getMessage());
    } catch (IOException e) {
      System.out.println("Eroare2:" + e.getMessage());
    }
  }

  void entry() {
    Enumeration entries = jar.entries();
    while (entries.hasMoreElements()) {
      String entry = ((JarEntry)entries.nextElement()).getName();
      TA.append(entry + "\n");
    }
  }

  public static void main(String[] args) {
    JarZipEntriesReader t = new JarZipEntriesReader("URL");
    t.getURLContent();
    t.init();
    t.entry();
  }
}
