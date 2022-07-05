package exp.xml.parsing;



// import handler interfaces:
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;

// import exception classes:
import org.xml.sax.SAXException;

/**
 * Title: Description: Copyright:    Copyright (c) Company:
 */

// import the SAX reader interface:
import org.xml.sax.XMLReader;

// import the helper class:
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;


/**
 * A simple SAX demo. Takes an XML document, parses it and outputs all
 * callbacks.\u0D0AChapter 12
 *
 * @author Oli Gauti Gudmundsson.
 */
public class SaxDemo2 {
  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args Insert doc ...
   */
  public static void main(String[] args) {
    /*
       if ( args.length != 1 ) {
       System.out.println("Usage: java SAXDemo <path to XML document>");
       System.exit(0);
       }
         System.out.println("Starting to parse " + args[0]);
     */
/*
   try {
      // Instantiate the reader:
   XMLReader reader = XMLReaderFactory.createXMLReader(
                        "org.apache.xerces.parsers.SAXParser");
   // Create an instance of the handlers:
   ContentHandler contentHandler = new DemoContentHandler();
   ErrorHandler errorHandler = new DemoErrorHandler();
   // Register the handlers:
   reader.setContentHandler( contentHandler );
   reader.setErrorHandler( errorHandler );
   // Turn on validation
   reader.setFeature("http://xml.org/sax/features/validation",
                     true);
   // Turn off namespace awareness
   //            reader.setFeature("http://xml.org/sax/features/namespaces",
   //                            false);
   // Parse:
   //reader.parse("src/exp/xml/parsing/example.xml");
   reader.parse("/mnt/win_c/sa/pint/XmlDoUser.xml");
   } catch ( IOException e ) {
     System.out.println("Error reading file: " + e.getMessage());
   }
   catch ( SAXException e ) {
     System.out.println("Error parsing file: " + e.getMessage());
   }
 */
  }
}
