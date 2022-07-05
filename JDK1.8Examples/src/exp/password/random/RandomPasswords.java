/**
 * <p>
 * Classname: exp.password.random.RandomPasswords
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

package exp.password.random;


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Nov 6, 2012, 3:16:53 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class RandomPasswords {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(RandomPasswords.class.getName());
  /** The default password length */
  private static final int I_DEFAULT_PASS_LENGTH = 10;


 /**
  * The RandomPasswords default constructor.
  */
  public RandomPasswords(){
  }

  /**
   *
   * @return
   */
  private String createRandomPassword(){
    return createRandomPassword(I_DEFAULT_PASS_LENGTH);
  }

  /**
   *
   * @param iPassLength
   * @return
   */
  private String createRandomPassword(final int iPassLength){
    String strPass = "";

    String char_allow = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,";
    char_allow += "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,";
    char_allow += "1,2,3,4,5,6,7,8,9,0,!,@,#,$,%,&,?";
    String[] arr = char_allow.split(",");

    String temp;
    Random rand = new Random();
    //Random rand = new Random();

    for (int i = 0; i < iPassLength; i++){
      temp = arr[rand.nextInt(arr.length)];
      strPass += temp;
    }

    return strPass;
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("RandomPasswords").append("").toString();
  }

  public static void main(final String[] args){
    final RandomPasswords clazz = new RandomPasswords();
    System.out.println("RandomPassword(30): "+clazz.createRandomPassword(30));
    System.out.println("RandomPassword(DE): "+clazz.createRandomPassword());
    System.out.println("RandomPassword(20): "+clazz.createRandomPassword(20));
    System.out.println("RandomPassword(10): "+clazz.createRandomPassword(10));
    System.out.println("RandomPassword(05): "+clazz.createRandomPassword(5));
  }
}
