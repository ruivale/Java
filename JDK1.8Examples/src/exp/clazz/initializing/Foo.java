package exp.clazz.initializing;


public class Foo extends Bar {

  static {
    System.out.println("Foo:static 1");
  }

  {
    System.out.println("Foo:instance 1");
  }

  static {
    System.out.println("Foo:static 2");
  }

  public Foo() {
    System.out.println("Foo:constructor");
  }

  public Foo(String name) {
    super(name);
    System.out.println("Foo:name-constructor");
  }

  {
    System.out.println("Foo:instance 2");
  }

  public static void main(String... args) {
    new Foo();
    System.out.println();
    new Foo("Baz");
  }
}
