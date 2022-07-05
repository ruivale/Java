/**
 * <p>
 * Classname:  jdk1_6examples.java.io.file.dirs.DirFilesScan
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_6examples.java.io.file.dirs;

import java.io.File;
import java.util.Set;
import java.util.logging.Logger;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class DirFilesScan {

  /** This class LOGGER */
  private static final Logger LOGGER =
                              Logger.getLogger(DirFilesScan.class.getName());

  /**
   * The DirFilesScan default constuctor.
   */
  public DirFilesScan() {
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("DirFilesScan").append("").toString();
  }

  public static void main(String[] _args) {
    int status = 0;
    try {
      final String[] args = {"C:/temp"};
      Set<File> files0 = null;
      Set<File> files1 = null;
      long t0 = System.nanoTime();
      files0 = SerialDirScanner.listAllContentsUnder(new File(args[0]));
      t0 = System.nanoTime() - t0;
      System.out.println(t0 + " ticks.");
      t0 = System.nanoTime();
      files1 = DirScanner.listAllContentsUnder(new File(args[0]), 5);
      t0 = System.nanoTime() - t0;
      System.out.println(t0 + " ticks.");
      System.out.println(files0.size() + "," + files1.size());
      System.out.println(files0.size() == files1.size());
    } catch (Throwable exc) {
      status = 1;
      exc.printStackTrace();
    } finally {
      System.exit(status);
    }
  }
}
