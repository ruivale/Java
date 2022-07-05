/**
 * <p>
 * Classname: com.efacec.trp.inoss.cctv.recs.bosch.obtainrecs.ObtainRecsServiceImpl
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

import com.efacec.trp.inoss.cctv.recs.bosch.obtainrecs.ObtainRecsServiceGrpc.ObtainRecsServiceImplBase;
import io.grpc.stub.StreamObserver;
import java.util.Calendar;
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
public class ObtainRecsServiceImpl extends ObtainRecsServiceImplBase {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER = Logger.getLogger(ObtainRecsServiceImpl.class.getName());

  
  /**
   * The method creates a ObtainRecsResponse. For each rec, it sends a message to the client
   * invoking responseObserver::onNext. It uses reponseObserver::onCompleted to signal that it's
   * done with the RPC.
   *
   * @param request
   * @param responseObserver
   */
  @Override
  public void obtainRecsServerStreaming(
      final ObtainRecsRequest request,
      final StreamObserver<ObtainRecsResponse> responseObserver) {

    System.out.println("ServiceImpl.obtainRecsServerStreaming(..)...");
    
    for (int i = 1; i <= 5; i++) {
      final ObtainRecsResponse response = ObtainRecsResponse.newBuilder()
        .setTrackId(request.getTrackId())
        .setSourceId(request.getSourceId())
        .setSourceUrl(request.getSourceUrl())
        .setStartTimeT(request.getStartTimeT())
        .setEndTimeT(request.getEndTimeT())
        .setNvrId(i)
        .setTrackName("TrackName " + i)
        .setTrackSourceId(i)
        .setTrackSourceUrl("TrackSourceUrl " + i)
        .setSourceName("SourceName " + i)
        .build();

      responseObserver.onNext(response);
      
      System.out.println("\tServiceImpl.obtainRecsServerStreaming(..). Sending: " + Utils.toString(response));

      try {
        Thread.sleep(1000);
      } catch (InterruptedException exc) {
      }
    }

    responseObserver.onCompleted();
    
    System.out.println("...ObtainRecsServiceImpl.obtainRecsServerStreaming(..)");       

  }

  /**
   * The method gets a StreamObserver<ObtainRecsResponse> as a parameter to respond to the client.
   * It returns a StreamObserver<ObtainRecsRequest>, where it processes the client request messages.
   * The returned StreamObserver<ObtainRecsRequest> overrides onNext() to get notified each time the
   * client sends a request. The method StreamObserver<ObtainRecsRequest>.onCompleted() is called
   * when the client has finished sending all the messages. With all the ObtainRecsRequest messages
   * that we have received, we do stuff, create a ObtainRecsResponse, and invoke
   * responseObserver::onNext to deliver the result to the client. Finally, we override
   * StreamObserver<ObtainRecsRequest>.onError() to handle abnormal terminations.
   *
   * @param responseObserver
   * @return
   */
  @Override
  public StreamObserver<ObtainRecsRequest> obtainRecsClientStreaming(
    final StreamObserver<ObtainRecsResponse> responseObserver) {

    System.out.println("ServiceImpl.obtainRecsClientStreaming(..)");       

    return new io.grpc.stub.StreamObserver<ObtainRecsRequest>() {
      int count;

      @Override
      public void onNext(final ObtainRecsRequest request) {
        this.count++;
        
        System.out.println("ServiceImpl.obtainRecsClientStreaming(..) Count=" + this.count);   
      }

      @Override
      public void onCompleted() {
        final ObtainRecsResponse obtainRecsResponse = 
          ObtainRecsResponse.newBuilder()
            .setTrackId(this.count)
            .setSourceId(this.count)
            .setSourceUrl("SourceUrl " + this.count)
            .setStartTimeT(Calendar.getInstance().getTimeInMillis() - (1000 * this.count))
            .setEndTimeT(Calendar.getInstance().getTimeInMillis() + (1000 * this.count))
            .setNvrId(this.count)
            .setTrackName("TrackName " + this.count)
            .setTrackSourceId(this.count)
            .setTrackSourceUrl("TrackSourceUrl " + this.count)
            .setSourceName("SourceName " + this.count)
            
            .build();
        
        responseObserver.onNext(obtainRecsResponse);        
        
        System.out.println("\tServiceImpl.obtainRecsClientStreaming(..) onNext(..):" + Utils.toString(obtainRecsResponse));   

        responseObserver.onCompleted();
        
        System.out.println("ServiceImpl.obtainRecsClientStreaming(..) onCompleted.");          
      }

      @Override
      public void onError(final Throwable thrwbl) {
        System.err.println("ServiceImpl.obtainRecsClientStreaming(..): \n" + thrwbl);
      }
    };    
  }

  /**
   * We have the same method signature as in the previous example. What changes is the
   * implementation: - we don't wait for the client to send all the messages before we respond.
   *
   * In this case, we invoke responseObserver::onNext immediately after receiving each incoming
   * message, and in the same order that it was received. It's important to notice that we could
   * have easily changed the order of the responses if needed.
   *
   * @param responseObserver
   * @return
   */
  @Override
  public StreamObserver<ObtainRecsRequest> obtainRecsBidirectionalStreaming(
    StreamObserver<ObtainRecsResponse> responseObserver) {

    System.out.println("ServiceImpl.bidirectionalStreaming(..)");       

    return new StreamObserver<ObtainRecsRequest>() {
      
      @Override
      public void onNext(final ObtainRecsRequest request) {
        System.out.println("ServiceImpl.bidirectionalStreaming(..) onNext(" + Utils.toString(request) + ")...");   
        
        for (int count = 1; count <= 5; count++) {
          final ObtainRecsResponse obtainRecsResponse = 
              ObtainRecsResponse.newBuilder()
                  .setTrackId(request.getTrackId())
                  .setSourceId(request.getSourceId())
                  .setSourceUrl(request.getSourceUrl())
                  .setStartTimeT(request.getStartTimeT())
                  .setEndTimeT(request.getEndTimeT())
                  .setNvrId(count)
                  .setTrackName("TrackName " + count)
                  .setTrackSourceId(count)
                  .setTrackSourceUrl("TrackSourceUrl " + count)
                  .setSourceName("SourceName " + count)

                  .build();
          
          responseObserver.onNext(obtainRecsResponse);

          System.out.println("\tServiceImpl.bidirectionalStreaming(..) onNext(..) Sending: " + Utils.toString(obtainRecsResponse));   
        }

        System.out.println("...ServiceImpl.bidirectionalStreaming(..) onNext");   
      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();

        System.out.println("ServiceImpl.bidirectionalStreaming(..) onCompleted()");         
      }

      @Override
      public void onError(final Throwable thrwbl) {
        System.err.println("ServiceImpl.bidirectionalStreaming(..): \n" + thrwbl);
      }
    };
  }
}
