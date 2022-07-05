package exp.calendar;

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
public class CalendarSetsTest {
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();
    cal.set(1970, 0, 1, 1, 1, 1); //
    System.out.println("1970, 0, 1, 0, 0, 0.getTimeInMillis()=" + cal.getTimeInMillis());
    System.out.println(cal.toString());
    cal.set(1971, 0, 1, 0, 0, 0);   //
    System.out.println("1971, 0, 1, 0, 0, 0.getTimeInMillis()=" + cal.getTimeInMillis());
    cal.set(1971, 0, 1, 0, 0, 1);   //
    System.out.println("1971, 0, 1, 0, 0, 1.getTimeInMillis()=" + cal.getTimeInMillis());

    System.out.println("cal.getTimeInMillis()=" + cal.getTimeInMillis());


    System.out.println("cal=" + cal.toString());
    System.out.println("cal.get(Calendar.YEAR)=" + cal.get(Calendar.YEAR));
    System.out.println("cal.get(Calendar.MONTH)=" + cal.get(Calendar.MONTH));
    System.out.println("cal.get(Calendar.DAY_OF_MONTH)=" + cal.get(Calendar.DAY_OF_MONTH));
    System.out.println("cal.get(Calendar.HOUR_OF_DAY)=" + cal.get(Calendar.HOUR_OF_DAY));
    System.out.println("cal.get(Calendar.MINUTE)=" + cal.get(Calendar.MINUTE));
    System.out.println("cal.get(Calendar.SECOND)=" + cal.get(Calendar.SECOND));

    cal.getInstance();
    cal.set(Calendar.YEAR, 1970);
    cal.set(Calendar.MONTH, 0);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    System.out.println("cal with independent sets:"+cal.getTimeInMillis()+".");
    System.out.println("cal=" + cal.toString());
    System.out.println("cal.get(Calendar.YEAR)=" + cal.get(Calendar.YEAR));
    System.out.println("cal.get(Calendar.MONTH)=" + cal.get(Calendar.MONTH));
    System.out.println("cal.get(Calendar.DAY_OF_MONTH)=" + cal.get(Calendar.DAY_OF_MONTH));
    System.out.println("cal.get(Calendar.HOUR_OF_DAY)=" + cal.get(Calendar.HOUR_OF_DAY));
    System.out.println("cal.get(Calendar.MINUTE)=" + cal.get(Calendar.MINUTE));
    System.out.println("cal.get(Calendar.SECOND)=" + cal.get(Calendar.SECOND));
    System.out.println("cal.get(Calendar.MILLI)=" + cal.get(Calendar.MILLISECOND));

    Date date = new Date();
    date.setTime(cal.getTimeInMillis());
    System.out.println("Date:"+date.toString()+".");
    System.out.println("DataFormat:"+DateFormat.getDateInstance().format(date)+".");

    System.out.println("cal.getTime().getTime();=" + cal.getTime().getTime());

    GregorianCalendar gregCal = new GregorianCalendar(1970,0,1,0,0,0);
    System.out.println("--------------------------------------------------");
    System.out.println("gregCal millis:"+gregCal.getTimeInMillis()+".");
    System.out.println("gregCal:"+gregCal.toString()+".");
    date = new Date();
    date.setTime(gregCal.getTimeInMillis());
    System.out.println("Date:"+date.toString()+".");
    System.out.println("DataFormat:"+DateFormat.getDateInstance().format(date)+".");


    System.out.println("--------------------------------------------------");
    long gregCalL   =  gregCal.getTimeInMillis() +
                   gregCal.getTimeZone().getOffset(gregCal.getTimeInMillis() );
    System.out.println("gregCalL:"+gregCalL+".");



    System.out.println("--------------------------------------------------");
    cal = Calendar.getInstance();
    System.out.println("System.currentTimeMillis():"+System.currentTimeMillis()+
                       " now.getTimeInMillis()=" + cal.getTimeInMillis());

  }
}



















