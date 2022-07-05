/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_6examples.inoss.tests;

/**
 *
 * 
 */
public interface ConfigPropertyInt {
  enum ConfigPropertyType{
    add, edit, remove
  }
  enum ConfigPropertyObject{
    group, station, zone, equip
  }
}
