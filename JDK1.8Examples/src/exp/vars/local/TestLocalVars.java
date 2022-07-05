package exp.vars.local;


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
public class TestLocalVars {

  int[] is = new int[2];

  public TestLocalVars(String s) {
    int i;

    int[] js = new int[2];

    if(s != null){
      i = 0;
    }else{
      i = 1;
    }

    int j = i;

    for (int k = 0; k < is.length; k++) {
      System.out.println("js["+k+"]=" + is[k]);
    }

    for (int k = 0; k < js.length; k++) {
      System.out.println("js["+k+"]=" + js[k]);
    }


  }

  public static void main(String[] args) {
    TestLocalVars testlocalvars = new TestLocalVars("s");
  }
}
