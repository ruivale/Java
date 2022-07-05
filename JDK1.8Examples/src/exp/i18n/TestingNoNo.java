/**
 * <p>
 * Classname: exp.i18n.TestingNoNo
 * </p>
 *
 * <p>Copyright: Copyright (c) 2014 Efacec Engenharia e Sistemas, S.A.
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

package exp.i18n;


import java.awt.Rectangle;
import java.util.Locale;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Nov 4, 2014, 11:25:27 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class TestingNoNo {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(TestingNoNo.class.getName());

  public static void main(final String[] args){
    Locale[] locales = {
      new Locale("en"),
      new Locale("en", "GB"),
      new Locale("en", "US"),
      new Locale("no", "NO"),
      new Locale("no", "NO", "NY"),
    };

    for (Locale locale : locales) {
      System.out.println("locale.getLanguage():"+locale.getLanguage());
      System.out.println("locale.getCountry():"+locale.getCountry());
      System.out.println("locale.getVariant():"+locale.getVariant());
      System.out.println("locale.getDisplayName():"+locale.getDisplayName());
      System.out.println("locale.getDisplayCountry():"+locale.getDisplayCountry());
      System.out.println("locale.getDisplayLanguage():"+locale.getDisplayLanguage());
      System.out.println("locale.getDisplayName():"+locale.getDisplayName());
      System.out.println("locale.getDisplayVariant():"+locale.getDisplayVariant());
      System.out.println("locale.getISO3Country():"+locale.getISO3Country());
      System.out.println("locale.getISO3Language():"+locale.getISO3Language());
      System.out.println("locale.toLanguageTag():"+locale.toLanguageTag());
      System.out.println("---------------------------------------------------");
    }

    Rectangle r = new Rectangle(1921, 0, 1920, 1080);
    System.out.println("Rectangle: "+r.toString());

  }
}
