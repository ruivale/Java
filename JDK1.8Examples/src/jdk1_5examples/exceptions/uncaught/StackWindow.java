package jdk1_5examples.exceptions.uncaught;


import java.awt.*;
import java.io.*;
import javax.swing.*;


/**
 *
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public class StackWindow
    extends JFrame implements Thread.UncaughtExceptionHandler {

  private JTextArea textArea;

  public StackWindow(String title, final int width, final int height) {
    super(title);
    setSize(width, height);
    textArea = new JTextArea();
    JScrollPane pane = new JScrollPane(textArea);
    textArea.setEditable(false);
    getContentPane().add(pane);
  }

  public void uncaughtException(Thread t, Throwable e) {
    addStackInfo(e);
  }

  public void addStackInfo(final Throwable t) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        // Bring window to foreground
        setVisible(true);
        toFront();
        // Convert stack dump to string
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        t.printStackTrace(out);
        // Add string to end of text area
        textArea.append(sw.toString());
      }
    });
  }
}
