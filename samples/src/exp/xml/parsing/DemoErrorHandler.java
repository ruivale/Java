package exp.xml.parsing;


/**
 * Title: Description: Copyright:    Copyright (c) Company:
 */
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * A demo implementation of the org.xml.sax.ErrorHandler class.
 */
public class DemoErrorHandler
    implements ErrorHandler {
  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param exception  Insert doc ...
   */
  public void error(SAXParseException exception) {
    System.out.println("--- ERROR ---");
    System.out.println("\tLine number:\t" + exception.getLineNumber());
    System.out.println("\tColumn number:\t" + exception.getColumnNumber());
    System.out.println("\tMessage:\t" + exception.getMessage());
    System.out.println("-------------\n");
  }

  /**
   * Insert doc ...
   *
   * @param exception  Insert doc ...
   *
   * @throws SAXException  Insert doc ...
   */
  public void fatalError(SAXParseException exception)
      throws SAXException {
    System.out.println("--- FATAL ERROR ---");
    System.out.println("\tLine number:\t" + exception.getLineNumber());
    System.out.println("\tColumn number:\t" + exception.getColumnNumber());
    System.out.println("\tMessage:\t" + exception.getMessage());
    System.out.println("-------------------\n");
    throw new SAXException("Fatal Error encountered – parsing terminated.");
  }

  /**
   * Insert doc ...
   *
   * @param exception  Insert doc ...
   */
  public void warning(SAXParseException exception) {
    System.out.println("--- WARNING ---");
    System.out.println("\tLine number:\t" + exception.getLineNumber());
    System.out.println("\tColumn number:\t" + exception.getColumnNumber());
    System.out.println("\tMessage:\t" + exception.getMessage());
    System.out.println("---------------\n");
  }
}
