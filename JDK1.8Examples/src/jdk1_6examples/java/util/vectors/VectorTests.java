package jdk1_6examples.java.util.vectors;


import java.util.*;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */
public class VectorTests {
  public VectorTests() {
    Vector<String> v1 = new Vector<String> ();
    v1.add("v1");
    v1.add("v2");
    v1.add("v8");
    v1.add("v4");
    v1.add("v1");
    v1.add("v6");
    v1.add("v7");
    v1.add("v8");
    Vector<String> v2 = new Vector<String> ();
    v2.addAll(v1);
    Vector<String> resultA = new Vector<String> ();

    int i = -1;
    for (String string : v1) {
      i++;
      v2.set(i, null);

      if (!v2.contains(string)) {
        resultA.add(i, null);
      } else {
        resultA.add(i, string);
      }

      v2.set(i, string);
    }
    System.out.println(resultA);

  }

  public static void main(String[] args) {
    VectorTests vectortests = new VectorTests();
  }
}
