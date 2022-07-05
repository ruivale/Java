package exp.date.doubles;

import java.util.Date;


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
public class DateLongTests {

/*
   Data: new DateTime(2008, 1, 3, 14, 0, 0, DateTimeKind.Utc)
   1199368800000

   Data: new DateTime(2006, 3, 30, 12, 5, 34, DateTimeKind.Utc);
   1143720334000

   Data: new DateTime(2003, 6, 14, 11, 34, 46, DateTimeKind.Utc);
   1055590486000
*/
  public DateLongTests() {
    long l = Long.parseLong("1199368800000");
    Date date = new Date(Date.UTC(108, 0, 3, 14, 0, 0));
    System.out.println(date.getTime()+":"+date.toLocaleString());

    l = Long.parseLong("1143720334000");
    date = new Date(Date.UTC(106, 2, 30, 12, 5, 34));
    System.out.println(date.getTime()+":"+date.toLocaleString());

    l = Long.parseLong("1055590486000");
    date = new Date(Date.UTC(103, 5, 14, 11, 34, 46));
    System.out.println(date.getTime()+":"+date.toLocaleString());
  }

  public static void main(String[] args) {
    DateLongTests datelongtests = new DateLongTests();
  }
}
