package exp.methods.findmethods;

import java.lang.reflect.Method;
import java.lang.reflect.Array;
import com.ent.corba.EventService.Property;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestFindingClassMethods {

  static final String className = "exp.methods.findmethods.FindClassMethods";


  public static void main(final String[] args) {
    //deliverEvent2();
    //deliverEvent();
    displayEvent();
  }

  private static void deliverEvent2(){
    try {

      Object clazz = Class.forName(className).newInstance();

      long[] longs = new long[] {2,4,5};

      final Class partypes[] = new Class[2];
      partypes[0] = Class.forName("[Lcom.ent.corba.EventService.Property;");
      partypes[1] = longs.getClass();//Class.forName("[Ljava.lang.Long;");

      final Method method = Class.forName(className).getMethod("deliverEvent", partypes);

      Property p1 = new Property();
      p1.key = "key 1";
      p1.value = "value 1";
      Property p2 = new Property();
      p2.key = "key 2";
      p2.value = "value 2";

      final Object params[] = new Object[2];
      params[0] = new Property[]{p1, p2};
      params[1] = longs;//new Long[]{new Long(12233445), new Long(1)};

      final boolean mustBeDisplay =
          ((Boolean) method.invoke(clazz, params)).booleanValue();

      System.out.println("deliverEvent - mustBeDisplay=" + mustBeDisplay);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static void deliverEvent(){
    try {
      final String className = "exp.methods.findmethods.FindClassMethods";


      final Class partypes[] = new Class[1];
      partypes[0] = Class.forName("[Lcom.ent.corba.EventService.Property;");

      final Method method =
          Class.forName(className).getMethod("deliverEvent", partypes);

      Property p1 = new Property();
      p1.key = "key 1";
      p1.value = "value 1";
      Property p2 = new Property();
      p2.key = "key 2";
      p2.value = "value 2";

      final Object params[] = new Object[1];
      params[0] = new Property[]{p1, p2};

      final boolean mustBeDisplay =
          ((Boolean) method.invoke(null, params)).booleanValue();
          //((Boolean) method.invoke(Class.forName(className).newInstance(), params)).booleanValue();

      System.out.println("deliverEvent - mustBeDisplay=" + mustBeDisplay);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static void displayEvent(){
    try {
      final String className = "exp.methods.findmethods.FindClassMethods";

      final long[] longs = {1234, 1235, 1236};
      final byte[] bytes = {1, 2, 3};

      final Class partypes[] = new Class[3];
      partypes[0] = Property[].class;
      partypes[1] = longs.getClass();
      partypes[2] = bytes.getClass();

      final Method method =
          Class.forName(className).getMethod("displayEvent", partypes);

      Property p1 = new Property();
      p1.key = "key 1";
      p1.value = "value 1";
      Property p2 = new Property();
      p2.key = "key 2";
      p2.value = "value 2";


      final Object params[] = new Object[3];
      params[0] = new Property[]{p1, p2};
      params[1] = longs;
      params[2] = bytes;

      final boolean mustBeDisplay =
          ((Boolean) method.invoke(Class.forName(className).newInstance(),
                                   params)).booleanValue();

      System.out.println("displayEvent - mustBeDisplay=" + mustBeDisplay);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
