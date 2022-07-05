package jdk1_5examples.enums;


import java.util.*;


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

public class Deal {
  public static void main(String args[]) {
    int numHands = 4;//Integer.parseInt(args[0]);
    int cardsPerHand = 5;//Integer.parseInt(args[1]);
    List<Card> deck = Card.newDeck();
    Collections.shuffle(deck);
    for (int i = 0; i < numHands; i++) {
      System.out.println(deal(deck, cardsPerHand));
    }
  }

  public static ArrayList<Card> deal(List<Card> deck, int n) {
    int deckSize = deck.size();
    List<Card> handView = deck.subList(deckSize - n, deckSize);
    ArrayList<Card> hand = new ArrayList<Card> (handView);
    handView.clear();
    return hand;
  }
}
