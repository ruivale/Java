/**
 * Java intro.
 * 
 * Classname: pt.intro.java.afirst.Plane
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
 * <p>
 * Description: a simple POJO.
 *
 * Holds information associated with airplanes.
 *
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class Plane {

  /* the plane's maker */
  String strMaker;
  /* the plane's model */
  String strModel;
  /* the plane's number of seats */
  int nSeats;

  /**
   * Default constructor...
   */
  Plane() {    
  }
  
  /**
   * Constructor with all needed information...
   * 
   * @param strMaker the plane's maker.
   * @param strModel the plane's model.
   * @param nSeats the plane's number of seats.
   */
  Plane(String strMaker, String strModel, int nSeats) {
    this.strMaker = strMaker;
    this.strModel = strModel;
    this.nSeats = nSeats;
  }
 
  /**
   * Will print a message signaling the plane's taking off...
   */
  void takeOff() {
    System.out.println("The plane " + this.toString() + " is taking off...");
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return 
        new StringBuffer(32)
          .append(this.strMaker)
          .append("/")
          .append(strModel).append(" with ")
          .append(this.nSeats).append(" seats").toString();
  }
}
