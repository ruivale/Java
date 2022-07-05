package jdk1_6examples.javax.scripting.engines;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


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
public class PassingParamsToScriptEngine {
  public static void main(String args[]) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("javascript");
    try {
      engine.put("name", "abcde");
      engine.eval("var output = '';for (i = 0; i <= name.length; i++) {" +
                  "  output = name.charAt(i)+'-' + output" + "}");
      String name = (String) engine.get("output");
      System.out.println(name);
    } catch (ScriptException e) {
      System.err.println(e);
    }
  }
}
