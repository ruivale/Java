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
 Using Arrays
 One final use of reflection is in creating and manipulating arrays.
 Arrays in the Java language are a specialized type of class, and an
 array reference can be assigned to an Object reference.

 To see how arrays work, consider the following example:
 */
import java.lang.reflect.*;

public class Array1 {

  public static void main(String[] args) {
    try {
      Class cls = Class.forName("java.lang.String");
      Object arr = Array.newInstance(cls, 10);
      Array.set(arr, 5, "this is a test");
      String s = (String) Array.get(arr, 5);
      System.out.println(s);

      System.out.println("\n\nStarting Array2:\n");

      Array2 array2 = new Array2();

    } catch (Throwable e) {
      System.err.println(e);
    }
  }
}

/*
 This example creates a 10-long array of Strings, and then sets location
 5 in the array to a string value. The value is retrieved and displayed.

 A more complex manipulation of arrays is illustrated by the following
 code:
 */
class Array2 {

  public Array2() {
    int dims[] = new int[] {
        5, 10, 15};
    Object arr = Array.newInstance(Integer.TYPE, dims);

    Object arrobj = Array.get(arr, 3);
    Class cls = arrobj.getClass().getComponentType();
    System.out.println(cls);
    arrobj = Array.get(arrobj, 5);
    Array.setInt(arrobj, 10, 37);

    int arrcast[][][] = (int[][][]) arr;
    System.out.println(arrcast[3][5][10]);
  }

}
