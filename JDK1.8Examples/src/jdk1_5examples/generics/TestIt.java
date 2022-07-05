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

public class TestIt {

  public static void main(String[] args) {

    Directory< Directory, File, AbstractEntry> root
      = new Directory< Directory, File, AbstractEntry>("Blah", null);
    Directory< Directory, File, AbstractEntry> childA
      = new Directory< Directory, File, AbstractEntry>("BlahChild", root);
    File< Directory, File> childB
      = new File< Directory, File>("BlahChild2", root);

    Collection< AbstractEntry> entries = new HashSet< AbstractEntry>();

    entries.add(childA);
    entries.add(childB);
    root.setSubEntries(entries);

    System.out.println(root.getName());
    for (File f : root.getFiles()) {
      System.out.println("File: " + f.getName());
    }

    for (Directory d : root.getSubDirectories()) {
      System.out.println("Directory: " + d.getName());
    }
  }

}
