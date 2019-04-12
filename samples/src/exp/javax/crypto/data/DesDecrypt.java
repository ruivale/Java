package exp.javax.crypto.data;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.security.spec.AlgorithmParameterSpec;

/**
 *
 * @author 2334
 */
public class DesDecrypt implements Serializable {

  private transient Cipher dcipher;
  private transient boolean retVal = true;
  // Buffer used to transport the bytes from one stream to another
  transient private byte[] buf = new byte[1024];
  //protected BPMSLogger bpmsLogger = BPMSLogger.getLogger(this.getClass().getName());

  /**
   *
   */
  private DesDecrypt(String confDir) throws Exception {
    // Create an 8-byte initialization vector
    byte[] iv = new byte[]{
      (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A
    };
    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);

    //try {
      // Deserialization of key object.
      FileInputStream in = new FileInputStream(confDir + "/key.object");
      ObjectInputStream s = new ObjectInputStream(in);
      SecretKey key = (SecretKey) s.readObject();
      dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      // CBC requires an initialization vector
      dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

//    } catch (Exception e) {
//      e.printStackTrace();
//      retVal = false;
//    }
  }

  /**
   *
   * @param in
   * @param out
   */
  private void decrypt(InputStream in, OutputStream out) {
    try {
      // Bytes read from in will be decrypted
      in = new CipherInputStream(in, dcipher);
      // Read in the decrypted bytes and write the cleartext to out
      int numRead = 0;
      while ((numRead = in.read(buf)) >= 0) {
        out.write(buf, 0, numRead);
      }
      out.close();

    } catch (java.io.IOException e) {
      e.printStackTrace();
      retVal = false;
    }
  }

  /**
   * 
   * @param confDir
   * @param source
   * @param dest
   * @return
   */
  public static boolean doDecryption(String confDir, String source, String dest) {
    boolean retVal = true;

    try {
      // Create encrypter/decrypter class
      DesDecrypt decrypter = new DesDecrypt(confDir);
      // Does the Encryption here...
      decrypter.decrypt(new FileInputStream(source), new FileOutputStream(dest));

    } catch (Exception e) {
      e.printStackTrace();
      retVal = false;
    }
    return retVal;
  }
}
