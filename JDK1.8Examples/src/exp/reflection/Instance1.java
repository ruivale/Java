package exp.reflection;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

/**
 Simulating the instanceof Operator

 Once Class information is in hand, often the next step is to ask basic
 questions about the Class object. For example, the Class.isInstance
 method can be used to simulate the instanceof operator:

 */

class A {}

public class Instance1 {

  public static void main(String[] args) {
    try {
      Class cls = Class.forName("A");
      boolean b1
          = cls.isInstance(new Integer(37));
      System.out.println(b1);
      boolean b2 = cls.isInstance(new A());
      System.out.println(b2);
    } catch (Throwable e) {
      System.err.println(e);
    }
  }
}

/*
 In this example, a Class object for A is created, and then class
 instance objects are checked to see whether they are instances of A.
 Integer(37) is not, but new A() is.
 */
