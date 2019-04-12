package exp.sysproperties;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class SeeSysProperties {

  public SeeSysProperties() {

    System.out.println("Property(user.dir): "+System.getProperty("user.dir")+".");

  }
  public static void main(String[] args) {
    SeeSysProperties seeSysProperties1 = new SeeSysProperties();
  }
}