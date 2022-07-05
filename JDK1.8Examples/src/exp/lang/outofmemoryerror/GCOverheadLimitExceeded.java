/**
 * <p>
 * Classname: exp.lang.outofmemoryerror.GCOverheadLimitExceeded
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

package exp.lang.outofmemoryerror;

import java.util.Map;
import java.util.Random;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 17, 2014, 3:32:31 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class GCOverheadLimitExceeded {

  // Cmmand line VM args: -Xmx100m -XX:+UseParallelGC
  public static void main(String args[]) throws Exception {
    Map map = System.getProperties();
    Random r = new Random();
    while (true) {
      map.put(r.nextInt(), "value");
    }
  }
}














