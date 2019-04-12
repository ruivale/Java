/**
 * <p>
 * Classname:  jdk1_6examples.java.lang.calendar.JavaCalendarTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_6examples.java.lang.calendar;

import java.util.Calendar;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class JavaCalendarTests {

  public static void main(final String[] args) {


    Calendar start = Calendar.getInstance();
    //start = Calendar.getInstance();
    start.set(Calendar.YEAR, 2009);
    start.set(Calendar.MONTH, 4);
    start.set(Calendar.DAY_OF_MONTH, 7);
    start.set(Calendar.HOUR_OF_DAY, 1);
    start.set(Calendar.MINUTE, 33);
    start.set(Calendar.SECOND, 40);
    System.out.println("start:"+start.getTime()+"");


    Calendar cal2 = Calendar.getInstance();
    cal2.set(Calendar.YEAR, 2000);
    cal2.set(Calendar.MONTH, 0);
    cal2.set(Calendar.DAY_OF_MONTH, 1);
    cal2.set(Calendar.HOUR_OF_DAY, 0);
    cal2.set(Calendar.MINUTE, 0);
    cal2.set(Calendar.SECOND, 0);
    System.out.println("cal:  "+cal2.getTime()+"");

    Calendar cal1 = Calendar.getInstance();
    cal1.set(Calendar.YEAR, 2009);
    cal1.set(Calendar.MONTH, 4);
    cal1.set(Calendar.DAY_OF_MONTH, 7);
    cal1.set(Calendar.HOUR_OF_DAY, 1);
    cal1.set(Calendar.MINUTE, 33);
    cal1.set(Calendar.SECOND, 40);
    System.out.println("cal:  "+cal1.getTime()+"");


  }
}
