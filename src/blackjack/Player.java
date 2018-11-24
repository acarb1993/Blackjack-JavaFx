package blackjack;

public class Player implements Comparable<Player>{
	private Hand hand;
	private int totalCardValue;
	
	public Player() {
		hand = new Hand();
		totalCardValue = 0;
	}
	
	// Accessor Methods
	public Hand getHand() { return hand; }
	
	public int getTotalCardValue() { return totalCardValue; }
	
	// Mutator Methods
	public void setTotalCardValue(int tcv) { totalCardValue = tcv; }
	
	// Class Methods
	public void addToPlayerHand(Card c) { 
		hand.addToHand(c); 
		
		totalCardValue += c.getValue();
		
		checkForAces(c);
	}
	
	public void checkForAces(Card c) {
		for (int i = 0; i < hand.getSize(); i++) {
			if (hand.getCardAtIndex(i).getRank().equals("Ace") && totalCardValue > 21 && hand.getCardAtIndex(i).getValue() != 1 ) {
				hand.getCardAtIndex(i).setValue(1);
				totalCardValue -= 9;
				if (totalCardValue < 21)
					break;
			}		
		}
	}
	
	public int compareTo(Player p) { return totalCardValue - p.getTotalCardValue(); }
}
