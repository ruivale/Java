package jdk1_5examples.generics;

import java.util.*;

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
public class Directory< D extends IDirectory, F extends IFile, E extends IEntry>
  extends AbstractEntry< D, F>
  implements IDirectory< D, F> {

  private Collection< E> subEntries;

  /**
   *
   * @param name String
   * @param parent D
   */
  public Directory(String name, D parent) {
    super(name, parent);

  }

  /**
   *
   * @param entries Collection
   */
  public void setSubEntries(Collection< E> entries) {
    subEntries = entries;

    if (subEntries == null) {
      subEntries = new HashSet< E>();
    }
  }

  public Collection< F> getFiles() {
    Collection< F> files = new HashSet< F>();
    for (IEntry entry : subEntries) {
      if (entry instanceof IFile) {
        F fileEntry = (F) entry;
        files.add(fileEntry);
      }
    }
    return files;
  }

  public Collection< D> getSubDirectories() {
    Collection< D> directories = new HashSet< D>();
    for (IEntry entry : subEntries) {
      if (entry instanceof IDirectory) {
        D directoryEntry = (D) entry;
        directories.add(directoryEntry);
      }
    }
    return directories;
  }

}
