package exp.reflection.proxy;

import java.lang.reflect.*;

public class Foo {

  public static void main(String[] args) {

    try {
      Class myClass = Class.forName("com.ent.stv.STV");
//      com.ent.PInt.GIs.GIInterface myGI =
//          (com.ent.PInt.GIs.GIInterface) com.ent.PInt.GIs.GIClassInvocation.
//          newInstance(myClass.newInstance());
//      myGI.initGI("STRING");

    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }

    Bar bar = (Bar) TraceProxy.newInstance(new BarImpl());
    bar.hello(2001, "xxx");
    bar.goodbye("yyy", 2002);

  }

}
