/**
 * <p>
 * Classname:  jdk1_6examples.lang.StringConcatPerformanceTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
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

package jdk1_6examples.lang;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class StringConcatPerformanceTests {
  private static final int N = 10000;

  public StringConcatPerformanceTests(){
    usingSimpleConcat();
    //usingStringBuffer();
    //usingStringBuilder();
  }

  /**
   * ~0 millis [10000 cicles]
   */
  private void usingStringBuilder() {
    final StringBuilder sb = new StringBuilder();
    final long before = System.currentTimeMillis();

    for (int i = 0; i < N; i++) {
      sb.append("some string");
    }

    final long after = System.currentTimeMillis();

    System.out.println("usingStringBuilder: it took "+(after-before)+" millis.");
  }

  /**
   * ~0 millis [10000 cicles]
   */
  private void usingStringBuffer() {
    final StringBuffer sb = new StringBuffer();
    final long before = System.currentTimeMillis();

    for (int i = 0; i < N; i++) {
      sb.append("some string");
    }

    final long after = System.currentTimeMillis();

    System.out.println("usingStringBuffer: it took "+(after-before)+" millis.");
  }

  /**
   * ~4000 millis [10000 cicles]
   */
  private void usingSimpleConcat() {
    String str = "";
    final long before = System.currentTimeMillis();

    for (int i = 0; i < N; i++) {
      str += "some string";
    }

    final long after = System.currentTimeMillis();

    System.out.println("usingStringBuilder: it took "+(after-before)+" millis.");
  }


  public static void main(final String[] args){
    final StringConcatPerformanceTests clazz = new StringConcatPerformanceTests();
  }
}
