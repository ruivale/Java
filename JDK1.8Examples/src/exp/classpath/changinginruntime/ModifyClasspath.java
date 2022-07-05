package exp.classpath.changinginruntime;


import java.net.MalformedURLException;
import java.net.URL;



/**
 * Example application that use a custom classloader
 * @author Andrea Zito
 *
 */
public class ModifyClasspath {

  /**
   * Initialize app
   * @param args
   */
  public static void main(String[] args) {
    Class app;
    try {
      /*
       * Get a new instance of AddableClassLoader.
       */
      ClassLoader cl = AddableClassLoader.newInstance();

      /*
       * Reaload the class of the Application. This way it
       * will use cl as classloader.
       */
      app = cl.loadClass(ModifyClasspath.class.getName());
      Thread.currentThread().setContextClassLoader(cl);

      /*
       * Add jar to the classloader. This can be done whenever you want before the actual
       * class loading.
       */
      AddableClassLoader.addURL(new URL("file://" +
                                        System.getProperty("user.dir") +
          "/test/classloading/out_of_classpath.jar"), cl);

      /*
       * At this time is not jet possible to use Class.forName(String) to load class, couse this
       * method use the classloader of the current class which right now is the default one.
       * However you can use Class.forName(String, booloean, Classloader) if you need to load some
       * class now.
       */

      //Other initialization code here...

      /*
       * I ave to use reflection to instantiate ModifyClasspathReflection if I want it to use
       * cl as Classloader.
       *
       * If I instantiate the class this way:
       *
       *  new ModifyClasspathReflection();
       *
       * then the classloader would be the default one, and thus I have no
       * way to modify the classpath of the classloader
       */
      app.newInstance();

      /*
       * Handle Exceptions
       */
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Actual app code
   */
  public ModifyClasspath() {
    System.out.println("Starting application...");
    try {
      Class out = Class.forName("test.out.of.classpath.OutOfClassPath");
      System.err.println("Loading of OutOfClassPath succeded: " +
                         ModifyClasspath.class.getClassLoader());
      System.out.print("\nCreating a new instance of OutOfClassPath:\n\t");
      out.newInstance();
    } catch (ClassNotFoundException e) {
      System.err.println("Loading of OutOfClassPath failed: " +
                         ModifyClasspath.class.getClassLoader());
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

}
