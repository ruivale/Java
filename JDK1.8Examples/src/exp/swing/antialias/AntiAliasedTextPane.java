package exp.swing.antialias;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 *
 * While this works fine, this is really annoying to do when you want to use
 * application-wide antialiasing. Note that you can also use the RenderingHints.
 * KEY_FRACTIONALMETRICS to improve the quality but it raises many issues with
 * caret handling so you should avoid it with editable text components. Anyway
 * as you cannot subclass every single widget of Swing we need another way to
 * do this.
 *
 * With the J2SE 5 SDK 1.5 there are two other techniques, muche more easier and
 * elegant. The JComponent class can accept a property called
 * SwingUtilities2.AA_TEXT_PROPERTY_KEY.
 * When you set it to true, the JComponent text is rendered with antialiasing on.
 * Thus you can have an antialiased button by simply writing this:
 * button.putClientProperty(
 *   SwingUtilities2.AA_TEXT_PROPERTY_KEY, new Boolean(true));
 *
 * Even though it is much better than the first solution it is still annoying
 * when you want application-wide antialiasing. A third solution exists and is
 * really simple: you can simply set the value of the system property
 * "swing.aatext" to "true" to enable antialiasing for every JComponent.
 *
 * You can set it through the command line with the -D flag :
 * $ java -jar lib/application.jar -Dswing.aatext=true
 *
 *
 * @author not attributable
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextPane;

public class AntiAliasedTextPane
    extends JTextPane {
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
    super.paintComponent(g2);
  }

  public static void main(String[] args) {
    AntiAliasedTextPane a = new AntiAliasedTextPane();
    JFrame f = new JFrame("UI Defaults");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(a, BorderLayout.CENTER);
    f.setBounds(100, 100, 300, 200);
    f.setVisible(true);
  }
}
