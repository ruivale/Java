/**
 * <p>
 * Classname: exp._quiz.MyClass
 * </p>
 *
 * <p>Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
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

package exp._quiz;


public class MyClass {
    static int x;
    String s = "";
    static String s2 = "";
    public MyClass(int  i) {
        x += i;
        s += x;
        s2 += x;
    }
    public static void main(String[] args) {
        new MyClass(2);
        MyClass mc = new MyClass(1);
        new MyClass(4);
        System.out.println(mc.s + mc.s2);
    }
}