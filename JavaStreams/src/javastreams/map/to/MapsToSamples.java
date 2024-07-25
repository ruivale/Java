/**
 * <p>
 * Classname: javastreams.map.to.MapsToSamples
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
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
package javastreams.map.to;

import javastreams.util.Product;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * Description:
 * Choosing toMap 
 * Use Collectors.toMap() when:
 *    - You want a simple mapping of stream elements to a map where each element contributes exactly
 *      one key-value pair.
 *    - You need control over how to resolve collisions (when multiple elements map to the same key).
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class MapsToSamples {

  /**
   * This block of code demonstrates the basic usage of Collectors.toMap() to convert a list of
   * Product objects into a map. The products list contains three Product objects. Using a stream,
   * each Product object is mapped to an entry in the resulting map, where the key is the product?s
   * id (obtained using Product::getId) and the value is the product?s name (obtained using
   * Product::getName).
   *
   * @param args
   */
  public static void toMap(String[] args) {

    List<Product> products = List.of(
      new Product(1, "Shirt", 1200.0, "Clothing"),
      new Product(2, "Laptop", 2000.0, "Electronics"),
      new Product(3, "Keyboard", 80.0, "Accesories")
    );

    System.out.println("\ntoMap:\nProducts: "+products);

    // Example 1: Basic usage
    Map<Integer, String> productMap = products.stream().collect(Collectors.toMap(
      Product::getId, // keyMapper
      Product::getName // valueMapper
    ));
    System.out.println("After toMap:\n"+productMap);

  }

  /**
   * Handling duplicates with the merge function in Collectors.toMap() is crucial when dealing with
   * scenarios where multiple elements might map to the same key.
   *
   * This code example code demonstrates handling duplicates with Collectors.toMap() while
   * converting the list of Product objects into a map. The products list contains four Product
   * objects, including two Products (laptops) with different prices, causing a key collision.
   *
   * Using a stream, each Product object is mapped to an entry in the resulting map, where the key
   * is the product?s name (Product::getName) and the value is the product?s price
   * (Product::getPrice). The merge function (existing, replacement) -> existing resolves key
   * collisions by retaining the existing value, ensuring that the first encountered price for each
   * product name is kept.
   *
   * @param args
   */
  public static void handlingDuplicates(String[] args) {

    // Example 2: Handling duplicates with merge function
    List<Product> products = List.of(
      new Product(1, "Shirt", 1200.0, "Clothing"),
      new Product(2, "Laptop", 2000.0, "Electronics"),
      new Product(3, "Keyboard", 80.0, "Accesories"),
      new Product(4, "Laptop", 2500.0, "Electronics") // Duplicate key with different price
    );
    System.out.println("\nhandlingDuplicates:\nProducts: " + products);

    Map<String, Double> productPriceMap = products.stream()
      .collect(Collectors.toMap(Product::getName, Product::getPrice,
        (existing, replacement) -> existing));
    
    System.out.println("After handlingDuplicates:\n" + productPriceMap);

  }

  
  
  public static void main(final String[] args) {
    MapsToSamples.toMap(args);
    MapsToSamples.handlingDuplicates(args);
    
    System.out.println("\n\n");
  }
}
