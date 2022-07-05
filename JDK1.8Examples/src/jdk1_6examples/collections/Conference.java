package jdk1_6examples.collections;

import java.util.*;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
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
