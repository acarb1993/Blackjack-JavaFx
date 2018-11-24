package blackjack;

public class Card implements Comparable<Card> {
	private int value; // Integer from 0 to 10
	private String rank, suit;
	
	// Constructors
	public Card() {
		value = 0;
		rank = suit = "";
	}
	
	public Card(String r, String s) {
		value = 0;
		rank = r;
		suit = s;
	}
	
	public Card(int v, String r, String s) {
		value = v;
		rank = r;
		suit = s;
	}
	
	// Accessor Methods
	public int getValue() { return value; }
	
	public String getRank() { return rank; }
	
	public String getSuit() { return suit; }
	
	public Card getCard() { return this; }
	
	// Mutator Methods
	public void setValue(int v) { value = v; }
	
	public void setRank(String r) { rank = r; }
	
	public void setSuit(String s) { suit = s; }
	
	@Override
	public int compareTo(Card c) { return getValue() - c.getValue(); }
	
	public String toString() { return rank + " of " + suit + " has a value of " + value; }

}
