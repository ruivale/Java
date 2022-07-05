/*
 * Copyright 2011 Sergey Ushakov

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package exp.runtime.runprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Vector;


/**
 * <p>
 * A wrapper for an arbitrary Java command-line tool to work around the
 * problem of using Unicode command line arguments (e.g. file names, etc)
 * on certain JVM / OS combinations, see:</p>
 * <ul>
 * <li><a
 *   href="http://www.java.net/node/644569">Support for Unicode in Mustang
 * ?</a></li>
 * <li><a
 *   href="http://www.coderanch.com/t/279253/Streams/java/Unicode-cmd-parameters-main-args#1284172"
 * >Unicode: cmd parameters (main args); exec parameters; filenames</a></li>
 * <li><a
 *   href="http://www.coderanch.com/t/279253/Streams/java/Unicode-cmd-parameters-main-args#1284176"
 * >Unicode: cmd parameters (main args); exec parameters; filenames</a></li>
 * <li><a
 *   href="http://mail.openjdk.java.net/pipermail/core-libs-dev/2009-March/thread.html#1231"
 * >RFE 4519026: (process) Process should support Unicode on Win NT,
 * request for review</a></li>
 * </ul>
 * <p>
 * <p>
 * The <code>main()</code> method of this wrapper accepts two
 * arguments, the first one being a fully qualified name of the class to load
 * and invoke the <code>main()</code> method thereof, and the second one
 * being the name of the file containing the arguments for the target
 * <code>main()</code> method. The arguments in the file are expected to be
 * provided in UTF-16LE encoding, one argument per line. The file in UTF-16LE
 * encoding, in turn, may be crafted on Windows platform using
 * <code>cmd /u</code> call(s).</p>
 *
 * @author Sergey Ushakov, 2011
 */
public class UnicodeLauncher {

  /*........................*/
  protected static void reportCalledMainExcepion(String sTargetClassName,
                                                 Throwable e) {
    System.err.println("Exception calling the 'main()' method for '" + sTargetClassName + "' class: "
                           + e.getMessage());
  } // reportCalledMainExcepion

  /**
   * @param args command line arguments, the first one being a fully
   *             qualified name of the class to load and invoke the
   *             <code>main()</code> method thereof, and the second one
   *             containing the name of the file containing the
   *             arguments for the <code>main()</code> method
   */
  /*====*/
  public static void main(String[] args) {

    if (args.length != 2) {
      System.out.println("Usage:");
      System.out.println("  java [JVM arguments] " + "usn.lang.UnicodeLauncher class_name_to_launch name_of_file_with_args");
      System.exit(1);
    }


    String sTargetClass = args[0];
    Class<?> targetClass = null;

    try {
      targetClass = Class.forName(sTargetClass);

    } catch (ClassNotFoundException e) {
      System.err.println("Failure loading the class to launch : " + e.getMessage());
      System.exit(1);
    }


    File file = new File(args[1]);
    BufferedReader br = null;

    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-16LE"));

    } catch (FileNotFoundException e) {
      System.err.println("Arguments file not found: " + args[1]);
      System.exit(1);
    } catch (UnsupportedEncodingException e) {
      System.err.println("Incredible: 'UTF-16LE' encoding is " + "reported as not supported!");
      System.exit(1);
    }


    Vector<String> argsForTarget = new Vector<String>();

    for (; true;) {
      String s = null;

      try {
        s = br.readLine();

      } catch (IOException e) {
        System.err.println("Error reading arguments file: " + e.getMessage());
        try {
          br.close();
        } catch (IOException e1) {
          // not really much we can do here...
        }
        System.exit(1);
      }
      if (s == null) {
        break;
      }
      s = s.trim();
      if (s.length() > 0) {
        argsForTarget.add(s);
      }
    }


    // identify the target class' main() method
    Class<? extends String[]> classStringArray = (new String[0]).getClass();
    Method methodMain = null;

    try {
      methodMain = targetClass.getDeclaredMethod("main", new Class[]{classStringArray});

    } catch (SecurityException e) {
      System.err.println("Security exception while looking for the 'main()' method " + "of the '"+ sTargetClass + "' class: " + e.getMessage());
      System.exit(1);
    } catch (NoSuchMethodException e) {
      System.err.println("The 'main()' method not found for '" + sTargetClass + "' class: " + e.getMessage());
      System.exit(1);
    }


    // call the target class' main() method
    String[] asArgsForTarget = argsForTarget.toArray(new String[0]);
    Object[] aMethodArgsArray = new Object[]{asArgsForTarget};

    try {
      methodMain.invoke(null, aMethodArgsArray);
      
    } catch (IllegalArgumentException e) {
      reportCalledMainExcepion(sTargetClass, e);
      System.exit(1);
    } catch (IllegalAccessException e) {
      reportCalledMainExcepion(sTargetClass, e);
      System.exit(1);
    } catch (InvocationTargetException e) {
      reportCalledMainExcepion(sTargetClass, e);
      e.printStackTrace();
      System.exit(1);
    }
  } // main

}
