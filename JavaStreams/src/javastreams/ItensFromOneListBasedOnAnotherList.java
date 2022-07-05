/**
 * <p>
 * Classname: javastreams.ItensFromOneListBasedOnAnotherList
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package javastreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class ItensFromOneListBasedOnAnotherList {

  private final List<Employee> emplList = new ArrayList<>();
  private final List<Department> deptList = new ArrayList<>();

  
  
  public static void main(final String[] args) {
    new ItensFromOneListBasedOnAnotherList();
  }
  
  
  ItensFromOneListBasedOnAnotherList(){
    
    populate();
    
    List<Employee> filteredList = emplList.stream()
      .filter(empl -> deptList.stream()
        .anyMatch(dept -> 
          dept.getDepartment().equals("Sales") && 
          empl.getEmployeeId().equals(dept.getEmployeeId())))
        .collect(Collectors.toList());     
    
    
    System.out.println("-> " + Arrays.deepToString(filteredList.toArray()));
    
    
    System.out.println("-----------------------------------------------");
    
    Map<String, List<Department>> map = 
      deptList.stream().collect(Collectors.groupingBy(Department::getDepartment));
    
    
    map.keySet()
      .stream()
      .forEach(d -> 
      {
        System.out.println(d + ":");
        map.get(d).forEach(dep -> System.out.println("\t"  + dep));
      });
  }

  private void populate() {
    emplList.add(new Employee(101, "Employee 1"));
    emplList.add(new Employee(102, "Employee 2"));
    emplList.add(new Employee(103, "Employee 3"));
    emplList.add(new Employee(104, "Employee 4"));
    
    deptList.add(new Department(101, "Sales"));
    deptList.add(new Department(102, "R&D"));
    deptList.add(new Department(103, "Management"));
    deptList.add(new Department(104, "Sales"));
  }

  
  class Employee {

    Integer employeeId;
    String employeeName;

    public Employee(Integer employeeId, String employeeName) {
      this.employeeId = employeeId;
      this.employeeName = employeeName;
    }

    Integer getEmployeeId(){
      return this.employeeId;
    }
    
    String getEmployeeName(){
      return this.employeeName;
    }
    
    public String toString(){
      return "Employee("+this.employeeId+","+this.employeeName+")";
    }
  }

  
  class Department {

    Integer employeeId;
    String department;

    public Department(Integer employeeId, String department) {
      this.employeeId = employeeId;
      this.department = department;
    }

    Integer getEmployeeId(){
      return this.employeeId;
    }
    
    String getDepartment(){
      return this.department;
    }    
    
    public String toString(){
      return "Department(employeeId: "+this.employeeId+", department: "+this.department+")";
    }    
  }
}
