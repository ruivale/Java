/**
 * <p>
 * Classname:  com.efacec.trp.tests.grpc.simplegrpcsamplejavacpp.SimplegRPCSampleJavaCppServer
 * </p>
 *
 * <p>Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
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

package com.efacec.trp.tests.grpc.simplegrpcsamplejavacpp;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.examples.helloworld.EmptyRequest;
import io.grpc.examples.helloworld.GoodbyeReply;
import io.grpc.examples.helloworld.GreeterGrpc.GreeterImplBase;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class SimplegRPCSampleJavaCppServer extends GreeterImplBase {

  private static final Logger logger = Logger.getLogger(SimplegRPCSampleJavaCppServer.class.getName());
  private Server server;
  private int port = 50051;

  
  /*
   * 
   * @param argsa
   * @throws IOException
   * @throws InterruptedException 
   */
  private void start(String[] args) throws IOException, InterruptedException {
    
    parseArgs(args);
    
    server = ServerBuilder.forPort(port)
                .addService(this)
                .build()
                .start();
    
//    server =
//        Grpc.newServerBuilderForPort(port, AltsServerCredentials.create())
//            .addService(this)
//            .executor(Executors.newFixedThreadPool(1))
//            .build();
//    server.start();
    
//    server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
//        .addService(this)
//        .build();    
    
    logger.log(Level.INFO, "Started on {0}", this.port);
    
    server.awaitTermination();
  }

  
  /**
   * 
   * @param request
   * @param observer 
   */
  @Override
  public void sayHello(HelloRequest request, StreamObserver<HelloReply> observer) {
    observer.onNext(
        HelloReply.newBuilder().setMessage(
            "Hello, " + request.getName() + ". This is the Java gRPC service.").build());
    observer.onCompleted();
  }  
  
  /**
   * 
   * @param request
   * @param observer 
   */
  @Override
  public void sayGoodbye(EmptyRequest request, StreamObserver<GoodbyeReply> observer) {
    observer.onNext(GoodbyeReply.newBuilder().setMessage("Goodbye! (This is the Java gRPC service.)").build());
    observer.onCompleted();
  }  
  


  
  public static void main(String[] args) throws IOException, InterruptedException {
    new SimplegRPCSampleJavaCppServer().start(args);
  }

  
  /*
   * 
   * @param args 
   */
  private void parseArgs(String[] args) {
    boolean usage = false;
    
    for (String arg : args) {
      if (!arg.startsWith("--")) {
        System.err.println("All arguments must start with '--': " + arg);
        usage = true;
        break;
      }
      
      
      String[] parts = arg.substring(2).split("=", 2);
      String key = parts[0];
      
      if ("help".equals(key)) {
        usage = true;
        break;
      }
      
      if (parts.length != 2) {
        System.err.println("All arguments must be of the form --arg=value");
        usage = true;
        break;
      }
      
      
      String value = parts[1];
      
      if ("port".equals(key)) {
        port = Integer.parseInt(value);
        
      } else {
        System.err.println("Unknown argument: " + key);
        usage = true;
        break;
      }
    }
    
    
    if (usage) {
      SimplegRPCSampleJavaCppServer s = new SimplegRPCSampleJavaCppServer();
      System.out.println(
          "Usage: [ARGS...]"
              + "\n"
              + "\n  --port=PORT           Server port to bind to. Default "
              + s.port);
      System.exit(1);
    }
  }


}
