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
public class Test {
    void testB(){
        System.out.println("testB");
        B.static_a();
        // aqui o static init da B não é invocado
    }
    void testBa(){
        System.out.println("testBa");
        B b = new B();
        b.a();
        // aqui os statics são invocados, 1º o A, depois o B por causa do new B()
    }

    void testBb(){
        System.out.println("testBb");
        B b = new B();
        b.b();
        // aqui os statics são invocados, 1.º o A, depois o B por causa do new B()
    }
    void testBstatic_b(){
        System.out.println("testBstatic_b");
        B.static_b();
        // aqui os statics são invocados, 1.º o A, depois o B
    }

    void testA(){
        System.out.println("testA");
        A.static_a();
        // apenas o static A, como é trivial ...
    }

    void testNewA(){
        System.out.println("testNewA");
        A a = new A();
        a.a();
        // apenas o static A, como é trivial ...
    }


    public static void main(String[] s){
        Test t = new Test();
        t.testB();
        //t.testBa();
        //t.testBb();
        //t.testBstatic_b();
        //t.testA();
        //t.testNewA();
    }
}
