package exp.encrypt.base64;

import java.security.SecureRandom;


/**
 * 
 * @author 2334
 */
public class PasswordSalts {

  public static final int SALT_LENGTH = 16;

  /**
   *
   * @return
   */
  public static byte[] nextSalt() {
    byte[] salt = new byte[SALT_LENGTH];
    SecureRandom sr = new SecureRandom();
    sr.nextBytes(salt);
    return salt;
  }

//  public void testSaltEncoding() throws Exception {
//    byte[] saltBytes = PasswordSalts.nextSalt();
//    String encodedSalt = Base64.encode(saltBytes);
//    System.out.println(encodedSalt);
//    assertEquals(encodedSalt.length(), 24);
//    assertEquals(encodedSalt.substring(22, 24), "==");
//  }
}