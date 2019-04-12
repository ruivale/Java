/**
 * <p>
 * Classname: exp.dynamic.DynamicCasting
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
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
package exp.dynamic;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class DynamicCasting {

  
  /**
   * 
   * @param <T>
   * @param clazz
   * @param items
   * @return 
   */
  static <T> List<T> filter(Class<T> clazz, List<?> items) {
    return items.stream()
        .filter(clazz::isInstance)
        .map(clazz::cast)
        .collect(Collectors.toList());
  }

  
  
  
  public static void main(final String[] args) {
    
    final List<Object> list = new ArrayList<>(7);
    list.add("1");
    list.add(22);
    list.add("2");
    list.add(32);
    list.add("4");

    DynamicCasting.filter(String.class, list).forEach(System.out::println);
  }

}
