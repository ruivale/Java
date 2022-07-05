package exp.reflection;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

/*
 Creating New Objects
 There is no equivalent to method invocation for constructors, because
 invoking a constructor is equivalent to creating a new object (to be the
 most precise, creating a new object involves both memory allocation and
 object construction). So the nearest equivalent to the previous example
 is to say:
 */

import java.lang.reflect.*;

public class Constructor2 {
  int a=-1, b=-1;
  public Constructor2() {
  }

  public Constructor2(int a, int b) {
    this.a = a;
    this.b = b;
  }

  public void printArgs(){
    System.out.println(
        "a = " + a + " b = " + b);
  }


  public static void main(String[] args) {
    try {
      Class cls = Class.forName("exp.reflection.Constructor2");
      Class partypes[] = new Class[2];
      partypes[0] = Integer.TYPE;
      partypes[1] = Integer.TYPE;
      Constructor ct = cls.getConstructor(partypes);
      Object arglist[] = new Object[2];
      arglist[0] = new Integer(37);
      arglist[1] = new Integer(47);
      Object retobj = ct.newInstance(arglist);

      ((Constructor2)retobj).printArgs();

    } catch (Throwable e) {
      System.err.println(e);
    }
  }

}

/*
 which finds a constructor that handles the specified parameter types and
 invokes it, to create a new instance of the object. The value of this
 approach is that it's purely dynamic, with constructor lookup and invocation
 at execution time, rather than at compilation time.
 */
