package jdk1_5examples.generics;

import java.util.*;

/**
 * <p>Title: JDK1.5 Examples</p>
 * <p>Description: Examples for the Java5. </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ??????????</p>
 * @author rUI vALE
 * @version 1.0
 */

public interface IDirectory <D extends IDirectory, F extends IFile>
    extends IEntry < D, F > {

  Collection < D > getSubDirectories();

  Collection < F > getFiles();

}
