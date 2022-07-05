package exp.reflection.constructors;


import java.lang.reflect.Constructor;


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
public class CreateInstanceThroughConstructor {

  /**
   *
   * @param i int
   * @param s1 String
   * @param s2 String
   */
  public CreateInstanceThroughConstructor(int i, String s1, String s2) {
    System.out.println("i=" + i + ", s1=" + s1 + ", s2=" + s2 + ".");
  }


  public static void main(String[] a) {
    try {
      // Class invocation reflection
      final Class swCodecClass = Class.forName(
          "exp.reflection.constructors.CreateInstanceThroughConstructor");

      final Object[] objParams = {
                                 new Integer(3232),
                                 "exp.reflection.constructors.CreateInstanceThroughConstructor",
                                 "SWCodec"};

      // Obtaining the constructor with this parameters type ...
      final Class partypes[] = new Class[3];
      partypes[0] = Integer.TYPE;
      partypes[1] = String.class;
      partypes[2] = String.class;
      final Constructor constructor = swCodecClass.getConstructor(partypes);

System.out.println("constructor=" + constructor.toString() + ".");

      // Creating a new instance of the SWCodecInt implementation type
      constructor.newInstance(objParams);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
