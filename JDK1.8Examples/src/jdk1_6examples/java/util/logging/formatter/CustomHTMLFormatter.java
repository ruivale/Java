/**
 * <p>
 * Classname: package jdk1_6examples.java.util.logging.formatter.CustomHTMLFormatter
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
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
package jdk1_6examples.java.util.logging.formatter;

/*
 * Copyright(c)2008 JavaEECoding.com. All rights reserved.
 */
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class CustomHTMLFormatter
    extends Formatter {
  //This method is called for each log record

  public String format(LogRecord record) {
    StringBuilder sb = new StringBuilder();
    sb.append("<tr>\n");
    sb.append("\t<td>" + record.getSequenceNumber() + "</td>\n");
    sb.append("\t<td>" + new Date(record.getMillis()) + "</td>\n");

    int logLevel = record.getLevel().intValue();
    if(logLevel >= Level.WARNING.intValue()) {
      sb.append("\t<td><b>" + record.getLevel() + "</b></td>\n");
      sb.append("\t<td><b>" + record.getMessage() + "</b></td>\n");
    } else {
      sb.append("\t<td>" + record.getLevel() + "</td>\n");
      sb.append("\t<td>" + record.getMessage() + "</td>\n");
    }

    sb.append("\t<td>" + record.getSourceClassName() + "</td>\n");
    sb.append("\t<td>" + record.getSourceMethodName() + "</td>\n");
    sb.append("\t<td>" + record.getLoggerName() + "</td>\n");
    sb.append("</tr>\n");

    return sb.toString();
  }

  //This method is called just after the handler is set to use this formatter
  public String getHead(Handler h) {
    StringBuilder sb = new StringBuilder();
    sb.append("<html><head></head><body>\n");
    sb.append("<table border=\"1\">\n");
    sb.append("<tr>\n");
    sb.append("\t<th>Sequence</th>\n");
    sb.append("\t<th>Time</th>\n");
    sb.append("\t<th>Level</th>\n");
    sb.append("\t<th>Message</th>\n");
    sb.append("\t<th>Class Name</th>\n");
    sb.append("\t<th>Method Name</th>\n");
    sb.append("\t<th>Logger Name</th>\n");
    sb.append("</tr>\n");

    return sb.toString();
  }

  //This method is called just after the handler using this formatter is
  //closed.
  public String getTail(Handler h) {
    return "</table>\n</body></html>\n";
  }

  public static void main(String[] args) {
    try {
      FileHandler fileHandler = new FileHandler("MyAppLog.html");

      fileHandler.setLevel(Level.ALL);

      fileHandler.setFormatter(new CustomHTMLFormatter());

      Logger logger = Logger.getLogger("com.javaeecoding");

      logger.addHandler(fileHandler);

      logger.setLevel(Level.FINE);

      logger.setUseParentHandlers(false);

      logger.severe("I'm logging SEVERE messages.");

      logger.warning("I'm logging WARNING messages.");

      logger.info("I'm logging INFO messages.");

      logger.config("I'm logging CONFIG messages.");

      logger.fine("I'm logging FINE messages.");

      logger.finer("I'm logging FINER messages.");

      logger.finest("I'm logging FINEST messages.");

      fileHandler.close();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
