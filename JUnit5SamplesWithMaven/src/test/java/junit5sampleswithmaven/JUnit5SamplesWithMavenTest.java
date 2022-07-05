/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package junit5sampleswithmaven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 2334
 */
public class JUnit5SamplesWithMavenTest {
  
  public JUnit5SamplesWithMavenTest() {
  }

  @org.junit.jupiter.api.BeforeAll
  public static void setUpClass() throws Exception {
  }

  @org.junit.jupiter.api.BeforeEach
  public void setUp() throws Exception {
  }
  
//  @BeforeAll
//  public static void setUpClass() {
//  }
//  
//  @BeforeEach
//  public void setUp() {
//  }

  /**
   * Test of getDouble method, of class JUnit5SamplesWithMaven.
   */
  @org.junit.jupiter.api.Test
  public void testGetDouble() {
    System.out.println("getDouble");
    long lValue = 3;
    long expResult = 6;
    long result = JUnit5SamplesWithMaven.getDouble(lValue);
    assertEquals(expResult, result);
  }

  /**
   * Test of getPower method, of class JUnit5SamplesWithMaven.
   */
  @org.junit.jupiter.api.Test
  public void testGetPower() {
    System.out.println("getPower");
    long lValue = 3;
    long expResult = 9;
    long result = JUnit5SamplesWithMaven.getPower(lValue);
    assertEquals(expResult, result);
  }
  
}
