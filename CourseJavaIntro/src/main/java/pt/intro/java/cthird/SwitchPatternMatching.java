/**
 * Java intro.
 * 
 * Classname: pt.intro.java.cthird.SwitchPatternMatching
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
package pt.intro.java.cthird;


/**
 * <pre>
 * Description: Java’s evolution continues to simplify common programming tasks, and one of the
 * latest and most exciting additions is Pattern Matching for Switch (JEP 406). Introduced in Java
 * 17, pattern matching extends the language’s expressiveness and readability, particularly when
 * dealing with switch expressions. This feature enhances type safety, reduces boilerplate code, and
 * makes Java code more concise and maintainable. In this article, we’ll explore how pattern
 * matching works in Java, its key benefits, and how you can use it effectively in your
 * applications.
 *
 * 1. What Is Pattern Matching for Switch? 
 * Pattern matching is a feature that allows developers to perform more complex type checks and conditional
 * logic in a simplified manner. In the context of switch expressions, JEP 406 enables you to match patterns
 * of values directly, reducing the need for cumbersome type-casting and instanceof checks.
 *
 * In earlier versions of Java, switch was limited to comparing primitive types and enums, or using
 * the instanceof keyword for more complex types. This often led to repetitive and error-prone code.
 * With JEP 406, you can now directly test the type and extract values within a single case label,
 * making the code cleaner, safer, and more readable.
 *
 * For instance, with pattern matching, a switch statement can match types, destructure values, and
 * apply logic all in one go.
 *
 * 2. Syntax and Features of Pattern Matching for Switch 
 * Here’s a quick overview of how pattern
 * matching works with switch expressions in Java 17 and beyond:
 *
 * Type Patterns: You can now match the type of an object in a switch expression. This eliminates
 * the need for an explicit instanceof check. Null Safety: Pattern matching ensures that the types
 * are checked, avoiding NullPointerException and making your code more resilient. Enhanced
 * Readability: The syntax is cleaner because the type check and casting happen automatically.
 * </pre>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class SwitchPatternMatching {
  
  /**
   * 
   * @param obj the object to be described.
   * @return the description of the object type.
   */
  public static String getTypeDescription(Object obj) {
      return switch (obj) {
          case Integer i -> "Integer: " + i;
          case String s -> "String: " + s;
          case Double d -> "Double: " + d;
          default -> "Unknown Type";
      };
  }  

  public static void main(final String[] args){
    System.out.println(SwitchPatternMatching.getTypeDescription("This is a String."));
    System.out.println(SwitchPatternMatching.getTypeDescription(999));
    System.out.println(SwitchPatternMatching.getTypeDescription(99.9));
    System.out.println(SwitchPatternMatching.getTypeDescription(9999999999999L));
  }
  
}



















