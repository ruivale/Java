package jdk8examples.streams.employee;

import java.util.List;
import java.util.function.Predicate;


public class MatchDemo {

  private static void matchingWithStreams() {

    Predicate<Employee> p1 = e -> e.id < 10 && e.name.startsWith("A");
    Predicate<Employee> p2 = e -> e.sal < 10000;

    List<Employee> eList = Employee.getEmpList();

    /** *** Example #1 - Using 'allMatch' **** */
    boolean b1 = eList.stream().allMatch(p1);
    System.out.println("All employees having 'eid<10' & 'ename.equalsIgnoreCase('A')'?= " + b1 + "\n");

    boolean b2 = eList.stream().allMatch(p2);
    System.out.println("All employees having 'esalary<10000'?= " + b2 + "\n");

    /** *** Example #2 - Using 'anyMatch' **** */
    boolean b3 = eList.stream().anyMatch(p1);
    System.out.println("Any employee having 'eid<10' & 'ename.equalsIgnoreCase('A')'?= " + b3 + "\n");

    boolean b4 = eList.stream().anyMatch(p2);
    System.out.println("Any employee having 'esalary<10000'?= " + b4 + "\n");

    /** ** Example #3 - Using 'noneMatch' **** */
    boolean b5 = eList.stream().noneMatch(p1);
    System.out.println("No employee having 'esalary<10000'?= " + b5);
  }

  public static void main(String[] args) {
    matchingWithStreams();
  }
}
