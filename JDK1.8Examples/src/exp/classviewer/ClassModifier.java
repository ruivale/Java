package exp.classviewer;

import org.apache.bcel.*;
import org.apache.bcel.classfile.*;
import org.apache.bcel.generic.*;


public class ClassModifier implements Constants {
  private JavaClass clazz;
  private ClassGen classGen;
  private ConstantPoolGen cp;

  public ClassModifier(String clazz) {
    this.clazz = Repository.lookupClass(clazz);
    this.classGen = new ClassGen(this.clazz);
    this.cp = this.classGen.getConstantPool();
  }

  public static void main(String[] a) {
    String[] args = {"ClassViewer.class"};
    if(a != null && a.length > 0){
      args = a;
      if (args.length != 1) {
        throw new IllegalArgumentException("One and only one class at a time!");
      }
    }

    ClassModifier modifier = new ClassModifier(args[0]);
    modifier.start();
  }

  private void start() {
    if (this.clazz != null) {
      // print the methods BEFORE adding the new one
      Method[] methods = classGen.getJavaClass().getMethods();
      System.err.println("++++ Before adding new method ++++");

      for (int i = 0; i < methods.length; i++) {
        System.err.println(methods[i]);
      }

      InstructionList il = new InstructionList();
      classGen.addMethod(new MethodGen(ACC_PUBLIC | ACC_STATIC, Type.VOID,
          Type.NO_ARGS, new String[] {  }, "newMethod", clazz.getClassName(),
          il, cp).getMethod());

      // print the methods AFTER adding the new one
      methods = classGen.getJavaClass().getMethods();
      System.err.println("\n++++ After adding new method ++++");

      for (int i = 0; i < methods.length; i++) {
        System.err.println(methods[i]);
      }
    } else {
      throw new RuntimeException("Class file is null!");
    }
  }
}
