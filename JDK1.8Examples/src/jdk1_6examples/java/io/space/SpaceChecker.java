package jdk1_6examples.java.io.space;

import java.io.File;

public class SpaceChecker {
  public static void main(String[] args) {
    File[] roots = File.listRoots();

    for (int i = 0; i < roots.length; i++) {
      System.out.println(roots[i]);
      System.out.println("Total space = " + roots[i].getTotalSpace());
      System.out.println("Free space = " + roots[i].getFreeSpace());
      System.out.println("Usable space = " + roots[i].getUsableSpace());
      System.out.println();
    }
  }
}
