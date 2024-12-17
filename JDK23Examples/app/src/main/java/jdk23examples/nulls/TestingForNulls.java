package jdk23examples.nulls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;



/**
 * <p>
 * Description: Independent of functional style or imperative code, checking null references is a
 * common and recommended technique used for mitigating the occurrence of famous
 * NullPointerException exception. This kind of checking is heavily exploited for method arguments
 * to ensure that the passing references will not cause NullPointerException or unexpected behavior.
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 *
 * @since 241213 (Friday ;-) )
 */
public class TestingForNulls {
  /* This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(TestingForNulls.class.getName());

  /* .. */
  private static final long serialVersionUID = 0L;
  /* .. */
  private int memoizedHashCode = 0;
  /* .. */
  private List<Integer> numbers= Arrays.asList(1, 2, null, 4, null, 16, 7, null);

  
  /**
   * 
   * @param integers
   * @return 
   */
  public static List<Integer> evenIntegersSimple(final List<Integer> integers) {
    if (integers == null) {
      return Collections.emptyList();
    }
    
    final List<Integer> evens = new ArrayList<>(integers.size() / 2); // just a guess
    
    for (Integer nr : integers) {
      if (nr != null && nr % 2 == 0) {
        evens.add(nr);
      }
    }

    return evens;
  }
 
  
  /**
   * Starting with JDK 8, the java.util.Objects class contains two methods that wrap the null checks
   * based on these two operators: object == null was wrapped in Objects.isNull(), and object !=
   * null was wrapped in Objects.nonNull().
   *
   * @param integers
   * @return 
   */
  public static List<Integer> evenIntegersWithObjMethods(final List<Integer> integers) {
    if (Objects.isNull(integers)) {
      return Collections.emptyList();
    }
    
    final List<Integer> evens = new ArrayList<>(integers.size() / 2); // just a guess
    
    for (Integer nr : integers) {
      if (Objects.nonNull(nr) && nr % 2 == 0) {
        evens.add(nr);
      }
    }
    
    return evens;
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("TestingForNulls").append("").toString();
  }

  
  
  
  public static void main(final String[] args){
    final TestingForNulls clazz = new TestingForNulls();
  }
}


