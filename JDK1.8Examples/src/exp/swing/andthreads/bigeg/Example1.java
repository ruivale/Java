package exp.swing.andthreads.bigeg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 @begin exp.swing.andthreads.bigeg.Example1 Interrupting a Swing Worker Thread
 To show you how to use the new <code>SwingWorker</code> class,
 this section features an example called
 <code>Example1.java</code>.
 The worker thread in this example
 contains a loop that executes 100 times,
 sleeping for half a second each time.

 <blockquote>
 <pre>
//progressBar maximum is NUMLOOPS (100)
 for(int i = 0; i < NUMLOOPS; i++) {
    updateStatus(i);
    ...
    Thread.sleep(500);
 }
 </pre>
 </blockquote>

 <p>

 To demonstrate the <code>SwingWorker</code> <code>interrupt</code>
 method,
 we've created an application that lets you
 start a <code>SwingWorker</code>
 and then either wait for it to complete or interrupt it.
 In this application's subclass of <code>SwingWorker</code>,
 the only thing the <code>construct</code> method does
 is to call an <code>Example1</code> method called <code>doWork</code>.
 The full code for the <code>doWork</code> method follows.
 The example handles interruptions of the worker thread
 by resetting the progress bar and setting the label to "Interrupted".
 Because an interruption might occur
 outside of the <code>sleep</code> method call,
 the code checks for interruptions
 before calling <code>sleep</code>.

 <blockquote>
 <pre>
 Object doWork() {
    try {
        for(int i = 0; i < NUMLOOPS; i++) {
            updateStatus(i);
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            Thread.sleep(500);
        }
    }
    catch (InterruptedException e) {
        updateStatus(0);
        return "Interrupted";
    }
    return "All Done";
 }
 </pre>
 </blockquote>

 This method does what any time consuming operation should:
 It periodically lets the user know that it's making progress.
 The <code>updateStatus</code> method queues a <code>Runnable</code> object
 for the event dispatching thread
 (remember: don't do GUI work on other threads) to
 update a progress bar.
 When the Start button is pressed,
 the action listener creates the <code>SwingWorker</code>
 and invokes <code>start()</code> on it,
 which results in the worker thread being created.
 The worker thread executes its <code>construct</code> method,
 which (as the following code shows) calls <code>doWork</code>.
 Here is the code that implements, instantiates, and starts using
 <code>Example1</code>'s subclass of <code>SwingWorker</code>.

 <blockquote>
 <pre>
 worker = new SwingWorker() {
    public Object construct() {
        return doWork();
    }
    public void finished() {
        startButton.setEnabled(true);
        interruptButton.setEnabled(false);
        statusField.setText(get().toString());
    }
 };
 worker.start();
 </pre>
 </blockquote>

 The <code>finished</code> method runs
 after the <code>construct</code> method returns
 (when the worker thread is finished).
 It simply reenables the Start button,
 disables the Cancel button,
 and sets the status field to the value computed by the worker.
 Remember that the <code>finished</code> method runs
 on the event dispatching thread,
 so it can safely update the GUI directly.
 @end Example1
 */

class Example1
    extends JPanel {
  JProgressBar progressBar = new JProgressBar();
  static int NUMLOOPS = 100;
  JLabel statusField = new JLabel("Click Start to begin");
  SwingWorker worker;
  JButton startButton;
  JButton interruptButton;

  JButton getStartButton() {
    return startButton;
  }

  /**
   * When the worker needs to update the GUI we do so by queuing
   * a Runnable for the event dispatching thread with
   * SwingUtilities.invokeLater().  In this case we're just
   * changing the progress bars value.
   */
  void updateStatus(final int i) {
    Runnable doSetProgressBarValue = new Runnable() {
      public void run() {
        progressBar.setValue(i);
      }
    };
    SwingUtilities.invokeLater(doSetProgressBarValue);
  }

  /**
   * This method represents the application code that we'd like to
   * run on a separate thread.  It simulates slowly computing
   * a value, in this case just a string 'All Done'.  It updates the
   * progress bar every half second to remind the user that
   * we're still busy.
   */
  Object doWork() {
    try {
      for (int i = 0; i < NUMLOOPS; i++) {
        updateStatus(i);
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        Thread.sleep(500);
      }
    } catch (InterruptedException e) {
      updateStatus(0);
      return "Interrupted"; // SwingWorker.get() returns this
    }
    return "All Done"; // or this
  }

  /**
   * This action listener, called by the "Start" button, effectively
   * forks the thread that does the work.
   */
  ActionListener startListener = new ActionListener() {
    public void actionPerformed(ActionEvent event) {
      startButton.setEnabled(false);
      interruptButton.setEnabled(true);
      statusField.setText("Working...");

      /* Constructing the SwingWorker() causes a new Thread
       * to be created that will call construct(), and then
       * finished().  Note that finished() is called even if
       * the worker is interrupted because we catch the
       * InterruptedException in doWork().
       */
      worker = new SwingWorker() {
        public Object construct() {
          return doWork();
        }

        public void finished() {
          startButton.setEnabled(true);
          interruptButton.setEnabled(false);
          statusField.setText(get().toString());
        }
      };
      worker.start();
    }
  };

  /**
   * This action listener, called by the "Cancel" button, interrupts
   * the worker thread which is running this.doWork().  Note that
   * the doWork() method handles InterruptedExceptions cleanly.
   */
  ActionListener interruptListener = new ActionListener() {
    public void actionPerformed(ActionEvent event) {
      interruptButton.setEnabled(false);
      worker.interrupt();
      startButton.setEnabled(true);
    }
  };

  /**
   * And now for a little assembly.  Put together the buttons, progress
   * bar and status text field.
   */
  Example1() {
    super();
    progressBar.setMaximum(NUMLOOPS);

    startButton = new JButton("Start");
    JRootPane rootPane = getRootPane();
    startButton.addActionListener(startListener);
    startButton.setEnabled(true);

    interruptButton = new JButton("Cancel");
    interruptButton.addActionListener(interruptListener);
    interruptButton.setEnabled(false);

    statusField.setFont(new Font("Dialog", Font.BOLD, 14));

    setLayout(new BorderLayout());
    JComponent buttonBox = new JPanel();
    buttonBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    buttonBox.add(startButton);
    buttonBox.add(interruptButton);
    add(buttonBox, BorderLayout.NORTH);
    add(progressBar, BorderLayout.CENTER);
    add(statusField, BorderLayout.SOUTH);
  }
}

/**
 @begin exp.swing.andthreads.bigeg.Example2 Prompting the User from a Swing Worker Thread
 This example is implemented as a subclass of <code>Example1</code>.
 The only difference is that after the worker thread
 has run for about two seconds, it blocks until the user has
 responded to a Continue/Cancel modal dialog.
 If the user doesn't choose Continue,
 we exit the <code>doWork</code> loop.

 <p>

 This example demonstrates
 an idiom common to many worker threads:
 If the worker runs into
 an unexpected condition it may need to block until it has
 alerted the user or collected more information from the user
 with a modal dialog.  Doing so is a little complex because the dialog
 needs to be shown on the event-dispatching thread, and the
 worker thread needs to be blocked until the user has
 dismissed the modal dialog.
 <p>
 We use the <code>SwingUtilities</code> method
 <code>invokeAndWait</code> to pop up the
 dialog on the event-dispatching thread.  Unlike
 <code>invokeLater</code>, <code>invokeAndWait</code> blocks
 until its <code>Runnable</code> object
 returns.   In this case the <code>Runnable</code> object
 will
 not return until the dialog has been dismissed.  We create an
 inner <code>Runnable</code> class, <code>DoShowDialog</code>,
 to handle popping up the dialog.  An instance variable,
 <code>DoShowDialog.proceedConfirmed</code>, records the user's
 response:
 <blockquote>
 <pre>
 class DoShowDialog implements Runnable {
    boolean proceedConfirmed;
    public void run() {
        Object[] options = {"Continue", "Cancel"};
        int n = JOptionPane.showOptionDialog(Example2.this,
                                    "Example2: Continue?",
                                    "Example2",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    "Continue");
        proceedConfirmed = (n == JOptionPane.YES_OPTION);
    }
 }
 </pre>
 </blockquote>

 Because the <code>showConfirmDialog()</code> method
 pops up a modal dialog, the call blocks until the user
 dismisses the dialog.
 <p>
 To show the dialog and block the calling thread
 (the worker thread)
 until the dialog's dismissed,
 the worker thread calls the
 <code>invokeAndWait</code> method,
 specifying an instance of <code>DoShowDialog</code>:
 <blockquote>
 <pre>
 DoShowDialog doShowDialog = new DoShowDialog();
 try {
    SwingUtilities.invokeAndWait(doShowDialog);
 }
 catch (java.lang.reflect.InvocationTargetException e) {
    e.printStackTrace();
 }
 </pre>
 </blockquote>

 The code catches the <code>InvocationTargetException</code>
 as a relic of debugging
 <code>DoShowDialog</code>'s <code>run</code> method.
 Once the <code>invokeAndWait</code> method has returned,
 the worker thread can read
 <code>doShowDialog.proceedConfirmed</code>
 to get the user's response.
 @end Example2
 */
class Example2
    extends Example1 {
  /**
   * Popup a modal confirm dialog and block until the user responds.
   * Return true unless the user selects "NO".
   */
  boolean waitForUserConfirmation() throws InterruptedException {

    /* We're going to show the modal dialog on the event dispatching
     * thread with SwingUtilities.invokeLater(), so we create a
     * Runnable class that shows the dialog and stores the users
     * response.
     */
    class DoShowDialog
        implements Runnable {
      boolean proceedConfirmed;
      public void run() {
        Object[] options = {
            "Continue", "Cancel"};
        int n = JOptionPane.showOptionDialog(Example2.this,
                                             "Example2: Continue?",
                                             "Example2",
                                             JOptionPane.YES_NO_OPTION,
                                             JOptionPane.QUESTION_MESSAGE,
                                             null,
                                             options,
                                             "Continue");
        proceedConfirmed = (n == JOptionPane.YES_OPTION);
      }
    }

    DoShowDialog doShowDialog = new DoShowDialog();

    /* The invokeAndWait utility can throw an InterruptedException,
     * which we don't bother with, and an InvocationException.
     * The latter occurs if our Runnable throws - which would indicate
     * a bug in DoShowDialog.  The invokeAndWait() call will not return
     * until the user has dismissed the modal confirm dialog.
     */
    try {
      SwingUtilities.invokeAndWait(doShowDialog);
    } catch (java.lang.reflect.InvocationTargetException e) {
      e.printStackTrace();
    }
    return doShowDialog.proceedConfirmed;
  }

  /**
   * This is just a copy of Example1.doWork() with one extra wrinkle.
   * After about two seconds we ask the user to confirm with
   * a modal dialog (see waitForUserConfirmation()); if the answer
   * is NO then we short circuit.
   */
  Object doWork() {
    try {
      for (int i = 0; i < NUMLOOPS; i++) {
        updateStatus(i);
        if (i == 4) {
          if (!waitForUserConfirmation()) {
            updateStatus(0);
            return "Not Confirmed";
          }
        }
        Thread.sleep(500);
      }
    } catch (InterruptedException e) {
      updateStatus(0);
      return "Interrupted"; // SwingWorker.get() returns this
    }
    return "All Done"; // or SwingWorker.get() returns this
  }
}
