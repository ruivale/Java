/**
 * <p>
 * Classname: exp.http.ObtainHtmlPageAndSaveItToFile
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
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

package exp.http;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Sep 19, 2014, 7:19:39 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ObtainHtmlPageAndSaveItToFile {

  public static void main(final String[] args){
    try {
      URL url = new URL("http://172.18.56.218");
      //URL url = new URL("http://www.oreilly.com/");

      try (InputStream in = url.openStream()) {
        Files.copy(in, Paths.get("c:\\temp\\www.oreilly.com.html"));

      } catch (IOException ex) {
        ex.printStackTrace();
      }

    } catch (MalformedURLException malformedURLException) {
      malformedURLException.printStackTrace();
    }


    try {
      URL url = new URL("http://172.18.56.218");
      //URL url = new URL("http://www.oreilly.com/");
      URLConnection conn = url.openConnection();

      String type = conn.getContentType();
      String encoding = conn.getContentEncoding();
      Date lastModified = new Date(conn.getLastModified());
      int len = conn.getContentLength();
      InputStream in = conn.getInputStream();

      System.out.println(url.getHost()+" type:"+type+" encoding:"+encoding);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
