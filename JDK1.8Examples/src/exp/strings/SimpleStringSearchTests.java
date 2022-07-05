package exp.strings;


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
public class SimpleStringSearchTests {
  static String STR_NETWORKMAPICON = "networkMapIcon";
  static String symbolsOther = "icon=ahahahahah#networkMapIcon=stv/images/t01_icon.gif#ajax=lklklk.jpg";
  static String STATION_SYMBOLS_OTHER = "garbage";

  public static void main(String[] args) {
    // Search fo the "networkMapIcon" string in the STATION_SYMBOLS_OTHER ...
    if(symbolsOther != null && !symbolsOther.equals("")){
      final int iIndex = symbolsOther.indexOf(STR_NETWORKMAPICON);
      System.out.println("iIndex=" + iIndex);
      if(iIndex > -1){
        symbolsOther = symbolsOther.substring(iIndex, symbolsOther.length());
        System.out.println("symbolsOther=" + symbolsOther);
        STATION_SYMBOLS_OTHER =
            symbolsOther.substring(
                STR_NETWORKMAPICON.length()+1,
                symbolsOther.indexOf("#")>-1?
                  symbolsOther.indexOf("#"):
                  symbolsOther.length());
      }
    }

    System.out.println("STATION_SYMBOLS_OTHER=" + STATION_SYMBOLS_OTHER);

  }
}









