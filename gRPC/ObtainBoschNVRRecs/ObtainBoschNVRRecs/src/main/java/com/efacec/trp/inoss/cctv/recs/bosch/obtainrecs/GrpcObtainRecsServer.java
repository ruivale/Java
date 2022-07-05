/**
 * <p>
 * Classname: com.efacec.trp.inoss.cctv.recs.bosch.obtainrecs.GrpcObtainRecsServer
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

import io.grpc.Server;
import io.grpc.ServerBuilder;
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
public class GrpcObtainRecsServer {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER = Logger.getLogger(GrpcObtainRecsServer.class.getName());

  /**
   * 
   */
  public void start() {
    try {
      final Server server = ServerBuilder
        .forPort(8080)
        .addService(new ObtainRecsServiceImpl()).build();

      System.out.println("Starting obtain recs server...");
      server.start();
      System.out.println("... obtain recs server started!");
      server.awaitTermination();

      Runtime.getRuntime()
        .addShutdownHook(new Thread() {
          @Override
          public void run() {
            System.err.println("shutting down server");
            try {
              if (server != null) {
                server.shutdown()
                  .awaitTermination(30, TimeUnit.SECONDS);
              }
            } catch (InterruptedException e) {
              e.printStackTrace(System.err);
            }
            System.err.println("server shutted down");
          }
        });
    } catch (Exception iOException) {
      iOException.printStackTrace();
    }

  }

  /**
   * Here, again we use the builder to create a gRPC server on port 8080 and add the
   * ObtainRecsServiceImpl service that we defined. start() would start the server. In our example,
   * we will call awaitTermination() to keep the server running in the foreground blocking the
   * prompt.
   *
   * @param args
   */
  public static void main(String[] args) {
    new GrpcObtainRecsServer().start();
  }
}
