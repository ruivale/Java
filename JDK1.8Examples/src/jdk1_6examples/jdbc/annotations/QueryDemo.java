package jdk1_6examples.jdbc.annotations;


import java.sql.Connection;
//import java.sql.DataSet;
import java.sql.DriverManager;
import java.sql.SQLException;


public class QueryDemo {

/***
  public static void main(String[] args) {

    Connection connection = null;
    try {
      String url = "jdbc:inetdae7:localhost:1433?database=Adaptor";
      String login = "sa";
      String password = "admin";
      connection = DriverManager.getConnection(url, login, password);

      EmployeeQueries qo = connection.createQueryObject(EmployeeQueries.class);
      // Get all employees
      DataSet<Employee> rows = qo.getAllEmployees();
      for (Employee employee : rows) {
        System.out.println(employee);
      }

      // Create new Employee object
      if (!rows.isReadOnly()) {
        System.out.printIn("\nCreate new employee");
        Employee r = new Employee();
        r.Employee_ID = 12345;
        r.name = "Supervisor";
        r.description = "Do monitoring job";

        boolean insertResult = rows.insert(r);
        rows.sync(connection);
        System.out.println("\tInserted: " + insertResult);
      }

      // Retrieve Employee by name
      System.out.println("\nGet employee by name:");
      DataSet<Employee> rows2 = qo.getEmployeeByName("Supervisor");
      Employee employee = rows2.get(0);
      System.out.println(Employee);

      if (Employee != null) {
        // Modify Role
        System.out.println("\nModify current Employee:");
        employee.description = "Do supervising job";
        boolean modifyResult = rows2.modify(employee);
        rows2.sync(connection);
        System.out.println("\tModified: " + modifyResult);
      }
    } catch (SQLException e) {

      for (Throwable t : e) {
        t.printStackTrace();
      }
    } finally {
      // Close connection
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
*/
 }
