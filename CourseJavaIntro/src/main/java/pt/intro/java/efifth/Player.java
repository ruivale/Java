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
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class Player implements Cloneable {

  /* .. */
  private static final long serialVersionUID = 0L;

  /* .. */
  private int memoizedHashCode = 0;
  /* .. */
  private final int id;
  /* .. */
  private final String name;

  
  
  /**
   *
   * @param id
   * @param name
   */
  public Player(int id, String name) {
    this.id = id;
    this.name = name;
  }
  
  /**
   * 
   * @param player 
   */
  public Player(Player player) {
    this.id = player.id;
    this.name = player.name;
  }

  
  /**
   * Indicates whether some other object is "equal to" this one.
   * 
   * @param obj
   * @return 
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
   * 
   * @return 
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
   * @return
   * @throws CloneNotSupportedException 
   */
  @Override
  public Player clone() throws CloneNotSupportedException {
    return (Player) super.clone();
  }
}
