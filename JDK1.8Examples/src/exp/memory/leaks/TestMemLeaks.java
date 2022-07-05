package exp.memory.leaks;


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
public class TestMemLeaks {
  public static void main(String[] args) {

    java.util.ArrayList al = new java.util.ArrayList();
    al.add(new myObject());
    al.add(new myObject());
    al.add(new myObject());

    myObject.printMem();
    //We can see that Objects are not freed.
    al.clear();
    myObject.printMem();
    //Ah yes,  Objects are released.
  }
}


class myObject {

  public static int OBJC;
  public static int OBJD;

  {
    //When an Object is created, we increase this counter
    OBJC++;
    System.out.println("OBJC++");
  }

  /**
   * When an Object is finalized we decrease this counter
   */
  public void finalize() {
    OBJD++;
    System.out.println("OBJD++");
  }

  /**
   * Print the Objects created and finalized
   */
  static void printMem() {
    System.gc();
    System.out.println("created " + OBJC + " finalized " + OBJD);
  }

}
