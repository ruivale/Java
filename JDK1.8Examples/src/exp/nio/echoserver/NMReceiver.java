package exp.nio.echoserver;


/*******************************************************************************************
    NMReceiver.java
        author PKWooster, Oct 2003 GPL license

        interface describing a class that receives NMessages
 */
public interface NMReceiver {
  public void runNMessage(NMessage msg);
}
