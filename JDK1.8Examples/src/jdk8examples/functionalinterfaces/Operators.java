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
 * Operators
 * </pre> Operator interfaces are special cases of a function that receive and return the same value
 * type. The UnaryOperator interface receives a single argument. One of its use cases in the
 * Collections API is to replace all values in a list with some computed values of the same type:
 * <pre>
 *    List<String> names = Arrays.asList("bob", "josh", "megan");
 *    names.replaceAll(name -> name.toUpperCase());
 * </pre>
 *
 * The List.replaceAll function returns void as it replaces the values in place. To fit the purpose,
 * the lambda used to transform the values of a list has to return the same result type as it
 * receives. This is why the UnaryOperator is useful here. Of course, instead of name ->
 * name.toUpperCase(), we can simply use a method reference:
 * <pre>
 *    names.replaceAll(String::toUpperCase);
 * </pre>
 *
 * One of the most interesting use cases of a BinaryOperator is a reduction operation. Suppose we
 * want to aggregate a collection of integers in a sum of all values. With Stream API, we could do
 * this using a collector, but a more generic way to do it would be to use the reduce method:
 * <pre>
 *    List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);
 *    int sum = values.stream().reduce(0, (i1, i2) -> i1 + i2);
 * </pre>
 *
 * The reduce method receives an initial accumulator value and a BinaryOperator function. The
 * arguments of this function are a pair of values of the same type; the function itself also
 * contains a logic for joining them in a single value of the same type. The passed function must be
 * associative, which means that the order of value aggregation does not matter, i.e. the following
 * condition should hold:
 * <pre>
 *    op.apply(a, op.apply(b, c)) == op.apply(op.apply(a, b), c)
 * </pre>
 *
 * The associative property of a BinaryOperator operator function allows us to easily parallelize
 * the reduction process. Of course, there are also specializations of UnaryOperator and
 * BinaryOperator that can be used with primitive values, namely DoubleUnaryOperator,
 * IntUnaryOperator, LongUnaryOperator, DoubleBinaryOperator, IntBinaryOperator, and
 * LongBinaryOperator.
 *
 * </p>
 *
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250825
 */
public class Operators {

  /**
   * Filter a list using the Stream API and keep only the names that start with the letter ?A?. The
   * Predicate implementation encapsulates the filtering logic.
   */
  private static void operatorsExample() {
    List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);

    int sum = values.stream().reduce(0, (i1, i2) -> i1 + i2);
    
    System.out.println("Sum: "+sum);
  }

  
  
  
  public static void main(String[] args) {
    Operators.operatorsExample();
  }
}
