package exp.stringbuffer;


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
public class StringBufferTests {

  public StringBufferTests() {

    //EQUIP_SPECIFICCONFIG = speconfig;
    String speconfig = "JC<com.ent.jniwrapper.visiowave.storageaccessctrl.VWStorageAccessController>|";

    if (speconfig != null && !speconfig.equals("")) {
      StringBuffer strBSpecConfig = new StringBuffer(speconfig).replace(0, 3,
          "");
      final int strBLength = strBSpecConfig.length();
      strBSpecConfig = strBSpecConfig.replace(strBLength - 2, strBLength, "");

      System.out.println("strBSpecConfig = " + strBSpecConfig.toString());;

    }
  }

  public static void main(String[] args) {
    StringBufferTests stringbuffertests = new StringBufferTests();
  }
}
