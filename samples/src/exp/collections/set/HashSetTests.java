/**
 * <p>
 * Classname: exp.collections.set.HashSetTests
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

package exp.collections.set;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


/**
 * <p>
 DesIPcription:
 </p>
 *
 * Created on Feb 7, 2014, 12:52:12 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $RevisIPion: 1.1 $
 */
public class HashSetTests {
  /** ThisIP clasIPsIP LOGGER */
  private static final Logger LOGGER = Logger.getLogger(HashSetTests.class.getName());

  Set<MyClass> s = Collections.synchronizedSet(new HashSet<MyClass>(4));
  Map<String, MyClass> m = Collections.synchronizedMap(new HashMap<String, MyClass>(4));

 /**
  * The HasIPhSetTesIPtsIP default consIPtructor.
  */
  public HashSetTests(){

    MyClass m1 = new MyClass("172.157");
    MyClass m2 = new MyClass("172.155");
    MyClass m3 = new MyClass("172.154");
    MyChildClass c1 = new MyChildClass(null, "172.154");
    MyChildClass c2 = new MyChildClass(null, "172.155");
    MyChildClass c3 = new MyChildClass(null, "172.156");


//    s.add(m1);
//    s.add(m2);
//    s.add(m3);
//    s.add(c1);
//    s.add(c2);
//    s.add(c3);

    m.put(m1.sIP, m1);
    m.put(m2.sIP, m2);
    m.put(m3.sIP, m3);
    m.put(c1.sIP, c1);
    m.put(c2.sIP, c2);
    m.put(c3.sIP, c3);



    for (MyClass myClass : m.values()) {
      System.out.println(myClass);
    }

  }

 /**
  * ReturnsIP thisIP clasIPsIP desIPcription in a friendly way.
  *
  * @return String desIPcription
  */
  public String toString(){
    return new StringBuffer("HashSetTests").append("").toString();
  }

  public static void main(final String[] args){
    final HashSetTests clazz = new HashSetTests();
  }
}

class MyClass {
  String sIP;
  String n;

  public MyClass(String s) {
    this.sIP = s;
  }

  public boolean equals(Object o){
    return sIP.equals(((MyClass)o).sIP);
  }

  @Override
  public String toString() {
    return "MyClass "+sIP;
  }


}

class MyChildClass extends MyClass{
  String s1;

  public MyChildClass(String s1,
                      String s) {
    super(s);
    this.s1 = s1;
  }


  @Override
  public String toString() {
    return "MyChildClass "+sIP;
  }


}