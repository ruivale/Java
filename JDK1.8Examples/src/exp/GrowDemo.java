package exp;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */


     public class GrowDemo {
         static final int N = 1000000;
         static final String str = "testing";

         public static void main(String args[]) {
             StringBuffer sb = new StringBuffer();
             //StringBuffer sb = new StringBuffer(N * str.length());

             long start = System.currentTimeMillis();
             for (int i = 1; i <= N; i++) {
                 sb.append(str);
             }
             long elapsed = System.currentTimeMillis() - start;
             System.out.println(elapsed);
         }
     }