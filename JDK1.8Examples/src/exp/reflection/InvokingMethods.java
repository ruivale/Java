package exp.reflection;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

import java.lang.reflect.*;

public class InvokingMethods {

  public static String append(String firstWord, String secondWord) {

    String result = null;
    try {
      Class c = Class.forName("java.lang.String");
      //      Class c = String.class;
      Class[] parameterTypes = new Class[] {
          String.class};
      Method concatMethod;
      Object[] arguments = new Object[] {
          secondWord};

      concatMethod = c.getMethod("concat", parameterTypes);
      result = (String) concatMethod.invoke(firstWord, arguments);

    } catch (ClassNotFoundException e) {
      System.out.println(e);
    } catch (NoSuchMethodException e) {
      System.out.println(e);
    } catch (IllegalAccessException e) {
      System.out.println(e);
    } catch (InvocationTargetException e) {
      System.out.println(e);
    }
    return result;
  }

  public static void main(String[] args) {
    String firstWord = "Hello ";
    String secondWord = "everybody.";
    String bothWords = append(firstWord, secondWord);
    System.out.println(bothWords);
  }

}
