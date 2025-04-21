/**
 * Java intro.
 * 
 * Classname: pt.intro.java.afirst.PlaneTest
 * Copyright (C) 2024 RGV
 * Email: ruivale at gmail dot com
 *
 * This is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU Lesser General Public License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */
package pt.intro.java.afirst;

/**
 * <pre>
 * Description: a test case for the Plane class.
 * </pre>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class PlaneTest {

  
  public static void main(String[] args) {
    // creating planes
    // creating "airbus/350"...
    Plane planeAirbus350 = new Plane();
    planeAirbus350.strMaker = "Airbus";
    planeAirbus350.strModel = "350";
    planeAirbus350.nSeats = 375;

    // creating "boeing/787"...
    Plane planeBoeing787 = new Plane("Boeing", "787", 290);

    // invoking "takeOff" method...
    planeAirbus350.takeOff();
    planeBoeing787.takeOff();
  }
}
