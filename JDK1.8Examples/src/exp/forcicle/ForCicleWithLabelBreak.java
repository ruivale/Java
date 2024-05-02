/**
 * <p>
 * Classname:  exp.forcicle.ForCicleWithLabelBreak
 * </p>
 *
 * <p>Copyright: Copyright (c) 2019 EFACEC SE
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

package exp.forcicle;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ForCicleWithLabelBreak {

 /**
  * The ForCicleWithLabelBreak default constuctor.
  */
  public ForCicleWithLabelBreak(){
    int n = 6;
    
//outer:
//    for (int i = 0; i < n; i++) {
//      System.out.println("\ti="+i);

inner:
      for (int j = 0; j < n; j++) {
        System.out.println("\tj="+j);

innest:
        for (int k = 0; k < n; k++) {
          System.out.println("\t\tk="+k);
          
          if(k==3){
            break inner;
          }
          
          
        }        
      }
//    }
    
  }


  public static void main(final String[] args){
    final ForCicleWithLabelBreak clazz = new ForCicleWithLabelBreak();
  }
}
