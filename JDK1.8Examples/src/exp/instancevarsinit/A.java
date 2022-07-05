package exp.instancevarsinit;


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
public abstract class A {
  private Object o = new Object();

  public A(Object obj) {
    if(obj != null){
      System.out.println("B obj is NOT null ...");
    }else{
      System.out.println("B obj is null ...");
    }
  }

}
