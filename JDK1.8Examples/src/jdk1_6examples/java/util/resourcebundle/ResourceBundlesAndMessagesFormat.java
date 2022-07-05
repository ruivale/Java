/**
 * <p>
 * Classname:  jdk1_6examples.java.util.resourcebundle.ResourceBundlesAndMessagesFormat
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

package jdk1_6examples.java.util.resourcebundle;


import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ResourceBundlesAndMessagesFormat {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(ResourceBundlesAndMessagesFormat.class.getName());


 /**
  * The ResourceBundlesAndMessagesFormat default constuctor.
  */
  public ResourceBundlesAndMessagesFormat(){

    final ResourceBundle bundle =
        ResourceBundle.getBundle(
            "jdk1_6examples.java.util.resourcebundle.ResourceBundleMsgFormat");

    //pattern={0,choice,0#No files|1#One file|1<There're {0,number,integer} files in the disk \"{1}}
    String strPattern = bundle.getString("pattern");

    MessageFormat form = new MessageFormat(strPattern);
    
    System.out.println(
        "23,MyDisk: " + form.format(new Object[]{new Long(23), "MyDisk"}));
    System.out.println(
        "1,1111: " + form.format(new Object[]{new Long(1), "1111"}));
    System.out.println(
        "0,0000: " + form.format(new Object[]{new Long(0), "0000"}));

//////////////////////////////////////////////////////////////////////////////////////
System.out.println("------------------------------------------------------");

    strPattern = bundle.getString("message");
    form = new MessageFormat(strPattern);

    System.out.println(
        form.format(
            new Object[]{"CCTV", "Sandyfor Depot", "Black/Depot"}));

//////////////////////////////////////////////////////////////////////////////////////
System.out.println("------------------------------------------------------");

    strPattern = bundle.getString("message2");
    form = new MessageFormat(strPattern);

    System.out.println(
        form.format(
            new Object[]{"0000", "11111", ""}));


//////////////////////////////////////////////////////////////////////////////////////
System.out.println("------------------------------------------------------");

    strPattern = bundle.getString("messageEmpty");
    form = new MessageFormat(strPattern);

    System.out.println(form.format(null));


//////////////////////////////////////////////////////////////////////////////////////
System.out.println("------------------------------------------------------");

    strPattern = bundle.getString("auto.conn");
    form = new MessageFormat(strPattern);

    System.out.println(form.format(
        new Object[]{new Integer(0), "station xpto", "equip xptb", "station target", "target equip"}));
    System.out.println(form.format(
        new Object[]{new Integer(1), "station xpto", "equip xptb", "group_name"}));




  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("ResourceBundlesAndMessagesFormat").append("").toString();
  }

  public static void main(final String[] args){
    final ResourceBundlesAndMessagesFormat clazz = new ResourceBundlesAndMessagesFormat();
  }
}
