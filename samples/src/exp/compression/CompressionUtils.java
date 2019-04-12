/**
 * <p>
 * Classname: exp.compression.CompressionUtils
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

package exp.compression;


import java.util.logging.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Feb 6, 2013, 3:40:10 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class CompressionUtils {
  /** This class LOGGER */
  private static final Logger LOG = Logger.getLogger(CompressionUtils.class.getName());


 /**
  * The CompressionUtils default constructor.
  */
  public CompressionUtils(){
  }

  public static byte[] compress(byte[] data) throws IOException {
   Deflater deflater = new Deflater();
   deflater.setInput(data);

   ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

   deflater.finish();
   byte[] buffer = new byte[1024];
   while (!deflater.finished()) {
    int count = deflater.deflate(buffer); // returns the generated code... index
    outputStream.write(buffer, 0, count);
   }
   outputStream.close();
   byte[] output = outputStream.toByteArray();

   System.out.println("Original: " + data.length / 1024 + " Kb");
   System.out.println("Compressed: " + output.length / 1024 + " Kb");
   return output;
  }

  public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
   Inflater inflater = new Inflater();
   inflater.setInput(data);

   ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
   byte[] buffer = new byte[1024];
   while (!inflater.finished()) {
    int count = inflater.inflate(buffer);
    outputStream.write(buffer, 0, count);
   }
   outputStream.close();
   byte[] output = outputStream.toByteArray();

   System.out.println("Original: " + data.length);
   System.out.println("Compressed: " + output.length);
   return output;
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("CompressionUtils").append("").toString();
  }

  public static void main(final String[] args) {
    try {
      final CompressionUtils clazz = new CompressionUtils();
      RandomAccessFile f = new RandomAccessFile("c:/temp/img.jpg", "r");
      byte[] b = new byte[(int) f.length()];
      f.read(b);

      clazz.compress(b);

    } catch (IOException iOException) {
      iOException.printStackTrace();
    }
  }
}
