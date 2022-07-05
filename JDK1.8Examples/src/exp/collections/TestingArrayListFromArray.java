/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exp.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author c2334
 */
public class TestingArrayListFromArray {
  final Clazz[] clazzes = {new Clazz(1),new Clazz(2),new Clazz(3),new Clazz(4) };
  final List l = new ArrayList(clazzes.length);
  
  public TestingArrayListFromArray(){
    l.addAll(Arrays.asList(clazzes));
    
    for(int i = 0; i < l.size(); i++) {
      System.out.println("Clazz("+i+") from List: "+((Clazz)l.get(i)).toString());
    }
        
  }
  
  public static void main(String[] args) {
    new TestingArrayListFromArray();
  }
  
  class Clazz {
    int i = 0;
    
    Clazz(int i){
      this.i = i;
    }
    
    public String toString(){
      return "Clazz->"+i;
    }
  }

}

