
package javastreams;

import java.util.Arrays;
import java.util.List;


/**
 * <p>
 * Description:
 * </p>
 *
 */
public class GeneralTests {
  
  private static List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
  
  
  private static void mapAndFlatMap(){
    
    System.out.print("map c%2==0: \n\t");
    list.stream().map(c -> c % 2 == 0? c: null).forEach(d -> System.out.print(d + ","));    
    
//    System.out.print("flatmap c%2==0: \n\t");
//    list.stream().flatMap(c -> c % 2 == 0? c: null).forEach(d -> System.out.print(d + ","));
   
    System.out.println("");    
  }

  public static void main(final String[] args){
    GeneralTests.mapAndFlatMap();   
  }
}
