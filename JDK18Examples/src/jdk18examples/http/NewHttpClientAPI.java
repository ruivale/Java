

/**
 * <p>
 * Classname: jdk18examples.http.NewHttpClientAPI
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2021 EFACEC SE
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
package jdk18examples.http;


import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.logging.Logger;

/**
 * <p>
 * Description: the new Java 11 HttpClient API
 * 
 * https://openjdk.org/groups/net/httpclient/intro.html
 * 
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class NewHttpClientAPI {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER = Logger.getLogger(NewHttpClientAPI.class.getName());

  /**
   * The NewHttpClientAPI default constructor.
   */
  public NewHttpClientAPI() {
   
  }

  
  /**
   *  GET request that prints the response body as a String
   */
  public void getRequestAndPrintResponse() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://openjdk.java.net/"))
      .build();
    client.sendAsync(request, BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenAccept(System.out::println)
      .join();
  }

  /**
   * 
   * @throws Exception 
   */
  public void syncRequest() throws Exception
  {   
    // To send a request, first create an HttpClient from its builder. The builder can be used to
    // configure per-client state, like:
    // The preferred protocol version ( HTTP/1.1 or HTTP/2 )
    //    Whether to follow redirects
    //    A proxy
    //    An authenticator  
    HttpClient client = HttpClient.newBuilder()
          .version(Version.HTTP_2)
          
          //.followRedirects(Redirect.ALWAYS)
          ////.followRedirects(Redirect.SAME_PROTOCOL)
          
          .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
          .authenticator(Authenticator.getDefault())
          .build();
    
    
    // An HttpRequest is created from its builder. The request builder can be used to set:
    //    The request URI
    //    The request method ( GET, PUT, POST )
    //    The request body ( if any )
    //    A timeout
    //    Request headers  
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://openjdk.java.net/"))
      .timeout(Duration.ofMinutes(1))
      .header("Content-Type", "application/json")
      .POST(BodyPublishers.ofFile(Paths.get("file.json")))
      .build();
    // Once built an HttpRequest is immutable, and can be sent multiple times.
    
    
    // Requests can be sent either synchronously or asynchronously. The synchronous API, as 
    // expected, blocks until the HttpResponse is available.
    HttpResponse<String> response =
          client.send(request, BodyHandlers.ofString());
    System.out.println(response.statusCode());
    System.out.println(response.body());    
  }
  
  
  /**
   * 
   * @throws Exception 
   */
  public void aSyncRequest() throws Exception
  {   
    // To send a request, first create an HttpClient from its builder. The builder can be used to
    // configure per-client state, like:
    // The preferred protocol version ( HTTP/1.1 or HTTP/2 )
    //    Whether to follow redirects
    //    A proxy
    //    An authenticator  
    HttpClient client = HttpClient.newBuilder()
          .version(Version.HTTP_2)
          
          //.followRedirects(Redirect.ALWAYS)
          ////.followRedirects(Redirect.SAME_PROTOCOL)
          
          .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
          .authenticator(Authenticator.getDefault())
          .build();
    
    
    // An HttpRequest is created from its builder. The request builder can be used to set:
    //    The request URI
    //    The request method ( GET, PUT, POST )
    //    The request body ( if any )
    //    A timeout
    //    Request headers  
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://openjdk.java.net/"))
      .timeout(Duration.ofMinutes(1))
      .header("Content-Type", "application/json")
      .POST(BodyPublishers.ofFile(Paths.get("file.json")))
      .build();
    // Once built an HttpRequest is immutable, and can be sent multiple times.
    
    
    // The asynchronous API returns immediately with a CompletableFuture that completes with the 
    // HttpResponse when it becomes available. CompletableFuture was added in Java 8 and supports 
    // composable asynchronous programming.
    client.sendAsync(request, BodyHandlers.ofString())
          .thenApply(response -> {
            System.out.println(response.statusCode());
            return response;
          })
          .thenApply(HttpResponse::body)
          .thenAccept(System.out::println);
  }
  
  
  
  
  public static void main(final String[] args) {
    final NewHttpClientAPI clazz = new NewHttpClientAPI();
    
    clazz.getRequestAndPrintResponse();
    
    try {
      System.out.println("-------------------------------------------------\nsyncRequest");
      clazz.syncRequest();
      System.out.println("-------------------------------------------------\naSyncRequest");
      clazz.aSyncRequest();
      
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}


