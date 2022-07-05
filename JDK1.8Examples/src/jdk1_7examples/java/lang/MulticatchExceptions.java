/**
 * <p>
 * Classname:  jdk1_7examples.java.lang.MulticatchExceptions
 * </p>
 *
 * <p>Copyright: Copyright (c) 2010 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua EngÂº Frederico Ulrich - Apartado 3081
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

package jdk1_7examples.java.lang;


//import com.sun.istack.internal.NotNull;
//import com.sun.istack.internal.Nullable;
import java.io.File;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class MulticatchExceptions {

  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(MulticatchExceptions.class.getName());


 /**
  * The MulticatchExceptions default constuctor.
  */
  public MulticatchExceptions(){

    try {
      File file = new File("");
      file.delete();

    } catch (NullPointerException nullPointerException) {
      nullPointerException.printStackTrace();
    } catch (SecurityException securityException) {
      securityException.printStackTrace();
    }

    // or in Java 7:

    // NOT YET ... IN THIS BUILD ....

//    try {
//      File file = new File("");
//      file.delete();
//
//    } catch (NullPointerException | SecurityException ex) {
//      ex.printStackTrace();
//    }


  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  @Override
  public String toString(){
    return new StringBuffer("MulticatchExceptions").append("").toString();
  }

  public static void main(final String[] args){
    final MulticatchExceptions clazz = new MulticatchExceptions();
  }
}
