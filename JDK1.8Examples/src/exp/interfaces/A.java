package exp.interfaces;

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
public abstract class A implements AInt{
  public int getID(){
    System.out.println("A - getID()");
    getName();
    return 1009;
  }
}
