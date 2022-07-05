package jdk1_6examples.javax.scripting.engines;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class GetEngineByMimeDemo {
  public static void main(String[] args) {
    ScriptEngineManager manager = new ScriptEngineManager();
    /* Retrieve a ScriptEngine that supports the text/javascript MIME type */
    ScriptEngine jsEngine = manager.getEngineByMimeType("text/javascript");
    if (! (jsEngine == null)) {
      System.out.println("text/javascript MIME type retrieved:" + jsEngine);
    }
    ScriptEngine jsEngine2 = manager.getEngineByMimeType("text/vbscript");
    if (jsEngine2 == null) {
      System.out.println(
          "\nNo supported script engine found for text/vbscript MIME type.");
    }
  }
}
