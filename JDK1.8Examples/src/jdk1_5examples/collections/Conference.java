package jdk1_5examples.collections;

import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;


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
public class Conference {
  private Collection delegates = new ArrayList();
  public void add(String ...names) {
    Collections.addAll(delegates, names);
  }

  public void removeFirst() {
    delegates.remove(0);
  }

  public String toString() {
    return "Conference " + delegates;
  }

  public static void main(String[] args) {
    Conference sun_tech_days = new Conference();
    sun_tech_days.add("Herman", "Bobby", "Robert");
    sun_tech_days.removeFirst();
    System.out.println(sun_tech_days);
  }
}
