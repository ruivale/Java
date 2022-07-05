package exp.nio.echoserver;


/*******************************************************************************************
    NIOController.java
        provides support for a Selector for multiple Selectable channels
        adds the capability to run arbitrary methods in the controller's thread
        author PKWooster, Oct 2003 GPL license
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;


public class NIOController {
  private Selector selector; // the NIO selector
  private LinkedList invocations; // a list of invokations
  boolean running;
  int loopCount = 0;

  // constructor
  NIOController() {
    try {
      selector = Selector.open();
    } catch (IOException e) {
      e.printStackTrace();
    }
    invocations = new LinkedList();
    running = false;
  }

  // start the controller, asThread will start it in a separate thread
  public void start() {
    start(true);
  } // default runs a thread

  public void start(boolean asThread) {
    if (asThread) {
      Thread th = new Thread(new Runnable() {
        public void run() {
          select();
          System.out.println("Controller thread ended");
        }
      });
      th.setName("NIOController");
      th.start();
    } else {
      select();
    }
  }

  // stop the dispathcer
  public void stop() {
    running = false;
    selector.wakeup();
  }


  // register a channel with this controller
  // if this is run from another thread after the controller is started this may block,
  // use invoke to prevent that.
  public SelectionKey enroll(NIOControllable c, SelectableChannel sch,
                             int interest) {
    SelectionKey sk = null;
    try {
      sch.configureBlocking(false);
      sk = sch.register(selector, interest, c);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.out.println("key="+sk+" enrolled interest="+sk.interestOps());
    return sk;
  }

  // request a call back
  public synchronized void invoke(Runnable d) {
    invocations.add(d); // add it to our request queue
    selector.wakeup(); // break out of the select
  }

  public void select() {
    int n;
    Iterator it;
    SelectionKey key;
    Object att;
    NIOControllable c;
    int io;
    running = true;
    int j = 0;

    while (running) {
      // run any requested invocations
      doInvocations();

      // now we select any pending io
      try {
        n = selector.select();
      } catch (Exception e) { // select
        e.printStackTrace();
        return;
      }
      // System.out.println("select n="+n);
      if (n == 0) {
        loopCount++;
        if (loopCount > 10) {
          System.out.println("loop detected");
          break;
        }
      } else {
        loopCount = 0; // **** testing
      }

      // process any selected keys
      Set selectedKeys = selector.selectedKeys();
      it = selectedKeys.iterator();
      while (it.hasNext()) {
        key = (SelectionKey) it.next();
        c = (NIOControllable) key.attachment(); // get the controllable
        c.processSelection(key); // ask it to process its selections
        it.remove(); // remove the key
      }
    }
    System.out.println("select ended");
  }

  // run the invocations in our thread, these probably set the interestOps,
  // or do I/o
  // but they could do almost anything
  private synchronized void doInvocations() {
    Runnable r;
    boolean b = true;
    while (invocations.size() > 0) {
      loopCount = 0;
      r = (Runnable) invocations.removeFirst();
      r.run();
    }
  }
}

