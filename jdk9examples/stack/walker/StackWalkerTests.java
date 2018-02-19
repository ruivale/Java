package jdk9examples.stack.walker;

import java.util.stream.Collectors;


/**
 * From: https://zeroturnaround.com/rebellabs/the-best-java-9-language-and-api-improvements/
 * <p>
 * Rejoice exception haters, now you?ll have a new way of accessing the stack traces without
 * creating the exception objects. Welcome the StackWalker. StackWalker enables you to walk,
 * filter and otherwise access stack traces in a very efficient manner.
 * <p>
 */
public class StackWalkerTests {

  public StackWalkerTests() {
    try {

      // StackWalker enables you to walk, filter and otherwise access stack traces in a very efficient
      // manner. Check out the code snippet below that accesses the top 5 stack trace elements:
      System.out.println("StackWalker: " + StackWalker.getInstance().walk(s -> s.limit(5).collect(
        Collectors.toList())));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    final StackWalkerTests clazz = new StackWalkerTests();
  }
}
