package exp.reflection.method;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class Auxiliar {

  public Auxiliar() {
  }

  public void doA() {
    System.out.println("NADINHA doA");
  }

  public void doA(String a) {
    System.out.println("a: " + a);
  }

  public void doA(String a, String b) {
    System.out.println("a: " + a);
    System.out.println("b: " + b);
  }


  public void doA(String a, int b) {
    System.out.println("a: " + a);
    System.out.println("int b: " + b);
  }

  //Just to test with the PInt
  public void doA(String a, String b, String c) {
    System.out.println("a: " + a);
    System.out.println("b: " + b);
    System.out.println("c: " + c);
  }
  //Just to test with the PInt
  public void doA(String a, int b, int c) {
    System.out.println("a: " + a);
    System.out.println("int b: " + b);
    System.out.println("int c: " + c);
  }

  public void methodToInvoke() {
    System.out.println("NADINHA");
  }

  public void methodToInvoke(String a) {
    System.out.println(a);
  }

  public void methodToInvoke(String[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }

  public void methodToInvoke(String a, String b) {
    System.out.println(a + b);
  }

  public int methodToInvoke(int a, int b) {
    System.out.println("o a: " + a + "; o b: " + b + ".");
    return a + b;
  }

  public void methodToInvoke(String a, String b, String c) {
    System.out.println(a + b + c);
  }

///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////

}
