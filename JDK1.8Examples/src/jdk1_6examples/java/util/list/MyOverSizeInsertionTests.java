/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_6examples.java.util.list;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author c2334
 */
public class MyOverSizeInsertionTests {
  public static void main(String[] args) {
    List l = new ArrayList(5);
    
    l.add("1");
    l.add(0, "0");
    
    l.add(6 > l.size()? l.size():6, "6");
    
    System.out.println("->"+l.toString()); 
  }
}
