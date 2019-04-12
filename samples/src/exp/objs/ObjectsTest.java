package exp.objs;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ObjectsTest {
  A a = new A();
  B b = new B();
  public ObjectsTest() {
    new C();
    /*
    System.out.println("ObjectsTest a.a="+a.a+". antes");
    System.out.println("ObjectsTest b.b="+b.b+". antes");
    new Test(this);
    //new Test(a,b);
    System.out.println("ObjectsTest a.a="+a.a+". depois");
    System.out.println("ObjectsTest b.b="+b.b+". depois");
    */
  }

  public A getA(){
    return a;
  }
  public B getB(){
    return b;
  }

  public static void main(String[] args) {
    ObjectsTest objectsTest1 = new ObjectsTest();
  }

  class A{
    int a = 0;
  }
}
class B{
  int b = 0;

  protected int b(){return b;}
}

class C extends B{
  int c = 10000;

  C(){
    System.out.println("b()="+b()+".");
  }
  public int b(){
    System.out.println("super.b()="+super.b()+".");

    return c;
  }
}

class Test{
  public Test(final ObjectsTest o){
    ObjectsTest.A a = o.getA();
    B b = o.getB();
    a.a = 20;
    b.b = 20;
    System.out.println("Test a.a="+a.a+".");
    System.out.println("Test b.b="+b.b+".");
    a = null;
    b = null;
  }
  public Test(ObjectsTest.A _a, B _b){
    ObjectsTest.A a = _a;
    B b = _b;
    a.a = 20;
    b.b = 20;
    System.out.println("Test a.a="+a.a+".");
    System.out.println("Test b.b="+b.b+".");
    a = null;
    b = null;
    _a = null;
    _b = null;
  }
}




