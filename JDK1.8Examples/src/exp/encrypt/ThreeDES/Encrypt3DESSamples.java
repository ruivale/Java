/**
 * <p>
 * Classname: exp.encrypt.ThreeDES.Encrypt3DESSamples
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package exp.encrypt.ThreeDES;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class Encrypt3DESSamples {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER = Logger.getLogger(Encrypt3DESSamples.class.getName());

  /**
   * A secret key that will be used for the encryption-decryption process. In our case, we'll use a
   * 24-byte key constructed from random numbers and letters.
   *
   * Note: that a secret key shouldn't be shared publicly.
   */
  private final byte[] secretKey = "9mng65v8jf4lxn93nabf981m".getBytes();

  /**
   * 
   */
  private final String secretMessage = "Baeldung secret message";
    
  /**
   *
   */
  private SecretKeySpec secretKeySpec;

  /**
   *
   */
  private IvParameterSpec ivSpec;
  
  /**
   * 
   */
  private byte[] encryptedMessageBytes;
    
  /**
   * The Encrypt3DESSamples default constructor.
   */
  public Encrypt3DESSamples() {
  }

  /**
   *
   */
  private void generateSecretKey() {

    // we'll wrap our key in the SecretKeySpec combining it with a chosen algorithm:
    this.secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");

    // Another item we should generate in advance is the Initialization Vector for our key. 
    // We'll use an 8-byte array of random numbers and letters:F
    final byte[] iv = "a76nb5h9".getBytes();

    //  we'll wrap it in the IvParameterSpec class:
    this.ivSpec = new IvParameterSpec(iv);
    
  }

  /**
   *
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   * @throws InvalidAlgorithmParameterException
   * @throws NoSuchPaddingException
   */
  private void encrypting()
      throws NoSuchAlgorithmException,
             InvalidKeyException,
             InvalidAlgorithmParameterException,
             NoSuchPaddingException,
             IllegalBlockSizeException,
             BadPaddingException {

    // 
    

    // a Cipher object initialized with the encryption mode, secret key, and the initialization 
    // vector that we generated previously.
    // 
    // Note: we're using the TripleDES algorithm with a CBC and a PKCS#5 padding scheme.
    final Cipher encryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
    encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

    // With the Cipher, we can run the doFinal method to encrypt our message. 
    // Note: it only works with a byte array, so we need to transform our String first:
    final byte[] secretMessagesBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
    this.encryptedMessageBytes = encryptCipher.doFinal(secretMessagesBytes);

    // Now, our message is successfully encrypted. If we'd like to store it in a database or 
    // send it via a REST API, it would be more convenient to encode it with the Base64 alphabet:
    final String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);

    //Base64 encoding makes the message more readable and easier to work with.
  }

  /**
   * 
   * @throws NoSuchAlgorithmException
   * @throws NoSuchPaddingException
   * @throws InvalidKeyException
   * @throws InvalidAlgorithmParameterException
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException 
   */
  private void decrypting() 
      throws NoSuchAlgorithmException, 
             NoSuchPaddingException, 
             InvalidKeyException, 
             InvalidAlgorithmParameterException, 
             IllegalBlockSizeException, 
             BadPaddingException {
    // Now, let's see how we can reverse the encryption process and decrypt the message to its 
    // original form. For this, we'll need a new Cipher instance, but this time, we'll initialize 
    // it in decryption mode:
    final Cipher decryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");

    decryptCipher.init (Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

    // Next, we'll run the doFinal method:
    final byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);

    // Now, we'll decode the result to a String variable:
    final  String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

    // Finally, we can verify the results to make sure the decryption process performed correctly 
    // by comparing it to the initial value:
//    Assertions.assertEquals (secretMessage, decryptedMessage);
}

/**
 * Returns this class description in a friendly way.
 *
 * @return String description
 */
public String toString() {
    return new StringBuffer("Encrypt3DESSamples").append("").toString();
  }

  public static void main(final String[] args) {
    final Encrypt3DESSamples clazz = new Encrypt3DESSamples();
  }
}
