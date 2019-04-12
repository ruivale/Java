/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exp.strings;

/**
 *
 * @author c2334
 */
public class StringTests {
  public static void main(String[] args) {
    String s = "Hoje"+" amanhã"+ " depois";
    
    StringBuffer sb = new StringBuffer("hoje").append(" manahã").append(" depois");
    
    int x = 1;
    int y = 2;
    int z = 3;
    
    String s0 = "->"+x+", "+y+", "+z+".";
  }

}
