/**

Use the modern java.time classes.

ZonedDateTime.now(           // Capture the current moment in the wall-clock time used by the people of a certain region (a time zone).
    ZoneId.systemDefault()   // Get the JVM?s current default time zone. Can change at any moment during runtime. If important, confirm with the user.
)                            // Renders a `ZonedDateTime` object. To see the same moment in UTC, extract a `Instant` object by calling `ZonedDateTime::getInstant`.
You may omit the explicit call to ZoneId.systemDefault.

ZonedDateTime.now()          // Capture the current moment in the JVM?s current default time zone.
Parse your string as a LocalDateTime, and adjust into desired time zone.

LocalDateTime.parse( "2014-02-14T06:04:00:00" )    // Parse a string lacking any indicator of time zone or offset-from-UTC. *Not* a specific point on the timeline.
             .atOffset( ZoneOffset.UTC )           // Apply UTC as we are certain that offset-from-UTC of zero was intended by the supplier of that input string. Returns a `OffsetDateTime` object.
             .atZoneSameInstant(                   // Adjust into another time zone. The `sameInstant` part means the same moment, different wall-clock time. 
                 ZoneId.of( "Africa/Tunis" )       // Specify the particular zone of interest to you.
             )                                     // Returns a `ZonedDateTime` object.
Avoid java.util.Date & .Calendar
These legacy classes are notoriously troublesome. Sun/Oracle added the java.time package in Java 8 
to supplant them. That package was inspired by Joda-Time.

Amongst the legacy classes? problems is this confusing behavior: While a java.util.Date has no time 
zone information, it's toString implementation applies the JVM?s current default time zone when 
generating a String. So it misleads you by seeming to have a time zone when it does not.

java.time
I am trying to convert this string "2014-02-14T06:04:00:00", ?

Your input string lacks any indicator of time zone or offset-from-UTC. So we parse as a LocalDateTime, 
which lacks any concept of zone/offset.

A LocalDateTime does not represent a moment, is not a point on the timeline. The word ?Local? here 
does not mean a specific locality. It means ?no specific locality at all?. Without the context of a 
zone/offset, it has no real meaning.

LocalDateTime ldt = LocalDateTime.parse( "2014-02-14T06:04:00:00" ) ;
? which is in GMT ?

You say you are certain the supplier of that input string intended UTC as the context. We can apply
an offset-from-UTC of zero, or UTC itself, to get an OffsetDateTime object. An OffsetDateTime is a
moment, a point on the timeline. We can specify the ZoneOffset using the constant for UTC, ZoneOffset.UTC.

OffsetDateTime odt = ldt.atOffset( ZoneOffset.UTC ) ;
? to my timezone datetime

Apparently you want to adjust that moment into another time zone, to see the wall-clock time used by 
the people of a particular region. We need to apply a time zone (ZoneId) to get a ZonedDateTime.

ZoneId z = ZoneId.of( "America/Montreal" ) ;
ZonedDateTime zdt = odt.atZone( z ) ;
Instead of specifying a time zone, you can ask your JVM for its current default time zone. 
Beware: The JVM?s current default time zone can be changed at any moment by any code in any thread 
        of any app within that JVM.

ZoneId z = ZoneId.systemDefault() ;
ZonedDateTime zdt = odt.atZone( z ) ;
Point is my application will be used in different geographical locations.

Simply specify your desired/expected time zones explicitly. This is always good practice, in my 
opinion. The default time zone lies outside your control as a programmer, which makes it unreliable.

Specify a proper time zone name in the format of continent/region, such as America/Montreal, 
Africa/Casablanca, or Pacific/Auckland. Never use the 3-4 letter abbreviation such as EST or IST 
as they are not true time zones, not standardized, and not even unique(!).

Another tip: Work, think, store, and exchange in UTC. Forget about your own parochial time zone, 
as translating back-and-forth to your home zone will drive you batty. Think of UTC as the 
One True Time, and other zones/offsets are but mere variations.

Instant instant = Instant.now() ;  // Capture current moment in UTC.

ZoneId zAuckland = ZoneId.of( "Pacific/Auckland" ) ;
ZonedDateTime zdtAuckland = instant.atZone( zAuckland ) ;

ZoneId zKolkata = ZoneId.of( "Asia/Kolkata" ) ;  
ZonedDateTime zdtKolkata = instant.atZone( zKolkata ) ;

ZoneId zCasablanca = ZoneId.of( "Africa/Casablanca" ) ;  
ZonedDateTime zdtCasablanca = instant.atZone( zCasablanca ) ;
There we have four ways ( instant, zdtAuckland, zdtKolkata, zdtCasablanca ) of looking at the very 
same simultaneous moment, the same point on the timeline.

instant.toString(): 2018-05-08T20:55:14.761721Z

zdtAuckland.toString(): 2018-05-09T08:55:14.761721+12:00[Pacific/Auckland]

zdtKolkata.toString(): 2018-05-09T02:25:14.761721+05:30[Asia/Kolkata]

zdtCasablanca.toString(): 2018-05-08T21:55:14.761721+01:00[Africa/Casablanca]

Zone vs Offset
An offset-from-UTC is simply a number of hours, minutes, and seconds. Nothing more, nothing less. 
Any number of time zones may share a particular offset at a particular moment.

A time zone is a history of past, present, and future changes to the offset used by the people of 
a particular region. For example, Daylight Saving Time (DST) is a practice where the people of a 
region (inexplicably) decide to change their offset twice a year.

So a time zone is always preferable to a mere offset. Having a zone allows us to add or subtract 
time in a meaningful way, to account for changes in offset in that region?s history.

About java.time
The java.time framework is built into Java 8 and later. These classes supplant the troublesome old 
legacy date-time classes such as java.util.Date, Calendar, & SimpleDateFormat.

The Joda-Time project, now in maintenance mode, advises migration to the java.time classes.

To learn more, see the Oracle Tutorial. And search Stack Overflow for many examples and explanations. 
Specification is JSR 310.

You may exchange java.time objects directly with your database. Use a JDBC driver compliant with 
JDBC 4.2 or later. No need for strings, no need for java.sql.* classes.

Where to obtain the java.time classes?

Java SE 8, Java SE 9, Java SE 10, and later
Built-in.
Part of the standard Java API with a bundled implementation.
Java 9 adds some minor features and fixes.
Java SE 6 and Java SE 7
Much of the java.time functionality is back-ported to Java 6 & 7 in ThreeTen-Backport.
Android
Later versions of Android bundle implementations of the java.time classes.
For earlier Android (<26), the ThreeTenABP project adapts ThreeTen-Backport (mentioned above). 
See How to use ThreeTenABP?.
The ThreeTen-Extra project extends java.time with additional classes. This project is a proving 
ground for possible future additions to java.time. You may find some useful classes here such as 
Interval, YearWeek, YearQuarter, and more.

Joda-Time
UPDATE: The Joda-Time project is now in maintenance mode. The team advises migration to the 
java.time classes. Skip to java.time section below in this Answer.

The Joda-Time package has good clear support for time zones. Unlike java.util.Date, a
Joda-Time DateTime does know its own assigned time zone. If you fail to specify a time zone, 
the JVM's current default time zone is implicitly assigned.

DateTime dateTime = DateTime.now(); // Applies JVM?s default time zone implicitly.
I recommend against relying on the default time zone implicitly. Doing so leads to confusion and 
errors when doing date-time work.

DateTime dateTime = DateTime.now( DateTimeZone.getDefault() ); // Explicitly using default time zone.
If needed you may assign a time zone.

DateTime dateTimeKolkata = DateTime.now( DateTimeZone.forID( "Asia/Kolkata" ) ); // Specify a time zone.
For server-side work, best practice is to do business logic and database storage in UTC.

DateTime dateTimeUtc = DateTime.now( DateTimeZone.UTC ); // Assign UTC (GMT) time zone.
You can convert from the assigned time zone to another, including the JVM's current default time zone.

DateTime dateTime = dateTimeUtc.withZone( DateTimeZone.getDefault() );
Immutable
For thread-safety, Joda-Time uses immutable objects. Instead of modifying an object, methods such 
as withZone create a new instance based on the original.

Parse String
To parse a String as a DateTime, you must note whether the String includes an offset from UTC and/or 
a time zone. Yours does not. So you must specify a time zone by which to interpret that String. 
If you do not specify, the JVM?s current default time zone will be used during parsing.

In your Question, you said the String represents a date-time in UTC (GMT).

DateTime dateTimeUtc = new DateTime( "2014-02-14T06:04:00:00", DateTimeZone.UTC );
After parsing, you may assign another time zone if needed. Same moment in the time-line of the 
Universe, but shows a different Wall-Clock time.

DateTime dateTimeDefaultZone = dateTimeUtc.withZone( DateTimeZone.getDefault() );
So notice this was a two-step process. First we parsed your String using our external knowledge 
of that String's intended time zone because it lacked internal representation of that time zone or 
offset. Secondly we adjusted the time zone to another (the JVM default zone).

If your String had included an offset of +00:00 or the customary Z, we could have collapsed those 
two steps into one.

DateTime dateTimeDefaultZone = new DateTime(  "2014-02-14T06:04:00:00Z", DateTimeZone.getDefault() ); // Apply time zone adjustment *after* parsing.
Note that this DateTime constructor looks like the one above but is actually quite different. 
This one's time zone argument is applied after parsing, rather than during parsing. 
Here the time zone argument is used to adjust the already-parsed DateTime. 
That Z on the end makes a world of difference.

Source of Default Time Zone
The JVM initially gets its default time zone from the host operating system. But be aware that a 
programmer can override this by:

Pass an argument on command-line when launching the JVM.
Call java.util.TimeZone.setDefault.
Doing this override affects all threads of all apps running in that JVM. So you should know that 
the JVM?s default time zone is usually the same as host OS but not necessarily the same.

shareimprove this answer
edited May 8 '18 at 21:02
answered Jul 17 '14 at 16:06

Basil Bourque
119k32404566
 
 
 */

