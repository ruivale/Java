package jdk8examples.collections.tomap;

import static java.lang.System.out;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



/**
 * Demonstrates conversion of Java collections to Java Maps.
 */
public class CollectionToMapDemo {

  /**
   * Multiple instances of Book, a class that lacks a proper
   * equals(Object) method, but for which its getIsbn() method
   * is assumed to return a unique identifier for each instance.
   */
  private static final Book[] books;

  static {
    books = new Book[]{
      new Book("978-0-201-31005-4", "Effective Java", 1),
      new Book("978-0-321-35668-0", "Effective Java", 2),
      new Book("978-0-13-468599-1", "Effective Java", 3)
    };
  }

  /**
   * Convert provided array of Book instances to Map of each Book's ISBN to
   * that instance of the Book.
   *
   * @param booksArray Array of Book instances.
   *
   * @return Map of each book's ISBN (key) to the book's full instance (value).
   */
  private static Map<String, Book> convertArrayToMap(final Book[] booksArray) {
    return Arrays.stream(books).collect(
      Collectors.toMap(Book::getIsbn, book -> book));
  }

  /**
   * Convert provided List of Book instances to Map of each Book's ISBN to
   * that instance of the Book.
   *
   * @param booksList List of Book instances.
   *
   * @return Map of each book's ISBN (key) to the book's full instance (value).
   */
  private static Map<String, Book> convertListToMap(final List<Book> booksList) {
    return booksList.stream().collect(
      Collectors.toMap(Book::getIsbn, book -> book));
  }

  /**
   * Convert provided Set of Book instances to Map of each Book's ISBN to
   * that instance of the Book.
   *
   * @param booksSet Set of Book instances.
   *
   * @return Map of each book's ISBN (key) to the book's full instance (value).
   */
  private static Map<String, Book> convertSetToMap(final Set<Book> booksSet) {
    return booksSet.stream().collect(
      Collectors.toMap(Book::getIsbn, book -> book));
  }

  public static void main(final String[] arguments) {
    out.println("ARRAY->MAP:\n" + convertArrayToMap(books));

    final List<Book> booksList = Arrays.asList(books);
    out.println("LIST->MAP:\n" + convertListToMap(booksList));

    final Set<Book> booksSet
      = new HashSet<>(Arrays.stream(books).collect(Collectors.toSet()));
    out.println("SET->MAP:\n" + convertSetToMap(booksSet));
  }
}
