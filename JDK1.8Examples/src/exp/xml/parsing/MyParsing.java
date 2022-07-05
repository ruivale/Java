package exp.xml.parsing;


import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * Title: Description: Copyright:    Copyright (c) Company:
 */
import java.io.*;
import javax.xml.parsers.SAXParser;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class MyParsing {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new MyParsing object.
   */
  public MyParsing() {
    try {
      XMLReader parser = null;// = new SAXParser();

      boolean   isUnix    =
        (File.separatorChar=='/') && (File.pathSeparatorChar==':');
      boolean   isWindows =
        (File.separatorChar=='\\') && (File.pathSeparatorChar==';');

      if(isUnix) {
        parser.parse("/mnt/win_c/sa/pint/XmlDoUser.xml");
      } else if(isWindows) {
        parser.parse("src/exp/xml/parsing/XmlDoUser.xml");
      }
    } catch(IOException ioe) {
      System.out.println("ERROR: " + ioe.getMessage());
    } catch(SAXException saxe) {
      System.out.println("ERROR: " + saxe.getMessage());
    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    MyParsing myParsing1 = new MyParsing();
  }
}
