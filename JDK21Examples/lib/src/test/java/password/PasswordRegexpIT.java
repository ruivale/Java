/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package password;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 2334
 */
public class PasswordRegexpIT {

  public PasswordRegexpIT() {
  }

  @Test
  public void givenStringPassword_whenUsingRegulaExpressions_thenCheckIfPasswordValid() {
    final PasswordRegexp pr = new PasswordRegexp();
    final String strPass = "kdj%o47OJ@";

    assertTrue(pr.checkForValidPassword(strPass.toCharArray()));
  }

  @Test
  public void givenStringPassword_whenUsingRegulaExpressions_thenCheckIfPasswordValidII() {
    final PasswordRegexp pr = new PasswordRegexp();
    final String strPass = "kdjo47kg";

    assertTrue(pr.checkForValidPassword(strPass.toCharArray()));
  }
}
