package exp.collections.map.hashtable;

import ilog.views.sdm.model.IlvDefaultSDMNode;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestHT {
  public TestHT() {

    java.util.Map ht = new java.util.HashMap(5, 2);

    ht.put("A", new String[]{"a", "aa"});
    ht.put("B", new String[]{"b", "bb"});
    ht.put("C", new String[]{"c", "cc"});

    java.util.Set setValues = ht.entrySet();
    java.util.Iterator iter = setValues.iterator();
    java.util.Set setKeys = ht.keySet();
    java.util.Iterator iterKeys = setKeys.iterator();
    String[] strs;
    String s;

    System.out.println("##############################################");
    while(iterKeys.hasNext()){
      //ilvNode = (IlvDefaultSDMNode)iterKeys.next();
      //System.out.println("ilvNode.getProperty(\"name\")=" + ilvNode.getProperty("name")+".");

      //System.out.println("\tvalue=" + iter.next().toString()+".");

      Object obj = iter.next();
      if(obj instanceof String){
        System.out.println("string");

      }else if(obj instanceof String[]){
        System.out.println("string[]");
      }

      //strs = (String[])iter.next();
      //System.out.println("[0]="+strs[0]+", [1]="+strs[1]+".");
    }
    System.out.println("##############################################");

  }

  public static void main(String[] args) {
    TestHT testht = new TestHT();
  }
}