package jdk8examples.time;

import java.time.*;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class NewJava8TimePkg {

  
  /**
   * 
   */
  private static void differentZones(){

    Instant instant = Instant.now() ;  // Capture current moment in UTC.
    System.out.println(instant.toString());

    Instant instantDefault = Instant.now(Clock.systemDefaultZone()) ;  
    System.out.println(instantDefault.toString());
    
    
    ZoneId zAuckland = ZoneId.of("Pacific/Auckland");
    ZonedDateTime zdtAuckland = instant.atZone(zAuckland);
    System.out.println(zAuckland.toString()+": "+zdtAuckland.toString());

    ZoneId zKolkata = ZoneId.of("Asia/Kolkata");
    ZonedDateTime zdtKolkata = instant.atZone(zKolkata);
    System.out.println(zKolkata.toString()+": "+zdtKolkata.toString());

    ZoneId zCasablanca = ZoneId.of("Africa/Casablanca");
    ZonedDateTime zdtCasablanca = instant.atZone(zCasablanca);   
    System.out.println(zCasablanca.toString()+": "+zdtCasablanca.toString());
 
    ZoneId zLx = ZoneId.of("Europe/Lisbon");
    ZonedDateTime zdtLx = instant.atZone(zLx);   
    System.out.println(zLx.toString()+": "+zdtLx.toString());
    
    ZoneId zLondon = ZoneId.of("Europe/London");
    ZonedDateTime zdtLondon = instant.atZone(zLondon);   
    System.out.println(zLondon.toString()+": "+zdtLondon.toString());
    
    ZoneId zCopenhagem = ZoneId.of("Europe/Copenhagen");
    ZonedDateTime zdtCopenhagen = instant.atZone(zCopenhagem);   
    System.out.println(zCopenhagem.toString()+": "+zdtCopenhagen.toString());
    
  }
  
  
  
  private static void parseString(){
    // JODA
    //DateTime dateTimeUtc = new DateTime( "2014-02-14T06:04:00:00", DateTimeZone.UTC );    
  }
  
  

  public static void main(final String[] args){
    NewJava8TimePkg.differentZones();
  }
}
