/**
 * <p>
 * Classname: exp.strings.StringSplitExample
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package exp.strings;

import java.util.stream.IntStream;


/**
 * 
 * @author 2334
 */
public class StringsExamples {

  public static void split()
  {
    System.out.println("\nsplit:");
    
    String str = "java@program@to.com";

    String[] splitArray = str.split("@");

    for (String value : splitArray) {
      System.out.println(value);
    }    
  }
  
  public static void codePoints()
  {
    System.out.println("\ncodePoints:");
    String str = "Code Points as Stream";
    System.out.println("Input string value: " + str);

    IntStream intStream = str.codePoints();
    System.out.println("Printing each char from string as ASCII value");

    intStream.forEach(value -> System.out.print(value + " "));
    System.out.println();
  }
  
  public static void codePointsRemotion()
  {
    System.out.println("\ncodePointsRemotion:");    
    String str = "Digit ZERO 0 is not considered in input name. So removing all Zero's 00000000";
    IntStream intStream = str.codePoints();

    String zeroRemovedString = intStream.filter(ch -> ch != 48)
             .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
             .toString();    
    System.out.println("Original: " + str);
    System.out.println("W/out zeros: " + zeroRemovedString);
  }

  public static void stringPalindromeAppend()
  {
    System.out.println("\nstringPalindromeAppend:");    
    String input1 = "civic";

    StringBuffer buffer = new StringBuffer();

    for (int i = input1.length() - 1; i >= 0; i--) {
      buffer.append(input1.charAt(i));
    }

    String reversedString1 = buffer.toString();

    if (input1.equals(reversedString1)) {
      System.out.println(input1 + " is a palindrome");
    } else {
      System.out.println(input1 + " is not a palindrome");
    }    
  }

  public static void stringPalindromeRecursively()
  {
    System.out.println("\nstringPalindromeRecursively:");    
    String input1 = "civic";
    
    boolean isPalindrome = StringsExamples.isPalindrome(input1);

    if (isPalindrome) {
      System.out.println(input1 + " is a palindrome");
    } else {
      System.out.println(input1 + " is not a palindrome");
    }    
  }
  
  public static boolean isPalindrome(String s) {

      // if the string has one or zero characters then recursive call is stopped.
      if (s.length() == 0 || s.length() == 1) {
        return true;
      }

      // checking the first and last character of the string. if equals then call the
      // same function with substring from index 1 to length -1. Because substring
      // excludes the endIndex.
      // if these two values are not same then string is not Palindrome so this
      // returns false.
      if (s.charAt(0) == s.charAt(s.length() - 1)) {
        return isPalindrome(s.substring(1, s.length() - 1));
      }

      // this statment is executed if and if only first and last character of string
      // at any time is not equal.
      return false;
  }

  public static void compareWitoutDiffSign()
  {    
    System.out.println("\ncompareWitoutDiffSign:");    
    
    String status = new String("Failure");

    if (status.intern() != "Failure") {
      System.out.println("Valid age");
    } else {
      System.out.println("Invalid age");
    }    
  }
 


  
  public static void main(String[] args) {
    
    StringsExamples.split();
    StringsExamples.codePoints();
    StringsExamples.codePointsRemotion();
    StringsExamples.stringPalindromeAppend();
    StringsExamples.stringPalindromeRecursively();
    StringsExamples.compareWitoutDiffSign();
    
    
    
    
    
  }
}
