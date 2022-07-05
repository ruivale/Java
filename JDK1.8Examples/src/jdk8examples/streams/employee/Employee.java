package jdk8examples.streams.employee;

import java.util.ArrayList;
import java.util.List;


public class Employee {

  public int id, sal;
  public String name;

  public Employee() {
  }

  public Employee(int id,
                  String name,
                  int sal) {
    this.id = id;
    this.name = name;
    this.sal = sal;
  }

  public static List<Employee> getEmpList() {
    List<Employee> empList = new ArrayList<Employee>();
    empList.add(new Employee(1, "A", 2000));
    empList.add(new Employee(2, "B", 3000));
    empList.add(new Employee(3, "C", 4000));
    empList.add(new Employee(4, "D", 5000));
    return empList;
  }
}
