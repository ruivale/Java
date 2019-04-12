/**
 * <p>
 * Classname: exp.web.service.soap.Hello
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

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class Hello {

  public String sayHello(String name) {

    return "Hello " + name;

  }

}
