package exp.classpath.changinginruntime;


public class OutOfClassPath {

  public OutOfClassPath() {
    System.out.println("Yep! OutOfClasspath loaded by: " +
                       this.getClass().getClassLoader());
  }
}
