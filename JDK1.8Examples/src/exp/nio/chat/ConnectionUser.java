package exp.nio.chat;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

/**
 * describes a user of a Connection
 * @author PKWooster
 * @version 1.0 June 15,2004
 */
public interface ConnectionUser {
  public void receive(String str);

  public void stateChange(int state);
}
