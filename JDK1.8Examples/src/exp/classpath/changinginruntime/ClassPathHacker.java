package exp.classpath.changinginruntime;


import java.io.*;
import java.net.*;


public class ClassPathHacker {

  public static void addFile(String s) throws IOException {
    File f = new File(s);
    addFile(f);
  } //end method

  public static void addFile(File f) throws IOException {
    addURL(f.toURL());
  } //end method

  public static void addURL(URL u) throws IOException {
    URL urls[] = new URL[] {u};
    ClassLoader aCL = Thread.currentThread().getContextClassLoader();
    URLClassLoader aUrlCL = new URLClassLoader(urls, aCL);

    Thread.currentThread().setContextClassLoader(aUrlCL);
  }
}
