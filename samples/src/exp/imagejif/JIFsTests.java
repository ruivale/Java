package exp.imagejif;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.swing.*;


/**
 * An example of drawing a PDF to an image.
 *
 * @author joshua.marinacci@sun.com
 */
public class JIFsTests {

  public static int numeroPagine;
  public static int i;

  private static final JLabel jLabel = new JLabel();

  public static void setup() throws IOException {
    i = 1;
    final JFrame frame2 = new JFrame("PDF Test");
    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //load a pdf from a byte buffer

    final JInternalFrame frame = new JInternalFrame("PDF Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final File file = new File("c:/m.pdf");
    final RandomAccessFile raf = new RandomAccessFile(file, "r");
    final FileChannel channel = raf.getChannel();
    final ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

    final PDFFile pdffile = new PDFFile(buf);

    numeroPagine = pdffile.getNumPages();

    // draw the first page to an image
    PDFPage page = pdffile.getPage(0);

    final BufferedImage img = new BufferedImage(
        (int) page.getBBox().getWidth(),
        (int) page.getBBox().getHeight(), 
        BufferedImage.TYPE_INT_ARGB);
    
    Graphics2D g2 = img.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //get the width and height for the doc at the default zoom
    Rectangle rect = new Rectangle(0, 0,(int) page.getBBox().getWidth(),(int) page.getBBox().getHeight());

    try {
      //generate the image
      PDFRenderer renderer = new PDFRenderer(
          page,
          g2,
          new Rectangle(0, 0, (int) page.getBBox().getWidth(),(int) page.getBBox().getHeight()), 
          null,
          Color.white);
      page.waitForFinish();
      renderer.run();

    } catch (Exception e) {
    }

    final JButton Botton = new JButton("Avanti");
    Botton.setSize(Botton.getPreferredSize());
    frame2.add(Botton);
    Botton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        if (i > numeroPagine) {
          Botton.setEnabled(false);

        } else {
          try {
            i++;
            // File file = new File("c:/m.pdf");
            //  RandomAccessFile raf = new RandomAccessFile(file, "r");
            //  FileChannel channel = raf.getChannel();
            // ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            //PDFFile pdffile = new PDFFile(buf);
            PDFPage page2 = pdffile.getPage(i);

            BufferedImage img = new BufferedImage(
                (int) page2.getBBox().getWidth(),
                (int) page2.getBBox().getHeight(), 
                BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //get the width and height for the doc at the default zoom
            Rectangle rect = new Rectangle(
                0,
                0,
                (int) page2.getBBox().getWidth(),
                (int) page2.getBBox().getHeight());

            //generate the image
            PDFRenderer renderer = new PDFRenderer(
                page2,
                g2,
                new Rectangle(0, 0, (int) page2.getBBox().getWidth(),(int) page2.getBBox().getHeight()), 
                null,
                Color.white);
            page2.waitForFinish();
            renderer.run();


//            frame.add(new JLabel(new ImageIcon(img)));
//            frame.validate();
            jLabel.setIcon(new ImageIcon(img));

          } catch (Exception r) {
          }
          System.out.println("sono arrivato");
          //frame2.removeAll();
          // frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          // frame.removeAll();
          //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
      }
    });
    //show the image in a frame

    jLabel.setIcon(new ImageIcon(img));
    frame.add(jLabel);
    frame.pack();
    frame.setVisible(true);


    frame2.add(frame);
    frame2.pack();
    frame2.setVisible(true);
  }

  public static void main(final String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        try {
          JIFsTests.setup();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });
  }
}

