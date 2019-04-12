package exp.properties;



import java.util.*;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ReloadPropertiesFromFile {
  String bundleName = "exp/properties/property";
  String var = "a.property.to.be.reload";

  public ReloadPropertiesFromFile() {
    try{
      Properties propertiesFile = new Properties();
      propertiesFile.load(new FileInputStream(bundleName + ".properties"));

      System.out.println("a.property.to.be.reload="+ propertiesFile.getProperty(var)+".");

      Thread.sleep(10000);

      Properties propertiesFile2 = new Properties();
      propertiesFile2.load(new FileInputStream(bundleName + ".properties"));

      System.out.println("a.property.to.be.reload="+ propertiesFile2.getProperty(var)+". AFTER");

    }catch(Exception e){
      e.printStackTrace();
    }

  }
  public static void main(String[] args) {
    ReloadPropertiesFromFile reloadPropertiesFromFile1 = new ReloadPropertiesFromFile();
  }
}