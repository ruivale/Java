package exp.files;

import java.net.URL;

public class AbsoluteFilePath {

  public static String getFilePath(String className) {
    if (!className.startsWith("/")) {
      className = "/" + className;
    }

    className = className.replace('.', '/');
    className = className + ".class";

    URL classUrl = new AbsoluteFilePath().getClass().getResource(className);
    if (classUrl != null) {
      String temp = classUrl.getFile();
      if (temp.startsWith("file:")) {
        return temp.substring(5);
      }

      return temp;
    } else {
      return "\nClass '" + className + "' not found in \n'" +
          System.getProperty("java.class.path") + "'";
    }
  }


  public static void main(String a[]){
    String s = "javax.swing.JFrame";

    System.out.println("Absolute path "+s+"="+
                       AbsoluteFilePath.getFilePath(s)+".");
  }

}
