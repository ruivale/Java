package javastreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * Description: A reduction operation (also called a fold) takes a sequence of input elements and
 * combines them into a single summary result by repeated application of a combining operation, such
 * as finding the sum or maximum of a set of numbers, or accumulating elements into a list. The
 * streams classes have multiple forms of general reduction operations, called reduce() and
 * collect(), as well as multiple specialized reduction forms such as sum(), max(), or count(). Of
 * course, such operations can be readily implemented as simple sequential loops, as in:
 *
 *
 * int sum = 0; for (int x : numbers) { sum += x; }
 *
 * However, there are good reasons to prefer a reduce operation over a mutative accumulation such as
 * the above. Not only is a reduction "more abstract" -- it operates on the stream as a whole rather
 * than individual elements -- but a properly constructed reduce operation is inherently
 * parallelizable, so long as the function(s) used to process the elements are associative and
 * stateless. For example, given a stream of numbers for which we want to find the sum, we can
 * write:
 *
 * int sum = numbers.stream().reduce(0, (x,y) -> x+y);
 *
 * or:
 *
 * int sum = numbers.stream().reduce(0, Integer::sum);
 *
 * These reduction operations can run safely in parallel with almost no modification:
 *
 *
 * int sum = numbers.parallelStream().reduce(0, Integer::sum);
 *
 * Reduction parallellizes well because the implementation can operate on subsets of the data in
 * parallel, and then combine the intermediate results to get the final correct answer. (Even if the
 * language had a "parallel for-each" construct, the mutative accumulation approach would still
 * required the developer to provide thread-safe updates to the shared accumulating variable sum,
 * and the required synchronization would then likely eliminate any performance gain from
 * parallelism.) Using reduce() instead removes all of the burden of parallelizing the reduction
 * operation, and the library can provide an efficient parallel implementation with no additional
 * synchronization required.
 *
 * The "widgets" examples shown earlier shows how reduction combines with other operations to
 * replace for loops with bulk operations. If widgets is a collection of Widget objects, which have
 * a getWeight method, we can find the heaviest widget with:
 *
 *
 * OptionalInt heaviest = widgets.parallelStream() .mapToInt(Widget::getWeight) .max();
 *
 * In its more general form, a reduce operation on elements of type <T> yielding a result of type
 * <U> requires three parameters:
 *
 *
 * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
 *
 * Here, the identity element is both an initial seed value for the reduction and a default result
 * if there are no input elements. The accumulator function takes a partial result and the next
 * element, and produces a new partial result. The combiner function combines two partial results to
 * produce a new partial result. (The combiner is necessary in parallel reductions, where the input
 * is partitioned, a partial accumulation computed for each partition, and then the partial results
 * are combined to produce a final result.) More formally, the identity value must be an identity
 * for the combiner function. This means that for all u, combiner.apply(identity, u) is equal to u.
 * Additionally, the combiner function must be associative and must be compatible with the
 * accumulator function: for all u and t, combiner.apply(u, accumulator.apply(identity, t)) must be
 * equals() to accumulator.apply(u, t).
 *
 * The three-argument form is a generalization of the two-argument form, incorporating a mapping
 * step into the accumulation step. We could re-cast the simple sum-of-weights example using the
 * more general form as follows:
 *
 *
 * int sumOfWeights = widgets.stream() .reduce(0, (sum, b) -> sum + b.getWeight()) Integer::sum);
 *
 * though the explicit map-reduce form is more readable and therefore should usually be preferred.
 * The generalized form is provided for cases where significant work can be optimized away by
 * combining mapping and reducing into a single function.
 * </p>
 *
 */
public class Reduce {

  private static final List<Integer> numbers = new ArrayList<>(Arrays.asList(2,4,6,8,10,12,14,16));
  
  
  public static void main(final String[] args) {
    {
      int sum = 0;
      for (int x : numbers) {
         sum += x;
      }
      System.out.println("for (int x : numbers): " + sum);
    }
    
    {
      int sum = numbers.stream().reduce(0, (x,y) -> x+y);
      System.out.println("numbers.stream().reduce(0, (x,y) -> x+y): " + sum);
    }
 
    {
      int sum = numbers.stream().reduce(0, Integer::sum);
      System.out.println("numbers.stream().reduce(0, Integer::sum): " + sum);
    }
 
    {
      int sum = numbers.parallelStream().reduce(0, Integer::sum);
      System.out.println("numbers.parallelStream().reduce(0, Integer::sum): " + sum);
    }
  }
}
