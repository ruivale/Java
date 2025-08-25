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

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <p>
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
 * Suppliers
 * </pre>
 *
 * The Supplier functional interface is yet another Function specialization that does not take any
 * arguments. We typically use it for lazy generation of values. For instance, let?s define a
 * function that squares a double value. It will not receive a value itself, but a Supplier of this
 * value:
 * <pre>
 *    public double squareLazy(Supplier<Double> lazyValue) {
 *      return Math.pow(lazyValue.get(), 2);
 *    }
 * </pre>
 *
 * This allows us to lazily generate the argument for invocation of this function using a Supplier
 * implementation. This can be useful if the generation of the argument takes a considerable amount
 * of time. We?ll simulate that using Guava?s sleepUninterruptibly method:
 * <pre>
 *    Supplier<Double> lazyValue = () -> {
 *      Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);
 *      return 9d;
 *    };
 *
 *    Double valueSquared = squareLazy(lazyValue);
 * </pre>
 *
 * Another use case for the Supplier is defining logic for sequence generation. To demonstrate it,
 * let?s use a static Stream.generate method to create a Stream of Fibonacci numbers:
 * <pre>
 *    int[] fibs = {0, 1};
 *    Stream<Integer> fibonacci = Stream.generate(() -> {
 *      int result = fibs[1];
 *      int fib3 = fibs[0] + fibs[1];
 *      fibs[0] = fibs[1];
 *      fibs[1] = fib3;
 *
 *      return result;
 *    });
 * </pre>
 *
 * The function that we pass to the Stream.generate method implements the Supplier functional
 * interface. Notice that to be useful as a generator, the Supplier usually needs some sort of
 * external state. In this case, its state comprises the last two Fibonacci sequence numbers. To
 * implement this state, we use an array instead of a couple of variables because all external
 * variables used inside the lambda have to be effectively final. Other specializations of the
 * Supplier functional interface include BooleanSupplier, DoubleSupplier, LongSupplier and
 * IntSupplier, whose return types are corresponding primitives.
 *
 * </p>
 *
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250825
 */
public class SuppliersExample {

  private static double squareLazy(Supplier<Double> lazyValue) {
    return Math.pow(lazyValue.get(), 2);
  }

  private static void supplierExample() {
    Supplier<Double> lazyValue = () -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException interruptedException) {
      }

      return 9d;
    };

    Double valueSquared = squareLazy(lazyValue);
  }

  private static void sequenceGenerator() {
    int[] fibs = {0, 1};
    Stream<Integer> fibonacci = Stream.generate(() -> {
      int result = fibs[1];
      int fib3 = fibs[0] + fibs[1];
      fibs[0] = fibs[1];
      fibs[1] = fib3;
      return result;
    });
    
    fibonacci.forEach(s -> System.out.println(s));
  }

  
  
  
  public static void main(String[] args) {
    SuppliersExample.supplierExample();
    System.out.println("-------------------------------------------");
    SuppliersExample.sequenceGenerator();
  }
}
