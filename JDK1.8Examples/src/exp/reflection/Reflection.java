package exp.reflection;

//import exp.reflection.method.*;
//
//import java.lang.reflect.*;
////import com.visiowave.display.xvwdisplaylib.impl.IVWActiveXComponentImpl;
//import com.jniwrapper.win32.ole.OleFunctions;
//import com.jniwrapper.DefaultLibraryLoader;
//import com.jniwrapper.win32.automation.OleContainer;
//import com.jniwrapper.IntegerParameter;
//import com.jniwrapper.*;

/**
 * Enumera os métodos de uma det. classe. Neste caso da javax.swing.JFrame
 */
public class Reflection {
//  static {
//    // Adding bin dir to the lib path ...
//    DefaultLibraryLoader.getInstance().addPath(
//        "D:/JBProjects/JavaWinComm/bin");
//
//    // initialize OLE ...
//    OleFunctions.oleInitialize();
//
//  }
//
//  private static String strClass =
//      "com.visiowave.display.xvwdisplaylib.impl.IVWActiveXComponentImpl";
//  //private static String strClass = "exp.reflection.method.Auxiliar";
//
//
//
//  public static void getAllMethods() {
//    try {
//      Class c = Class.forName(strClass);
//      Method m[] = c.getDeclaredMethods();
//      for (int i = 0; i < m.length; i++) {
//        System.out.println("\n" + m[i].toString());
//        Class[] classes = m[i].getParameterTypes();
//        final int intNbrOfParam = classes.length;
//        for (int j = 0; j < intNbrOfParam; j++) {
//          System.out.println("\tParam:" + classes[j].getName());
//        }
//        System.out.println("\tReturn type:" + m[i].getReturnType());
//      }
//    } catch (Throwable e) {
//      System.err.println(e);
//    }
//  }
//
//  public static Method getMethod(String sClass, String method, Class[] params) {
//
//    Class[] paramClasses = new Class[params.length];
//
//    for (int i = 0; i < params.length; i++) {
//      paramClasses[i] = java.lang.Object.class;
//    }
//
//    try {
//      Class c = Class.forName(sClass);
//      Method m = c.getMethod(method, params);
//      //Method m = c.getMethod(method, paramClasses);
//
//      if (m != null) {
//        System.out.println("\n" + m.getName());
//        Class[] classes = m.getParameterTypes();
//        final int intNbrOfParam = classes.length;
//        for (int j = 0; j < intNbrOfParam; j++) {
//          System.out.println("\tParam:" + classes[j].getName());
//        }
//        System.out.println("\tReturn type:" + m.getReturnType());
//
//      }
//
//      return m;
//
//    } catch (Exception ex) {
//      ex.printStackTrace();
//    }
//    return null;
//  }
//
//  /**
//   *
//   * @param args String[]
//   *
//  public static void main(String[] args) {
//    //Reflection.getAllMethods();
//
//    Method m = Reflection.getMethod(
//        strClass,
//        "getSourceProtocol",
//        new Class[] {
//        com.jniwrapper.Int16.class,
//        com.jniwrapper.Int32.class
//    });
//
//    if (m != null) {
//      try {
//
//        OleContainer oleContainer = new OleContainer();
//        oleContainer.createObject("XVWDisplay.VWActiveXComponent.1");
//        IVWActiveXComponentImpl ivwaxc = new IVWActiveXComponentImpl(
//            oleContainer.getOleObject());
//
//        //Class[] cs = m.getParameterTypes();
//
//        Class cInt16 = Class.forName("com.jniwrapper.Int16");
//        cInt16.newInstance();
//
//        com.jniwrapper.Int16 i1 = new com.jniwrapper.Int16();
//        i1.setValue(1);
//        com.jniwrapper.Int32 i2 = new com.jniwrapper.Int32();
//        i1.setValue(2);
//
//        Object obj = m.invoke(
//            ivwaxc,
//            new Object[] {
//            buildParameterClass("com.jniwrapper.Int16", new String[] {"1"}),
//            buildParameterClass("com.jniwrapper.Int32", new String[] {"2"})
//        });
//
//        if (obj != null) {
//          System.out.println("obj=" + obj.toString() + ".");
//        }
//      } catch (Exception ex) {
//        ex.printStackTrace();
//      }
//    }
//  }
//
//  /**
//   *
//   * @param strClass String
//   * @param strParams String[]
//   * @return Object
//   *
//  public static Object buildParameterClass(
//      final String strClass,
//      final String[] strParams) {
//
//    Object obj = null;
//    if (strClass != null && !strClass.equals("") &&
//        strParams != null && strParams.length > 0) {
//
//      if (strClass.equals("com.jniwrapper.Int16")) {
//        com.jniwrapper.Int16 i16 = new com.jniwrapper.Int16(Short.parseShort(strParams[0]));
//        obj = i16;
//
//      } else if (strClass.equals("com.jniwrapper.Int32")) {
//        com.jniwrapper.Int32 i32 = new com.jniwrapper.Int32(Integer.parseInt(strParams[0]));
//        obj = i32;
//
//      } else if (strClass.equals("com.jniwrapper.SingleFloat")) {
//        com.jniwrapper.SingleFloat sf = new com.jniwrapper.SingleFloat(Float.parseFloat(strParams[0]));
//        obj = sf;
//
//      } else if (strClass.equals("com.jniwrapper.Pointer.Const")) {
//
//      } else if (strClass.equals("com.jniwrapper.Pointer")) {
//
//      } else if (strClass.equals("com.jniwrapper.DoubleFloat")) {
//
//      } else if (strClass.equals("com.jniwrapper.win32.automation.types.BStr")) {
//        com.jniwrapper.win32.automation.types.BStr bstr =
//            new com.jniwrapper.win32.automation.types.BStr(strParams[0]);
//        obj = bstr;
//
//      } else if (strClass.equals("com.visiowave.display.stdole.OLE_COLOR")) {
//        com.visiowave.display.stdole.OLE_COLOR oc =
//            (com.visiowave.display.stdole.OLE_COLOR)new com.jniwrapper.UInt32(
//          Long.parseLong("1276276372"));
//
//      } else if (strClass.equals("com.jniwrapper.win32.automation.types.VariantBool")) {
//        com.jniwrapper.win32.automation.types.VariantBool vb =
//            new com.jniwrapper.win32.automation.types.VariantBool(
//                strParams[0].equals("true") || strParams[0].equals("1"));
//        obj = vb;
//      }
//    }
//
//    return obj;
//  }
///**/

}
