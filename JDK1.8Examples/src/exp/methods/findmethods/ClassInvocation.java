package exp.methods.findmethods;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * <p>Classname:com.ent.PInt.GIs.GIClassInvocation</p>
 *
 * <p>Description:
 * Used when the PInt creates a new GI instance when the user clicks over an GI
 * at the GI access area. This class is used while "reflection" is used to
 * create a new instance of a GI.
 * </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version $Revision: 1.8 $
 */
public class ClassInvocation
    implements java.lang.reflect.InvocationHandler {
  //~ Instance fields //////////////////////////////////////////////////////////

  private Object obj;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Class that makes use of reflection to create instances of the GIs using a
   * string.
   *
   * @param obj Object an object representative of a class.
   */
  private ClassInvocation(final Object obj) {
    this.obj = obj;
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Creates an instance of the given class (obj) using the default constructor.
   *
   * @param obj Object an object representative of a class.
   *
   * @return Object the new instance of the given class.
   */
  public static Object newInstance(final Object obj) {
    return java.lang.reflect.Proxy.newProxyInstance(
        obj.getClass().getClassLoader(),
        obj.getClass().getInterfaces(),
        new ClassInvocation(obj));
  }

  /**
   * Invokes the default constructor of the desired class.
   *
   * @param proxy Object
   * @param m Method
   * @param args Object[]
   * @return Object
   * @throws Throwable
   */
  public Object invoke(
      final Object proxy,
      final Method m,
      final Object[] args) throws Throwable {
    final Object result;

    try {
      result = m.invoke(obj, args);
    } catch (InvocationTargetException e) {
      throw e.getTargetException();
    } catch (Exception e) {
      throw new RuntimeException(
          new StringBuffer(
              "ClassInvocation.java - Unexpected invocation exception: ").
          append(
              e.getMessage()).toString());
    }

    return result;
  }
}
