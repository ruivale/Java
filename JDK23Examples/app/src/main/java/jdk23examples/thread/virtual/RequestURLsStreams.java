/**
 * <p>
 * Classname: jdk23examples.thread.virtual.RequestURLsStreams
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2024 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package jdk23examples.thread.virtual;

import com.sun.net.httpserver.Request;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250821
 */
public class RequestURLsStreams {

  /*
   * 
   * @param request
   * @param response 
   */
  void handle(/*Request request, Response response*/) throws Exception {
    var url1 = URI.create("https://www.google.pt").toURL();
    var url2 = URI.create("https://www.sapo.pt").toURL();
 
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      var future1 = executor.submit(() -> fetchURL(url1));
      var future2 = executor.submit(() -> fetchURL(url2));
      //response.send(future1.get() + future2.get());
      
      System.out.println("1: \n"+future1.get());
      System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      System.out.println("2: \n"+future2.get());
      
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  /*
   * 
   * @param url
   * @return
   * @throws IOException 
   */
  String fetchURL(URL url) throws IOException {
    try (var in = url.openStream()) {
      return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }
  }


  

  public static void main(final String[] args) {
    try {
      new RequestURLsStreams().handle();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
