// %1209993855380:%
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exp.arraysvscollections;


import java.util.*;


/**
 * 
 *
 * @author $author$
 * @version $Revision$
  */
public class ArraysVsCollections {
  
  private static final int I_CICLES = 900000;
  /**
   *
   */
  public void arrays() {
    for(int k = 0; k < I_CICLES; k++) {
      int[] arrInt = new int[3];
      arrInt[0]   = 1;
      arrInt[1]   = 2;
      arrInt[2]   = 3;
    } // end for
  } // end arrays()

  /**
   *
   */
  public void collection() {
    for(int k = 0; k < I_CICLES; k++) {
      List intList = new ArrayList(3);
      intList.add(new Integer(1));
      intList.add(new Integer(2));
      intList.add(new Integer(3));
    } // end for
  } // end collection()

  /**
   *
   *
   * @param args 
   */
  public static void main(final String[] args) {
    for(int i = 0; i < 5; i++) {
      ArraysVsCollections obj          = new ArraysVsCollections();
      long                startTimeArr = System.currentTimeMillis();
      obj.arrays();

      long endTimeArr = System.currentTimeMillis();
      long timeArr    = endTimeArr - startTimeArr;
      System.out.println("Time for arrays is : " + timeArr + " ms");

      long startTimeCol = System.currentTimeMillis();
      obj.collection();

      long endTimeCol = System.currentTimeMillis();
      long timeCol    = endTimeCol - startTimeCol;
      System.out.println("Time for collection is : " + timeCol + " ms");
    } // end for
  } // end main()
} // end ArraysVsCollections
