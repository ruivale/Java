package exp.classviewer;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;


public class ClassViewer {
  private JavaClass clazz;

  public ClassViewer(String clazz) {
    this.clazz = Repository.lookupClass(clazz);
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      throw new IllegalArgumentException("One and only one class at a time!");
    }

    ClassViewer viewer = new ClassViewer(args[0]);
    viewer.start();
  }

  private void start() {
    if (this.clazz != null) {
      // first print the structure
      // of the class file
      System.err.println(clazz);

      // next print the methods
      Method[] methods = clazz.getMethods();

      for (int i = 0; i < methods.length; i++) {
        System.err.println(methods[i]);

        // now print the actual
        // byte code for each method
        Code code = methods[i].getCode();

        if (code != null) {
          System.err.println(code);
        }
      }
    } else {
      throw new RuntimeException("Class file is null!");
    }
  }
}
