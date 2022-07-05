package exp.swing.formattedtext.date;

//import com.ent.stv.util.EntJFormattedTextField;


import java.util.*;

import java.text.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
public class MyFormattedDate
    extends JPanel {
//  private static final String DATE_MASK = "####/##/## ##:##:##";
//  private static final String DATE_VALID_CHARS = "0123456789";
//
//  private static final String STR_ZERO = "0";
//  private static final String DATE_DELIM = "/";
//  private static final String HOUR_DELIM = ":";
//  private static final String VALUE_DELIM = " ";
//
//  private static final int YEAR_BOTTOM_LIMIT = 1970;
//  private static final int MONTH_UPPER_LIMIT = 11; // cause the Calendar class.
//  private static final int MONTH_BOTTOM_LIMIT = 0; // cause the Calendar class.
//  private static final int DAY_UPPER_LIMIT = 31;
//  private static final int DAY_BOTTOM_LIMIT = 1;
//  private static final int HOUR_UPPER_LIMIT = 23;
//  private static final int HOUR_BOTTOM_LIMIT = 0;
//  private static final int MIN_UPPER_LIMIT = 59;
//  private static final int MIN_BOTTOM_LIMIT = 0;
//  private static final int SEC_UPPER_LIMIT = 59;
//  private static final int SEC_BOTTOM_LIMIT = 0;
//
//  /**
//   *
//   */
//  public MyFormattedDate() {
//    setLayout(new BorderLayout());
//    final EntJFormattedTextField ejft = new EntJFormattedTextField(DATE_MASK, DATE_VALID_CHARS);
//    final JButton b1 = new JButton("value");
//    final JButton b2 = new JButton("date");
//
//    b1.addActionListener(new ActionListener(){
//      public void actionPerformed(final ActionEvent e){
//        System.out.println("->"+ejft.getValue()+".");
//      }
//    });
//    b2.addActionListener(new ActionListener(){
//      public void actionPerformed(final ActionEvent e){
//        try{
//          int year, month, day, hour, minute, second;
//          String strDateValue = ejft.getValue();
//          year = Integer.parseInt(
//              strDateValue.substring(0, strDateValue.indexOf(DATE_DELIM)));
//          year = (year < YEAR_BOTTOM_LIMIT)? YEAR_BOTTOM_LIMIT: year;
//          strDateValue = strDateValue.substring(
//              strDateValue.indexOf(DATE_DELIM)+1, strDateValue.length());
//
//          // Must decrease, by one, the months values cause the Calendat
//          month = Integer.parseInt(
//              strDateValue.substring(0, strDateValue.indexOf(DATE_DELIM)))-1;
//          month = (month < MONTH_BOTTOM_LIMIT)? MONTH_BOTTOM_LIMIT: month;
//          month = (month > MONTH_UPPER_LIMIT)? MONTH_UPPER_LIMIT: month;
//          strDateValue = strDateValue.substring(
//              strDateValue.indexOf(DATE_DELIM)+1, strDateValue.length());
//
//          day = Integer.parseInt(
//              strDateValue.substring(0, strDateValue.indexOf(VALUE_DELIM)));
//          day = (day < DAY_BOTTOM_LIMIT)? DAY_BOTTOM_LIMIT: day;
//          day = (day > DAY_UPPER_LIMIT)? DAY_UPPER_LIMIT: day;
//          strDateValue = strDateValue.substring(
//              strDateValue.indexOf(VALUE_DELIM)+1, strDateValue.length());
//
//          hour = Integer.parseInt(
//              strDateValue.substring(0, strDateValue.indexOf(HOUR_DELIM)));
//          hour = (hour < HOUR_BOTTOM_LIMIT)? HOUR_BOTTOM_LIMIT: hour;
//          hour = (hour > HOUR_UPPER_LIMIT)? HOUR_UPPER_LIMIT: hour;
//          strDateValue = strDateValue.substring(
//              strDateValue.indexOf(HOUR_DELIM)+1, strDateValue.length());
//
//          minute = Integer.parseInt(
//              strDateValue.substring(0, strDateValue.indexOf(HOUR_DELIM)));
//          minute = (minute < MIN_BOTTOM_LIMIT)? MIN_BOTTOM_LIMIT: minute;
//          minute = (minute > MIN_UPPER_LIMIT)? MIN_UPPER_LIMIT: minute;
//          strDateValue = strDateValue.substring(
//              strDateValue.indexOf(HOUR_DELIM)+1, strDateValue.length());
//
//          second = Integer.parseInt(
//              strDateValue.substring(0, strDateValue.length()));
//          second = (second < SEC_BOTTOM_LIMIT)? SEC_BOTTOM_LIMIT: second;
//          second = (second > SEC_UPPER_LIMIT)? SEC_UPPER_LIMIT: second;
//
//
//          Calendar cal = Calendar.getInstance();
//          cal.set(year, month, day, hour, minute, second);
//
//          final String strRealDate = formatDate(
//              new StringBuffer().append(cal.get(Calendar.YEAR)),
//              // Must encrease, by one, the months values cause the Calendar
//              new StringBuffer().append((cal.get(Calendar.MONTH)+1)),
//              new StringBuffer().append(cal.get(Calendar.DAY_OF_MONTH)),
//              new StringBuffer().append(cal.get(Calendar.HOUR_OF_DAY)),
//              new StringBuffer().append(cal.get(Calendar.MINUTE)),
//              new StringBuffer().append(cal.get(Calendar.SECOND)),
//              DATE_DELIM,
//              HOUR_DELIM,
//              VALUE_DELIM);
//          ejft.setValue(strRealDate);
//
//          System.out.println("Calendar("+strRealDate+")");
//          System.out.println("Date="+cal.getTime().toString()+".");
//
//        }catch(NumberFormatException pe){
//          pe.printStackTrace();
//        }
//      }
//    });
//
//    final JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
//    p1.add(b1);
//    p1.add(b2);
//
//    add(ejft, BorderLayout.CENTER);
//    add(p1, BorderLayout.SOUTH);
//    try {
//      jbInit();
//    } catch (Exception ex) {
//      ex.printStackTrace();
//    }
//
//  }
//
//  public static String formatDate(
//      final StringBuffer year,
//      final StringBuffer month,
//      final StringBuffer day,
//      final StringBuffer hour,
//      final StringBuffer minute,
//      final StringBuffer second,
//      final String strToDelimTheDate,
//      final String strToDelimTheHours,
//      final String strToSeparateTheDateAndHour) {
//
//
//    if (month.length() == 1) {
//      month.insert(0, STR_ZERO);
//    }
//
//    if (day.length() == 1) {
//      day.insert(0, STR_ZERO);
//    }
//
//    if (hour.length() == 1) {
//      hour.insert(0, STR_ZERO);
//    }
//
//    if (minute.length() == 1) {
//      minute.insert(0, STR_ZERO);
//    }
//
//    if (second.length() == 1) {
//      second.insert(0, STR_ZERO);
//    }
//
//    return new StringBuffer().append(year)
//        .append(strToDelimTheDate)
//        .append(month)
//        .append(strToDelimTheDate)
//        .append(day)
//        .append(strToSeparateTheDateAndHour)
//        .append(hour)
//        .append(strToDelimTheHours)
//        .append(minute)
//        .append(strToDelimTheHours)
//        .append(second)
//        .toString();
//  }
//
//
//  public static void main(String[] args) {
//    MyFormattedDate m = new MyFormattedDate();
//    JFrame f = new JFrame("MyFormattedDate");
//    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    f.getContentPane().setLayout(new BorderLayout());
//    f.getContentPane().add(m, BorderLayout.CENTER);
//    f.pack();
//    f.setVisible(true);
//  }
//
//  private void jbInit() throws Exception {
//  }
}
