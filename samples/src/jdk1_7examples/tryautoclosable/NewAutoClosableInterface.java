/**
 * <p>
 * Classname: jdk1_7examples.tryautoclosable.NewAutoClosableInterface
 * </p>
 *
 * <p>Copyright: Copyright (c) 2014 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.� Frederico Ulrich ? Ap. 3078
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

package jdk1_7examples.tryautoclosable;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Nov 20, 2014, 3:57:43 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class NewAutoClosableInterface {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(NewAutoClosableInterface.class.getName());


 /**
  * The NewAutoClosableInterface default constructor.
  */
  public NewAutoClosableInterface(){

    try(AutoClosableImpl a1 = new AutoClosableImpl("1");
        AutoClosableImpl a2 = new AutoClosableImpl("2");
        AutoClosableImpl a3 = new AutoClosableImpl("3");){

    }catch(Exception e){
      e.printStackTrace();
    }

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("NewAutoClosableInterface").append("").toString();
  }

  public static void main(final String[] args){
    final NewAutoClosableInterface clazz = new NewAutoClosableInterface();
  }
}
