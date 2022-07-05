package exp.ifs;


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
public class IfTests {
  public static void main(final String[] args) {
    int i = 1;
    int j = 2;

    i = j++;
    if((i = j++) != 0){
      System.out.println("i is equal to 0");
    }else{
      System.out.println("i is != 0");
    }
  }

}
