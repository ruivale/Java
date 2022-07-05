/**
 * <p>
 * Classname: exp.encrypt.base64.Passwords
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

package exp.encrypt.base64;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author 2334
 */
public class Passwords {
   public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
   public static final int ITERATION_COUNT = 8192;
   public static final int KEY_SIZE = 160;

   /**
    *
    * @param password
    * @param salt
    * @return
    * @throws GeneralSecurityException
    */
   public static byte[] hashPassword(char[] password, byte[] salt)
          throws GeneralSecurityException {
      return hashPassword(password, salt, ITERATION_COUNT, KEY_SIZE);
   }

   /**
    *
    * @param password
    * @param salt
    * @param iterationCount
    * @param keySize
    * @return
    * @throws GeneralSecurityException
    */
   public static byte[] hashPassword(
       char[] password,
       byte[] salt,
       int iterationCount,
       int keySize)
          throws GeneralSecurityException {
      PBEKeySpec spec = new PBEKeySpec(password, salt, iterationCount, keySize);
      SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
      return factory.generateSecret(spec).getEncoded();
   }

   /**
    *
    * @param password
    * @param passwordHash
    * @param salt
    * @return
    * @throws GeneralSecurityException
    */
   public static boolean matches(
       char[] password,
       byte[] passwordHash,
       byte[] salt)
          throws GeneralSecurityException {

      return matches(password, passwordHash, salt, ITERATION_COUNT, KEY_SIZE);
   }

   /**
    *
    * @param password
    * @param passwordHash
    * @param salt
    * @param iterationCount
    * @param keySize
    * @return
    * @throws GeneralSecurityException
    */
   public static boolean matches(
       char[] password,
       byte[] passwordHash,
       byte[] salt,
       int iterationCount,
       int keySize)
          throws GeneralSecurityException {
      return Arrays.equals(passwordHash, hashPassword(password, salt, iterationCount, keySize));
   }

   public static void test() throws Exception {
      char[] password = "12345678".toCharArray();
      byte[] salt = PasswordSalts.nextSalt();
      byte[] hash = Passwords.hashPassword(password, salt);

      System.out.println(
          "The passwords("+
              new String(password)+" ; "+
              new String(salt)+" ; "+
              new String(hash)+") match? "+Passwords.matches(password, hash, salt));

      byte[] otherSaltBytes = Arrays.copyOf(salt, salt.length);
      otherSaltBytes[0] ^= otherSaltBytes[0];

      System.out.println("The passwords("+
              new String(password)+" ; "+
              new String(hash)+" ; "+
              new String(otherSaltBytes)+") match? "+Passwords.matches(password, hash, otherSaltBytes));
      //assertFalse(Passwords.matches(password, hash, otherSaltBytes));
      final String str = "wrong";
      System.out.println("The passwords("+
              str+" ; "+
              new String(hash)+" ; "+
              new String(salt)+") match? "+Passwords.matches(str.toCharArray(), hash, salt));
      //assertFalse(Passwords.matches("wrong".toCharArray(), hash, salt));
   }


   public static void testEffort() throws Exception {
      final String password = "12345678";
      final long startMillis = System.currentTimeMillis();
      final byte[] saltBytes = PasswordSalts.nextSalt();
      final String sHashedPass = new String(Passwords.hashPassword(password.toCharArray(), saltBytes));
      final long lElapsedMillis = (System.currentTimeMillis() - startMillis);
      System.out.println("time " + lElapsedMillis+" HashedPass: "+sHashedPass);

      if (lElapsedMillis < 10) {
         System.out.println("Ooooooo.... i'm not sure");
      } else if (lElapsedMillis > 500) {
         System.out.println("Mmmmmmm.... i don't know");
      }
   }


   public static void main(String[] args) throws Exception {
     test();
     testEffort();
  }
}
