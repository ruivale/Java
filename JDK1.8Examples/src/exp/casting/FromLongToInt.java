/**
 * <p>
 * Classname:  exp.casting.FromLongToInt
 * </p>
 *
 * <p>Copyright: Copyright (c) 2026 EFACEC SE
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

package exp.casting;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class FromLongToInt {

  private int memoizedHashCode = 0;


  /**
   * 
   * @param l
   * @return 
   */
  private static int cast(final long l) {
    final int i = (int)l;
    
    return i;
  }

  public static void main(final String[] args){
    System.out.println("\n\n");
    final long[] ls = {
     -123487874834783L, -12787, 0, 11, 222222222222L, 3, 44444, 555555, 6666666, 77777777, 888888888, 9999999999L      
    };
    
    for (long l : ls) {
      System.out.println("From "+l+ " to: "+FromLongToInt.cast(l));
    }
    System.out.println("\n\n");
  }
}
