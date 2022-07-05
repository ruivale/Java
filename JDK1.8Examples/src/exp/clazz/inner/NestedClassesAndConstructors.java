/**
 * <p>
 * Classname: exp.clazz.inner.NestedClassesAndConstructors
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

package exp.clazz.inner;

public class NestedClassesAndConstructors{
    private int x;
    private int y;
    NestedClassesAndConstructors(){
        x = 4;
        y = 3;
    }
    NestedClassesAndConstructors(int z){
        new NestedClassesAndConstructors();
        x += z;
        y -= z;
    }
    class Inner{
        Inner(){
            x ++ ;
            y += 2;
        }
        Inner(int i){
            this();
            x -= i;
            y += i;
            System.out.print(x ++ + " " + -- y + " ");
        }
    }
    public static void main(String[] args){
        Inner inner = new NestedClassesAndConstructors(3).new Inner(2);
    }
}