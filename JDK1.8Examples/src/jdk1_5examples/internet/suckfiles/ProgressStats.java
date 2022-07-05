package jdk1_5examples.internet.suckfiles;

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
public class ProgressStats extends Stats {
  private final long contentLength;
  public ProgressStats(long contentLength) {
    this.contentLength = contentLength;
  }
  public String calculatePercentageComplete(int totalBytes) {
    return Long.toString((totalBytes * 100 / contentLength));
  }
}
