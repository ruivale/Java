package exp.date.doubles;


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
public class DoubleFromDateFromMS
    extends java.util.Date {

  //int[] rgMonthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  static int rgMonthDays[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304,
      334, 365};
  static double HALF_SECOND = (1.0 / 172800.0);


  public DoubleFromDateFromMS() {
    super(108, 0, 3, 14, 0, 0);

    // trying:  39450.583333333336 ==> 2008/01/03 14:00:00

    System.out.println("Date:" + toLocaleString());
    System.out.println("39450.583333333336 must equals " + toDouble());



    this.setYear(103);
    this.setMonth(6);
    this.setDate(28);
    this.setHours(12);
    this.setMinutes(4);
    this.setSeconds(22);
    System.out.println("Date:" + toLocaleString()+" Double:" + toDouble());


    this.setYear(82);
    this.setMonth(10);
    this.setDate(28);
    this.setHours(0);
    this.setMinutes(34);
    this.setSeconds(56);
    System.out.println("Date:" + toLocaleString()+" Double:" + toDouble());


    this.setYear(110);
    this.setMonth(4);
    this.setDate(17);
    this.setHours(23);
    this.setMinutes(58);
    this.setSeconds(59);
    System.out.println("Date:" + toLocaleString()+" Double:" + toDouble());


    this.setYear(104);
    this.setMonth(3);
    this.setDate(1);
    this.setHours(12);
    this.setMinutes(4);
    this.setSeconds(22);
    System.out.println("Date:" + toLocaleString()+" Double:" + toDouble());

    this.setYear(104);
    this.setMonth(1);
    this.setDate(23);
    this.setHours(12);
    this.setMinutes(4);
    this.setSeconds(22);
    System.out.println("Date:" + toLocaleString()+" Double:" + toDouble());

    this.setYear(100);
    this.setMonth(3);
    this.setDate(1);
    this.setHours(12);
    this.setMinutes(4);
    this.setSeconds(22);
    System.out.println("Date:" + toLocaleString()+" Double:" + toDouble());




    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    double d = 39450.583333333336;
    setDate(d);
    System.out.println(d + "=" + toLocaleString());

    d = 39451.583333333336;
    setDate(d);
    System.out.println(d + "=" + toLocaleString());

    d = 37830.50303240741;
    setDate(d);
    System.out.println(d + "=" + toLocaleString());

    d = 38077.50303240741;
    setDate(d);
    System.out.println(d + "=" + toLocaleString());

    d = 36616.50303240741;
    setDate(d);
    System.out.println(d + "=" + toLocaleString());


  }


  public void setDate(double dtSrc) {

    // source code copied from MFC 4.21 and modified

    long nDays; // Number of days since Dec. 30, 1899
    long nDaysAbsolute; // Number of days since 1/1/0
    long nSecsInDay; // Time in seconds since midnight
    long nMinutesInDay; // Minutes in day
    long n400Years; // Number of 400 year increments since 1/1/0
    long n400Century; // Century within 400 year block (0,1,2 or 3)
    long n4Years; // Number of 4 year increments since 1/1/0
    long n4Day; // Day within 4 year block
    //  (0 is 1/1/yr1, 1460 is 12/31/yr4)
    long n4Yr; // Year within 4 year block (0,1,2 or 3)
    boolean bLeap4 = true; // TRUE if 4 year block includes leap year

    // values in terms of year month date.
    int tm_sec;
    int tm_min;
    int tm_hour;
    int tm_mday;
    int tm_mon;
    int tm_year;
    int tm_wday;
    int tm_yday;

    double dblDate = dtSrc; // temporary serial date

    // If a valid date, then this conversion should not overflow
    nDays = (long) dblDate;

    // Round to the second
    dblDate += ((dtSrc > 0.0) ? HALF_SECOND : -HALF_SECOND);

    // Add days from 1/1/0 to 12/30/1899
    nDaysAbsolute = (long) dblDate + 693959L;
    dblDate = Math.abs(dblDate);
    nSecsInDay = (long) ((dblDate - Math.floor(dblDate)) * 86400.);

    // Leap years every 4 yrs except centuries not multiples of 400.
    n400Years = (long) (nDaysAbsolute / 146097L);
    // Set nDaysAbsolute to day within 400-year block

    nDaysAbsolute %= 146097L;
    // -1 because first century has extra day
    n400Century = (long) ((nDaysAbsolute - 1) / 36524L);
    // Non-leap century


    if (n400Century != 0) {

      // Set nDaysAbsolute to day within centurY
      nDaysAbsolute = (nDaysAbsolute - 1) % 36524L;

      // +1 because 1st 4 year increment has 1460 days
      n4Years = (long) ((nDaysAbsolute + 1) / 1461L);

      if (n4Years != 0) {
        n4Day = (long) ((nDaysAbsolute + 1) % 1461L);
      } else {
        bLeap4 = false;
        n4Day = (long) nDaysAbsolute;
      }
    } else {
      // Leap century - not special case!
      n4Years = (long) (nDaysAbsolute / 1461L);
      n4Day = (long) (nDaysAbsolute % 1461L);
    }

    if (bLeap4) {
      // -1 because first year has 366 days
      n4Yr = (n4Day - 1) / 365;

      if (n4Yr != 0) {
        n4Day = (n4Day - 1) % 365;
      }

    } else {
      n4Yr = n4Day / 365;
      n4Day %= 365;
    }

    tm_year = (int) (n400Years * 400 + n400Century * 100 +
                     n4Years * 4 + n4Yr);

    // Handle leap year: before, on, and after Feb. 29.
    if (n4Yr == 0 && bLeap4 && n4Day == 59) {
      /* Feb. 29 */
      tm_mon = 2;
      tm_mday = 29;

    } else {
      if (n4Yr == 0 && bLeap4 && n4Day >= 59) {
        --n4Day;
      }

      tm_mon = 1;

      // Make n4DaY a 1-based day of non-leap year and compute
      //  month/day for everything but Feb. 29.
      ++n4Day;
      // Month number always >= n/32, so save some loop time */

     /***/
     for (tm_mon = (int) (((n4Day != 5) ? 1 : 0) + 1);
                   n4Day > rgMonthDays[tm_mon];
                   tm_mon++) {
       ;
     }
      /**/



      tm_mday = (int) (n4Day - rgMonthDays[tm_mon - 1]);
    }

    if (nSecsInDay == 0) {
      tm_hour = tm_min = tm_sec = 0;
    } else {
      tm_sec = (int) (nSecsInDay % 60L);
      nMinutesInDay = nSecsInDay / 60L;
      tm_min = (int) (nMinutesInDay % 60);
      tm_hour = (int) (nMinutesInDay / 60);
    }

    setYear(tm_year - 1900);
    setMonth(tm_mon - 1);
    super.setDate(tm_mday); // resolves ambiguity
    // between OleDate.setDate and
    // java.util.Date.setDate
    setHours(tm_hour);
    setMinutes(tm_min);
    setSeconds(tm_sec);

  }

  public double toDouble() {
    // source code copied from MFC 4.21 and modified.

    int wYear = getYear() + 1900;
    int wMonth = getMonth() + 1;
    int wDay = getDate();
    int wHour = getHours();
    int wMinute = getMinutes();
    int wSecond = getSeconds();

    //  Check for leap year and set the number of days in the month
    boolean bLeapYear = ((wYear & 3) == 0) &&
                        ((wYear % 100) != 0 || (wYear % 400) == 0);

    //int nDaysInMonth =
      //  rgMonthDays[wMonth] - rgMonthDays[wMonth - 1] +
        //((bLeapYear && wDay == 29 && wMonth == 2) ? 1 : 0);

    // Cache the date in days and time in fractional days
    long nDate;
    double dblTime;

    //It is a valid date; make Jan 1, 1AD be 1
    nDate = wYear * 365L + wYear / 4 - wYear / 100 + wYear / 400 +
            rgMonthDays[wMonth - 1] + (wDay);

    //  If leap year and it's before March, subtract 1:
    if (wMonth <= 2 && bLeapYear) {
      --nDate;
    }

    //  Offset so that 12/30/1899 is 0
    nDate -= 693959L;

    dblTime = (((long) wHour * 3600L) + // hrs in seconds
               ((long) wMinute * 60L) + // mins in seconds
               ((long) wSecond)) / 86400.;

    double dtDest = (double) nDate +
                    ((nDate >= 0) ? dblTime : -dblTime);

    return dtDest;
  }

  public static void main(String[] args) {
    DoubleFromDateFromMS d = new DoubleFromDateFromMS();
  }
}
