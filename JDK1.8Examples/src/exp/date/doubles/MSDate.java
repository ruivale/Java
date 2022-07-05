package exp.date.doubles;


import java.util.Date;
import java.util.Calendar;


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

public class MSDate
    extends Date {
  private final static int MILLISECONDS_IN_A_DAY = 86400000;
  private final static long MS_BASE_DATE = -2209147200000L; // December 29, 1899, 23:00:00.000 GMT.

  private final static double D_DIFF = 0.16665618056140374 /*+ +1.1574069503694773E-5*/;

  /**
   * Allocates a MSDate object and initializes it so that it represents the time
   * at which it was allocated, measured to the nearest millisecond.
   */
  public MSDate() {
    super();
  }

  /**
   * Allocates a MSDate object and initializes it to represent the specified
   * number of milliseconds since the standard base time known as "the epoch",
   * namely January 1, 1970, 00:00:00 GMT.
   *
   * @param date long
   */
  public MSDate(long date) {
    super(date);
  }

  /**
   * Allocates a MSDate object using the given MS date.
   *
   * @param MSDate double
   */
  public MSDate(double MSDate) {
    this.setMSDate(MSDate);
  }

  /**
   * Sets this MSDate object to represent the MS date (double) provided.
   *
   * @param MSDate double
   */
  public void setMSDate(double MSDate) {
    double dMilliseconds;

    // Convert MS Date, less the DIFF, (double) to the number of milliseconds
    // since January 1, 1970, 00:00:00.000 GMT.
    dMilliseconds = (MSDate - D_DIFF) * MILLISECONDS_IN_A_DAY;

    // Add MS's base date.
    dMilliseconds += MS_BASE_DATE;

    super.setTime(Math.round(dMilliseconds));
  }

  /**
   * Returns a MS date (double) that represented by this MSDate object.
   *
   * @return double
   */
  public double getMSDate() {
    double dMSDate = 0;
    double dMilliseconds;

    // Get the number of milliseconds since January 1, 1970, 00:00:00.000 GMT.
    dMilliseconds = this.getTime();

    // Subtract MS's base date.
    dMilliseconds -= MS_BASE_DATE;

    // Convert milliseconds back to a MS date (double).
    dMSDate = dMilliseconds  / MILLISECONDS_IN_A_DAY;

    // Return the MS date plus the diff
    return dMSDate + D_DIFF;
  }

   /*
   3/Jan/2008 14:00:00 - 39450.583332789356
   4/Dez/1998 12:45:56 - 36133.531897604174
   28/Jul/2003 12:04:23 - 37830.461376770836
   28/Nov/1982 0:34:56 - 30283.024258715282
   17/Mai/2010 23:58:59 - 40315.95762677084
   */

  public static void main(final String[] args) {
    double i = 39450.583333333336;
    MSDate m = new MSDate(i);
    System.out.println(i+"=" + m.toLocaleString());

    i = 39450.416674803244;
    m = new MSDate(i);
    System.out.println(i+"=" + m.toLocaleString());

    i = 39450.583323564824;
    m = new MSDate(i);
    System.out.println(i+"=" + m.toLocaleString());

    Calendar cal = Calendar.getInstance();
    cal.set(2008, 0, 3, 14, 0, 0);
    MSDate m2 = new MSDate(cal.getTimeInMillis());
    System.out.println(m2.toLocaleString() + " - " + m2.getMSDate());

    double d2 = m2.getMSDate();

    cal = Calendar.getInstance();
    cal.set(2008, 0, 3, 13, 59, 59);
    m2 = new MSDate(cal.getTimeInMillis());
    System.out.println(m2.toLocaleString() + " - " + m2.getMSDate());

    System.out.println("\n+"+(d2-m2.getMSDate())+"\n");


    cal = Calendar.getInstance();
    cal.set(2003, 6, 28, 12, 4, 22);
    m2 = new MSDate(cal.getTimeInMillis());
    System.out.println(m2.toLocaleString() + " - " + m2.getMSDate());

    cal = Calendar.getInstance();
    cal.set(1982, 10, 28, 0, 34, 56);
    m2 = new MSDate(cal.getTimeInMillis());
    System.out.println(m2.toLocaleString() + " - " + m2.getMSDate());

    cal = Calendar.getInstance();
    cal.set(2010, 4, 17, 23, 58, 59);
    m2 = new MSDate(cal.getTimeInMillis());
    System.out.println(m2.toLocaleString() + " - " + m2.getMSDate());


  }

}
