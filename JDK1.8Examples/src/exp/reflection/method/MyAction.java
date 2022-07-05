package exp.reflection.method;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

import java.lang.reflect.*;

public class MyAction {
  Method method;
  Class c;

  public MyAction(String className, String methodName, Object[] args) {

    Class para[]; // = new Class[args.length];

    try {
      c = Class.forName(className);

      if (args != null) {
        para = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
          para[i] = Class.forName("java.lang.String");
        }
      } else {
        para = new Class[0];
        args = new Object[0];
      }

      Method method = c.getMethod(methodName, para);

      method.invoke(c.newInstance(), args);

    } catch (ClassNotFoundException cnfe) {
      System.out.println("ClassNotFoundException");
      cnfe.printStackTrace();
    } catch (NoSuchMethodException nsme) {
      System.out.println("NoSuchMethodException");
      nsme.printStackTrace();
    } catch (Exception e) {
      System.out.println("InvokationException");
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    String arguments[] = {
        "a", "b"};
    MyAction myAction1 = new MyAction("exp.reflection.method.Auxiliar",
                                      "methodToInvoke",
                                      arguments);
  }

}
