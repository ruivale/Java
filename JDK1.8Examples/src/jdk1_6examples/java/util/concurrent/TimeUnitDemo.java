package jdk1_6examples.java.util.concurrent;

import java.util.concurrent.TimeUnit;

public class TimeUnitDemo {
  public static void main(String[] args) {
    TimeUnit tu = TimeUnit.DAYS;

    System.out.println(tu.toDays(1));
    System.out.println(tu.toHours(1));
    System.out.println(tu.toMinutes(1));

  }
}
