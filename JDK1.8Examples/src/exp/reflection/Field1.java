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
 Finding Out About Class Fields
 It's also possible to find out which data fields are defined in a class.
 To do this, the following code can be used:
 */

import java.lang.reflect.*;

public class Field1 {
  private double d;
  public static final int i = 37;
  String s = "testing";

  public static void main(String[] args) {
    try {
      Class cls = Class.forName("exp.reflection.Field1");

      Field fieldlist[]
          = cls.getDeclaredFields();
      for (int i
           = 0; i < fieldlist.length; i++) {
        Field fld = fieldlist[i];
        System.out.println("name = " + fld.getName());
        System.out.println("decl class = " +
                           fld.getDeclaringClass());
        System.out.println("type = " + fld.getType());
        int mod = fld.getModifiers();
        System.out.println("modifiers = " +
                           Modifier.toString(mod));
        System.out.println("-----");
      }
    } catch (Throwable e) {
      System.err.println(e);
    }
  }

}

/*
 This example is similar to the previous ones. One new feature is the use of
 Modifier. This is a reflection class that represents the
 modifiers found on a field member, for example "private int".
 The modifiers themselves are represented by an integer, and
 Modifier.toString is used to return a string representation in the "official" declaration order (such as "static" before
 "final").
 */
