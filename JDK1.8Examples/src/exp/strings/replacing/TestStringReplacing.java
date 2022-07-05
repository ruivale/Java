package exp.strings.replacing;

//import pt.efacec.se.inoss.stv.network.TlcEquipInfo;


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
public class TestStringReplacing {
//
//  private static final String STR_NAME_IN_EXTAPP = "«NAME»";
//  private static final String STR_CODEC_RX = "TYr";
//  private static final String STR_CODEC_TX = "TYt";
//  private static final String STR_CODEC_TYPE_VALUE_ATTR = "->";
//  private static final String STR_CODEC_TYPE_SEPARATOR = ";";
//  private static final String STR_VARS_INIT = "«";
//  private static final String STR_CODEC_VARS_CLOSING = ";»";
//  private static final String STR_SPACE_SUBSTITUTE = "+";
//
//  final static String s =
//      "table=«TYr->monitors;TYt->video_ports;»&single_id=«NAME»";
//
//  /**
//   *
//   * @param strExtApp String
//   * @param type String
//   * @param name String
//   * @param genconfig String
//   */
//  public TestStringReplacing(String strExtApp,String type, String name, String genconfig) {
//
//    System.out.println("Before=" + strExtApp+".");
//
//    // Searching for the TAGS ...
//    String strEA = strExtApp.replaceAll(
//      STR_NAME_IN_EXTAPP,
//      name.replaceAll(" ", STR_SPACE_SUBSTITUTE).trim());
//
//    if(type.equals(TlcEquipInfo.CODEC_TYPE)){
//      final String strGenConfig = genconfig;
//      if(strGenConfig != null && !strGenConfig.equals("")){
//        // Obtaining the string to replace at the end of the execution ...
//        String strCodecTypeStrToReplace = "";
//        String strCodecRx = new StringBuffer(STR_VARS_INIT).append(STR_CODEC_RX).toString();
//        String strCodecTx = new StringBuffer(STR_VARS_INIT).append(STR_CODEC_TX).toString();
//        if(strEA.indexOf(strCodecRx) > -1){
//          // ${TYr->monitors;TYt->video_ports;}
//          strCodecTypeStrToReplace =
//              strEA.substring(strEA.indexOf(strCodecRx),
//                              strEA.length());
//
//          strCodecTypeStrToReplace = strCodecTypeStrToReplace.substring(
//            0,
//            strCodecTypeStrToReplace.indexOf(STR_CODEC_VARS_CLOSING)+STR_CODEC_VARS_CLOSING.length());
//
//        }else if(strEA.indexOf(strCodecTx) > -1){
//          // ${TYt->video_ports;TYr->monitors;}
//          strCodecTypeStrToReplace =
//              strEA.substring(strEA.indexOf(strCodecTx),
//                              strEA.length());
//
//          strCodecTypeStrToReplace = strCodecTypeStrToReplace.substring(
//            0,
//            strCodecTypeStrToReplace.indexOf(STR_CODEC_VARS_CLOSING)+STR_CODEC_VARS_CLOSING.length());
//        }
//
//        String strRealValue = "";
//
//        if(strGenConfig.indexOf(STR_CODEC_RX) > -1){
//          // Rx CODEC
//          String strRxValue =
//              strEA.substring(strGenConfig.indexOf(STR_CODEC_RX)+STR_CODEC_RX.length(),
//              strEA.length());
//          // Now we have, f.i.:TYr->monitors;TYt->video_ports;}&single_id=${NAME}
//
//          strRxValue = strRxValue.substring(
//              strRxValue.indexOf(STR_CODEC_TYPE_VALUE_ATTR)+STR_CODEC_TYPE_VALUE_ATTR.length(),
//              strRxValue.indexOf(STR_CODEC_TYPE_SEPARATOR));
//          // ... and now we've monitors, the real value to be used ...
//
//          strRealValue = strRxValue;
//
//        }else if(strGenConfig.indexOf(STR_CODEC_TX) > -1){
//          // Tx CODEC
//          String strTxValue =
//              strEA.substring(strGenConfig.indexOf(STR_CODEC_TX)+STR_CODEC_TX.length(),
//              strEA.length());
//          // Now we have, f.i.:TYt->video_ports;}&single_id=${NAME}
//
//          strTxValue = strTxValue.substring(
//              strTxValue.indexOf(STR_CODEC_TYPE_VALUE_ATTR)+STR_CODEC_TYPE_VALUE_ATTR.length(),
//              strTxValue.indexOf(STR_CODEC_TYPE_SEPARATOR));
//          // ... and now we've video_ports, the real value to be used ...
//
//          strRealValue = strTxValue;
//
//        }
//
//        strEA = strEA.replaceAll(strCodecTypeStrToReplace, strRealValue);
//      }
//    }
//
//    System.out.println("After=" + strEA+".");
//  }
//
//  public static void main(String[] args) {
//    TestStringReplacing t = new TestStringReplacing(s, "codec", "101 C1", "TYr|POs|");
//  }
}
