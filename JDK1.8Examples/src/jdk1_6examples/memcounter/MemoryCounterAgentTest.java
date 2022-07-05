package jdk1_6examples.memcounter;


import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;


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

public class MemoryCounterAgentTest {
  public static void measureSize(Object o) {
    long memShallow = MemoryCounterAgent.sizeOf(o);
    long memDeep = MemoryCounterAgent.deepSizeOf(o);
    System.out.printf("%s, shallow=%d, deep=%d%n", o.getClass().getSimpleName(),
                      memShallow, memDeep);
  }

  public static void main(String[] args) {
    measureSize(new Object());
    measureSize(new HashMap());
    measureSize(new LinkedHashMap());
    measureSize(new ReentrantReadWriteLock());
    measureSize(new byte[1000]);
    measureSize(new boolean[1000]);
    measureSize(new String("Hello World".toCharArray()));
    measureSize("Hello World");
    measureSize(10);
    measureSize(100);
    measureSize(1000);
    measureSize(new Parent());
    measureSize(new Kid());
    measureSize(Thread.State.TERMINATED);
  }

  private static class Parent {
    private int i;
    private boolean b;
    private long l;
  }


  private static class Kid
      extends Parent {
    private boolean b;
    private float f;
  }
}
