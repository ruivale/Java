package exp.strings;

//import com.ent.stv.sdm.XMLNodesProperties;


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
public class ReplacingTests {
  public static final String STR =
      "ID1|PS70|TS70|ZS80|JC<com.ent.jniwrapper.visiowave.serverctrl.VWMobileCamerasOper>|";


  String STR_JC = "JC=";
  String STR_PS = "PS";
  String STR_TS = "TS";
  String STR_ZS = "ZS";
  String STR_FS = "FS";
  String STR_ETH$ = "Eth$";
  String STR_VALUES_DELIM = "|";


  public ReplacingTests() {
    System.out.println("STR=" + STR);
    //System.out.println("STR.replaceAll(\"JC<\", \"JC=\")= " + STR.replaceAll("JC<", "JC="));
    final String S = STR.replaceAll("JC<", "JC=");
    System.out.println("S=" + S);
    final String s2 = S.replaceAll(">", "");
    System.out.println("s2=" + s2);


    // remember that here, the string has ... JC= ...
    System.out.println("obtainSpecificConfigParam(s2, \"JC=\")=" + obtainSpecificConfigParam(s2, "JC="));
    System.out.println("obtainSpecificConfigParam(s2, \"PS\")=" + obtainSpecificConfigParam(s2, "PS"));;
    System.out.println("obtainSpecificConfigParam(s2, \"TS\")=" + obtainSpecificConfigParam(s2, "TS"));;
    System.out.println("obtainSpecificConfigParam(s2, \"ZS\")=" + obtainSpecificConfigParam(s2, "ZS"));;
    System.out.println("obtainSpecificConfigParam(s2, \"FS\")=" + obtainSpecificConfigParam(s2, "FS"));;

  }



  private static String obtainSpecificConfigParam(
      final String strSpecConfig,
      final String strToSearchFor){

    String strParam = null;

    // *|JC=java_class|*
    if (strSpecConfig != null &&
        strSpecConfig.indexOf(strToSearchFor) > -1){

      final StringBuffer strBJavaClass = new StringBuffer(strSpecConfig.length());

      try {
        strBJavaClass.append(strSpecConfig.trim());

        strBJavaClass.replace(0,
                     strBJavaClass.length(),
                     strBJavaClass.substring(
                         strBJavaClass.indexOf(strToSearchFor),
                         strBJavaClass.length()));

        strBJavaClass.replace(0,
                     strBJavaClass.length(),
                     strBJavaClass.substring(
                         strToSearchFor.length(),
                        strBJavaClass.indexOf("|")));

        strParam = strBJavaClass.toString();

      } catch (Exception ex) {
        strParam = null;
      }
    }

    return strParam;
  }



  public static void main(String[] args) {
    ReplacingTests replacingtests = new ReplacingTests();
  }
}
