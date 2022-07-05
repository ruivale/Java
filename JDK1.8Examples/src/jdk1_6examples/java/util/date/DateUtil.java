/**
 * <p>
 * Classname:  jdk1_6examples.java.util.date.DateUtil
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.java.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;




/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class DateUtil {

  private static final String S_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

  /**
   * 
   * @param timestamp
   * @return
   * @throws Exception
   */
 public static Calendar parseTimestamp(String timestamp) throws Exception {

   SimpleDateFormat sdf = new SimpleDateFormat(S_DATE_FORMAT, Locale.US);
   //SimpleDateFormat sdf = new SimpleDateFormat(S_DATE_FORMAT, new Locale("pt", "PT"));
   Date d = sdf.parse(timestamp);
   Calendar cal = Calendar.getInstance();
   cal.setTime(d);
   return cal;
 }

 
 public static void main (String a[]) throws Exception{
   String timestampToParse = "1998/09/23 17:39:35";
   System.out.println("Timestamp : " +  timestampToParse);

   SimpleDateFormat sdf = new java.text.SimpleDateFormat(S_DATE_FORMAT);
   final Calendar cal = parseTimestamp(timestampToParse);
   System.out.println("Calendar [formatted]: " + sdf.format(cal.getTime()));

   System.out.println("Calendar [raw]: " +
       cal.get(Calendar.YEAR) + "/"+
       (cal.get(Calendar.MONTH)+1) + "/"+
       cal.get(Calendar.DAY_OF_MONTH) + " "+
       cal.get(Calendar.HOUR_OF_DAY) + ":"+
       cal.get(Calendar.MINUTE) + ":"+
       cal.get(Calendar.SECOND));

 }
}
