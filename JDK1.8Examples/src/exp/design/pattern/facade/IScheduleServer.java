/**
 * <p>
 * Classname:  exp.design.pattern.facade.IScheduleServer
 * </p>
 *
 * <p>Copyright: Copyright (c) 2019 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package exp.design.pattern.facade;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public interface IScheduleServer {
  
  void startBooting();
  void readSystemConfigFile();
  void init();
  void initializeContext();
  void initializeListeners();
  void createSystemObjects();  
  
  void releaseProcesses();
  void destory();
  void destroySystemObjects();
  void destoryListeners();
  void destoryContext();
  void shutdown();

}
