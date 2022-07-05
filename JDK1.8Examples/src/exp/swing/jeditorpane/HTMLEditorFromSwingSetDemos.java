package exp.swing.jeditorpane;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import javax.swing.filechooser.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;


/**
 * exp.swing.jeditorpane.HTMLEditorFromSwingSetDemos
 *
 * Html Demo
 *
 * @version 1.8 03/01/23
 * @author Jeff Dinkins
 */
public class HTMLEditorFromSwingSetDemos
    extends /*DemoModule*/ JPanel{

  JEditorPane html;

  /**
   * main method allows us to run as a standalone demo.
   */
  public static void main(String[] args) {
    HTMLEditorFromSwingSetDemos d = new HTMLEditorFromSwingSetDemos();
    //demo.mainImpl();
    JFrame f = new JFrame("Worker Thread Examples");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(d, BorderLayout.CENTER);
    f.pack();
    f.setSize(600,350);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * HtmlDemo Constructor
   */
  public HTMLEditorFromSwingSetDemos() {

    try {
      URL url = null;
      // System.getProperty("user.dir") +
      // System.getProperty("file.separator");
      String path = null;
      try {
        path = "PIntAboutInfo.html";
        url = HTMLEditorFromSwingSetDemos.class.getResource(path);

      } catch (Exception e) {
        System.err.println("Failed to open " + path);
        url = null;
      }

      if (url != null) {
        html = new JEditorPane(url);
        //html = new JEditorPane();

        html.setEditable(false);


        //String msg = "<html><body><center><a href=\"http://www.sapo.pt\">sapo</a><h2>Titlet</h2></center><b><p>Text to</p><p>display</p></b></body></html>";
        //String strMimeType = "text/html";
        //html.setContentType(strMimeType);
        //html.setText(msg);


        html.addHyperlinkListener(createHyperLinkListener());

        JScrollPane scroller = new JScrollPane();
        JViewport vp = scroller.getViewport();
        vp.add(html);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public HyperlinkListener createHyperLinkListener() {
    return new HyperlinkListener() {
      public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
          if (e instanceof HTMLFrameHyperlinkEvent) {
            ((HTMLDocument) html.getDocument()).processHTMLFrameHyperlinkEvent(
                (HTMLFrameHyperlinkEvent) e);
          } else {
            try {
              html.setPage(e.getURL());
            } catch (IOException ioe) {
              System.out.println("IOE: " + ioe);
            }
          }
        }
      }
    };
  }

}
