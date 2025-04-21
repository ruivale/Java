/**
 * Java intro.
 * 
 * Classname: pt.intro.java.efifth.EqualsCloneAndHashCode
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


/**
 * <pre>
 * Description:
 * The equals() contract that imposes the following statements: 
 *    Reflexivity: An object is equal to itself, which means that p1.equals(p1) must return true. 
 *    Symmetry: p1.equals(p2) must return the same result (true/false) as p2.equals(p1). 
 *    Transitive: If p1.equals(p2) and p2.equals(p3), then also p1.equals(p3). 
 *    Consistent: Two equal objects must remain equal all the time unless one of them is changed.
 * 
 * As a rule of thumb, in order to respect the equals() and hashCode() contracts, follow two golden rules: 
 *    When equals() is overridden, hashCode() must be overridden as well, and vice versa. 
 *    Use the same identifying attributes for both methods in the same order.
 * 
 * Some of the common mistakes of working with equals() and hashCode(): 
 *    You override equals() and forget to override hashCode() or vice versa (override both or none). 
 *    You use the == operator instead of equals() for comparing object values. 
 *    In equals(), you omit one or more of the following: 
 *        Start by adding the self-check (if (this == obj)...). 
 *        Since no instance should be equal to null, continue by adding null-check (if(obj == null)...). 
 *        Ensure that the instance is what we are expecting (use getClass() or instanceof). 
 *        Finally, after these corner-cases, add field comparisons.
 *    You violate equals() symmetry via inheritance. Assume a class A and a class B extending A and 
 *        adding a new field. The B class overrides the equals() implementation inherited from A but 
 *        forget the new field. Or the B class does not override the equals() implementation from A.
 *    You return a constant from hashCode() instead of a unique hash code per object.
 * 
 * The Object class contains a method named clone(). This method is useful for creating shallow
 * copies (it can be used for deep copies as well). In order to use it, a class should follow the
 * given steps: 
 *    Implement the Cloneable interface (if this interface is not implemented, then 
 *        CloneNotSupportedException will be thrown). 
 *    Override the clone() method (Object.clone() is protected). 
 *    Call super.clone().
 * 
 * Many consider clone and Cloneable broken in Java, largely because the rules for overriding clone
 * are tricky and difficult to get right, according to Joshua Bloch: Object’s clone method is very
 * tricky. It’s based on field copies, and it’s "extra-linguistic." It creates an object without
 * calling a constructor. There are no guarantees that it preserves the invariants established by
 * the constructors. There have been lots of bugs over the years, both in and outside Sun, stemming
 * from the fact that if you just call super.clone repeatedly up the chain until you have cloned an
 * object, you have a shallow copy of the object. The clone generally shares state with the object
 * being cloned. If that state is mutable, you don’t have two independent objects. If you modify
 * one, the other changes as well. And all of a sudden, you get random behavior.
 * Compliant Solution
 *    public class MyClass { // ...
 *        MyClass (MyClass source) { //... } 
 *    }
 * 
 * </pre>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class EqualsCloneAndHashCode {

  public static void main(final String[] args){
    System.out.println("\n\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
    
    Player p1 = new Player(1, "Rafael Nadal");
    Player p2 = new Player(1, "Rafael Nadal");
    
    System.out.println(p1.equals(p2)); 
    System.out.println("p1 hash code: " + p1.hashCode()); 
    System.out.println("p2 hash code: " + p2.hashCode()); 
    
    System.out.println("--------------------------------------------");
    // Little weird...
    Integer n = 127;
    Integer j = 127;
    System.out.println("n(\"Integer n = 127\") is equal to j(\"Integer j = 127\")? "+ (n == j));
    // Little weird...
    Integer n2 = 128;
    Integer j2 = 128;
    System.out.println("n(\"Integer n2 = 128\") is equal to j2(\"Integer j = 128\")? "+ (n2 == j2));

    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n\n");
  }
}
