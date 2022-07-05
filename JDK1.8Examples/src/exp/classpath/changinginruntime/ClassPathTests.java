package exp.classpath.changinginruntime;


import java.net.*;
import java.io.*;
import java.lang.reflect.Method;


public class ClassPathTests {

  private static final Class[] parameters = new Class[] {URL.class};

  public static void addFile(String s) throws IOException {
    File f = new File(s);
    addFile(f);
  } // end method

  public static void addFile(File f) throws IOException {
    addURL(f.toURL());
  } // end method

  public static void addURL(URL u) throws IOException {
    URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    Class sysclass = URLClassLoader.class;
    try {
      Method method = sysclass.getDeclaredMethod("addURL", parameters);
      method.setAccessible(true);
      method.invoke(sysloader, new Object[] {u});
    } catch (Throwable t) {
      t.printStackTrace();
      throw new IOException(
          "Error, could not add URL to system classloader");
    } // end try catch
  } // end method

  public static void main(String[] args) throws Exception {
    System.out.println("Hello.");
    System.out.println("\nBefore ");
    System.out.println("***********Test**************");
    try {
      Class.forName("com.test.Test");
      System.out.println(" Test Class Found ....");
    } catch (ClassNotFoundException e) {
      System.out.println(" Test Class Not Found ....");
    }
    System.out.println("\n\n\n");
    System.out.println("After");

    try {
      addFile("D:\\ClassPathTest\\lib\\test.jar");
      Class.forName("com.test.Test");
      System.out.println(" Test Class Found ....");
    } catch (ClassNotFoundException e) {
      System.out.println(" Test Class Found ....");
    }
  }
}
