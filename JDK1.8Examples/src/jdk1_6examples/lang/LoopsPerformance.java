/**
 * <p>
 * Classname:  jdk1_6examples.lang.LoopsPerformance
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

package jdk1_6examples.lang;


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
public class LoopsPerformance {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(LoopsPerformance.class.getName());

  final int n = 1000000000;
  final int scale = 100000;
  boolean is = true;


 /**
  * The LoopsPerformance default constuctor.
  */
  public LoopsPerformance(){
    //ifsInsideForLoop();
    ifsOutsideForLoop();
  }

  
  private void ifsInsideForLoop(){
    final long before = System.currentTimeMillis();

    for (int i = 0; i < n; i++) {
      if(is){
        if(i%scale == 0){
          System.out.print(".");
        }
      }else{
        if(i%scale == 0){
          System.out.print(".");
        }
      }
    }

    final long after = System.currentTimeMillis();

    System.out.println("\nTook "+(after-before)+" millis. ifsInsideForLoop");
  }


  private void ifsOutsideForLoop(){
    final long before = System.currentTimeMillis();

    if(is){
      for (int i = 0; i < n; i++) {
        if(i%scale == 0){
          System.out.print(".");
        }
      }
    }else{
      for (int i = 0; i < n; i++) {
        if(i%scale == 0){
          System.out.print(".");
        }
      }
    }

    final long after = System.currentTimeMillis();

    System.out.println("\nTook "+(after-before)+" millis. ifsOutsideForLoop");
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("LoopsPerformance").append("").toString();
  }

  public static void main(final String[] args){
    final LoopsPerformance clazz = new LoopsPerformance();
  }
}
