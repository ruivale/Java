/**
 * <p>
 * Classname:  jdk1_6examples.java.util.collections.MapAndSetTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
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

package jdk1_6examples.java.util.collections;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class MapAndSetTests {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(MapAndSetTests.class.getName());


 /**
  * The MapAndSetTests default constuctor.
  */
  public MapAndSetTests(){
    Map<String, Map<Set<Integer>, String>> map = new HashMap<String, Map<Set<Integer>, String>>(3);


    Map<String, Set<Integer>> mapMainGroupsMapsImgs = new HashMap<String, Set<Integer>>(3);
    Set<Integer> set1 = new HashSet<Integer>(3);
    set1.add(2);
    set1.add(3);
    set1.add(4);
    mapMainGroupsMapsImgs.put("img1.png", set1);

    Set<Integer> set2 = new HashSet<Integer>(2);
    set2.add(2);
    set2.add(3);
    mapMainGroupsMapsImgs.put("img2.png", set2);

    Set<Integer> set4 = new HashSet<Integer>(4);
    set4.add(2);
    set4.add(3);
    set4.add(4);
    set4.add(5);
    mapMainGroupsMapsImgs.put("img4.png", set4);

    System.out.println("mapMainGroupsMapsImgs.containsKey(\"img4.png\"):"+
        mapMainGroupsMapsImgs.containsKey("img4.png"));


    Set<Integer> _set4 = new HashSet<Integer>(4);
    _set4.add(2);
    _set4.add(3);
    _set4.add(4);
    _set4.add(5);
    System.out.println("mapMainGroupsMapsImgs.containsValue(_set4):"+
        mapMainGroupsMapsImgs.containsValue(_set4));

    Set<Integer> set25 = new HashSet<Integer>(2);
    set25.add(2);
    set25.add(5);
    System.out.println("mapMainGroupsMapsImgs.containsValue(set25):"+
        mapMainGroupsMapsImgs.containsValue(set25));

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("MapAndSetTests").append("").toString();
  }

  public static void main(final String[] args){
    final MapAndSetTests clazz = new MapAndSetTests();
  }
}
