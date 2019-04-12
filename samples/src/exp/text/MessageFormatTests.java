/**
 * <p>
 * Classname:  exp.text.MessageFormatTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2018 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package exp.text;


import java.text.MessageFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class MessageFormatTests {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(MessageFormatTests.class.getName());


 /**
  * The MessageFormatTests default constuctor.
  */
  public MessageFormatTests(){

    final String[][] strPatterns = {
      {"Le utilisateur suivant a été ajouté avec succès: {0}", "fr-FR"},
      {"L'utilisateur suivant a été ajouté d'avec succès: {0}", "fr-FR"},
      {"L'utilisateur suivant a été ajouté du avec succès: {0}", "fr-FR"},
      {"Le utilisateur suivant a été ajouté d'avec succès: {0}", "fr-FR"},
      {"The following user was successfully added: {0}", "en-GB"}
    };
    final Object[] objs = new Object[]{"lixo"};
    
    for(String[] strPattern: strPatterns){
      String strFrmtMsg;
      
      try {
        final MessageFormat form = 
            new MessageFormat(
                strPattern[0].replaceAll("'", "''")
                //, Locale.forLanguageTag(strPattern[1])
            );
        strFrmtMsg = form.format(objs);

      } catch (Exception e) {
        strFrmtMsg = "";

        LOGGER.log(Level.SEVERE, "Cannot format the pattern("+strPattern[0]+").", e);
      }
    
      System.out.println(strPattern[0]+" - FrmtMsg: "+strFrmtMsg);
    }
  }


  public static void main(final String[] args){
    final MessageFormatTests clazz = new MessageFormatTests();
  }
}
