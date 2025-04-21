/**
 * Java intro.
 * 
 * Classname: pt.intro.java.afirst.Intro
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
 * Description:  
 * Java is a high-level, object-oriented, cross-platform programming language.
 * It was created by Sun Microsystems in 1995 and currently belongs to Oracle.
 * The language is used for developing enterprise, web, mobile (Android), and other types of applications.
 * 
 * One of Java's main mottos is:
 *    - "Write Once, Run Anywhere" (WORA) ? thanks to the JVM (Java Virtual Machine),
 *      which allows Java code to run on different operating systems without needing recompilation.
 *      For this to work, each operating system must have a native version of the JVM installed.
 * 
 * Features:
 *    - Code is compiled to bytecode, which can be executed on any installed JVM;
 *    - Follows object-oriented programming principles like encapsulation, inheritance, polymorphism, and abstraction;
 *    - Variables must be declared with a specific type and cannot be changed at runtime;
 *    - Automatic memory management (Garbage Collector or GC) takes care of cleaning unused objects;
 *    - Vast set of libraries and frameworks for multiple purposes such as Swing/JavaFX, collections, IO, etc.;
 *    - Multithreading and concurrency support, allowing simultaneous execution of tasks that access shared objects;
 *    - Exception handling and absence of pointers (like in C/C++) help prevent common programming errors;
 *    - Large community support;
 *    - And more.
 * 
 * Concepts:
 * 
 *  Classes and Objects:
 *      class Plane {
 *         String strMaker;
 *         String strModel;
 *         int nSeats;
 *      
 *         Plane(String strMaker, String strModel, int nSeats) {
 *           this.strMaker = strMaker;
 *           this.strModel = strModel;
 *           this.nSeats = nSeats;
 *         }
 *      
 *         void takeOff() {
 *            System.out.println("The " + this.strMaker + "/" + this.strModel + ", with " + this.nSeats + " seats, is taking off...");
 *         }
 *      }
 *      
 *      public class Test {
 *         public static void main(String[] args) {
 *           // creating planes
 *           // creating "airbus/350"...
 *           Plane planeAirbus350 = new Plane();
 *           planeAirbus350.strMaker = "Airbus";
 *           planeAirbus350.strModel = "350";
 *           planeAirbus350.nSeats = 375;
 *      
 *           // creating "boeing/787"...
 *           Plane planeBoeing787 = new Plane("Boeing", "787", 290);
 *      
 *           // invoking "takeOff" method...
 *           planeAirbus350.takeOff();
 *           planeBoeing787.takeOff();
 *         }
 *      }
 *      
 *  OOP Pillars in Java (aka POJO - Plain Old Java Object):
 *      - Encapsulation: using access modifiers like private, protected, and public;
 *      - Inheritance: using the "extends" keyword;
 *      - Polymorphism: through method overloading and overriding;
 *      - Abstraction: using abstract classes and interfaces.
 *      
 *  Exceptions:
 *      try {
 *        int division = 10 / 0;
 *      } catch (ArithmeticException e) {
 *        System.out.println("Exception: 10 divided by 0!");
 *      }
 * 
 *  Interfaces and abstract classes:
 *      - Allow for defining contracts and shared functionality;
 * 
 *  Collections Framework:
 *      - Provides data structures like List, Set, Map, etc., for example:
 *        List&lt;String&gt; planeMakers = new ArrayList&lt;&gt;();
 *        planeMakers.add("Airbus");
 *        planeMakers.add("Boeing");
 *        planeMakers.add("Embraer");
 *        System.out.println(planeMakers);
 * 
 * Java runtime environment (JRE, JDK, JVM):
 *    - JVM (Java Virtual Machine): interprets bytecode and runs it on the OS.
 *    - JRE (Java Runtime Environment): consists of the JVM and essential libraries to run Java apps.
 *    - JDK (Java Development Kit): includes the JRE and development tools such as the "javac" compiler.
 *
 * Pros and cons of Java:
 *  Pros:
 *    - Portability;
 *    - Strong community support;
 *    - Mature development tools;
 *    - Robust and secure code;
 *  Cons:
 *    - Slower performance compared to natively compiled languages like C/C++;
 *    - Verbose syntax compared to, e.g., Python;
 *    - JVM uses more memory than native programs;
 * 
 * </pre>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class Intro {

  /**
   * The Intro default constructor.
   */
  public Intro() {
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  @Override
  public String toString() {
    return "Intro";
  }

}
