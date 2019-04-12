/**
 * <p>
 * Classname:  jdk1_6examples.java.lang.string.StringTestsClass
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

package jdk1_6examples.java.lang.string;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class StringTestsClass {

  String s1;
  String s2;
  String s3;

  String ss = "11111"+"22222"+"33333";
  String sbui = new StringBuilder().append("1111").append("2222").append("3333").toString();
  String sb = new StringBuffer().append("111").append("222").append("333").toString();
  String sb2 = new StringBuffer().append("111").append(s2).append("222").append(s3).append("333").toString();
  String ss1 = s1+" ahah "+s2+" bhbh "+s3;
  String sbui1 = new StringBuilder().append(s1).append(s2).append(s3).toString();
  String sbui2 = new StringBuilder().append(s1).append("111").append(s2).append("222").append(s3).toString();
  String sb1 = new StringBuffer().append(s1).append(s2).append(s3).toString();

  public static void main(final String[] args){
    final StringTestsClass clazz = new StringTestsClass();
  }
}
