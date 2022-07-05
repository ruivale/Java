package exp.print;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.print.*;

import java.io.*;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.event.*;

import javax.swing.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class PrintPrintable {
  /**
  * DOCUMENT ME!
  *
  * @param args DOCUMENT ME!
  *
  * @throws Exception DOCUMENT ME!
  */
  public static void main(String[] args) throws Exception {
    final JFrame frame = new JFrame("Printing Graphics");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container contentPane = frame.getContentPane();
    final Component printIt = new MyEditorPane();

    //    final Component printIt = new MyComponent();
    contentPane.add(printIt, BorderLayout.CENTER);

    JButton button = new JButton("Print");
    contentPane.add(button, BorderLayout.SOUTH);

    ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

          /*
          DocFlavor        flavor       =
            DocFlavor.SERVICE_FORMATTED.PRINTABLE;
          PrintService     printService =
            PrintServiceLookup.lookupDefaultPrintService();
          DocPrintJob      job        = printService.createPrintJob();

          PrintJobListener pjlistener =
            new PrintJobAdapter() {
              public void printDataTransferCompleted(PrintJobEvent e) {
                System.out.println("Good-bye");
                System.exit(0);
              }
            };

          job.addPrintJobListener(pjlistener);

          PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
          DocAttributeSet          das = new HashDocAttributeSet();
          Doc                      doc = new SimpleDoc(printIt, flavor, das);

          try {
            job.print(doc, pras);
          } catch (PrintException pe) {
            pe.printStackTrace();
          }
          */

          PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
          DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
          PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor,
              pras);
          PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

          PrintService service = ServiceUI.printDialog(null, 200, 200,
              printService, defaultService, flavor, pras);

          if (service != null) {
            DocPrintJob job = service.createPrintJob();
            DocAttributeSet das = new HashDocAttributeSet();
            Doc doc = new SimpleDoc(printIt, flavor, das);

            try {
              job.print(doc, pras);
              //Thread.sleep(10000);
            } catch (Exception exc) {
              exc.printStackTrace();
            }
          }
        }
      };

    button.addActionListener(listener);
    frame.setSize(350, 250);
    frame.show();
  }

  static class MyComponent extends JPanel implements Printable {
    Font theFont = new Font("Serif", Font.ITALIC, 48);

    public void paint(Graphics g) {
      super.paint(g);

      String msg = "Hello, Printer";
      g.setFont(theFont);

      FontMetrics fm = g.getFontMetrics();

      // Center line
      int width = getWidth();
      int stringWidth = fm.stringWidth(msg);
      int x = (width - stringWidth) / 2;
      int height = getHeight();
      int stringHeight = fm.getHeight();
      int ascent = fm.getAscent();
      int y = ((height - stringHeight) / 2) + ascent;
      g.drawString(msg, x, y);
      g.drawRect(x, y - ascent, stringWidth, stringHeight);
    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
      int x = (int) pageFormat.getImageableX();
      int y = (int) pageFormat.getImageableY();
      g.translate(x, y);

      if (pageIndex == 0) {
        paint(g);

        return Printable.PAGE_EXISTS;
      } else {
        return Printable.NO_SUCH_PAGE;
      }
    }
  }

  static class MyEditorPane extends JEditorPane implements Printable {
    Font theFont = new Font("Serif", Font.ITALIC, 48);

    /**
    *
    */
    public MyEditorPane() {
      String msg = "<html><body><center><a href=\"http://www.sapo.pt\">sapo</a><h2>Titlet</h2></center><b><p>Text to</p><p>display</p></b></body></html>";
      String strMimeType = "text/html";
      this.setContentType(strMimeType);
      this.setText(msg);
      this.setOpaque(false);

    }

    public void paint(Graphics g) {
      super.paint(g);

      //ImageIcon image = new ImageIcon("d:\\jbprojects\\exp\\beans.jpg" );
      //g.drawImage( image.getImage(), 0, 0, null, null );


      /*
      g.setFont(theFont);
      FontMetrics fm = g.getFontMetrics();

      // Center line
      int width        = getWidth();
      int stringWidth  = fm.stringWidth(this.getText());
      int x            = (width - stringWidth) / 2;
      int height       = getHeight()+image.getIconHeight();
      int stringHeight = fm.getHeight();
      int ascent       = fm.getAscent();
      int y            = ((height - stringHeight) / 2) + ascent;

      g.drawString(this.getText(), x, y);
      g.drawRect(x, y - ascent, stringWidth, stringHeight);
      */

    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
      int x = (int) pageFormat.getImageableX();
      int y = (int) pageFormat.getImageableY();
      g.translate(x, y);

      if (pageIndex == 0) {
        paint(g);

        return Printable.PAGE_EXISTS;
      } else {
        return Printable.NO_SUCH_PAGE;
      }
    }
  }
}
