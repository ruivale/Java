package exp.staticinits;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class B extends A {
    static{
        System.out.println("B");
    }
    public B() {
    }

    public static void static_b(){
        System.out.println("static_b");
    }
    public void b(){
        System.out.println("b");
    }
}
