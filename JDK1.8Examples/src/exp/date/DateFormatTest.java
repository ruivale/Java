package exp.date;

import java.util.*;
import java.text.*;


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
public class DateFormatTest {
  // FULL, LONG, MEDIUM, and SHORT.

  // 1271674105000 -> "2010/04/19 11:48:25" but should be 12:48:25 ...
  private static final long lNow = 1271674105000L;

  public DateFormatTest() throws Exception {

    String strDate = "07/23/2007 19:23:34.987654";
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    final Date dateInit = simpleDateFormat.parse(strDate);
    System.out.println("Date="+dateInit.toString()+".");

    final Calendar cal = Calendar.getInstance();
    cal.setTime(dateInit);


    
    cal.setTimeInMillis(lNow);


    //Calendar cal = Calendar.getInstance(new Locale("pt", "PT"));
    Date date = new Date(cal.getTimeInMillis());
    //DateFormat dateFormat = DateFormat.getInstance();
    SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    System.out.println("Instance: "+_dateFormat.format(date)+".");

    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
    System.out.println("FULL: "+dateFormat.format(date)+".");

    dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
    System.out.println("LONG: "+dateFormat.format(date)+".");

    dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    System.out.println("MEDIUM: "+dateFormat.format(date)+".");

    dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
    System.out.println("SHORT: "+dateFormat.format(date)+".");

    System.out.println(" T I M E ");

    dateFormat = DateFormat.getTimeInstance(DateFormat.FULL);
    System.out.println("FULL: "+dateFormat.format(date)+".");

    dateFormat = DateFormat.getTimeInstance(DateFormat.LONG);
    System.out.println("LONG: "+dateFormat.format(date)+".");

    dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM);
    System.out.println("MEDIUM: "+dateFormat.format(date)+".");

    dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
    System.out.println("SHORT: "+dateFormat.format(date)+".");


  }

  public static void main(String[] args) throws Exception{
    DateFormatTest dateformattest = new DateFormatTest();
  }
}
