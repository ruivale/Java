/**
 * <p>
 * Classname: exp.collections.convertions.ConvertMapValuesToList
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2018 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
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
package exp.collections.convertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ConvertMapValuesToList {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER
    = Logger.getLogger(ConvertMapValuesToList.class.getName());

  /**
   * The ConvertMapValuesToList default constuctor.
   */
  public ConvertMapValuesToList() {
  }

  /**
   * 
   * @param map
   * @return 
   */
  public List<String> convertMapValuesToList(Map<Integer, String> map) {
    List<String> listOfValues = new ArrayList<>(map.values());
    return listOfValues;
  }
  
  
  

  public static void main(final String[] args) {
    final ConvertMapValuesToList clazz = new ConvertMapValuesToList();
  }
}
