/**
 * <p>
 * Classname: exp.lang.innerclasses.Outer
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

package exp.lang.innerclasses;


class Outer {
    private int a = 12;
    private int b = 6;
    Outer() {
        NestedA nestedA = new NestedA(4);
        a = a -12;
    }
    class NestedA {
        NestedA(int y){
            NestedB nestedB = new NestedB(y);
            nestedB.methodB(y, a);
            a= a + b - y;
        }
        private void methodA(int z) {
            System.out.print("-a"+a+z+"-b"+(b-z));
        }
        class NestedB {
            NestedB(int i) {
                a = a - i; b = b +i;
            }
            private void methodB(int x, int z) {
                if(x < 5) {
                    a = a+x+b;
                }
                b = b+z;
                System.out.print("-a"+a+"-b"+b);
            }
        }
    }
    public static void main(String[] args) {
        NestedA nestedA = new Outer().new NestedA(2);
    }
}