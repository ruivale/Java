/**
 * <p>
 * Classname: exp.exceptions.ExceptionThrowEx
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

package exp.exceptions;



/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 18, 2015, 10:29:12 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ExceptionThrowEx {

  public ExceptionThrowEx() throws Exception{

    try {
      m();
    } catch (Exception e) {
      throw new Exception(e);
    }

    try {
      m();
    } catch (Exception e) {
      throw e;
    }

  }

  private void m() throws Exception{
    throw new Exception("lixo");
  }

  public static void main(final String[] args)throws Exception{
    final ExceptionThrowEx clazz = new ExceptionThrowEx();
  }
}
