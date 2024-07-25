/**
 * <p>
 * Classname: javastreams.map.grouping.MapsGroupingBySamples
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
package javastreams.map.grouping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javastreams.util.Product;

/**
 * <p>
 * Description:
 * Choosing groupingBy
 * Use Collectors.groupingBy() when:
 *    - You need to categorize elements based on some criteria (like grouping words by their lengths
 *      or grouping objects by their attributes).
 *    - You expect multiple elements to map to the same key and want them to be collected into a list 
 *      or another collection.
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class MapsGroupingBySamples {

  /**
   * The Collectors.groupingBy() collector is used to group elements of a stream based on a
   * classifier function. It divides the elements into groups (represented by keys) and collects
   * elements into a map where each key maps to a list of elements.
   *
   * This block of code illustrates the use of Collectors.groupingBy() to categorize a list of
   * Product objects in various ways. The products list contains three items: a shirt, a laptop, and
   * a keyboard. 
   *    - In the first example, the products are grouped by their names, resulting in a map
   *      where each key is a product name and the corresponding value is a list of products with that
   *      name. 
   *    - In the second example, products are grouped by their category, creating a map where
   *      each key is a category and the value is a list of products within that category. 
   *    - The third example demonstrates grouping products by price range based on specified price thresholds.
   *
   * @param args
   */
  public static void groupingBy(String[] args) {

    List<Product> products = List.of(
      new Product(1, "Shirt", 1200.0, "Clothing"),
      new Product(2, "Laptop", 2000.0, "Electronics"),
      new Product(3, "Razor", 10.0, "Accesories"),
      new Product(4, "Keyboard", 80.0, "Accesories")
    );
    
    System.out.println("\ngroupingBy:\n\tproducts: "+products+"\n");


    // Example 1: Grouping by product name
    Map<String, List<Product>> productGroups = products.stream()
      .collect(Collectors.groupingBy(Product::getName));

    System.out.println("byName:\n\t"+productGroups);

    
    // Example 2: Grouping by price category
    Map<String, List<Product>> productByCategory = products.stream()
      .collect(Collectors.groupingBy(Product::getCategory));

    System.out.println("byCategory:\n\t"+productByCategory);

    
    // Example 3: Grouping by price range
    Map<String, List<Product>> priceRangeGroups = products.stream()
      .collect(Collectors.groupingBy(
        p -> {
          if (p.getPrice() < 50.0) {
            return "Cheap";
          } else if (p.getPrice() < 200.0) {
            return "Moderate";
          } else {
            return "Expensive";
          }
        }
      ));
    
    System.out.println("byPriceRange:\n\t"+priceRangeGroups);

  }

  
  
  
  public static void main(final String[] args) {
    MapsGroupingBySamples.groupingBy(args);
    
    System.out.println("\n\n");
  }
}
