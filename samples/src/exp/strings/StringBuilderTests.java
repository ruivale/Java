/**
 * <p>
 * Classname: exp.strings.StringBuilderTests
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
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
package exp.strings;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Dec 19, 2013, 3:38:29 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class StringBuilderTests {

  int a = 1;
  String b = "";
  boolean c = true;
  final int af = 1;
  final String bf = "";
  final boolean cf = true;
  int a1;
  String b1;
  boolean c1;

  public static void main(final String[] args) {
    String x = "A";
    String y = "B";
    String z = x + y;

    System.out.println("Z:" + z + " lklskdls " + x);

    System.out.println("sdkjskdjksjjsdksjk" + "sdkjskjd"
                       + "kskjdksj");
  }

  public String toString11() {
    return "with value {a:" + a + ", b:" + b + ", c: " + c + "}";
  }

  public String toString12() {
    final StringBuilder sb = new StringBuilder(100);
    return sb.append("with value {a:").append(a)
        .append(", b:").append(b)
        .append(", c:").append(c)
        .append("}").toString();
  }

  public String toString21() {
    return "final {af:" + af + ", bf:" + bf + ", cf: " + cf + "}";
  }

  public String toString22() {
    final StringBuilder sb = new StringBuilder(100);
    return sb.append("final {af:").append(af)
        .append(", bf:").append(bf)
        .append(", cf:").append(cf)
        .append("}").toString();
  }

  public String toString31() {
    return "nothing {a1:" + a1 + ", b1:" + b1 + ", c: " + c1 + "}";
  }

  public String toString32() {
    final StringBuilder sb = new StringBuilder(100);
    return sb.append("nothing {a1:").append(a1)
        .append(", b1:").append(b1)
        .append(", c1:").append(c1)
        .append("}").toString();
  }
}
