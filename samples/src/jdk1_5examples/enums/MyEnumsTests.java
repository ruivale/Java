/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_5examples.enums;

/**
 *
 * @author c2334
 */
public class MyEnumsTests {
  // Where to display the module menu. 
  // In some TLC menu or in it?s own module menu
  enum TlcMenus{
    module, inoss, view, system, consult, help
  }
  MyEnumsTests.TlcMenus tlcMenu = MyEnumsTests.TlcMenus.module;
      
  MyEnumsTests.TlcMenus getTlcMenu(){
    return tlcMenu;
  }
  
  
  public static void main(String[] args) {
    MyEnumsTests m = new MyEnumsTests();
    System.out.println("Menu: " + m.getTlcMenu()); 
    
  }

}
