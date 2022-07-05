/**
 * <p>
 * Classname: jdk8examples.streams.StreamsSamples
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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

package jdk8examples.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 8, 2016, 12:34:22 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class StreamsSamples {
  private static final List<String> listItensToRemove = new ArrayList<>(2);
  private static final List<String> list = new ArrayList<>(5);
  private static final List<Integer> listInts = new ArrayList<>(5);


  private static void insertDataInLists(){
    listItensToRemove.clear();
    listItensToRemove.add(String.valueOf(4));
    listItensToRemove.add(String.valueOf(2));

    list.clear();
    list.add(String.valueOf(1));
    list.add(String.valueOf(5));
    list.add(String.valueOf(2));
    list.add(String.valueOf(4));
    list.add(String.valueOf(3));
  }


 /**
  * The StreamsSamples default constructor.
  */
  public StreamsSamples(){
    insertDataInLists();

//    list.stream().peek(it -> System.out.printf("it is %s%n", it));
//    list.stream().peek(new Consumer<String>() {
//      @Override
//      public void accept(String t) {
//        javax.swing.JOptionPane.showMessageDialog(null, "sdkskdjjs");
//      }
//    });
//    list.stream().forEach( (it) -> {System.out.print(it);} );


//    list.stream().peek(StreamsSamples::remove).forEach(System.out::println);




    try {
      list.stream().peek(list::remove).forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
      try {Thread.sleep(267);} catch (InterruptedException interruptedException) {}
    }
    // So, all elements are consumed, and removed correctly. The sorted() operation is a ?stateful
    // intermediate operation?, which means that subsequent operations no longer operate on the backing
    // collection, but on an internal state. It is now ?safe? to remove elements from the list!
    list.stream().sorted().peek(list::remove).forEach(System.out::println);
    System.out.println("----");
    list.stream().forEach(System.out::println);

    System.out.println("|||||||||");
    insertDataInLists();

    // Well? can we really? Let?s proceed with parallel(), sorted() removal:
    list.stream().sorted().parallel().peek(list::remove).forEach(System.out::println);
    System.out.println("----");
    list.stream().forEach(System.out::println);
    // Eek. We didn?t remove all elements!? Free beers (and jOOQ stickers) go to anyone who solves this
    // streams puzzler! This all appears quite random and subtle, we can only suggest that you never
    // actually do modify a backing collection while consuming a stream. It just doesn?t work.


 
    System.out.println();

    System.exit(0);
  }

  public static Object remove(final String str){

    if(listItensToRemove.contains(str)){
      list.remove(str);
    }

    return str;
  }


  public static void main(final String[] args){
    final StreamsSamples clazz = new StreamsSamples();
  }
}
