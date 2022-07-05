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

public class InvokeMethod {

  public InvokeMethod() {
    try {
      Class cls = Class.forName("exp.reflection.method.Auxiliar");
      Class partypes[] = new Class[2];
      partypes[0] = Integer.TYPE;
      partypes[1] = Integer.TYPE;
      Method meth = cls.getMethod("methodToInvoke", partypes);
//            exp.reflection.method.Auxiliar methobj = new exp.reflection.method.Auxiliar();
      Object arglist[] = new Object[2];
      arglist[0] = new Integer(7);
      arglist[1] = new Integer(4);
      Object retobj = meth.invoke(cls.newInstance(), arglist);
//            Object retobj = meth.invoke(methobj, arglist);
      Integer retval = (Integer) retobj;
      System.out.println(retval.intValue());
    } catch (Throwable e) {
      System.err.println(e);
    }
  }

  public static void main(String[] args) {
    InvokeMethod invokeMethod1 = new InvokeMethod();
  }

}
