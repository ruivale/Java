package exp.nio.echoserver;


/*******************************************************************************************
    NIOControllable.java
        interface describing a controllable object
        this is the client of a NIOController
        author PKWooster, Oct 2003 GPL license
 */
import java.nio.channels.*;


public interface NIOControllable {
  public static final int CLOSED = 0;
  public static final int OPENING = 1;
  public static final int OPENED = 2;
  public static final int CLOSING = 3;

  public void processSelection(SelectionKey sk); // the key is selected

  public int getState(); // get the connection state
}
