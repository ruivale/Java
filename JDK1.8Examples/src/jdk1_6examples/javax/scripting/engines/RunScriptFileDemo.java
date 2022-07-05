package jdk1_6examples.javax.scripting.engines;


import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class RunScriptFileDemo {
  public static void main(String[] args) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("js");
    try {
      FileReader reader = new FileReader("yourFile.js");
      engine.eval(reader);
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
