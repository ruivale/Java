package exp.swing.jeditorpane;


import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;


/**
 * <p>Classname: com.ent.PInt.windows.AboutWindow</p>
 *
 * <p>Description:
 * Used to display some information related to EFACEC.
 * </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version $Revision: 1.9 $
 */
public class PIntAboutWindow
    extends JDialog {

  private static final String STR_PROPERTY_FILE =
      "com.ent.PInt.windows.PIntAboutInfo";

  /** This class LOGGER */
  private static final Logger LOGGER =
      Logger.getLogger(PIntAboutWindow.class.getName());

  private static final int INT_W = 460;
  private static final int INT_H = 610;

  /**
   *
   * @param owner JFrame
   */
  public PIntAboutWindow(final JFrame owner) {
    super(owner,
          "About",
          true);

    //this.setResizable(false);


    final JEditorPane html;
    final JScrollPane scroller = new JScrollPane();
    final JViewport vp;

    try {
      URL url = new URL("http://172.18.56.69/cgi-bin/ulogin.cgi");

      html = new JEditorPane(url);
      html.setEditable(false);
      html.addHyperlinkListener(createHyperLinkListener(html));

      vp = scroller.getViewport();
      vp.add(html);

    } catch (Exception ex) {
      if (LOGGER.isLoggable(Level.SEVERE)) {
        LOGGER.log(
            Level.SEVERE,
            "Cannot create the PInt's about window.",
            ex);
      }
    }

    final JButton jButtonCancel = new JButton("Exit");
    jButtonCancel.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          dispose();
        }
      });

    final JPanel jPanelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
    jPanelSouth.add(jButtonCancel);


    final Container container = this.getContentPane();
    container.setLayout(new BorderLayout(0,0));

    container.add(jPanelSouth, BorderLayout.SOUTH);
    container.add(scroller, BorderLayout.CENTER);

    setSize(INT_W, INT_H);
    setLocationRelativeTo(owner);

    setVisible(true);
  }

  /**
   *
   * @param html JEditorPane
   * @return HyperlinkListener
   */
  private HyperlinkListener createHyperLinkListener(final JEditorPane html) {
    return new HyperlinkListener() {
      public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
          if (e instanceof HTMLFrameHyperlinkEvent) {
            ( (HTMLDocument) html.getDocument()).processHTMLFrameHyperlinkEvent(
                (HTMLFrameHyperlinkEvent) e);
          } else {
            try {
              html.setPage(e.getURL());
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }
        }
      }
    };
  }

  public static void main(String[] args) {
    JFrame f = new JFrame("...");
    f.getContentPane().setLayout(new BorderLayout());
    f.setSize(200,100);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    PIntAboutWindow d = new PIntAboutWindow(f);
  }
}
