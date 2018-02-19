package jdk9examples.optionals;

import java.util.Optional;
import java.util.function.Function;


/**
 * From: https://zeroturnaround.com/rebellabs/the-best-java-9-language-and-api-improvements/
 * <p>
 * public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier)
 * - The or() method gives you a fluent way of chaining behaviour on Optionals without checking if the
 * value is present or not.
 * <p>
 * <p>
 * Another welcome addition is the ability to convert an Optional into a Stream containing at most
 * one element. It?s really useful if you want to use the laziness of the Streams API.
 * <p>
 * <p>
 * The last, but by no means least, Optional addition we want to talk about is the ifPresentOrElse method.
 * In Java 8, you could specify the behaviour you want to execute if the value in an Optional is present.
 * In Java 9 you can pass 2 Runnables to specify what to do if the value is present and otherwise.
 * <p>
 */
public class OptionalTests {

  public OptionalTests() {
    try {
      ////////////////////////////////////////////////////////////////////////////////////////////////
      // public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier)
      System.setProperty("cache.limit", "999");
      System.setProperty("cache.enabled", "tr_ue");

      int size = OptionalTests.getProperty("cache.limit", Integer::parseInt).orElse(500);
      System.out.println("Size: " + size);

      final Optional<String> opt = OptionalTests.getProperty("cache.limit").or(() -> Optional.of("-1"));
      System.out.println("Size: " + opt);

      // ??????????????' didn't get it .... yet!
      Optional.empty().or(() -> Optional.of("RebelLabs"));
      ////////////////////////////////////////////////////////////////////////////////////////////////

      ////////////////////////////////////////////////////////////////////////////////////////////////
      // Check out the difference in the examples below. Calling map() on the Optional executes the
      // mapping function immediately, on the Stream ? not.
      Optional.of(1).map(x -> x * 3);

      Optional.of(1).stream().map(x -> x * 3);
      ////////////////////////////////////////////////////////////////////////////////////////////////

      ////////////////////////////////////////////////////////////////////////////////////////////////
      //
      Optional.empty().ifPresentOrElse(x -> System.out.println(x), () -> System.out.println("empty"));
      ////////////////////////////////////////////////////////////////////////////////////////////////

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   *
   * @param propertyName
   *
   * @return
   */
  public static Optional<String> getProperty(final String propertyName) {
    return propertyName != null && !propertyName.isEmpty() ? Optional.ofNullable(System.getProperty(
      propertyName)) : Optional.empty();
  }

  /**
   *
   * @param <T>
   * @param propertyName
   * @param func
   *
   * @return
   */
  public static <T> Optional<T> getProperty(
    final String propertyName,
    final Function<String, ? extends T> func) {

    return getProperty(propertyName).map(val -> {
      try {
        return func.apply(val);

      } catch (Exception e) {
        System.err.println("Invalid property transform, will default " + e.getMessage());
        return null;
      }
    });
  }

  public static void main(final String[] args) {
    final OptionalTests clazz = new OptionalTests();
  }
}
