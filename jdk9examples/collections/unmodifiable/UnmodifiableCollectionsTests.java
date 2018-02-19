/**
 * <p>
 * Classname: jdk9examples.collections.unmodifiable.Tests
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
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
package jdk9examples.collections.unmodifiable;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 *
 */
public class UnmodifiableCollectionsTests {

  /**
   * The Tests default constructor.
   */
  public UnmodifiableCollectionsTests() {
    List<String> list = List.of("apple", "bat");
    List<String> list2 = List.of();

    //list.add("kj");
    Set<String> set = Set.of("apple", "bat");
    Set<String> set2 = Set.of();

    Map<Integer, String> emptyMap = Map.of();
    Map<Integer, String> map = Map.of(1, "Apple", 2, "Bat", 3, "Cat");

    Map<Integer, String> emptyEntry = Map.ofEntries();
    Map.Entry<Integer, String> mapEntry1 = Map.entry(1, "Apple");
    Map.Entry<Integer, String> mapEntry2 = Map.entry(2, "Bat");
    Map.Entry<Integer, String> mapEntry3 = Map.entry(3, "Cat");
    Map<Integer, String> mapEntry = Map.ofEntries(mapEntry1, mapEntry2, mapEntry3);

  }

  public static void main(final String[] args) {
    final UnmodifiableCollectionsTests clazz = new UnmodifiableCollectionsTests();
  }
}
