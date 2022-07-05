package jdk1_5examples.enums;


/**
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public class UseCards {
  /**
   * The Card class, above, contains a static factory that returns a deck,
   * but there is no way to get an individual card from its rank and suit.
   * Merely exposing the constructor would destroy the singleton property
   * (that only a single instance of each card is allowed to exist). Here
   * is how to write a static factory that preserves the singleton property,
   * using a nested EnumMap:
   */

/*

  private static Map<Suit, Map<Rank, Card>> table = new EnumMap<Suit, Map<Rank,
      Card>> (Suit.class);

  static {
    for (Suit suit : Suit.values()) {
      Map<Rank, Card> suitTable = new EnumMap<Rank, Card> (Rank.class);
      for (Rank rank : Rank.values()) {
        suitTable.put(rank, new Card(rank, suit));
      }
      table.put(suit, suitTable);
    }
  }

  public static Card valueOf(Rank rank, Suit suit) {
    return table.get(suit).get(rank);
  }
 */

  /**
   * The EnumMap (table) maps each suit to an EnumMap that maps each rank to
   * a card. The lookup performed by the valueOf method is internally
   * implemented as two array accesses, but the code is much clearer and safer.
   * In order to preserve the singleton property, it is imperative that the
   * constructor invocation in the prototype deck initialization in Card be
   * replaced by a call to the new static factory:
   */

/*
  // Initialize prototype deck
  static {
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        protoDeck.add(Card.valueOf(rank, suit));
      }
    }
  }
*/
  /**
   * It is also imperative that the initialization of table be placed above the
   * initialization of protoDeck, as the latter depends on the former.
   */
}





