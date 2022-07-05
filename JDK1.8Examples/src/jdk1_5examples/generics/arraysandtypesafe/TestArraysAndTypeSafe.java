/**
 * <p>
 * Classname:  jdk1_5examples.generics.arraysandtypesafe.TestArraysAndTypeSafe
 * </p>
 *
 * <p>Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package jdk1_5examples.generics.arraysandtypesafe;


/**
 * <p>
 * Description:
 * The implementation of arrays in Java has a hole in its type system. This is one of the rare 
 * cases where Java is not statically type-safe.
 * 
 * Consider this question: If B is a subtype of A, is List<B> a subtype of List<A>? For lists, 
 * the answer is no. Earlier in this article, I explained why this is and how it could go wrong 
 * if you were to consider List<B> a subtype. However, for arrays (a very similar situation), 
 * Java does consider the list to be a subtype. This introduces a potential type problem. 
 * Consider the following code:
 * 
 * <code>
 * A[] aa;
 * B[] ba = new B[3];
 * aa = ba; // allowed! B[] is subtype of A[]
 * aa[0] = new B();
 * aa[1] = new A(); // java.lang.ArrayStoreException: A
 * </code>
 * 
 * The last line in this example represents a type error: I am trying to insert an A object into 
 * an array of B. However, the assignment in the third line is allowed. This problem is picked up 
 * only at runtime, not at compile time, breaking Java?s static type safety. When designing generic 
 * classes, the Java team decided to be more conservative and detect the equivalent problem at 
 * compile time.
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class TestArraysAndTypeSafe {

  public static void main(final String[] args){
    A[] aa;
    B[] ba = new B[3];
    aa = ba; // allowed! B[] is subtype of A[]
    aa[0] = new B();
    aa[1] = new A(); // java.lang.ArrayStoreException: A

  }
}
