/**
 * <p>
 * Classname: jdk8examples.crypto.basic.symmetric.SymmetricEncryptionDecryption
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
package jdk8examples.crypto.basic.symmetric;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * <p>
 * Description: Basic Encryption/Decryption in Java
 *
 * When working with data encryption, you can use this security control mechanism to protect three
 * types of data states: - Data at rest is information not actively moving between devices or
 * networks, stored in a database, or kept on a disk. - Data in motion represents information
 * travelling from one network point to another. - Data in use refers to information loaded in memory
 * actively accessed and processed by users.
 *
 * Encryption is important for all three data states to offer an extra layer of protection against
 * attacks. There are two methods of encryption: symmetric and asymmetric encryption.
 *
 *
 * Implementing Basic Symmetric Encryption/Decryption Symmetric or shared key encryption is a method
 * where both parties share a key, kept secret by both parties. For example, sender A can encrypt a
 * message with a shared key, then receiver B can decrypt the encrypted message only with that key.
 *
 *
 * Symmetric encryption is a valid option if you need an inexpensive computational encryption method 
 * as it requires the creation of a short single key (40-512 bits) available to both the sender and 
 * receiver. If you are looking for an option that uses different, lengthier keys for encryption and 
 * decryption, then read on about asymmetric encryption and decryption.
 *
 * 
 * Source: https://dev.java/learn/security/intro/
 *
 * </p>
 *
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class SymmetricEncryptionDecryption {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER
    = Logger.getLogger(SymmetricEncryptionDecryption.class.getName());

  /**
   * The SymmetricEncryptionDecryption default constructor.
   */
  public SymmetricEncryptionDecryption() {
  }

  /**
   * To implement symmetric encryption with Java you first need to generate a shared key. You can do
   * that using the following snippet: - you start by instantiating a secret key generator that uses
   * the AES algorithm. Next, you initialize the secret key generator for 128 bits key size and
   * requiring random bytes. Starting with JDK 19 the default size for AES algorithm has been
   * increased from 128 bits to 256 bits (if permitted by crypto policy), otherwise the default
   * falls back to 128 bits. And finally generate a secret key.
   *
   * @return
   * @throws NoSuchAlgorithmException
   */
  public static SecretKey generateKey() throws NoSuchAlgorithmException {
    KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
    keygenerator.init(128);
    return keygenerator.generateKey();
  }

  /**
   * To enhance the encryption/decryption mechanism you can initialize a vector (IV) with an
   * arbitrary value
   *
   * @return
   */
  public static IvParameterSpec generateIv() {
    byte[] initializationVector = new byte[16];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(initializationVector);
    return new IvParameterSpec(initializationVector);
  }

  /**
   * As symmetric encryption transforms a fixed-length block of plaintext data into a block of
   * ciphertext, it can use several modes in Block cipher: ECB (Electronic Code Book Mode) CBC
   * (Cipher Block Chain Mode) CCM (Counter/CBC Mode) CFB (Cipher Feedback Mode) OFB/OFBx (Output
   * Feedback) CTR (Counter mode) GCM (Galois/Counter Mode) KW (Key Wrap Mode) KWP (Key Wrap Padding
   * Mode) PCBC (Propagating Cipher Block Chaining)
   *
   * You can check all the modes and supported transformations in the Cipher Section of the Java
   * Security Standard Algorithm Names Specification.
   *
   * Next, you need to specify the Block cipher in the encryption method, when getting an instance
   * of Cipher class:
   *
   * @param input
   * @param key
   * @param iv
   * @return
   * @throws Exception
   */
  public static byte[] encrypt(String input, SecretKey key, IvParameterSpec iv)
    throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
    return cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * To convert the ciphertext back to the original plaintext, you should use the same Block cipher,
   * key and IV.
   * 
   * The doFinal() method invoked on cipher encrypts or decrypts data in a single-part operation, 
   * or finishes a multiple-part operation and returns a byte array.
   *
   * @param cipherText
   * @param key
   * @param iv
   * @return
   * @throws Exception
   */
  public static String decrypt(byte[] cipherText, SecretKey key, IvParameterSpec iv) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
    cipher.init(Cipher.DECRYPT_MODE, key, iv);
    // encrypts or decrypts data in a single-part operation, or finishes a multiple-part operation and returns a byte array
    byte[] plainText = cipher.doFinal(cipherText);
    return new String(plainText);
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("SymmetricEncryptionDecryption").append("").toString();
  }

  public static void main(final String[] args) throws Exception {
    SecretKey symmetricKey = generateKey();
    System.out.println("Generating IvParameterSpec...");
    IvParameterSpec iv = generateIv();

    System.out.println("\nType something:");
    
    // Takes input from the keyboard
    Scanner message = new Scanner(System.in); 
    String plainText = message.nextLine();
    message.close();

    // Encrypt the message using the symmetric key
    byte[] cipherText = encrypt(plainText, symmetricKey, iv);

    System.out.println("The encrypted message is: " + cipherText);

    // Decrypt the encrypted message
    String decryptedText = decrypt(cipherText, symmetricKey, iv);

    System.out.println( "Your original message is: " + decryptedText);
  }
}
