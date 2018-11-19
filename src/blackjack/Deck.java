package blackjack;

import java.util.Stack;
import java.util.Random;

public class Deck {
	private Stack<Card> stack;
	
	private Card[] cards = new Card[52];
	private String[] suits = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	private String[] faces = {"Clubs", "Diamonds", "Hearts", "Spades"};
	
	
	int capacity, size;
	
	public Deck() {
		stack = new Stack<Card>();
		capacity = 52;
		size = 0;
	}
	
	// Accessor Methods
	public int getSize() { return size; }
	
	public int getCapacity() { return capacity; }
	
	public String[] getSuits() { return suits; }
	
	public String[] getFaces() { return faces; }
	
	public Stack<Card> getStack() { return stack; }
	
	// Generates all of the cards that will go in the deck unshuffled
	public void generate() {
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < faces.length; j++) {
				cards[size++] = new Card(0, suits[i], faces[j]);
				stack.add(cards[size - 1]);
			}
		}
	}
	
	// Fisher-Yates shuffle. Emptys the deck from generate() and shuffles the cards in the array, then re-added to the deck
	public void shuffle() {
		Random r = new Random();
		stack.clear();
		
		// Swaps cards[i] with a random position in the deck then, puts that card into the random position
		for (int i = 0; i < size; i++) {
			int rand = r.nextInt(size - 1);
			Card card = cards[i];
			cards[i] = cards[rand];
			cards[rand] = card;
		}
		
		// Adds the cards to the deck once it's been shuffled
		for (Card c : cards) { stack.add(c); }
	}
	
	// Draw a card from the deck and return it.
	public Card draw() throws Exception {
		if (size <= 0) { throw new Exception("Error: Deck is empty!"); }
		
		else {
			size--;
			return stack.pop();
		}
	}
	
	// Displays the entirity of the deck to the console.
	public void showStack() {
		for (Card c : stack) {
			System.out.println(c.toString() );
			System.out.println(c.getValue() );
		}
	}
}
