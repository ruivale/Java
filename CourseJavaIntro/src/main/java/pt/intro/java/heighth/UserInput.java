/**
 * Java intro.
 * 
 * Classname: pt.intro.java.heighth.UserInput
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
package pt.intro.java.heighth;

import java.util.Scanner;


/**
 * <pre>
 * Description: 
 * Taking user input is a fundamental aspect of programming that allows applications to
 * interact with users. In Java, there are several ways to capture user input, but the most common
 * method is using the Scanner class from the java.util package. This article will guide you through
 * how to use the Scanner class to take input from users, complete with code examples and expected
 * outputs.
 *
 * Using the Scanner Class in Java To use the Scanner class for user input, follow these steps:
 *
 * Import the Scanner Class: You need to import the java.util.Scanner package at the beginning of
 * your program. Create a Scanner Object: Instantiate a Scanner object that reads from System.in,
 * which is the standard input stream. Use Scanner Methods: Utilize various methods provided by the
 * Scanner class to read different types of input (e.g., strings, integers, doubles).
 * 
 * Using the Scanner class in Java provides a straightforward way to gather user input for your
 * applications. By following this guide and utilizing the provided code example, you can
 * effectively implement user input functionality in your Java programs. As you become more familiar
 * with Java, you'll find that capturing user input can significantly enhance interactivity and
 * usability in your applications.
 * </pre>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class UserInput {

  public static void main(final String[] args) {
    // Create a Scanner object
    Scanner scanner = new Scanner(System.in);

    // Prompt for and read a string input
    System.out.print("Enter your name: ");
    String name = scanner.nextLine(); // Read user input as a string

    // Prompt for and read an integer input
    System.out.print("Enter your age: ");
    int age = scanner.nextInt(); // Read user input as an integer

    // Prompt for and read a double input
    System.out.print("Enter your salary: ");
    double salary = scanner.nextDouble(); // Read user input as a double

    // Output the collected inputs
    System.out.println("Hello, " + name + "!");
    System.out.println("You are " + age + " years old.");
    System.out.println("Your salary is $" + salary);

    // Close the scanner
    scanner.close();    
  }
}
