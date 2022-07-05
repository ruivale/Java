package exp.classpath.changinginruntime;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * Provide an addable ClassLoader.
 * @author Andrea Zito
 *
 */
public class AddableClassLoader
    extends URLClassLoader {

  /**
   * Setup the classloader using all the entry specified in the classpath
   * property.
   */
  private AddableClassLoader() {
    super(new URL[0], null);
    String classpath = System.getProperty("java.class.path");
    String separator = System.getProperty("path.separator", ":");
    String urlString[] = classpath.split(separator);
    for (int i = 0; i < urlString.length; i++) {
      try {
        /*
         * If the path doesn't point to a jar, I assume it's a directory.
         * If the path doesn't end with / and it's not a jar, add an / at
         * the end, couse URLClassLoader cosider it a directory only if it ends
         * with /.
         */
        if (!urlString[i].endsWith(".jar") && !urlString[i].endsWith("/")) {
          urlString[i] = urlString[i] + "/";
        }
        super.addURL(new URL("file://" + urlString[i]));
      } catch (MalformedURLException e) {
        System.err.println(urlString[i]);
      }
    }
  }

  /**
   * Create an instance of AddableClassLoader
   *
   * @return Instance of AddableClassloader
   * @throws AddableClassLoaderException
   */
  public static ClassLoader newInstance() throws Exception {

    try {
      /*
       * Use the SystemClassLoader to load an instance of MyClassLoader. This is needed
       * couse 2 class are equal only if they are equal from a bytecode point of view and
       * if they have the same class loader.
       *
       * Thus I need this to make the upcast in addURL(URL,ClassLoader);
       */
      Class loaderClass = ClassLoader.getSystemClassLoader().loadClass(
          AddableClassLoader.class.getName());
      ClassLoader clInstance = (ClassLoader) (loaderClass.newInstance());
      return clInstance;

    } catch (ClassNotFoundException e) {
      throw new Exception(e.getMessage());
    } catch (InstantiationException e) {
      throw new Exception(e.getMessage());
    } catch (IllegalAccessException e) {
      throw new Exception(e.getMessage());
    }
  }

  /**
   * Add the URL to the classpath
   */
  public void addURL(URL newURL) {
    super.addURL(newURL);
  }

  /**
   * Add the specified URL to the AddableClassLoader instance specified by classLoader.
   * If classLoader does't point to an instance od AddableClassLoader then raise an
   * IllegalArgumentException.
   *
   * @param newURL URL to be added
   * @param classLoader Instance of AddableClassLoader to consider
   * @throws IllegalArgumentException classLoader isn't an instance of AddableClassLoader
   */
  public static void addURL(URL newURL, ClassLoader classLoader) throws
      Exception {
    Class cl = classLoader.getClass();
    Class myClassLoader = null;
    try {
      /*
       * Load the class of MyClassLoader from the same classloader as the one used in getInstance.
       * This is necessary. Otherwise I'll get ClassCastException later
       */
      myClassLoader = ClassLoader.getSystemClassLoader().loadClass(
          AddableClassLoader.class.getName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    /*
     * Check if the ClassLoader passed is an instance of MyClassLoader
     */
    if (!myClassLoader.isAssignableFrom(cl)) {
      throw new Exception(classLoader +
          " is not an instance of MyClassLoader");
    }
    try {

      /*
       * Here we are... now I call the public method addURL of MyClassLoader which pass
       * the URL to the protected method addURL of URLClassLoader.
       * Reflection is required. If I use:
       *
       *   ((AddableClassLoader)clasLoader).addURL(newURL);
       *
       * it will be raised a ClassCastException.
       */
      Method addURL = cl.getMethod("addURL", new Class[] {URL.class});
      addURL.invoke(classLoader, new Object[] {newURL});

    } catch (SecurityException e) {
      //This should not happen, couse I know cl is an instance of AddableClassLoader
    } catch (NoSuchMethodException e) {
      //This should not happen, couse I know cl is an instance of AddableClassLoader
      throw new Exception(e.getMessage());
    } catch (IllegalAccessException e) {
      throw new Exception(e.getMessage());
    } catch (InvocationTargetException e) {
      throw new Exception(e.getMessage());
    }

  }
}
