package exp.nio.echoserver;


/*******************************************************************************************
    NMessage.java
        author PKWooster, Oct 2003 GPL license

        a simple message that can be given to invokeLater or NIOController.invoke since it implements Runnable
        note: no new thread is created for this runnable, its just a way to pass a method to the
        AWT event dispatch thread or the NIOController thread
 */

public class NMessage
    implements Runnable {
  int type;
  static public final int DATA = 0; // string data for the target thread
  static public final int OPEN = 1; // open action
  static public final int CLOSE = 2; // close action

  NMReceiver target;
  Object data; // the message data
  // construct a message from a receiver, an data string and a type
  NMessage(NMReceiver t, Object data, int type) {
    target = t;
    this.data = data;
    this.type = type;
  }

  // this method is run by a thread when it pops this runnable off the queue
  public void run() {
    target.runNMessage(this);
  }
}
