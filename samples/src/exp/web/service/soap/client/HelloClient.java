/**
 * <p>
 * Classname: exp.web.service.soap.client.HelloClient
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.� Frederico Ulrich ? Ap. 3078
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
package exp.web.service.soap.client;


public class HelloClient {

  public static void start(String[] args) {
    HelloService service = new HelloService();
    Hello hello = service.getHelloPort();
    String text = hello.sayHello("hany");
    System.out.println(text);
  }


  public static void main(String[] args) {
    HelloClient.start(args);
  }
}
