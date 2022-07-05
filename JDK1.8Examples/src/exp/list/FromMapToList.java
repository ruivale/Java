package exp.list;

import java.util.*;

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
public class FromMapToList {

  int intNbrOfItens = 150000;

  Map m = new HashMap(intNbrOfItens);
  List l = new Vector(intNbrOfItens);

  public FromMapToList() {
    for (int i = 0; i < intNbrOfItens; i++) {
      m.put(new Integer(i), new StringBuffer("item nbr ").append(i).toString());
    }
    for (int i = 0; i < intNbrOfItens; i++) {
      //System.out.println("item nbr "+i+"= "+m.get(new Integer(i))+".");
    }
    System.out.println("Transforming ...");

    l.addAll(m.values());

    for (int i = 0; i < intNbrOfItens; i++) {
      //System.out.println("FROM LIST item nbr "+i+"= "+l.get(i)+".");
    }
    System.out.println("FINISH");

    long before, after;

    // list
    before = System.currentTimeMillis();
    getFromList("item nbr 500");
    after = System.currentTimeMillis() - before;
    System.out.println("list search:"+after+".");

    // map
    before = System.currentTimeMillis();
    m.get(new Integer(200));
    after = System.currentTimeMillis() - before;
    System.out.println("map search:"+after+".");

  }


  private String getFromList(String strObject){
    int intnbr = l.size();
    for (int i = 0; i < intnbr; i++) {
      if(strObject.equals(""+l.get(i))){
        break;

      }
    }
    return "";
  }

  public static void main(String[] args) {
    FromMapToList f = new FromMapToList();
  }
}

















