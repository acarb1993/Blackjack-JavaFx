package blackjack;

import java.util.Stack;
import java.util.Random;

public class Deck {
	private Stack<Card> deck;
	
	private Card[] cards = new Card[52];
	private String[] suits = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	private String[] faces = {"Clubs", "Diamonds", "Hearts", "Spades"};
	
	
	int capacity, size;
	
	public Deck() {
		deck = new Stack<Card>();
		capacity = 52;
		size = 0;
	}
	
	public int getSize() { return size; }
	
	public int getCapacity() { return capacity; }
	
	// Generates all of the cards that will go in the deck
	public void generate() {
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < faces.length; j++) {
				cards[size++] = new Card(0, suits[i], faces[j]);
			}
		}
	}
	
	// Fisher-Yates shuffle
	public void shuffle() {
		Random r = new Random();
		
		// Swaps cards[i] with a random position in the deck then, puts that card into the random position
		for (int i = 0; i < size; i++) {
			int rand = r.nextInt(size - 1);
			Card card = cards[i];
			cards[i] = cards[rand];
			cards[rand] = card;
		}
		
		// Adds the cards to the deck once it's been shuffled
		for (Card c : cards) {  deck.add(c); }
		
		for (Card c : deck) { 
			System.out.println(c.toString() ); 
		}
	}
	
	public Card draw() {
		size--;
		return deck.pop();
	}
}
