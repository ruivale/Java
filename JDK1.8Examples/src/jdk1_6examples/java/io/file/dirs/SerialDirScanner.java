/**
 * <p>
 * Classname:  jdk1_6examples.java.io.file.dirs.SerialDirScanner
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

public class SerialDirScanner {

  private final ConcurrentMap<File, Boolean> files = new ConcurrentHashMap<File, Boolean>();

  public SerialDirScanner() {
    super();
  }

  public Set<File> scan(File dir) throws InterruptedException,
                                         ExecutionException {
    scan0(dir);
    return files.keySet();
  }

  private void scan0(File dir) {
    for (final File file : dir.listFiles()) {
      files.putIfAbsent(file, Boolean.TRUE);
      if (file.isDirectory()) {
        scan0(file);
      }
    }
  }

  public static Set<File> listAllContentsUnder(File dir)
      throws InterruptedException, ExecutionException {
    SerialDirScanner scanner = new SerialDirScanner();
    return scanner.scan(dir);
  }
}