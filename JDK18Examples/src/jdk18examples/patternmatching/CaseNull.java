/**
 * <p>
 * Classname: jdk18examples.patternmatching.CaseNull
 * </p>
 *
 * https://blogs.oracle.com/javamagazine/post/java-switch-expression-arrow-case-null?source=:em:nw:mt::::RC_WWMK200429P00043C0062:NSL400244550
 *
 */
package jdk18examples.patternmatching;

import java.util.function.*;

/**
 * <p>
 * Description: The case null clause in switch JDK 17 adds the (preview) ability to include the
 * previously illegal case null clause in a switch. Historically, you had to check for null cases
 * outside the switch, as shown by old() in the code below.
 *
 * In checkNull() you see that null is now a legitimate case for a switch using both arrow and colon
 * syntax.
 *
 * You might wonder whether default includes the null case. Well, defaultOnly() shows that default
 * does not capture null, and without a case null you?ll get a NullPointerException. Why? Java says
 * that a switch must cover all possible values even if it does not cover null. This is a
 * backward-compatibility issue: If Java suddenly enforced null checking, a lot of existing code
 * wouldn?t compile.
 *
 * In general, multiple patterns can be combined into one case using commas. You can even combine
 * null with another pattern, as shown below in combineNullAndCase(). Conveniently, you can combine
 * case null with default, as seen in combineNullAndDefault().
 *
 * </p>
 */
public class CaseNull {

  static void old(String s) {
    if (s == null) {
      System.out.println("null");
      return;
    }
    switch (s) {
      case "XX" ->
        System.out.println("XX");
      default ->
        System.out.println("default");
    }
  }

  static void checkNull(String s) {
    switch (s) {
      case "XX" ->
        System.out.println("XX");
      case null ->
        System.out.println("null");
      default ->
        System.out.println("default");
    }
    // Works with colon syntax, too:
    switch (s) {
      case "XX":
        System.out.println("XX");
        break;
      case null:
        System.out.println("null");
        break;
      default:
        System.out.println("default");
    }
  }

  static void defaultOnly(String s) {
    switch (s) {
      case "XX" ->
        System.out.println("XX");
      default ->
        System.out.println("default");
    }
  }

  static void combineNullAndCase(String s) {
    switch (s) {
      case "XX", null ->
        System.out.println("XX|null");
      default ->
        System.out.println("default");
    }
  }

  static void combineNullAndDefault(String s) {
    switch (s) {
      case "XX" ->
        System.out.println("XX");
      case null, default ->
        System.out.println("both");
    }
  }

  static void test(Consumer<String> cs) {
    cs.accept("XX");
    cs.accept("YY");
    try {
      cs.accept(null);
    } catch (NullPointerException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Output: XX default null XX XX default default null null XX default Cannot invoke
   * "String.hashCode()" because "<local1>" is null XX|null default XX|null XX both both
   *
   *
   * @param args
   */
  public static void main(String[] args) {
    test(CaseNull::old);
    test(CaseNull::checkNull);
    test(CaseNull::defaultOnly);
    test(CaseNull::combineNullAndCase);
    test(CaseNull::combineNullAndDefault);
  }

}
