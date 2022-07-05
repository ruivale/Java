package jdk8examples.collections.tomap;


/**
 * Represents a book, but does not override {@code equals(Object)}
 * or {@code hashCode()}.
 */
public class Book {

  /** International Standard Book Number (ISBN-13). */
  final String isbn;

  /** Title of book. */
  final String title;

  /** Edition of book. */
  final int edition;

  /**
   * Constructor.
   *
   * @param newIsbn    International Standard Book Number (-13).
   * @param newTitle   Title.
   * @param newEdition Edition.
   */
  public Book(final String newIsbn,
              final String newTitle,
              final int newEdition) {
    isbn = newIsbn;
    title = newTitle;
    edition = newEdition;
  }

  /**
   * Provide ISBN-13 identifier associated with this book.
   *
   * @return ISBN-13 identifier.
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * Provide title of this book.
   *
   * @return Book's title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Provide edition of this book.
   *
   * @return Book's edition.
   */
  public int getEdition() {
    return edition;
  }

  @Override
  public String toString() {
    return title + " (Edition " + edition + ") - ISBN-13: " + isbn;
  }
}
