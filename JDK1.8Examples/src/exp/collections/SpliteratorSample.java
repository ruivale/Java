
package exp.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

/**
 * <p>
 * Description:
 * Why use Spliterator?
 *    Java Spliterator offers us several advantages:
 * 
 *        - Supports parallel programming.
 *        - We can use it for both sequential and parallel processing of data items .
 *        - tryAdvance() method combines both next() and hasNext() operations of a simple Iterator 
 *          and so offers a better performance
 * 
 *    Also, it?s important to realize that the Spliterator works fine for both Collection and 
 *    Stream sources, but not with the Map implementations as the source.
 * 
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class SpliteratorSample {

  public static void main(final String[] args) {

    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    Spliterator splitr = list.spliterator();

    int charactersticsORed = splitr.characteristics(); //16464
    System.out.println("characterstics="+charactersticsORed);

    boolean isSized = splitr.hasCharacteristics(Spliterator.SIZED);   //true
    System.out.println("isSized="+isSized);
    boolean isSorted = splitr.hasCharacteristics(Spliterator.SORTED); //false 
    System.out.println("isSorted="+isSorted);
    boolean isNonNull = splitr.hasCharacteristics(Spliterator.NONNULL); //false
    System.out.println("isNonNull="+isNonNull);

    long estimatedSize = splitr.estimateSize(); // 5
    System.out.println("estimatedSize="+estimatedSize);

    long size = splitr.getExactSizeIfKnown(); // 5
    System.out.println("size="+size);

    System.out.println("");
    while (splitr.tryAdvance((item) -> System.out.println(item)));

    System.out.println("");
    splitr.forEachRemaining(item -> System.out.println(item));
    
    

    {
      // trySplit() method over ORDERED splitr
      Spliterator<Integer> splitrNew = splitr.trySplit();

      // Elements in our splitrNew = {1, 2, 3}
      if (splitrNew != null) {
        splitrNew.forEachRemaining((n) -> System.out.println(n));
      }

      // Elements in our splitr - {4 , 5}
      splitr.forEachRemaining((n) -> System.out.println(n));
    }

  }
}
