/**
 * <p>
 * Classname: jdk1_7examples.file_type_guessing.FileContentType
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
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
package jdk1_7examples.file_type_guessing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author 2334
 */
public class FileContentType {

  public static void main(String[] args) {

    printContentType("D:/Downloads/java.txt");
    printContentType("D:/Downloads/java.ppt");
    printContentType("D:/Downloads/java.doc");
    printContentType("D:/Downloads/java.jar");

  }

  private static void printContentType(String pathToFile) {

    Path path = Paths.get(pathToFile);
    String contentType = null;
    try {
      contentType = Files.probeContentType(path);
    } catch (IOException e) {

      e.printStackTrace();
    }
    System.out.println("File content type is : " + contentType);

  }
}
