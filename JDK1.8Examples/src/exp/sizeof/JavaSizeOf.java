package exp.sizeof;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JavaSizeOf {
  public JavaSizeOf() {
  }

  public static void main(String[] args) {
    String[] s = {
        //"java.awt.Panel",
        "javax.swing.JFrame",
        //"javax.swing.JDialog",
        //"javax.swing.JPanel",
        //"javax.swing.JButton",
        //"java.awt.Frame",
        //"java.awt.Dialog",
        //"java.awt.Button",
    };
    Object obj;
    Class clazz;
    Runtime runtime = Runtime.getRuntime();
    long start, end;
    for (int i = 0; i < s.length; i++) {
      try {
        runtime.gc();
        start = runtime.freeMemory();
        clazz = Class.forName(s[i]);
        obj = clazz.newInstance();
        end = runtime.freeMemory();
        System.out.println("That took " + (start - end) + " bytes. (" + s[i] +
                           ")");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
