package jdk1_6examples.javax.util.calendar;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class DisplayNames {
  public static void main(String args[]) {
    Calendar now = Calendar.getInstance();
   // Locale locale = Locale.getDefault();
     Locale locale = Locale.ITALIAN;
    Map<String, Integer> names = now.getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
    System.out.println(names);
    String name = now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
    System.out.printf("Today is a %s.%n", name);
  }
}
