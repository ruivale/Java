/**
 * <p>
 * Classname: jdk23examples.nulls.Car
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
package jdk23examples.nulls;

import java.awt.Color;
import java.awt.Point;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 *
 * @since 241213 (Friday ;-) )
 */
public class Car {

  /* This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(Car.class.getName());

  /* .. */
  private static final long serialVersionUID = 0L;

  private int memoizedHashCode = 0;

  private final String name;
  private final Color color;

  

//  /**
//   * 
//   * @param name
//   * @param color 
//   * 
//   * @note: +/- 10 million calls take 2500 millis.
//   */
//  public Car(String name, Color color) {
//    if (name == null) {
//      throw new NullPointerException("Car name cannot be null");
//    }
//    
//    if (color == null) {
//      throw new NullPointerException("Car color cannot be null");
//    }
//    
//    this.name = name;
//    this.color = color;
//  }
    
//  
//  /**
//   *
//   * @param name
//   * @param color
//   * 
//   * @note: +/- 10 million calls take 2650 millis.
//   */
//  public Car(String name, Color color) {
//    this.name = Objects.requireNonNull(name, () -> "Car name cannot be null");
//    this.color = Objects.requireNonNull(color, () -> "Car color cannot be ull");
//  }
  
  
//  /**
//   * 
//   * @param name
//   * @param color 
//   * 
//   * @note: +/- 10 million calls take 7 millis. VAT? Mainly cause there're no Exceptions being thrown.
//   */
//  public Car(String name, Color color) {
//    this.name = Objects.requireNonNullElse(name, "No name");
//    this.color = Objects.requireNonNullElseGet(color, () -> new Color(0, 0, 0));
//  } 
  

  /**
   *
   * @param name
   * @param color
   * 
   * @note +/- 10 million calls take 2700 millis.
   */
  public Car(String name, Color color) {
    // +/- 10 million calls take 2500 millis.
    this.name = 
        Utils.requireNonNullElseThrow(
            name,
            new UnsupportedOperationException("Name cannot be set as null"));
    
    // +/- 10 million calls take 2700 millis.
    this.color = 
        Utils.requireNotNullElseThrow(
            color, 
            () -> new UnsupportedOperationException("Color cannot be set as null"));
  }

  /**
   *
   * @param license
   * @param location
   */
  public void assignDriver(String license, Point location) {
    Objects.requireNonNull(license, () -> "License cannot be null");
    Objects.requireNonNull(location, () -> "Location cannot be null");
  }
  
  
  
  
  
  public static void main(String[] args) {
    Instant before = Instant.now();
    
    for (int i = 0; i < 10_000_000; i++) {
      try {
        new Car(null, null);
      } catch (Exception e) {
        
      }
    }

    Instant after = Instant.now();
    
    System.out.println("\n\n\nIt took "+Duration.between(before, after).toMillis()+" millis to complete.");
  }
 
}
