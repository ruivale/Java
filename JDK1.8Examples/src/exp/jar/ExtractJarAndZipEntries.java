// %1131545903890:exp.jar%
package exp.jar;


import java.awt.*;

import java.io.*;

import java.net.*;

import java.util.*;
import java.util.jar.*;


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
class ExtractJarAndZipEntries
    extends Frame {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  JarFile          jar    = null;

  /** .. */
  JarURLConnection URLcon = null;

  /** .. */
  TextArea TA  = new TextArea(15, 35);

  /** .. */
  URL url = null;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new ExtractJarAndZipEntries object.
   *
   * @param titlu  Insert doc ...
   */
  public ExtractJarAndZipEntries(String titlu) {
    super(titlu);
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   */
  public void getURLContent() {
    try {
      //local file
      url=new URL("jar:file:/C:/JDKs/jdk1.5.0_14/jre/lib/jsse.jar!/");
      //remote file
      //url   = new URL("jar:http://sosnoski.com/opensrc/xmlbench/code/xmlbench.jar!/");

      URLcon   = (JarURLConnection)(url.openConnection());
      jar      = URLcon.getJarFile();

    } catch(MalformedURLException e) {
      System.out.println("Eroare1:" + e.getMessage());
    } catch(IOException e) {
      System.out.println("Eroare2:" + e.getMessage());
    }
  }

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    ExtractJarAndZipEntries t = new ExtractJarAndZipEntries("URL");
    t.getURLContent();
    t.init();
    t.entry();
  }

  /**
   * Insert doc ...
   */
  void entry() {
    Enumeration entries = jar.entries();

    while(entries.hasMoreElements()) {
      String entry = ((JarEntry)entries.nextElement()).getName();
      TA.append(entry + "\n");
    }
  }

  /**
   * Insert doc ...
   */
  void init() {
    setLayout(new FlowLayout());
    setSize(
      300,
      300);
    add(TA);
    setVisible(true);
  }
}
