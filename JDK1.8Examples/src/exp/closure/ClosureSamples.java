/**
 * <p>
 * Classname: exp.closure.ClosureSample
 * </p>
 */
package exp.closure;

import java.io.*;

// Defining an interface whose
// implementation is given in
// the lambda expression.
// This uses the concept of
// closures
interface SalutationInterface {
  public String salHello();
}

// Defining an interface whose
// implementation is given in
// the lambda expression.
// This uses the concept of
// closures
interface concatStrings {
  public String concat(final String a, final String b);
}

// Defining an interface whose
// implementation is given in
// the lambda expression.
// This uses the concept of
// closures
interface NumToMonth {
  public String convertToMonth(final int x);
}


/**
 * <p>
 * Description:
 * </p>
 */
public class ClosureSamples {

  // Driver code
  public static void simpleSample() {
    // Lambda Expression
    final SalutationInterface obj = ()
      -> {
      return "Hello, GFGians!";
    };

    // Calling the above interface
    System.out.println(obj.salHello());
  }

  public static void notSoSimpleSample() {
    // Lambda Expression
    final concatStrings s = (s1, s2) -> s1 + s2;

    System.out.println(
      s.concat("Hello, ",
        "GFGians!"));
  }

  public static void moreElaborateSample() {
    // Lambda Expression
    final NumToMonth obj = new NumToMonth() {
      final String[] months = {
        "Jan", "Feb", "Mar",
        "Apr", "May", "Jun",
        "Jul", "Aug", "Sep",
        "Oct", "Nov", "Dec"
      };

      public String convertToMonth(final int n) {
        return (n > 0 && n <= months.length)
          ? months[n - 1]
          : null;
      };
    };
        
    System.out.println(obj.convertToMonth(8));    
  }
  
  
  
  public static void main(String[] args) {

    ClosureSamples.simpleSample();
    ClosureSamples.notSoSimpleSample();
    ClosureSamples.moreElaborateSample();
  }

}
