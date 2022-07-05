package exp.reflection.proxy;

import java.lang.reflect.*;

public class TraceProxy
    implements java.lang.reflect.InvocationHandler {

  private Object obj;

  private TraceProxy(Object obj) {
    this.obj = obj;
  }

  public static Object newInstance(Object obj) {
    return java.lang.reflect.Proxy.newProxyInstance(
        obj.getClass().getClassLoader(),
        obj.getClass().getInterfaces(),
        new TraceProxy(obj));
  }

  public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
    Object result;
    try {
      System.out.print("begin method " + m.getName() + "(");
      for (int i = 0; i < args.length; i++) {
        if (i > 0) {
          System.out.print(",");
        }
        System.out.print(" " + args[i].toString());
      }

      System.out.println(" )");
      result = m.invoke(obj, args);

    } catch (InvocationTargetException e) {
      throw e.getTargetException();
    } catch (Exception e) {
      throw new RuntimeException("unexpected invocation exception: " +
                                 e.getMessage());
    } finally {
      System.out.println("end method " + m.getName());
    }
    return result;
  }

}
