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
 Changing Values of Fields
 Another use of reflection is to change the values of data fields in
 objects. The value of this is again derived from the dynamic nature of
 reflection, where a field can be looked up by name in an executing program
 and then have its value changed. This is illustrated by the following example:
 */
import java.lang.reflect.*;

public class Field2 {
  public double d;

  public static void main(String[] args) {
    try {
      Class cls = Class.forName("exp.reflection.Field2");
      Field fld = cls.getField("d");
      Field2 f2obj = new Field2();
      System.out.println("d = " + f2obj.d);
      fld.setDouble(f2obj, 12.34);
      System.out.println("d = " + f2obj.d);
    } catch (Throwable e) {
      System.err.println(e);
    }
  }

}

/*
   In this example, the d field has its value set to 12.34.
 */
