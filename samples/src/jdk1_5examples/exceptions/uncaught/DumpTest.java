package jdk1_5examples.exceptions.uncaught;


import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * <p>
 * Title: JDK1.5 Examples
 * </p>
 *
 * <p>
 * Description:
 *
 * As its name implies, the UncaughtExceptionHandler handles uncaught
 * exceptions. More specifically, it handles uncaught runtime exceptions. The
 * java compiler requires all non-runtime exceptions to be handled, otherwise
 * the program won't compile. Here "handled" means that exceptions are
 * declared in the throws clause of the declaring method or caught in the
 * catch clause of a try-catch block.
 *
 * To demonstrate, lets look at two exceptions: FileNotFoundException and ArithmeticException. Calling the constructor for FileReader with either a String or File argument throws a FileNotFoundException if the provided location does not point to a valid regular file. The compiler requires that when you call either of these constructors, you handle the thrown exception:

    FileReader input;
    String filename = ...;
    try {
      input = new FileReader(filename);
    } catch (FileNotFoundException e) {
      processMissingFile(filename, e);
    }

 By comparison, an ArithmeticException is a type of runtime exception. The Java programming language specification (and so too the compiler) doesn't require that runtime exceptions be handled. So the following loop, which divides by 100 the numbers from 10-to-0, throws an ArithmeticException on the final pass through the loop:

    for (int i=10; i >= 0; i--) {
      int div = 100 / i;
    }

    Exception in thread "main" java.lang.ArithmeticException: /
    by zero
         at Test.main(Test.java:4)

 Printing the stack trace is what the default uncaught exception handler does. Typically, this default behavior is sufficient, but sometimes it isn't. Imagine that you want to show the stack trace in a popup window, instead of dumping the trace to the system console. By installing your own default uncaught exception handler, you can get this behavior.

 There are at least three ways to install an uncaught exception handler. First, you can call the setUncaughtExceptionHandler() method of Thread. This allows you to customize the behavior for a specific thread. Second, you can define your own ThreadGroup and change the behavior of any threads created in the group by overriding their uncaughtException() method. Third, you can set the default behavior for all threads by calling the static setDefaultUncaughtExceptionHandler() method of Thread.

 Both the setUncaughtExceptionHandler() and setDefaultUncaughtExceptionHandler() methods of Thread accept an implementation of the UncaughtExceptionHandler interface an argument. The interface is an inner interface of the Thread class so its full name is Thread.UncaughtExceptionHandler. This interface has a single method:

    void uncaughtException(Thread t, Throwable e)

 You can get the customized behavior by providing an implementation of the uncaughtException method, either as part of your custom implementation of the interface or as an overridden method of ThreadGroup. To demonstrate, here's an implementation of UncaughtExceptionHandler that shows a window with the stack trace appended to end of a text area whenever a runtime exception is encountered. You can close the window between exceptions. The window will reappear in front of other windows when the next exception happens.

    import java.awt.*;
    import java.io.*;
    import javax.swing.*;

    public class StackWindow extends JFrame
        implements Thread.UncaughtExceptionHandler {

      private JTextArea textArea;

      public StackWindow(
       String title, final int width, final int height) {
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

 To test the handler, you need a program that installs the handler and then throws some runtime exceptions. The following program, DumpTest, does that. For simplicity, DumpTest only generates two exceptions. Feel free to add more troublesome code to the program, with more exceptions thrown. The program pauses between exceptions to show that you can close the exception dump stack window between exceptions.

    import java.io.*;

    public class DumpTest {
     public static void main(final String args[])
      throws Exception {
        Thread.UncaughtExceptionHandler handler =
          new StackWindow("Show Exception Stack", 400, 200);
        Thread.setDefaultUncaughtExceptionHandler(handler);
        new Thread() {
          public void run() {
            System.out.println(1 / 0);
          }
        }.start();
        BufferedReader br =
          new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Press Enter for next exception");
        br.readLine();
        new Thread() {
          public void run() {
            System.out.println(args[0]);
          }
        }.start();
        System.out.print("Press Enter to end");
        br.readLine();
        System.exit(0);
      }
    }

 Compile StackWindow and DumpTest. When you run DumpTest, you should see the follow in your console:

    > java DumpTest
    Press Enter for next exception

 You'll also see a window with the stack trace for an exception in the text area.

 UncaughtExceptionWindow Window

 Press Enter, and you should see the following in your console:

    Press Enter to end

 You should also see another stack trace appended to the text area in the window.

 UncaughtExceptionWindow Window

 Although you might think that this is all there is to handling uncaught exceptions, there is more. Modal dialogs require their own event thread and because of that, their own uncaught handler. The system property sun.awt.exception.handler does cover all cases, but is not well documented. An RFE (request for enhancement) has been submitted to expand the property into a formal API.

 In addition to the Best Practices tip on exception handling, the May 2002 tip on StackTraceElements is another useful point of reference. See also the lesson Handling Errors Using Exceptions in The Java Tutorial.
 *
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 *
 * <p>
 * Company: ??????????
 * </p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public class DumpTest {
  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  public static void main(final String[] args)
      throws Exception {
    Thread.UncaughtExceptionHandler handler = new StackWindow("Show Exception Stack",
                                                              400,
                                                              200);
    Thread.setDefaultUncaughtExceptionHandler(handler);
    new Thread() {
        public void run() {
          System.out.println(1 / 0);
        }
      }.start();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Press Enter for next exception");
    br.readLine();
    new Thread() {
        public void run() {
          System.out.println(args[0]);
        }
      }.start();
    System.out.print("Press Enter to end");
    br.readLine();
    System.exit(0);
  }
}
