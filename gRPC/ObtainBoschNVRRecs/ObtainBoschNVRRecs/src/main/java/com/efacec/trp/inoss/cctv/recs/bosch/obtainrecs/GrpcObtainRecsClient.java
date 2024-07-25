/**
 * <p>
 * Classname: com.efacec.trp.inoss.cctv.recs.bosch.obtainrecs.GrpcObtainRecsClient
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
package com.efacec.trp.inoss.cctv.recs.bosch.obtainrecs;

import cctv.recs.bosch.obtainrecs.ObtainRecsRequest;
import cctv.recs.bosch.obtainrecs.ObtainRecsResponse;
import cctv.recs.bosch.obtainrecs.ObtainRecsServiceGrpc;
import cctv.recs.bosch.obtainrecs.ObtainRecsServiceGrpc.ObtainRecsServiceBlockingStub;
import cctv.recs.bosch.obtainrecs.ObtainRecsServiceGrpc.ObtainRecsServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * <p>
 * Description:
 * In this article, we've seen how to use streaming in gRPC. Streaming is a powerful feature that 
 * allows clients and servers to communicate by sending multiple messages over a single connection. 
 * Furthermore, the messages are received in the same order as they were sent, but either side can 
 * read or write the messages in any order they wish.
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class GrpcObtainRecsClient {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER = Logger.getLogger(GrpcObtainRecsClient.class.getName());
  
  private ObtainRecsServiceBlockingStub blockingStub;
  private ObtainRecsServiceStub nonBlockingStub;
  
  private List<ObtainRecsRequest> listObtainrecsRequest = 
    Arrays.asList(
      ObtainRecsRequest.newBuilder().setTrackId(2).setSourceId(2).setSourceUrl("SourceUrl 2").setStartTimeT(22222222).setEndTimeT(22222222).build(),
      ObtainRecsRequest.newBuilder().setTrackId(3).setSourceId(3).setSourceUrl("SourceUrl 3").setStartTimeT(33333333).setEndTimeT(33333333).build(),
      ObtainRecsRequest.newBuilder().setTrackId(4).setSourceId(4).setSourceUrl("SourceUrl 4").setStartTimeT(44444444).setEndTimeT(44444444).build(),
      ObtainRecsRequest.newBuilder().setTrackId(6).setSourceId(6).setSourceUrl("SourceUrl 6").setStartTimeT(66666666).setEndTimeT(66666666).build(),
      ObtainRecsRequest.newBuilder().setTrackId(9).setSourceId(9).setSourceUrl("SourceUrl 9").setStartTimeT(99999999).setEndTimeT(99999999).build()      
    );
  
  
  /**
   * gRPC provides a channel construct that abstracts out the underlying details like connection,
   * connection pooling, load balancing, etc. We'll create a channel using ManagedChannelBuilder.
   * Here, we specify the server address and port. We'll be using plain text without any encryption:
   * 
   * @param channel 
   */
  public GrpcObtainRecsClient( final ManagedChannel channel){
    
    this.blockingStub = ObtainRecsServiceGrpc.newBlockingStub(channel);
    this.nonBlockingStub = ObtainRecsServiceGrpc.newStub(channel);    
    
  }


  /**
   * We use blockingStub::obtainRecsServerStreaming to make a synchronous request. 
   * We get back a list of ObtainRecsResponse with the Iterator.
   */
  private void obtainRecsServerSideStreaming() throws InterruptedException {

    final ObtainRecsRequest request = 
      ObtainRecsRequest.newBuilder()
        .setTrackId(9999)
        .setSourceId(999)
        .setSourceUrl("SourceUrl")
        .setStartTimeT(88888888)
        .setEndTimeT(99999999)
        .build();

    Iterator<ObtainRecsResponse> responses;
    
    try {
      System.out.println("Client.obtainRecsServerSideStreaming() REQUEST: " + Utils.toString(request));

      responses = this.blockingStub.obtainRecsServerStreaming(request);

      for (int i = 1; responses.hasNext(); i++) {
        final ObtainRecsResponse response = responses.next();
        
        System.out.println("Client.obtainRecsServerSideStreaming() RESPONSE #" + i + ": " + Utils.toString(response));
      }
    } catch (StatusRuntimeException e) {
      System.out.println("Client.obtainRecsServerSideStreaming() RPC failed: " + e.getStatus());
    }

  }
    
  
  /**
   * The client sends a stream of ObtainRecsRequest to the server and gets back a ObtainRecsResponse
   * with some statistics:
   * 
   * As we did with the server example, we use StreamObservers to send and receive messages.
   * The requestObserver uses the non-blocking stub to send the list of ObtainRecsRequest to the server.
   * With responseObserver, we get back the ObtainRecsResponse with some statistics.
   * 
   * @throws InterruptedException 
   */
  private void obtainRecsClientSideStreaming() throws InterruptedException {
    
    StreamObserver<ObtainRecsResponse> responseObserver = new StreamObserver<ObtainRecsResponse>() {
      @Override
      public void onNext(final ObtainRecsResponse response) {
        System.out.println("Client.obtainRecsClientSideStreaming(..) RESPONSE Got: " + Utils.toString(response));
      }

      @Override
      public void onCompleted() {
        System.out.println("Finished Client.obtainRecsClientSideStreaming(..)");
      }

      @Override
      public void onError(final Throwable thrwbl) {
        System.err.println("Client.obtainRecsClientSideStreaming(..): Exception:\n" + thrwbl);
      }
      
    };

    
    final StreamObserver<ObtainRecsRequest> requestObserver = 
        this.nonBlockingStub.obtainRecsClientStreaming(responseObserver);
    
    try {
      for (ObtainRecsRequest request : this.listObtainrecsRequest) {
        System.out.println("GrpcObtainRecsClient.obtainRecsClientSideStreaming(..) REQUEST: " + Utils.toString(request));
        
        requestObserver.onNext(request);
      }
    } catch (RuntimeException e) {
      requestObserver.onError(e);
      throw e;
    }
    
    requestObserver.onCompleted();
  }
  
  /**
   * The client sends a stream of Stocks and gets back a list of prices for each Stock.
   * 
   * The implementation is quite similar to the client-side streaming case. We send the ObtainRecsRequest 
   * with the requestObserver — the only difference is that now we get multiple responses with the 
   * responseObserver. 
   * The responses are decoupled from the requests — they can arrive in any order.
   * 
   * @throws InterruptedException 
   */
  private void obtainRecsBidirectionalStreaming() throws InterruptedException {
    StreamObserver<ObtainRecsResponse> responseObserver = new StreamObserver<ObtainRecsResponse>() {
      @Override
      public void onNext(ObtainRecsResponse response) {
        System.err.println("Client.obtainRecsBidirectionalStreaming(..) RESPONSE Got: " + Utils.toString(response));
      }

      @Override
      public void onCompleted() {
        System.err.println("Finished Client.obtainRecsBidirectionalStreaming(..)");
      }

      @Override
      public void onError(final Throwable thrwbl) {
        System.err.println("Client.obtainRecsBidirectionalStreaming(..): Exception:\n" + thrwbl);
      }
    };

    StreamObserver<ObtainRecsRequest> requestObserver = 
        this.nonBlockingStub.obtainRecsBidirectionalStreaming(responseObserver);
    
    try {
      for (ObtainRecsRequest request :  this.listObtainrecsRequest) {
        System.out.println("Client.obtainRecsClientSideStreaming(..) REQUEST: " +  Utils.toString(request));
        
        requestObserver.onNext(request);
        
        Thread.sleep(200);
        
      }
    } catch (RuntimeException e) {
      requestObserver.onError(e);
      throw e;
    }
    
    requestObserver.onCompleted();
  }  
  

  /**
   * 
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    // gRPC provides a channel construct that abstracts out the underlying details like connection,
    // connection pooling, load balancing, etc. We'll create a channel using ManagedChannelBuilder.
    // Here, we specify the server address and port. We'll be using plain text without any encryption:
    final ManagedChannel channel = 
        ManagedChannelBuilder.forAddress("localhost", 8080)
            .usePlaintext()
            .build();

    try {
       
      GrpcObtainRecsClient client = new GrpcObtainRecsClient(channel);

//      client.obtainRecsServerSideStreaming();
//      client.obtainRecsClientSideStreaming();
      client.obtainRecsBidirectionalStreaming();


      Thread.sleep(5000);
      
    } catch(Exception exc){
      exc.printStackTrace();
      
    } finally {
      channel.shutdownNow().awaitTermination(20, TimeUnit.SECONDS);
    }    

    
  }
    
}
