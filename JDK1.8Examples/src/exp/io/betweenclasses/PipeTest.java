package exp.io.betweenclasses;

import java.io.*;

public class PipeTest {
  public static void main(String args[]) {
    new OilRefinery();
    try { // delay arrival
      Thread.currentThread().sleep(500);
    } catch (InterruptedException e) {
    }
    new SuperTanker();
  }

  // This class consists of a Thread that can accept "pipline" hook-ups
  // via the "clickClunk" method. Clients have to find us though from
  // our Thread name "ThePipeTerminal"
  static class OilRefinery
      extends Thread {
    static final int EOF = -1;
    boolean alone = true;

    // Can't connect piped until "clickClunk"
    PipedReader inPipe;
    PipedWriter outPipe;

    public OilRefinery() {
      start(); // Start the thread
    }

    public synchronized void run() {
      int ch;
      // Open for business
      setName("ThePipeTerminal");
      System.out.println("Processing plant operational and on-line");
      while (alone) {
        try {
          wait(); // Non-busy wait for connection
        } catch (InterruptedException ohLeaveMeAlone) {
        }
      }
      System.out.println("Client arrived");
      // At this point, a client has connected up to the pipes
      // so process the flow of oil
      try {
        while ( (ch = inPipe.read()) != EOF) {
          // add some value to raw input...
          outPipe.write(Character.toUpperCase( (char) ch));
        }
      } catch (IOException pipeMalfunction) {
      }
      try {
        outPipe.close(); // signal client "The show's over!"
      } catch (IOException ignored) {
      }
      alone = true;
      System.out.println("Processing plant shutting down.");
    }

    // This is the method clients have to call to connect up to
    // the processing plant
    public synchronized boolean clickClunk(PipedWriter clientOutputPipe,
                                           PipedReader clientInputPipe) {
      System.out.println("Client arrives to hook-up its pipes");
      try {
        inPipe = new PipedReader(clientOutputPipe);
        outPipe = new PipedWriter(clientInputPipe);
      } catch (IOException connectionFailed) {
        System.err.println("Hook up failed");
        return false;
      }
      System.out.println("Hook-up successful");
      alone = false;
      notify();
      return true;
    }
  } // End of class OilRefinery

  // This class implements a processing plant client, say a
  // supertanker that arrives at the plant to unload its
  // crude oil and load up with refined oil
  static class SuperTanker {
    OilRefinery pipeTerminal = null;
    PipedReader returnPipe = new PipedReader();
    PipedWriter crudePipe = new PipedWriter();

    public SuperTanker() {
      pipeTerminal = (OilRefinery) findThread("ThePipeTerminal");
      if (pipeTerminal == null) {
        System.err.println("Snow blizzards prevent rendezvous");
        System.exit(100);
      } else {
        if (pipeTerminal.clickClunk(crudePipe, returnPipe)) {
          haveOilProcessed();
        } else {
          System.err.println("Failed to connect to processing plant");
        }
        try {
          crudePipe.close();
        } catch (IOException brokenValves) {
          System.err.println("Couldn't close valves!");
        }
      }
    }

    // Send data (oil) to processing plant, which refines data and
    // sends it back via second pipe stream
    public void haveOilProcessed() {
      String oilToBeRefined = "Crude Oil";

      try {
        crudePipe.write(oilToBeRefined);
        crudePipe.close();

        // Get back refined oil
        int ch;
        while ( (ch = returnPipe.read()) != -1) {
          System.out.print( (char) ch);
        }
        System.out.println();
      } catch (IOException oilFlowFailure) {
        System.err.println("Pipe malfunction");
      }
    }

    // This generic method locates the refinery thread
    // Note that threads may start/end while checking
    public Thread findThread(String target) {
      int SAFETY_MARGIN = 10;
      // Find master ThreadGroup which all others descend
      ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
      while (rootGroup.getParent() != null) {
        rootGroup = rootGroup.getParent();
      }
      Thread threadList[] =
          new Thread[rootGroup.activeGroupCount() + SAFETY_MARGIN];
      int count = rootGroup.enumerate(threadList);
      Thread aThread;
      for (int i = 0; i < count; i++) {
        aThread = threadList[i];
        if (aThread == null) {
          continue;
        }
        if (aThread.getName().equals(target)) {
          return aThread;
        }
      }
      return null;
    }
  } // End of class SuperTanker

} // End of class PipeTest
