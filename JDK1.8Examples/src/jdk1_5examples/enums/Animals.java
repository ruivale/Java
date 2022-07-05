package jdk1_5examples.enums;


/**
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
enum Animal {
  Fish(0) {
   public String speak() {
     return "Gurgle";
   }
  },

  Cat(4) {
   public String speak() {
     return "Meow";
   }
  },

  Owl(2) {
   public String speak() {
     return "Hoo";
   }
  };

  private int numLegs;

  private Animal(int numLegs) {
   this.numLegs = numLegs;
  }

  public int getNumberOfLegs() {
   return numLegs;
  }

  abstract public String speak();
}

public class Animals{

  public static void main(String[] a) {
   Animal animal = Animal.Owl;

   System.out.println(animal.getNumberOfLegs()); //"2"
   System.out.println(animal.speak()); // "Hoo"

  }

}
