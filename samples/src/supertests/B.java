package supertests;


public class B extends A {

  public B(){
    System.out.println("B()");
  }

  public B(String s){
    super(3, 5);
    System.out.println("B("+s+")");
  }
  
  public static void main(final String[] args){
    final B clazz = new B();
    System.out.println("-----------------------------");
    final B b = new B("string");
  }
}
