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
public class A {

    static{
        System.out.println("A");
    }

    public A() {
    }

    public static void static_a(){
        System.out.println("A.static_a ");
    }

    public void a(){
        System.out.println("A.a");
    }

}
