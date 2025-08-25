/**
 * <p>
 * Classname: jdk8examples.functionalinterfaces.FunctionExample
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2024 EFACEC SE
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
package jdk8examples.functionalinterfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 
 * https://www.baeldung.com/java-8-functional-interfaces
 * 
 * <pre>
 * Introduction 
 * </pre>
 * This tutorial is a guide to different functional interfaces present in Java 8, as
 * well as their general use cases, and usage in the standard JDK library.
 *
 * <pre>
 * Lambdas 
 * </pre>
 * in Java 8 Java 8 brought a powerful new syntactic improvement in the form of lambda
 * expressions. A lambda is an anonymous function that we can handle as a first-class language
 * citizen. For instance, we can pass it to or return it from a method. Before Java 8, we would
 * usually create a class for every case where we needed to encapsulate a single piece of
 * functionality. This implied a lot of unnecessary boilerplate code to define something that served
 * as a primitive function representation. The article ?Lambda Expressions and Functional
 * Interfaces: Tips and Best Practices? describes in more detail the functional interfaces and best
 * practices of working with lambdas. This guide focuses on some particular functional interfaces
 * that are present in the java.util.function package.
 *
 * <pre>
 * Functional Interfaces
 * </pre>
 * It?s recommended that all functional interfaces have an informative
 *
 * @FunctionalInterface annotation. This clearly communicates the purpose of the interface, and also
 * allows a compiler to generate an error if the annotated interface does not satisfy the
 * conditions. Any interface with a SAM(Single Abstract Method) is a functional interface, and its
 * implementation may be treated as lambda expressions. Note that Java 8?s default methods are not
 * abstract and do not count; a functional interface may still have multiple default methods. We can
 * observe this by looking at the Function?s documentation.
 *
 * <pre>
 * Functions 
 * </pre>
 * The most simple and general case of a lambda is a functional interface with a method
 * that receives one value and returns another. This function of a single argument is represented by
 * the Function interface, which is parameterized by the types of its argument and a return value:
 *
 * <pre>
 *    public interface Function<T, R> { ? }
 * </pre>
 *
 * One of the usages of the Function type in the standard library is the Map.computeIfAbsent method.
 * This method returns a value from a map by key, but calculates a value if a key is not already
 * present in a map. To calculate a value, it uses the passed Function implementation:
 *
 * <pre>
 *    Map<String, Integer> nameMap = new HashMap<>();
 *    Integer value = nameMap.computeIfAbsent("John", s-> s.length());
 * </pre>
 *
 * In this case, we will calculate a value by applying a function to a key, put inside a map, and
 * also returned from a method call. We may replace the lambda with a method reference that matches
 * passed and returned value types. Remember that an object we invoke the method on is, in fact, the
 * implicit first argument of a method. This allows us to cast an instance method length reference
 * to a Function interface:
 *
 * <pre>
 *    Integer value = nameMap.computeIfAbsent("John", String::length);
 * </pre>
 *
 * The Function interface also has a default compose method that allows us to combine several
 * functions into one and execute them sequentially:
 *
 * <pre>
 *    Function<Integer, String> intToString = Object::toString;
 *    Function<String, String> quote = s -> "'" + s + "'";
 *    Function<Integer, String> quoteIntToString = quote.compose(intToString);
 *    assertEquals("'5'", quoteIntToString.apply(5));
 * </pre>
 *
 * The quoteIntToString function is a combination of the quote function applied to a result of the
 * intToString function.
 *
 *
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250825
 */
public class FunctionExample {

  private static void mapComputeIfAbsent() {
    Map<String, Integer> nameMap = new HashMap<>();
    Integer value = nameMap.computeIfAbsent("John", String::length);

    System.out.println("mapComputeIfAbsent -> value: " + value);
  }

  
  private static void composeMethod() {
    Function<Integer, String> intToString = Object::toString;
    Function<String, String> quote = s -> "'" + s + "'";
    Function<Integer, String> quoteIntToString = quote.compose(intToString);
  }

  
  
  
  public static void main(String[] args) {
    FunctionExample.mapComputeIfAbsent();

  }
}
