package exp.i18n.format;

import java.text.MessageFormat;
import java.util.Date;
import java.text.ChoiceFormat;
import java.util.ResourceBundle;


/**
 * <p>Title: </p>
 *
 * <p>Description:
 *
 * </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class MessageFormatTests {
  public MessageFormatTests() {

    /***
    Object[] arguments = {
        new Integer(7),
        new Date(System.currentTimeMillis()),
        "a disturbance in the Force"
    };

    String result = MessageFormat.format(
        "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
        arguments);

    System.out.println("result="+result+".");
    /**/

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***
    System.out.println("--------------------------------------------------");

    Object[] testArgs0 = {new Long(3), "MyDisk"};

    MessageFormat form0 = new MessageFormat(
        "The disk \"{1}\" contains {0} file(s).");

    System.out.println(form0.format(testArgs0));
    /**/

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /**
    System.out.println("--------------------------------------------------");

    MessageFormat form = new MessageFormat("The disk \"{1}\" contains {0}.");
    double[] filelimits = {0,1,2};
    String[] filepart = {"não há fxs","um fx","{0,number} ficheiros"};
    ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
    form.setFormatByArgumentIndex(0, fileform);

    Object[] testArgs = {new Long(523), "MyDisk"};

    System.out.println(form.format(testArgs));
    /**/

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /**/
    ResourceBundle bundle = ResourceBundle.getBundle("exp/i18n/format/property");
    String strPattern = bundle.getString("pattern");

    MessageFormat form = new MessageFormat(strPattern);
    //pattern={0,choice,0#Não há ficheiros|1#Há um fx|1<Há {0,number,integer} fxs no disco \"{1}}

    System.out.println("23,MyDisk: "+form.format(new Object[]{new Long(23), "MyDisk"}));
    System.out.println("1,1111: "+form.format(new Object[]{new Long(1), "1111"}));
    System.out.println("0,0000: "+form.format(new Object[]{new Long(0), "0000"}));

    // OUTPUT:
    //23,MyDisk: Há 23 fxs no disco "MyDisk"
    //1,1111: Há um fx
    //0,0000: Não há ficheiros

    /**/

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***
    ChoiceFormat choiceFormat = new ChoiceFormat(
        "0#are no files |1#is one file |1<are {0,number,integer} files");
    //System.out.println("Formatter Pattern : " + choiceFormat.toPattern());

    //Object[] testArgs4 = {new Long(523), "MyDisk"};

    System.out.println(choiceFormat.format(3));
    /**/

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***
    ChoiceFormat fmt = new ChoiceFormat(
         "-1#is negative| 0#is zero or fraction | 1#is one |1.0<is 1+ |2#is two |2<is more than 2.");
    System.out.println("Formatter Pattern : " + fmt.toPattern());

    System.out.println("Format with -INF : " + fmt.format(Double.NEGATIVE_INFINITY));
    System.out.println("Format with -1.0 : " + fmt.format(-1.0));
    System.out.println("Format with 0 : " + fmt.format(0));
    System.out.println("Format with 0.9 : " + fmt.format(0.9));
    System.out.println("Format with 1.0 : " + fmt.format(1));
    System.out.println("Format with 1.5 : " + fmt.format(1.5));
    System.out.println("Format with 2 : " + fmt.format(2));
    System.out.println("Format with 2.1 : " + fmt.format(2.1));
    System.out.println("Format with NaN : " + fmt.format(Double.NaN));
    System.out.println("Format with +INF : " + fmt.format(Double.POSITIVE_INFINITY));
    /**/
  }

  public static void main(String[] args) {
    MessageFormatTests messageformattests = new MessageFormatTests();
  }
}
