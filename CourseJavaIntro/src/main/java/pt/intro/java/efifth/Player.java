/**
 * Java intro.
 * 
 * Classname: pt.intro.java.efifth.Player
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
package pt.intro.java.efifth;

import java.util.Objects;


/**
 * <pre>
 * Description:
 * </pre>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class Player implements Cloneable {

  /* .. */
  private int memoizedHashCode = 0;
  /* .. */
  private final int id;
  /* .. */
  private final String name;

  
  
  /**
   * Creates a new Player instance with the given id and name.
   *
   * @param id the Player identification
   * @param name the Player name
   */
  public Player(int id, String name) {
    this.id = id;
    this.name = name;
  }
  
  /**
   * Creates a new Player instance with the same state as the given Player.
   *
   * @param player the Player to copy from
   */
  public Player(Player player) {
    this.id = player.id;
    this.name = player.name;
    this.memoizedHashCode = player.memoizedHashCode;
  }

  
  /**
   * Indicates whether some other object is "equal to" this one.
   * 
   * @param obj the reference object with which to compare
   * @return the result of the comparison
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (obj == null) {
      return false;
    }
    
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    
    final Player other = (Player) obj;
    
    if (this.id != other.id) {
      return false;
    }
    
    return Objects.equals(this.name, other.name);
  }

  
  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hash
   * tables such as those provided by HashMap.
   * 
   * @return the hash code
   */
  @Override
  public int hashCode() {
    
    if(this.memoizedHashCode != 0){
      // only because both, id and name, are final, otherwise, the hash code should always be calculated
      return this.memoizedHashCode;
    }
    
    int hash = 7;
    hash = 53 * hash + this.id;
    hash = 53 * hash + Objects.hashCode(this.name);
    
    this.memoizedHashCode = hash;
    
    return hash;
  }
  
  
  /**
   * Many consider clone and Cloneable broken in Java, largely because the rules for overriding 
   * clone are tricky and difficult to get right...
   * 
   * @return a clone of this instance
   * @throws CloneNotSupportedException if the object's class does not support the Cloneable interface.
   */
  @Override
  public Player clone() throws CloneNotSupportedException {
    return (Player) super.clone();
  }
}
