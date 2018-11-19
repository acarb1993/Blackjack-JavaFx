package blackjack;

import java.util.LinkedList;

public class Hand {
	private LinkedList<Card> hand;
	
	private int totalValue, size; //total value is the combined value of the cards in the hand
	
	public Hand() {
		hand = new LinkedList<Card>();
		totalValue = size = 0;
	}
	
	// Accessor Methods
	public int getSize() { return size; }
	
	public int getTotalValue() { return totalValue; }
	
	// Gets a card at a given index from the hand
	public Card getCardAtIndex(int index) {
		return hand.get(index);
	}
	
	// Adds a card to the hand from a given deck
	public void addToHand(Card c) { 
		hand.add(c); 
		size++;
	}
	
	// Displays the hand to the console
	public void showHand() {
		for (Card c : hand) { 
			System.out.println(c.toString() );
		}
	}
}
