package jdk1_6examples.javax.tools.compiling;


import java.io.IOException;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;


public class SecondCompile {
  public static void main(String args[]) throws IOException {

    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    DiagnosticCollector<JavaFileObject>
        diagnostics = new DiagnosticCollector<JavaFileObject> ();
    StandardJavaFileManager fileManager = compiler.getStandardFileManager(
        diagnostics, null, null);
    Iterable<? extends JavaFileObject> compilationUnits = fileManager.
        getJavaFileObjectsFromStrings(
            Arrays.asList("src/jdk1_6examples/javax/tools/compiling/YourFile.java"));
    Iterable<String>
        options = Arrays.asList("-d", "classes", "-sourcepath", "src");
    JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
        diagnostics, options, null, compilationUnits);

    boolean success = task.call();
    for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
      System.out.println(diagnostic.getCode());
      System.out.println(diagnostic.getKind());
      System.out.println(diagnostic.getPosition());
      System.out.println(diagnostic.getStartPosition());
      System.out.println(diagnostic.getEndPosition());
      System.out.println(diagnostic.getSource());
      System.out.println(diagnostic.getMessage(null));
    }
    fileManager.close();
    System.out.println("Success: " + success);
  }
}
