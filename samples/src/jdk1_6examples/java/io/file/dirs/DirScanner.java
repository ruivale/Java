/**
 * <p>
 * Classname:  jdk1_6examples.java.io.file.dirs.DirScanner
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class DirScanner {

  private final ConcurrentMap<File, Boolean> files = new ConcurrentHashMap<File, Boolean>();
  private final ExecutorService executor;
  private final Semaphore sem = new Semaphore(1);
  private final AtomicInteger count = new AtomicInteger();

  public DirScanner(int threads) {
    super();
    executor = Executors.newFixedThreadPool(threads);
  }

  public Set<File> scan(File dir) throws InterruptedException,
                                         ExecutionException {
    sem.acquire();
    scan0(dir);
    sem.acquire();
    return files.keySet();
  }

  private void scan0(File dir) {
    for (final File file : dir.listFiles()) {
      files.putIfAbsent(file, Boolean.TRUE);
      if (file.isDirectory()) {
        count.incrementAndGet();
        executor.submit(new Runnable() {

          public void run() {
            DirScanner.this.scan0(file);
          }
        });
      }
    }
    if (count.decrementAndGet() < 0) {
      sem.release();
    }
  }

  public void close() {
    executor.shutdown();
  }

  public static Set<File> listAllContentsUnder(File dir, int threads)
      throws InterruptedException, ExecutionException {
    DirScanner scanner = new DirScanner(threads);
    return scanner.scan(dir);
  }
}