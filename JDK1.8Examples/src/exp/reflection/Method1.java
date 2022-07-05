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
 Finding Out About Methods of a Class
 One of the most valuable and basic uses of reflection is to find out
 what methods are defined within a class. To do this the
 following code can be used:
 */

import java.lang.reflect.*;

public class Method1 {

  public static void main(String[] args) {
    try {
      Class cls = Class.forName("exp.reflection.Method1");

      Method methlist[]
          = cls.getDeclaredMethods();
      for (int i = 0; i < methlist.length;
           i++) {
        Method m = methlist[i];
        System.out.println("name = " + m.getName());
        System.out.println("decl class = " +
                           m.getDeclaringClass());
        Class pvec[] = m.getParameterTypes();
        for (int j = 0; j < pvec.length; j++) {
          System.out.println("param #" + j + " " + pvec[j]);
        }
        Class evec[] = m.getExceptionTypes();
        for (int j = 0; j < evec.length; j++) {
          System.out.println("exc #" + j
                             + " " + evec[j]);
        }
        System.out.println("return type = " +
                           m.getReturnType());
        System.out.println("-----");
      }
    } catch (Throwable e) {
      System.err.println(e);
    }
  }

  private int f1(Object p, int x) throws NullPointerException {
    if (p == null) {
      throw new NullPointerException();
    }
    return x;
  }

}

/*
 The program first gets the Class description for method1, and then calls
 getDeclaredMethods to retrieve a list of Method
 objects, one for each method defined in the class. These include public,
 protected, package, and private methods. If you use getMethods
 in the program instead of getDeclaredMethods, you can also obtain information for inherited methods.

 Once a list of the Method objects has been obtained, it's
 simply a matter of displaying the information on parameter types, exception
 types, and the return type for each method. Each of these types,
 whether they are fundamental or class types, is in turn represented by a
 Class descriptor.
 */
