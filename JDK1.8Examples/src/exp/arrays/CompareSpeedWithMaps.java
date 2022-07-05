package exp.arrays;


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
public class CompareSpeedWithMaps {
  private static final int NBR_ITENS = 499999;

  Map map = new HashMap(NBR_ITENS);

  String[][] ss;

  long before, after;

  /**
   *
   */
  public CompareSpeedWithMaps() {
    ss = new String[NBR_ITENS][2];

    for (int i = 0; i < NBR_ITENS; i++) {
      ss[i][0] = ""+1;
      ss[i][1] = ss[i][0]+ss[i][0]+ss[i][0]+ss[i][0]+ss[i][0];

      map.put(ss[i][0], ss[i][1]);
    }

    test();
  }

  /**
   *
   */
  private void test(){
    String str = ""+377988;

    before = System.currentTimeMillis();
    for (int i = 0; i < NBR_ITENS; i++) {
      if(ss[i][0].equals(str)){
        break;
      }
    }
    after = System.currentTimeMillis() - before;
    System.out.println("Search in array="+after+".");

    before = System.currentTimeMillis();
    map.get(str);
    after = System.currentTimeMillis() - before;
    System.out.println("Search in Map="+after+".");

  }

  public static void main(String[] args) {
    CompareSpeedWithMaps comparespeedwithmaps = new CompareSpeedWithMaps();
  }

  class Property{
    Property(){

    }
  }
}
