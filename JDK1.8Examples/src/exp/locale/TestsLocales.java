package exp.locale;

import java.util.*;
import java.text.DateFormat;

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
public class TestsLocales {

  public TestsLocales() {
    Locale l = new Locale("pt", "PT");
    System.out.println("l.getDisplayName()=" + l.getDisplayName());
    System.out.println("l.getCountry()=" + l.getCountry());
    System.out.println("l.getDisplayLanguage()=" + l.getDisplayLanguage());
    System.out.println("l.getDisplayVariant()=" + l.getDisplayVariant());
    System.out.println("l.getISO3Country()=" + l.getISO3Country());


    /**
    Locale[] ls = l.getAvailableLocales();
    for (int i = 0; i < ls.length; i++) {
      System.out.println("ls["+i+"]="+ls[i].toString()+".");
    }
    ls = Calendar.getAvailableLocales();
    for (int i = 0; i < ls.length; i++) {
      System.out.println("Calendar ls["+i+"]="+ls[i].toString()+".");
    }
    */

    Calendar cal = Calendar.getInstance(l);
    System.out.println("Cal="+cal.toString()+".");
    System.out.println("Cal="+cal.getTime().toString()+".");

    DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, l);
    df.setCalendar(cal);
    df.format(cal.getTime());
    System.out.println("df.toString()=" + df.toString());

    df = DateFormat.getDateInstance();
    System.out.println(df.format(cal.getTime()) );

    try {
      Date d = df.parse(DateFormat.getDateInstance().format(cal.getTime()));
      System.out.println("Date="+d.toString()+".");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String s[]){
    new TestsLocales();
  }
}
