/**
 * <p>
 * Classname: exp.util.map.MapOfIntsPerformance
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
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

package exp.util.map;


import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on May 6, 2014, 4:26:32 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class MapOfIntsPerformance {
  private static final boolean IS_TIME_UNITS_IN_MILLIS = false;
  private static final int I_SIZE = 10;

  private final Map<String, String> map = new HashMap<>(I_SIZE);
  private int iRec;
  private String strRec;

 /**
  * The MapOfIntsPerformance default constructor.
  */
  public MapOfIntsPerformance(){

    for (int i = 0; i < I_SIZE; i++) {
      map.put(Integer.toString(i), Integer.toString(i));
    }

    this.iRec = 1;
    this.strRec = "1";
  }

  /**
   *
   * @param strValue
   * @return
   */
  private int obtainIntValueFromMap(final String strValue){
    int iValue;

    try {
      iValue = Integer.parseInt(map.get(strValue));
    } catch (NumberFormatException e) {
      iValue = -1;
      e.printStackTrace();
    }

    return iValue;
  }

  /**
   *
   * @param strValue
   * @return
   */
  private String obtainStringValueFromMap(final String strValue){
    return map.get(strValue);
  }

  /**
   *
   * @return
   */
  private int getIntRec(){
    return this.iRec;
  }

  /**
   *
   * @return
   */
  private String getStringRec() {
    return this.strRec;
  }


  public static void main(final String[] args){
    final MapOfIntsPerformance clazz = new MapOfIntsPerformance();

    long before = IS_TIME_UNITS_IN_MILLIS? System.currentTimeMillis(): System.nanoTime();
    for (int i = 0; i < I_SIZE; i++) {
      clazz.obtainIntValueFromMap("3");
    }
    long after = IS_TIME_UNITS_IN_MILLIS? System.currentTimeMillis(): System.nanoTime();
    System.out.println("Obtaining the string from the Map and convert it to int took "+(after-before)+
                       " time "+(IS_TIME_UNITS_IN_MILLIS? "millis.": "nanos."));

    before = IS_TIME_UNITS_IN_MILLIS? System.currentTimeMillis(): System.nanoTime();
    for (int i = 0; i < I_SIZE; i++) {
      clazz.getIntRec();
    }
    after = IS_TIME_UNITS_IN_MILLIS? System.currentTimeMillis(): System.nanoTime();
    System.out.println("Obtaining the rec int directly took "+(after-before)+
                       " time "+(IS_TIME_UNITS_IN_MILLIS? "millis.": "nanos."));


    before = IS_TIME_UNITS_IN_MILLIS? System.currentTimeMillis(): System.nanoTime();
    for (int i = 0; i < I_SIZE; i++) {
      clazz.obtainStringValueFromMap("3");
    }
    after = IS_TIME_UNITS_IN_MILLIS? System.currentTimeMillis(): System.nanoTime();
    System.out.println("Obtaining the string from the Map took "+(after-before)+
                       " time "+(IS_TIME_UNITS_IN_MILLIS? "millis.": "nanos."));

    before = IS_TIME_UNITS_IN_MILLIS? System.currentTimeMillis(): System.nanoTime();
    for (int i = 0; i < I_SIZE; i++) {
      clazz.getStringRec();
    }
    after = IS_TIME_UNITS_IN_MILLIS? System.currentTimeMillis(): System.nanoTime();
    System.out.println("Obtaining the rec string directly took "+(after-before)+
                       " time "+(IS_TIME_UNITS_IN_MILLIS? "millis.": "nanos."));

  }
}
