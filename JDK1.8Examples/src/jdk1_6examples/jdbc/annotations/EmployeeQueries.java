package jdk1_6examples.jdbc.annotations;


/*
import java.sql.BaseQuery;
import java.sql.DataSet;
import java.sql.Select;
*/
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

public interface EmployeeQueries /*extends BaseQuery */{
/**

    // Select all employees
    @Select (sql = "SELECT Employee_ID, NAME, DESCRIPTION FROM Employee", readOnly=false, connected=false)
    DataSet<Employee> getAllEmployees();

    // Select employee by name
    @Select ("SELECT Employee_ID, NAME, DESCRIPTION FROM Employee where NAME = ?")
    DataSet<Employee> getEmployeeByName(String name);
/**/
}
