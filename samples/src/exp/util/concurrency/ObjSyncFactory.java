/**
 * <p>
 * Classname: exp.util.concurrency.ObjSyncFactory
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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

package exp.util.concurrency;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 14, 2016, 6:06:07 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ObjSyncFactory {

  private static final Object OBJ = new Object();


  public static Object getLockableObject(){
    return ObjSyncFactory.OBJ;
  }

}
