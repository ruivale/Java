package exp.clazz.initializing;


public abstract class Bar {

  private String name;

  static {
    System.out.println("Bar:static 1");
  }

  {
    System.out.println("Bar:instance 1");
  }

  static {
    System.out.println("Bar:static 2");
  }

  public Bar() {
    System.out.println("Bar:constructor");
  }

  {
    System.out.println("Bar:instance 2");
  }

  public Bar(String name) {
    this.name = name;
    System.out.println("Bar:name-constructor");
  }
}
