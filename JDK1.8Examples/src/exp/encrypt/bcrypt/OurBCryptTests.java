/**
 * <p>
 * Classname: exp.encrypt.bcrypt.OurBCryptTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
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
package exp.encrypt.bcrypt;

import java.util.logging.Logger;



/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Sep 3, 2013, 5:05:35 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class OurBCryptTests {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER = Logger.getLogger(OurBCryptTests.class.getName());


  private static String strPlainPassword = "hoje";
  private static String strStoredHash =
            //"$2a$10$UGt1eAraE2T/veuRw67Rw.BLY52hMDzduCvkHglKYyNOIJOCoLBHy"; // hoje (salt = 10)
            "$2a$12$XDXx1zhLuPB1adf3yc5NV.cV1RmOJGVIEkEd60LiWA4kwYndtbCoC"; // hoje (salt = 12)
  private static String strCandidatePassword = strPlainPassword;

  /**
   * The OurBCryptTests default constructor.
   */
  public OurBCryptTests() throws Exception{

    // Hash a password for the first time
//    String hashed = BCrypt.hashpw(strPlainPassword, BCrypt.gensalt(12));
//    System.out.println("\""+strPlainPassword+"\" hashed: "+hashed);
    System.out.print("Write the candidate password: ");
    final byte[] bytes = new byte[1024];
    System.in.read(bytes, 0, 1024);
    final String str = new String(bytes).trim();

    if(str != null && !str.isEmpty()){
      strCandidatePassword = str;
    }

    // gensalt's log_rounds parameter determines the complexity
    // the work factor is 2**log_rounds, and the default is 10
    //String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

    // Check that an unencrypted password matches one that has
    // previously been hashed
    if (BCrypt.checkpw(
            strCandidatePassword,
            strStoredHash)) {
            //hashed)) {
      System.out.println("It matches");
    } else {
      System.out.println("It does not match. strCandidatePassword="+strCandidatePassword+".END");
    }

    try {
      Thread.sleep(567);
    } catch (InterruptedException interruptedException) {
    }
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("OurBCryptTests").append("").toString();
  }

  public static void main(final String[] args) throws Exception{
    final OurBCryptTests clazz = new OurBCryptTests();
  }
}
