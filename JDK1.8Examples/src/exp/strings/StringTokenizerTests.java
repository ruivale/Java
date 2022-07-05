package exp.strings;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.*;
import java.lang.*;

public class StringTokenizerTests {

  public StringTokenizerTests() {

    /*
            String str = "EQUIPALARM=images/stv/linha2_station_equip_alarm.gif;OTHERA=a.gif;OTHERB=b.gif";
        //String str = "EQUIPALARM=images/stv/linha2_station_equip_alarm.gif";


        int i = 0;
        String s = "";
        StringTokenizer st = new StringTokenizer(str, ";");
        while(st.hasMoreTokens()){
          s = st.nextToken();
          // Found some EQUIPALARM
          if(s.indexOf("EQUIPALARM") > -1){
            s = s.substring(s.indexOf('=')+1, s.length());
          }
          System.out.println("-> "+(i++)+": "+s+"");
        }
     */



    /*
        String s = "";
        int indexOf = str.indexOf(";");
        if(indexOf > 0){
          s = str.substring(str.indexOf("EQUIPALARM"), indexOf);
        }else{
          s =  str;
        }

        System.out.println("\nSSSSSSSSSSSS= "+s+".\n");

        s = "";
        indexOf = str0.indexOf(";");
        if(indexOf > 0){
          s = str0.substring(str0.indexOf("OTHERA"), indexOf);
        }else{
          s =  str0;
        }

        System.out.println("\nSSSSSSSSSSSS= "+s+".\n");
     */

    String strAux = "";
    String s1 = "1|2|3|4";
    String s2 = "1|2||4";
    String s3 = "1|2|3";
    String s4 = "1|2";

    System.out.println("s1="+s1.substring(s1.indexOf("|", 2)+1)+". ("+s1+")");
    System.out.println("s2="+s2.substring(s2.indexOf("|", 2)+1)+". ("+s2+")");
    System.out.println("s3="+s3.substring(s3.indexOf("|", 2)+1)+". ("+s3+")");
    System.out.println("s4="+s4.substring(s4.indexOf("|", 2)+1)+". ("+s4+")");

    StringTokenizer t = new StringTokenizer(s1, "|");
    int j = t.countTokens();
    while (t.hasMoreElements()) {
      System.out.println("->" + t.nextElement() + ".");
    }

if (j > 2) {
  strAux = s1.substring(s1.indexOf("|", 2)+1);
} else {
  strAux = "";
}


  }

  public static void main(String[] args) {
    StringTokenizerTests stringTokenizerTests1 = new StringTokenizerTests();
  }
}
