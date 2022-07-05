package javastreams.filter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * Description:
 * </p>
 *
 */
public class FilterWithOptionals {

  private static List<Optional<String>> listOfOptionals
    = Arrays.asList(
      Optional.empty(),
      Optional.of("foo"),
      Optional.empty(),
      Optional.of("bar"));

  /**
   * The FilterWithOptionals default constructor.
   */
  public FilterWithOptionals() {
  }

  /**
   * One of the options in Java 8 is to filter out the values with Optional::isPresent and then
   * perform mapping with the Optional::get function to extract values.
   */
  static void withFilter() {
    List<String> filteredList = listOfOptionals.stream()
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(Collectors.toList());
  }

  /**
   * The other option would be to use flatMap with a lambda expression that converts an empty
   * Optional to an empty Stream instance, and non-empty Optional to a Stream instance containing
   * only one element. Alternatively, you could apply the same approach using a different way of
   * converting an Optional to Stream:
   */
  static void withFlatMap() {
    List<String> filteredList1 = listOfOptionals.stream()
      .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
      .collect(Collectors.toList());

    List<String> filteredList2 = listOfOptionals.stream()
      .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
      .collect(Collectors.toList());
  }

  /**
   * All this will get quite simplified with the arrival of Java 9 that adds a stream() method to
   * Optional.
   *
   * This approach is similar to the one showed in section 3 but this time we are using a predefined
   * method for converting Optional instance into a Stream instance:
   *
   * It will return a stream of either one or zero element(s) whether the Optional value is or isn't
   * present
   */
  static void withOptionalStream_Java9() {
    
    List<String> filteredList = listOfOptionals.stream()
      .flatMap(Optional::stream)
      .collect(Collectors.toList());

  }

  public static void main(final String[] args) {
    final FilterWithOptionals clazz = new FilterWithOptionals();
  }
}
