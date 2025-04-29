/**
 * Java intro.
 * 
 * Classname: pt.intro.java.afirst.HelloWorld
 * Copyright (C) 2024 RGV
 * Email: ruivale at gmail dot com
 *
 * This is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU Lesser General Public License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */
package pt.intro.java.afirst;

/**
 * <pre>
 * Description: a simple Java class.
 *
 * Check for the Java version
 *    In the Command Prompt or Terminal window, type the following command and press Enter:
 *    >java -version
 *
 *
 * Installing Java
 *      The JDK 21, last LTS (Long Term Support) version, is available at:
 *          Amazon Corretto:  <a href="https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html">https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html</a>
 *          Oracle OpenJDK:   <a href="https://jdk.java.net/java-se-ri/21">https://jdk.java.net/java-se-ri/21</a>
 *          Oracle JDK:       <a href="https://www.oracle.com/java/technologies/downloads/#java21">https://www.oracle.com/java/technologies/downloads/#java21</a>
 *
 *      The JDK 24, most recent release, is available at:
 *          Amazon Corretto:  <a href="https://docs.aws.amazon.com/corretto/latest/corretto-24-ug/downloads-list.html">https://docs.aws.amazon.com/corretto/latest/corretto-24-ug/downloads-list.html</a>
 *          Oracle OpenJDK:   <a href="https://jdk.java.net/java-se-ri/24">https://jdk.java.net/java-se-ri/24</a>
 *          Oracle JDK:       <a href="https://www.oracle.com/java/technologies/downloads/#java24">https://www.oracle.com/java/technologies/downloads/#java24</a>
 *
 *      More sources:     <a href="https://javaalmanac.io/">https://javaalmanac.io/</a>
 *
 *
 * Installing an Integrated Development Environment (IDE)
 *    Netbeans:
 *      - Download: <a href="https://netbeans.apache.org/front/main/download/">Netbeans</a>
 *           Advantages:
 *               Official IDE supported by Oracle (historically).
 *               Good out-of-the-box support for Java SE, Java EE, and JavaFX.
 *               Built-in support for Maven and Ant.
 *               GUI Builder (Matisse) makes it easy to design UIs visually.
 *               Lightweight compared to some others.
 *
 *           Disadvantages:
 *               Slower development of new features compared to IntelliJ and VS Code.
 *               UI can feel outdated.
 *               Not as extensible or customizable as others.
 *               Performance can degrade on large projects.
 *
 *    IntelliJ IDEA:
 *      - Download: <a href="https://www.jetbrains.com/idea/download/">IntelliJ IDEA</a>
 *           Advantages:
 *               Excellent code completion, navigation, and refactoring tools.
 *               Smart and context-aware code suggestions.
 *               Strong integration with build tools (Maven, Gradle), version control, Docker, databases, etc.
 *               Ultimate edition offers comprehensive enterprise, Spring, and web development support.
 *
 *           Disadvantages:
 *               Ultimate edition is paid and quite expensive.
 *               Can be heavy on system resources.
 *               Steeper learning curve for beginners.
 *
 *    Visual Studio Code:
 *      - Download: <a href="https://code.visualstudio.com/download">Visual Studio Code</a>
 *           Advantages:
 *               Lightweight and very fast to launch.
 *               Highly customizable with extensions (Java support via extensions like "Extension Pack for Java").
 *               Great for mixed-language projects (e.g., JavaScript + Java, or Java + Python).
 *               Excellent Git integration and debugging tools.
 *
 *           Disadvantages:
 *               Not a full-fledged IDE by default (relies on extensions for core Java features).
 *               Refactoring and navigation not as powerful as IntelliJ or Eclipse.
 *               Managing complex Java projects (especially enterprise) can be cumbersome.
 *
 *    Eclipse:
 *      - Download: <a href="https://www.eclipse.org/downloads/packages/">Eclipse</a>
 *           Advantages:
 *               Mature and widely adopted in enterprise environments.
 *               Very extensible with plugins.
 *               Strong support for Java EE and other languages (via plugins).
 *               Good integration with build tools and version control.
 *
 *           Disadvantages:
 *               UI and UX are often criticized as outdated and unintuitive.
 *               Plugin ecosystem can be overwhelming and inconsistent.
 *               Memory-heavy and slower startup times.
 *               Occasional instability or sluggish behavior in large projects.
 *
 *
 * Writing, compiling, and running a Java program
 *      This class will serve as a first compilation and execution of a Java program. The file
 *      has a ".java" extension and contains the "main" method.
 *      
 *      Compilation consists of executing the "javac" command, available in the "bin" folder of the
 *      JDK installation, specifying the pt.intro.java.afirst.HelloWorld class as the one to compile.
 *      This command will translate the ".java" file into a ".class" file containing the class's bytecode. Example:
 *       $javac src\main\java\pt\intro\java\afirst\HelloWorld.java
 *      
 *      Execution is very similar to compilation, but the command used is "java", specifying that
 *      the initial class to execute is pt.intro.java.afirst.HelloWorld. Example:
 *       $java -cp src\main\java\ pt.intro.java.afirst.HelloWorld
 *      or
 *       $ cd src\main\java\ 
 *       $java pt.intro.java.afirst.HelloWorld
 *
 * 
 * Basic syntax and structure of a Java program  
 *      Our basic HelloWorld Java program follows this structure:
 *        - package declaration (optional): 
 *            package pt.intro.java.afirst;
 *        - import of classes (optional): 
 *            import packageName.ClassName;
 *        - class definition: 
 *            public class HelloWorld { ... }
 *        - main method (program entry point): 
 *            public static void main(String[] args) { ... }
 *        - instructions: 
 *            variable declarations, method calls, control structures, etc.
 * 
 *</pre>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class HelloWorld {
  public static void main(final String[] args){
    System.out.println("Hello world!");
  }
}
