/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_6examples.generics.newforcicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author c2334
 */
public class GenericsAndNewForCicle {
  public static void main(String[] args) {
    //final Set setKeys = mapModulesNetwork.keySet(); // the modules IDs
    //final Iterator iterKeys = setKeys.iterator();
    final Map<Integer, List<Group>> mapGroups = 
        new HashMap<Integer, List<Group>>();
    
    List<Group> listGroup1 = new ArrayList<Group>(3);
    listGroup1.add(new Group(11, "UM UM"));
    listGroup1.add(new Group(12, "UM DOIS"));
    listGroup1.add(new Group(13, "UM TRÊS"));
    mapGroups.put(Integer.valueOf(1), listGroup1);

    List<Group> listGroup2 = new ArrayList<Group>(3);
    listGroup2.add(new Group(21, "DOIS UM"));
    listGroup2.add(new Group(22, "DOIS DOIS"));
    listGroup2.add(new Group(23, "DOIS TRÊS"));
    mapGroups.put(Integer.valueOf(2), listGroup2);

    List<Group> listGroup3 = new ArrayList<Group>(3);
    listGroup3.add(new Group(31, "TRÊS UM"));
    listGroup3.add(new Group(32, "TRÊS DOIS"));
    listGroup3.add(new Group(33, "TRÊS TRÊS"));
    mapGroups.put(Integer.valueOf(3), listGroup3);
    
    for(List<Group> listGroups : mapGroups.values()) {
      for(Group g: listGroups){        
        System.out.println("Group("+g.id+", "+g.name+")"); 
      }
    }

  }
}

class Group {
  int id = -1;
  String name;

  public Group(int id, String name) {
    this.id = id;
    this.name = name;
  }
    
}
