package exp.arrays;

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
public class MyArraysCopy {
  public MyArraysCopy() {
    int[] is = new int[]{1,2,3,4};
    int[] os = new int[]{5,6,7,8,9};
    int[] dest = new int[is.length+os.length];

    System.arraycopy(is,0,dest,0,is.length);
    System.arraycopy(os,0,dest,is.length,os.length);

    System.out.println("");
    for (int i = 0; i < dest.length; i++) {
      System.out.print(dest[i]+",");
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    MyArraysCopy m = new MyArraysCopy();
  }
}
