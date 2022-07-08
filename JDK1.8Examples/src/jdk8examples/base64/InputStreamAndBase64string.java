/**
 * <p>
 * Classname:  jdk8examples.base64.InputStreamAndBase64string
 * </p>
 *
 * <p>Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
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

package jdk8examples.base64;


import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;







/**
 * <p>
 * Description:
 * Base64 is a text encoding scheme that provides portability for binary data between applications 
 * and platforms. Base64 can be used to store binary data in a database string column, thereby 
 * avoiding messy file operations. When combined with the data URI scheme, Base64 can be used to 
 * embed images in web pages and emails, in conformance with the HTML and Multipurpose Internet 
 * Mail Extensions (MIME) standards.
 * 
 * In this brief tutorial, we'll demonstrate Java Streaming IO functions and the built-in Java 
 * Base64 class to load binary data as an InputStream and then convert it to a String.
 * 
 * https://www.baeldung.com/java-inputstream-to-base64-string
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class InputStreamAndBase64string {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(InputStreamAndBase64string.class.getName());


 /**
  * The InputStreamAndBase64string default constructor.
  */
  public InputStreamAndBase64string(){
  }

  /**
   * 
   * @throws Exception 
   */
  public void compare() throws Exception
  {
//    InputStream sourceStream  = getClass().getClassLoader().getResourceAsStream("efacomic.png");
//    InputStream sourceStream2 = new BufferedInputStream(new FileInputStream("jdk8examples\\base64\\efacomic.png"));
    InputStream sourceStream = InputStreamAndBase64string.class.getResource("efacomic.png").openStream();
    
    byte[] sourceBytes = IOUtils.toByteArray(sourceStream);
    String encodedString = Base64.getEncoder().encodeToString(sourceBytes); 
    assertNotNull(encodedString);    
    
    byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    assertNotNull(decodedBytes);
    assertTrue(decodedBytes.length == sourceBytes.length);
    assertTrue(calculateChecksum(decodedBytes) == calculateChecksum(sourceBytes));    
  }
  
  /**
   * 
   * @param bytes
   * @return 
   */
  int calculateChecksum(byte[] bytes) {
    int checksum = 0;
    for (int index = 0; index < bytes.length; index++) {
      checksum += bytes[index];
    }
    return checksum;
  }

  public static void main(final String[] args){
    try {
      final InputStreamAndBase64string clazz = new InputStreamAndBase64string();      
      clazz.compare();
      
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
