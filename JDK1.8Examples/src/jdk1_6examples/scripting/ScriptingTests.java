package jdk1_6examples.scripting;


import java.util.*;
import javax.script.*;


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
public class ScriptingTests {
  public static void main(String[] args) {
    try {
      ScriptEngineManager mgr = new ScriptEngineManager();
      List<ScriptEngineFactory> factories = mgr.getEngineFactories();
      System.out.println("Available script engines:");
      for (int i = 0; i < factories.size(); i++) {
        ScriptEngineFactory factory = factories.get(i);
        String engine = factory.getEngineName();
        String language = factory.getLanguageName();

        System.out.println("-------------------------------------------");
        System.out.println("Language: " + language);
        System.out.println("Engine: " + engine);
        System.out.println("-------------------------------------------");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
