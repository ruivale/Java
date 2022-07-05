/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exp.collections.list;

import java.lang.String;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author 2334
 */
public class SortingLists {

  static Comparator<User> f1 = (x, y) -> x.getLastName().compareToIgnoreCase(y.getLastName());

  static Comparator<User> f2 = (x, y) -> x.getFirstName().compareToIgnoreCase(y.getFirstName());
  static Comparator<User> f3 = (x, y) -> Integer.compare(x.getAge(),y.getAge());

  static Comparator<User> f4 = f1.thenComparing(f2);

  static Comparator<User> f5 = f1.thenComparing(f2).thenComparing(f3);

  static Comparator<User> f6 = f5.reversed();



  static void SimpleSort() {
    List alist = Arrays.asList("guava", "orange", "apple", "mango", "grapes");
    System.out.println("List: " + alist);
    alist.sort(null);
    System.out.println("\tSorted list: " + alist);
  }

  static void SimpleNumericSort() {
    List blist = Arrays.asList(95, 277, 23, 134, 9, 33);
    System.out.println("List: " + blist);
    Collections.sort(blist);
    System.out.println("\tSorted list: " + blist);
  }

  static void IgnoringCaseSort() {
    List<String> alist = Arrays.asList("apple", "Guava", "Orange", "mango", "grapes");
    System.out.println("List: " + alist);
    alist.sort(null);
    System.out.println("\tSorted list: " + alist);
    alist.sort((x, y) -> {
      return x.compareToIgnoreCase(y);
    });
    System.out.println("\tIgnoring case: " + alist);

    alist = Arrays.asList("apple", "Guava", "Orange", "mango", "grapes");
    alist.sort(String::compareToIgnoreCase);
    System.out.println("\tIgnoring case (using Java8 Lambdas): " + alist);
  }

  static void UserSort(boolean shouldCompareEqualUsersAge) {
    List<User> users = Arrays.asList(
            new User("Chris", "Pruitt", 34),
            new User("Matt", "Bates", 15),
            new User("John", "Wagner", 82),
            new User("Vernon", "McGuire", 31),
            new User("Mary", "Bates", 37),
            new User("Mary", "Bates", 23));
    System.out.println("List: " + users);
    users.sort((x, y) -> {
      if (shouldCompareEqualUsersAge) {
        int r = x.getLastName().compareToIgnoreCase(y.getLastName());
        if ( r != 0 ){
          r = Integer.compare(x.getAge(),y.getAge());
        }
        return r;

      } else {
        return x.getLastName().compareToIgnoreCase(y.getLastName());
      }
    });
    System.out.println("\tSorted List: " + users);
  }

  static void UserComplexSort(boolean reverseSort) {
    List<User> users = Arrays.asList(
            new User("Chris", "Pruitt", 34),
            new User("Matt", "Bates", 15),
            new User("John", "Wagner", 82),
            new User("Vernon", "McGuire", 31),
            new User("Mary", "Bates", 37),
            new User("Mary", "Bates", 23));
    System.out.println("List: " + users);


    String strSortComparator;

    if(reverseSort){
      strSortComparator = "f6";
      users.sort(f6);
    }else{
      strSortComparator = "f5";
      users.sort(f5);
    }

    System.out.println("\tSorted List ("+strSortComparator+"): " + users);
  }




  public static void main(String[] args) {
    SimpleSort();
    SimpleNumericSort();
    IgnoringCaseSort();
    IgnoringCaseSort();
    UserSort(false /*shouldCompareEqualUsersAge*/);
    UserSort(true /*shouldCompareEqualUsersAge*/);
    UserComplexSort(false /*reverse sort*/);
    UserComplexSort(true /*reverse sort*/);
  }

}
