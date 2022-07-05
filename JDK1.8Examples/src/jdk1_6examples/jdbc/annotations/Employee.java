package jdk1_6examples.jdbc.annotations;

/**
 *
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
public class Employee {
  public int employeeId;
  public String name;
  public String description;

  public String toString() {
    StringBuilder buf = new StringBuilder();
    buf.append("\n\tEmployee ID = ").append(employeeId);
    buf.append("\n\tName = ").append(name);
    buf.append("\n\tDescription = ").append(description);
    return buf.toString();
  }
}
