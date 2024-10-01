/**
 * <p>
 * Classname: com.efacec.es.efarail.cctv.CctvGRpcSimple
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
package com.efacec.es.efarail.cctv;



import com.efacec.es.trp.efarail.cctv.grpc.v1.Cctv;
import com.efacec.es.trp.efarail.cctv.grpc.v1.Cctv.CctvVersion;
import com.efacec.es.trp.efarail.cctv.grpc.v1.Cctv.ListCctvVersionsResponse;
import com.efacec.es.trp.efarail.cctv.grpc.v1.Cctv.VersionsRequest;
import com.efacec.es.trp.efarail.cctv.grpc.v1.OperationGrpc;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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
public class CctvGRpcSimple {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER = Logger.getLogger(CctvGRpcSimple.class.getName());

  /**
   * The CctvGRpcSimple default constructor.
   */
  public CctvGRpcSimple() {
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("CctvGRpcSimple").append("").toString();
  }

  

  
  public static void main(String[] args) {
    
    System.out.println("\n\n\tRunning Cctv gRPC simple client...");
    
    final int nPort = 5555;
    final String strHost = 
      //"172.17.29.93";
      "localhost";
    
    try {
      //https://localhost:7125
      //http://localhost:5003
      final ManagedChannel channel = ManagedChannelBuilder.forAddress(strHost, nPort).usePlaintext().build();
      
      final OperationGrpc.OperationBlockingStub operBlockStub = OperationGrpc.newBlockingStub(channel);
            
      // Creating the Workstation info...
      final Cctv.WorkstationInformation ws = 
          Cctv.WorkstationInformation.newBuilder()
              .setId(Int64Value.of(102))
              .setUserName(StringValue.of("inoss")).build();
      
      // setting the request values...
      final VersionsRequest versionsRequest = 
          VersionsRequest.newBuilder()
              .setWorkstationInfo(ws)
              .setVersionMod(Cctv.VersionMod.VERMOD_ALL).build();
      
      
      final ListCctvVersionsResponse versionReply = operBlockStub.getVersions(versionsRequest);
      
      System.out.println("\t... received something from the server.\n");
      
      for(CctvVersion cctvVersion: versionReply.getCctvVersionsList()){
        System.out.println("\t\tVersion: " + cctvVersion.getDesc());
      }
      
      channel.shutdown();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("\n\n");
  }
}
