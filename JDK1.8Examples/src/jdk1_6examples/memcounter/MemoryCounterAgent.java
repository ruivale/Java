package jdk1_6examples.memcounter;


import java.lang.instrument.Instrumentation;
import java.lang.reflect.*;
import java.util.*;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...
 *
  Let's go back to the steps necessary to use the instrumentation mechanism in
 Java 5 to estimate memory usage.

 We can create an instrumentation agent in Java by writing a class containing a
 premain(String, Instrumentation) method. This method is called by the JVM on
 startup and an instance of Instrumentation is passed in. In the
 MemoryCounterAgent class, we keep a handle to the Instrumentation handle in
 a static variable.

 We need to package the agent class into a JAR file and specify the
 Premain-Class in the manifest:

    Premain-Class: eu.javaspecialists.tjsn.memory.MemoryCounterAgent


 When we start the Java application, we need to specify the JVM parameter
 -javaagent pointing it to the JAR file. If we call the JAR file memoryagent.jar,
 we would need to start our class like this:

    java -javaagent:lib/memoryagent.jar <Our main class>


 Here is an ANT build script, which assumes your sources are in the src directory:

    <?xml version="1.0"?>
    <project name="memoryagent" default="compile">
      <target name="init">
        <tstamp/>
        <mkdir dir="build"/>
      </target>

      <target name="compile" depends="init">
        <javac srcdir="src" source="1.5" target="1.5"
               destdir="build"/>
        <copy todir="build/META-INF">
          <fileset dir="src/META-INF"/>
        </copy>
        <jar jarfile="memoryagent.jar" basedir="build"
             filesetmanifest="merge"/>
      </target>

      <target name="clean">
        <delete dir="build"/>
        <delete file="memoryagent.jar"/>
      </target>
    </project>

 *
 * </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */

public class MemoryCounterAgent {
  private static Instrumentation instrumentation;

  /** Initializes agent */
  public static void premain(String agentArgs, Instrumentation instrumentation) {
    MemoryCounterAgent.instrumentation = instrumentation;
  }

  /** Returns object size. */
  public static long sizeOf(Object obj) {
    if (instrumentation == null) {
      throw new IllegalStateException(
          "Instrumentation environment not initialised.");
    }
    if (isSharedFlyweight(obj)) {
      return 0;
    }
    return instrumentation.getObjectSize(obj);
  }

  /**
   * Returns deep size of object, recursively iterating over its
   * fields and superclasses.
   */
  public static long deepSizeOf(Object obj) {
    Map visited = new IdentityHashMap();
    Stack stack = new Stack();
    stack.push(obj);

    long result = 0;
    do {
      result += internalSizeOf(stack.pop(), stack, visited);
    } while (!stack.isEmpty()); return result;
  }

  /**
   * Returns true if this is a well-known shared flyweight.
   * For example, interned Strings, Booleans and Number objects.
   */
  private static boolean isSharedFlyweight(Object obj) {
    // optimization - all of our flyweights are Comparable
    if (obj instanceof Comparable) {
      if (obj instanceof Enum) {
        return true;
      } else if (obj instanceof String) {
        return (obj == ( (String) obj).intern());
      } else if (obj instanceof Boolean) {
        return (obj == Boolean.TRUE || obj == Boolean.FALSE);
      } else if (obj instanceof Integer) {
        return (obj == Integer.valueOf( (Integer) obj));
      } else if (obj instanceof Short) {
        return (obj == Short.valueOf( (Short) obj));
      } else if (obj instanceof Byte) {
        return (obj == Byte.valueOf( (Byte) obj));
      } else if (obj instanceof Long) {
        return (obj == Long.valueOf( (Long) obj));
      } else if (obj instanceof Character) {
        return (obj == Character.valueOf( (Character) obj));
      }
    }
    return false;
  }

  private static boolean skipObject(Object obj, Map visited) {
    return obj == null || visited.containsKey(obj) || isSharedFlyweight(obj);
  }

  private static long internalSizeOf(Object obj, Stack stack, Map visited) {
    if (skipObject(obj, visited)) {
      return 0;
    }

    Class clazz = obj.getClass();
    if (clazz.isArray()) {
      addArrayElementsToStack(clazz, obj, stack);
    } else {
      // add all non-primitive fields to the stack
      while (clazz != null) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
          if (!Modifier.isStatic(field.getModifiers()) &&
              !field.getType().isPrimitive()) {
            field.setAccessible(true);
            try {
              stack.add(field.get(obj));
            } catch (IllegalAccessException ex) {
              throw new RuntimeException(ex);
            }
          }
        }
        clazz = clazz.getSuperclass();
      }
    }
    visited.put(obj, null);
    return sizeOf(obj);
  }

  private static void addArrayElementsToStack(Class clazz, Object obj,
      Stack stack) {
    if (!clazz.getComponentType().isPrimitive()) {
      int length = Array.getLength(obj);
      for (int i = 0; i < length; i++) {
        stack.add(Array.get(obj, i));
      }
    }
  }
}
