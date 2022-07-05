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
public class NewForeachWithNullList {
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    list.add("sdkfjhkdf");
    list = null;
    
    for( String string : (list != null?list: new ArrayList<String>())) {
      System.out.println("String: "+string); 
    }
  }
}
