package jdk9examples.streams;

import java.util.stream.IntStream;


/**
 * From: https://zeroturnaround.com/rebellabs/the-best-java-9-language-and-api-improvements/
 * <p>
 * A couple of very welcome additions to Java 9 have been updates to the existing Streams API.
 * In particular, the dropWhile and takeWhile methods. These behave quite predictably:
 * dropWhile discards the first items of the stream until a condition is met, takeWhile processes
 * items until a condition is met.
 * <p>
 * Another welcome addition is the iterate() method. Iterate allows you to write proper replacements
 * for the for loops using streams. It takes the initial value of the stream, the condition that defines
 * when to stop iterating and the step function to produce the next element.
 */
public class StreamsTests {

  public StreamsTests() {
    try {
      System.out.println("DropWhile:");
      IntStream.range(1, 10).dropWhile(x -> x < 5).forEach(System.out::println);

      System.out.println("TakeWhile:");
      IntStream.range(1, 10).takeWhile(x -> x < 5).forEach(System.out::println);

      System.out.println("\nIterate:");
      IntStream.iterate(0, x -> x < 3, x -> x + 1).forEach(System.out::println);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    final StreamsTests clazz = new StreamsTests();
  }
}
