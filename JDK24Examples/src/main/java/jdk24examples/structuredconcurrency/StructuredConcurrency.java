/**
 * <p>
 * Classname: jdk24examples.structuredconcurrency.StructuredConcurrency
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
package jdk24examples.structuredconcurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250909
 */
public class StructuredConcurrency {


  public static void main(final String[] args) {
    try {      
      Response response = new StructuredConcurrency().getResponse();      
      
      System.out.println("Response: user: "+response.u + " order: "+response.o);
      
    } catch (InterruptedException | ExecutionException exc) {
      exc.printStackTrace();
    }
  }
  
  
  private Response getResponse() throws InterruptedException, ExecutionException {
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      StructuredTaskScope.Subtask<String> u = scope.fork(() -> getUser());
      StructuredTaskScope.Subtask<String> o = scope.fork(() -> getOrder());
      
      scope.join();      
      scope.throwIfFailed();
      
      return new Response(u.get(), o.get());
    }    
  }

  private String getUser(){
    try {Thread.sleep(3500);} catch (InterruptedException interruptedException) {}
    
    return "User";
  }
  
  private String getOrder(){
    try {Thread.sleep(5000);} catch (InterruptedException interruptedException) {}
    
    return "Order";
  }

  
  class Response {
    String u;
    String o;
    
    Response(String u, String o){
      this.u = u;
      this.o = o;
    }
  }
}
