/**
 * <p>
 * Classname:  jdk1_6examples.java.util.collections.concurrencyexception.ConcurrentModificationTest
 * </p>
 *
 * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A.
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
package jdk1_6examples.java.util.collections.concurrencyexception;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * The class explains how concurrent modification is detected in List.
 */
public class ConcurrentModificationTest {

  public static void main(String[] args) {

// Instantiating the list sets the modCount field of list to 0
// modCount is a protected integer variable defined in the AbstractList class.

    List<Object> myList = new LinkedList<Object>();

    myList.add(new Object()); // modCount incremented to 1
    myList.add(new Object()); // modCount incremented to 2
    myList.add(new Object()); // modCount incremented to 3

// Getting an iterator sets the value of expectedModCount field
// equal to that of the modCount field of list.

    Iterator<Object> itr1 = myList.iterator(); //itr1: expectedModCount = 3
    Iterator<Object> itr2 = myList.iterator(); //itr2: expectedModCount = 3

// The first Iterator traverses the list once
    itr1.next();

// The second iterator traverses and removes the first element
    itr2.next();
    itr2.remove();
// In the previous step Itr2 modifies the list and sets the
// expectedModCount field to 4. The modCount field of the list
// has also been now incremented to 4

// The first iterator again tries to traverse the list
    itr1.next();

// java.util.ConcurrentModificationException is thrown at this stage
// because itr1 finds its expectedModCount (which is still 3) to be not
// matching with the modCount field of the list
  }
}
