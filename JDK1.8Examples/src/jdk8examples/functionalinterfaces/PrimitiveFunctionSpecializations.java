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
 * It's recommended that all functional interfaces have an informative
 *
 * @FunctionalInterface annotation. This clearly communicates the purpose of the interface, and also
 * allows a compiler to generate an error if the annotated interface does not satisfy the
 * conditions. Any interface with a SAM(Single Abstract Method) is a functional interface, and its
 * implementation may be treated as lambda expressions. Note that Java 8?s default methods are not
 * abstract and do not count; a functional interface may still have multiple default methods. We can
 * observe this by looking at the Function?s documentation.
 *
 * <pre>
 * Primitive Function Specializations
 * </pre>
 * Since a primitive type can?t be a generic type argument, there are versions of the Function 
 * interface for the most used primitive types double, int, long, and their combinations in argument 
 * and return types:
 * <pre>
 *    - IntFunction, LongFunction, DoubleFunction: arguments are of specified type, return type is 
 *      parameterized ToIntFunction, ToLongFunction, ToDoubleFunction: return type is of specified 
 *      type, arguments are parameterized
 *    - DoubleToIntFunction, DoubleToLongFunction, IntToDoubleFunction, IntToLongFunction, 
 *      LongToIntFunction, LongToDoubleFunction: having both argument and return type defined as 
 *      primitive types, as 
 *      specified by their names
 * </pre>
 * 
 * As an example, there is no out-of-the-box functional interface for a function that takes a short 
 * and returns a byte, but nothing stops us from writing our own:
 * <pre>
 *    @FunctionalInterface
 *    public interface ShortToByteFunction {
 *        byte applyAsByte(short s);
 *    }
 * </pre>
 * 
 * Now we can write a method that transforms an array of short to an array of byte using a rule 
 * defined by a ShortToByteFunction:
 * <pre>
 *    public byte[] transformArray(short[] array, ShortToByteFunction function) {
 *      byte[] transformedArray = new byte[array.length];
 *      for (int i = 0; i < array.length; i++) {
 *        transformedArray[i] = function.applyAsByte(array[i]);
 *      }
 * 
 *      return transformedArray;
 *    }
 * </pre>
 * 
 * Here?s how we could use it to transform an array of shorts to an array of bytes multiplied by 2:
 * <pre>
 *    short[] array = {(short) 1, (short) 2, (short) 3};
 *    byte[] transformedArray = transformArray(array, s -> (byte) (s * 2));
 *    byte[] expectedArray = {(byte) 2, (byte) 4, (byte) 6};
 *    assertArrayEquals(expectedArray, transformedArray);
 * </pre>
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250825
 */
public class PrimitiveFunctionSpecializations {

  /**
   * 
   * @param array
   * @param function
   * @return 
   */
  private static byte[] transformArray(short[] array, ShortToByteFunction function) {
    byte[] transformedArray = new byte[array.length];
    for (int i = 0; i < array.length; i++) {
      transformedArray[i] = function.applyAsByte(array[i]);
    }
    return transformedArray;
  }
  
  
  public static void main(String[] args) {
    short[] array = {(short) 1, (short) 2, (short) 3};
    byte[] transformedArray = transformArray(array, s -> (byte) (s * 2));

    byte[] expectedArray = {(byte) 2, (byte) 4, (byte) 6};
    
    System.out.println("TxxArray: "+Arrays.toString(transformedArray));
    System.out.println("ExpArray: "+Arrays.toString(expectedArray));

  }
  
  

  @FunctionalInterface
  public interface ShortToByteFunction {
    byte applyAsByte(short s);
  }  
}
