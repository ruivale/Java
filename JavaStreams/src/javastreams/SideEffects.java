package javastreams;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * <p>
 * Description: Side-effects in behavioral parameters to stream operations are, in general,
 * discouraged, as they can often lead to unwitting violations of the statelessness requirement, as
 * well as other thread-safety hazards. If the behavioral parameters do have side-effects, unless
 * explicitly stated, there are no guarantees as to the visibility of those side-effects to other
 * threads, nor are there any guarantees that different operations on the "same" element within the
 * same stream pipeline are executed in the same thread. Further, the ordering of those effects may
 * be surprising. Even when a pipeline is constrained to produce a result that is consistent with
 * the encounter order of the stream source (for example, IntStream.range(0,5).parallel().map(x ->
 * x*2).toArray() must produce [0, 2, 4, 6, 8]), no guarantees are made as to the order in which the
 * mapper function is applied to individual elements, or in what thread any behavioral parameter is
 * executed for a given element.
 *
 * Many computations where one might be tempted to use side effects can be more safely and
 * efficiently expressed without side-effects, such as using reduction instead of mutable
 * accumulators. However, side-effects such as using println() for debugging purposes are usually
 * harmless. A small number of stream operations, such as forEach() and peek(), can operate only via
 * side-effects; these should be used with care.
 *
 * As an example of how to transform a stream pipeline that inappropriately uses side-effects to one
 * that does not, the following code searches a stream of strings for those matching a given regular
 * expression, and puts the matches in a list.
 *
 *
 * ArrayList<String> results = new ArrayList<>(); 
 * stream.filter(s -> pattern.matcher(s).matches()).forEach(s -> results.add(s)); // Unnecessary use of side-effects!
 *
 * This code unnecessarily uses side-effects. If executed in parallel, the non-thread-safety of
 * ArrayList would cause incorrect results, and adding needed synchronization would cause
 * contention, undermining the benefit of parallelism. Furthermore, using side-effects here is
 * completely unnecessary; the forEach() can simply be replaced with a reduction operation that is
 * safer, more efficient, and more amenable to parallelization:
 *
 * List<String>results = 
 *    stream.filter(s -> pattern.matcher(s).matches()).collect(Collectors.toList()); // No side-effects!
 * </p>
 * 
 */
public class SideEffects {

  public static void main(final String[] args) {

    System.out.println("IntStream.range(0, 10).parallel().map(x -> x * 2).toArray(): \n\t"
      + (Arrays.toString(IntStream.range(0, 10).parallel().map(x -> x * 2).toArray())));

  }
}
