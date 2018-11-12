package blackjack;

public class Card implements Comparable<Card> {
	private int value; // 0 to 10
	private String rank, suit;
	
	public Card() {
		value = 0;
		rank = suit = "";
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
	public int compareTo(Card c) { return this.getValue() - c.getValue(); }
	
	public String toString() { return rank + " of " + suit; }

}
