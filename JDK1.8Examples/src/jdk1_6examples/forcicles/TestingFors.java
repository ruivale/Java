/**
 * <p>
 * Classname:  jdk1_6examples.forcicles.TestingFors
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

package jdk1_6examples.forcicles;



/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class TestingFors {
 
 /**
  * The TestingFors default constuctor.
  */
  public TestingFors(){

    for (int i = 0; i < 5; i++) {
      System.out.println("i:"+i);
    }
    System.out.println("-----------------");
    for (int i = 0; i < 5; ++i) {
      System.out.println("i:"+i);
    }
    System.out.println("-----------------");
    System.out.println("-----------------");
    int i = 0;
    while(i < 5){
      System.out.println("i++:"+(i++));
    }
    System.out.println("-----------------");
    i = 0;
    while(i < 5){
      System.out.println("++i:"+(++i));
    }

  }

  public static void main(final String[] args){
    final TestingFors clazz = new TestingFors();
  }
}
