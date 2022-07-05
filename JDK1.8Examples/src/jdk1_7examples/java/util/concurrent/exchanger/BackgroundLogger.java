/**
 * <p>
 * Classname: jdk1_7examples.java.util.concurrent.exchanger.BackgroundLogger
 * </p>
 *
 * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich – Ap. 3078
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

package jdk1_7examples.java.util.concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author 2334
 */
public class BackgroundLogger implements Runnable {
  static final int ENTRIES = 64;

  static class LogEntry {
    long time;
    int level;
    final StringBuilder text = new StringBuilder();
  }

  static class LogEntries {
    final LogEntry[] lines = new LogEntry[ENTRIES];
    int used = 0;
  }

  private final ExecutorService executor = Executors.newSingleThreadExecutor();
  final Exchanger<LogEntries> logEntriesExchanger = new Exchanger<LogEntries>();
  LogEntries entries = new LogEntries();

  BackgroundLogger() {
    executor.submit(this);
  }

  public StringBuilder log(int level) {
    try {
      if (entries.used == ENTRIES)
        entries = logEntriesExchanger.exchange(entries);
      LogEntry le = entries.lines[entries.used++];
      le.time = System.currentTimeMillis();
      le.level = level;
      return le.text;

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void flush() throws InterruptedException {
    if(entries.used > 0)
        entries = logEntriesExchanger.exchange(entries);
  }

  public void stop() {
    try {
      flush();
    } catch (InterruptedException e) {
      e.printStackTrace(); // use standard logging.
    }
    executor.shutdownNow();
  }

  @Override
  public void run() {
    LogEntries entries = new LogEntries();
    try {
      while (!Thread.interrupted()) {
        entries = logEntriesExchanger.exchange(entries);
            for (int i = 0; i < entries.used; i++) {
              bgLog(entries.lines[i]);
              entries.lines[i].text.delete(0, entries.lines[i].text.length());
        }
        entries.used = 0;
      }
    } catch (InterruptedException ignored) {

    } finally {
      System.out.println("Warn: logger stopping."); // use standard logging.
    }
  }

  private void bgLog(LogEntry line) {
    // log the entry to a file.
  }
}
