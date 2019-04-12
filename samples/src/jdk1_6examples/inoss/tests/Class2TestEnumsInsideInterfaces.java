/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_6examples.inoss.tests;

/**
 *
 * @author c2334
 */
public class Class2TestEnumsInsideInterfaces {

  public Class2TestEnumsInsideInterfaces() {
    ConfigPropertyInt.ConfigPropertyType configPropType =
        ConfigPropertyInt.ConfigPropertyType.add;
    ConfigPropertyInt.ConfigPropertyObject configPropObj = 
        ConfigPropertyInt.ConfigPropertyObject.station;

    switch(configPropType){
      case add:
        System.out.println("add: "+configPropObj.equip.ordinal()); 
        break;
      case edit:
        System.out.println("edit"); 
        break;
      case remove:
        System.out.println("remove"); 
        break;
    }
    switch(configPropObj){
      case group:
        System.out.println("group"); 
        break;
      case station:
        System.out.println("station"); 
        break;
      case zone:
        System.out.println("zone"); 
        break;
    }
  }
  
  
  public static void main(String[] args) {
    new Class2TestEnumsInsideInterfaces();
  }
}
