package exp.ilog;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

// ILOG imports
import ilog.views.*;
import ilog.views.event.*;
import ilog.views.sdm.*;
import ilog.views.swing.*;
import ilog.views.interactor.*;
import ilog.views.sdm.event.*;

// JAVA IMPORTS
import javax.swing.*;
import java.awt.*;
import java.io.*;



/**
 *
 *
 */
public class TestCSSReading extends JPanel {
  IlvSDMView ilvSDMView = new IlvSDMView();
  IlvSDMEngine ilvSDMEngine = ilvSDMView.getSDMEngine();
  IlvJScrollManagerView ilvjScrollManagerView = new IlvJScrollManagerView(ilvSDMView);

  /**
   *
   */
  public TestCSSReading() {

    this.ilvSDMView = new IlvSDMView();
    this.ilvSDMEngine = this.ilvSDMView.getSDMEngine();
    this.ilvjScrollManagerView = new IlvJScrollManagerView(this.ilvSDMView);


    try {
    File f = new File("/JBProjects/backups/lixo2.xml");
    byte[] b = new byte[new Long(f.length()).intValue()];
    RandomAccessFile raf = new RandomAccessFile(f, "r");
    raf.read(b);
      this.ilvSDMEngine.readXML(new ByteArrayInputStream(b));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void main(String[] args) {
    TestCSSReading testCSSReading1 = new TestCSSReading();

    JFrame jFrame = new JFrame();
    jFrame.setBounds(50,50,400,400);
    jFrame.getContentPane().setLayout(new BorderLayout());
    jFrame.getContentPane().add(testCSSReading1, BorderLayout.CENTER);
    jFrame.setVisible(true);

  }
}









