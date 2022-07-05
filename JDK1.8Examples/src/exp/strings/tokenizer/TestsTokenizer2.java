package exp.strings.tokenizer;


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
public class TestsTokenizer2 {
  public TestsTokenizer2() {
    String speconfig = "JC<com.ent.jniwrapper.visiowave.display.VWDisplay>|";

      StringBuffer strBSpecConfig = new StringBuffer(speconfig).replace(
          0,
          3,
          "");

      final int    strBLength = strBSpecConfig.indexOf("|")+1;//strBSpecConfig.length();
      strBSpecConfig   = strBSpecConfig.replace(
          strBLength - 2,
          strBLength,
          "");

      System.out.println("final="+strBSpecConfig.toString()+".");
  }

  public TestsTokenizer2(boolean token){
    String speconfig =
      "JC<com.ent.jniwrapper.visiowave.display.VWDisplay>|xpto=eruyere|xpti=dfjbdjfbssdf";




  }

  public static void main(String[] args) {
    TestsTokenizer2 teststokenizer2 = new TestsTokenizer2();
  }
}


