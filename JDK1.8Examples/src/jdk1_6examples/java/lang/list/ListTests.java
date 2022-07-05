/**
 * <p>
 * Classname:  jdk1_6examples.java.lang.list.ListTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
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

package jdk1_6examples.java.lang.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 17/Dez/2010, 18:09:23
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ListTests {

  private final List<Integer> list = new ArrayList<Integer>(10);


  public ListTests(){
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
  }

  /**
   * Returns the station tree node displayed in PInt's navigation panel.
   *
   * @return TlcDefaultMutableTreeNode station tree node.
   */
  public List<Integer> getInts() {
    return Collections.unmodifiableList(this.list);
  }


  public static void main(String[] args) {
    final ListTests lt = new ListTests();

    final List<Integer> _list = lt.getInts();
    for (Integer integer : _list) {
      System.out.println(""+integer);
    }

    _list.add(13);
  }
}
