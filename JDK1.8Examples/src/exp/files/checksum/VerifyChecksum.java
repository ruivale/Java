/**
 * <p>
 * Classname: exp.files.checksum.VerifyChecksum
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
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
package exp.files.checksum;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Feb 12, 2018, 11:36:46 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class VerifyChecksum {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(VerifyChecksum.class.getName());

  /**
   * The VerifyChecksum default constructor.
   */
  public VerifyChecksum() {
  }

  /**
   * Verifies file's SHA256 checksum
   *
   * @param file     and name of a file that is to be verified
   * @param testChecksum the expected checksum
   *
   * @return true if the expected SHA256 checksum matches the file's SHA256 checksum; false otherwise.
   *
   * @throws NoSuchAlgorithmException
   * @throws IOException
   */
  public static boolean verifyChecksum(final String file, final String testChecksum)
        throws NoSuchAlgorithmException, IOException {

    final MessageDigest sha256 = MessageDigest.getInstance("SHA256");
    final FileInputStream fis = new FileInputStream(file);

    final byte[] data = new byte[1024];
    int read;

    while ((read = fis.read(data)) != -1) {
      sha256.update(data, 0, read);
    }


    final byte[] hashBytes = sha256.digest();


    final int nBytes = hashBytes!=null? hashBytes.length: 0;
    final StringBuilder sb = new StringBuilder(nBytes );

    for (int i = 0; i < nBytes; i++) {
      sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
    }

    final String fileHash = sb.toString();

    return fileHash.equals(testChecksum);
  }




  public static void main(final String[] args) {
    //args[0] = "/home/eclipse-jee-indigo-SR2-linux-gtk-x86_64.tar.gz";
    //args[1] = "177750b65a21a9043105fd0820b85b58cf148ae4";

    try {
      boolean result = verifyChecksum(args[0], args[1]);
      System.out.println("Does the file's checksum matches the expected one? " + result);
      final VerifyChecksum clazz = new VerifyChecksum();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
