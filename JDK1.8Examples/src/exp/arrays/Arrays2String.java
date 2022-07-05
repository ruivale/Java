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
public class Arrays2String {

  int myList [] = {4, 3, 7};

  public Arrays2String() {
    String[][] s = {{"aa","bb"},{"bb","cc"}};

    System.out.println("s="+s.toString()+".");
  }

  public static void main(String a[]){

    String[] args = {"1", "2", "3",};
    String [][] argCopy = new String[2][2];
    int x;
    argCopy[0] = args;
    x = argCopy[0].length;
    for (int y = 0; y < x; y++) {
      System.out.print(" " + argCopy[0][y]);
    }

    String[] argh = {"1", "2", "3", };
    x = argh.length;
    for (int y = 1; y <= x; y++) {
      System.out.print(" " + argh[y]);
    }


    String s1 = args[1];
    String s2 = args[2];
    String s3 = args[3];
    String s4 = args[4];
    System.out.print(" args[2] = " + s2);

    String names [] = new String[5];
    for ( x=0; x < args.length; x++){
      names[x] = args[x];
    }
    System.out.println(names[2]);

    //Arrays2String [][] theDogs = new Arrays2String[3][];
    //System.out.println(theDogs[2][0].toString());
    new Arrays2String();
  }
}
