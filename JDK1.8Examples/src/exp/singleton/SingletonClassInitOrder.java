/**
 * <p>
 * Classname: exp.singleton.SingletonClassInitOrder
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

package exp.singleton;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Nov 20, 2012, 11:00:24 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class SingletonClassInitOrder {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(SingletonClassInitOrder.class.getName());

  /** .. */
  private static SingletonClassInitOrderSing singleton = new SingletonClassInitOrderSing();
  /** .. */
//  private static final Object objQnapNVR = new Object();

  /**
   *
   */
  public SingletonClassInitOrder() {
//    synchronized(objQnapNVR){
//      if(qnapNVR == null){
//        qnapNVR = new QnapNVR();
//      }
//    }
  }

  public SingletonClassInitOrderSing getSingletonClassInitOrderSing(){
    return singleton;
  }

}
