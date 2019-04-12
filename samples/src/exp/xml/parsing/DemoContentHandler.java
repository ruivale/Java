package exp.xml.parsing;


/**
 * Title: Description: Copyright:    Copyright (c) Company:
 */
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;


/**
 * A demo implementation of the org.xml.sax.ContentHandler class.
 */
public class DemoContentHandler
    implements ContentHandler {
  //~ Instance fields //////////////////////////////////////////////////////////

  // The locator provides the location of a callback within a document

  /** .. */
  private Locator locator;

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param locator  Insert doc ...
   */
  public void setDocumentLocator(Locator locator) {
    //System.out.println("setDocumentLocator called");
    // store the locator:
    this.locator = locator;
  }

  /**
   * Insert doc ...
   *
   * @param chars  Insert doc ...
   * @param start  Insert doc ...
   * @param end  Insert doc ...
   *
   * @throws SAXException  Insert doc ...
   */
  public void characters(
    char[] chars,
    int    start,
    int    end)
      throws SAXException {
    String str = new String(chars, start, end);
    System.out.println("\tCharacters: " + str);
  }

  /**
   * Insert doc ...
   *
   * @throws SAXException  Insert doc ...
   */
  public void endDocument()
      throws SAXException {
    System.out.println("*** The end of parsing!");
  }

  /**
   * Insert doc ...
   *
   * @param namespaceURI  Insert doc ...
   * @param localName  Insert doc ...
   * @param fullName  Insert doc ...
   *
   * @throws SAXException  Insert doc ...
   */
  public void endElement(
    String namespaceURI,
    String localName,
    String fullName)
      throws SAXException {
    System.out.println("End element - Name: " + localName);

    if(!namespaceURI.equals("")) {
      System.out.println("\tIs in namespace, full name: " + fullName);
    }
  }

  /**
   * Insert doc ...
   *
   * @param prefix  Insert doc ...
   */
  public void endPrefixMapping(String prefix) {
    //System.out.println("End mapping - Prefix:" + prefix);
  }

  /**
   * Insert doc ...
   *
   * @param chars  Insert doc ...
   * @param start  Insert doc ...
   * @param end  Insert doc ...
   *
   * @throws SAXException  Insert doc ...
   */
  public void ignorableWhitespace(
    char[] chars,
    int    start,
    int    end)
      throws SAXException {
    //String whitespace = new String( chars,start,end );
    //System.out.println("Ignorable whitespace: \"" + whitespace + "\"");
  }

  /**
   * Insert doc ...
   *
   * @param target  Insert doc ...
   * @param instruction  Insert doc ...
   *
   * @throws SAXException  Insert doc ...
   */
  public void processingInstruction(
    String target,
    String instruction)
      throws SAXException {
    //System.out.println("PI - Target:" + target + " Instruction:" + instruction);
  }

  /**
   * Insert doc ...
   *
   * @param name  Insert doc ...
   *
   * @throws SAXException  Insert doc ...
   */
  public void skippedEntity(String name)
      throws SAXException {
    //System.out.println("Skipped entity: " + name);
  }

  /**
   * Insert doc ...
   *
   * @throws SAXException  Insert doc ...
   */
  public void startDocument()
      throws SAXException {
    System.out.println("*** The beginning of parsing!");
  }

  /**
   * Insert doc ...
   *
   * @param namespaceURI  Insert doc ...
   * @param localName  Insert doc ...
   * @param fullName  Insert doc ...
   * @param attributes  Insert doc ...
   *
   * @throws SAXException  Insert doc ...
   */
  public void startElement(
    String     namespaceURI,
    String     localName,
    String     fullName,
    Attributes attributes)
      throws SAXException {
    System.out.println("\nStart element - Name: " + localName);

    if(!namespaceURI.equals("")) {
      System.out.println("\tIs in namespace, full name: " + fullName);
    }

    // Print the attributes:
    for(int i = 0; i<attributes.getLength(); i++) {
      System.out.println("\tAttribute - Name:" + attributes.getLocalName(i) +
        " Value:" + attributes.getValue(i));
    }
  }

  /**
   * Insert doc ...
   *
   * @param prefix  Insert doc ...
   * @param uri  Insert doc ...
   */
  public void startPrefixMapping(
    String prefix,
    String uri) {
    //System.out.println("Start mapping - Prefix:" + prefix + " URI:" + uri);
  }
}
