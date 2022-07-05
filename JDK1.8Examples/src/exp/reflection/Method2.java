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
Invoking Methods by Name
So far the examples that have been presented all relate to obtaining
class information. But it's also possible to use reflection in other
ways, for example to invoke a method of a specified name.

To see how this works, consider the following example:
*/

   import java.lang.reflect.*;

   public class Method2 {
      public int add(int a, int b)
      {
         return a + b;
      }

      public static void main(String args[])
      {
         try {
            Class cls = Class.forName("exp.reflection.Method2");
            Class partypes[] = new Class[2];
            partypes[0] = Integer.TYPE;
            partypes[1] = Integer.TYPE;
            Method meth = cls.getMethod("add", partypes);
            Method2 methobj = new Method2();
            Object arglist[] = new Object[2];
            arglist[0] = new Integer(37);
            arglist[1] = new Integer(47);
            Object retobj = meth.invoke(methobj, arglist);
            Integer retval = (Integer)retobj;
            System.out.println(retval.intValue());
         }
         catch (Throwable e) {
            System.err.println(e);
         }
      }
   }
/*
Suppose that a program wants to invoke the add method, but doesn't
know this until execution time. That is, the name of the method is
specified during execution (this might be done by a JavaBeans
development environment, for example). The above program shows a way of
doing this.

getMethod is used to find a method in the class that has two
integer parameter types and that has the appropriate name. Once this method
has been found and captured into a Method object, it is invoked
upon an object instance of the appropriate type. To invoke a method, a
parameter list must be constructed, with the fundamental integer values
37 and 47 wrapped in Integer objects. The return value (84)
is also wrapped in an Integer object.
*/