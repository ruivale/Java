/**
 * <p>
 * Classname: exp.javax.crypto.data.DesTest
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

package exp.javax.crypto.data;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jul 24, 2013, 1:05:49 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class DesTest {
  private static final String STR_CONF_DIR = "./";

//  private static final String S_ORIG_FILE = "logTest.log";
//  private static final String S_DECRYPT_FILE = "logTestDecrypted.log";
//  private static final String S_CRYPT_FILE = "logTestCrypted.enc";
  private static final String S_ORIG_FILE = "beans.bmp";
  private static final String S_DECRYPT_FILE = "beansDecrypted.bmp";
  private static final String S_CRYPT_FILE = "beansEncrypted.enc";

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(DesTest.class.getName());


  public static void main(final String[] args){
    DesEncrypt.doEncryption(STR_CONF_DIR, STR_CONF_DIR + S_ORIG_FILE, STR_CONF_DIR + S_CRYPT_FILE);
    DesDecrypt.doDecryption(STR_CONF_DIR, STR_CONF_DIR + S_CRYPT_FILE, STR_CONF_DIR + S_DECRYPT_FILE);
  }
}
