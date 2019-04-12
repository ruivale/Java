package exp.iteraction.forinfor;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ForInsideFor {
  public ForInsideFor() {

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 5; j++) {
        System.out.println("j="+j);
        if(j==3)break;
      }
      System.out.println("i="+i);
    }


  }
  public static void main(String[] args) {
    ForInsideFor forInsideFor1 = new ForInsideFor();
  }

}
