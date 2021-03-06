package blackjack;

import java.util.Stack;
import java.util.Random;

public class Deck {
	private Stack<Card> stack;
	
	private Card[] cards = new Card[52];
	private String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	private String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
	
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
	
	public String[] getRanks() { return ranks; }
	
	public Stack<Card> getStack() { return stack; }
	
	// Generates all of the cards that will go in the stack unshuffled
	public void generate() {
		for (int i = 0; i < ranks.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				cards[size++] = new Card(0, ranks[i], suits[j]);
				stack.add(cards[size - 1]);
			}
		}
	}
	
	// Fisher-Yates shuffle. Emptys the stack from generate() and shuffles the cards in the array, then re-added to the stack
	public void shuffle() {
		Random r = new Random();
		stack.clear(); 
		
		// Swaps cards[i] with a random position in the array then, puts that card into the random position
		for (int i = 0; i < size; i++) {
			int rand = r.nextInt(size - 1);
			Card card = cards[i];
			cards[i] = cards[rand];
			cards[rand] = card;
		}
		
		// Adds the cards to the stack once it's been shuffled
		for (Card c : cards) { stack.add(c); }
	}
	
	// Draw a card from the deck and return it.
	public Card draw() {
		size--;
		return stack.pop();
	}
	
	// Displays the entirety of the deck to the console.
	public void showStack() {
		for (Card c : stack) {
			System.out.println(c.toString() );
			System.out.println(c.getValue() );
		}
	}
}
