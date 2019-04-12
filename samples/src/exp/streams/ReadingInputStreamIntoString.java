/**
 * <p>
 * Classname: exp.streams.ReadingInputStreamIntoString
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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
package exp.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Oct 26, 2015, 3:10:58 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ReadingInputStreamIntoString {


  Locale d;
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ReadingInputStreamIntoString.class.getName());

  /**
   *
   * @param input
   * @return
   * @throws IOException
   */
  public static String readUsingCollectionsJoining(final InputStream input) throws IOException {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
      return buffer.lines().collect(Collectors.joining("\n"));
    }
  }

  /**
   *
   * @param input
   * @return
   * @throws IOException
   */
  public static String readUsingScanner(final InputStream input) throws IOException {
    return new Scanner(input, "utf-8").useDelimiter("\\Z").next();
  }



  public static void main(final String[] args) {
//    ReadingInputStreamIntoString.readUsingCollectionsJoining(..);
//    ReadingInputStreamIntoString.readUsingScanner(..);
  }
}
