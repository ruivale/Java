package jdk1_6examples.scripting;


import java.util.*;
import java.io.*;
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

public class BrowserScript {
  public BrowserScript() {
    try {
      ScriptEngineManager m = new ScriptEngineManager();
      ScriptEngine engine = m.getEngineByName("javascript");
      if (engine != null) {
        InputStream is = this.getClass().getResourceAsStream("browse.js");
        Reader reader = new InputStreamReader(is);
        engine.eval(reader);
        Invocable invocableEngine = (Invocable) engine;
        invocableEngine.invokeFunction("browse");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    BrowserScript m = new BrowserScript();
  }
}
