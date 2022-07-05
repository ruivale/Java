package exp.reflection.proxy;

import java.io.*;

public class BarImpl
    implements Bar {

  public void goodbye(String str, int i) {
    System.out.println("   in pub.bar.Bar.goodbye");
  }

  public void hello(int i, String s) {
    System.out.println("   in pub.bar.Bar.hello");
  }

}
