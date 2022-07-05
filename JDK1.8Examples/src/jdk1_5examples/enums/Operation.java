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

public class Operation{
  public static void main(String s[]){
    final double x = 12.9;
    final double y = 3.9;

    for(Operations ops: Operations.values()){
      System.out.printf("%f %s %f = %f%n", x, ops, y, ops.eval(x,y));
    }
  }
}

enum Operations {
  PLUS, MINUS, TIMES, DIVIDE;

  double eval(final double x, final double y){
    switch(this){
      case PLUS:   return x+y;
      case MINUS:  return x-y;
      case TIMES:  return x*y;
      case DIVIDE: return x/y;
    }
    throw new AssertionError("Unknown op: "+this+".");
  }

}
