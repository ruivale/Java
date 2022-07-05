/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastreams;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The reason peek() didn't work in our first example is that it's an intermediate operation and we
 * didn't apply a terminal operation to the pipeline. Alternatively, we could have used forEach()
 * with the same argument to get the desired behavior:
 *
 * Stream<String> nameStream = Stream.of("Alice", "Bob", "Chuck");
 * nameStream.forEach(System.out::println); 
 * 
 * peek()?s Javadoc page says: ?This method exists mainly
 * to support debugging, where you want to see the elements as they flow past a certain point in a
 * pipeline?.
 *
 * @author 2334
 */
public class Peek {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {   
    Stream.of("one", "two", "three", "four")
      .filter(e -> e.length() > 3)
      .peek(e -> System.out.println("Filtered value: " + e))
      .map(String::toUpperCase)
      .peek(e -> System.out.println("Mapped value: " + e))
      .collect(Collectors.toList());
  }

}
