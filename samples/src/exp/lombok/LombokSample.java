/**
 * <p>
 * Classname: exp.lombok.LombokSample
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

package exp.lombok;


import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.*;

/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 5, 2014, 2:42:37 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class LombokSample {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(LombokSample.class.getName());

  private @Getter @Setter int id;


 /**
  * The LombokSample default constructor.
  */
  public LombokSample(){
  }

  public int getId(){
    System.out.println();
    return this.id;
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("LombokSample").append("").toString();
  }

  public static void main(final String[] args){
    final LombokSample clazz = new LombokSample();
  }
}
