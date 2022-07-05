package jdk8examples.time;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GetCurrentDateTimeZone {

  public static void main(String[] args) {
    
//    {
//      final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
//      final String strTZ = dateFormat.getTimeZone().getID();
//      final Calendar cal = Calendar.getInstance();
//      System.out.println(strTZ + ": " + dateFormat.format(cal.getTime()));
//    }
   
    
    {
      final String strTZ = "Europe/Copenhagen";
      System.setProperty("user.timezone", strTZ);    
      final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
      final Calendar cal = Calendar.getInstance();
      System.out.println(strTZ + ": " + dateFormat.format(cal.getTime()));
    }

    
//    {
//      final String strTZ = "Europe/Lisbon"; 
//      System.setProperty("user.timezone", strTZ);    
//      final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
//      final Calendar cal = Calendar.getInstance();
//      System.out.println(strTZ + ": " + dateFormat.format(cal.getTime()));    
//    }

    
  }
}
