/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.efacec.trp.tests.grpc.simplegrpcsamplejavacpp;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.EmptyRequest;
import io.grpc.examples.helloworld.GoodbyeReply;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import java.time.Duration;
import java.time.Instant;

/**
 * Client to t
 * 
 * @author 2334
 */
public class SimplegRPCSampleJavaCppClient {
  
  private static final int PORT = 50051;

  private static final String STR_ADD_CPP = "172.19.181.242";
  
  //private static final String STR_ADD_JAVA = "localhost";
  //private static final String STR_ADD_JAVA = "172.25.240.1";
  //private static final String STR_ADD_JAVA = "172.18.64.77";
  //private static final String STR_ADD_JAVA = "172.31.0.1";


  
  public static void main(String[] args) {
    try {
      final ManagedChannel channel = 
          ManagedChannelBuilder.forAddress(STR_ADD_CPP, PORT)
              .usePlaintext()
              .build();
            
      final GreeterGrpc.GreeterBlockingStub greeter = GreeterGrpc.newBlockingStub(channel);
       
      
      {
        final HelloRequest helloRequest = HelloRequest.getDefaultInstance();
        helloRequest.toBuilder().setName("This is a Java client...");      

        final Instant before = Instant.now();
        
        final HelloReply helloReply = greeter.sayHello(helloRequest);

        final Instant after = Instant.now();
        
        System.out.println("\nJava client - received from \"sayHello()\": "+helloReply.getMessage()+
          "\n\tIt took "+(Duration.between(before, after).toMillis())+" millis\n");
      }

      {
        final EmptyRequest emptyRequest = EmptyRequest.getDefaultInstance();
        
        final Instant before = Instant.now();
        
        final GoodbyeReply goodbyeReply = greeter.sayGoodbye(emptyRequest);

        final Instant after = Instant.now();

        System.out.println("\nJava client - received from \"sayGoodbye()\": "+goodbyeReply.getMessage()+
          "\n\tIt took "+(Duration.between(before, after).toMillis())+" millis\n");
      }
             
      channel.shutdown();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
}
