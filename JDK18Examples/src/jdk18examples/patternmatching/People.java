/**
 * <p>
 * Classname:  jdk18examples.patternmatching.People
 * </p>
 * 
 * https://blogs.oracle.com/javamagazine/post/java-pattern-matching-guards-coverage-dominance?source=:em:nw:mt::::RC_WWMK200429P00043C0062:NSL400244550
 * 
 */

package jdk18examples.patternmatching;

import java.util.*;

record Person(String name, int age) {}



/**
 * <p>
 * Description:
 *    The guard in pattern line [2] seems like it would match Kane at age 118, but instead Kane
 *    matches with the pattern at line [1]. You cannot rely on the compiler to help with the logic 
 *    of your guard expressions.
 * 
 * Without the last case Person p, the compiler complains that the switch expression does not cover 
 * all possible input values. With that case, a default is still not required, so the most general 
 * case becomes the default. Because the argument to the switch is a Person, all cases are covered 
 * (except for null).
 * 
 * </p>
 */
public class People {
  
  static String categorize(Person person) {
    return switch(person) {
      case Person p && p.age() > 40 -> p + " is middle aged";                           // [1]
      case Person p && (p.name().contains("D") || p.age() == 14) -> p + " D or 14";
      case Person p && !(p.age() >= 100) -> p + " is not a centenarian";                // [2]
      case Person p -> p + " Everyone else";
    };
  }
  
  
  
/**
 * Output:
 *    Person[name=Dorothy, age=15] D or 14
 *    Person[name=John Bigboote, age=42] is middle aged
 *    Person[name=Morty, age=14] D or 14
 *    Person[name=Morty Jr., age=1] is not a centenarian
 *    Person[name=Jose, age=39] is not a centenarian
 *    Person[name=Kane, age=118] is middle aged
 * 
 * @param args 
 */  
  public static void main(String[] args) {
    List.of(
      new Person("Dorothy", 15),
      new Person("John Bigboote", 42),
      new Person("Morty", 14),
      new Person("Morty Jr.", 1),
      new Person("Jose", 39),
      new Person("Kane", 118)
    ).forEach(
      p -> System.out.println(categorize(p))
    );
  }  
  
}
