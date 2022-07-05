package jdk1_5examples.internet.suckfiles;

import java.net.*;
import java.io.IOException;
import java.util.Date;

/**
 *
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public abstract class Stats {
  private volatile int totalBytes;
  private long start = System.currentTimeMillis();
  public int seconds() {
    return (int) ((System.currentTimeMillis() - start) / 1000);
  }
  public void bytes(int length) {
    totalBytes += length;
  }
  public void print() {
    int kbpersecond = (int) (totalBytes / seconds() / 1024);
    System.out.printf("%10d KB%5s%%  (%d KB/s)%n", totalBytes/1024,
        calculatePercentageComplete(totalBytes), kbpersecond);
  }

  public abstract String calculatePercentageComplete(int bytes);

  public static Stats make(URL url) throws IOException {
    System.out.println(new Date() + " Opening connection to URL");
    URLConnection con = url.openConnection();
    System.out.println(new Date() + " Getting content length");
    int size = con.getContentLength();
    return size == 0 ? new BasicStats() : new ProgressStats(size);
  }
}
