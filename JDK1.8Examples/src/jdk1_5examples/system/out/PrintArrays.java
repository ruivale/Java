package jdk1_5examples.system.out;

import java.util.Arrays;


/**
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public class PrintArrays {

  String[][] sss = {{"1","2"},{"3","4"},{"5","6"}};
  String [] ss = {"11","22","33"};

  public PrintArrays() {
    System.out.println("sss="+Arrays.deepToString(sss)+".");
    System.out.println("ss="+Arrays.toString(ss)+".");
  }


  public static void main(String[] args) {
    PrintArrays printarrays = new PrintArrays();
  }


}
