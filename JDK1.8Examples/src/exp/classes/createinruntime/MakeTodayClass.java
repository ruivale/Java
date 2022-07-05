package exp.classes.createinruntime;

import java.io.*;

import java.lang.reflect.*;

import java.util.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class MakeTodayClass {
  Date today = new Date();
  String todayMillis = Long.toString(today.getTime());
  String todayClass = "z_" + todayMillis;
  String todaySource = todayClass + ".java";

  public static void main(String[] args) {
    MakeTodayClass mtc = new MakeTodayClass();
    mtc.createIt();

    if (mtc.compileIt()) {
      System.out.println("Running " + mtc.todayClass + ":\n\n");
      mtc.runIt();
    } else {
      System.out.println(mtc.todaySource + " is bad.");
    }
  }

  public void createIt() {
    try {
      FileWriter aWriter = new FileWriter(todaySource, true);
      aWriter.write("public class " + todayClass + "{");
      aWriter.write(" public void doit() {");
      aWriter.write(" System.out.println(\"" + todayMillis + "\");");
      aWriter.write(" }}\n");
      aWriter.flush();
      aWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean compileIt() {
    String[] source = { new String(todaySource) };
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    //new sun.tools.javac.Main(baos, source[0]).compile(source);
    //return (baos.toString().indexOf("error") == -1);

    // if using JDK >= 1.3 then use
    return false;//(com.sun.tools.javac.Main.compile(source) == 0);
  }

  public void runIt() {
    try {
      Class[] params = {  };
      Object[] paramsObj = {  };
      Class thisClass = Class.forName(todayClass);
      Object iClass = thisClass.newInstance();
      Method thisMethod = thisClass.getDeclaredMethod("doit", params);
      thisMethod.invoke(iClass, paramsObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
