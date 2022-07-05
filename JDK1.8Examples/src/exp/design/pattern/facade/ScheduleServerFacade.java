/**
 * <p>
 * Classname: exp.design.pattern.facade.ScheduleServerFacade
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2019 EFACEC SE
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
package exp.design.pattern.facade;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ScheduleServerFacade {

  private final ScheduleServer scheduleServer;

  public ScheduleServerFacade(ScheduleServer scheduleServer) {
    this.scheduleServer = scheduleServer;
  }

  public void startServer() {
    scheduleServer.startBooting();
    scheduleServer.readSystemConfigFile();
    scheduleServer.init();
    scheduleServer.initializeContext();
    scheduleServer.initializeListeners();
    scheduleServer.createSystemObjects();
  }

  public void stopServer() {
    scheduleServer.releaseProcesses();
    scheduleServer.destory();
    scheduleServer.destroySystemObjects();
    scheduleServer.destoryListeners();
    scheduleServer.destoryContext();
    scheduleServer.shutdown();
  }
}
