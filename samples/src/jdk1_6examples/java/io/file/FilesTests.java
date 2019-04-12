/**
 * <p>
 * Classname:  jdk1_6examples.java.io.file.FilesTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
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

package jdk1_6examples.java.io.file;


import java.io.File;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class FilesTests {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(FilesTests.class.getName());


  public static void main(String[] args) {
    try {
      final File filePlayerFolder =
          new File("D:\\Projects\\TLC-LUAS\\tests\\AllInOne\\TLC-LUAS\\stv\\recordings\\Player");

      if (filePlayerFolder.exists() && filePlayerFolder.isDirectory()) {
        final File[] files = filePlayerFolder.listFiles();

        for (File file : files) {
          System.out.println(file.getAbsolutePath());
        }
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
