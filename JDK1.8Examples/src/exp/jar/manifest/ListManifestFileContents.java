package exp.jar.manifest;


import java.io.*;

import java.text.*;

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
public class ListManifestFileContents {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new ListManifestFileContents object.
   */
  public ListManifestFileContents() {
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    listManifest("D:/JBProjects/GISTV/lib/IgStv.jar");
  }

  /**
   * Insert doc ...
   *
   * @param strJarFileName  Insert doc ...
   */
  private static void listManifest(String strJarFileName) {
    JarFile    jar                   = null;
    Manifest   manif                 = null;
    Date       date                  = null;
    String     strKey                = null;
    Map        map                   = null;
    Attributes attrMain              = null;
    Set        setMapEntries         = null;
    Set        setAttributesEntries  = null;
    Iterator   iterMapEntries        = null;
    Iterator   iterAttributesEntries = null;

    if(strJarFileName!=null) {
      System.out.println("Jar: " + strJarFileName);

      try {
        jar        = new JarFile(strJarFileName);
        manif      = jar.getManifest();
        map        = manif.getEntries();
        attrMain   = manif.getMainAttributes();

        setMapEntries          = map.keySet();
        setAttributesEntries   = attrMain.keySet();

        iterMapEntries          = setMapEntries.iterator();
        iterAttributesEntries   = setAttributesEntries.iterator();

        while(iterMapEntries.hasNext()) {
          strKey = "" + iterMapEntries.next();

          System.out.println("mapEntries key= " + strKey + ", value= " +
            ((java.util.jar.Attributes)map.get(strKey)).getValue("Date") + ".");

          try {
            date =
              new SimpleDateFormat().parse(
                ((java.util.jar.Attributes)map.get(strKey)).getValue("Date"));

            System.out.println("Date is after this one? (2004-06-21 12:34:56) " +
              date.after(new SimpleDateFormat().parse("2004-06-21 12:34:56")) +
              ".");
          } catch(ParseException pe) {
            pe.printStackTrace();
          }

          System.out.println("");
        }

        System.out.println("\nMain attributes ...");

        while(iterAttributesEntries.hasNext()) {
          strKey = "" + iterAttributesEntries.next();
          System.out.println("attrMain key= " + strKey + ", value= " +
            attrMain.getValue(strKey) + ".");
        }

        System.out.println("\n\n###############################\n" +
          attrMain.getValue("Implementation-Version") +
          "\n\n###############################");
      } catch(IOException ioe) {
        ioe.printStackTrace();
      }
    } else {
      System.out.println("Jar file name is null!");
    }
  }
}
