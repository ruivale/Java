/**
 * <p>
 * Classname:  jdk1_6examples.string.regexp.TestsPatternMatch
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
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
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.string.regexp;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class TestsPatternMatch {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(TestsPatternMatch.class.getName());


 /**
  * The TestsPatternMatch default constuctor.
  */
  public TestsPatternMatch(){

    //final String s = "2009/11/21 12:34:23";
    final String s = "  /  /     :  :  ";

    if(s.matches("\\d{0,2}+/\\d{0,2}+/\\d{0,4}+ \\d{0,2}+:\\d{0,2}+:\\d{0,2}+")){
      System.out.println("IT MATCHES ...");
    }else{
      System.out.println("NO MATCH ...");
    }

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("TestsPatternMatch").append("").toString();
  }

  public static void main(final String[] args){
    final TestsPatternMatch clazz = new TestsPatternMatch();
  }
}
