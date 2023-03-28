/**
 * <p>
 * Classname: jdk8examples.crypto.basic.asymmetric.AsymmetricEncryptionDecryption
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
package jdk8examples.crypto.basic.asymmetric;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

/**
 * <p>
 * Description:
 *
 * Asymmetrical encryption uses a pair of mathematical related keys, one for encryption and the
 * other for decryption. In the example bellow Key1 is used for encryption and Key2 is used for
 * decryption.
 *
 * In such a system, A can encrypt a message using the receiver?s B public key, but only the private
 * key owned by B can decode the message. In a pair of keys, the public key is visible to all. The
 * private key is the secret key and is primarily used for decryption or for encryption with digital
 * signatures.
 *
 *
 * Source: https://dev.java/learn/security/intro/
 *
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class AsymmetricEncryptionDecryption {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER
    = Logger.getLogger(AsymmetricEncryptionDecryption.class.getName());

  /**
   * The AsymmetricEncryptionDecryption default constructor.
   */
  public AsymmetricEncryptionDecryption() {
  }

  /**
   * To implement asymmetric encryption in Java you first need to generate a keypair (public,
   * private) by getting an instance of KeyPairGenerator (for the RSA algorithm in this case). Given
   * the algorithm selected, the KeyPairGenerator object uses a 3072-bit key size and a random
   * number initialized via the SecureRandom class.
   *
   ****************************************************************************************************
   * If you are using JDK 19 or later, you should be aware that the RSA, RSASSA-PSS, and DH
   * algorithms have a default key size increased from 2048 bits to 3072 bits.
   * ***************************************************************************************************
   *
   * @return
   * @throws Exception
   */
  public static KeyPair generateRSAKKeyPair() throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(3072);
    return keyPairGenerator.generateKeyPair();
  }

  /**
   * Next, let?s implement the encrypt method that converts the plaintext into ciphertext using a
   * public key.
   *
   * @param plainText
   * @param publicKey
   * @return
   * @throws Exception
   */
  public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * To convert the ciphertext back to the original plaintext, you can use the private key.
   *
   * @param cipherText
   * @param privateKey
   * @return
   * @throws Exception
   */
  public static String decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] result = cipher.doFinal(cipherText);
    return new String(result);
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("AsymmetricEncryptionDecryption").append("").toString();
  }

  /**
   * Using the previous methods, you can write a small program to simulate how asymmetrical
   * encryption and decryption works.
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    KeyPair keypair = generateRSAKKeyPair();

    // takes input from the keyboard
    Scanner message = new Scanner(System.in);
    System.out.print("Enter the message you want to encrypt using RSA: ");
    String plainText = message.nextLine();
    message.close();

    byte[] cipherText = encrypt(plainText, keypair.getPublic());

    System.out.print("\nThe encrypted text is: ");
    System.out.println(DatatypeConverter.printHexBinary(cipherText));
    //System.out.println(HexFormat.of().formatHex(cipherText));

    String decryptedText = decrypt(cipherText, keypair.getPrivate());

    System.out.println("\nThe decrypted text is: " + decryptedText);
  }

  
  
  

  /**
   * You can ensure both the sender and the integrity of the message transmitted over an insecure
   * channel by hashing the message using MessageDigest. To implement this, you should create the
   * digest of the message and encrypt it with the private key.
   *
   * This digest is called a digital signature that can be decrypted only by the receiver who has
   * the sender?s public key.
   *
   * @param plainText
   * @param privateKey
   * @return
   * @throws Exception
   */
  public static byte[] generateDigitalSignature(byte[] plainText, PrivateKey privateKey) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] messageHash = md.digest(plainText);

    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    return cipher.doFinal(messageHash);
  }

  /**
   * To validate the authenticity of the message and sender you should use the public key.
   *
   * @param plainText
   * @param digitalSignature
   * @param publicKey
   * @return
   * @throws Exception
   */
  public static boolean verify(byte[] plainText, byte[] digitalSignature, PublicKey publicKey) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hashedMessage = md.digest(plainText);

    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    byte[] decryptedMessageHash = cipher.doFinal(digitalSignature);

    return Arrays.equals(decryptedMessageHash, hashedMessage);
  }
  
//  /**
//   * Below you can find a sample call that would make use of the above methods.
//   *
//   * @param args
//   * @throws Exception
//   */
//  public static void main(String[] args) throws Exception {
//    byte[] digitalSignature = generateDigitalSignature(plainText.getBytes(), keypair.getPrivate());
//    System.out.println("Signature Value: " + HexFormat.of().formatHex(digitalSignature));
//    System.out.println("Verification: " + verify(plainText.getBytes(), digitalSignature, keypair.getPublic()));
//  }
}
