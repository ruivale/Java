package jdk1_5examples.generics;


/**
 * <p>Title: JDK1.5 Examples</p>
 * <p>Description: Examples for the Java5. </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ??????????</p>
 * @author rUI vALE
 * @version 1.0
 */

public class File < D extends IDirectory, F extends IFile >
    extends AbstractEntry < D, F >
    implements IFile < D, F > {
  public File(String name, D parent) {
    super(name, parent);
  }
}
