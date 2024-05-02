/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package password;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 2334
 */
public class PasswordRegexpIT {
  
  public PasswordRegexpIT() {
  }
  
  
@Test
public void givenStringPassword_whenUsingRegulaExpressions_thenCheckIfPasswordValid() {
    PasswordRegexp pr = new PasswordRegexp();
    assertTrue(matcher.matches());
}  
}
