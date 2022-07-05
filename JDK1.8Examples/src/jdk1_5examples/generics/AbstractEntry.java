package jdk1_5examples.generics;

/**
 * <p>
 * Title: JDK1.5 Examples</p>
 * <p>
 * Description: Examples for the Java5. </p>
 * <p>
 * Copyright: Copyright (c) 2004</p>
 * <p>
 * Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public class AbstractEntry< D extends IDirectory, F extends IFile>
  implements IEntry< D, F> {

  private D parent;
  private String name;

  public AbstractEntry(String name, D parent) {
    this.name = name;
    this.parent = parent;
  }

  public String getName() {
    return name;
  }

  public D getParent() {
    return parent;
  }

}
