package javastreams.merging;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


/**
 * <p>
 * Description: In this quick article, we explain different ways of merging Java Streams ? which is
 * not a very intuitive operation.
 *
 * Then, we'll present StreamEx which is an open-source Java library that extends possibilities of
 * Java 8 Streams. It uses the StreamEx class as an enhancement to the JDK's Stream interface.
 *
 * And also jOO? which is a JDK 8 compatible library that provides useful extensions to the JDK. The
 * most important stream abstraction here is called Seq. Note that this is a sequential and ordered
 * stream, so calling parallel() will have no effect.
 * </p>
 *
 *
 */
public class MergingStreams {

  /**
   *
   */
  public void whenMergingStreams_thenResultStreamContainsElementsFromBoth() {
    Stream<Integer> stream1 = Stream.of(1, 3, 5);
    Stream<Integer> stream2 = Stream.of(2, 4, 6);

    Stream<Integer> resultingStream = Stream.concat(stream1, stream2);

    assertEquals(
      Arrays.asList(1, 3, 5, 2, 4, 6),
      resultingStream.collect(Collectors.toList()));
  }

  /**
   * As we can see, this approach becomes unfeasible for more streams. Of course, we can create
   * intermediate variables or helper methods to make it more readable
   */
  public void given3Streams_whenMerged_thenResultStreamContainsAllElements() {
    Stream<Integer> stream1 = Stream.of(1, 3, 5);
    Stream<Integer> stream2 = Stream.of(2, 4, 6);
    Stream<Integer> stream3 = Stream.of(18, 15, 36);

    Stream<Integer> resultingStream = Stream.concat(
      Stream.concat(stream1, stream2), stream3);

    assertEquals(
      Arrays.asList(1, 3, 5, 2, 4, 6, 18, 15, 36),
      resultingStream.collect(Collectors.toList()));
  }

  /**
   * What happens here is: - we first create a new Stream containing the 4 Streams, which results in
   * a Stream<Stream<Integer>> - then we flatMap() this into a Stream<Integer> using the identity
   * function
   */
  public void given4Streams_whenMerged_thenResultStreamContainsAllElements() {
    Stream<Integer> stream1 = Stream.of(1, 3, 5);
    Stream<Integer> stream2 = Stream.of(2, 4, 6);
    Stream<Integer> stream3 = Stream.of(18, 15, 36);
    Stream<Integer> stream4 = Stream.of(99);

    Stream<Integer> resultingStream = Stream.of(
      stream1, stream2, stream3, stream4)
      .flatMap(i -> i);

    assertEquals(
      Arrays.asList(1, 3, 5, 2, 4, 6, 18, 15, 36, 99),
      resultingStream.collect(Collectors.toList()));
  }

  public static void main(final String[] args) {
    final MergingStreams clazz = new MergingStreams();
  }
}
