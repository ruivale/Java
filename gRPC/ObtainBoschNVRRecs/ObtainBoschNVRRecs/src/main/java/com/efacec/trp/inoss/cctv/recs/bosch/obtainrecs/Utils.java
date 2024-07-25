/**
 * <p>
 * Classname:  com.efacec.trp.inoss.cctv.recs.bosch.obtainrecs.Utils
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

package com.efacec.trp.inoss.cctv.recs.bosch.obtainrecs;


import cctv.recs.bosch.obtainrecs.ObtainRecsRequest;
import cctv.recs.bosch.obtainrecs.ObtainRecsResponse;
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
public class Utils {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(Utils.class.getName());


  /**
   *     trackId_ = 0;
    sourceId_ = 0;
    sourceUrl_ = "";
    startTimeT_ = 0L;
    endTimeT_ = 0L;
    * 
   * @param request
   * @return 
   */
  static String toString(ObtainRecsRequest request) {
    return "Request{trck:"+
      request.getTrackId() + ",srcid:"+ 
      request.getSourceId() + ",srcurl:"+ 
      request.getSourceUrl() + " ["+ 
      request.getStartTimeT() + ","+ 
      request.getEndTimeT() + "]].";
  }

  /**
   *     trackId_ = 0;
    sourceId_ = 0;
    sourceUrl_ = "";
    startTimeT_ = 0L;
    endTimeT_ = 0L;
    nvrId_ = 0;
    trackName_ = "";
    trackSourceId_ = 0;
    trackSourceUrl_ = "";
    sourceName_ = "";
    * 
   * @param response
   * @return 
   */
  static String toString(ObtainRecsResponse response) {
    return "Response{trck:"+
      response.getTrackId() + ",srcid:"+ 
      response.getSourceId() + ",srcurl:"+ 
      response.getSourceUrl() + " ["+ 
      response.getStartTimeT() + ","+ 
      response.getEndTimeT() + "],nvr:" +      
      response.getNvrId() + ",trckname:" +
      response.getTrackName() + ",trcksrcid:" +
      response.getTrackSourceId() + ",:trcksrcurl" +
      response.getTrackSourceUrl() + ",srcname:" +
      response.getSourceName() + "]"
      ;  
  
  }


 /**
  * The Utils default constructor.
  */
  public Utils(){
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("Utils").append("").toString();
  }

  public static void main(final String[] args){
    final Utils clazz = new Utils();
  }
}
