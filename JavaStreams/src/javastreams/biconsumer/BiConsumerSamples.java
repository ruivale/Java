package javastreams.biconsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * <p>
 * Description: Java8 BiConsumer function interface represents an operation with two arguments and
 * returns no result. Syntax - accept(T t, U u);
 *
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class BiConsumerSamples {
// example method 1

  private static void basic() {
    // BiConsumer lambda expression
    final BiConsumer<String, String> concat
      = (val1, val2) -> System.out.println(val1 + " " + val2);

    concat.accept("Hello", "world!");
  }

  // example method 2
  private static void printMap() {
    final Map<Integer, String> map = new HashMap<>();
    map.put(1, "Car");
    map.put(2, "Ship");
    map.put(3, "Bike");
    map.put(4, "Trolley");
    map.put(5, "Airplane");

    // BiConsumer lambda expression
    // print the key and value for a map
    final BiConsumer<Integer, String> print
      = (val1, val2) -> System.out.println(val1 + " " + val2);

    // using forEach() as it can accept the BiConsumer lambda expression
    map.forEach(print);
  }

  // example method 3
  private static void andThen() {
    // BiConsumer lambda expression
    final BiConsumer<Integer, Integer> addition
      = (val1, val2) -> System.out.println("Sum of input is= " + (val1 + val2));

    final BiConsumer<Integer, Integer> subtraction
      = (val1, val2) -> System.out.println("Subtraction of input is= " + (val1 - val2));

    // using andThen()
    // run the 2 logic one after another on the same input
    // if passing null to andThen() method it will throw NullPointerException
    addition.andThen(subtraction).accept(10, 5);
  }

  // driver method
  public static void main(String[] args) {
    System.out.println("----- BiConsumer functional interface example -----\n");

    basic();

    System.out.println("\n");

    printMap();

    System.out.println("\n");

    andThen();
  }
}
