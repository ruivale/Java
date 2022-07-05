
package exp.interfaces;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;


public class InterfaceSampleRun {


  public static void main(final String[] args){


    try {
      final Class<?> myClass = Class.forName("exp.interfaces.InterfaceSampleImpl");
      final Object obj = myClass.newInstance();

      // obtaining all the interfaces directly implemented by the class in obj...
      final Class<?>[] clazzInterfacesDirect = obj.getClass().getInterfaces();

      // copying those direct implemented interfaces to a new array...
      Class<?>[] clazzInts = Arrays.copyOf(clazzInterfacesDirect, clazzInterfacesDirect.length + 1);

      // which will hold also a reference to the GIInterface, the one ALL GIs must implement and that
      // GraphicInterfaceApplication class implements, class that ALL GIs init classes SHOULD extend.
      clazzInts[clazzInterfacesDirect.length] = InterfaceSample.class;

      //<Class<?>>
      Object[] objs = Arrays.asList(clazzInts).stream().distinct().toArray();
      //Class<?>[] dest = new Class<?>[objs.length];
      System.arraycopy(objs, 0, clazzInts, 0, objs.length);


      final Object _obj =
        Proxy.newProxyInstance(
            obj.getClass().getClassLoader(),
            clazzInts,
            new InvocationHandler() {
              public Object invoke(Object proxy,Method method,Object[] args) throws Throwable {
                throw new UnsupportedOperationException("Not supported yet.");
              }
        });
    } catch (Exception exc) {
      exc.printStackTrace();
    }




//    final InterfaceSampleImpl clazz = new InterfaceSampleImpl();
//
//    if(clazz instanceof InterfaceSample){
//      final InterfaceSample is = (InterfaceSample)clazz;
//      System.out.println("InterfaceSampleRun: clazz instanceof InterfaceSample");
//
//    } else {
//      System.out.println("InterfaceSampleRun: clazz IS NOT AN instanceof InterfaceSample");
//    }
//
//
//    try {
//      final Class<?> myClass = Class.forName("exp.interfaces.InterfaceSampleImpl");
//
//      InterfaceSample ie = (InterfaceSample) (myClass.newInstance());
//
//      System.out.println("InterfaceSampleRun. Using REFLECTION: clazz instanceof InterfaceSample");
//
//    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//      System.err.println(e);
//    }













//    final InterfaceSampleImpl clazz = new InterfaceSampleImpl();
//
//    if(clazz instanceof InterfaceSample){
//      final InterfaceSample is = (InterfaceSample)clazz;
//      System.out.println("InterfaceSampleRun: clazz instanceof InterfaceSample");
//
//    } else {
//      System.out.println("InterfaceSampleRun: clazz IS NOT AN instanceof InterfaceSample");
//    }
//
//
//    try {
//      final Class<?> myClass = Class.forName("exp.interfaces.InterfaceSampleImpl");
//
//      InterfaceSample ie = (InterfaceSample) (myClass.newInstance());
//
//      System.out.println("InterfaceSampleRun. Using REFLECTION: clazz instanceof InterfaceSample");
//
//    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//      System.err.println(e);
//    }
  }
}
