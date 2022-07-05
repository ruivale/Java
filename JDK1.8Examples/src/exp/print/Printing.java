package exp.print;

import java.io.*;

import javax.print.*;
import javax.print.attribute.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Printing {
  /**
   * DOCUMENT ME!
   */
  public static void showPrintDialog()
      throws Exception {
    String                   filename     = "a.png";
    PrintRequestAttributeSet pras         = new HashPrintRequestAttributeSet();
    DocFlavor                flavor       = DocFlavor.INPUT_STREAM.PNG;
    PrintService[]           printService =
      PrintServiceLookup.lookupPrintServices(flavor, pras);
    PrintService             defaultService =
      PrintServiceLookup.lookupDefaultPrintService();
    PrintService             service =
      ServiceUI.printDialog(
        null,
        200,
        200,
        printService,
        defaultService,
        flavor,
        pras);

System.out.println("printDialog");

    if (service != null) {
      DocPrintJob     job = service.createPrintJob();
      FileInputStream fis = new FileInputStream(filename);
      DocAttributeSet das = new HashDocAttributeSet();
      Doc             doc = new SimpleDoc(fis, flavor, das);
      job.print(doc, pras);
      Thread.sleep(10000);
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @throws Exception DOCUMENT ME!
   */
  public static void doNotShowPrintDialog()
      throws Exception {
    String                   filename     = "a.png";
    PrintRequestAttributeSet pras         = new HashPrintRequestAttributeSet();
    DocFlavor                flavor       = DocFlavor.INPUT_STREAM.PNG;
    PrintService[]           printService =
      PrintServiceLookup.lookupPrintServices(flavor, pras);
    PrintService             defaultService =
      PrintServiceLookup.lookupDefaultPrintService();

    if (defaultService != null) {
      DocPrintJob     job = defaultService.createPrintJob();
      FileInputStream fis = new FileInputStream(filename);
      DocAttributeSet das = new HashDocAttributeSet();
      Doc             doc = new SimpleDoc(fis, flavor, das);
      job.print(doc, pras);
      //Thread.sleep(10000);
    }else{
      System.out.println("PrintServiceLookup.lookupDefaultPrintService() == null");
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   *
   * @throws Exception DOCUMENT ME!
   */
  public static void main(String[] args) {
    try {
      Printing.doNotShowPrintDialog();
      //Printing.showPrintDialog();
    } catch (Exception e) {
      ;
    }

    System.exit(0);
  }
}
