package jdk1_5examples.autoboxing;


import java.util.*;


/**
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */

// Prints a frequency table of the words on the command line
public class Frequency {
  public static void main(String[] args) {
    String[] a = {
        "fonix",
        "pra",
        "eta",
        "medinha",
        "carago",
        "carago",
        "não",
        "carago",
        "fonix",
        "ta"
    };

    Map<String, Integer> m = new TreeMap<String, Integer> ();
    for (String word : a) {
      Integer freq = m.get(word);
      m.put(word, (freq == null ? 1 : freq + 1));
    }
    System.out.println(m);
  }
}




