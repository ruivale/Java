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
import java.util.stream.Collectors;

/**
 * <p>
 * 
 * https://www.baeldung.com/java-8-functional-interfaces
 * 
 * <pre>
 * Introduction
 * </pre> This tutorial is a guide to different functional interfaces present in Java 8, as well as
 * their general use cases, and usage in the standard JDK library.
 *
 * <pre>
 * Lambdas
 * </pre> in Java 8 Java 8 brought a powerful new syntactic improvement in the form of lambda
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
 * Predicates
 * </pre> 
 * In mathematical logic, a predicate is a function that receives a value and returns a
 * boolean value. The Predicate functional interface is a specialization of a Function that receives
 * a generified value and returns a boolean. A typical use case of the Predicate lambda is to filter
 * a collection of values:
 * <pre>
 *    List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");
 *
 *    List<String> namesWithA = names.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList());
 * </pre>
 *
 * In the code above, we filter a list using the Stream API and keep only the names that start with
 * the letter ?A?. The Predicate implementation encapsulates the filtering logic. As in all of the
 * previous examples, there are IntPredicate, DoublePredicate and LongPredicate versions of this
 * function that receive primitive values.
 *
 * </p>
 *
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250825
 */
public class PredicatesExample {

  /**
   * Filter a list using the Stream API and keep only the names that start with the letter ?A?. 
   * The Predicate implementation encapsulates the filtering logic.
   */
  private static void predicatesExample() {
    List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");

    List<String> namesWithA = names.stream()
      .filter(name -> name.startsWith("A"))
      .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    PredicatesExample.predicatesExample();

  }
}
