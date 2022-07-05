/**
 * <p>
 * Classname: exp.refactoring.RefactoringTests
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

package exp.refactoring;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Dec 9, 2015, 6:55:30 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class RefactoringTests {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(RefactoringTests.class.getName());


  private String strValue = "";

 /**
  * The RefactoringTests default constructor.
  */
  public RefactoringTests(){
  }

  private void methodA(final String strValue){

    if(strValue.equals(strValue)){
      System.out.println("Different");
    }

    System.out.println(strValue);
  }


  public static void main(final String[] args){
    final RefactoringTests clazz = new RefactoringTests();

    clazz.methodA("licxo");
  }
}
