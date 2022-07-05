package jdk1_6examples.javax.scripting.engines;


import javax.script.Invocable;
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
public class InvokeFunction {

  public static void main(String args[]) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("javascript");
    try {
      engine.eval("function myFunction(name){var output = '';" +
          "  for (i = 0; i <= name.length; i++) {output = name.charAt(i)+'-'+ output" +
                  "  } return output;}");
      Invocable invokeEngine = (Invocable) engine;
      Object o = invokeEngine.invokeFunction("myFunction", "abcde");
      System.out.println(o);

    } catch (NoSuchMethodException e) {
      System.err.println(e);
    } catch (ScriptException e) {
      System.err.println(e);
    }
  }

}
