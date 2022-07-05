package exp.xml.xslt;

import java.io.*;

import java.net.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 *
 *
 * File: howto.xsl
 *
 * <?xml version="1.0"?>
 * <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
 * <xsl:template match="/">
 * <html>
 * <head><title>Real's HowTo</title></head>
 * <body>
 * <table border="1">
 * <tr>
 * <th>Title</th>
 * <th>URL</th>
 * </tr>
 * <xsl:for-each select="howto/topic">
 * <tr>
 * <td><xsl:value-of select="title"/></td>
 * <td><xsl:value-of select="url"/></td>
 * </tr>
 * </xsl:for-each>
 * </table>
 * </body></html>
 * </xsl:template>
 * </xsl:stylesheet>
 *
 *
 *
 * File: howto.xml
 *
 * <?xml version="1.0"?>
 * <howto>
 *   <topic>
 *      <title>Java</title>
 *       <url>http://www.rgagnon/javahowto.htm</url>
 *   </topic>
 *   <topic>
 *      <title>PowerBuilder</title>
 *      <url>http://www.rgagnon/pbhowto.htm</url>
 *   </topic>
 * </howto>
 *
 *
 */

// jdk1.4.1
import javax.xml.transform.*;


public class HowToXSLT {
  public static void main(String[] args) {
    try {

      TransformerFactory tFactory = TransformerFactory.newInstance();

      Transformer transformer = tFactory.newTransformer(
        new javax.xml.transform.stream.StreamSource(
            "howto.xsl"));

      transformer.transform(new javax.xml.transform.stream.StreamSource(
          "howto.xml"),
        new javax.xml.transform.stream.StreamResult(
          new FileOutputStream("howto.html")));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
