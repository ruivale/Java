package exp.print;

import java.io.*;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;


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
 * Copyright: Copyright (c) 2003
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class MyPrintingTests {
  /**
   * Creates a new MyPrintingTests object.
   */
  public MyPrintingTests() {
  }

  /**
   * DOCUMENT ME!
   */
  public void printHtml() {
    String textStream =
      "<html><body><p>Hoje há frango!</p>" +
      "<p><b>AH!AH!AH!</b></p><p><h1>Achas piada?</h1></p></body></html>";

    // Set the document type
    DocFlavor myFormat = DocFlavor.STRING.TEXT_HTML;

    // Create a Doc
    Doc myDoc = new SimpleDoc(textStream, myFormat, null);

    // Build a set of attributes
    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

    PrintService             pservice =
      PrintServiceLookup.lookupDefaultPrintService();
    DocPrintJob              job = pservice.createPrintJob();

    try {
      job.print(myDoc, aset);
    } catch (PrintException pe) {
      pe.printStackTrace();
    }
  }

  /**
   * DOCUMENT ME!
   */
  public void printAsciiFromFile() {
    // Input the file
    FileInputStream textStream;

    try {
      textStream = new FileInputStream("file.TXT");

      if (textStream == null) {
        return;
      }
    } catch (FileNotFoundException ffne) {
      ffne.printStackTrace();

      return;
    }

    // Set the document type
    DocFlavor myFormat = DocFlavor.INPUT_STREAM.TEXT_PLAIN_US_ASCII;

    // Create a Doc
    Doc myDoc = new SimpleDoc(textStream, myFormat, null);

    // Build a set of attributes
    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

    //aset.add(new Copies(2));
    //aset.add(MediaSize.ISO.A4);
    //aset.add(Sides.DUPLEX);
    // discover the printers that can print the format according to the
    // instructions in the attribute set
    PrintService[] services =
      PrintServiceLookup.lookupPrintServices(myFormat, aset);

    // Create a print job from one of the print services
    if (services.length > 0) {
      DocPrintJob job = services[0].createPrintJob();

      try {
        job.print(myDoc, aset);
      } catch (PrintException pe) {
        pe.printStackTrace();
      }
    } else {
      System.out.println(
        "There are no services for this kind of printing job! Trying the default!");

      PrintService dservice = PrintServiceLookup.lookupDefaultPrintService();
      DocPrintJob  job = dservice.createPrintJob();

      try {
        job.print(myDoc, aset);
      } catch (PrintException pe) {
        pe.printStackTrace();
      }
    }
  }

  /**
   * DOCUMENT ME!
   */
  public void printGIF() {
    try {
      /*
            DocFlavor      flavor   = DocFlavor.INPUT_STREAM.GIF;
            PrintService[] services =
              PrintServiceLookup.lookupPrintServices(flavor, aset);
            DocPrintJob    printJob = services[0].createPrintJob();
            Doc            doc      = new InputStreamDoc("duke.gif", flavor);
            printJob.print(doc, aset);
      */
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * DOCUMENT ME!
   */
  public void findPrintServices() {
    DocFlavor                flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT;
    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
    aset.add(MediaSizeName.ISO_A4);
    aset.add(new Copies(2));

    PrintService[] service =
      PrintServiceLookup.lookupPrintServices(flavor, aset);

    int            intNbrOf = service.length;

    for (int i = 0; i < intNbrOf; i++) {
      System.out.println("Service: " + service[i].getName() + ".");
    }
  }

  /**
   * DOCUMENT ME!
   */
  public void printPS() {
    /* Construct the print request specification.
    * The print data is Postscript which will be
    * supplied as a stream. The media size
    * required is A4, and 2 copies are to be printed
    */
    DocFlavor                flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT;
    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
    aset.add(MediaSizeName.ISO_A4);
    aset.add(new Copies(2));
    aset.add(Sides.TWO_SIDED_LONG_EDGE);
    aset.add(Finishings.STAPLE);

    /* locate a print service that can handle it
    */
    PrintService[] pservices =
      PrintServiceLookup.lookupPrintServices(flavor, aset);

    if (pservices.length > 0) {
      System.out.println("selected printer " + pservices[0].getName());

      /* create a print job for the chosen service
      */
      DocPrintJob pj = pservices[0].createPrintJob();

      try {
        /* * Create a Doc object to hold the print data.
        * Since the data is postscript located in a disk file,
        * an input stream needs to be obtained
        * BasicDoc is a useful implementation that will if
        * requested close the stream when printing is completed.
        */
        FileInputStream fis = new FileInputStream("example.ps");
        Doc             doc = new SimpleDoc(fis, flavor, null);

        /* print the doc as specified
        */
        pj.print(doc, aset);
      } catch (IOException ie) {
        System.err.println(ie);
      } catch (PrintException e) {
        System.err.println(e);
      }
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   */
  public static void main(String[] args) {
    MyPrintingTests m = new MyPrintingTests();

    //m.printAsciiFromFile();
    //m.printHtml();
    //m.printGIF();
    //m.printPS();

    m.findPrintServices();
  }
}



