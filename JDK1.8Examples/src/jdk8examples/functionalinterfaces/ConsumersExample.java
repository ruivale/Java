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

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 
 * https://www.baeldung.com/java-8-functional-interfaces
 * 
 * <pre>
 * Introduction
 * </pre> 
 * This tutorial is a guide to different functional interfaces present in Java 8, as well as
 * their general use cases, and usage in the standard JDK library.
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
 * </pre> It?s recommended that all functional interfaces have an informative
 *
 * @FunctionalInterface annotation. This clearly communicates the purpose of the interface, and also
 * allows a compiler to generate an error if the annotated interface does not satisfy the
 * conditions. Any interface with a SAM(Single Abstract Method) is a functional interface, and its
 * implementation may be treated as lambda expressions. Note that Java 8?s default methods are not
 * abstract and do not count; a functional interface may still have multiple default methods. We can
 * observe this by looking at the Function?s documentation.
 *
 * <pre>
 * Consumers
 * </pre> 
 * As opposed to the Supplier, the Consumer accepts a generified argument and returns
 * nothing. It is a function that is representing side effects. For instance, let?s greet everybody
 * in a list of names by printing the greeting in the console. The lambda passed to the List.forEach
 * method implements the Consumer functional interface:
 * <pre>
 *    List<String> names = Arrays.asList("John", "Freddy", "Samuel");
 *    names.forEach(name -> System.out.println("Hello, " + name));
 * </pre>
 *
 * There are also specialized versions of the Consumer ? DoubleConsumer, IntConsumer and
 * LongConsumer ? that receive primitive values as arguments. More interesting is the BiConsumer
 * interface. One of its use cases is iterating through the entries of a map:
 * <pre>
 *    Map<String, Integer> ages = new HashMap<>();
 *    ages.put("John", 25);
 *    ages.put("Freddy", 24);
 *    ages.put("Samuel", 30);
 *
 *    ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));
 * </pre>
 *
 * Another set of specialized BiConsumer versions is comprised of ObjDoubleConsumer, ObjIntConsumer,
 * and ObjLongConsumer, which receive two arguments; one of the arguments is generified, and the
 * other is a primitive type.
 * </p>
 *
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250825
 */
public class ConsumersExample {

  private static void consumersExample() {
    List<String> names = Arrays.asList("John", "Freddy", "Samuel");
    names.forEach(name -> System.out.println("Hello, " + name));
  }

  public static void main(String[] args) {
    ConsumersExample.consumersExample();
    
  }
}
