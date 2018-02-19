/**
 * <p>
 * Classname: jdk8examples.traits.BeryNaiceTraits
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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
package jdk8examples.traits;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


/**
 * <p>
 * Description: I will show you a neat trick that you can use if you ever need to stream over a
 * collection of different objects and separate those that fulfill a number of traits.
 * Say that you have two noun classes Person and Elephant. There is no reason really why people and
 * elephants should belong to the same super class; elephants are intelligent four-legged creatures and
 * most humans are not. You might still find the two of them in the same computer system and sometimes
 * you even need to store them in the same collection. One way of operating on this collection of various
 * living beings without making them share a common ancestor, (which would totally be just a theory),
 * you can give them similar traits.
 *
 * </p>
 * <p>
 * Created on Jan 29, 2016, 5:13:35 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class BeryNaiceTraits {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(BeryNaiceTraits.class.getName());

  /**
   * 
   */
  public BeryNaiceTraits() {

    // Using the Abstract Document Pattern, the trait can set and get the attribute "name" from a map.
    // If we now want to iterate over our collection of many living things that might or might not
    // implement our specified traits, we can easily do it like this:
    final Set<Object> livingBeings = new HashSet<>(3);

    livingBeings.add(new Person("eve", 18, 76));
    livingBeings.add(new Person("adam", 21, 85));
    livingBeings.add(new Elephant("dumbo"));

    // Using the and character (&) we can cast instances that implement all the
    // required traits into a dynamic type, without them sharing an ancestor.
    livingBeings.stream()
        .filter(HasName.class::isInstance)
        .filter(HasAge.class::isInstance)
        .filter(HasWeight.class::isInstance)
        .map(p -> (HasName & HasAge & HasWeight) p)
        .forEach(p
            -> System.out.println(
                p.getName() + " is " + p.getAge() + " years old and weighs " + p.getWeight() + " kgs."
            )
        );
  }

  public static void main(final String[] args) {
    final BeryNaiceTraits clazz = new BeryNaiceTraits();
  }
}
