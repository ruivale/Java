/**
 * <p>
 * Classname: exp.web.service.soap.Test
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package exp.web.service.soap;

import exp.web.service.soap.client.HelloClient;



public class Test {
  public static void main(final String[] args) throws Exception{
    System.out.println("startint the service...");
    ServiceStarter.start(args);

    System.out.println("starting the client...");
    HelloClient.start(args);

    System.out.println("\npress any key to exit...");
    System.in.read();

    System.exit(0);
  }
}
