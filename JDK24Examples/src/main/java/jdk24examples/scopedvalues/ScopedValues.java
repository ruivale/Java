/**
 * <p>
 * Classname: jdk24examples.scopedvalues.ScopedValues
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2024 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package jdk24examples.scopedvalues;

import static java.lang.ScopedValue.where;

/**
 * <p>
 * Rebinding scoped values
 * That scoped values have no set method means that a caller can use a scoped 
 * value to reliably communicate a value to its callees in the same thread. 
 * However, there are occasions when one of its callees might need to use the 
 * same scoped value to communicate a different value to its own callees. 
 * The ScopedValue API allows a new nested binding to be established for subsequent calls:
 * 
 * private static final ScopedValue<String> X = ScopedValue.newInstance();
 * 
 * void foo() {
 *    where(X, "hello").run(() -> bar());
 * }
 * 
 * void bar() {
 *     System.out.println(X.get()); // prints hello
 *     where(X, "goodbye").run(() -> baz());
 *     System.out.println(X.get()); // prints hello
 * }
 * 
 * void baz() {
 *     System.out.println(X.get()); // prints goodbye
 * }
 * bar reads the value of X to be "hello", as that is the binding in the 
 * scope established in foo. But then bar establishes a nested scope to run 
 * baz where X is bound to goodbye.
 * 
 * Notice how the "goodbye" binding is in effect only inside the nested scope. 
 * Once baz returns, the value of X inside bar reverts to '"hello"'. The body of 
 * bar cannot change the binding seen by that method itself but can change the 
 * binding seen by its callees. After foo  exits, X reverts to being unbound. 
 * This nesting guarantees a bounded lifetime for sharing of the new value.
 *
 *
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250909
 */
public class ScopedValues {

  /**
   * The ScopedValues default constructor.
   */
  private void testScopedValues() {
    foo();
  }

  private static final ScopedValue<String> X = ScopedValue.newInstance();

  void foo() {
    where(X, "hello").run(() -> bar());
  }

  void bar() {
    System.out.println(X.get()); // prints hello
    where(X, "goodbye").run(() -> baz());
    System.out.println(X.get()); // prints hello
  }

  void baz() {
    System.out.println(X.get()); // prints goodbye
  }

  public static void main(final String[] args) {
    final ScopedValues clazz = new ScopedValues();
    clazz.testScopedValues();
  }
}
